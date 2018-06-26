package hadoop.rpc.server;

/**
 * @ClassName LoginServiceInterface
 * @Description TODO
 * @Author liang
 * @Date 2018/6/25 16:47
 * @Version 1.0
 **/
public interface LoginServiceInterface {
    public static final long versionID = 1L;
    public String login(String username,String password);
}
