/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creative.framework.parser;

import creative.framework.data.Instance;
import creative.framework.model.Pattern;
import creative.framework.model.PatternItem;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author cel
 */
public class PatternParser implements Parser<Pattern> {

    private Map<String, Integer> attributes;
    private Integer attributeCount;

    public PatternParser() {
    	
        attributes = new HashMap<>();
        Integer index = 0;
        /*
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
        attributeCount = attributes.size();*/
        attributeCount = 0;
    }

    public void setInstance(Pattern artifact) {
    	// Integer index;
        String attributeName;
        for (PatternItem item : artifact.getPatternItems()) {
        	 attributeName = (item.getType() + ":" + item.getAtribute());        	 
        	 if(!attributes.containsKey(attributeName)) {
        		 //System.out.println(attributeName);
        		 this.attributes.put(attributeName, this.attributes.size());        		 
        		 this.attributeCount++;
        	 }
        }
        
    }
    
	public Instance getInstance(Pattern artifact) {
		// Integer index;
        String attributeName;
        double[] dataInstance = new double[attributeCount+4];
        
        for (PatternItem item : artifact.getPatternItems()) {
        	attributeName = (item.getType() + ":" + item.getAtribute());
        	//System.out.println(attributeName);
        	if(!attributes.containsKey(attributeName)) {
       		 	this.attributes.put(attributeName, this.attributes.size());        		 
       		 	this.attributeCount++;
       	 	}  
        	int index = attributes.get(attributeName);
        	dataInstance[index]++;
        }
        return new Instance(dataInstance);
	}

    /*
    @Override
    public Instance getInstance(Apparel artifact) {
        // Integer index;
        String attributeName;
        double[] dataInstance = new double[attributeCount];

        for (PatternItem item : artifact.getClothingItems()) {
            attributeName = (item.getType() + ":" + item.getColor()).toLowerCase();
            // index = attributes.get(attributeName);
            dataInstance[attributes.get(attributeName)]++;
        }
        return new Instance(dataInstance);
    }*/
    
    public Integer attributeCount(){
        return attributeCount;
    }
     
}
