package Utils;

import java.util.Date;

public class String2Date {

    private String2Date(){

    }

    public static Date string2Date(String fechaCompleta){
        String diaString = fechaCompleta.substring(0, fechaCompleta.indexOf("/"));
        String aux1 = fechaCompleta.substring(fechaCompleta.indexOf("/") + 1);
        String mesString = aux1.substring(0, aux1.indexOf("/"));
        aux1 = aux1.substring(aux1.indexOf("/") + 1);
        String anioString = aux1.substring(0,5);
        aux1 = aux1.substring(5);
        String horaString = aux1.substring(0, aux1.indexOf(":"));
        aux1 = aux1.substring(aux1.indexOf(":") + 1);
        String minuteString = aux1;

        Date date = FormatedDate.formatedDate(new Date());
        date.setMonth(Integer.parseInt(mesString)-1);
        date.setDate(Integer.parseInt(diaString));
        date.setYear(Integer.parseInt(anioString)-1900);
        date.setHours(Integer.parseInt(horaString));
        date.setMinutes(Integer.parseInt(minuteString));
        return date;
    }
}
