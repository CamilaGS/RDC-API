package creative.framework.main;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import creative.framework.data.Dataset;
import creative.framework.data.Instance;
import creative.framework.model.Apparel;
import creative.framework.model.Color;
import creative.framework.novelty.BayesianSurprise;
import creative.framework.novelty.Novelty;
import creative.framework.parser.ApparelParser;
import creative.framework.parser.Parser;
import creative.framework.util.Utils;
import creative.framework.value.SynergyValue;
import creative.framework.value.Value;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class Main {

    public static void main(String[] args) {
        apparelTest();
    }

    public static void apparelTest() {
        //Artifact Type
        Type artifactType = new TypeToken<HashMap<Integer, Apparel>>() {
        }.getType();

        //Artifact Type
        Type synergyType = new TypeToken<HashMap<Color, List<Color>>>() {
        }.getType();

        //Gson to manager json file
        Gson gson = new Gson();

        //Read Knowledge Database
        HashMap<Integer, Apparel> artifactSet = gson.fromJson(Utils.getReader("datafiles/knowledge_database2.json"), artifactType);

        //Create Parser
        Parser<Apparel> parser = new ApparelParser();

        //Generate Instances for Dataset (Parser Format)
        ArrayList<Instance> instances = new ArrayList<>();

        for (Entry<Integer, Apparel> entry : artifactSet.entrySet()) {
            instances.add(parser.getInstance(entry.getValue()));
        }

        //Create Dataset
        Dataset dataset = new Dataset(instances);

        //Lines
        System.out.println("Dataset Lines");
        for (Instance i : dataset.getInstances()) {
            System.out.println(i.getAttributes().toString());
        }

        //Averages
        System.out.println("Averages ");
        for (Mean m : dataset.getMeans()) {
            System.out.println(m.getResult());
        }

        //Variances
        System.out.println("Variances");
        for (Variance v : dataset.getVariances()) {
            System.out.println(v.getResult());
        }

        //Read artifact to be evaluated
        HashMap<Integer, Apparel> artifact = gson.fromJson(Utils.getReader("datafiles/artifact2.json"), artifactType);
        Apparel apparel = artifact.get(1);

        //Get Novelty Interface
        Novelty novelty = new BayesianSurprise(dataset.getMeans(), dataset.getVariances(), 0.02, parser.getInstance(apparel).getNumberOfAtributtes(), parser);
        HashMap<Color, List<Color>> colorSynergy = gson.fromJson(Utils.getReader("datafiles/colorSynergy.json"), synergyType);
        Value value = new SynergyValue(colorSynergy);

        System.out.println("Novelty: " + novelty.getNovelty(apparel));
        System.out.println("Value: " + value.getValue(apparel));
    }
}
