package proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @ClassName Test
 * @Description TODO
 * @Author liang
 * @Date 2018/6/25 15:54
 * @Version 1.0
 **/
public class Test {
    public static void main(String [] args){
        //创建被代理实例对象
        Person zs = new Student("张三");
        //创建一个动态代理handler
        InvocationHandler stuHandler = new StuInvocationHandler<Person>(zs);
        //创建动态代理对象来代理zhangsan
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),new Class<?>[]{Person.class},stuHandler);
        //执行代理者
        stuProxy.giveMoney();
    }
}
