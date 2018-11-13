/*package com.ny.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ny.mapper.UserMapper;
import com.ny.model.User;
import com.ny.param.ModifyPwdParam;
import com.ny.param.RegisterParam;
import com.ny.vo.Response;

*//**
 * 用户服务
 *
 *
 *//*
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${redis.key.user-resources}")
    private String kpiUserResourcesKey;

    public User login(String username) {
        return userMapper.getUserInfo(username);
    }

    @Transactional(rollbackFor = Exception.class)
    public Response register(RegisterParam registerParam) {
        // 判断用户是否存在
        User user = userMapper.getUserInfo(registerParam.getUserName());
        if (user == null || StringUtils.isEmpty(user.getUserName())) {
            // 新增用户
            User user1 = new User();
            user1.setUserName(registerParam.getUserName());
            user1.setPassword(registerParam.getPassword());
            // 插入数据返回主键，主键保存在 User 实例中
            int result = userMapper.insertUser(user1);
            if (result == 1) {
                // 添加用户角色关系
                // TODO roleId 需要前台传递
                userMapper.insertUserRole(user1.getId(), 3);
            }
            return new Response().success();
        } else {
            return new Response().failure("用户已存在！");
        }
    }

    public Response modifyPwd(ModifyPwdParam modifyPwdParam) {
        int result = userMapper.modifyPwd(modifyPwdParam);
        if (result > 0) {
            return new Response().success();
        } else {
            return new Response().failure("旧密码错误！");
        }
    }

    *//**
     * 获取用户服务路由资源： 如果缓存存在，则从缓存获取； 如果缓存不存在，则从 DB 获取数据，并写入缓存
     *
     * @return List<String>
     *//*
    @SuppressWarnings("unchecked")
    public List<String> getUserResource(int userId) {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();

        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(kpiUserResourcesKey + userId);
        if (hasKey) {
            return listOperations.range(kpiUserResourcesKey + userId, 0, -1);
        }

        // 从 DB 获取媒体信息
        List<String> resources = userMapper.getUserResource(userId);

        // 插入缓存
        listOperations.leftPushAll(kpiUserResourcesKey + userId, resources);
        return resources;
    }
}
*/