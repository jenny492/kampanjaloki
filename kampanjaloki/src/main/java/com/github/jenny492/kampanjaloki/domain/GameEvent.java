package com.github.jenny492.kampanjaloki.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class GameEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventid;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "session")
    private GameSession session;

    private String title;

    // @NotNull
    private Integer orderIndex;

    private String content;
    private String details;

    public GameEvent() {
    }

    public GameEvent(GameSession session) {
        this.session = session;
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
