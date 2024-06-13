package com.example.press_lab.entity;

import com.example.press_lab.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;
    private Integer verificationCode;

    private String name;
    private String surname;

//    @Email
//    @Column(unique = true)
    private String username;

    private int age;

    private String password;

    @Email
    @Column(unique = true)
    private String email;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;




    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities;

    @PrePersist
    public void autoFill() {
        setAccountNonExpired(true);
        setAccountNonLocked(true);
        setCredentialsNonExpired(true);
        setEnabled(true);
    }



}
