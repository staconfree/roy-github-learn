package roy.github.learn.springboot.servlet;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by Administrator on 2018/11/17 0017.
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "roy.github.learn.springboot.servlet.web.servlet")
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }

}
