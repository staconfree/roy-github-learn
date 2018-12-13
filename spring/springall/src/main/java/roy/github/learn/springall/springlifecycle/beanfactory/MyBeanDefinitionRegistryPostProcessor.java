package roy.github.learn.springall.springlifecycle.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by roy on 2018/12/13.
 * BeanDefinitionRegistryPostProcessor 触发实例化和接口运行是在 AbstractApplicationContext.invokeBeanFactoryPostProcessors 里面
 * 参考mybatis的 MapperScannerConfigurer 类实现
 * @AbstractApplicationContext
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("-----BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry------");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
