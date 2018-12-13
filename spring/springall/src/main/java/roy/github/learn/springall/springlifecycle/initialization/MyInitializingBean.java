package roy.github.learn.springall.springlifecycle.initialization;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * InitializingBean,ApplicationContextAware 实例化触发和方法运行是在  AbstractApplicationContext.finishBeanFactoryInitialization 里面
 * 只对单个bean生效
 * Created by roy on 2018/12/13.
 */
@Component
public class MyInitializingBean implements InitializingBean,ApplicationContextAware {

    @PostConstruct // 在构造函数执行完之后执行,等同于配置bean xml里面的init-method
    public void initMethod(){
        System.out.println("bean初始化之后执行initMethod");
    }

    @PreDestroy // 在bean销毁之前执行,等同于配置bean xml里面的destory-method
    public void destroyMethod(){
        System.out.println("bean销毁之前执行destroyMethod");
    }

    public MyInitializingBean(){
        System.out.println("初始化MyInitializingBean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--afterPropertiesSet--");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("--applicationContext--");
    }
}
