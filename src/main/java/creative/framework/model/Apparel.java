/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creative.framework.model;

import java.util.List;

/**
 *
 * @author cel
 */
public class Apparel extends Artifact {

    List<PatternItem> patternItems;

    public Apparel() {
    }

    public Apparel(List<PatternItem> patternItems) {
        this.patternItems = patternItems;
    }

    public List<PatternItem> getClothingItems() {
        return patternItems;
    }

    public void setClothingItems(List<PatternItem> patternItems) {
        this.patternItems = patternItems;
    }

    /*@Override
    public String toString() {
        StringBuilder apparel = new StringBuilder();
        apparel.append("\nApparel Items:\n");
        for (CItem item : clothingItems) {
            apparel.append(item.getType()).append(":").append(item.getColor()).append("\n");
        }
        return apparel.toString();
    }*/

}
