package com.studyspot.backend.domain.user.entity;

import com.studyspot.backend.domain.user.UserRole;
import com.studyspot.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

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

    @Column(nullable = false, length = 100)
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
     * 아이디(이메일) 변경
     */
    public void changeEmail(String email) {
        Assert.hasText(email, "email must not be empty");
        this.email = email;
    }

    public void changeNickname(String nickname) {
        Assert.hasText(nickname, "nickname must not be empty");
        this.nickname = nickname;
    }

    public void changePassword(String encodedPassword) {
        Assert.hasText(encodedPassword, "password must not be empty");
        this.password = encodedPassword;
    }

    public void updateBio(String bio) {
        this.bio = bio;
    }
}