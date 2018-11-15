package roy.github.learn.springboot.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by roy on 2018/11/14.
 */
@RestController
public class EmailController {

    @Autowired
    private EmailTool emailTool;

    @RequestMapping("/sendEmail")
    public String sendEmail(HttpServletRequest request) {
        System.out.println("========sendEmail begin=====");
        String value = request.getParameter("value");
        String title=null;
        String content=null;
        if (value!=null){
            title = value.substring(0,value.indexOf(","));
            content = value.substring(value.indexOf(",")+1);
        }
        emailTool.sendSimpleMail(title,content);
        return "发送成功";
    }

}
