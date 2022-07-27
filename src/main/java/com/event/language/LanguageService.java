package com.event.language;

import com.event.language.dao.LanguageModel;
import com.event.language.dao.LanguageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageService {
    Pageable firstPageWithTenElements = PageRequest.of(0,10);
    LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository){
        this.languageRepository = languageRepository;
    }

    public Language addLanguage(Language language){
        LanguageModel model = new LanguageModel(language.getPropertyName());
        languageRepository.save(model);
        language.setId(model.getId());
        return language;
    }
    public Language getLanguage(int languageId){
        LanguageModel model = languageRepository.findById(languageId).get();
        return createLanguage(model);
    }
    public String deleteLanguage(int languageId){
        languageRepository.deleteById(languageId);
        return "delete";
    }
    public Language updateLanguage(int languageId, Language newLanguage){
        LanguageModel model = languageRepository.findById(languageId).get();
        model.setPropertyName(newLanguage.getPropertyName());
        model.setId(languageId);
        languageRepository.save(model);
        return newLanguage;
    }
    public List<Language> getAllLanguages(){
        List<Language> languages = new ArrayList<>();
        Iterable<LanguageModel> languageModels = languageRepository.findAll();
        for (LanguageModel model: languageModels){
            languages.add(createLanguage(model));
        }
        return languages;
    }

    private Language createLanguage(LanguageModel languageModel){
        return new Language(languageModel.getId(), languageModel.getPropertyName());
    }

//PAGINACJA
    public List<Language> getAllTenLanguage(int pageNo){

        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        List<Language> languages = new ArrayList<>();
        Iterable<LanguageModel> languageModels = languageRepository.findAll(pageable);
        for (LanguageModel model: languageModels){
            languages.add(createLanguage(model));
        }
        System.out.println(languages);
        return languages;
    }
    public Long getCountLanguage(){
        return languageRepository.count();    }
}