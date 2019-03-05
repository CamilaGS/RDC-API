package creative.framework.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import creative.framework.model.Artifact;
import creative.framework.model.Attribute;
import creative.framework.model.Pattern;
import creative.framework.model.PatternItem;
import creative.framework.model.TypeAttribute;

public class Main {

    /**
     * Main
     *
     * @param args
     * @throws ParseException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
       
    	String filePath = "C:\\Users\\camil\\git\\RDC-API\\src\\main\\resources\\datafiles\\pattern.json";
    	
    	List<String>attributes = readFile(filePath);
    	
    	test_1_yarn_category_tags(attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4));
    	
    }

    public static List<String> readFile(String filePath) throws FileNotFoundException, IOException, ParseException{
    	JSONParser parser = new JSONParser();
    	Object obj = parser.parse(new FileReader(filePath));
    	JSONArray a = (JSONArray) obj;
    	List<String>attributes = new ArrayList<String>();
    	
    	for (Object o : a) {
    	    JSONObject pattern = (JSONObject) o;

    	    String id = pattern.get("id").toString();
    	    System.out.println(id);
    	    attributes.add(id);

    	    String yarn = (String) pattern.get("yarn_name");
    	    attributes.add(yarn);
    	    
    	    String categories = (String) pattern.get("categories");
    	    String category = categories.split(",")[0];
    	    attributes.add(category);
    	    
    	    String tags = (String) pattern.get("tags");
    	    String tag1 = tags.split(",")[0];
    	    String tag2 = tags.split(",")[1];
    	    attributes.add(tag1);
    	    attributes.add(tag2);
    	}
    	return attributes;
    }
    

    public static void test_1_yarn_category_tags(String arg0, String arg1, String arg2, String arg3) throws FileNotFoundException, IOException, ParseException{
    	
    	
         List<String> yarns = Arrays.asList("AlpacaSilk","CottonBraid","GemsPearl","Footpath","YorkshireTweed4Ply");
         List<String> tags = Arrays.asList("female","adult","male","lace","doolsize","teen","seamed","collar","textured",
         		"topcuffdown","heelflap","unisex","baby","othertoe","stranded","eyelets","writtenpattern","reversible","child","mesh");
         List<String> categories = Arrays.asList("Accessories","Clothing","FeetLegs","ToysandHobbies","Hands","NeckTorso","Bag","Tops","Components",
         		"Socks","Sweater","Hat","Categories");

         String inputYarn = arg0.replace(" ", "").replace("\"", "");
         String inputCategory = arg1.replace("-", " ").replace("\"", "").replace("\'", "");
         String inputTag1 = arg2.replaceAll("/", "").replace(" ", "").replace("\"", "").replace("\'", "");
         String inputTag2 = arg3.replaceAll("/", "").replace(" ", "").replace("\"", "").replace("\'", "");
         
         if (yarns.contains(inputYarn)) {
             if (categories.contains(inputCategory)) {
                 if (tags.contains(inputTag1) && tags.contains(inputTag2)) {
                     apparelExample(inputYarn, inputCategory, inputTag1, inputTag2);
                 } else {
                     System.out.println(inputTag1 + " or " + inputTag2 + " is not a tag");
                 }
             } else {
                 System.out.println(inputCategory + " is not a category");
             }
         } else {
             System.out.println(inputYarn + " is not a yarn");
         }
         
    }
    
    public static void apparelExample(
    		String inputYarn,
            String inputCategory,
            String inputTag1,
            String inputTag2) throws FileNotFoundException, IOException, ParseException {

        // a context which the artifact is evalutated
        ArtifactContext<Pattern> context = new ApparelContext(
                "C:\\Users\\camil\\git\\RDC-API\\src\\main\\resources\\datafiles\\PatternDataset.json",
                "C:\\Users\\camil\\git\\RDC-API\\src\\main\\resources\\datafiles\\PatternSynergy.json");
        System.out.println(context.toString());

        // a julde to evaluate an artifact in a context
        ArtifactJudge judge = new ApparelJudge();

        // some pattern items
        List<PatternItem>patternItem = Arrays.asList(
        		new PatternItem(TypeAttribute.yarn, Attribute.valueOf(inputYarn)),
        		new PatternItem(TypeAttribute.category, Attribute.valueOf(inputCategory)),
        		new PatternItem(TypeAttribute.tag, Attribute.valueOf(inputTag1)),
        		new PatternItem(TypeAttribute.tag, Attribute.valueOf(inputTag2))); 

        // an artifact to be evaluated
        Artifact artifact = new Pattern(patternItem);
        System.out.println(artifact.toString());

        // show the evaluation of an artifact
        System.out.println("Apparel RDC Metric:" + judge.evaluateArtifact(context, artifact));

    }
}
