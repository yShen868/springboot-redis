package com.srudyredis2.service.impl;

import com.srudyredis2.model.User;
import com.srudyredis2.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 郑悦恺
 * @Classname Userservice
 * @Description TODO
 * @Date 2020/3/10 13:38
 */
@Service
@Log
public class UserServiceImpl implements UserService {
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String,String> stringValueOperations;

    @Resource(name = "redisTemplate")
    private HashOperations<String,String,User> hashOperations;




    @Override
    public String getString(String key) {

        Jedis jedis = jedisPool.getResource();

        if (jedis.exists(key)) {
            System.out.println(jedis.get(key));
            log.info(jedis.get(key));
            jedis.close();
            return jedis.get(key);
        }else {
            jedis.set(key,key);
            jedis.close();


            ValueOperations valueOperations = redisTemplate.opsForValue();
            User user = new User();

            user.setName("hahaha");
            user.setId("888");
            user.setAge(111);
            hashOperations.put("user",user.getId(),user);
            User user1 = hashOperations.get("user", user.getId());
            System.out.println(user1);
            Object test = valueOperations.get("test");
            System.out.println(test);
            return jedis.get(key);


        }



    }

    @Override
    public User selectById(String id) {

        String keys = "user:"+id;
        Jedis jedis = jedisPool.getResource();
        if (jedis.exists(keys)) {
            log.info("存在查询");
            Map<String, String> map = jedis.hgetAll(keys);
            User user = new User();
            user.setAge(Integer.parseInt(map.get("age")));
            user.setId(map.get("id"));
            user.setName(map.get("name"));
            return user;
        }else {
            User user = new User();
            user.setAge(99);
            user.setId("11");
            user.setName("小明");
            log.info("数据库查出来");

            Map<String,String> map= new HashMap<>();
            map.put("id",user.getId());
            map.put("name",user.getName());
            map.put("age",user.getAge().toString());
            jedis.hset(keys,map);
            return user;

        }

    }
}
