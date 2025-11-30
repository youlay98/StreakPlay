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
@Table(name = "activity_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityLog {
    @Id
    private String id;
    private String userId;
    private String challengeId;
    private String notes;
    private LocalDateTime completedAt;

    public static ActivityLog create(String userId, String challengeId, String notes) {
        return new ActivityLog(UUID.randomUUID().toString(), userId, challengeId, notes, LocalDateTime.now());
    }
}
