package org.project.wicket;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.project.repository.UserDaoInterface;
import org.project.repository.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class SignInSession extends AuthenticatedWebSession
{

    private static final Logger logger = LoggerFactory.getLogger(SignInSession.class);

    @SpringBean
    private UserDaoInterface userDao;


    @SpringBean
    private PasswordEncoder encoder;

    private UserModel user;

     protected SignInSession(Request request) {
        super(request);
        Injector.get().inject(this);
     }

    //return True if the user was
    @Override
    public final boolean authenticate(final String username, final String rawPassword)
    {

        String password = userDao.getPasswordByName(username);
        if (password==null) return false;
        return  encoder.matches(rawPassword, password);
    }

    public UserModel getUser()
    {
        return user;
    }

    public void setUser(final UserModel user)
    {
        this.user = user;
    }

    @Override
    public Roles getRoles()
    {
        return null;
    }
}
