package proxy.staticproxy;


/**
 * @ClassName Test
 * @Description TODO
 * @Author liang
 * @Date 2018/6/25 15:26
 * @Version 1.0
 **/
public class Test {

    private static StudentProxy proxy;

    public static void main(String [] args){
        Person zs = new Student("张三");
        //自己直接交钱
        zs.giveMoney();
        //让代理去交钱
        proxy = new StudentProxy(zs);
        proxy.giveMoney();
    }
}
