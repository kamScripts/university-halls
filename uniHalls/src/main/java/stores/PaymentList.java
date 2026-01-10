/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stores;


import people.Payment;

/** Collection class to store Payment objects/
 * @author Kamil Gebski
 * @version 10/01/26
 */
public class PaymentList {
    private final Payment[] paymentList;
    private int pointer;
    public int max;
    /**
     * 
     * @param maxIn: Maximum number of payments.
     */
    public PaymentList(int maxIn){
        max = maxIn;
        paymentList = new Payment[max];
        pointer = 0;
    }
    @Override
    public String toString()
    {
        String s = "";
        for (Payment p : paymentList){
            if (p==null){
                break;
            }
            s +=p;
        }
        return s;
    }        
    public boolean addPayment(Payment payIn)
    {
        if (pointer >=0 && pointer < max) {
            paymentList[pointer] = payIn;
            pointer+=1;
            return true;
        }
        return false;        
    }
    public Payment getPayment(int index)
    {
        if(pointer >=0 && pointer < max) {
           return paymentList[index]; 
        }
        return null;
    }

    
    
    
}
