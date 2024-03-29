package cn.mrfish.module04_aac_databinding;

import androidx.databinding.BaseObservable;

/**
 * @author Jack
 * @time 19-9-27 上午9:45
 * @describe
 */
public class Student extends BaseObservable {

    private String name;
    private String addr;

    public Student() {
    }

    public Student(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
