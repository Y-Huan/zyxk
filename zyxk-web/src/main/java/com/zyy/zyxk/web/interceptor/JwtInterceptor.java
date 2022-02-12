package com.zyy.zyxk.web.interceptor;

import com.zyy.zyxk.common.annotation.FreeAuthentication;
import com.zyy.zyxk.common.exception.JwtException;
import com.zyy.zyxk.service.RedisService;
import com.zyy.zyxk.service.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Description jwt 拦截器
 * @Author Yang.H
 * @Date 2021/6/18
 *
 **/
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //接口白名单检查
        String requestURI=request.getRequestURI();
        if(initWhiteList().contains(requestURI)){
            return true;
        }

        // 如果不是映射到方法，则直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        //检查是否有FreeAuthentication注解，有则跳过认证
        Method method=((HandlerMethod)handler).getMethod();
        if (method.isAnnotationPresent(FreeAuthentication.class)) {
            return true;
        }

        // 从请求头中获取 token
        String token = request.getHeader("token");
        // 当没有获取到 token 时的处理
        if (StringUtils.isEmpty(token)) {
            throw new JwtException("认证失败：无令牌");
        }

        try {
            // token解析
            JwtUtil.verifyToken(token);
            String userId=JwtUtil.getCurrentUser(token).getId();

            // redis 验证token
            if(!redisService.isValidToken(userId)){
                throw new JwtException("认证失败：无效令牌");
            }

            //单点登录控制
            if(!token.equals(redisService.getToken(userId))){
                throw new JwtException("认证失败：令牌已过期");
            }

            return true;

        }catch (ExpiredJwtException e) {
            throw new JwtException("认证失败：令牌已过期");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("认证失败：无效令牌");
        } catch (MalformedJwtException e) {
            throw new JwtException("认证失败：令牌格式有误");
        } catch (SignatureException e) {
            throw new JwtException("认证失败：签名错误");
        } catch (IllegalArgumentException e) {
            throw new JwtException("认证失败：非法参数");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    //初始化api白名单
    private List<String> initWhiteList(){
        List<String> whiteList=new ArrayList<>();
        //swagger文档相关api
        whiteList.add("/doc.html");
        whiteList.add("/webjars/bycdao-ui/images/api.ico");
        whiteList.add("/swagger-resources");
        whiteList.add("/swagger-resources/configuration/ui");

        return whiteList;
    }

    //查询用户信息的方法但是暂时没有用到的地方由于账号分在两个表具体需要重新更改
//    private List<String> getPermissions(long userId){
//        List<Map<String,Object>> permissions=userService.selectUserPermissions(userId);
//        List<String> list=new ArrayList<>();
//        for(Map<String,Object> item:permissions){
//            list.add(item.get("resource_permission").toString());
//        }
//
//        return list;
//    }
}
