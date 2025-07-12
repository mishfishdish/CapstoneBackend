package com.fit3161.project.database.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class UserClubId implements Serializable {

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "club_id")
    private UUID clubId;

    public UserClubId() {}

    public UserClubId(UUID userId, UUID clubId) {
        this.userId = userId;
        this.clubId = clubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserClubId)) return false;
        UserClubId that = (UserClubId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(clubId, that.clubId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, clubId);
    }

    // Getters and setters (or use Lombok)
}