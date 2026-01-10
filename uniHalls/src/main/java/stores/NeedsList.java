/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stores;

import items.Facility;
import items.NEEDS;
import items.REQ_TYPE;
import java.util.ArrayList;
import java.util.HashSet;


/**
 *
 * @author kg00k
 */
public class NeedsList {
    private final HashSet<Facility> needs;
    
    public NeedsList (){
        needs = new HashSet<>();
    }
    
    @Override
    public String toString(){
        return needs.toString();
    }
    
    public Facility[] getAll (){
        return needs.toArray(Facility[]::new);
    }
    public ArrayList getByType(REQ_TYPE type) {
        ArrayList<Facility> sorted = new ArrayList<>();
        for (Facility need : needs) {
            if (need.getNeedType() == type){
                sorted.add(need);
            }
        }
        return sorted;
    }
    
    public boolean addItem(Facility need){
        if (contains(need)){
            return false;
        } 
        needs.add(need);
        return true; 
       }
    public boolean removeItem(Facility need) {
        if (needs.contains(need)){
            needs.remove(need);
            return true;
        }
        return false;        
    }
    public boolean contains(Facility need){
        REQ_TYPE type = need.getNeedType();
        NEEDS n = need.getNeed();
        for (Facility f : needs){
            if (type == f.getNeedType() && n == f.getNeed()){
                return true;
            }
        }
        return false;
    }
}
            

