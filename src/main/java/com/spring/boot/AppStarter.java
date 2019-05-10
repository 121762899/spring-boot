package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author: zhangxuesong 
 * @Description: spring boot 启动方式1: 直接在类上加注解
 *                                    @SpringBootApplication 被 @Configuration、@EnableAutoConfiguration、@ComponentScan 注解所修饰，
 *                                    扫包范围：在启动类上加上@SpringBootApplication注解,当前包及子包下所有的类都可以被扫到
 *                           启动方式2: 在HelloController 上加注解如HelloeController类
 *                           启动方式3: 用上面三个注解代替SpringBootApplication,如注释掉的部分
 * @date:   2019年5月10日 上午11:19:06   
 * @Param 
 *
 */
@SpringBootApplication
public class AppStarter {

    
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }
    
}
/*@Configuration
@ComponentScan(basePackages = "com.spring.boot.controller")
@EnableAutoConfiguration
public class AppStarter {

    
    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args);
    }
    
}*/
