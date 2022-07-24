package com.event.databaseDefaults;

import com.event.equipment.Equipment;
import com.event.equipment.EquipmentService;
import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentCategory.EquipmentCategoryService;
import com.event.equipmentData.EquipmentData;
import com.event.equipmentPhoto.EquipmentPhoto;
import com.event.equipmentStatus.EquipmentStatus;
import com.event.privilege.PrivilegeEnum;
import com.event.privilege.dao.PrivilegeModel;
import com.event.privilege.dao.PrivilegeRepository;
import com.event.role.Role;
import com.event.role.RoleService;
import com.event.role.roleDao.RoleModel;
import com.event.role.roleDao.RoleRepository;
import com.event.user.User;
import com.event.user.UserService;
import com.event.user.dao.UserModel;
import com.event.user.dao.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;


@Configuration
public class FakeDataInflator {

    @Bean
    CommandLineRunner run(
        UserRepository userRepository,
        UserService userService,
        PrivilegeRepository privilegeRepository,
        RoleRepository roleRepository,
        RoleService roleService,
        EquipmentCategoryService equipmentCategoryService,
        EquipmentService equipmentService,
        PasswordEncoder passwordEncoder) {
        return args -> {

            // AUTH

            RoleModel roleAdmin = new RoleModel("ADMIN");
            RoleModel roleGuest = new RoleModel("GUEST");
            RoleModel roleUser = new RoleModel("USER");

            roleRepository.save(roleGuest);

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

//            roleAdmin.setPrivilegesList(List.of(
//                privilege_clients_C,privilege_clients_R,privilege_clients_U,privilege_clients_D,
//                privilege_equipment_C,privilege_equipment_R,privilege_equipment_U,privilege_equipment_D,
//                privilege_admin_C,privilege_admin_R,privilege_admin_U,privilege_admin_D));
            roleRepository.save(roleAdmin);

//            roleUser.setPrivilegesList(List.of(privilege_admin_C));

//            roleUser.setPrivilegesList(List.of(
//                privilege_clients_C,privilege_clients_R,
//                privilege_equipment_C,privilege_equipment_R,
//                privilege_admin_C,privilege_admin_R));
            roleRepository.save(roleUser);

            // USER

            UserModel userAdmin = new UserModel("admin", passwordEncoder.encode("123"), "Józef", "Baryła");
            UserModel userUser = new UserModel("user", passwordEncoder.encode("123"), "Jadwiga", "Kapusta");
            UserModel userGuest = new UserModel("guest", passwordEncoder.encode("123"), "Stefan", "Burczymucha");
            userAdmin.setUserRolesIds(List.of(roleAdmin.getRoleModelId()));
            userUser.setUserRolesIds(List.of(roleUser.getRoleModelId()));
            userGuest.setUserRolesIds(List.of(roleGuest.getRoleModelId()));

            userRepository.save(userAdmin);
            userRepository.save(userUser);
            userRepository.save(userGuest);


            // WERSJA Z TWORZENIEM USERA I ROLE PRZEZ SERVICE

            User stefan = new User("hirek", "123", "Hieronim","Trąbka");
            Role cleaner = new Role("CLEANER");
            Role postman = new Role("POSTMAN");
            roleService.addRole(cleaner);
            roleService.addRole(postman);

            stefan.setUserRoles(List.of(cleaner, postman));
            userService.addUser(stefan);

            // EQUIPMENT



            // EQUIPMENT CATEGORY
            EquipmentCategory ec1 = new EquipmentCategory(0L, "Category1", "Description1");
            EquipmentCategory ec2 = new EquipmentCategory(0L, "Category2", "Description2");
            EquipmentCategory ec3 = new EquipmentCategory(0L, "Category3", "Description3");

            equipmentCategoryService.addEquipmentCategory(ec1);
            equipmentCategoryService.addEquipmentCategory(ec3);
            equipmentCategoryService.addEquipmentCategory(ec2);

            Equipment e1 = new Equipment(1, 1, "EQ1", null, "Notes 111", null, null, null, 0, null, true);
            Equipment e2 = new Equipment(2, 1, "EQ2", null, "Notes 222", null, null, null, 0, null, true);
            Equipment e3 = new Equipment(3, 1, "EQ3", null, "Notes 333", null, null, null, 0, null, true);

            equipmentService.addEquipment(e1);
            equipmentService.addEquipment(e2);
            equipmentService.addEquipment(e3);

        };
    }
}
