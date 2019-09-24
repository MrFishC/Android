package cn.jack.module02_recycleivew.cardlist;

/**
 * @author Jack
 * @time 19-9-24 下午2:01
 * @describe
 */
public class CardBean {

    private String name;
    private String age;
    private String constellation;
    private int icon;

    public CardBean(String name, String age, String constellation, int icon) {
        this.name = name;
        this.age = age;
        this.constellation = constellation;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "CardBean{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", constellation='" + constellation + '\'' +
                ", icon=" + icon +
                '}';
    }

}
