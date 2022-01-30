package com.jamesdev.springbootinstagramclone.handler.aop;

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
                        System.out.println("유효성 검사가 적용");
                        BindingResult bindingResult= (BindingResult) arg;
                        if(bindingResult.hasErrors()){
                              //custom Error 만들어서 errormap 던지기
                              Map<String,String> errorMap = new HashMap<>();
                              for(FieldError fieldError:bindingResult.getFieldErrors()){
                                    errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
                              }
                              throw new Exception();
                        }
                  }
            }
            return null;
      }
}
