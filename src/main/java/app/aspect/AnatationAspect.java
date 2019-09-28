package app.aspect;

import app.bean.UserSession;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author vasil
 */
@Aspect
@Component
public class AnatationAspect {

    private static final Logger LOG = LoggerFactory.getLogger(AnatationAspect.class);

    @Autowired
    UserSession currentSession;

    @Pointcut("@annotation(SecurityAnatation)")
    public void callAtMyServiceSecurityAnnotation() {
    }

    @Around("callAtMyServiceSecurityAnnotation()")
    public Object aroundCallAt(ProceedingJoinPoint pjp) throws Throwable {
        LOG.info("method user = {}", pjp.getSignature().getName());
        LOG.info("user = {}", currentSession);
        Object[] methodArgs = pjp.getArgs();
        Arrays.asList(methodArgs).forEach((t) -> {
            LOG.info("arg = {}", t);
        });
        if (currentSession.getSessionId() == null) {
            LOG.info("NULL");
            return new ResponseEntity("test", HttpStatus.UNAUTHORIZED);
        } else {
            Object retVal = null;
            retVal = pjp.proceed();
            return retVal;
        }
    }
}
