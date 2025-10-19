package com.github.jenny492.kampanjaloki;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.jenny492.kampanjaloki.domain.AppUser;
import com.github.jenny492.kampanjaloki.domain.Campaign;
import com.github.jenny492.kampanjaloki.domain.GameEvent;
import com.github.jenny492.kampanjaloki.domain.GameSession;
import com.github.jenny492.kampanjaloki.domain.PlayerCharacter;
import com.github.jenny492.kampanjaloki.repository.AppUserRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;
import com.github.jenny492.kampanjaloki.repository.GameEventRepository;
import com.github.jenny492.kampanjaloki.repository.GameSessionRepository;
import com.github.jenny492.kampanjaloki.repository.PlayerCharacterRepository;

@SpringBootApplication
public class KampanjalokiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KampanjalokiApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CampaignRepository cRepository, 
            AppUserRepository uRepository,
            GameSessionRepository sRepository,
            GameEventRepository eRepository, 
            PlayerCharacterRepository charRepository) {
        return (args) -> {
            LocalDateTime testTimeNow = LocalDateTime.now();

            AppUser testUser = new AppUser("username", "password", testTimeNow, "USER");
            uRepository.save(testUser);

            Campaign testCampaign = new Campaign("testi", "desc", "img-url", testTimeNow, testUser);
            cRepository.save(testCampaign);

            GameSession testSession1 = new GameSession(testCampaign, "Testisessio eka", "Tässä sessiossa tapahtui",
                    testTimeNow);
            sRepository.save(testSession1);

            GameEvent testEvent1 = new GameEvent(testSession1);
            GameEvent testEvent2 = new GameEvent(testSession1);
            GameEvent testEvent3 = new GameEvent(testSession1);
            eRepository.save(testEvent1);
            eRepository.save(testEvent2);
            eRepository.save(testEvent3);

            GameSession testSession2 = new GameSession(testCampaign, "Testisessio toka", "Tässä sessiossa tapahtui",
                    testTimeNow);
            sRepository.save(testSession2);

            GameEvent testEvent4 = new GameEvent(testSession2);
            GameEvent testEvent5 = new GameEvent(testSession2);
            GameEvent testEvent6 = new GameEvent(testSession2);
            eRepository.save(testEvent4);
            eRepository.save(testEvent5);
            eRepository.save(testEvent6);

            PlayerCharacter character1 = new PlayerCharacter("Hahmo 1", "Kuvaus");
            PlayerCharacter character2 = new PlayerCharacter("Hahmo 2", "Kuvaus");
            PlayerCharacter character3 = new PlayerCharacter("Hahmo 3", "Kuvaus");
            charRepository.save(character1);
            charRepository.save(character2);
            charRepository.save(character3);
        };
    }

}
