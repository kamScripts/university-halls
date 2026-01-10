/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package stores.tests;

import people.Payment;
import stores.PaymentList;

/** Collection class to store Payment objects/
 * @author Kamil Gebski
 * @version 10/01/26
 */
public class testPaymentList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PaymentList pl = new PaymentList(10);
        pl.addPayment(new Payment(400.0, 9));
        pl.addPayment(new Payment(400.0, 10));
        pl.addPayment(new Payment(400.0, 11));
        System.out.println(pl);
        System.out.println(pl.getPayment(1));
    }
    
}
