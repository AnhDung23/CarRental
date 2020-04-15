/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.emailservice;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
public class EmailService implements Serializable{
    private String email;
    private String code;

    public EmailService() {
    }

    public EmailService(String email, String code) {
        this.email = email;
        this.code = code;
    }
    
    public void verifyEmail(){
        String emailAdmin = "adung5341@gmail.com";  
        String pass = "vhijsxfmeoskgngl";
        
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAdmin, pass);
            }
        });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAdmin));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(code + " is code confirm your email for App");
            message.setText("Code confirm your email: " + code);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
}
