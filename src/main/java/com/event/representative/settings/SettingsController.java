package com.event.representative.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class SettingsController {

    private final SettingsService service;

    @Autowired
    public SettingsController(SettingsService service) {
        this.service = service;
    }

    @PostMapping("/admin/settings")
    public Settings addSettings(@RequestBody Settings settings){
        return service.addSettings(settings);
    }

    @PutMapping("/admin/settings/{settingsId}")
    public ResponseEntity<Object> updateSettings(@PathVariable int settingsId, @RequestBody Settings newSettings) {
        Settings updateSettings = service.updateSettings(settingsId, newSettings);
        if (updateSettings == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateSettings);
        }
    }

    @GetMapping("/admin/settings/{settingsId}")
    public Settings getSettings(@PathVariable int settingsId){
        return service.getSettings(settingsId);
    }

    @GetMapping("/admin/settings")
    public List<Settings> getAllSettings(){
        return service.getAllSettings();
    }

    @DeleteMapping("/admin/settings/{settingsId}")
    public String deleteSettings(@PathVariable int settingsId){
        return service.deleteSettings(settingsId);
    }
}
