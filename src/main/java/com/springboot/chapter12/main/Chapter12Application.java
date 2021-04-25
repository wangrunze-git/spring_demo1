package com.springboot.chapter12.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.sql.DataSource;


@SpringBootApplication(scanBasePackages = {"com.springboot.chapter12"})
public class Chapter12Application extends WebSecurityConfigurerAdapter{

    private static final Logger logger = LoggerFactory.getLogger(Chapter12Application.class);

    //pbkdf加密私钥
    @Value("${system.user.password.secret}}")
    private String secret = null;

    @Autowired
    private UserDetailsService userDetailsService = null;

    //spring boot配置登录请求连接和"记住我"
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //访问/admin下的请求需要管理员权限
            .authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')")
                //启用remember me功能
            .and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
                //启用HTTP Basic功能
            .and().httpBasic()
                //通过签名后可以访问任何请求
            .and().authorizeRequests().antMatchers("/**").permitAll()
                //设置登录页和默认的跳转路径
            .and().formLogin().loginPage("/login/page")
                .defaultSuccessUrl("/welcome1")
            .and().logout().logoutSuccessUrl("/logout/page");
    }

    /*//Spring表达式设置权限
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //使用Spring表达式限定只有角色ROLE_USER或者ROLE_ADMIN
                .antMatchers("/test/**").access("hasRole('USER') or hasRole('ADMIN')")
                //设置访问权限给角色ROLE_ADMIN，要求是完整登录（非记住我登录）
                .antMatchers(("/admin/page1"))
                .access("hasAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
                //限定"/admin/welcome2"访问权限给角色ROLE_ADMIN，允许不完整登录
                .antMatchers("/admin/page2").access("hasAuthority('ROLE_ADMIN')")
                //使用记住我的功能
                .and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
                //使用Spring Security默认的登录页面
                .and().formLogin()
                //启动HTTP登录验证
                .and().httpBasic();
        //强制使用安全渠道，限定为https请求
        *//*http.requiresChannel().antMatchers("/admin/**").requiresSecure()
                //不使用HTTPS请求
        .and().requiresChannel().antMatchers("/user/**").requiresSecure()
        .and().portMapper().http(8898).mapsTo(8443);*//*
    }*/


    /*//使用Ant风格配置限定
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //限定签名后的权限
        //会采取先配置优先的原则，所以要把具体的配置放到前面配置，把不具体的配置放到后面配置
        http.
                *//*第一段*//*
            authorizeRequests()
                //限定"/test/page1"请求赋予角色ROLE_USER或者ROLE_ADMIN
            .antMatchers("/test/page1", "/test/page2").hasAnyRole("USER", "ADMIN")
                //限定"/admin"下所有请求权限赋予角色ROLE_ADMIN
            .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                //其他路径允许签名后访问
            .anyRequest().permitAll()
                *//*第二段*//*
            //and代表连接词
            //对于没有配置权限的其他请求允许匿名访问
            .and().anonymous()
                *//*第三段*//*
                //使用Spring Security默认的登录页面
            .and().formLogin()
                //启动HTTP基础验证
            .and().httpBasic();
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编码器
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //设置用户密码服务和密码编码器
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }



    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /*
    //注入数据源
    @Qualifier("dataSource_12")
    @Autowired
    private DataSource dataSource = null;

    //使用用户名称查询密码
    String pwdQuery = "select user_name, pwd, available "
            + " from t_user where user_name = ?";

    //使用用户名称查询角色信息
    String roleQuery = "select u.user_name, r.role_name "
            +" from t_user u, t_user_role ur, t_role r "
            + "where u.id = ur.user_id and r.id = ur.role_id "
            + " and u.user_name = ?";

    /**
     * 覆盖WebSecurityConfigurerAdapter用户详情方法
     * @param auth  用户签名管理器构造器
     * @throws Exception
     *//*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编码器
        System.out.println(dataSource);
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Pbkdf2PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(this.secret);
        auth.jdbcAuthentication()
                //密码编码器
        .passwordEncoder(passwordEncoder)
                //数据源
        .dataSource(dataSource)
                //查询用户，自动判断密码是否一致
        .usersByUsernameQuery(pwdQuery)
                //赋予权限
        .authoritiesByUsernameQuery(roleQuery);
    }*/

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码编辑器
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();//单向，不可逆的密码加密方式
        //使用内存存储
        auth.inMemoryAuthentication()
                //设置密码编辑器
            .passwordEncoder(passwordEncoder)
                //注册用户admin，密码为abc，并赋予USER和ADMIN的角色全县
            .withUser("admin")
                //可通过passwordEncoder.encode("abc")得到加密后的密码
            .password(passwordEncoder.encode("abc"))
                .roles("USER", "ADMIN")
                //连接方法
                .and()
                //注册用户myuser，密码为123456，并赋予USER的角色权限
            .withUser("myuser")
                .password(passwordEncoder.encode("123456"))
                .roles("USER");
    }*/



     /*//WebSecurityConfigurerAdapter默认设定访问权限和登录方式
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("Using default configure(HttpSecurity). " +
                "If subclassed this will potentially " +
                "override subclass configure(HttpSecurity).");
        //只需要通过验证就可以访问所有的请求
        //authorizeRequest方法限定只对签名成功的用户请求
        //anyRequest方法限定所有请求
        //authenticated方法对所有签名成功的用户允许方法
        http.authorizeRequests().anyRequest().authenticated()
                //and()方法是连接词，formLogin代表使用Spring Security默认的登录页面
                .and().formLogin()
                //httpBasic方法说明启用HTTP基础认证
                .and().httpBasic();
    }*/



    public static void main(String[] args) {
        SpringApplication.run(Chapter12Application.class, args);
    }
}
