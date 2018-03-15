package com.example.shehnepours.taxam.webCrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shehnepour.s on 11/16/2017.
 */

public class Crawler {

    public static void main(String[] args) {
        List<String> Web = new ArrayList<String>();
        Web.add("http://www.thehindu.com");
        Web.add("http://www.indianexpress.com");
        Web.add("http://www.ndtv.com");
        Web.add("http://www.tehekla.com");

        try {

            for (int i = 0; i < Web.size(); i++) {
                // URL my_url = new URL("http://www.thehindu.com/");
                String a = Web.get(i).toString();
                System.out.println(a);
                URL my_url = new URL(a);
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        my_url.openStream()));
                String strTemp = "";
                while (null != (strTemp = br.readLine())) {
                    System.out.println(strTemp);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
