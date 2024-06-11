package com.example.press_lab.service.user;



import com.example.press_lab.constant.UserMessages;
import com.example.press_lab.repository.UserRepository;
import com.example.press_lab.request.user.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserLoginService {

    private final UserRepository userRepository;

    private final JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String fromMail;
    private MimeMailMessage mimeMessage;


    public String login(UserLoginRequest userLoginRequest) {
        if (userRepository.findUserWithUsernameAndPassword(userLoginRequest.getUsername(), userLoginRequest.getPassword()).isEmpty()) {
            return UserMessages.USER_FAILED_LOGIN;
        } else {
           try {
               LocalDate date = LocalDate.now();
               SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
               simpleMailMessage.setSubject("Login is succesfull. ");
               simpleMailMessage.setTo("memmedxan95@gmail.com");
               simpleMailMessage.setFrom(fromMail);
               simpleMailMessage.setText(String.valueOf(date));
               mailSender.send(simpleMailMessage);

           } catch (Exception exception) {
               log.error("Error occurred when mail sending: {}", exception.getMessage());
               throw new RuntimeException(exception.getMessage());
           }
            return UserMessages.USER_SUCCES_LOGIN;
        }
    }
}