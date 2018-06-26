package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ServerCenter
 * @Description TODO
 * 服务中心实现类
 * @Author liang
 * @Date 2018/6/25 13:53
 * @Version 1.0
 **/
public class ServerCenter implements Server {
    //用来创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待，定长线程池的大小通常根据系统资源进行设置
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static final  HashMap<String,Class> serviceRegistry = new HashMap<String,Class>();

    private static boolean isRunning = false;

    private static int port;

    public ServerCenter(int port){
        this.port = port;
    }
    @Override
    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    @Override
    public void start() throws Exception {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        System.out.println("start server");
        try {
            while(true){
                //1.监听客户端的TCP连接，接到TCP连接后，将其封装成task，由线程池执行
                executor.execute(new ServiceTask(server.accept()));
            }
        }finally {
            server.close();
        }
    }

    @Override
    public void register(Class serviceInterface, Class impl) {
        serviceRegistry.put(serviceInterface.getName(),impl);
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPort() {
        return port;
    }

    class ServiceTask implements Runnable{
        Socket clent = null;
        public ServiceTask(Socket client){
            this.clent = client;
        }
        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try{
                //将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果
                input = new ObjectInputStream(clent.getInputStream());
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class<?>[] parameterType = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[])input.readObject();
                Class serviceClass = serviceRegistry.get(serviceName);
                if(serviceClass == null){
                    throw  new ClassNotFoundException(serviceName + "not found");
                }
                Method method = serviceClass.getMethod(methodName,parameterType);
                Object result = method.invoke(serviceClass.newInstance(),arguments);
                //将执行结果反序列化，通过socket发送给客户端
                output = new ObjectOutputStream(clent.getOutputStream());
                output.writeObject(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (clent != null) {
                    try {
                        clent.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
