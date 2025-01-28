package org.project.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.project.repository.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SignIn extends WebPage
{

    private static final Logger logger = LoggerFactory.getLogger(SignIn.class);

    @SpringBean
    private UserModel user;

    public SignIn()
    {
        add(new FeedbackPanel("feedback"));

        add(new SignInForm("signInForm", user));
    }

    public static final class SignInForm extends Form<Void>
    {
        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";

        private UserModel user;

        //id of the form component
        public SignInForm(final String id,  UserModel user)
        {
            super(id);
            this.user = user;
            add(new TextField<>(USERNAME, LambdaModel.of(user::getName, user::setName)));
            add(new PasswordTextField(PASSWORD, LambdaModel.of(user::getPassword, user::setPassword)));
        }

        @Override
        public final void onSubmit()
        {

            SignInSession session = getMySession();

            if (session.signIn(user.getName(), user.getPassword()))
            {
                continueToOriginalDestination();
                setResponsePage(getApplication().getHomePage());
            }
            else
            {
                String errmsg = getString("loginError", null, "Unable to sign you in");
                error(errmsg);
            }
        }


        private SignInSession getMySession() { return (SignInSession)getSession();}
    }
}
