package org.scoula.common.util.dto;

import lombok.Getter;
import lombok.Setter;

/*
    공통 응답 래퍼 패턴
    -> RESTAPI는 엔드포인트마다 성공/예외에 대한 응답 구조가 달라짐
    -> 따라서 FrontEnd에서 처리하기가 어렵다.
    -> 클라이언트가 성공/실패 여부를 쉽게 판단할 수 있게 함
 */
@Getter
@Setter
public class ApiResponse<T> {
    private boolean success; // 성공여부
    private String message; // 성공시 "success" 실패시 에러메시지
    private T data; // 실제 응답 데이터

    // 성공
    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.builder()
                .success(true)
                .message("success")
                .data(data)
                .build();
    }

    // 실패
    public static <T> ApiResponse<T> fail(String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .build();
    }
}
