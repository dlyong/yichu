package com.dlyong.yichu.cofig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.dlyong.yichu.dao")
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
}
