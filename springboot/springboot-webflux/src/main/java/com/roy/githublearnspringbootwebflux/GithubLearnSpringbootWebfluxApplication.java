package com.roy.githublearnspringbootwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Indexed;

@SpringBootApplication
@Indexed // 不需要ComponentScan运行时扫描，而是编译环节事先扫描到META-INF/spring.componets文件里，启动更快
public class GithubLearnSpringbootWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubLearnSpringbootWebfluxApplication.class, args);
	}

}
