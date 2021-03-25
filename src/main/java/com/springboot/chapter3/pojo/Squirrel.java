package com.springboot.chapter3.pojo;

import com.springboot.chapter3.pojo.definition.Animal;
import org.springframework.stereotype.Component;

public class Squirrel implements Animal {

    @Override
    public void use() {
        System.out.println("松鼠可以采松果");
    }

    public Squirrel() {
        System.out.println("Squirrel通过xml加载到IOC容器中！");
    }
}
