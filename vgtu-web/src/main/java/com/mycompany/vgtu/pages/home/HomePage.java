package com.mycompany.vgtu.pages.home;

import com.google.inject.Inject;
import com.mycompany.vgtu.domain.user.internal.UserDao;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

        @Inject
        private UserDao calculatorDao;
	public HomePage(final PageParameters parameters) {
		super(parameters);
                

		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		// TODO Add your page's components here

    }
}
