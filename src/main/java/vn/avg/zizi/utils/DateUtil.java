package vn.avg.zizi.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;

import vn.avg.zizi.common.CommonConstants;

/**
 * <p>ファイル名 : DateUtil</p>
 * <p>説明 : Handling convert data Date</p>
 * @author hung.pd
 * @since 2018/5/31
 */
public class DateUtil {

    /** default yyyy/mm/dd */
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    /**
     * 
     * <p>説明 : convertDateToString</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @param localDate LocalDate
     * @return string date yyyy/mm/dd
     */
    public static String convertDateToString(LocalDate localDate) {
        if (localDate == null) {
            return CommonConstants.EMPTY;
        }
        return localDate.format(formatter);
    }

    /**
     * 
     * <p>説明 : convertDateToString</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @param localDate LocalDate
     * @param parternDate Format date
     * @return string date yyyy/mm/dd
     */
    public static String convertDateToString(LocalDate localDate, String parternDate) {
        try {
            formatter = DateTimeFormatter.ofPattern(parternDate);
            return localDate.format(formatter);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * <p>説明 : convertDateToString</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @param localDateTime LocalDateTime
     * @param parternDate Format date
     * @return string date yyyy/mm/dd
     */
    public static String convertDateToString(LocalDateTime localDateTime, String parternDate) {
        formatter = DateTimeFormatter.ofPattern(parternDate);
        return localDateTime.format(formatter);
    }

    /**
     * 
     * <p>説明 : convertStringToDate</p> 
     * @author hung.pd
     * @since 2018/5/31
     * @param strDate   String value date
     * @param parternDate   format date
     * @return local date
     */
    public static LocalDate convertStringToDate(String strDate, String parternDate) {
        try {
            formatter = DateTimeFormatter.ofPattern(parternDate);
            return (LocalDate) LocalDate.parse(strDate, formatter);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 
     * <p>説明 : check fromDate and toDate is valid or not</p> 
     * @author : an.tv
     * @since : 2018/05/31
     * @param fromDate From date
     * @param toDate To date
     * @return boolean
     */
    public static boolean isValidFromDateToDate(LocalDate fromDate, LocalDate toDate) {
        if (fromDate != null && toDate != null && fromDate.compareTo(toDate) > 0) {
            return false;
        }
        return true;
    }

    /**
     * 
     * <p>説明 : convert java.util.Date to LocalDateTime</p> 
     * @author : phuc.nh
     * @since : 2018/05/31
     * @param date
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLdt(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        ZonedDateTime zdt = cal.toZonedDateTime();
        return zdt.toLocalDateTime();
    }

    /**
    * 
    * <p>説明 : convert java.time.LocalDateTime to java.util.Date</p> 
    * @author : phuc.nh
    * @since : 2018/05/31
    * @param date
    * @return LocalDateTime
    */
    public static Date convertLdtToDate(LocalDateTime ldt) {
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        GregorianCalendar cal = GregorianCalendar.from(zdt);
        return cal.getTime();
    }

    /**
    * 
    * <p>説明 : convert java.time.LocalDate to java.util.Date</p> 
    * @author : phuc.nh
    * @since : 2018/05/31
    * @param date
    * @return LocalDateTime
    */
    public static Date convertLdtToDate(LocalDate ldt) {
        return Date.from(ldt.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * <p>説明 : Convert Datetime to string yyyy/MM/dd </p> 
     * @author hung.pd
     * @param localDateTime LocalDateTime
     * @return String format date
     */
    public static String convertDateToString(LocalDateTime localDateTime) {
        return localDateTime.format(formatter);
    }
}
