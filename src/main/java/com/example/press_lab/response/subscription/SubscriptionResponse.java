package com.example.press_lab.response.subscription;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponse {

    private Long id;
    private String email;

}
