package kh.edu.cstad.mbapi.init;

import jakarta.annotation.PostConstruct;
import kh.edu.cstad.mbapi.domain.Role;
import kh.edu.cstad.mbapi.domain.User;
import kh.edu.cstad.mbapi.repository.RoleRepository;
import kh.edu.cstad.mbapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SecurityInitialize {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        if (roleRepository.count() == 0) {
            Role roleUser = new Role();
            roleUser.setName("USER");

            Role roleAdmin = new Role();
            roleAdmin.setName("ADMIN");

            Role roleStaff = new Role();
            roleStaff.setName("STAFF");

            Role roleCustomer = new Role();
            roleCustomer.setName("CUSTOMER");

            roleRepository.saveAll(List.of(roleAdmin, roleStaff, roleCustomer, roleUser));

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("pwq@123"));
            admin.setIsEnabled(true);
            admin.setRoles(Set.of(roleAdmin,roleUser));

            User staff = new User();
            staff.setUsername("staff");
            staff.setPassword(passwordEncoder.encode("pwq@123"));
            staff.setIsEnabled(true);
            staff.setRoles(Set.of(roleStaff,roleUser));

            User customer = new User();
            customer.setUsername("customer");
            customer.setPassword(passwordEncoder.encode("pwq@123"));
            customer.setIsEnabled(true);
            customer.setRoles(Set.of(roleCustomer,roleUser));

            userRepository.saveAll(List.of(admin, staff, customer));
        }
    }

}