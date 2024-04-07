package com.example.dartfanpage.users;

import com.example.dartfanpage.contact.EmailSender;
import com.example.dartfanpage.users.resetPassword.PasswordResetToken;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private EmailSender emailSender;



    public void registerUser(RegistrationUserDto dto) throws UserExistsException {

        Optional<User> byEmail = userRepository.findByEMail(dto.getEMail());
        if (byEmail.isPresent()) {
            throw new UserExistsException("Użytkownik o email " + byEmail.get().geteMail() + " już istnieje!");
        }

        User user = User.fromDto(dto, dto.getPassword());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash()));
        Role role = roleRepository.findByRoleName(Role.USER).orElseThrow(() -> new RuntimeException("Nie znaleziono roli!"));
        user.addRole(role);
        userRepository.save(user);
    }


    public void sendEmailToResetPassword(String email) {

        User user = userRepository.findByEMail(email)
                .orElseThrow(() -> new RuntimeException("Nie znalezione emaila w bazie dnaych"));
        String token = UUID.randomUUID().toString();
        user.setPasswordResetToken(createPasswordResetTokenForUser(token));
        userRepository.save(user);
        String url = ServletUriComponentsBuilder.fromCurrentRequest().toUriString() + "/?token=" + token;
        emailSender.sendPasswordResetTokenEmail(user.geteMail(), url);
    }

    private PasswordResetToken createPasswordResetTokenForUser(String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, LocalDateTime.now());
        return myToken;
    }

    public void saveNewPassword(String token, String password) {
        User user = userRepository.findUserByPasswordResetToken(token);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setPasswordResetToken(null);
        userRepository.save(user);
    }

}
