package vn.tdtin.quarkus.oidc;

import io.quarkus.security.identity.SecurityIdentity;

public class User {

    private final String userName;

    public User(SecurityIdentity securityIdentity) {
        this.userName = securityIdentity.getPrincipal().getName();
    }

    public String getUserName() {
        return userName;
    }
}
