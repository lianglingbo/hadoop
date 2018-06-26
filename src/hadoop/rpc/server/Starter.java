package hadoop.rpc.server;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.RPC.Server;

import java.io.IOException;

/**
 * @ClassName Starter
 * @Description TODO
 * @Author liang
 * @Date 2018/6/25 16:50
 * @Version 1.0
 **/
public class Starter {
    public static void main(String [] args) throws IOException {

        Builder builder = new RPC.Builder(new Configuration());
        //绑定地址端口服务，对象
        builder.setBindAddress("192.168.31.251").setPort(10000).setProtocol(LoginServiceInterface.class).setInstance(new LoginServiceImpl());

        Server server = builder.build();
        server.start();


    }

}
