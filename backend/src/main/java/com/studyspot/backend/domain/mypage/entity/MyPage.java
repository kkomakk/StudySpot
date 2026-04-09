package com.studyspot.backend.domain.admin.entity; // 패키지명 주의! 만약 에러나면 Alt+Enter로 수정하세요.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MyPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String bio; //예시입니다.
}