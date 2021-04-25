package com.springboot.chapter12.service;

import com.springboot.chapter12.pojo.DatabaseRole;
import com.springboot.chapter12.pojo.DbUser;
import com.sun.tools.javac.util.ArrayUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    //注入服务接口

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //获取数据库用户信息
        DbUser dbUser = new DbUser();
        dbUser.setId(1l);
        dbUser.setUserName("root");
        dbUser.setPwd("123");
        //获取角色信息
        List<DatabaseRole> roleList =
                Arrays.asList(new DatabaseRole(1l, "ROLE_ADMIN"), new DatabaseRole(2l, "ROLE_USER"));
        //将信息转换为UserDetails对象
        return changeToUser(dbUser, roleList);
    }

    private UserDetails changeToUser(DbUser dbUser, List<DatabaseRole> roleList) {
        //权限列表
        ArrayList<GrantedAuthority> authorityList = new ArrayList<>();
        //赋予查询到的角色
        for (DatabaseRole role : roleList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorityList.add(authority);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //创建UserDetails对象，设置用户名、密码和权限
        UserDetails userDetails = new User(dbUser.getUserName(), passwordEncoder.encode("123"), authorityList);
        return userDetails;

    }
}
