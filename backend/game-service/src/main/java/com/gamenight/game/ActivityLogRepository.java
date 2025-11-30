package com.gamenight.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, String> {
    List<ActivityLog> findByUserId(String userId);

    List<ActivityLog> findByChallengeId(String challengeId);
}
