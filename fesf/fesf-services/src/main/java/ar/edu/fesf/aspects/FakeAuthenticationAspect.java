package ar.edu.fesf.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;

import ar.edu.fesf.security.FakeAuthentication;

@Aspect
public class FakeAuthenticationAspect {

    @Around("execution(* ar.edu.fesf.services.SpringInitializedService.initialize())")
    public Object serviceLayerExceptionHandling(final ProceedingJoinPoint pjp) throws Throwable {

        SecurityContextHolder.getContext().setAuthentication(new FakeAuthentication());

        Object result = pjp.proceed();

        SecurityContextHolder.getContext().setAuthentication(null);

        return result;
    }

    // @Before("execution(* ar.edu.fesf.scala.view.pages.ScalaHome.initialize())")
    // public void applicationFakeUser() {
    // SecurityContextHolder.getContext().setAuthentication(new FakeAuthentication());
    // }

}
