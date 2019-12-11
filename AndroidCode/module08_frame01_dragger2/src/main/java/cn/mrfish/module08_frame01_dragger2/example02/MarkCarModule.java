package cn.mrfish.module08_frame01_dragger2.example02;

import dagger.Module;
import dagger.Provides;

/**
 * @author Jack
 * @time 19-10-21 下午5:35
 * @describe
 */
@Module
public class MarkCarModule {

    public MarkCarModule(){ }

    @Provides
    Engine provideEngine(){
        return new Engine("gear");
    }

}
