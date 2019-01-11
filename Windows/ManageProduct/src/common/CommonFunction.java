/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;
import sun.security.provider.MD5;

/**
 *
 * @author PHUC
 */
public class CommonFunction {

    public static String currentTimeDateFormat() {
        Date date = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
        String content = format.format(date);
        return content;
    }

    public static String currentDateFormat() {
        Date date = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String content = format.format(date);
        return content;
    }

    public static String getCurrency(int number) {
        NumberFormat formatter = new DecimalFormat("###,###");
        String resp = formatter.format(number);
        resp = resp.replaceAll(",", ".");
        return resp;
    }

    public static String formatDate(Date date, String formatString) {
        DateFormat format = new SimpleDateFormat(formatString);
        String resp = format.format(date);
        return resp;
    }

    public static Date parseDate(String dateS, String formatString) {
        DateFormat format = new SimpleDateFormat(formatString);
        Date resp;
        try {
            resp = format.parse(dateS);
        } catch (ParseException ex) {
            resp = null;
        }
        return resp;
    }

    public static String encodePassword(String pass) {
        String passEncode = Base64.encodeBase64URLSafeString(pass.getBytes());
        return passEncode;
    }

}
