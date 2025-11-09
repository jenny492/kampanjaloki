package com.github.jenny492.kampanjaloki.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.jenny492.kampanjaloki.domain.GameEvent;
import com.github.jenny492.kampanjaloki.exception.NotFoundException;
import com.github.jenny492.kampanjaloki.repository.GameEventRepository;
import com.github.jenny492.kampanjaloki.repository.GameSessionRepository;

@Controller
public class GameEventController {

    private GameEventRepository repository;
    private GameSessionRepository sessionRepository;

    public GameEventController(GameEventRepository repository, GameSessionRepository sessionRepository) {
        this.repository = repository;
        this.sessionRepository = sessionRepository;
    }

    @GetMapping(value = { "/addevent" })
    public String addSession(Model model, @RequestParam(name = "sessionid", required = false) Long sessionid) {
        model.addAttribute("event", new GameEvent());
        model.addAttribute("sessionid", sessionid);
        return "addevent";
    }

    @PostMapping(value = "/saveEvent")
    public String saveSession(@ModelAttribute("event") GameEvent event,
            @RequestParam("sessionid") Long sessionid) {
        event.setSession(sessionRepository.findById(sessionid)
                .orElseThrow(() -> new NotFoundException("Session not found")));
       
        repository.save(event);

        return "redirect:/dashboard";
    }

    

}
