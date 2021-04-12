package com.springboot.chapter10.converter;

import com.springboot.chapter8.pojo.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 自定义字符串用户转换器
 */
@Component
public class StringToUserConverter implements Converter<String, User> {

    /**
     * 转换方法
     * @param
     * @return
     */
    @Override
    public User convert(String userStr) {
        User user = new User();
        String[] strArr = userStr.split("-");
        long id = Long.parseLong(strArr[0]);
        String userName = strArr[1];
        String note = strArr[2];
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        return user;
    }
}
