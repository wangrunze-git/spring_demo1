package com.example.demo;

import com.springboot.chapter4.aspect.MyAspect;
import com.springboot.chapter4.aspect.MyAspect1;
import com.springboot.chapter4.aspect.MyAspect2;
import com.springboot.chapter4.aspect.MyAspect3;
import com.springboot.chapter5.dao.MyBatisUserDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

//指定扫描的包
//@SpringBootApplication(scanBasePackages = {"com.springboot.chapter4.aspect"})
@SpringBootApplication()
@ComponentScan(basePackages = {"com.springboot.chapter5"})
//定义JPA接口扫描包路径
//@EnableJpaRepositories(basePackages = "com.springboot.chapter5.dao")
//定义实体Bean扫描包路径
//@EntityScan(basePackages = "com.springboot.chapter5.pojo")
//----------mybatis扫描
//定义MyBatis的扫描
@MapperScan(
        //指定扫描包
        basePackages = "com.springboot.chapter5.*",
        //指定sqlSessionFactory，如果sqlSessionTemplate被指定，则作废
        //如果你的项目中不存在多个SqlSessionFactory或者SqlSessionTemplate，那么你可以完全不用配置sqlSessionFactoryRef或者sqlSessionTemplateRef
        sqlSessionFactoryRef = "sqlSessionFactory",
        //指定sqlSessionTemplate，将忽略sqlSessionFactory的配置
        sqlSessionTemplateRef = "sqlSessionTemplate",
        annotationClass = Repository.class
        //也可以使用@Mapper注解
//        annotationClass = Mapper.class
)
public class DemoApplication {

    /*//定义切面
    @Bean(name = "myAspect")
    public MyAspect initMyAspect(){
        return new MyAspect();
    }

    @Bean(name="myAspect2")
    public MyAspect2 initMyAspect2(){
        return new MyAspect2();
    }
    @Bean(name="mAspect1")
    public MyAspect1 initMyAspect1(){
        return new MyAspect1();
    }
    @Bean(name="myAspect3")
    public MyAspect3 initMyAspect3(){
        return new MyAspect3();
    }*/

    //-------------mybatis配置
    //-------------使用MapperFactoryBean
    /*@Autowired
    SqlSessionFactory sqlSessionFactory = null;
    //定义一个MyBatis的Mapper接口
    @Bean
    public MapperFactoryBean<MyBatisUserDao> initMyBatisUserDao(){
        MapperFactoryBean<MyBatisUserDao> bean = new MapperFactoryBean<>();
        bean.setMapperInterface(MyBatisUserDao.class);
        bean.setSqlSessionFactory(sqlSessionFactory);
        return bean;
    }*/

    //------------使用MapperScannerConfigurer

    /**
     * 配置MyBatis接口扫描
     * @return 返回扫描器
     */
    /*@Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        //定义扫描器实例
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //加载SqlSessionFactory,Spring Boot会自动生产SqlSessionFactory实例
        //定义扫描的包
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.springboot.chapter5.*");
        //限定被标注@Repository的接口才能被扫描
        mapperScannerConfigurer.setAnnotationClass(Repository.class);
        return mapperScannerConfigurer;
    }*/

    //启动切面
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }



}
