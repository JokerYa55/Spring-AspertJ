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
import org.springframework.stereotype.Component;

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
    public Object aroundCallAt(ProceedingJoinPoint pjp) {
        LOG.info("method user = {}", pjp.getSignature().getName());
        LOG.info("user = {}", currentSession);
        if (currentSession.getSessionId() == null) {
            return null;
        }
        Object retVal = null;
        Object[] methodArgs = pjp.getArgs();
        Arrays.asList(methodArgs).forEach((t) -> {
            LOG.info("arg = {}", t);
        });
//        if (securityService.checkRight(user)) {
//            retVal = pjp.proceed();
//        }
        return retVal;
    }
}
