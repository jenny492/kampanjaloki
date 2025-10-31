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

            AppUser testUser1 = new AppUser("user1", "$2a$10$QkkwsO61MqIVTMdf60.AReRHTDCs7pPNz/kOTZMOwfsqh4AjAy2dm", testTimeNow, "USER");
            AppUser testUser2 = new AppUser("user2", "$2a$10$/iZiuKOq5H4/ZpANveL9KezgXuGo8DqiAzOzzgi.8//bda28gQVgu", testTimeNow, "USER");
            uRepository.save(testUser1);
            uRepository.save(testUser2);

            AppUser testAdmin = new AppUser("admin", "$2a$10$qKjgF7ibr/9.hAULqzabm.nd.rY/zPLANE1eCf3XUVHQQCSsNDxEm", testTimeNow, "ADMIN");
            uRepository.save(testAdmin);

            Campaign testCampaign = new Campaign("testi", "desc", "img-url", testTimeNow, testUser1);
            cRepository.save(testCampaign);

            GameSession testSession1 = new GameSession(testCampaign, "Testiseikkailu eka", "Tässä seikkailussa tapahtui",
                    testTimeNow, testTimeNow);
            sRepository.save(testSession1);

            GameEvent testEvent1 = new GameEvent(testSession1);
            GameEvent testEvent2 = new GameEvent(testSession1);
            GameEvent testEvent3 = new GameEvent(testSession1);
            eRepository.save(testEvent1);
            eRepository.save(testEvent2);
            eRepository.save(testEvent3);

            GameSession testSession2 = new GameSession(testCampaign, "Testiseikkailu toka", "Tässä seikkailussa tapahtui",
                    testTimeNow, testTimeNow);
            sRepository.save(testSession2);

            GameEvent testEvent4 = new GameEvent(testSession2);
            GameEvent testEvent5 = new GameEvent(testSession2);
            GameEvent testEvent6 = new GameEvent(testSession2);
            eRepository.save(testEvent4);
            eRepository.save(testEvent5);
            eRepository.save(testEvent6);

            PlayerCharacter character1 = new PlayerCharacter("Hahmo 1", "Kuvaus", testUser1);
            PlayerCharacter character2 = new PlayerCharacter("Hahmo 2", "Kuvaus", testUser2);
            PlayerCharacter character3 = new PlayerCharacter("Hahmo 3", "Kuvaus", testUser2);
            charRepository.save(character1);
            charRepository.save(character2);
            charRepository.save(character3);
        };
    }

}
