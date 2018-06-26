package hadoop.rpc.server;

/**
 * @ClassName LoginServiceImpl
 * @Description TODO
 * @Author liang
 * @Date 2018/6/25 16:48
 * @Version 1.0
 **/
public class LoginServiceImpl implements LoginServiceInterface {
    @Override
    public String login(String username,String password) {

        return  username + " logged in success";
    }
}
