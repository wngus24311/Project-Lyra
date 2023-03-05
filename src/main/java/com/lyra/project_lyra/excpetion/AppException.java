package com.lyra.project_lyra.excpetion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@AllArgsConstructor
@Log4j2
@Getter
public class AppException extends RuntimeException {
    // 임의의 CustomException 생성
    private ErrorCode errorCode;
    String message;

}
