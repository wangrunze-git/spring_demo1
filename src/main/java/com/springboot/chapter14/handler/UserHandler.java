package com.springboot.chapter14.handler;

import com.mysql.cj.x.protobuf.Mysqlx;
import com.springboot.chapter14.vo.UserVo;
import com.springboot.chapter14.pojo.User;
import com.springboot.chapter14.repository.UserRepository;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//用户处理器
@Service
public class UserHandler {

    @Autowired
    private UserRepository userRepository = null;

    public Mono<ServerResponse> getUser(ServerRequest request){
        //获取请求URI参数
        String idStr = request.pathVariable("id");
        Long id = Long.valueOf(idStr);
        Mono<UserVo> userVoMono = userRepository.findById(id)
        //转换为UserVo
            .map(u -> translate(u));
        return ServerResponse
                //响应成功
            .ok()
                //响应体类型
            .contentType(MediaType.APPLICATION_JSON)
                //响应体
            .body(userVoMono, UserVo.class);
    }

    public Mono<ServerResponse> insertUser(ServerRequest request){
        Mono<UserVo> userVoMono = request.bodyToMono(User.class)
                //缓存请求体
            .cache()
                //处理业务逻辑，转变数据流
            .flatMap(user -> userRepository.save(user))
                //转换为UserVo对象
            .map(u -> translate(u));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userVoMono, UserVo.class);
    }

    public Mono<ServerResponse> updateUser(ServerRequest request){
        Mono<User> userMonoParam = request.bodyToMono(User.class);
        Mono<UserVo> userVoMono = userMonoParam.cache()
                .flatMap(user -> userRepository.save(user))
                .map(u -> translate(u));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userVoMono, UserVo.class);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request){
        String idStr = request.pathVariable("id");
        Long id = Long.valueOf(idStr);
        Mono<Void> monoVoid = userRepository.deleteById(id);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(monoVoid, void.class);
    }

    public Mono<ServerResponse> findUsers(ServerRequest request){
        String userName = request.pathVariable("userName");
        String note = request.pathVariable("note");
        Flux<UserVo> userVoFlux = userRepository.findByUserNameLikeAndNoteLike(userName, note)
                .map(u -> translate(u));
        //可参考getUser方法的注释
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userVoFlux, UserVo.class);
    }

    public Mono<ServerResponse> updateUserName(ServerRequest request){
        //获取请求头数据
        String idStr = request.headers().header("id").get(0);
        Long id = Long.valueOf(idStr);
        String userName = request.headers().header("userName").get(0);
        //获取原有用户信息
        Mono<User> userMono = userRepository.findById(id);
        User user = userMono.block();
        //修改用户名
        user.setUserName(userName);
        Mono<UserVo> result = userRepository.save(user).map(u -> translate(u));
        //响应结果
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result, UserVo.class);
    }

    private UserVo translate(User user){
        UserVo userVo = new UserVo();
        userVo.setUserName(user.getUserName());
        userVo.setNote(user.getNote());
        userVo.setId(user.getId());
        return userVo;
    }
}
