package org.project.wicket;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.project.repository.UserDao;
import org.project.repository.UserModel;

public final class SignInSession extends AuthenticatedWebSession
{

    @SpringBean
    private UserDao userDao;

    private UserModel user;

     protected SignInSession(Request request) {
        super(request);
        Injector.get().inject(this);
     }

    //return True if the user was
    @Override
    public final boolean authenticate(final String username, final String password)
    {
        user = userDao.getUserByName(username);
        return password.equals(user.getPassword());
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
