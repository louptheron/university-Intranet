package tp_portail;

import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter("/SessionFilter")
public class SessionFilter implements Filter {
    /**
     * Default constructor. 
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        

         HttpSession session =request.getSession(false);
         Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
         
        if(utilisateur != null&&"theron".equals(utilisateur.getNom())&&"/tp_portail/authentification".equals(request.getRequestURI())){
        	 req.setAttribute("errorMessage", "dejaConnecté gros bof");
             System.out.println(utilisateur.getPrenom());
             req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, res);
        }
        else{
		String uri = request.getRequestURI();
        System.out.println("Sa marche ...." + uri);
        chain.doFilter(request, response);}
    }
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
    }
}
