package app.bean;

/**
 *
 * @author vasil
 */
public class UserSession {

    private String sessionId;

    public UserSession() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "UserSession{" + "sessionId=" + sessionId + '}';
    }

}
