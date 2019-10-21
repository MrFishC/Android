package cn.jack.module08_frame01_dragger2.example04;

import dagger.Module;
import dagger.Provides;

/**
 * @author Jack
 * @time 19-10-21 下午5:35
 * @describe
 */
@Module   //用于标注提供依赖的类
public class MarkCarModule {

    public MarkCarModule(){ }

    @CarScope
    @Provides
    Engine provideEngine(){
        return new Engine("gear");
    }

}
