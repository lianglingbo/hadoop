package proxy.staticproxy;

/**
 * @ClassName StudentProxy
 * @Description TODO
 * 学生代理类，可以代理学生产生行为
 * @Author liang
 * @Date 2018/6/25 15:23
 * @Version 1.0
 **/
public class StudentProxy implements Person{
    //被代理类的引用
    Student stu;
    public StudentProxy(Person stu){
        //只代理学生对象
        if(stu.getClass() == Student.class){
            this.stu=(Student)stu;
        }
    }
    //调用学生的行为
    @Override
    public void giveMoney() {
        System.out.println("代理开始工作");
        stu.giveMoney();
        System.out.println("代理结束工作");
    }
}
