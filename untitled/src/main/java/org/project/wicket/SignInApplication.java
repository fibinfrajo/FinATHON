package org.project.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.project.config.SpringJdbcConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class SignInApplication extends WebApplication
{

    private static final Logger logger = LoggerFactory.getLogger(SignInApplication.class);

    private static AnnotationConfigApplicationContext springContext;

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

        // Initialize Spring context
        springContext = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);

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

                // okay to proceed
                return true;
            }
        });
    }
}
