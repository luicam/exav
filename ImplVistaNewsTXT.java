import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImplVistaNewsTXT implements VistaNews{
     public void writeNew(News n) {
          Date date = new Date();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
          
          String strDate = null;
          String title = null;
          String link = null;
          Rome4 r = new Rome4();
          for(News n1 : r.getListFeed()){
          	title=n1.getTitle();
          	link=n1.getLink();
          }
          //title=r.getListFeed().getTitle();
          //link=r.getListFeed().getLink();

          //Rome4 r = new Rome4(String name);
          //Rome4 r = new Rome4(args[0]);
          /*List<Object> res = feed.getEntries();
          int i = 0;
          for(Object o : res) {
          	System.out.println(((SyndEntryImpl) o).getTitle());
            title = ((SyndEntryImpl) o).getTitle(); 
            if(++i >= 0) {
                break;
            }
          }*/
          
          // codifica print el title
          // codifica print el link
          strDate = dateFormat.format(n.getFecha());
          System.out.println(title + "\n");
          System.out.println(link + "\n");
          System.out.println(strDate + "\n");

      }
}
