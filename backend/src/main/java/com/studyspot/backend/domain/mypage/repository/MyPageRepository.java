package com.studyspot.backend.domain.mypage.repository;

import com.studyspot.backend.domain.mypage.entity.MyPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageRepository extends JpaRepository<MyPage, Long> {
}