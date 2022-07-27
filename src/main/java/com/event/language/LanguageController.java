package com.event.language;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LanguageController {
    private final LanguageService service;

    @Autowired
    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @PostMapping("/admin/language")
    public Language addLanguage(@RequestBody Language language){
        return service.addLanguage(language);
    }

    @PutMapping("/admin/language/{languageId}")
    public ResponseEntity<Object> updateLanguage(@PathVariable int languageId, @RequestBody Language newLanguage) {
        System.out.println("ID");
        System.out.println(languageId);
        Language updateLanguage = service.updateLanguage(languageId, newLanguage);
        if (updateLanguage == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateLanguage);
        }
    }

    @GetMapping("/admin/language/{languageId}")
    public Language getLanguage(@PathVariable int languageId){
        return service.getLanguage(languageId);
    }

    @GetMapping("/admin/language")
    public List<Language> getAllLanguages(){
        return service.getAllLanguages();
    }

    @GetMapping("/admin/language/languagePage={pageNum}")
    public List<Language> getTenLanguages(@PathVariable int pageNum) {
        return service.getAllTenLanguage(pageNum);
    }

    @DeleteMapping("/admin/language/{languageId}")
    public String deleteLanguage(@PathVariable int languageId){
        return service.deleteLanguage(languageId);
    }

    @GetMapping("/admin/language/count")
    public String countLanguage() {return service.getCountLanguage();}
}
