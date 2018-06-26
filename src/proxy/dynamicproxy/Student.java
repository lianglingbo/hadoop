package proxy.dynamicproxy;


/**
 * @ClassName Student
 * @Description TODO
 * 学生类，可实现交钱的动作
 * @Author liang
 * @Date 2018/6/25 15:20
 * @Version 1.0
 **/
public class Student implements Person{
    private String name;
    public Student(String name){
        this.name = name;
    }
    @Override
    public void giveMoney() {
        System.out.println(name+" 交了50钱");
    }
}
