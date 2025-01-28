package org.project.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.project.repository.UserModel;

public final class SignIn extends WebPage
{

    public SignIn()
    {
        add(new FeedbackPanel("feedback"));
        add(new SignInForm("signInForm"));
    }

    public static final class SignInForm extends Form<Void>
    {
        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";

        Model<String> nameModel = Model.of("");
        Model<String> passwordModel = Model.of("");
        //id of the form component
        public SignInForm(final String id)
        {
            super(id);
            add(new TextField<>(USERNAME, nameModel));
            add(new PasswordTextField(PASSWORD, passwordModel));
        }

        @Override
        public final void onSubmit()
        {

            SignInSession session = getMySession();
            if (session.signIn(nameModel.getObject(), passwordModel.getObject()))
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
