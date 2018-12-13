package roy.github.learn.springall.springlifecycle.initialization;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * BeanFactoryAware 的实例化和方法运行是在  AbstractApplicationContext.finishBeanFactoryInitialization 里面
 * 针对单个bean的
 * Created by roy on 2018/12/13.
 */
@Component
public class MyBeanFactoryAware implements BeanFactoryAware {

    public MyBeanFactoryAware(){
        System.out.println("这是BeanFactoryAware的构造器！！！");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

}
