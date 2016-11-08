package aroen.com.studiopushtest;

/**
 * Created by zhanghongyu on 16/11/8.
 */

public class Student {
    private String name;
    private String age;
    private String sex;
    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }
}
