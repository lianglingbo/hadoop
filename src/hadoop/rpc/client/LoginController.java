package hadoop.rpc.client;

import hadoop.rpc.server.LoginServiceInterface;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author liang
 * @Date 2018/6/25 16:59
 * @Version 1.0
 **/
public class LoginController {
    public static void main(String [] args) throws IOException {
      LoginServiceInterface proxy =  RPC.getProxy(LoginServiceInterface.class,1L,new InetSocketAddress("127.0.0.1",10000),new Configuration());
      String result =  proxy.login("liang","123");
        System.out.println(result);
    }
}
