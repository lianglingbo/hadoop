package proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName StuInvocationHandler
 * @Description TODO
 * 实现InvocationHandler接口，持有一个被代理对象的实例target
 *
 * @Author liang
 * @Date 2018/6/25 15:46
 * @Version 1.0
 **/
public class StuInvocationHandler<T> implements InvocationHandler {
    //持有的被代理对象
    T target;
    public StuInvocationHandler(T target){
        this.target = target;
    }

    /***
     *
     * @param proxy 动态代理对象
     * @param method 执行的方法
     * @param args  调用目标方法时传入的实参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行"+method.getName()+"方法");
        //插入自定义方法
        System.out.println("执行自定义方法");
        //执行代理方法
        Object result = method.invoke(target,args);

        return result;
    }
}
