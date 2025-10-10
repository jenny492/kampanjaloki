package com.github.jenny492.kampanjaloki.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GameEvent {

    @Id
    private Long id;
    private GameSession session;
    private String title;
    private int order_index;
    private String summary;
    private String details;

    public GameEvent() {
    }

    public GameEvent(GameSession session, String title, String summary, String details) {
        this.session = session;
        this.title = title;
        this.summary = summary;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getOrder_index() {
        return order_index;
    }

    public void setOrder_index(int order_index) {
        this.order_index = order_index;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", title=" + title + ", order_index=" + order_index + ", summary=" + summary
                + ", details=" + details + "]";
    }

}
