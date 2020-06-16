package com.example.email.service;

import javax.mail.NoSuchProviderException;

public interface IEmailService {
    void sendMaill(String to,String titlem,String context);

    void readEmail() throws NoSuchProviderException, Exception;
}
