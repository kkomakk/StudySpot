package com.studyspot.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 나중에 로그인할 때 이메일로 유저를 찾으려고
    Optional<User> findByEmail(String email);
}