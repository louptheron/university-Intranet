package tp_portail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/gestionSupports")
@MultipartConfig
public class GestionSupportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String resultat;

	public String getResultat() {
		return resultat;
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionSupportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnseignementDB enseignementDB = new EnseignementDB();

		request.setAttribute("modules", enseignementDB.recupererEnseignements());	

		
		this.getServletContext().getRequestDispatcher("/WEB-INF/gestionSupports.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	    // Create path components to save the file
	    final String path = request.getParameter("path");
	    final Part filePart = request.getPart("file");
	    
	    final String fileName = getFileName(filePart);
	    
	    OutputStream out = null;
	    InputStream filecontent = null;

	    try {
	    	new File(getServletContext().getRealPath("/") + path).mkdirs();
	        out = new FileOutputStream(new File(getServletContext().getRealPath("/") + path + File.separator
	                + fileName));
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
			request.setAttribute("upload", "Nouveau fichier " + fileName + " : " + path);	

	        
	    } catch (FileNotFoundException fne) {
			request.setAttribute("upload", "Erreur: " + fne.getMessage());	

	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	    }
	    EnseignementDB enseignementDB = new EnseignementDB();

		request.setAttribute("modules", enseignementDB.recupererEnseignements());	

		doGet(request, response);
	}
	
    private String getFileName(final Part pPart) {
        for (String lContent : pPart.getHeader("content-disposition")
            .split(";")) {
          if (lContent.trim().startsWith("filename")) {
            return lContent.substring(lContent.indexOf('=') + 1).trim().replace("\"", "");
          }
        }
        return null;
      }

}
