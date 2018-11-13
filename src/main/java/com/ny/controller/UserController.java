/*package com.ny.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ny.model.User;
import com.ny.param.LoginParam;
import com.ny.param.ModifyPwdParam;
import com.ny.param.RegisterParam;
import com.ny.param.SignatureParam;
import com.ny.security.IgnoreSecurity;
import com.ny.service.UserService;
import com.ny.util.Decript;
import com.ny.util.JsapiTicketUtil;
import com.ny.util.JwtTokenUtil;
import com.ny.util.ShaUtil;
import com.ny.vo.Response;
import com.ny.vo.UserBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

*//**
 * 用户控制接口服务
 *
 *
 *//*
@Api(value = "用户控制接口服务")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    *//**
     * 登录接口服务
     *//*
    @ApiOperation(value = "登录接口服务", notes = "Java调用样例：\n" + "\n" + "\n"
            + "    public static void main(String[] args) throws Exception {\n"
            + "        JSONObject jsonObject = new JSONObject();\n" + "        List<String> list = new ArrayList<>();\n"
            + "        list.add(\"/application/web-httpd/app/shipin\"); //文件路径，取值对应图1\n"
            + "        list.add(\"/application/web-httpd/app/pinglun\"); //文件路径...\n"
            + "        jsonObject.put(\"paths\", list);\n"
            + "        //HttpGET httpGET = new HttpGET(\"http://10.8.0.110:8080/app-service/api/user/login\"); //内网地址 \n"
            + "        HttpGET httpGET = new HttpGET(\"http://app.wokeji.com:8890/app-service/api/user/login\"); //外网地址 \n"
            + "        StringEntity entity = new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8);\n"
            + "        entity.setContentType(\"application/json\");\n" + "        httpPost.setEntity(entity);\n"
            + "        CloseableHttpClient httpclient = HttpClients.createDefault();\n"
            + "        CloseableHttpResponse response = httpclient.execute(httpPost);\n" + "        try {\n"
            + "            System.out.println(response.getStatusLine());\n"
            + "            HttpEntity httpEntity = response.getEntity();\n"
            + "            String s = EntityUtils.toString(httpEntity);\n" + "            System.out.println(s);\n"
            + "            //消耗掉response\n" + "            EntityUtils.consume(httpEntity);\n" + "        } finally {\n"
            + "            response.close();\n" + "        }\n" + "}\n" + "\n"
            + "\n", httpMethod = "GET", response = Response.class)
    @ApiImplicitParam(name = "loginParam", value = "登录参数", required = true, dataType = "LoginParam")
    @ApiResponse(code = 200, message = "返回数据")
    @IgnoreSecurity
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response angularLogin(LoginParam loginParam) {
        // 获取登录信息
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new Response().failure("请输入用户名和密码！");
        }
        // SHA加密
        password = ShaUtil.encode(password);
        // 调用登录服务
        User user = userService.login(username);
        // 用户名不存在
        if (user == null || StringUtils.isEmpty(user.getUserName())) {
            return new Response().failure("用户不存在！");
        }
        // 密码错误
        if (password != null && !password.equals(user.getPassword())) {
            return new Response().failure("密码错误！");
        }

        // 封装 UserBean 对象
        UserBean userBean = new UserBean();
        String token = jwtTokenUtil.generateToken(user);
        userBean.setToken(token);
        userBean.setUsername(username);

        // 获取用户服务路由资源
        List<String> resources = userService.getUserResource(user.getId());
        userBean.setResources(resources);

        // 登录成功，将 user 对象返回前端
        return new Response().success(userBean);
    }

    *//**
     * 注册新用户
     *//*
    @ApiOperation(value = "注册新用户", notes = "Java调用样例：\n" + "\n" + "\n"
            + "    public static void main(String[] args) throws Exception {\n"
            + "        JSONObject jsonObject = new JSONObject();\n" + "        List<String> list = new ArrayList<>();\n"
            + "        list.add(\"/application/web-httpd/app/shipin\"); //文件路径，取值对应图1\n"
            + "        list.add(\"/application/web-httpd/app/pinglun\"); //文件路径...\n"
            + "        jsonObject.put(\"paths\", list);\n"
            + "        //HttpPost httpPost = new HttpPost(\"http://10.8.0.110:8080/app-service/api/user/register\"); //内网地址 \n"
            + "        HttpPost httpPost = new HttpPost(\"http://app.wokeji.com:8890/app-service/api/user/register\"); //外网地址 \n"
            + "        StringEntity entity = new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8);\n"
            + "        entity.setContentType(\"application/json\");\n" + "        httpPost.setEntity(entity);\n"
            + "        CloseableHttpClient httpclient = HttpClients.createDefault();\n"
            + "        CloseableHttpResponse response = httpclient.execute(httpPost);\n" + "        try {\n"
            + "            System.out.println(response.getStatusLine());\n"
            + "            HttpEntity httpEntity = response.getEntity();\n"
            + "            String s = EntityUtils.toString(httpEntity);\n" + "            System.out.println(s);\n"
            + "            //消耗掉response\n" + "            EntityUtils.consume(httpEntity);\n" + "        } finally {\n"
            + "            response.close();\n" + "        }\n" + "}\n" + "\n"
            + "\n", httpMethod = "POST", response = Response.class)
    @ApiImplicitParam(name = "registerParam", value = "注册新用户参数", required = true, dataType = "RegisterParam")
    @ApiResponse(code = 200, message = "返回数据")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(RegisterParam registerParam) {
        // 参数校验
        if (StringUtils.isEmpty(registerParam) || StringUtils.isEmpty(registerParam.getUserName())
                || StringUtils.isEmpty(registerParam.getPassword())) {
            return new Response().failure("用户名或密码不能为空！");
        }
        // SHA加密
        String password = ShaUtil.encode(registerParam.getPassword());
        registerParam.setPassword(password);
        return userService.register(registerParam);
    }

    *//**
     * 修改密码
     *
     * @param modifyPwdParam ModifyPwdParam
     * @param request        HttpServletRequest
     * @return Response
     *//*
    @ApiOperation(value = "修改密码", notes = "Java调用样例：\n" + "\n" + "\n"
            + "    public static void main(String[] args) throws Exception {\n"
            + "        JSONObject jsonObject = new JSONObject();\n" + "        List<String> list = new ArrayList<>();\n"
            + "        list.add(\"/application/web-httpd/app/shipin\"); //文件路径，取值对应图1\n"
            + "        list.add(\"/application/web-httpd/app/pinglun\"); //文件路径...\n"
            + "        jsonObject.put(\"paths\", list);\n"
            + "        //HttpPost httpPost = new HttpPost(\"http://10.8.0.110:8080/app-service/api/user/modifyPwd\"); //内网地址 \n"
            + "        HttpPost httpPost = new HttpPost(\"http://app.wokeji.com:8890/app-service/api/user/modifyPwd\"); //外网地址 \n"
            + "        StringEntity entity = new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8);\n"
            + "        entity.setContentType(\"application/json\");\n" + "        httpPost.setEntity(entity);\n"
            + "        CloseableHttpClient httpclient = HttpClients.createDefault();\n"
            + "        CloseableHttpResponse response = httpclient.execute(httpPost);\n" + "        try {\n"
            + "            System.out.println(response.getStatusLine());\n"
            + "            HttpEntity httpEntity = response.getEntity();\n"
            + "            String s = EntityUtils.toString(httpEntity);\n" + "            System.out.println(s);\n"
            + "            //消耗掉response\n" + "            EntityUtils.consume(httpEntity);\n" + "        } finally {\n"
            + "            response.close();\n" + "        }\n" + "}\n" + "\n"
            + "\n", httpMethod = "POST", response = Response.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "modifyPwdParam", value = "修改密码参数", required = true, dataType = "ModifyPwdParam"),
        @ApiImplicitParam(name = "request", value = "请求信息", required = true, dataType = "HttpServletRequest") })
    @ApiResponse(code = 200, message = "返回数据")
    @RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
    public Response modifyPwd(ModifyPwdParam modifyPwdParam, HttpServletRequest request) {
        // 参数校验
        if (StringUtils.isEmpty(modifyPwdParam) || StringUtils.isEmpty(modifyPwdParam.getOldPwd())
                || StringUtils.isEmpty(modifyPwdParam.getNewPwd())) {
            return new Response().failure("旧密码或新密码不能为空！");
        }
        // 从请求头中获取 userName
        modifyPwdParam.setUserName(request.getHeader("X-Username"));
        // 密码加密
        modifyPwdParam.setOldPwd(ShaUtil.encode(modifyPwdParam.getOldPwd()));
        modifyPwdParam.setNewPwd(ShaUtil.encode(modifyPwdParam.getNewPwd()));
        return userService.modifyPwd(modifyPwdParam);
    }

    *//**
     * 判断是否登录
     *//*
    @ApiOperation(value = "判断是否登录", notes = "Java调用样例：\n" + "\n" + "\n"
            + "    public static void main(String[] args) throws Exception {\n"
            + "        JSONObject jsonObject = new JSONObject();\n" + "        List<String> list = new ArrayList<>();\n"
            + "        list.add(\"/application/web-httpd/app/shipin\"); //文件路径，取值对应图1\n"
            + "        list.add(\"/application/web-httpd/app/pinglun\"); //文件路径...\n"
            + "        jsonObject.put(\"paths\", list);\n"
            + "        //HttpPost httpPost = new HttpPost(\"http://10.8.0.110:8080/app-service/api/user/isLogin\"); //内网地址 \n"
            + "        HttpPost httpPost = new HttpPost(\"http://app.wokeji.com:8890/app-service/api/user/isLogin\"); //外网地址 \n"
            + "        StringEntity entity = new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8);\n"
            + "        entity.setContentType(\"application/json\");\n" + "        httpPost.setEntity(entity);\n"
            + "        CloseableHttpClient httpclient = HttpClients.createDefault();\n"
            + "        CloseableHttpResponse response = httpclient.execute(httpPost);\n" + "        try {\n"
            + "            System.out.println(response.getStatusLine());\n"
            + "            HttpEntity httpEntity = response.getEntity();\n"
            + "            String s = EntityUtils.toString(httpEntity);\n" + "            System.out.println(s);\n"
            + "            //消耗掉response\n" + "            EntityUtils.consume(httpEntity);\n" + "        } finally {\n"
            + "            response.close();\n" + "        }\n" + "}\n" + "\n"
            + "\n", httpMethod = "POST", response = Response.class)
    @ApiResponse(code = 200, message = "返回数据")
    @RequestMapping(value = "/isLogin", method = RequestMethod.POST)
    public Response isLogin() {
        return new Response().success();
    }

    *//**
     * 微信分享获取签名
     *
     * @return Response
     *//*
    @ApiOperation(value = "微信分享获取签名", notes = "Java调用样例：\n" + "\n" + "\n"
            + "    public static void main(String[] args) throws Exception {\n"
            + "        JSONObject jsonObject = new JSONObject();\n" + "        List<String> list = new ArrayList<>();\n"
            + "        list.add(\"/application/web-httpd/app/shipin\"); //文件路径，取值对应图1\n"
            + "        list.add(\"/application/web-httpd/app/pinglun\"); //文件路径...\n"
            + "        jsonObject.put(\"paths\", list);\n"
            + "        //HttpPost httpPost = new HttpPost(\"http://10.8.0.110:8080/app-service/api/user/getSignature\"); //内网地址 \n"
            + "        HttpPost httpPost = new HttpPost(\"http://app.wokeji.com:8890/app-service/api/user/getSignature\"); //外网地址 \n"
            + "        StringEntity entity = new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8);\n"
            + "        entity.setContentType(\"application/json\");\n" + "        httpPost.setEntity(entity);\n"
            + "        CloseableHttpClient httpclient = HttpClients.createDefault();\n"
            + "        CloseableHttpResponse response = httpclient.execute(httpPost);\n" + "        try {\n"
            + "            System.out.println(response.getStatusLine());\n"
            + "            HttpEntity httpEntity = response.getEntity();\n"
            + "            String s = EntityUtils.toString(httpEntity);\n" + "            System.out.println(s);\n"
            + "            //消耗掉response\n" + "            EntityUtils.consume(httpEntity);\n" + "        } finally {\n"
            + "            response.close();\n" + "        }\n" + "}\n" + "\n"
            + "\n", httpMethod = "POST", response = Response.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "signatureParam", value = "签名", required = true, dataType = "SignatureParam"),
            @ApiImplicitParam(name = "request", value = "请求信息", required = true, dataType = "HttpServletRequest") })
    @ApiResponse(code = 200, message = "返回数据")
    @IgnoreSecurity
    @RequestMapping(value = "/getSignature", method = RequestMethod.POST)
    public Response getSignature(@RequestBody SignatureParam signatureParam, HttpServletRequest request)
            throws Exception {
        String jsapi_ticket = JsapiTicketUtil.getJsapiTicket();
        String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + signatureParam.getNoceStr() + "&timestamp="
                + signatureParam.getTimestamp() + "&url=" + signatureParam.getUrl();
        // SHA1签名
        String signature = Decript.sha1(str);
        return new Response().success(signature);
    }
}
*/