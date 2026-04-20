package com.studyspot.backend.domain.user;

import com.studyspot.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

/**
 * 사용자 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_users_nickname", columnNames = "nickname")
        }
)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(length = 255)
    private String bio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    @Builder
    public User(String email, String password, String nickname, UserRole role, String bio) {
        Assert.hasText(email, "email must not be empty");
        Assert.hasText(password, "password must not be empty");
        Assert.hasText(nickname, "nickname must not be empty");

        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = (role != null) ? role : UserRole.USER;
        this.bio = bio;
    }


    /**
     * 닉네임 변경
     */
    public void changeNickname(String nickname) {
        Assert.hasText(nickname, "nickname must not be empty");
        this.nickname = nickname;
    }

    /**
     * 비밀번호 변경
     */
    public void changePassword(String encodedPassword) {
        Assert.hasText(encodedPassword, "password must not be empty");
        this.password = encodedPassword;
    }

    /**
     * 자기소개 업데이트
     */
    public void updateBio(String bio) {
        this.bio = bio;
    }
}