package com.event.language;

import com.event.language.dao.LanguageModel;
import com.event.language.dao.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LanguageRRR {
    @Bean
    CommandLineRunner run(LanguageRepository languageRepository){
        return args -> {
            languageRepository.save(new LanguageModel(1, "Pol"));
            languageRepository.save(new LanguageModel(2, "Ang"));
            languageRepository.save(new LanguageModel(3, "Ger"));
            languageRepository.save(new LanguageModel(4, "Esp"));
            languageRepository.save(new LanguageModel(5, "Fra"));
            languageRepository.save(new LanguageModel(6, "Fin"));
            languageRepository.save(new LanguageModel(7, "Sve"));
            languageRepository.save(new LanguageModel(8, "Esperanto"));
            languageRepository.save(new LanguageModel(9, "Bul"));
            languageRepository.save(new LanguageModel(10, "Ukr"));
            languageRepository.save(new LanguageModel(11, "Lit"));
            languageRepository.save(new LanguageModel(12, "Rum"));
        };
    }
}
