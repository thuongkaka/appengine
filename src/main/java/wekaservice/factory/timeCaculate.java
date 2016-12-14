package wekaservice.factory;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * Created by ltctien on 2016/11/23.
 */
public class timeCaculate {
    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
    public Boolean validateTimeRange(Date fromDate, Date toDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);

        int dateCount = daysBetween(fromDate, toDate);

        int fromDateOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTime(toDate);
        int toDateOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return dateCount==6 && fromDateOfWeek == Calendar.SUNDAY && toDateOfWeek == Calendar.SATURDAY;
    }
    // convert birthday to age
    public  float getFloatAge(Date _birthday,Date _forecasted_date){
        float age= 0;
        Calendar calendar_birthday = new GregorianCalendar();
        calendar_birthday.setTime(_birthday);
        Calendar calendar_forecasted_date = new GregorianCalendar();
        calendar_forecasted_date.setTime(_forecasted_date);
        int years= calendar_forecasted_date.get(Calendar.YEAR) - calendar_birthday.get(Calendar.YEAR);
        int month = calendar_forecasted_date.get(Calendar.MONTH) - calendar_birthday.get(Calendar.MONTH);

        int date = calendar_forecasted_date.get(Calendar.DATE) - calendar_birthday.get(Calendar.DATE);
        Calendar last_birthday = new GregorianCalendar(calendar_forecasted_date.get(Calendar.YEAR) -1 , calendar_birthday.get(Calendar.MONTH), calendar_birthday.get(Calendar.DATE));
        if(month<=0)
        {
            if(month==0 && date==0){
                age = years;
            }
            if(month==0 && date!=0){
                age = (float)years + date/365;
            }
            if(month<0){
                age= (float)(years -1)+ (float)(calendar_forecasted_date.get(Calendar.DAY_OF_YEAR) + (365- last_birthday.get(Calendar.DAY_OF_YEAR)))/365;
            }
        }else{
            age= (float)years+ (float)(calendar_forecasted_date.get(Calendar.DAY_OF_YEAR) + (365-last_birthday.get(Calendar.DAY_OF_YEAR)))/365;
        }
        return age;

    }
}
