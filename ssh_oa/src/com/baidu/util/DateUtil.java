package com.baidu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 日期工具类，提供与日期相关的常用方法
 * @Class Name DateUtil
 * @Author sa
 * @Create In 2008-10-6
 */
public class DateUtil {
    //~ Static fields/initializers =============================================

    private static Log log = LogFactory.getLog(DateUtil.class);
    public static String datePattern = "yyyy-MM-dd"; //20040807 tufei
    public static String timePattern = datePattern + " HH:mm:ss";

    //~ Methods ================================================================

    /**
     * Return default datePattern (yyyy-MM-dd)
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        return datePattern;
    }

    public static String getTimePattern() {
        return timePattern;
    }

    /**
     * This method attempts to convert an Oracle-formatted date
     * in the form dd-MMM-yyyy to yyyy-mm-dd.
     *
     * @param aDate date from database as a string
     * @return formatted string for the ui
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }
    
    public   static   Date   getLastDayOfMonth(Date   sDate1)   {   
        Calendar   cDay1   =   Calendar.getInstance();   
        cDay1.setTime(sDate1);   
        final   int   lastDay   =   cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);   
        Date   lastDate   =   cDay1.getTime();   
        lastDate.setDate(lastDay);   
        return   lastDate;   
	}   
    
    public   static   Date   getFirstDayOfMonth(Date   sDate1)   {   
        Calendar   cDay1   =   Calendar.getInstance();   
        cDay1.setTime(sDate1);   
        final   int   lastDay   =   cDay1.getActualMinimum(Calendar.DAY_OF_MONTH);   
        Date   lastDate   =   cDay1.getTime();   
        lastDate.setDate(lastDay);   
        return   lastDate;   
	}   


    /**
     * This method generates a string representation of a date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException
     */
    public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                      + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            //log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format:
     * yyyy-MM-dd HH:MM a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }
    
    /**
     * 获取当前日期，日期格式为年月日
     * @Methods Name getCurrentDate
     * @Create In 2008-10-6 By sa
     * @return java.util.Date
     */
    public static java.util.Date getCurrentDate(){
    	 Date today = new Date();
         SimpleDateFormat df = new SimpleDateFormat(datePattern);

         String todayAsString = df.format(today);
         Calendar cal = new GregorianCalendar();
//         try {
			cal.setTime(convertStringToDate(todayAsString));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

         return (java.util.Date)cal.getTime();
    }
    
    /**
     * 获取当前的日期，日期格式为年月日时分秒
     * @Methods Name getCurrentDateTime
     * @Create In 2008-10-6 By sa
     * @return java.util.Date
     */
    public static java.util.Date getCurrentDateTime(){
   	 Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.timePattern);

        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
//        try {
			cal.setTime(convertStringToDate(todayAsString));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

        return (java.util.Date)cal.getTime();
   }
    
    
    public static java.sql.Date getCurrentSQLDate(){
   	 Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(datePattern);

        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
//        try {
			cal.setTime(convertStringToDate(todayAsString));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

        return (java.sql.Date)cal.getTime();
   }

    /**
     * This method generates a string representation of a date's date/time
     * in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     *
     * @see java.text.SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 将日期转成 年月日 格式的字符串
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(Date aDate) {
        return getDateTime(datePattern, aDate);
    }
    
    /**
     * 将日期转成年月日 时分秒格式的字符串
     * @Methods Name convertDateTimeToString
     * @Create In Apr 13, 2009 By sa
     * @param aDate
     * @return String
     */
    public static final String convertDateTimeToString(Date aDate) {
        return getDateTime(timePattern, aDate);
    }
    
    public static final Date convertStringToDateTime(String aDate) {
        try {
			return convertStringToDate(timePattern, aDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format yyyy-MM-dd/)
     * @return a date object
     *
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate) {
        Date aDate = null;

        try {
            if (log.isDebugEnabled()) {
                log.debug("converting date with pattern: " + datePattern);
            }
            aDate = convertStringToDate(datePattern, strDate);
        } catch (ParseException pe) {
            log.error("Could not convert '" + strDate
                      + "' to a date, throwing exception");
            pe.printStackTrace();
            /*throw new ParseException(pe.getMessage(),
                                     pe.getErrorOffset());*/

        }

        return aDate;
    }


    public static Date addDays(Date date, int days) {
        return add(date, days, Calendar.DATE);
    }


    public static Date addMonths(Date date, int months) {
        return add(date, months, Calendar.MONTH);
    }

    public static Date add(Date date, int amount, int field) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(field, amount);

        return calendar.getTime();
    }
    /**
	 * 把yyyy-MM-dd HH类型的字符创转换成日期类型
	 * @param da
	 * @return
	 */
	public static Date getStringToDateFull(String da){
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date date=null;
		try {
			date=sdf.parse(da);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public static String getDateToStringFull(Date date){
		//SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		//Date date=null;
		String da=sdf.format(date);
		return da;
	}
	public static String getDateToStringFull2(Date date){
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMddHHmmss" );
		//Date date=null;
		String da=sdf.format(date);
		return da;
	}
	//获取指定范围的随机数
	public static int getRandom(int _min,int _max) {
	  int random = 0;
	  int min = _min;
	  int max = _max+1;
	  boolean flag = true;
	  
	  while(flag) {
	      random = (int) Math.round(Math.floor(Math.random() * max));
	      if(random >= min) {
	       flag = false;
	      }
	  }
	  return random;
	 }
	public static int getDateNum(Date date){
		Calendar   cDay1   =   Calendar.getInstance();   
        cDay1.setTime(date);   
        int   num   =   cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);   
        return num;
	}
	 /**
	  * 获取唯一值id，年月日时分秒+三位随即数
	  * @return
	  */
	public static String getdateAndRandom(){
		 String s=getDateToStringFull2(new Date())+getRandom(100,999);
		 return s;
	 }
	
	public static void main(String [] args){
		System.out.println(getDateToStringFull2(new Date())+" "+getRandom(100,999));
	}
}
