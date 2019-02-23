package com.elong.comment.comment.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configurable
@ComponentScan(basePackages = {"com.elong.comment.comment"}) // 扫描该包路径下的所有spring组件
@EnableJpaRepositories(basePackages= {"com.elong.comment.comment.dao"}) // JPA扫描该包路径下的Repositorie
@EntityScan(basePackages = {"com.elong.comment.comment.model.entity"}) // 扫描实体类
public class SpringConfiguration {

    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}


