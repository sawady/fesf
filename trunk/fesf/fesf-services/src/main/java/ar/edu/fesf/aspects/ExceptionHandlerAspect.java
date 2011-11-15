package ar.edu.fesf.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import ar.edu.fesf.exceptions.AuthenticationException;

@Aspect
public class ExceptionHandlerAspect {

    @AfterThrowing("execution(* ar.edu.fesf.*.*(..))")
    public Object serviceLayerExceptionHandling(final ProceedingJoinPoint pjp) {
        throw new AuthenticationException("sarasa");
    }

}
