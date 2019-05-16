import java.util.ArrayList;
import java.util.List;

import java.util.Date;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.XmlReader;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.feed.synd.SyndEntryImpl;

import java.net.URL;
import java.net.MalformedURLException;
import com.rometools.rome.io.FeedException;
import java.io.IOException;


public class Rome4 {
	public List<News> listFeed = null;
        public List<News> getListFeed() {
            // Codifica
            return listFeed;
        }
        public Rome4() {   // el objetivo de los dos constructores es el de
                           // dar el contenido correcto al atributo listFeed
            findFeed("elp");
        }
        public Rome4(String name) {
            findFeed(name);
        }
        private void findFeed(String name) {
            listFeed = new ArrayList<News>();
            SyndFeed feed = null;
            String url = "https://ep00.epimg.net/rss/tags/ultimas_noticias.xml";
            if(name.equals("lav")) {
              url = "https://www.lavanguardia.com/mvc/feed/rss/politica";
            }
            if(name.equals("abc")) {
              url = "https://sevilla.abc.es/rss/feeds/Sevilla_Sevilla.xml";
            }
            try {
              feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
            }catch(FeedException|IOException ex){
                System.out.println("EEERRRORR");
                ex.printStackTrace();
            }
            // Codifica
            List<String> tit = new ArrayList<String>();
            List res = feed.getEntries();
            int i = 0;
            for(Object o : res) {
                System.out.println(((SyndEntryImpl) o).getTitle());
                tit.add(0,((SyndEntryImpl) o).getTitle()); 
                if(++i >= 1) {
                    break;
                }
            }
            News n = new News(tit.get(0),url,null);
            listFeed.add(n);

        }
}
