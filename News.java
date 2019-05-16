import java.util.Date;

public class News {
      public String title;
      public String link;
      public Date fecha;
      public News(String title, String link, Date fecha) {
        // codifica 
        this.title=title;
        this.link=link;
        this.fecha=fecha;
      }
      public String getTitle(){ return title;}
      public String getLink(){ return link;}
      public Date getFecha(){ return fecha;}
}
