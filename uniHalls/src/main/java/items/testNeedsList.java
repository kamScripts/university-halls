/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package items;

import stores.NeedsList;

/**
 *
 * @author kg00k
 */
public class testNeedsList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NeedsList lst = new NeedsList();
        Facility n1 = new Facility(REQ_TYPE.FLOOR, NEEDS.GROUND_FLOOR);
        Facility n2 = new Facility(REQ_TYPE.ROOM, NEEDS.SINGLE);
        Facility n3 = new Facility(REQ_TYPE.DIET, NEEDS.VEGAN);
        lst.addItem(n1);
        lst.addItem(n2);
        lst.addItem(n3);

       

        System.out.println(lst);
        System.out.println(lst.getByType(REQ_TYPE.DIET));
    }
    
}
