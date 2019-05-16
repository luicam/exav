import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.XmlReader;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.feed.synd.SyndEntryImpl;

import java.net.URL;
import java.net.MalformedURLException;
import com.rometools.rome.io.FeedException;

 
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class PrimerServletModificadoEnClaseCompletandoPunto4 extends HttpServlet {
 
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handleRequest(req, res);
    }
 
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        handleRequest(req, res);
    }

    public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String pattern = req.getServletPath();
        HashMap<String,String> hash1 = new HashMap<String,String>(); 
 
        Enumeration<String> parameterNames = req.getParameterNames();
        int count = 0;
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
            String[] paramValues = req.getParameterValues(paramName);
            String st = "";
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                st += "\t" + paramValue;
                count += 1;
            }
            hash1.put(paramName,st);
        }
        if(pattern.equals("/greeting")) {
           req.setAttribute("pattern","greeting");
           String who = req.getParameter("name");
           if(count == 1 && (who != null)) {
              req.setAttribute("show","Hello " + who);
           }
           else {
              req.setAttribute("show","Parameter name required");
           }
        }
        if(pattern.equals("/news")) {
           List<News> lista1 = null;
           List<News> lista2 = null;

           req.setAttribute("pattern","news");
           lista1 = getting_titles_news();
           
           lista2 = aplicando_filtro_titles_news(lista1);
           req.setAttribute("lista",lista2);
        }
        if(pattern.equals("/allparameters")) {
           req.setAttribute("pattern","all");
           req.setAttribute("listParam", hash1);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/show.jsp");
        requestDispatcher.forward(req, res);
    }

    public List<News> getting_titles_news() throws IOException, ServletException  {
    
        SyndFeed feed = null;
        List<String> list = new ArrayList<String>();
        List<News> list1 = new ArrayList<News>();
        String url = furl("lav");
        try {
              feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
            }catch(FeedException|IOException ex){
                System.out.println("EEERRRORR");
                ex.printStackTrace();
            }
            List res = feed.getEntries();
            String descri;
            int i = 0;
            for(Object o : res) {
                String title = ((SyndEntryImpl) o).getTitle();
                if(((SyndEntryImpl) o).getDescription() != null) {
                  descri = ((SyndEntryImpl) o).getDescription().getValue();
                  if(descri != null && descri.length() > 6) {
                    News n = new News(title,descri);
                    list1.add(n);
                    ++i;
                  }
                }
                /*if(i >= 10) {
  		    break;
                }*/
            }
            return list1; 
            //return null;
    }
    //he querido tener en un metodo aparte la seleccion de fuente de informacion.
    public String furl(String name){
      String url ="https://www.lavanguardia.com/mvc/feed/rss/politica";
        if(name.equals("elp")) {
          url = "https://ep00.epimg.net/rss/tags/ultimas_noticias.xml";
        }
        if(name.equals("abc")) {
          url = "https://sevilla.abc.es/rss/feeds/Sevilla_Sevilla.xml";
        }
        return url;
    }

    public List<News> aplicando_filtro_titles_news(List<News> lista1){
      List<News> l2 = new ArrayList<News>();
      for(News n1 : lista1){
        if(n1.getTitle().matches(".*(PSOE|JxCat|Casado|Vox).*")){
          l2.add(n1);
        }else if(n1.getDescri().matches(".*Catalunya.*diputado.*")){
          l2.add(n1);
        }
      }
      return l2;
    }

    public class News {
      public String title;
      public String descri;
      public News(String t, String d) {
        title=t; descri=d;
      }
      public String getTitle(){ return title;}
      public String getDescri(){ return descri;}
    }

      //(".*(PSOE|J+Cat|Casado).*")
      //(".*Catalunya.*diputado.*")
      //en Catalunya habra seis diputados en la .....
      //true 142 teniendo en cuenta 143
      //(?i).  insensitivo
    
}
