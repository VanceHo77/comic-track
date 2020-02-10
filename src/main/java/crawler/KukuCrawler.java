package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Pattern;

public class KukuCrawler implements BaseCrawler {

    private final String url;

    public KukuCrawler(String url) {
        this.url = url;
    }

    @Override
    public Result getLastedEp() throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements comiclistn = doc.select("#comiclistn dd");
        Element lastedEpElement = comiclistn.get(comiclistn.size() - 1);
        final String lastedEpText = lastedEpElement.text();
        final String lastedEpName = lastedEpText.split(" ")[0];
        final Integer lastedEpNumber = extractEpNumber(lastedEpText);
        final String lastedEpUrl = lastedEpElement.select("a").get(1).attr("href");
        return new Result(lastedEpName, lastedEpNumber, lastedEpUrl);
    }

    public Integer extractEpNumber(String lastedEpText) {
        char[] epNumberCharAry = lastedEpText.toCharArray();
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("\\d");
        for (char c : epNumberCharAry) {
            if (pattern.matcher(String.valueOf(c)).matches()) {
                sb.append(c);
            }
        }
        return Integer.parseInt(sb.toString());
    }

}
