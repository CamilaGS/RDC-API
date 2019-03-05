/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creative.framework.main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import creative.framework.data.Dataset;
import creative.framework.model.Attribute;
import creative.framework.model.TypeAttribute;
import creative.framework.model.Pattern;
import creative.framework.model.PatternItem;
import creative.framework.novelty.BayesianSurprise;
import creative.framework.novelty.Novelty;
import creative.framework.parser.PatternParser;
import creative.framework.parser.Parser;
import creative.framework.util.Utils;
import creative.framework.value.SynergyValue;
import creative.framework.value.Value;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author creapar team
 *
 */
public class ApparelContext implements ArtifactContext<Pattern> {

    Dataset dataset;
    Parser<Pattern> parser;
    Double lambda;
    Map<Attribute, List<Attribute>> attributeSynergy;
    Utils utils;

    public ApparelContext(String datasetFileDescription, String synergyFileDescription) throws FileNotFoundException, IOException, ParseException {
        this.utils = new Utils();
        this.parser = new PatternParser();
        this.dataset = getApparelDataset(datasetFileDescription);
        this.attributeSynergy = getApparelSynergy(synergyFileDescription);
        this.lambda = 0.1;

    }

    @Override
    public Novelty<Pattern> getNoveltyModel() {
        return new BayesianSurprise(
                dataset.getMeans(),
                dataset.getVariances(),
                lambda,
                parser.attributeCount(),
                parser);
    }

    @Override
    public Value<Pattern> getValueModel() {
        return new SynergyValue(attributeSynergy);
    }

    private Map<Attribute, List<Attribute>> getApparelSynergy(String synergyFileDescription) throws FileNotFoundException, IOException, ParseException {
    	JSONParser parser = new JSONParser();
    	JSONObject obj = (JSONObject) parser.parse(new FileReader(synergyFileDescription));
    	Map<Attribute, List<Attribute>>attributes = new HashMap<Attribute, List<Attribute>>();
    	    	
    	for (Object key : obj.keySet()) {
    		List<Attribute> goodAttributesList = new ArrayList<Attribute>();
    		String mainAttribute = key.toString();
    	    String goodAttributes = obj.get(mainAttribute).toString();
    	    for(String att : goodAttributes.split(",")) {
    	    	att = att.replace("\"", "").replace("[", "").replaceAll("]", "");
    	    	//System.out.println(att);
    	    	if(att.length() > 1)
    	    		goodAttributesList.add(Attribute.valueOf(att));
    	    }
    	    attributes.put(Attribute.valueOf(mainAttribute), goodAttributesList);
    	}
    	
    	return attributes;
//    	
//    	Gson gson = new Gson();
//
//        Type type =  new TypeToken< HashMap<Attribute, List<Attribute>>>() {
//        }.getType();
//        
//        TypeAttribute synergyType = TypeAttribute.valueOf(type.toString());
//        return gson.fromJson(utils.getReader(synergyFileDescription), synergyType);

    }

    private Dataset getApparelDataset(String datasetFileDescription) throws FileNotFoundException, IOException, ParseException {

        //Type artifactType = new TypeToken<List<Pattern>>() {
        //}.getType();

        //Gson gson = new Gson();

        List<Pattern> existingArtifacts = readFile(datasetFileDescription);//gson.fromJson(utils.getReader(datasetFileDescription), artifactType);

        Integer attributeCount = existingArtifacts.size();//parser.attributeCount();

        Dataset dataset = new Dataset(attributeCount);

        for (Pattern apparel : existingArtifacts) {
            dataset.addInstance(parser.getInstance(apparel));
        }
        return dataset;
    }
    
    public static List<Pattern> readFile(String filePath) throws FileNotFoundException, IOException, ParseException{
    	JSONParser parser = new JSONParser();
    	Object obj = parser.parse(new FileReader(filePath));
    	JSONArray a = (JSONArray) obj;
    	List<Pattern>patterns = new ArrayList<Pattern>();
    	
    	for (Object o : a) {
    		List<PatternItem>attributes = new ArrayList<PatternItem>();
    	    JSONObject pattern = (JSONObject) o;

    	    String id = pattern.get("id").toString();
    	    //System.out.println(id);
    	    //attributes.add(id);

    	    JSONArray patternItems = (JSONArray) pattern.get("patternItems");
    	    
    	    JSONObject categoryObj = (JSONObject) patternItems.get(0);    	   
    	    String categories = categoryObj.get("attribute").toString();
    	    String category = categories.split(",")[0].replace("-", "").replace("\"", "").replace("\'", "").replace("/", "").replace(" ", "");
    	    if(Attribute.valueOf(category) != null) {
    	    	attributes.add(new PatternItem(TypeAttribute.category,  Attribute.valueOf(category)));
    	    }
    	    
    	    
    	    JSONObject yarnObj = (JSONObject) patternItems.get(1);    	   
    	    String yarn = yarnObj.get("attribute").toString();
    	    yarn = yarn.replace(" ", "").replace("\"", "");    	    
    	    if(Attribute.valueOf(yarn) != null)
    	    	attributes.add(new PatternItem(TypeAttribute.yarn,  Attribute.valueOf(yarn))); 
    	    
    	    JSONObject tagObj = (JSONObject) patternItems.get(2);    	   
    	    String tags = tagObj.get("attribute").toString();
    	    String tag1 = tags.split(",")[0].replaceAll("/", "").replace(" ", "").replace("\"", "").replace("\'", "").replace("-", "");
    	    String tag2 = tags.split(",")[1].replaceAll("/", "").replace(" ", "").replace("\"", "").replace("\'", "").replace("-", "");
    	    if(Attribute.valueOf(tag1) != null)
    	    	attributes.add(new PatternItem(TypeAttribute.tag,  Attribute.valueOf(tag1)));
    	    if(Attribute.valueOf(tag2) != null)
    	    	attributes.add(new PatternItem(TypeAttribute.tag,  Attribute.valueOf(tag2)));
    	    Pattern p = new Pattern(attributes);
    	    patterns.add(p);
    	}
    	return patterns;
    }

    @Override
    public String toString() {
        StringBuilder context = new StringBuilder();
        context.append(dataset.toString());
        context.append("\nAverages:\n").append(meansToString(dataset.getMeans()));
        context.append("\nVariances:\n").append(variancesToString(dataset.getVariances()));
        return context.toString();
    }

    private StringBuilder meansToString(List<Mean> means) {
        StringBuilder m = new StringBuilder();
        NumberFormat formatter = new DecimalFormat("#0.00");

        for (Mean mean : means) {
            m.append(formatter.format(mean.getResult())).append(" ");
        }
        return m;
    }

    private StringBuilder variancesToString(List<Variance> variances) {
        StringBuilder v = new StringBuilder();
        NumberFormat formatter = new DecimalFormat("#0.00");
        for (Variance variance : variances) {
            v.append(formatter.format(variance.getResult())).append(" ");
        }
        return v;
    }

}
