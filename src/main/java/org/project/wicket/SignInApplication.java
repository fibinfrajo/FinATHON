package org.project.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public final class SignInApplication extends WebApplication
{

    @Override
    public Class<? extends Page> getHomePage()
    {
        return Home.class;
    }

    @Override
    public Session newSession(Request request, Response response)
    {
        return new SignInSession(request);
    }


    @Override
    protected void init()
    {
        super.init();

        // Enable Spring dependency injection in Wicket components
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        
        // Register the authorization strategy
        getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy.AllowAllAuthorizationStrategy()
        {
            @Override
            public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
                    Class<T> componentClass)
            {
                // Check if the new Page requires authentication (implements the marker interface)
                if (AuthenticatedWebPage.class.isAssignableFrom(componentClass))
                {
                    if (((SignInSession)Session.get()).isSignedIn())
                    {
                        return true;
                    }

                    throw new RestartResponseAtInterceptPageException(SignIn.class);
                }

                return true;
            }
        });
    }

}
