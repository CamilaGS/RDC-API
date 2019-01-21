/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creative.framework.value;

import creative.framework.model.Apparel;
import creative.framework.model.PatternItem;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author creapar team
 */
public class SynergyValue implements Value<Apparel> {

    Map<String, List<String>> colorSynergy;
    Graph<PatternItem, Integer> graph;

    public SynergyValue(Map<String, List<String>> colorSynergy) {
        this.colorSynergy = colorSynergy;
    }
    
    

    @Override
    public Double getValue(Apparel artifact) {
        // create new graph
        graph = new SparseMultigraph<>();
        // add vertex
        addVertex(artifact);
        // add edges
        addEdges(artifact);

        return 0.5*(kc()+p());
    }

    /**
     *
     * @param graph
     * @param artifact
     */
    private void addVertex(Apparel artifact) {
        for (PatternItem item : artifact.getClothingItems()) {
            graph.addVertex(item);
        }
    }

    private void addEdges(Apparel artifact) {
        Integer edges = 0;
        String uColor, vColor;

        for (PatternItem u : artifact.getClothingItems()) {
            uColor = u.getColor();
            for (PatternItem v : artifact.getClothingItems()) {
                vColor = v.getColor();
                if (isSynergic(uColor, vColor)) {
                    graph.addEdge(edges++, u, v);
                }

            }

        }
    }

    private Double kc() {
        DijkstraShortestPath<PatternItem, Integer> djk = new DijkstraShortestPath(graph);
        Collection<PatternItem> vertices = graph.getVertices();
        int vertexCount = graph.getVertexCount();
        int paths = 0;
        for (PatternItem v1 : vertices) {
            for (PatternItem v2 : vertices) {
                if (!v1.equals(v2)) {
                    if (djk.getDistance(v1, v2) != null) {
                        paths++;
                    }
                }
            }
        }
        return (paths) / (1.0 * vertexCount * (vertexCount - 1));
    }

    private Double p() {
        int e = graph.getEdgeCount();
        int n = graph.getVertexCount();
        return 1.0 * e / (n * (n - 1));
    }

    private boolean isSynergic(String uColor, String vColor) {
        return colorSynergy.get(vColor).contains(uColor);
    }

}
