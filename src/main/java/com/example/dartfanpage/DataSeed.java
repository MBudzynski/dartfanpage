package com.example.dartfanpage;

import com.example.dartfanpage.users.Role;
import com.example.dartfanpage.users.RoleRepository;
import com.example.dartfanpage.users.User;
import com.example.dartfanpage.users.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DataSeed implements InitializingBean {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        populateRole();
        populateUsers();
    }

    private void populateRole() {
        if (roleRepository.count() > 0) {
            return;
        }

        Role user = new Role(Role.USER);
        Role admin = new Role(Role.ADMIN);

        roleRepository.save(user);
        roleRepository.save(admin);
    }

    private void populateUsers() {
        if (userRepository.count() > 0) {
            return;
        }
        User user = new User("user","Adam", "Błaszczykiewicz", "user@user.pl", passwordEncoder.encode("user123"),
                "Lublin", "20-601",
                "krakowskie", "1233-12-12", "123456");

        User admin = new User("admin","Adam", "Nowak", "admin@admin.pl", passwordEncoder.encode("admin123"),
                "Lublin", "20-601",
                "krakowskie", "1233-12-12", "123456");

        user.addRole(roleRepository.findByRoleName(Role.USER).orElseThrow(() -> new RuntimeException()));
        admin.addRole(roleRepository.findByRoleName(Role.ADMIN).orElseThrow(() -> new RuntimeException()));

        userRepository.save(user);
        userRepository.save(admin);
    }

}
