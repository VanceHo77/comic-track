import crawler.BaseCrawler;
import crawler.KukuCrawler;
import crawler.ManhuaguiCrawler;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Runner {
  public static void main(String[] args) throws IOException {
    final String propertiesPath = getPropertiesPath();
    File propertiesFile = new File(propertiesPath);
    if (!propertiesFile.exists()) {
      propertiesFile.createNewFile();
    }
    Properties properties = PropertiesAccessHelper.read(propertiesPath);

    String comicName = "海賊王";
    String baseKukuUrl = "http://comic.ikkdm.com";
    BaseCrawler kuku海賊王 = new KukuCrawler(baseKukuUrl + "/comiclist/4/");
    BaseCrawler.Result result;
    boolean isLastedEp;
    try {
      result = kuku海賊王.getLastedEp();
      isLastedEp = isLastedEp(properties.getProperty(comicName), result);
      System.out.println(comicName + "is lasted:" + isLastedEp);
      if (isLastedEp) {
        System.out.println(result);
        properties.put(comicName, String.valueOf(result.getLastedEpNumber()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    comicName = "王者天下";
    BaseCrawler 漫畫人_王者天下 = new ManhuaguiCrawler("http://www.dm5.com", "manhua-wangzhetianxia");
    result = 漫畫人_王者天下.getLastedEp();
    isLastedEp = isLastedEp(properties.getProperty(comicName), result);
    System.out.println(comicName + " is lasted:" + isLastedEp);
    if (isLastedEp) {
      System.out.println(result);
      properties.put(comicName, String.valueOf(result.getLastedEpNumber()));
    }

    writeLastedEp(properties, propertiesPath);
  }

  private static boolean isLastedEp(String propEpNumber, BaseCrawler.Result result) {
    final Integer lastedEpNumber = result.getLastedEpNumber();
    if (null == propEpNumber) {
      return true;
    }
    return Integer.parseInt(propEpNumber) < lastedEpNumber;
  }

  private static String getPropertiesPath() {
    final File currentDirFile = new File(".");
    final String projectPath = currentDirFile.getAbsoluteFile().getParent();
    return projectPath + "/storeLastedEpInfo.properties";
  }

  private static void writeLastedEp(Properties properties, String propPath) throws IOException {
    PropertiesAccessHelper.write(properties, propPath);
  }
}
