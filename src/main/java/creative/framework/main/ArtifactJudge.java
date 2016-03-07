/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creative.framework.main;

import creative.framework.model.Color;
import creative.framework.novelty.Novelty;
import creative.framework.util.Utils;
import creative.framework.value.Value;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author cel
 * @param <T>
 */
public class ArtifactJudge<T> {

    Value<T> v;
    Novelty<T> n;

    public ArtifactJudge(Value<T> v, Novelty<T> n) {
        this.v = v;
        this.n = n;
    }

    public Double evaluateArtifact(T artifact) {
        Double v_a = v.getValue(artifact);
        Double n_a = n.getNovelty(artifact);

        return v_a + n_a - p(v_a, n_a);

    }

    private double p(Double v_a, Double n_a) {
        Double s = v_a + n_a;
        Double d = v_a - n_a;
        return s * (1 + Math.exp(-0.9 * d));
    }

}
