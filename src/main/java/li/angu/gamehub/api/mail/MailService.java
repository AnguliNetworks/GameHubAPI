package li.angu.gamehub.api.mail;

import li.angu.gamehub.api.configuration.MailConfig;
import li.angu.gamehub.api.mail.html.HTMLObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/*************************************************************************
 *
 * ANGULI NETWORKS CONFIDENTIAL
 * __________________
 *
 *  [2014] - [2018] Anguli Networks 
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Anguli Networks. The intellectual and 
 * technical concepts contained herein are proprietary to 
 * Anguli Networks and may be covered by German/EU and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Anguli Networks
 *
 * This File belongs to the GameHubAPI from Anguli Networks
 */
@Service
public class MailService {

    private MailConfig mailConfig;

    @Autowired
    public MailService(MailConfig mailConfig) {
        this.mailConfig = mailConfig;

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.port", mailConfig.getPort() + "");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.host", mailConfig.getHost());
        properties.setProperty("mail.smtp.port", mailConfig.getPort() + "");
    }

    public void sendMail(String to, String subject, HTMLObject html) {
        try {
            Session session = Session.getDefaultInstance(System.getProperties(), new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailConfig.getMail(), mailConfig.getPassword());
                }
            });

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress("noreply@gamehub.one", "GameHubOne"));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress("log@gamehub.one", "GameHubOne Mail Log"));

            message.setSubject(subject);

            message.setContent(html.toString(), "text/html; charset=utf-8");

            Transport.send(message);
        } catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }
    }

}
