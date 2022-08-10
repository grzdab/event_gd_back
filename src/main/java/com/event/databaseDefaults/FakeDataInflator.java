package com.event.databaseDefaults;

import com.event.address.Address;
import com.event.address.AddressService;
import com.event.businessBranch.BusinessBranch;
import com.event.businessBranch.BusinessBranchService;
import com.event.businessCategory.BusinessCategory;
import com.event.businessCategory.BusinessCategoryService;
import com.event.client.Client;
import com.event.client.ClientService;
import com.event.clientType.ClientType;
import com.event.clientType.ClientTypeService;
import com.event.contact.Contact;
import com.event.contact.ContactService;
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
import com.event.legalEntityType.LegalEntityType;
import com.event.legalEntityType.LegalEntityTypeService;
import com.event.privilege.PrivilegeEnum;
import com.event.privilege.dao.PrivilegeModel;
import com.event.privilege.dao.PrivilegeRepository;
import com.event.representative.Representative;
import com.event.representative.RepresentativeService;
import com.event.role.Role;
import com.event.role.RoleService;
import com.event.role.roleDao.RoleModel;
import com.event.role.roleDao.RoleRepository;
import com.event.taxInfo.TaxInfo;
import com.event.taxInfo.TaxInfoService;
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
import java.util.UUID;


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
            PasswordEncoder passwordEncoder,
            AddressService addressService,
            ContactService contactService,
            LegalEntityTypeService legalEntityTypeService,
            ClientTypeService clientTypeService,
            TaxInfoService taxInfoService,
            BusinessBranchService businessBranchService,
            BusinessCategoryService businessCategoryService,
            RepresentativeService representativeService,
            ClientService clientService) {
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
            EquipmentBookingStatus equipmentBookingStatus1 = new EquipmentBookingStatus(0, "available", "#53FAA5");
            EquipmentBookingStatus equipmentBookingStatus2 = new EquipmentBookingStatus(0, "booked", "#FAEA81");
            EquipmentBookingStatus equipmentBookingStatus3 = new EquipmentBookingStatus(0, "rented", "#FA483F");

            equipmentBookingStatusService.addEquipmentBookingStatus(equipmentBookingStatus1);
            equipmentBookingStatusService.addEquipmentBookingStatus(equipmentBookingStatus2);
            equipmentBookingStatusService.addEquipmentBookingStatus(equipmentBookingStatus3);

            EquipmentOwnership ownershipOwn = new EquipmentOwnership(0, "own");
            EquipmentOwnership ownershipForeign = new EquipmentOwnership(0, "foreign");

            equipmentOwnershipService.addEquipmentOwnership(ownershipOwn);
            equipmentOwnershipService.addEquipmentOwnership(ownershipForeign);


            // EQUIPMENT
            Equipment e1 = new Equipment(
                0,
                1,
                "EQ1",
                equipmentCategory1,
                "Notes 111",
                null,
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
                "EQ2",
                equipmentCategory1,
                "Notes 222",
                null,
                equipmentStatus1,
                equipmentBookingStatus1,
                ownershipForeign,
                new ArrayList<>(List.of(new EquipmentBookingPeriods(1, LocalDateTime.of(2019, 01, 28, 14, 33), LocalDateTime.of(2019, 01, 29, 14, 33)))),
                true,
                1,2,3,4,5,6,7,8);
            equipmentService.addEquipment(e2);

            //ADDRESS
            Address address = new Address(0, "STREET", "NO", "CODE", "CITY", 1, true, "");
            addressService.addAddress(address);

            //CONTACT
            Contact contact = new Contact("MAIL", "PHONE");
            contactService.addContact(contact);
            Contact contact2 = new Contact("MAIL2", "PHONE2");
            contactService.addContact(contact2);

            //CLIENT TYPE
            ClientType clientType = new ClientType(0, "CLIENT-TYPE");
            clientTypeService.addClientType(clientType);

            //LEGAL ENTITY TYPE
            LegalEntityType legalEntityType = new LegalEntityType("LEGAL-ENTITY-TYPE");
            legalEntityTypeService.addLegalEntityType(legalEntityType);

            //TAX INFO
            TaxInfo taxInfo = new TaxInfo(0, legalEntityType, "REGON", "PESEL", "NIP", "KRS", "INSURANCE");
            taxInfoService.addTaxInfo(taxInfo);

            //BUSINESS BRANCH
            BusinessBranch businessBranch = new BusinessBranch(0, "BUSINESS-BRANCH");
            businessBranchService.addBusinessBranch(businessBranch);

            //BUSINESS CATEGORY
            BusinessCategory businessCategory = new BusinessCategory(0, "BUSINESS-CATEGORY");
            businessCategoryService.addBusinessCategory(businessCategory);

            //REPRESENTATIVE
            Representative representative = new Representative(0, "NAME", "LASTNAME", contact2, "");
            representativeService.addRepresentative(representative);

            //CLIENT
            Client client = new Client(UUID.randomUUID(), "CLIENT-FULL-NAME", "SHORT-NAME", List.of(address),
                    contact, true, clientType, taxInfo, List.of(businessBranch), List.of(businessCategory),
                    "NOTES", List.of(representative), "APP-USER-ID");

            clientService.addClient(client);

            address.setClientId(client.getId().toString());
            addressService.updateAddress(address.getId(), address);

            representative.setClientId(client.getId().toString());
            representativeService.updateRepresentative(representative.getId(), representative);

            clientService.addBusinessBranchToClient(client.getId().toString(), businessBranch.getId());
            clientService.addBusinessCategoryToClient(client.getId().toString(), businessCategory.getId());

            System.out.println("Jarek");
        };
    }
}
