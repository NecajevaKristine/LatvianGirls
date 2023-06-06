package com.latviangirls.mailConfig;

import com.sendgrid.*;
//import com.sendgrid.helpers.mail.Mail;
//import com.sendgrid.helpers.mail.objects.Email;
//import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

//*https://www.youtube.com/watch?v=bJqAb4G9uKM

@Service
public class EmailService {

    @Value("${app.email.templateId}")
    private String templateId;

    @Autowired
    SendGrid sendGrid;
    public String sendEmail(String email){

        try {

        Mail mail = prepareMail(email);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sendGrid.api(request);

        if(response!=null){
            System.out.println("response code from sendgrid"+response.getHeaders());
        }

        } catch (IOException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "error in sending!";
        }
        return "mail has been sent, check Your inbox!";
    }

    public Mail prepareMail(String email){
        Mail mail = new Mail();
        Email fromEmail = new Email();
        fromEmail.setEmail("ritucis@inbox.lv");
        Email to = new Email();
        String guestEmail = null;
        to.setEmail(guestEmail);

        Personalization personalization = new Personalization();
        personalization.addTo(to);

        mail.setTemplateId(templateId);
        return null;
    }

}
