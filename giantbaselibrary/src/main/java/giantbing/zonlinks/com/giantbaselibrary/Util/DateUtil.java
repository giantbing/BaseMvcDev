package giantbing.zonlinks.com.giantbaselibrary.Util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by P on 2017/8/9.
 */

public class DateUtil {

    /**
     * 得到几天前的时间
     *
     * @param day
     * @return date
     */
    public static Date getDateBefore(int day){
        Date dNow = new Date();
        Calendar now =Calendar.getInstance();
        now.setTime(dNow);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d,int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }
    //根据日期取得星期几
    public static String getWeek(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }
}
