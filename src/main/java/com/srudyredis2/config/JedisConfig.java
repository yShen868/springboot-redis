package com.srudyredis2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 郑悦恺
 * @Classname JedisConfig
 * @Description TODO
 * @Date 2020/3/10 13:12
 */

@Configuration
public class JedisConfig {

    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port ;
    @Value("${spring.redis.password}")
    private String password ;
    @Value("${spring.redis.timeout}")
    private int timeout ;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive ;

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);

//        ...
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,password);

        logger.info("redis连接成功");



        return jedisPool;
    }


}
