package roy.github.learn.springall.springlifecycle.factorybean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by roy on 2018/12/13.
 */
@Component
public class MyFactoryBean implements FactoryBean<MyFactoryBean.Cat>,ApplicationContextAware {

    private ApplicationContext applicationContext;

    @PostConstruct // 在构造函数执行完之后执行,等同于配置bean xml里面的init-method
    public void initMethod(){
        try {
            Cat cat = this.getObject();
            System.out.println(cat.toString());
            Cat cat1 = (Cat)applicationContext.getBean("myFactoryBean");
            System.out.println(cat1);
            MyFactoryBean myFactoryBean = (MyFactoryBean) applicationContext.getBean("&myFactoryBean");
            System.out.println(myFactoryBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cat getObject() throws Exception {
        Cat cat = new Cat();
        cat.setName("mini");
        cat.setColor("black");
        return cat;
    }

    @Override
    public Class<?> getObjectType() {
        return MyFactoryBean.Cat.class;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    public static class Cat{
        private String name;
        private String color;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "CAT[name="+name+",color="+color+"]";
        }
    }


}
