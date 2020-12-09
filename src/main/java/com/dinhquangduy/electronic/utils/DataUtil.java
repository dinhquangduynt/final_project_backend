package com.dinhquangduy.electronic.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class DataUtil {
    
    @Autowired
    public static JavaMailSender emailSender;
    public static JsonObject getJsonObject(String strJson) throws Exception {
        JsonObject json = null;
        try {
            if (null != strJson && 0 != strJson.length()) {
                json = new Gson().fromJson(strJson, JsonObject.class);
            }
            if (json != null && isJsonObjectHasKey(json, "data")) {
                json = json.getAsJsonObject("data");
            }
        } catch (Exception ex) {
            json = null;
        }

        if (null == json) {
            // The JSON format is incorrect.
            throw new Exception();
        }
        return json;
    }
    
    public static boolean isJsonObjectHasKey(JsonObject json, String key) {
        return json.has(key);
    }
    
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    public static void sendmail(String to, String subject, String msg) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        
        boolean multipart = true;
         
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
         
         
        message.setContent(msg, "text/html");
         
        helper.setTo(to);
         
        helper.setSubject(subject);
         
     
        emailSender.send(message);
    }

    public static String randomPassword() {
        return RandomStringUtils.random(8, Constants.rander);
    }
    
    public static String getBaseURL(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        StringBuffer url =  new StringBuffer();
        url.append(scheme).append("://").append(serverName);
        if ((serverPort != 80) && (serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        url.append(contextPath);
        if(url.toString().endsWith("/")){
            url.append("/");
        }
        return url.toString();
    }
}
