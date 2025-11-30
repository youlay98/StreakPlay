package com.gamenight.game;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "challenges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Challenge {
    @Id
    private String id;
    private String title;
    private String description;
    private String cadence; // DAILY, WEEKLY
    private String ownerId;
    private LocalDateTime createdAt;
}
