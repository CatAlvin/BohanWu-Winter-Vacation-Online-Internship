package org.bohan.newsportal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bohan
 */
@SpringBootApplication
@MapperScan("org.bohan.newsportal.mapper")
public class newsPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(newsPortalApplication.class, args);
    }

}
