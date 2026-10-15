package com.github.jenny492.kampanjaloki.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sessions")
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", nullable = false, updatable = false)
    private Long sessionid;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    @NotNull
    private Campaign campaign;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "content")
    private String content;

    @Column(name = "session_date")
    private LocalDateTime sessionDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("orderIndex ASC")
    @JsonIgnore
    private List<GameEvent> events;

    public GameSession() {

    }

    public GameSession(Campaign campaign, String title, String content, LocalDateTime session_date,
            LocalDateTime created_at) {
        this.campaign = campaign;
        this.title = title;
        this.content = content;
        this.sessionDate = session_date;
        this.createdAt = created_at;
    }

    public Long getSessionid() {
        return sessionid;
    }

    public void setSessionid(Long id) {
        this.sessionid = id;
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

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDateTime sessionDate) {
        this.sessionDate = sessionDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
        return "Session [id=" + sessionid + ", title=" + title + ", content=" + content + ", sessionDate="
                + sessionDate
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}
