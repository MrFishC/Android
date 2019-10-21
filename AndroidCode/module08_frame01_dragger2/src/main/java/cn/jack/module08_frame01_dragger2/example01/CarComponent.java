package cn.jack.module08_frame01_dragger2.example01;

import dagger.Component;

/**
 * @author Jack
 * @time 19-10-21 下午5:29
 * @describe    用来将Engine注入到Car中。
 */
@Component
public interface CarComponent {
    void inject(Car car);
}
