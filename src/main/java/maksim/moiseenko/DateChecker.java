package maksim.moiseenko;

import java.util.Date;

public class DateChecker {
    public static boolean dateChecker(Date date){
        int day=date.getDay();
        int month=date.getMonth();
        int year=date.getYear();
        if(year%4==0 && month==2 && day<=29) return true;
        if((month==1 ||month==3 || month==5 || month==7 || month==8 || month==10 || month==12) && day <=31)
            return true;
        if((month==4 ||month==6 || month==9 || month==11 ) && day <=30) return true;
        return false;
    }
}
