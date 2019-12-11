package cn.mrfish.module08_frame01_dragger2.example02;

import javax.inject.Inject;


/**
 * @author Jack
 * @time 19-10-21 下午5:29
 * @describe
 */
public class Car {

    @Inject
    Engine engine;

    public Car() {
        DaggerCarComponent.builder()
                .markCarModule(new MarkCarModule())
                .build().inject(this);
    }

    public Engine getEngine() {
        return this.engine;
    }

}
