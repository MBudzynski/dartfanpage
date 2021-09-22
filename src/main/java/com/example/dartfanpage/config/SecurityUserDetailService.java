package com.example.dartfanpage.config;

import com.example.dartfanpage.users.User;
import com.example.dartfanpage.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserNameOrEMail(username).map(user -> mapToUserDetails(user))
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    private UserDetails mapToUserDetails(User user) {
        List<GrantedAuthority> authorities =
                Arrays.asList(new SimpleGrantedAuthority(user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.joining())));

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPasswordHash(), authorities);
    }

}
