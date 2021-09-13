package com.example.dartfanpage;

import com.example.dartfanpage.users.Role;
import com.example.dartfanpage.users.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSeed implements InitializingBean {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        populateRole();
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

}
