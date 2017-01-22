package com.hqjg;

import com.hqjg.mapper.StudentMapper_fail;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by ygma on 16-9-1.
 */

@SpringBootApplication
@ServletComponentScan
//@EnableAutoConfiguration
@ComponentScan(basePackages = {"com/hqjg"})
//@MapperScan(basePackages = {"com/hqjg/mapper"},
//        basePackageClasses = {StudentMapper_fail.class})
@EnableTransactionManagement
@RestController
public class Application {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
