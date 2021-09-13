package com.example.dartfanpage.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;



    public void registerUser(RegistrationUserDto dto) throws UserExistsException {

        Optional<User> byEmail = userRepository.findByEMail(dto.getEMail());
        if (byEmail.isPresent()) {
            throw new UserExistsException("Użytkownik o email " + byEmail.get().geteMail() + " już istnieje!");
        }

        User user = User.fromDto(dto, dto.getPassword());
        Role role = roleRepository.findByRoleName(Role.USER).orElseThrow(() -> new RuntimeException("Nie znaleziono roli!"));
        user.addRole(role);
        userRepository.save(user);

    }
}
