package com.hqjg.web;

import com.google.gson.Gson;
import com.hqjg.common.Constant;
import com.hqjg.config.util.BaseUtil;
import com.hqjg.config.util.CookiesUtil;
import com.hqjg.config.util.MD5Util;
import com.hqjg.domain.vo.MapParam;
import com.hqjg.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ygma on 16-9-1.
 */

@Controller
@ResponseBody
@RequestMapping("/user")
public class LoginController extends BaseController implements CommandLineRunner {

    Log log = LogFactory.getLog(LoginController.class);

    @Resource
    private UserService userService;


    /**
     * 用户登录
     * code = 200, 500, 501
     * msg = OK, name or password is null, name or password is error
     * @return
     */
    @RequestMapping(value = "/isLogin", method = {RequestMethod.GET})
    public Map<String, Object> isLogin(MapParam mapParam,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        Map<String, Object> data = new HashMap<>();
        log.info(mapParam.getMap());
        data.put("code", 200);
        data.put("msg", "OK");
        ServletContext context = request.getServletContext();
        String sessionId = String.valueOf(mapParam.getMap().get("sessionId"));
        /*Enumeration<String> params = context.getAttributeNames();
        while(params.hasMoreElements()) {
            String key = params.nextElement();
            log.info("key = " + key);
            log.info("value = " + context.getAttribute(key));
        }*/
        if(!BaseUtil.isNotNull(sessionId)
                || context.getAttribute(sessionId) == null) {
            data.put("code", 500);
            data.put("msg", false);
            data.put("data", null);
            return data;
        }
        Gson gson = new Gson();
        Map fromJson = gson.fromJson(String.valueOf(context.getAttribute(sessionId)), Map.class);
        log.info("fromJson = " + fromJson);
        data.put("data", fromJson);
        return data;
    }

    /**
     * 用户登录
     * code = 200, 500, 501
     * msg = OK, name or password is null, name or password is error
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public Map<String, Object> login(MapParam mapParam,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> data = new HashMap<String, Object>();

        int expires = 60 * 60 * 24;

        // 如果用户已经登录 就尝试清除Cookies
        String sessionId = CookiesUtil.getCookieValue(request, Constant.LOGIN_HCKEY);
        if(!BaseUtil.isNotNull(sessionId)){
            // 清除cookies
            CookiesUtil.delCookies(request, response, Constant.LOGIN_HCKEY);
        }

        log.info(mapParam.getMap());
        data.put("code", 200);
        data.put("msg", "OK");
        data.put("map", mapParam.getMap());
        if(!BaseUtil.containsKey(mapParam.getMap(), "loginName")
                || !BaseUtil.containsKey(mapParam.getMap(), "password")) {
            data.put("code", "500");
            data.put("msg", "用户名 和 密码 都不能为空");
            return data;
        }

        String password = String.valueOf(mapParam.getMap().get("password"));
        password = MD5Util.GetMD5Code(password);
        mapParam.getMap().put("password", password);

        Map<String, Object> user = userService.findUserByMap(mapParam.getMap());
        log.info("user  = " + user);
        if(user == null) {
            data.put("code", "501");
            data.put("msg", "用户名或者密码错误");
            return data;
        }

        sessionId = request.getSession().getId();
        log.info("sessionId = " + sessionId);
        CookiesUtil.addCookies(response, Constant.LOGIN_HCKEY, sessionId, expires);
        user.put(Constant.LOGIN_HCKEY, sessionId);
        Gson gson = new Gson();
        log.info("toJson = " + gson.toJson(user));
        request.getServletContext().setAttribute(sessionId, gson.toJson(user));
        data.put("data", user);
        return data;
    }

    /*public static void main(String[] args) {
        System.out.println(MD5Util.GetMD5Code("a1cf116"));
        System.out.println(MD5Util.GetMD5Code("a1cf116"));
    }*/

    /**
     *
     * 注册
     * ref = http://www.cnblogs.com/rixiang/p/5468234.html
     * url = localhost:10111/user/register?map['loginName']=马亚广
     *       &map['nikeName']=ygma&map['mobilePhone']=13699194789
     *       &map['password']=a1cf116&map['userImgPath']=tmp/ygma.jpg
     * code = 200, 500, 501
     * msg = OK, name or password is null, name or password is error
     * @param mapParam
     * @return
     */
    @RequestMapping("/register")
    public Map<String, Object> register(MapParam mapParam) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> data = new HashMap<String, Object>();
        log.info(mapParam);
        data.put("code", 200);
        data.put("msg", "OK");
        data.put("map", mapParam.getMap());

        // 密码加密
        if(mapParam.getMap().containsKey("password")) {
            String password = String.valueOf(mapParam.getMap().get("password"));
            password = MD5Util.GetMD5Code(password);
            mapParam.getMap().put("password", password);
            log.info("newPassword = " + password);
            log.info("mapParam.password = " + mapParam.getMap().get("password"));
        }
        userService.register(mapParam.getMap());

        /**
         * TODO：// 2， 工具系统接口文件上传接口， 3，在线人数统计方法 ,
         */
        return data;
    }

    @RequestMapping("/uploadImg")
    public Map<String, Object> uploadImg() {
        Map<String, Object> map = new HashMap<String, Object>();
        return map;
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
