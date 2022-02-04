package com.jamesdev.springbootinstagramclone.handler.aop;

import com.jamesdev.springbootinstagramclone.handler.ex.CustomValidationApiException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class ValidationAdvice {

      @Around("execution (* com.jamesdev.springbootinstagramclone.controller.api.*Controller.*(..))")
      public Object apiAdvice(ProceedingJoinPoint pj) throws Throwable{
            Object[] args=pj.getArgs();
            for (Object arg : args) {
                  if(arg instanceof BindingResult){
                        BindingResult bindingResult= (BindingResult) arg;
                        if(bindingResult.hasErrors()){
                              //custom Error 만들어서 errormap 던지기
                              Map<String,String> errorMap = new HashMap<>();
                              for(FieldError fieldError:bindingResult.getFieldErrors()){
                                    errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                              }
                              System.out.println("errorMap : "+errorMap);
                              throw new CustomValidationApiException("유효성 검사 실패",errorMap);
                        }
                  }
            }
            return pj.proceed();
      }
}
