package com.example.authentication.services;


import com.example.authentication.DamhaApplication;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Properties;
import java.util.regex.Pattern;

@Service
public class EmailUtilService {


    private String prepareHTML_CONSTANT_FILE(String file , String builder , String quote){

        String htmlContent = "";
        String l = "";
        BufferedReader reader = null;
        try{

         reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            while ((l = reader.readLine()) != null) {
                htmlContent += l;
            }

            //for response email quote = "%RESPONSE%"
            htmlContent = htmlContent.replaceAll(Pattern.quote(quote), builder.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

        return htmlContent;




    }

    public void responseForComplaintEmail(String email , String builder) {
        try {

            String responseComplaintTittle = DamhaApplication.CONSTANTS_MAP.get("RESPONSE_COMPLAINTS_TITLE");

            sendEmail(email, responseComplaintTittle, prepareHTML_CONSTANT_FILE(
                    DamhaApplication.CONSTANTS_MAP.get("RESPONSE_COMPLAINTS_HTML"),
                    builder,
                    "%RESPONSE%"
            ));

        } catch (Exception e) {
        }
    }

    public void sendEmail(String recipient, String subject, String textMessage) throws Exception {
        Properties properties = new Properties();

        String mailHost= DamhaApplication.CONSTANTS_MAP.get("MAIL_HOST");
        String mailPort = DamhaApplication.CONSTANTS_MAP.get("MAIL_PORT");
        String mailUser = DamhaApplication.CONSTANTS_MAP.get("MAIL_USER");
        String mailPw = DamhaApplication.CONSTANTS_MAP.get("MAIL_PASSWORD");

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", mailHost);
        properties.put("mail.smtp.port", mailPort);

        String accountUsername = mailUser;
        String accountPassword = mailPw;

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(accountUsername, accountPassword);
            }
        });

        MimeMessage message = prepareMessage(session, accountUsername, recipient, subject, textMessage);

        Transport.send(message);



    } // end sendEmail


    private MimeMessage prepareMessage(Session session, String accountUsername, String recipient, String subject, String textMessage) {

        try {
            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type","text/html; charset=UTF-8");
            message.setFrom(new InternetAddress(accountUsername));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject,"UTF-8");
            message.setContent(textMessage, "text/html; charset=UTF-8");

            //%%%%%%%%%%%%%%%%%%%%%%%
            return message;

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    } ///end prepareMessage


}

