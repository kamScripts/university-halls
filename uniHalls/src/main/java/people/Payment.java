/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package people;

/**Class representing single rent payment
 *
 * @author Kamil Gebski
 * @version 10/01/26
 */
public class Payment {
       private final int month;
       private final double sum;
       /**Constructor initialises Payment
        * 
        * @param sumIn: Amount of Payment
        * @param monthIn: month of Payment 
        */
       public Payment(double sumIn, int monthIn) {
        sum = sumIn;
        month = monthIn;
    }
    @Override
    public String toString(){
        return "(" + month + ": " + sum + ")";
    }
    public int getMonth() {
        return month;
    }

    public double getSum() {
        return sum;
    }

    
}
