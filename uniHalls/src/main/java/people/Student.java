/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package people;

import java.util.ArrayList;

/**
 *
 * @author kg00k
 */
public class Student extends Person{
    private Payment[] paymentList;
    private int pointer;
    
    public Student(String name, int age, String email, Gender gender) {
        super(name, age, email, gender);
        paymentList = new Payment[10];
        pointer=0;
    }
    
}
