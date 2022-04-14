public class MonthConversion {
    
     public String monthNumeric(String monthString){
         String month = "";
         if(monthString.equals("January")){
             month = "01";
         }else if(monthString.equals("February")){
            month = "02";
         }else if(monthString.equals("March")){
            month = "03";
         }else if(monthString.equals("April")){
            month = "04";
         }else if(monthString.equals("May")){
            month = "05";
         }else if(monthString.equals("June")){
            month = "06";
         }else if(monthString.equals("July")){
            month = "07";
         }else if(monthString.equals("August")){
            month = "08";
         }else if(monthString.equals("September")){
            month = "09";
         }else if(monthString.equals("October")){
            month = "10";
         }else if(monthString.equals("November")){
            month = "11";
         }else if(monthString.equals("December")){
            month = "12";
         }
         return month;
     }       
}
