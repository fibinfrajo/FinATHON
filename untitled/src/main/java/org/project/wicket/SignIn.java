package org.project.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;
import org.project.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SignIn extends WebPage
{

    private static final Logger logger = LoggerFactory.getLogger(SignIn.class);

    /**
     * Constructor
     */
    public SignIn()
    {
        // Create feedback panel and add to page
        add(new FeedbackPanel("feedback"));

        // Add sign-in form to page
        add(new SignInForm("signInForm"));
    }

    public static final class SignInForm extends Form<Void>
    {
        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";


        // El-cheapo model for form
        private final ValueMap properties = new ValueMap();

        /**
         * Constructor
         *
         * @param id
         *            id of the form component
         */
        public SignInForm(final String id)
        {
            super(id);

            // Attach textfield components that edit properties map model
            add(new TextField<>(USERNAME, new PropertyModel<String>(properties, USERNAME)));
            add(new PasswordTextField(PASSWORD, new PropertyModel<>(properties, PASSWORD)));
        }

        @Override
        public final void onSubmit()
        {

            // Get session info
            SignInSession session = getMySession();

            // Sign the user in
            if (session.signIn(getUsername(), getPassword()))
            {
                continueToOriginalDestination();
                setResponsePage(getApplication().getHomePage());
            }
            else
            {
                // Get the error message from the properties file associated with the Component
                String errmsg = getString("loginError", null, "Unable to sign you in");

                // Register the error message with the feedback panel
                error(errmsg);
            }
        }
        private String getPassword()
        {
            return properties.getString(PASSWORD);
        }

        private String getUsername()
        {
            return properties.getString(USERNAME);
        }

        private SignInSession getMySession()
        {
            return (SignInSession)getSession();
        }
    }
}
