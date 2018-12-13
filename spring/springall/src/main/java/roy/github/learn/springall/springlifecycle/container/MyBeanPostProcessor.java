package roy.github.learn.springall.springlifecycle.container;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor 实例化的触发是在 AbstractApplicationContext.registerBeanPostProcessors 里面,
 * BeanPostProcessor 方法的运行一部分(spring底层的bean)在 AbstractApplicationContext.onRefresh 里面,一部分在 AbstractApplicationContext.finishBeanFactoryInitialization 里面
 * 在bean初始化之前和之后对bean做处理(针对所有bean)
 * Created by roy on 2018/12/13.
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
       super();
        System.out.println("这是BeanPostProcessor实现类构造器！！");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("myInitializingBean")){
            System.out.println("BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！"+beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("myInitializingBean")){
            System.out.println("BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！"+beanName);
        }
        return bean;
    }
}
