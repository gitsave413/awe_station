package cg.beylhond.soft.tools;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cg.beylhond.soft.controller.ControleurMere;
import cg.beylhond.soft.entite.Agent;

public class SecuredFilter implements Filter 
{

	/**
	 * Default constructor. 
	 */

	public SecuredFilter()
	{
	}

	/**
	 * @see Filter#destroy()
	 */

	public void destroy() 
	{
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		Agent agent = null;

		agent = (Agent) ControleurMere.workingMemory.get("principal");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep  = (HttpServletResponse) response;
			
		if(agent == null)
		{
			req.getRequestDispatcher("/vues/connexion.xhtml").forward(req, rep);
		}
		else if(agent != null && req.getServletPath().startsWith("/vues/connexion"))
		{
			req.getRequestDispatcher("/vues/index.xhtml").forward(req, rep);
		}

		// pass the request along the filter chain
		chain.doFilter(req, rep);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	
	public void init(FilterConfig fConfig) throws ServletException
	{
	}
}
