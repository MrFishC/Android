package cn.jack.module08_frame01_dragger2;

//import cn.jack.module08_frame01_dragger2.example01.Car;
//import cn.jack.module08_frame01_dragger2.example02.Car;
//import cn.jack.module08_frame01_dragger2.example03.Car;
import cn.jack.module08_frame01_dragger2.example04.Car;

/**
 * @author Jack
 * @time 19-10-21 下午5:32
 * @describe
 *
 *  Dagger2的基本使用
 *
 *  参考：https://juejin.im/post/5857f70361ff4b006cb0d9fd?utm_source=gold_browser_extension#heading-12
 *
 *  实现原理
 *      Dagger2根据注解生成各种代码的原理        待补充
 *      Dagger2生成的各种类如何帮我们实现依赖注入
 *
 *      通过example02分析原理
 *          Car_MembersInjector中的create()用于实例化自己，
 */
public class TestDrgger2Demo {

    public static void main(String[] args){

        function01();
    }

    private static void function01() {
//        Car car = new Car();
//        car.getEngine().run();

//        Car car = new Car();
//        car.getEngineA().printGearName();
//        car.getEngineB().printGearName();

        Car car = new Car();
        System.out.println(car.getEngineA().hashCode());
        System.out.println(car.getEngineB().hashCode());

    }


}
