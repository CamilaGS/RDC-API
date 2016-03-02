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
import creative.framework.parser.ApparelParser;
import creative.framework.parser.Parser;
import creative.framework.util.Utils;

public class Main {

	public static void main(String[] args) {

		//Artifact Type
		Type artifactType = new TypeToken<HashMap<Integer,Apparel>>(){}.getType();

		//Gson to manager json file
		Gson gson = new Gson();

		//Read Knowledge Database
		HashMap<Integer,Apparel> artifactSet = gson.fromJson(Utils.getReader("datafiles/knowledge_database.json"), artifactType);

		//Create Parser
		Parser<Apparel> parser = new ApparelParser();
		
		//Generate Instances for Dataset (Parser Format)
		ArrayList<Instance> instances = new ArrayList<Instance>();
		for( Entry<Integer, Apparel> entry : artifactSet.entrySet()) {
			instances.add(parser.getInstance(entry.getValue()));
		}
		
		//Create Dataset
		Dataset dataset = new Dataset(instances);
		
                //Lines
                System.out.println("Dataset Lines");
		for(Instance i: dataset.getInstances())
                    System.out.println(i.getAttributes().toString());
                
		//Averages
		System.out.println("Averages " + dataset.getAverages().toString());
		
		//Variances
		System.out.println("Variances " + dataset.getVariances(dataset.getAverages()).toString());
	}
}
