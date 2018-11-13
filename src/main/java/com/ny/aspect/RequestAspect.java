package com.ny.aspect;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ny.param.UserValidParam;
import com.ny.security.IgnoreSecurity;
import com.ny.util.JwtTokenUtil;
import com.ny.vo.Response;

/**
 * 拦截所有请求进行权限校验
 * 
 *
 */
@Aspect
@Configuration
public class RequestAspect {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${redis.key.user-resources}")
    private String userResourcesKey;

    @Pointcut("execution( * com.ny.controller.*.*Controller.*(..))")
    public void pointCutAt() {
    }

    @Around("pointCutAt()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // 若目标方法忽略了安全性检查，则直接调用目标方法
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return pjp.proceed();
        }

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 从 request header 中获取当前 token
        String token = request.getHeader("X-Token");
        // 从token中获取UserValidParam
        UserValidParam userValidParam = new UserValidParam();
        userValidParam.setUsername(request.getHeader("X-Username"));
        // 检查token有效性
        boolean isTokenValid = !StringUtils.isEmpty(token) && !StringUtils.isEmpty(userValidParam.getUsername())
                && jwtTokenUtil.validateToken(token, userValidParam);

        // 校验服务权限
        String requestURL = request.getRequestURI();
        boolean hasPermission = hasPermission(token, requestURL);
        if (isTokenValid && hasPermission) {
            return pjp.proceed();
        } else {
            // 无权限访问返回0，前端根据返回信息跳转至登录页面
            return new Response().failure("0");
        }
    }

    /**
     * 校验服务权限
     *
     * @param token String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    private boolean hasPermission(String token, final String requestURL) {
        // 从缓存中获取用户服务路由资源
        ListOperations<String, String> listOperations = redisTemplate.opsForList();

        String userId = jwtTokenUtil.getUserIdFromToken(token);
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(userResourcesKey + userId);
        if (hasKey) {
            List<String> resources = listOperations.range(userResourcesKey + userId, 0, -1);
            String resourceTemp = resources.stream().filter(resource -> resource.equals(requestURL)).findFirst()
                    .orElse(null);
            return resourceTemp != null;
        }
        return false;
    }
}
