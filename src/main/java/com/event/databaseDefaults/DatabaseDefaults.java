package com.event.databaseDefaults;

import com.event.equipmentCategory.DAO.EquipmentCategoryModel;
import com.event.equipmentCategory.DAO.EquipmentCategoryRepository;
import com.event.equipmentCategory.EquipmentCategory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseDefaults {

    @Bean
    CommandLineRunner run(EquipmentCategoryRepository repository) {

        return args -> {
            EquipmentCategoryModel model1 = new EquipmentCategoryModel(null, "Category A", "Description AAA");
            EquipmentCategoryModel model2 = new EquipmentCategoryModel(null, "Category B", "Description BBB");
            EquipmentCategoryModel model3 = new EquipmentCategoryModel(null, "Category C", "Description CCC");
            EquipmentCategoryModel model4 = new EquipmentCategoryModel(null, "Category D", "Description DDD");

            repository.saveAll(
                List.of(model1, model2, model3, model4)
            );
        };

    }

}
