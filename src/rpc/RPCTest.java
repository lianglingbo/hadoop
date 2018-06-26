package rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @ClassName RPCTest
 * @Description TODO
 * 测试类
 * @Author liang
 * @Date 2018/6/25 14:33
 * @Version 1.0
 **/
public class RPCTest {
    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Server serviceServer = new ServerCenter(8088);
                    serviceServer.register(HelloService.class, HelloServiceImply.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));
    }

}
