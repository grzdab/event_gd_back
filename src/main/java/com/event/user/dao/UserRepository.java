package com.event.user.dao;

import com.event.equipment.dao.EquipmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserModel, UUID> {

    UserModel findByLogin(String login);
    List<UserModel> findAllByUserRolesIds(int id);

}
