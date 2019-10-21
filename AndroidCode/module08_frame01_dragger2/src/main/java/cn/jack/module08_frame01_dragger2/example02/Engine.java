package cn.jack.module08_frame01_dragger2.example02;

import javax.inject.Inject;

/**
 * @author Jack
 * @time 19-10-21 下午5:29
 * @describe
 */
public class Engine {

    @Inject
    Engine(String gear){}

    public void run(){
        System.out.println("引擎转起来了~~~ 02 ");
    }

}
