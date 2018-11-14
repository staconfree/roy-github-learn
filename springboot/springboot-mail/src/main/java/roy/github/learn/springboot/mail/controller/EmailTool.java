package roy.github.learn.springboot.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by roy on 2018/11/14.
 */
@Component
public class EmailTool {
    @Autowired
    private JavaMailSender javaMailSender;


    public void sendSimpleMail(String title,String content){
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("staconfree@163.com");
            helper.setTo("526358233@qq.com");
            helper.setSubject("标题：发送Html内容");

            StringBuffer sb = new StringBuffer();
            sb.append("<h1>大标题-h1</h1>")
                    .append("<p style='color:#F00'>红色字</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            helper.setText(sb.toString(), true);
            if (title!=null){
                helper.setSubject(title);
            }
            if (content!=null){
                helper.setText(content);
            }
//            FileSystemResource fileSystemResource=new FileSystemResource(new File("D:\76678.pdf"));
//            helper.addAttachment("电子发票",fileSystemResource);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
