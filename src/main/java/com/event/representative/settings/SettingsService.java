package com.event.representative.settings;

import com.event.representative.settings.dao.SettingsRepository;
import com.event.representative.settings.dao.SettingsModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SettingsService{

    SettingsRepository settingsRepository;

    public SettingsService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public Settings addSettings(Settings settings){
        SettingsModel model = new SettingsModel(settings.getResourcesURI());
        settingsRepository.save(model);
        settings.setId(model.getId());
        return settings;
    }
    public Settings getSettings(int settingsId){
        SettingsModel model = settingsRepository.findById(settingsId).get();
        return createSettings(model);
    }
    public String deleteSettings(int settingsId){
        settingsRepository.deleteById(settingsId);
        return "delete";
    }
    public Settings updateSettings(int settingsId, Settings newSettings){
        SettingsModel model = settingsRepository.findById(settingsId).get();
        model.setResourcesURI(newSettings.getResourcesURI());
        settingsRepository.save(model);
        return newSettings;
    }
    public List<Settings>getAllSettings(){
        List<Settings> settingsList = new ArrayList<>();
        Iterable<SettingsModel> settingsModels = settingsRepository.findAll();
        for (SettingsModel model: settingsModels){
            settingsList.add(createSettings(model));
        }
        return settingsList;
    }

    private Settings createSettings(SettingsModel settingsModel){
        return new Settings(settingsModel.getId(), settingsModel.getResourcesURI());
    }
}
