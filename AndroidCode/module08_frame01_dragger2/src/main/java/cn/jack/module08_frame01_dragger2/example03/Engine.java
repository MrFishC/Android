package cn.jack.module08_frame01_dragger2.example03;

import javax.inject.Inject;

/**
 * @author Jack
 * @time 19-10-21 下午5:29
 * @describe
 */
public class Engine {

    private String gear;

    @Inject
    Engine(String gear){
        this.gear = gear;
    }

    public void printGearName(){
        System.out.println("GearName:" + gear);
    }

}
