package com.studyspot.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * 사용자 데이터 접근 레포지토리
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    // 닉네임 중복 체크 (회원가입/수정 시)
    boolean existsByNickname(String nickname);
}