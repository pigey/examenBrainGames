package com.pigey.examenbraingames.leaderboards.nummerMemory;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "nummer_memory")
public class NrMemModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String score;
    private String username;

    public NrMemModel(Long id, String score, String username) {
        this.id = id;
        this.score = score;
        this.username = username;
    }

    public NrMemModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TooDoModel{" +
                "listInformation='" + score + '\'' +
                ", author='" + username + '\'' +
                '}';
    }
}
