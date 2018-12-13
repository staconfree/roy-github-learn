package roy.github.learn.springall.springlifecycle.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * BeanFactoryPostProcessor 触发实例化和接口运行是在 AbstractApplicationContext.invokeBeanFactoryPostProcessors 里面触发
 * 往 BeanFactoryPostProcessor 注入bean定义
 * Created by roy on 2018/12/13.
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        super();
        System.out.println("这是BeanFactoryPostProcessor实现类构造器！！");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("--BeanFactoryPostProcessor.postProcessBeanFactory--");
    }

}
