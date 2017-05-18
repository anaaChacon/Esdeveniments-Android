package com.example.anabel.esdevenimentsvalencia.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Anabel on 17/05/2017.
 */

public class Utils {


    public static String parseDate(String toParse) {
        if (toParse != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date;
            try {
                date = format.parse(toParse);
            } catch (Exception e) {
                return "";
            }
            SimpleDateFormat appFormat = new SimpleDateFormat("EEEE dd/MM hh:mm");
            return appFormat.format(date);
        } else {
            return "";
        }
    }

}
