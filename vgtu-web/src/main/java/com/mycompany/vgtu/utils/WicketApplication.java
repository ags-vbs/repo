package com.mycompany.vgtu.utils;

import com.mycompany.vgtu.domain.modules.DomainModule;
import com.mycompany.vgtu.pages.HomePage;
import com.mycompany.vgtu.utils.modules.CustomServletModule;
import org.apache.wicket.guice.GuiceComponentInjector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.mycompany.vgtu.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{    	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
                getComponentInstantiationListeners().add(new GuiceComponentInjector(this,
                new CustomServletModule(), new DomainModule()));

		// add your configuration here
	}
}
