package com.example.press_lab.service.user;


import com.example.press_lab.entity.User;
import com.example.press_lab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userDb = userRepository.findByUsername(email).orElseThrow();
//        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
//                .username(userDb.getUsername())
//                .password(userDb.getPassword())
//                .authorities(List.of())
//                .build();
        return userDb;
    }
}








//        Authority admin = Authority.builder()
//                .authority("ADMIN").build();
//
//
//        az.binary.unitech.entity.User user = az.binary.unitech.entity.User.builder()
//                .username("xan")
//                .password(passwordEncoder.encode("12345"))
//                .accountNonExpired(true)
//                .accountNonLocked(true)
//                .credentialsNonExpired(true)
//                .enabled(true)
//                .email("xan@gmail.com")
//                .authorities(Set.of(admin))
//                .build();
//
//         userRepository.save(user);
//
//        return user;
//    }
//}
