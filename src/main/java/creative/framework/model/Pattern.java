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
public class Pattern extends Artifact {

    List<PatternItem> patternItems;

    public Pattern() {
    }

    public Pattern(List<PatternItem> patternItems) {
        this.patternItems = patternItems;
    }

    public List<PatternItem> getPatternItems() {
        return patternItems;
    }

    public void setPatternItems(List<PatternItem> patternItems) {
        this.patternItems = patternItems;
    }

    @Override
    public String toString() {
        StringBuilder apparel = new StringBuilder();
        apparel.append("\nApparel Items:\n");
        for (PatternItem item : patternItems) {
            apparel.append(item.getType()).append(":").append(item.getAtribute()).append("\n");
        }
        return apparel.toString();
    }

}
