package com.fit3161.project.database;

import com.fit3161.project.database.club.ClubRepository;
import com.fit3161.project.database.club.ClubService;
import com.fit3161.project.database.user.UserClubRepository;
import com.fit3161.project.database.user.UserRepository;
import com.fit3161.project.database.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@RequiredArgsConstructor
public class Database implements UserService, ClubService {
    private final UserRepository userRepository;
    private final UserClubRepository userClubRepository;
    private final ClubRepository clubRepository;
}
