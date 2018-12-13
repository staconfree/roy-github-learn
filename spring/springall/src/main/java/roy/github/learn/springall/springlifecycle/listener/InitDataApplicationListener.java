package roy.github.learn.springall.springlifecycle.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * ApplicationListener<ContextRefreshedEvent> 方法的运行在 AbstractApplicationContext.finishRefresh 里面
 * Created by roy on 2018/12/13.
 */
@Component
public class InitDataApplicationListener implements ApplicationListener<ContextRefreshedEvent>{
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent()==null) {//root application context 没有parent
            // 这里写下将要初始化的内容
            System.out.println("---初始化数据到内存里---");
        }
    }
}
