package com.atguigu.springcloud.entities;

import lombok.*;

import java.io.Serializable;

/**
 * @author lswstart
 * @create 2020-09-12-9:26
 */
//lombok 简化javabean开发
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Payment implements Serializable {
    private Integer  id;
    private String serial;
}
