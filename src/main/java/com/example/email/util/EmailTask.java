package com.example.email.util;

import com.example.email.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.*;
import java.util.Properties;

@Component
public class EmailTask {
    @Autowired
    IEmailService iEmailService;
//    @Value("${mail.refresh.time}")
//    private static int time;

    @Scheduled(fixedRate = 30000)
    private void startEamil() {
        try {
            iEmailService.readEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


