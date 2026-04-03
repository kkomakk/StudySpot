package com.studyspot.backend.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 📢 "프로젝트 전체의 에러는 내가 다 관리할게!" 라고 선언하는 어노테이션
public class GlobalExceptionHandler {

    // 🛡️ 유효성 검사(@Valid)에서 걸린 에러들만 잡아서 처리하는 메서드
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // 에러가 난 항목(field)과 우리가 적어둔 메시지(message)만 쏙쏙 뽑아서 담기
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }
}