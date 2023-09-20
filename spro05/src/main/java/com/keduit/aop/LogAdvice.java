package com.keduit.aop;


import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j
@Component
public class LogAdvice {

    //여기서 시작 *은 모든 그리고 패키지 및에 최종 interface에 모든 메소드의 모든 매개변수를 사용하겠다!!
    @Before("execution(* com.keduit.service.SampleService*.*(..))")
    public void logBefore(){
        log.info("============================");
    }

    @Before("execution(* com.keduit.service.SampleService*.doAdd(String,String)) && args(str1,str2)")
    public void logBeforeWithParam(String str1, String str2){
        log.info("str1......"+str1);
        log.info("str2......"+str2);
    }

    @AfterThrowing(pointcut = "execution(* com.keduit.service.SampleService*.*(..))"
            , throwing = "exception")
    public void logException(Exception exception){
        log.info("exception.....!!!!!!");
        log.info(exception);
    }

    @Around("execution(* com.keduit.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint joinPoint){
        long start = System.currentTimeMillis();
        log.info("------target----"+joinPoint.getTarget());
        log.info("--------param : "+ Arrays.toString(joinPoint.getArgs()));
        //invoke method
        Object result = null;

        try {
            result = joinPoint.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        log.info("Time : "+(end - start));
        return result;
    }

}
