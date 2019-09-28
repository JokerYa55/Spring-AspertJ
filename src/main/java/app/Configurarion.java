package app;

import app.bean.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author vasil
 */
@Configuration
public class Configurarion {

    private static final Logger LOG = LoggerFactory.getLogger(Configurarion.class);

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserSession getUserSession() {
        return new UserSession();
    }

}
