package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.regex.Pattern;

public class ManhuaguiCrawler implements BaseCrawler {

    private final String rootPath;
    private final String resourcePath;

    public ManhuaguiCrawler(String rootPath, String resourcePath) {
        this.rootPath = rootPath;
        this.resourcePath = resourcePath;
    }

    @Override
    public Result getLastedEp() throws IOException {
        Document doc = Jsoup.connect(rootPath + "/" + resourcePath).get();
        Element lastedEpElement = doc.select("#detail-list-select-1 li").get(0).select("a").get(0);
        final String lastedEpText = lastedEpElement.text();
        final String lastedEpName = lastedEpElement.attr("title");
        final int lastedEpNumber = extractEpNumber(lastedEpText.split(" ")[0]);
        final String lastedEpLink = lastedEpElement.attr("href");

        assert lastedEpLink != null;
        final String lastedEpUrl = rootPath + lastedEpLink;
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
        final String epNumberText = sb.toString();
        if (!Pattern.compile("\\d+").matcher(epNumberText).matches()) {
            return 0;
        }

        return Integer.parseInt(epNumberText);
    }

}
