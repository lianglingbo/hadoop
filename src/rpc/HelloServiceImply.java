package rpc;

/**
 * @ClassName HelloServiceImply
 * @Description TODO
 * @Author liang
 * @Date 2018/6/25 13:51
 * @Version 1.0
 **/
public class HelloServiceImply implements  HelloService{
    @Override
    public String sayHi(String name) {
        return "Hi  "+name;
    }
}
