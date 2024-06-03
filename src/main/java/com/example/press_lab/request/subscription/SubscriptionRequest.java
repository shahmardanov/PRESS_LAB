package com.example.press_lab.request.subscription;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest {

    @NotBlank
    @Email
    private String email;

}
