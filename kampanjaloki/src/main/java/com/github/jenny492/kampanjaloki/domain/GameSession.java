package com.github.jenny492.kampanjaloki.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionid;

    @ManyToOne
    @JoinColumn(name = "campaignid")
    @NotNull
    private Campaign campaignid;

    @NotBlank
    private String title;
    private String content;

    @NotNull
    private LocalDateTime session_date;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "sessionid", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    @JsonIgnore
    private List<GameEvent> events;

    public GameSession () {

    }

    public GameSession(Campaign campaignid, String title, String content, LocalDateTime session_date, LocalDateTime created_at) {
        this.campaignid = campaignid;
        this.title = title;
        this.content = content;
        this.session_date = session_date;
        this.created_at = created_at;
    }

    public Long getSessionid() {
        return sessionid;
    }

    public void setSessionid(Long id) {
        this.sessionid = id;
    }
  
    public Campaign getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(Campaign campaign) {
        this.campaignid = campaign;
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
        return "Session [id=" + sessionid + ", title=" + title + ", content=" + content + ", session_date=" + session_date
                + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
    }
}
