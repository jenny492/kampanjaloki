package com.github.jenny492.kampanjaloki.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "game_events")
public class GameEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", nullable = false, updatable = false)
    private Long eventid;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "session_id")
    private GameSession session;

    @Column(name = "title")
    private String title;

    // @NotNull
    @Column(name = "order_index")
    private Integer orderIndex;

    @Column(name = "content")
    private String content;
    
    @Column(name = "details")
    private String details;

    public GameEvent() {
    }

    public GameEvent(GameSession session, String content) {
        this.session = session;
        this.content = content;
    }

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long id) {
        this.eventid = id;
    }

    public GameSession getSession() {
        return session;
    }

    public void setSession(GameSession session) {
        this.session = session;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }
    /*
     * public void setOrder_index(int order_index) {
     * this.order_index = order_index;
     * }
     */

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Event [id=" + eventid + ", title=" + title + ", content=" + content
                + ", details=" + details + "]";
    }

}
