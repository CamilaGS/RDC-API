/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creative.framework.parser;

import creative.framework.data.Instance;
import creative.framework.model.Apparel;
import creative.framework.model.ClothingItem;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cel
 */
public class ApparelParser implements Parser<Apparel> {

    Map<String, Integer> attributes;

    public ApparelParser() {
        attributes = new HashMap<>();
        Integer index = 0;
        attributes.put("shirt:white", index++);
        attributes.put("shirt:navy", index++);
        attributes.put("shirt:gray", index++);
        attributes.put("shirt:blue", index++);
        attributes.put("shirt:lilac", index++);
       attributes.put("pants:white", index++);
        attributes.put("pants:black", index++);
        attributes.put("pants:navy", index++);
        attributes.put("pants:gray", index++);
        attributes.put("pants:brown", index++);
        attributes.put("shoes:white", index++);
        attributes.put("shoes:black", index++);
        attributes.put("shoes:navy", index++);
        attributes.put("shoes:gray", index++);
        attributes.put("shoes:brown", index++);
        
    }

    @Override
    public Instance getInstance(Apparel artifact) {
        Integer index;
        double[] dataInstance = new double[attributes.size()];

        for (ClothingItem item : artifact.getClothingItems()) {
            String attributeName = item.getType() + ":" + item.getColor();
            String toLowerCase = attributeName.toLowerCase();
           
            index = attributes.get(toLowerCase);
            dataInstance[index]++;
        }
        return new Instance(dataInstance);
    }

}
