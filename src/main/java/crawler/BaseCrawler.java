package crawler;

import java.io.IOException;

public interface BaseCrawler {


    /**
     * 取得最新一集的url
     *
     * @return
     */
    Result getLastedEp() throws IOException;


    class Result {
        private String lastedEpName;
        private Integer lastedEpNumber;
        private String lastedEpUrl;

        public Result(String lastedEpName, Integer lastedEpNumber, String lastedEpUrl) {
            this.lastedEpName = lastedEpName;
            this.lastedEpNumber = lastedEpNumber;
            this.lastedEpUrl = lastedEpUrl;
        }

        public String getLastedEpName() {
            return lastedEpName;
        }

        public Integer getLastedEpNumber() {
            return lastedEpNumber;
        }

        public String getLastedEpUrl() {
            return lastedEpUrl;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "lastedEpName='" + lastedEpName + '\'' +
                    ", lastedEpNumber=" + lastedEpNumber +
                    ", lastedEpUrl='" + lastedEpUrl + '\'' +
                    '}';
        }
    }

}
