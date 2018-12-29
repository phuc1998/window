/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author PHUC
 */
public class CommonFunction {
    public static String currentTimeDateFormat(){
        Date date = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
        String content = format.format(date);
        return content;
    }
    
    public static String currentDateFormat(){
        Date date = Calendar.getInstance().getTime();
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String content = format.format(date);
        return content;
    }
}
