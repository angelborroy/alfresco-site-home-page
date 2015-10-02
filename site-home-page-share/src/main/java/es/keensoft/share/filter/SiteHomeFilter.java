package es.keensoft.share.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.ApplicationContext;
import org.springframework.extensions.surf.RequestContextUtil;
import org.springframework.extensions.surf.site.AuthenticationUtil;
import org.springframework.extensions.surf.support.AlfrescoUserFactory;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.connector.Connector;
import org.springframework.extensions.webscripts.connector.ConnectorContext;
import org.springframework.extensions.webscripts.connector.ConnectorService;
import org.springframework.extensions.webscripts.connector.HttpMethod;
import org.springframework.extensions.webscripts.connector.Response;
import org.springframework.web.context.support.WebApplicationContextUtils;

// Unordered filter: chain until first user logged request arrives
@WebFilter(urlPatterns={"/page/*"})
public class SiteHomeFilter implements Filter {
	
	private static final String SESSION_ATTRIBUTE_KEY_ON_SITE = "_alf_ON_SITE_NAVIGATION";
	private static final String REGEX_SITE_NAME = "\\/site\\/(.*)\\/(.*)";
	
	private ConnectorService connectorService;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		this.connectorService = (ConnectorService) context.getBean("connector.service");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		
		String userId = AuthenticationUtil.getUserId(request);
		
		// Site home page redirection
		if (request.getPathInfo().indexOf("/site/") != -1 && request.getPathInfo().endsWith("/dashboard")) {
			if (doRedirect(request, request.getPathInfo())) {
				
				try {
					
					RequestContextUtil.initRequestContext(WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext()), request, true);
                    Connector conn = connectorService.getConnector(AlfrescoUserFactory.ALFRESCO_ENDPOINT_ID, userId, session);
			        Response res = conn.call("/keensoft/site/home/" + getSiteName(request.getPathInfo()), new ConnectorContext(HttpMethod.GET));
			        
			        if (Status.STATUS_OK == res.getStatus().getCode()) {
			        	
			            JSONObject siteHomePageUrl = (JSONObject) new JSONParser().parse(res.getResponse());
			            if (siteHomePageUrl.get("homePagePath") != null) {
					        response.sendRedirect(request.getContextPath() + "/" + siteHomePageUrl.get("homePagePath"));
			            }
			            
			        }
			        
				} catch (Exception e) {
					throw new ServletException(e);
				}
			}
		}
		// If out of site scope, clear session
		if (request.getPathInfo().indexOf("/site/") == -1) {
			clearAttribute(request);
		}
        
    	chain.doFilter(req, resp);
	}
	
	private boolean doRedirect(HttpServletRequest request, String pathInfo) {
		
        HttpSession session = request.getSession();
        
        String site = getSiteName(pathInfo);
        
        if (session.getAttribute(SESSION_ATTRIBUTE_KEY_ON_SITE) != null && 
        	session.getAttribute(SESSION_ATTRIBUTE_KEY_ON_SITE).equals(site)) {
        	return false;
        } else {
        	session.setAttribute(SESSION_ATTRIBUTE_KEY_ON_SITE, site);
        	return true;
        }
        
	}
	
	private void clearAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SESSION_ATTRIBUTE_KEY_ON_SITE);
	}
	
    public static String getSiteName(String shareUrl) {
        Pattern p1 = Pattern.compile(REGEX_SITE_NAME);
        Matcher m1 = p1.matcher(shareUrl);
        if (m1.find()) {
            return m1.group(1);
        } else {
            return null;
        }
    }
	
	@Override
	public void destroy() {}
}
