package com.srudyredis2.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 郑悦恺
 * @Classname User
 * @Description TODO
 * @Date 2020/3/10 13:02
 */
@Data
public class User implements Serializable {
    private String id;
    private String name;
    private Integer age;


}
