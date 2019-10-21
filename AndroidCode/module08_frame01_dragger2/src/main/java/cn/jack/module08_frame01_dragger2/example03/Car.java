package cn.jack.module08_frame01_dragger2.example03;

import javax.inject.Inject;


/**
 * @author Jack
 * @time 19-10-21 下午5:29
 * @describe
 */
public class Car {

    @QualifierA
    @Inject
    Engine engineA;

    @QualifierB
    @Inject
    Engine engineB;

    public Car() {
        DaggerCarComponent.builder().markCarModule(new MarkCarModule())
                .build().inject(this);
    }

    public Engine getEngineA() {
        return this.engineA;
    }

    public Engine getEngineB() {
        return this.engineB;
    }
}
