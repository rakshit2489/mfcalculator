package com.pimp.finance.mfCalculator.service;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MFService {

    public BigDecimal getSensexDetails(String url, List<String> check) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements ele = doc.getAllElements();
            String sensex = doc.getElementById("sensex_value").ownText();
            BigDecimal sharePrice = new BigDecimal(sensex);
            return sharePrice;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BigDecimal getMFPrice(String url, List<String> check) {
        try {
            Document doc = Jsoup.connect(url).get();   //.parse(getPageAsString(url));
            Elements value = doc.getElementsByTag("title");
            String price = "";
            for (Element e : value) {
                price = e.html();
                //if (check.contains(price)) {
                    price = StringUtils.substringBetween(price, "[", "]");
                //}
            }
            //System.out.println("---------- " + check.toString() + " pirce -  " + price);
            return new BigDecimal(price);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
