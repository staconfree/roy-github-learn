package roy.github.learn.springall.springlifecycle.container;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

/**
 * 针对所有bean有效
 * InstantiationAwareBeanPostProcessorAdapter 是spring对InstantiationAwareBeanPostProcessor的包装实现，两者功能类似，一般我们用此类来操作
 * Created by roy on 2018/12/13.
 */
@Component
public class MyInstantiationAwareBeanPostProcessorAdapter extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessorAdapter(){
        System.out.println("这是InstantiationAwareBeanPostProcessorAdapter实现类构造器!!");
    }
}
