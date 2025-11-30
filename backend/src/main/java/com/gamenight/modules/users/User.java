package com.gamenight.modules.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String email;
    private String displayName;
    private LocalDateTime createdAt;

    public static User create(String email, String displayName) {
        return new User(UUID.randomUUID().toString(), email, displayName, LocalDateTime.now());
    }
}
