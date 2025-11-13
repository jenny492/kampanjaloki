package com.github.jenny492.kampanjaloki;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.github.jenny492.kampanjaloki.Storage.StorageProperties;
import com.github.jenny492.kampanjaloki.Storage.StorageService;
import com.github.jenny492.kampanjaloki.domain.AppUser;
import com.github.jenny492.kampanjaloki.domain.Campaign;
import com.github.jenny492.kampanjaloki.domain.CampaignCharacter;
import com.github.jenny492.kampanjaloki.domain.CampaignMember;
import com.github.jenny492.kampanjaloki.domain.GameEvent;
import com.github.jenny492.kampanjaloki.domain.GameSession;
import com.github.jenny492.kampanjaloki.domain.PlayerCharacter;
import com.github.jenny492.kampanjaloki.repository.AppUserRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignCharacterRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignMemberRepository;
import com.github.jenny492.kampanjaloki.repository.CampaignRepository;
import com.github.jenny492.kampanjaloki.repository.GameEventRepository;
import com.github.jenny492.kampanjaloki.repository.GameSessionRepository;
import com.github.jenny492.kampanjaloki.repository.PlayerCharacterRepository;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class KampanjalokiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KampanjalokiApplication.class, args);
    }

   /*  @Bean
    public CommandLineRunner demo(CampaignRepository cRepository, 
            AppUserRepository uRepository,
            GameSessionRepository sRepository,
            GameEventRepository eRepository, 
            PlayerCharacterRepository charRepository,
            CampaignCharacterRepository cCharRepository,
            CampaignMemberRepository cMemRepository) {
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

            CampaignMember campaignMember1 = new CampaignMember(testUser1, testCampaign);
            CampaignMember campaignMember2 = new CampaignMember(testUser2, testCampaign);
            cMemRepository.save(campaignMember1);
            cMemRepository.save(campaignMember2);

            GameSession testSession1 = new GameSession(testCampaign, "Testiseikkailu eka", "Tässä seikkailussa tapahtui",
                    testTimeNow, testTimeNow);
            sRepository.save(testSession1);

            GameEvent testEvent1 = new GameEvent(testSession1, "Ensimmäinen tapahtuma");
            GameEvent testEvent2 = new GameEvent(testSession1, "Toinen tapahtuma");
            GameEvent testEvent3 = new GameEvent(testSession1, "Kolmas tapahtuma");
            GameEvent testEvent7 = new GameEvent(testSession1, "Neljäs tapahtuma");
            eRepository.save(testEvent1);
            eRepository.save(testEvent2);
            eRepository.save(testEvent3);
            eRepository.save(testEvent7);
            GameSession testSession2 = new GameSession(testCampaign, "Testiseikkailu toka", "Tässä seikkailussa tapahtui",
                    testTimeNow, testTimeNow);
            sRepository.save(testSession2);

            GameEvent testEvent4 = new GameEvent(testSession2, "Ensimmäinen tapahtuma");
            GameEvent testEvent5 = new GameEvent(testSession2, "Toinen tapahtuma");
            GameEvent testEvent6 = new GameEvent(testSession2, "Kolmas tapahtuma");
            eRepository.save(testEvent4);
            eRepository.save(testEvent5);
            eRepository.save(testEvent6);

            PlayerCharacter character1 = new PlayerCharacter("Hahmo 1", "Kuvaus", testUser1);
            PlayerCharacter character2 = new PlayerCharacter("Hahmo 2", "Kuvaus", testUser2);
            PlayerCharacter character3 = new PlayerCharacter("Hahmo 3", "Kuvaus", testUser2);
            charRepository.save(character1);
            charRepository.save(character2);
            charRepository.save(character3);

            CampaignCharacter campaignCharacter1 = new CampaignCharacter(character1, testCampaign);
            CampaignCharacter campaignCharacter2 = new CampaignCharacter(character2, testCampaign);
            cCharRepository.save(campaignCharacter1);
            cCharRepository.save(campaignCharacter2);

 
        };
    }
<<<<<<< Updated upstream
*/
=======

    @Bean
    public CommandLineRunner initStorage(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
>>>>>>> Stashed changes
}
