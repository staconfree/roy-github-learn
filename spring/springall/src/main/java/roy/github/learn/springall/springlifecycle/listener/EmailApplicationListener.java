package roy.github.learn.springall.springlifecycle.listener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by roy on 2018/12/13.
 */
@Component
public class EmailApplicationListener implements ApplicationListener,ApplicationContextAware {


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof EmailEvent){
            System.out.println("----emailEvent---");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EmailEvent emailEvent = new EmailEvent("hello");
        applicationContext.publishEvent(emailEvent);
    }


    public static class EmailEvent extends ApplicationEvent {
        private String address;
        private String content;
        public EmailEvent(Object source) {
            super(source);
        }
        public EmailEvent(Object source,String address,String content) {
            super(source);
            this.address=address;
            this.content=content;
        }
    }

}
