package creative.framework.value;

import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Celso
 */
public class SynergyValue implements Value<List<Integer>> {

    Map<Integer, List<Integer>> synergyList;

    public SynergyValue(Map<Integer, List<Integer>> synergyList) {
        this.synergyList = synergyList;
    }

    @Override
    public Double getEfficiency(List<Integer> artifact) {
        // create new graph
        Graph<Integer, Integer> graph = new SparseMultigraph<>();
        // add vertex
        addVertex(graph, artifact);
        // add edges
        addEdges(graph, artifact);
        
        Double c = conectedness(graph);
        Double a = degreeAverage(graph);
        
        return (c+a - Math.abs(c-a))/2;
    }
    
       private double penalty(Double c, Double a) {
        double maxPenalty= c+a;
        Double penalty = Math.sqrt(Math.abs(c - a));
        if (penalty > maxPenalty) {
            penalty = maxPenalty;
        }
        return penalty;
    }

    private void addVertex(Graph<Integer, Integer> graph, List<Integer> artifact) {
        artifact.stream().forEach((cardId) -> {
            graph.addVertex(cardId);
        });
    }

    private void addEdges(Graph<Integer, Integer> graph, List<Integer> artifact) {
        Integer edges = 0;
        for (Integer vertex : artifact) {
            for (Integer synergicVerte : synergyList.get(vertex)) {
                if (graph.containsVertex(synergicVerte)) {
                    graph.addEdge(edges++, vertex, synergicVerte);
                }
            }

        }
    }

    private Double conectedness(Graph<Integer, Integer> graph) {
        DijkstraShortestPath<Integer, Integer> djk = new DijkstraShortestPath(graph);
        Collection<Integer> vertices = graph.getVertices();
        int vertexCount = graph.getVertexCount();
        int paths = 0;
        for (Integer v1 : vertices) {
            for (Integer v2 : vertices) {
                if (v1 != v2) {
                    if (djk.getDistance(v1, v2) != null) {
                        paths++;
                    }
                }
            }
        }
        return (paths) / (1.0 * vertexCount * (vertexCount - 1));
    }

    private Double degreeAverage(Graph<Integer, Integer> graph) {
        Integer totalDegree = 0;
        for (Integer v : graph.getVertices()) {
            totalDegree += graph.degree(v);
        }
        Double degreeAverage = totalDegree / (1.0 * graph.getVertexCount());
        return (1 - Math.exp(-0.1 * degreeAverage));
    }

    private Double minDegree(Graph<Integer, Integer> graph) {
        Integer minDegree = Integer.MAX_VALUE;
        Integer degree;
        for (Integer v : graph.getVertices()) {
            degree = graph.degree(v);
            if (degree < minDegree) {
                minDegree = degree;
            }
        }
        return (1 - Math.exp(-0.1 * minDegree));
    }

    @Override
    public Double getValue(List<Integer> artifact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
