package ar.edu.fesf.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import ar.edu.fesf.exceptions.TransactionException;

@Aspect
public class ExceptionHandlerAspect {

    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object serviceLayerExceptionHandling(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (final Exception e) {
            final Logger log = Logger.getLogger(pjp.getSignature().getDeclaringType());
            log.error("Error executing method " + pjp.getSignature().getName(), e);
            throw new TransactionException(e.getMessage());
        }
    }
}