package com.example.notesontoolsback.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.concurrent.TimeUnit;
@Component
public class Captcha {
    @Resource(name = "myStringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Resource(name = "mailSender")
    private JavaMailSender mailSender;
    //生成并存储验证码
    public String sendCode(String phone,String isemail) {
        // 检查phone和isemail是否为空或无效
        if (phone == null || phone.isEmpty() || isemail == null || isemail.isEmpty()) {
            return "无效的输入";
        }

        // 生成 6 位随机验证码
        String code = String.format("%06d", new Random().nextInt(999999));
        String countKey = "captcha_count:" + phone;
        String codeKey = "captcha_code:" + phone;

        // 检查redisTemplate是否为空
        if (redisTemplate == null) {
            return "服务器错误";
        }

        // 检查发送次数（每日最多3次）
        Long count = redisTemplate.opsForValue().increment(countKey);
        if (count == 1) {
            redisTemplate.expire(countKey, 24, TimeUnit.HOURS);  // 首次设置24小时过期[2](@ref)
        }
        if (count > 3) {
            return "今日发送次数已达上限";
        }

        // 存储验证码并设置有效期（参考网页6）
        redisTemplate.opsForValue().set(codeKey, code, 5, TimeUnit.MINUTES);
        if("email".equals(isemail)) { //发送邮件
            try {
                emailGet(phone, code);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return code;
    }
    //邮箱发送验证码
    @Value("${spring.mail.username}")
    private String from;  // 发件人邮箱（与配置一致）
    public void emailGet(String email , String code) throws MessagingException {
        // 检查mailSender是否为空
        if (mailSender == null) {
            return;
        }
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);                   // 发件人
        helper.setTo(email);                  // 收件人
        helper.setSubject("狐书阁验证码");         // 邮件主题
        // HTML 内容模板
        String htmlContent = "<h3 style='color: #1890ff;'>您的验证码：</h3>" +
                "<p style='font-size: 20px;'>" + code + "</p>";
        helper.setText(htmlContent, true);  // true 表示启用 HTML
        mailSender.send(message);
    }
}
