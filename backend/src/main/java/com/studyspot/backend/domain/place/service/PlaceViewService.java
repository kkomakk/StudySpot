package com.studyspot.backend.domain.place.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PlaceViewService {

    private final Map<Long, Map<String, Long>> activeViewers = new ConcurrentHashMap<>();

    // 1. 시청자 입장 및 하트비트 갱신
    public void addViewer(Long placeId, String sessionId) {
        activeViewers.putIfAbsent(placeId, new ConcurrentHashMap<>());
        activeViewers.get(placeId).put(sessionId, System.currentTimeMillis());
    }

    // 2. 현재 특정 장소를 보고 있는 접속자 퇴장
    public void removeViewer(Long placeId, String sessionId) {
        if (activeViewers.containsKey(placeId)) {
            activeViewers.get(placeId).remove(sessionId);
        }
    }

    // 3. 현재 특정 장소를 보고 있는 사람 수 조회
    public int getViewerCount(Long placeId) {
        if (!activeViewers.containsKey(placeId)) return 0;
        return activeViewers.get(placeId).size();
    }

    // 4. 비정상 종료자 자동 청소
    @Scheduled(fixedRate = 30000)
    public void removeInactiveViewers() {
        long now = System.currentTimeMillis();
        long timeout = 60000;

        activeViewers.forEach((placeId, viewers) -> {
            viewers.entrySet().removeIf(entry -> (now - entry.getValue()) > timeout);
        });
    }
}