package creative.framework.model;

import java.util.List;

/**
 *
 * @author cel
 */
public class PatternItem {
    
   TypeAttribute type;
   Attribute attribute;
   
   public PatternItem(TypeAttribute type, Attribute atribute) {
	   super();
	   this.type = type;
	   this.attribute = atribute;
   }

	public TypeAttribute getType() {
		return type;
	}
	
	public void setType(TypeAttribute type) {
		this.type = type;
	}
	
	public Attribute getAtribute() {
		return attribute;
	}
	
	public void setAtribute(Attribute atribute) {
		this.attribute = atribute;
	}
	    
	    
}
