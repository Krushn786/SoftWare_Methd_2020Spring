import java.util.StringTokenizer;

/**
 * Contains methods for constructing a date and checking if the date is valid.
 * Contains methods for accessing the day, month, and year individually,
 *    a toString method for converting the object into a string,
 *    and an equals method to check equivalency.
 * @author  Jake Ippolito, Krushn Gor
 */
public class Date
{
   private int  day;
   private int  month;
   private int  year;
   private static String DELIM = "/";

   /**
    * Constructs a date object containing the month, day, and year given a string.
    * @param d, string with the format "mm/dd/yyyy"
    */
   public Date(String d)
   {
      //use StringTokenizer to parse the String and create a Date object
      StringTokenizer st = new StringTokenizer(d, DELIM);
      month = Integer.parseInt(st.nextToken());
      day =  Integer.parseInt(st.nextToken());
      year = Integer.parseInt(st.nextToken());
   }

   /**
    * Constructs a date object containing the month, day, and year given a Date object.
    * @param d, Date object
    */
   public Date(Date d)
   {
       this.year = d.getYear();
       this.month = d.getMonth();
       this.day = d.getDay();
   }

   /**
    * Accessor method for the day.
    * @return An integer for the day
    */
   public int getDay()
   {
      return day;
   }

   /**
    * Accessor method for the month.
    * @return An integer for the month
    */
   public int getMonth() {
      return month;
   }

   /**
    * Accessor method for the year.
    * @return An integer for the year
    */
   public int getYear() {
      return year;
   }

   /**
    * Checks if the date is valid.
    * A date is valid if it has the correct number of days in the month for the corresponding year.
    * @return true if the date is valid, false otherwise.
    */
   public boolean isValid()
   {
      if(this.day < 1)
      {
         return false;
      }
      boolean isLeapYear = false;
      //Check to see if the year is a quadrennial
      if(year % Month.QUADRENNIAL == 0)
      {
         isLeapYear = true;
         //Check to see if the year is a centennial
         if(year % Month.CENTENNIAL == 0)
         {
            //Check to see if the year is a quatercentennial
            if(year % Month.QUATERCENTENNIAL != 0)
            {
               isLeapYear = false;
            }
         }
      }
      //Check each month with their corresponding amount of days
      switch (month)
      {
         case Month.JAN:
         case Month.MAR:
         case Month.MAY:
         case Month.JUL:
         case Month.AUG:
         case Month.OCT:
         case Month.DEC:
            if(day > Month.DAYS_ODD)
            {
               return false;
            }
            break;
         case Month.FEB:
            //Check if the month is February and check the corresponding amount of days.
            //If the year is a leap year, add 1 otherwise add 0.
            if(day > (Month.DAYS_FEB + (isLeapYear ? 1 : 0)))
            {
               return false;
            }
            break;
         case Month.APR:
         case Month.JUN:
         case Month.SEP:
         case Month.NOV:
            if(day > Month.DAYS_EVEN)
            {
               return false;
            }
            break;
         default:
            return false;
      }
      return true;
   }

   /**
    * Returns the date in the form of a string.
    * @return the date in "mm/dd/yyyy" format
    */
   @Override
   public String toString()
   {
       return month + "/" + day + "/" + year;
   }

   /**
    * Checks if an object is equivalent.
    * An object is equivalent if the object is a date and has the same month, day, and year.
    * @param obj, object to be compared
    * @return Returns true if the object is equivalent, false otherwise.
    */
   @Override
   public boolean equals(Object obj)
   {

      // Date x = new Date();
      //Date y = new Date();
      //xx/yy/zzzz
      //mm/dd/yyyy
      if(obj instanceof Date) {

         Date that = (Date) obj;
         if (this.day == that.day)
            if (this.month == that.month)
               return this.year == that.year;
      }
      return false;

   }

   /**
    * Testbed.main used for independent testing of cases on the Date class.
    * @param args, main arguments, additional arguments unused
    */
public static void main(String [] args)
        {
           Date test1 = new Date("11/30/1998");
           Date test2 = new Date("2/29/2016");
           Date test3 = new Date("2/29/2015");
           Date test4 = new Date("2/29/2000");
           Date test5 = new Date("12/31/2020");
           Date test6 = new Date("9/31/2019");
           Date test7 = new Date("2/29/1600");
           Date test8 = new Date("2/30/1600");
           Date test9 = new Date("2/29/1700");
           Date test10 = new Date(test9);

           System.out.println("Validity Checks: (Date.isValid())");
           System.out.println(test1);
           System.out.println(test1.isValid());
           System.out.println(test2);
           System.out.println(test2.isValid());
           System.out.println(test3);
           System.out.println(test3.isValid());
           System.out.println(test4);
           System.out.println(test4.isValid());
           System.out.println(test5);
           System.out.println(test5.isValid());
           System.out.println(test6);
           System.out.println(test6.isValid());
           System.out.println(test7);
           System.out.println(test7.isValid());
           System.out.println(test8);
           System.out.println(test8.isValid());
           System.out.println(test9);
           System.out.println(test9.isValid());
           System.out.println();

           System.out.println("Equivalency Checks: (Date.equal())");
           System.out.println("Does Date: 2/29/1700 = 2/29/1700?");
           System.out.println(test10.equals(test9));
           System.out.println("Does Date: 2/29/1700 = 2/30/1600?");
           System.out.println(test10.equals(test8));
           String str = new String();
           System.out.println("Does Date: 2/29/1700 = (String object)?");
           System.out.println(test10.equals(str));
        }
}


