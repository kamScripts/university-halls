/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package items;

/**
 *
 * @author kg00k
 */
public class Facility {
    REQ_TYPE needType;
    NEEDS need;

    public Facility(REQ_TYPE needType, NEEDS need) {
        this.needType = needType;
        this.need = need;
    }
    
    @Override
    public String toString() {
        return needType + ": " + need;
    }
    public REQ_TYPE getNeedType() {
        return needType;
    }

    public void setNeedType(REQ_TYPE needType) {
        this.needType = needType;
    }

    public NEEDS getNeed() {
        return need;
    }

    public void setNeed(NEEDS need) {
        this.need = need;
    }
    
    
}
