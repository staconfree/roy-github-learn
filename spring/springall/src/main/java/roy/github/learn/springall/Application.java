package roy.github.learn.springall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by roy on 2018/11/24 0024.
 * spring容器的启动，查看 AbstractApplicationContext.refresh 方法
 */
@SpringBootApplication
public class Application {

    //
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
