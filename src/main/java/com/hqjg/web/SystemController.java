package com.hqjg.web;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.hqjg.common.Constant;
import com.hqjg.config.util.BaseUtil;
import com.hqjg.config.util.CaptchaUtil;
import com.hqjg.domain.vo.MapParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ygma on 16-9-1.
 */

@Controller
@ResponseBody
@RequestMapping("/system")
public class SystemController implements CommandLineRunner {

    Log log = LogFactory.getLog(SystemController.class);

    public static final String CAPTCHA = "CAPTCHA";

    /**
     * 生成验证码
     * http://localhost:10111/system/uploadImg
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/getOnlineCount", method = RequestMethod.GET)
    public Map<String, Object> getOnlineCount(HttpServletRequest request)
            throws ServletException, IOException {
        String count = String.valueOf(request.getSession(true).getServletContext().getAttribute("count"));
        Map<String, Object> data = new HashMap<>();
        log.info("count = " + count);
        data.put("code", 200);
        data.put("msg", "OK");
        data.put("data", count);
        return data;
    }

    /**
     * 生成验证码
     * http://localhost:10111/system/uploadImg
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void uploadImg(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("image/jpeg");

        String captcha = CaptchaUtil.getRandomString();
        log.info("captcha = " + captcha);
        log.info("request = " + request);
        log.info("request.para Names = " + request.getParameterMap());
        String captchaKey = String.valueOf(request.getParameter("captchaKey"));
        log.info("captchaKey = " + captchaKey);
        request.getServletContext().setAttribute(captchaKey, captcha);

        CaptchaUtil.outputCaptcha(captcha, response);
    }

    /**
     * 校验验证码是否正确
     * url = http://localhost:10111/system/checkCaptcha?map[%27captchaCode%27]=2VRV2S
     * code = 200, 501
     * @param request
     * @param mapParam
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/checkCaptcha", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> checkCaptcha(HttpServletRequest request, MapParam mapParam)
            throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        log.info(mapParam);
        log.info("getServletContext.captcha = " + request.getServletContext().getAttributeNames().nextElement());
        data.put("code", 200);
        data.put("msg", "OK");
        data.put("map", mapParam.getMap());

        if(!BaseUtil.containsKey(mapParam.getMap(), "captchaCode")
                || !BaseUtil.containsKey(mapParam.getMap(), "captchaKey")
                || !BaseUtil.equals(String.valueOf(mapParam.getMap().get("captchaCode")).toLowerCase(),
                String.valueOf(request.getServletContext()
                        .getAttribute(String.valueOf(mapParam.getMap().get("captchaKey")))).toLowerCase())) {
            data.put("code", 501);
            data.put("msg", "验证码错误");
        }
        return data;

    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(
            @RequestParam("uploadFile") MultipartFile uploadFile) {
        log.info(uploadFile.getName() + "----------------");
        Map<String, Object> data = new HashMap<>();
        data.put("code", 200);
        data.put("msg", "OK");
        try {
            // Get the filename and build the local file path (be sure that the
            // application have write permissions on such directory)
            String filename = uploadFile.getOriginalFilename();
            String directory = Constant.UPLOAD_PATH;
            String filepath = Paths.get(directory, filename).toString();

            // Save the file locally
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(uploadFile.getBytes());
            stream.close();
            log.info("filePath = " + filepath);
            data.put("filepath", filepath);
        }
        catch (Exception e) {
            log.error(e);
            data.put("code", "400");
            data.put("msg", "请求失败");
            return data;
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return data;
//        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    @ResponseBody
    public void getFile(MapParam mapParam,
                        HttpServletResponse response) {
        log.info(mapParam.getMap());

        if(!BaseUtil.containsKey(mapParam.getMap(), "filePath")) return;

        String filePath = String.valueOf(mapParam.getMap().get("filePath"));
        File file = new File(filePath);
        log.info(file.getName());
        log.info(file.toString());
        response.reset();
        response.setContentType("img/jpeg");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.addHeader("Content-Length", "" + file.length());
        try {
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            InputStream in=new FileInputStream(file);
            int len=0;
            byte[]buf=new byte[1024];
            while((len=in.read(buf,0,1024))!=-1){
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            OutputStream outputStream=response.getOutputStream();
            InputStream in=new FileInputStream(file);
            int len=0;
            byte[]buf=new byte[1024];
            while((len=in.read(buf,0,1024))!=-1){
                outputStream.write(buf, 0, len);
                outputStream.flush();
            }
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

    }




    @Override
    public void run(String... strings) throws Exception {

    }
}
