package company;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CURLParser {
    private Document html;

    @SneakyThrows
    private void fillHTML(String domain) throws IOException {
        String[] command = {"curl", "--location", "--request", "GET", "https://" + domain};
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String inputLine;
        StringBuffer buff = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            buff.append(inputLine);
        }

        String strHTML = buff.toString();
        html = Jsoup.parse(strHTML);
    }

    public String getTitleByCurl(){
        return html.title();
    }

    @SneakyThrows
    private String getLocationByCurl() throws IOException {
        fillHTML("en.wikipedia.org/wiki/" + getTitleByCurl().replace(" ", "_"));
        Elements infoTable = html.getElementsByClass("infobox");
        Elements details = null;

        for (Element i :infoTable) {
            details = i.getElementsByTag("tbody");
        }

        if (details != null) {
            for (Element i :infoTable) {
                Pattern pattern = Pattern.compile("<a.*?</a>");
                Matcher matcher = pattern.matcher(String.valueOf(i.getElementsByClass(
                        "locality")).replace("\n", "").replace("\r", ""));
                matcher.find();

                String aLink = matcher.group();
                pattern = Pattern.compile(">.*<");
                matcher = pattern.matcher(aLink);
                matcher.find();
                String address = matcher.group();
                return address.substring(1, address.length()-1);
            }
        }
        return "";
    }

    @SneakyThrows
    public Company getCURLedObject(String domain) throws IOException {
        CURLParser parser = new CURLParser();
        parser.fillHTML(domain);
        String title = parser.getTitleByCurl();
        String address = parser.getLocationByCurl();
        Company output =  Company.builder()
                .name(title)
                .address(address)
                .build();
        return output;
    }


    public boolean isFilled() {
        return html == null;
    }


}
