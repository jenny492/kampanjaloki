package com.github.jenny492.kampanjaloki.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;

@Entity
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Campaign campaign;

    private String title;
    private String content;
    private LocalDateTime session_date;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    @OrderColumn
    private List<GameEvent> events = new ArrayList<>();

    public GameSession () {

    }

    public GameSession(Campaign campaign, String title, String content, LocalDateTime session_date) {
        this.campaign = campaign;
        this.title = title;
        this.content = content;
        this.session_date = session_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  
    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSession_date() {
        return session_date;
    }

    public void setSession_date(LocalDateTime session_date) {
        this.session_date = session_date;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public void addEvent(GameEvent e) {
        events.add(e);
    }

    // TODO: Lisää myös removeEvent jos tarvitsee

    public List<GameEvent> getEvents() {
        return events;
    }

    public void setEvents(List<GameEvent> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Session [id=" + id + ", title=" + title + ", content=" + content + ", session_date=" + session_date
                + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
    }
}
