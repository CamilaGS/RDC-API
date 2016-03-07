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
public class Apparel {
    
    List<ClothingItem> clothingItems;

    public Apparel() {
    }

    public Apparel(List<ClothingItem> clothingItems) {
        this.clothingItems = clothingItems;
    }
    
    

    public List<ClothingItem> getClothingItems() {
        return clothingItems;
    }

    public void setClothingItems(List<ClothingItem> clothingItems) {
        this.clothingItems = clothingItems;
    }

    
    
    
    
}
