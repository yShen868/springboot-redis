package com.srudyredis2;

import com.srudyredis2.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.JedisPool;

import java.util.*;

@SpringBootTest
class Studyredis2ApplicationTests {


  /*  @Autowired
    private JedisPool jedisPool;

    @Test
    void contextLoads() {

        System.out.println(jedisPool);
    }*/


    @Autowired
    UserService userService;

    @Test
    void t1(){
        String name = userService.getString("yyyy");
        System.out.println(name);
    }


    @Test
    void t2(){
        int a = 1;
        int b = 2;

        int i = a+++b;
    }


    @Test
    void t3(){
        int a = 9876673;
        int b = 0;
        Set<Integer> set = new TreeSet<>();
        int i = 0;

        while (a>0){
            set.add(a%10);
            a = a/10;
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            int sss = iterator.next();
            System.out.println(sss);
            b= (b+sss)*10;
        }
        System.out.println(b/10);
    }





}
