/*package com.ny.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ny.model.User;
import com.ny.param.ModifyPwdParam;

*//**
 *
 *
 *//*
@Component
public interface UserMapper {
    *//**
     * 查询用户信息
     *
     * @param username String
     * @return User
     *//*
    User getUserInfo(@Param(value = "username") String username);

    *//**
     * 用户注册
     *
     * @param user User
     * @return int
     *//*
    int insertUser(@Param(value = "user") User user);

    *//**
     * 修改密码
     * 
     * @param modifyPwdParam ModifyPwdParam
     * @return int
     *//*
    int modifyPwd(@Param(value = "modifyPwdParam") ModifyPwdParam modifyPwdParam);

    *//**
     * 获取用户服务路由资源
     *
     * @param userId int
     * @return List<String>
     *//*
    List<String> getUserResource(@Param(value = "userId") int userId);

    *//**
     * 插入 kpi_user_role 表
     * 
     * @param userId int
     * @param roleId roleId
     *//*
    void insertUserRole(@Param(value = "userId") int userId, @Param(value = "roleId") int roleId);

    *//**
     * 获取用户对应媒体
     *
     * @param userId int
     * @return int
     *//*
    int getUserSite(@Param(value = "userId") int userId);
}
*/