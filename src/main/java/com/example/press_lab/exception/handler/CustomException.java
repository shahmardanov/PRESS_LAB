package com.example.press_lab.exception.handler;


import com.example.press_lab.exception.advertisement.AdvertisementConflictException;
import com.example.press_lab.exception.advertisement.AdvertisementContentNotFoundException;
import com.example.press_lab.exception.advertisement.AdvertisementNotFoundException;
import com.example.press_lab.exception.kart.KartConflictException;
import com.example.press_lab.exception.kart.KartContentNotFoundException;
import com.example.press_lab.exception.kart.KartFkNewsIdNotFoundException;
import com.example.press_lab.exception.kart.KartNotFoundException;
import com.example.press_lab.exception.news.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.CONFLICT;

@RestControllerAdvice
@Slf4j
public class CustomException {

    @ExceptionHandler(AdvertisementConflictException.class)
    @ResponseStatus(CONFLICT)
    public ProblemDetail handlerAdvertisementConflictException(Exception ex){
        log.info("handlerAdvertisementConflictException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(CONFLICT, ex.getMessage());
    }
    @ExceptionHandler(AdvertisementContentNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerAdvertisementContentNotFoundException(Exception ex){
        log.info("handlerAdvertisementContentNotFoundException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(AdvertisementNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerAdvertisementNotFoundException(Exception ex){
        log.info("handlerAdvertisementNotFoundException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }


    @ExceptionHandler(KartConflictException.class)
    @ResponseStatus(CONFLICT)
    public ProblemDetail handlerKartConflictException(Exception ex){
        log.info("handlerKartConflictException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(CONFLICT, ex.getMessage());
    }
    @ExceptionHandler(KartContentNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerKartContentNotFoundException(Exception ex){
        log.info("handlerKartContentNotFoundException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(KartFkNewsIdNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerKartFkNewsIdNotFoundException(Exception ex){
        log.info("handlerKartFkNewsIdNotFoundException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(KartNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerKartNotFoundException(Exception ex){
        log.info("handlerKartNotFoundException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }



    @ExceptionHandler(NewsConflictException.class)
    @ResponseStatus(CONFLICT)
    public ProblemDetail handlerNewsConflictException(Exception ex){
        log.info("handlerNewsConflictException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(CONFLICT, ex.getMessage());
    }
    @ExceptionHandler(NewsNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerNewsNotFoundException(Exception ex){
        log.info("handlerNewsNotFoundException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(NewsContentNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerNewsContentNotFoundException(Exception ex){
        log.info("handlerNewsContentNotFoundException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(NewsActiveNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerNewsStatusActiveException(Exception ex){
        log.info("handlerNewsStatusActiveException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(NewsInactiveNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerNewsStatusInActiveException(Exception ex){
        log.info("handlerNewsStatusInActiveException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(NewsCategoryNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerNewsCategoryNotFoundException(Exception ex){
        log.info("handlerNewsCategoryNotFoundException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(NewsSubCategoryNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ProblemDetail handlerNewsSubCategoryNotFoundException(Exception ex){
        log.info("handlerNewsSubCategoryNotFoundException {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
    }

}
