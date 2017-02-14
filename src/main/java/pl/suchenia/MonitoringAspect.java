package pl.suchenia;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitoringAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    private void isRestController() {
    }

    @Pointcut("execution(public * pl.suchenia.*.*(..))")
    private void webPackage() {
    }

    @Before("webPackage() && isRestController()")
    public void restMethodTime() {

    }
}
