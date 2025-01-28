package org.project.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SignOut extends WebPage
{
    public SignOut(final PageParameters parameters)
    {
        getSession().invalidate();
    }
}
