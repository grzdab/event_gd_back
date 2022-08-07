package com.event.databaseDefaults;

import com.event.equipment.Equipment;
import com.event.equipment.EquipmentService;
import com.event.equipmentBookingPeriods.EquipmentBookingPeriods;
import com.event.equipmentBookingStatus.EquipmentBookingStatus;
import com.event.equipmentBookingStatus.EquipmentBookingStatusService;
import com.event.equipmentCategory.EquipmentCategory;
import com.event.equipmentCategory.EquipmentCategoryService;
import com.event.equipmentData.EquipmentData;
import com.event.equipmentOwnership.EquipmentOwnership;
import com.event.equipmentOwnership.EquipmentOwnershipService;
import com.event.equipmentPhoto.EquipmentPhoto;
import com.event.equipmentStatus.EquipmentStatus;
import com.event.equipmentStatus.EquipmentStatusService;
import com.event.language.Language;
import com.event.language.LanguageService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        EquipmentBookingStatusService equipmentBookingStatusService,
        EquipmentStatusService equipmentStatusService,
        EquipmentOwnershipService equipmentOwnershipService,
        LanguageService languageService,
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

            // EQUIPMENT //////////////////////////////////////////////////////////
            // EQUIPMENT CATEGORY
            EquipmentCategory equipmentCategory1 = new EquipmentCategory(0, 0, "Category1", "Description1");
            EquipmentCategory equipmentCategory2 = new EquipmentCategory(0, 0, "Category2", "Description2");
            EquipmentCategory equipmentCategory3 = new EquipmentCategory(0, 0, "Category3", "Description3");

            equipmentCategoryService.addEquipmentCategory(equipmentCategory1);
            equipmentCategoryService.addEquipmentCategory(equipmentCategory3);
            equipmentCategoryService.addEquipmentCategory(equipmentCategory2);

            // EQUIPMENT STATUS
            EquipmentStatus equipmentStatus1 = new EquipmentStatus(0, "new");
            EquipmentStatus equipmentStatus2 = new EquipmentStatus(0, "broken");
            EquipmentStatus equipmentStatus3 = new EquipmentStatus(0, "in repair");

            equipmentStatusService.addEquipmentStatus(equipmentStatus1);
            equipmentStatusService.addEquipmentStatus(equipmentStatus2);
            equipmentStatusService.addEquipmentStatus(equipmentStatus3);

            // EQUIPMENT BOOKING STATUS
            EquipmentBookingStatus equipmentBookingStatus1 = new EquipmentBookingStatus(0, "available", "equipment is available for renting", "#53FAA5");
            EquipmentBookingStatus equipmentBookingStatus2 = new EquipmentBookingStatus(0, "booked", "equipment is booked, but not confirmed", "#FAEA81");
            EquipmentBookingStatus equipmentBookingStatus3 = new EquipmentBookingStatus(0, "rented", "equipment is rented, not available for renting at the moment","#FA483F");

            equipmentBookingStatusService.addEquipmentBookingStatus(equipmentBookingStatus1);
            equipmentBookingStatusService.addEquipmentBookingStatus(equipmentBookingStatus2);
            equipmentBookingStatusService.addEquipmentBookingStatus(equipmentBookingStatus3);

            EquipmentOwnership ownershipOwn = new EquipmentOwnership(0, "own", "our own equipment");
            EquipmentOwnership ownershipForeign = new EquipmentOwnership(0, "foreign", "borrowed from third-party companies");

            equipmentOwnershipService.addEquipmentOwnership(ownershipOwn);
            equipmentOwnershipService.addEquipmentOwnership(ownershipForeign);


            // EQUIPMENT
            Equipment e1 = new Equipment(
                0,
                1,
                "Inflatable Pirates Ship",
                equipmentCategory1,
                "A ship for max 10 participants at a time",
                List.of("pirates_ship.jpg"),
                equipmentStatus1,
                equipmentBookingStatus1,
                ownershipOwn,
                new ArrayList<>(List.of(new EquipmentBookingPeriods(1, LocalDateTime.of(2019, 01, 28, 14, 33), LocalDateTime.of(2019, 01, 29, 14, 33)))),
                true,
                0,0,0,0,0,0,0,0);
            equipmentService.addEquipment(e1);

            Equipment e2 = new Equipment(
                0,
                1,
                "Mechanical Bull",
                equipmentCategory1,
                "Mechanical bull with an operator who can control the intensity of the machine's movements and stop it before the rider can be thrown.",
                List.of("mechanical_bull.jpg"),
                equipmentStatus1,
                equipmentBookingStatus1,
                ownershipForeign,
                new ArrayList<>(List.of(new EquipmentBookingPeriods(1, LocalDateTime.of(2019, 01, 28, 14, 33), LocalDateTime.of(2019, 01, 29, 14, 33)))),
                true,
                1,2,3,4,5,6,7,8);
            equipmentService.addEquipment(e2);


            Equipment e3 = new Equipment(
                0,
                1,
                "Inflatable castle",
                equipmentCategory1,
                "Inflatable castle for youngest children",
                List.of("inflatable_castle.jpg"),
                equipmentStatus1,
                equipmentBookingStatus3,
                ownershipOwn,
                new ArrayList<>(List.of(new EquipmentBookingPeriods(1, LocalDateTime.of(2019, 01, 28, 14, 33), LocalDateTime.of(2019, 01, 29, 14, 33)))),
                true,
                1,2,3,4,5,6,7,8);
            equipmentService.addEquipment(e3);

            Language polish = new Language(0, "polski");
            Language english = new Language(0, "English");
            Language spanish = new Language(0, "español");
            Language german = new Language(0, "deutch");
            Language french = new Language(0, "Français");
            Language klingon = new Language(0, "tlhIngan");
            Language khmer = new Language(0, "ស្វាហ៊ីលី");
            Language japanese = new Language(0, "日本語");
            Language portuguese = new Language(0, "Português");
            Language hebrew = new Language(0, "עִברִית");
            Language greek = new Language(0, "Ελληνικά");
            Language czech = new Language(0, "čeština");
            Language thai = new Language(0, "ภาษาไทย");
            Language italian = new Language(0, "Italiano");
            Language swedish = new Language(0, "svenska");
            Language hungarian = new Language(0, "Magyar");
            Language turkish = new Language(0, "Türk");
            Language finnish = new Language(0, "Suomalainen");
            Language croatian = new Language(0, "Hrvatski");
            Language korean = new Language(0, "한국인");



            languageService.addLanguage(polish);
            languageService.addLanguage(english);
            languageService.addLanguage(spanish);
            languageService.addLanguage(german);
            languageService.addLanguage(french);
            languageService.addLanguage(klingon);
            languageService.addLanguage(khmer);
            languageService.addLanguage(japanese);
            languageService.addLanguage(portuguese);
            languageService.addLanguage(hebrew);
            languageService.addLanguage(greek);
            languageService.addLanguage(czech);
            languageService.addLanguage(thai);
            languageService.addLanguage(italian);
            languageService.addLanguage(swedish);
            languageService.addLanguage(hungarian);
            languageService.addLanguage(turkish);
            languageService.addLanguage(finnish);
            languageService.addLanguage(croatian);
            languageService.addLanguage(korean);



        };
    }
}
