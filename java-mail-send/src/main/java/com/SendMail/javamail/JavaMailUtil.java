package com.SendMail.javamail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static javax.mail.Transport.send;

public class JavaMailUtil {
    public static void sendMail(String recepient) throws Exception
    {
        System.out.println("Preparing sent email");
        Properties properties = new Properties();

        //настройки для подключение к почте
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust","smtp.gmail.com");//это свойство позволяет мне запустить связь с почтой через ssl протокол.
        properties.put("mail.smtp.port","587");//порт gmail почты

        //Мыло, пароль
        String myAccountEmail =""; //input here Email
        String password = "xxxxxxxxxx"; //input here pw

        //Создаем сессию-аутентификаю, которое возращает значение пароля, мыла.
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(myAccountEmail,password);
            }
        });

        //Создаётся связь с тем, кому мы отправляем письмо. Для этого нам надо подробнее описать метод prepareMessage
        Message message = prepareMessage(session,myAccountEmail,recepient);

        send(message);
        System.out.println("Message sent succesfull");
    }


    //Создается метод prepareMessage
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My second  Email");
            message.setText("How lovely to talk to yourself <ЗЗЗЗЗЗ");
            return message;
        }catch (Exception exception)
        {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,exception);
        }
        return null;
     }




}
