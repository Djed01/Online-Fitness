package org.unibl.etf.onlinefitness.services;


import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.NewsDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    private final String feedUrl = "https://feeds.feedburner.com/AceFitFacts";

    public List<NewsDTO> consumeFeed() {
        List<NewsDTO> result = new ArrayList<>();
        try {
            URL feedSource = new URL(feedUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            for (SyndEntry entry : feed.getEntries()) {
                NewsDTO item = new NewsDTO();
                item.setCategory(entry.getCategories().get(0).toString());
                item.setTitle(entry.getTitle());
                item.setDescription(entry.getDescription().getValue());
                item.setLink(entry.getLink());
                result.add(item);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
