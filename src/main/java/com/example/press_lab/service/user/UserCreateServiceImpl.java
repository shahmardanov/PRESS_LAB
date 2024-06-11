package com.example.press_lab.service.user;




import com.example.press_lab.annotation.ConsoleLog;
import com.example.press_lab.annotation.LocalDatePattern;
import com.example.press_lab.annotation.LogExecutionTime;
import com.example.press_lab.config.JwtService;
import com.example.press_lab.constant.UserMessages;
import com.example.press_lab.entity.Authority;
import com.example.press_lab.entity.User;
import com.example.press_lab.exception.user.AccountNotFoundException;
import com.example.press_lab.exception.user.UserExitsException;
import com.example.press_lab.exception.user.VerificationException;
import com.example.press_lab.mappers.UserMapper;
import com.example.press_lab.repository.AuthorityRepository;
import com.example.press_lab.repository.UserRepository;
import com.example.press_lab.request.user.AuthenticateRequest;
import com.example.press_lab.request.user.JwtDto;
import com.example.press_lab.request.user.UserCreateRequest;
import com.example.press_lab.response.user.UserCreateResponse;
import com.example.press_lab.service.emailService.MailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import static com.example.press_lab.constant.ConstantVariables.USER_AUTHORITY;
import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCreateServiceImpl  implements  UserCreateService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final JavaMailSender mailSender;
    private final JwtService jwtService;
    private final MailService mailService;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;


    private UserCreateResponse userCreateResponse;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @ConsoleLog
    @SneakyThrows
    @Transactional
    @LocalDatePattern
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        if (userRepository.findByUsername(userCreateRequest.getEmail()).isPresent()) {
            throw new UserExitsException(HttpStatus.BAD_REQUEST.name(), UserMessages.USER_EXIST);
        } else {
           try {
               User user = User.builder()
                       .name(userCreateRequest.getName())
                       .surname(userCreateRequest.getSurname())
                       .gender(userCreateRequest.getGender())
                       .birthDate(getBirthDate(userCreateRequest.getBirthDate()))
                       .age(calculateAge(userCreateRequest.getBirthDate()))
                       .username(userCreateRequest.getEmail())
                       .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                       .authorities(Set.of(getUserAuthority()))
                       .verificationCode(generateOtpCode())
                       .build();
               user = userRepository.save(user);
               mailService.sendEmail(user.getUsername(), user.getVerificationCode());


        } catch (Exception exception) {
               log.error("Error occurred when mail sending: {}", exception.getMessage());
               throw new RuntimeException(exception.getMessage());
           }

        }
        return userCreateResponse;
    }

    @Override
    @ConsoleLog
    @Transactional
    public void activate(String email, Integer verificationCode) {
        userRepository.findByUsername(email)
                .map(user -> {
                    if (!user.getVerificationCode().equals(verificationCode)) {
                        user.setVerificationCode(null);
                        userRepository.save(user);
                        throw new VerificationException("Verification failed!");
                    }
                    user.setVerificationCode(null);
                    user.setEnabled(true);
                    return userRepository.save(user);
                });
    }

    @Override
    @ConsoleLog
    public JwtDto authenticate(AuthenticateRequest authenticateDto) {
        User user = userRepository.findByUsername(authenticateDto.getEmail())
                .orElseThrow(() -> new AccountNotFoundException(HttpStatus.NOT_FOUND.name(), "Account not found!"));
        if (!passwordEncoder.matches(authenticateDto.getPassword(), user.getPassword())) {
            throw new VerificationException("Email or password is wrong!");
        }
        if (!user.isEnabled()) {
            throw new VerificationException("Account did not activated!");
        }

        String jwt = jwtService.generateToken(prepareClaims(user));
        return new JwtDto(jwt);
    }


    private LocalDate getBirthDate(String birthDate) {
        return LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    private int calculateAge(String birthDate) {
        return now().getYear() - getBirthDate(birthDate).getYear();
    }

    private Integer generateOtpCode() {
        int min = 1001;
        int max = 9999;
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    private Authority getUserAuthority() {
        return authorityRepository.findByAuthority(USER_AUTHORITY)
                .orElseGet(() -> authorityRepository.save(Authority.builder()
                        .authority(USER_AUTHORITY)
                        .build()));
    }

    private Map<String, Object> prepareClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", user.getName());
        claims.put("surname", user.getSurname());
        claims.put("username", user.getUsername());
        claims.put("age", user.getAge());
        claims.put("birthDate", user.getBirthDate().toString());
        claims.put("gender", user.getGender());
        claims.put("enable", user.isEnabled());
        claims.put("ROLE", List.of(USER_AUTHORITY));
        claims.put("email", user.getEmail());
        return claims;
    }

    @Override
    @LogExecutionTime
    @SneakyThrows
    @Transactional
    public void resendVerificationCode(String email) {
        User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new AccountNotFoundException(HttpStatus.NOT_FOUND.name(), "Account not found!"));
        user.setVerificationCode(generateOtpCode());
        userRepository.save(user);
        mailService.sendEmail(email, user.getVerificationCode());
    }






}
