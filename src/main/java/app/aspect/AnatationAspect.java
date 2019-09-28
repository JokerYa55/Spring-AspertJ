package app.aspect;

import app.bean.UserSession;
import java.util.Arrays;
import static jdk.nashorn.internal.objects.NativeFunction.call;
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
import org.springframework.util.StopWatch;

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
        StopWatch clock = new StopWatch(pjp.toString());
        clock.start(pjp.toShortString());
        if (currentSession.getSessionId() == null) {
            LOG.info("NULL");
            LOG.info(clock.prettyPrint());
            return new ResponseEntity("test", HttpStatus.UNAUTHORIZED);
        } else {
            Object retVal = null;
            retVal = pjp.proceed();
            LOG.info(clock.prettyPrint());
            return retVal;
        }
    }
}
