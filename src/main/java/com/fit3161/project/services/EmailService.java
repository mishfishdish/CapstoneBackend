package com.fit3161.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendInviteEmail(String to, String clubName, String inviteLink) {
        Context context = new Context();
        context.setVariable("clubName", clubName);
        context.setVariable("inviteLink", inviteLink);

        String htmlContent = templateEngine.process("email/invite-email", context);

        MimeMessagePreparator message = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to);
            helper.setSubject("You're invited to join a club on SigmaSchedule!");
            helper.setText(htmlContent, true); // true = isHtml
        };

        mailSender.send(message);
    }

    public void sendActivityNotification(String to, String userName) {
        Context context = new Context();
        context.setVariable("userName", userName);

        String htmlContent = templateEngine.process("email/activity-notification", context);

        MimeMessagePreparator message = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to);
            helper.setSubject("Upcoming Activity on SigmaSchedule");
            helper.setText(htmlContent, true); // HTML mode
        };

        mailSender.send(message);
    }
}