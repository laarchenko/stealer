package com.example.stealer.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ApplicationException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String reasonPhrase;

    ApplicationException() {
        httpStatus = HttpStatus.BAD_REQUEST;
        reasonPhrase = null;
    }
}
