package toast.library.meal;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/*
 * VERSION 8
 * UPDATE 20180212
 *
 * @author Mir(whdghks913)
 *
 * Use : getDateNew, getKcalNew, getMealNew, getPeopleNew
 * Delete : getDate, getKcal, getMeal, getMonthMeal, getPeople
 */
public class MealLibrary {

    /**
     * Version 8 Update
     *
     * �섏씠�� �쒕쾭媛� https濡� 諛붾�쒖뿉 �곕씪 �쒕쾭 蹂댁븞�쒕� 寃�利앺븯�� 肄붾뱶媛� �꾩슂�댁죱�듬땲��.
     * ��긽 true瑜� 諛섑솚�섎뒗 verify()瑜� Override�섎뒗 HostnameVerifier瑜� 留뚮뱾�덉뒿�덈떎.
     */
    private static HostnameVerifier hostnameVerifier = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * getDateNew
     */
    public static String[] getDateNew(String CountryCode, String schulCode, String schulCrseScCode,
                                      String schulKndScCode, String schMmealScCode) {

        String[] date = new String[7];
        String url = "https://stu." + CountryCode + "/sts_sci_md01_001.do?schulCode=" + schulCode + "&schulCrseScCode="
                + schulCrseScCode + "&schulKndScCode=" + schulKndScCode + "&schMmealScCode=" + schMmealScCode;

        return getDateNewSub(date, url);
    }

    public static String[] getDateNew(String CountryCode, String schulCode, String schulCrseScCode,
                                      String schulKndScCode, String schMmealScCode, String year, String month, String day) {

        String[] date = new String[7];
        String url = "https://stu." + CountryCode + "/sts_sci_md01_001.do?schulCode=" + schulCode + "&schulCrseScCode="
                + schulCrseScCode + "&schulKndScCode=" + schulKndScCode + "&schMmealScCode=" + schMmealScCode
                + "&schYmd=" + year + "." + month + "." + day;

        return getDateNewSub(date, url);
    }

    private static String[] getDateNewSub(String[] date, String url) {
        Source mSource = null;

        try {
            URL mUrl = new URL(url);

            InputStream mStream = null;

            /**
             * Version 8 Update
             *
             * �섏씠�ㅼ쓽 湲됱떇 �뚯떛 url�� https濡� 諛붾�쒖뿉 �곕씪 蹂댁븞�� 寃�利앺븯�� 異붽� 肄붾뱶媛� �꾩슂�댁죱�듬땲��.
             * �몄쬆�� �뺣낫瑜� 寃��ы븯�� 肄붾뱶瑜� 媛꾨떒�섍쾶 異붽��섏��듬땲��.
             *
             * 肄붾뱶瑜� �섏젙�⑥뿉 �곕씪 Source�� �앹꽦�먯뿉 �ㅼ뼱媛��� �섎뒗 �몄닔�� URL�� �ъ슜�섏� 紐삵빀�덈떎.
             * ���� jericho parser�� Source.java瑜� 蹂대㈃ �앹꽦�먯뿉 InputStream�� �ъ슜�� �� �덈떎�� �ъ떎�� 諛쒓껄�덉뒿�덈떎.
             */
            try {
                HttpsURLConnection urlConnection = (HttpsURLConnection) mUrl.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setHostnameVerifier(hostnameVerifier);
                mStream = urlConnection.getInputStream();
                mSource = new Source(mStream);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mStream != null) {
                    mStream.close();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mSource.fullSequentialParse();
        List<?> table = mSource.getAllElements("table");

        for (int i = 0; i < table.size(); i++) {
            if (((Element) table.get(i)).getAttributeValue("class").equals("tbl_type3")) {
                List<?> tr = ((Element) table.get(i)).getAllElements("tr");
                List<?> th = ((Element) tr.get(0)).getAllElements("th");

                for (int j = 0; j < 7; j++) {
                    date[j] = ((Element) th.get(j + 1)).getContent().toString();
                }

                break;
            }
        }

        return date;
    }

    /**
     * getKcalNew
     */
    public static String[] getKcalNew(String CountryCode, String schulCode, String schulCrseScCode,
                                      String schulKndScCode, String schMmealScCode) {
        String[] content = new String[7];
        String url = "https://stu." + CountryCode + "/sts_sci_md01_001.do?schulCode=" + schulCode + "&schulCrseScCode="
                + schulCrseScCode + "&schulKndScCode=" + schulKndScCode + "&schMmealScCode=" + schMmealScCode;

        return getKcalSubNew(content, url);
    }

    public static String[] getKcalNew(String CountryCode, String schulCode, String schulCrseScCode,
                                      String schulKndScCode, String schMmealScCode, String year, String month, String day) {
        String[] content = new String[7];
        String url = "https://stu." + CountryCode + "/sts_sci_md01_001.do?schulCode=" + schulCode + "&schulCrseScCode="
                + schulCrseScCode + "&schulKndScCode=" + schulKndScCode + "&schMmealScCode=" + schMmealScCode
                + "&schYmd=" + year + "." + month + "." + day;

        return getKcalSubNew(content, url);
    }

    private static String[] getKcalSubNew(String[] content, String url) {
        Source mSource = null;

        try {
            URL mUrl = new URL(url);

            InputStream mStream = null;

            try {
                HttpsURLConnection urlConnection = (HttpsURLConnection) mUrl.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setHostnameVerifier(hostnameVerifier);
                mStream = urlConnection.getInputStream();
                mSource = new Source(mStream);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mStream != null) {
                    mStream.close();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mSource.fullSequentialParse();
        List<?> table = mSource.getAllElements("table");

        work: for (int i = 0; i < table.size(); i++) {
            if (((Element) table.get(i)).getAttributeValue("class").equals("tbl_type3")) {
                List<?> tbody = ((Element) table.get(i)).getAllElements("tbody");
                List<?> __tr = ((Element) tbody.get(0)).getAllElements("tr");

                /**
                 * Version 8 Update
                 *
                 * 移쇰줈由� �뺣낫瑜� �닿퀬�덈뒗 tr�쒓렇�� index媛� 43-44濡� 蹂��섎뒗 �꾩긽�� 諛쒓껄�덉뒿�덈떎.
                 * �대줈�명빐 移쇰줈由щ� 媛��몄삤吏� 紐삵븯�� �ㅻ쪟媛� 諛쒖깮�덉뿀�듬땲��.
                 * 洹몃옒�� �됰꼮�섍쾶 42遺��� 45源뚯� 移쇰줈由� �뺣낫�� index瑜� 寃��ы븯�꾨줉 諛섎났臾몄쓣 援ъ꽦�덉뒿�덈떎.
                 */
                for (int index = 42; index < 46; index++) {
                    List<?> __th = ((Element) __tr.get(index)).getAllElements("th");

                    if (((Element) __th.get(0)).getContent().toString().equals("�먮꼫吏�(kcal)")) {
                        List<?> td = ((Element) __tr.get(index)).getAllElements("td");

                        for (int j = 0; j < td.size(); j++) {
                            content[j] = ((Element) td.get(j)).getContent().toString();
                        }

                        break work;
                    }
                }

                for (int index = 0; index < content.length; index++) {
                    content[index] = null;
                }

                break;
            }
        }

        return content;
    }

    /**
     * getMealNew
     */
    public static String[] getMealNew(String CountryCode, String schulCode, String schulCrseScCode,
                                      String schulKndScCode, String schMmealScCode) {

        String[] content = new String[7];
        String url = "https://stu." + CountryCode + "/sts_sci_md01_001.do?schulCode=" + schulCode + "&schulCrseScCode="
                + schulCrseScCode + "&schulKndScCode=" + schulKndScCode + "&schMmealScCode=" + schMmealScCode;

        return getMealNewSub(content, url);
    }

    public static String[] getMealNew(String CountryCode, String schulCode, String schulCrseScCode,
                                      String schulKndScCode, String schMmealScCode, String year, String month, String day) {

        String[] content = new String[7];
        String url = "https://stu." + CountryCode + "/sts_sci_md01_001.do?schulCode=" + schulCode + "&schulCrseScCode="
                + schulCrseScCode + "&schulKndScCode=" + schulKndScCode + "&schMmealScCode=" + schMmealScCode
                + "&schYmd=" + year + "." + month + "." + day;

        return getMealNewSub(content, url);
    }

    private static String[] getMealNewSub(String[] content, String url) {
        Source mSource = null;

        try {
            URL mUrl = new URL(url);

            InputStream mStream = null;

            try {
                HttpsURLConnection urlConnection = (HttpsURLConnection) mUrl.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setHostnameVerifier(hostnameVerifier);
                mStream = urlConnection.getInputStream();
                mSource = new Source(mStream);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mStream != null) {
                    mStream.close();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mSource.fullSequentialParse();
        List<?> table = mSource.getAllElements("table");

        for (int i = 0; i < table.size(); i++) {
            if (((Element) table.get(i)).getAttributeValue("class").equals("tbl_type3")) {
                List<?> tbody = ((Element) table.get(i)).getAllElements("tbody");
                List<?> tr = ((Element) tbody.get(0)).getAllElements("tr");
                List<?> title = ((Element) tr.get(2)).getAllElements("th");

                if (((Element) title.get(0)).getContent().toString().equals("�앹옱猷�")) {
                    List<?> tdMeal = ((Element) tr.get(1)).getAllElements("td");

                    for (int j = 0; j < 7; j++) {
                        content[j] = ((Element) tdMeal.get(j)).getContent().toString();
                        content[j] = content[j].replace("<br />", "\n");
                    }

                    break;
                }

                for (int index = 0; index < content.length; index++) {
                    content[index] = null;
                }

                break;
            }
        }

        return content;
    }

    /**
     * getPeopleNew
     */
    public static String[] getPeopleNew(String CountryCode, String schulCode, String schulCrseScCode,
                                        String schulKndScCode, String schMmealScCode) {
        String[] content = new String[7];
        String url = "https://stu." + CountryCode + "/sts_sci_md01_001.do?schulCode=" + schulCode + "&schulCrseScCode="
                + schulCrseScCode + "&schulKndScCode=" + schulKndScCode + "&schMmealScCode=" + schMmealScCode;

        return getPeopleSubNew(content, url);
    }

    public static String[] getPeopleNew(String CountryCode, String schulCode, String schulCrseScCode,
                                        String schulKndScCode, String schMmealScCode, String year, String month, String day) {
        String[] content = new String[7];
        String url = "https://stu." + CountryCode + "/sts_sci_md01_001.do?schulCode=" + schulCode + "&schulCrseScCode="
                + schulCrseScCode + "&schulKndScCode=" + schulKndScCode + "&schMmealScCode=" + schMmealScCode
                + "&schYmd=" + year + "." + month + "." + day;

        return getPeopleSubNew(content, url);
    }

    private static String[] getPeopleSubNew(String[] content, String url) {
        Source mSource = null;

        try {
            URL mUrl = new URL(url);

            InputStream mStream = null;

            try {
                HttpsURLConnection urlConnection = (HttpsURLConnection) mUrl.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setHostnameVerifier(hostnameVerifier);
                mStream = urlConnection.getInputStream();
                mSource = new Source(mStream);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mStream != null) {
                    mStream.close();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mSource.fullSequentialParse();
        List<?> table = mSource.getAllElements("table");

        for (int i = 0; i < table.size(); i++) {
            if (((Element) table.get(i)).getAttributeValue("class").equals("tbl_type3")) {
                List<?> tbody = ((Element) table.get(i)).getAllElements("tbody");
                List<?> __tr = ((Element) tbody.get(0)).getAllElements("tr");
                List<?> __th = ((Element) __tr.get(0)).getAllElements("th");

                if (((Element) __th.get(0)).getContent().toString().equals("湲됱떇�몄썝")) {
                    List<?> td = ((Element) __tr.get(0)).getAllElements("td");

                    for (int j = 0; j < 7; j++) {
                        content[j] = ((Element) td.get(j)).getContent().toString();
                    }

                    break;
                }

                for (int index = 0; index < content.length; index++) {
                    content[index] = null;
                }

                break;
            }
        }

        return content;
    }

    /**
     * isMealCheck meal�� "", " ", null�대㈃ false, �꾨땲硫� true
     */
    public static boolean isMealCheck(String meal) {
        return !("".equals(meal) || " ".equals(meal) || meal == null);
    }
}