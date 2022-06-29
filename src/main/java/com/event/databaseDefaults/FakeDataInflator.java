package com.event.databaseDefaults;

import com.event.privilege.PrivilegeEnum;
import com.event.privilege.dao.PrivilegeModel;
import com.event.privilege.dao.PrivilegeRepository;
import com.event.role.roleDao.RoleModel;
import com.event.role.roleDao.RoleRepository;
import com.event.user.dao.UserModel;
import com.event.user.dao.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FakeDataInflator {

    @Bean
    CommandLineRunner run(
        UserRepository userRepository,
        PrivilegeRepository privilegeRepository,
        RoleRepository roleRepository,
        PasswordEncoder passwordEncoder) {
        return args -> {

            RoleModel admin = new RoleModel("ADMIN");
            RoleModel guest = new RoleModel("GUEST");
            RoleModel manager = new RoleModel("MANAGER");

            roleRepository.save(guest);

            PrivilegeModel privilege_clients_C = new PrivilegeModel("clients", PrivilegeEnum.CREATE);
            PrivilegeModel privilege_clients_R = new PrivilegeModel("clients", PrivilegeEnum.READ);
            PrivilegeModel privilege_clients_U = new PrivilegeModel("clients", PrivilegeEnum.UPDATE);
            PrivilegeModel privilege_clients_D = new PrivilegeModel("clients", PrivilegeEnum.DELETE);

            PrivilegeModel privilege_equipment_C = new PrivilegeModel("equipment", PrivilegeEnum.CREATE);
            PrivilegeModel privilege_equipment_R = new PrivilegeModel("equipment", PrivilegeEnum.READ);
            PrivilegeModel privilege_equipment_U = new PrivilegeModel("equipment", PrivilegeEnum.UPDATE);
            PrivilegeModel privilege_equipment_D = new PrivilegeModel("equipment", PrivilegeEnum.DELETE);

            PrivilegeModel privilege_admin_C = new PrivilegeModel("admin", PrivilegeEnum.CREATE);
            PrivilegeModel privilege_admin_R = new PrivilegeModel("admin", PrivilegeEnum.READ);
            PrivilegeModel privilege_admin_U = new PrivilegeModel("admin", PrivilegeEnum.UPDATE);
            PrivilegeModel privilege_admin_D = new PrivilegeModel("admin", PrivilegeEnum.DELETE);

            privilegeRepository.save(privilege_clients_C);
            privilegeRepository.save(privilege_clients_R);
            privilegeRepository.save(privilege_clients_U);
            privilegeRepository.save(privilege_clients_D);

            privilegeRepository.save(privilege_equipment_C);
            privilegeRepository.save(privilege_equipment_R);
            privilegeRepository.save(privilege_equipment_U);
            privilegeRepository.save(privilege_equipment_D);

            privilegeRepository.save(privilege_admin_C);
            privilegeRepository.save(privilege_admin_R);
            privilegeRepository.save(privilege_admin_U);
            privilegeRepository.save(privilege_admin_D);

            admin.setPrivilegesList(List.of(
                privilege_clients_C,privilege_clients_R,privilege_clients_U,privilege_clients_D,
                privilege_equipment_C,privilege_equipment_R,privilege_equipment_U,privilege_equipment_D,
                privilege_admin_C,privilege_admin_R,privilege_admin_U,privilege_admin_D));
            roleRepository.save(admin);

            PrivilegeModel privilege_xxx = new PrivilegeModel("xxx", PrivilegeEnum.CREATE);
            privilegeRepository.save(privilege_xxx);
            manager.setPrivilegesList(List.of(privilege_admin_C));

            manager.setPrivilegesList(List.of(
                privilege_clients_C,privilege_clients_R,
                privilege_equipment_C,privilege_equipment_R,
                privilege_admin_C,privilege_admin_R));
            roleRepository.save(manager);

            UserModel ziutek = new UserModel("ziutek", passwordEncoder.encode("123"), "Józef", "Baryła");
            UserModel jadzka = new UserModel("jadźka", passwordEncoder.encode("123"), "Jadwiga", "Kapusta");
            ziutek.setRoles(List.of(admin));
            jadzka.setRoles(List.of(manager, guest));

            userRepository.save(ziutek);
            userRepository.save(jadzka);

        };
    }
}
