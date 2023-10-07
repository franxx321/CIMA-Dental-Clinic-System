package Utils;

import java.util.Date;

public class FormatedDate {

    public static Date formatedDate(Date date){
        date.setTime(date.getTime()-date.getTime()%86400000);
        return date;
    }


}
