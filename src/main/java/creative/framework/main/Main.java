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
       
    	// Test_user_18
    	String filePath = "C:\\Users\\camil\\git\\RDC-API\\src\\main\\resources\\datafiles\\User_18_pattern.json";
    	String datasetPath = "C:\\Users\\camil\\git\\RDC-API\\src\\main\\resources\\datafiles\\PatternDataset_user_18.json";
    	String synergyPath = "C:\\Users\\camil\\git\\RDC-API\\src\\main\\resources\\datafiles\\PatternSynergy_user_18.json";
    	List<List<String>>attributes = readFile(filePath);
    	
    	for (List<String> list : attributes) {
    		test_user_18(list.get(1), list.get(2), list.get(3), list.get(4), datasetPath, synergyPath);
    		System.out.println();
		}
    	
    	// Test_1
//    	String filePath = "C:\\Users\\camil\\git\\RDC-API\\src\\main\\resources\\datafiles\\pattern.json";
//    	
//    	List<String>attributes = readFile(filePath);
//    	
//    	test_1_yarn_category_tags(attributes.get(1), attributes.get(2), attributes.get(3), attributes.get(4));
//    	
    }

    public static List<List<String>> readFile(String filePath) throws FileNotFoundException, IOException, ParseException{
    	JSONParser parser = new JSONParser();
    	Object obj = parser.parse(new FileReader(filePath));
    	JSONArray a = (JSONArray) obj;
    	List<List<String>>patternsAttributes = new ArrayList<List<String>>();
    	
    	for (Object o : a) {
    		List<String>attributes = new ArrayList<String>();
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
    	    
    	    patternsAttributes.add(attributes);
    	}
    	return patternsAttributes;
    }
    

    public static void test_1_yarn_category_tags(String arg0, String arg1, String arg2, String arg3, String path1, String path2) throws FileNotFoundException, IOException, ParseException{
    	
    	
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
                     apparelExample(inputYarn, inputCategory, inputTag1, inputTag2, path1, path2);
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
    
    public static void test_user_18(String arg0, String arg1, String arg2, String arg3, String path1, String path2) throws FileNotFoundException, IOException, ParseException{
    	List<String> yarns = Arrays.asList("AlpacaSilk","CottonBraid","GemsPearl","Footpath","YorkshireTweed4Ply","BabyMerinoDK",
       		 "RYCSilkWoolDK","RYCSoftTweed","RYCCashcotton4Ply","RYCBabyAlpacaDK","RYCCashcottonDK","RYCNaturalSilkAran","CottonRope","RYCBambooSoft",
       		 "CottonGlace","Cork","Calmer","4PlyCotton");
        List<String> tags = Arrays.asList("female","adult","male","lace","doolsize","teen","seamed","collar","textured",
        		"topcuffdown","heelflap","unisex","baby","othertoe","stranded","eyelets","writtenpattern","reversible","child","mesh","newbornsize");
        List<String> categories = Arrays.asList("Accessories","Clothing","FeetLegs","ToysandHobbies","Hands","NeckTorso","Bag","Tops","Components",
        		"Socks","Sweater","Hat","Categories","Cozy","Home","Softies","Blanket");

        String inputYarn = arg0.replace(" ", "").replace("\"", "").replace("-", "");
        String inputCategory = arg1.replace("-", "").replace("\"", "").replace("\'", "");
        String inputTag1 = arg2.replaceAll("/", "").replace(" ", "").replace("\"", "").replace("\'", "");
        String inputTag2 = arg3.replaceAll("/", "").replace(" ", "").replace("\"", "").replace("\'", "");
        
        if (yarns.contains(inputYarn)) {
            if (categories.contains(inputCategory)) {
                if (tags.contains(inputTag1) && tags.contains(inputTag2)) {
                    apparelExample(inputYarn, inputCategory, inputTag1, inputTag2, path1, path2);
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
            String inputTag2,
            String datasetPath,
            String synergyPath) throws FileNotFoundException, IOException, ParseException {

        // a context which the artifact is evalutated
        ArtifactContext<Pattern> context = new ApparelContext(
        		datasetPath,
        		synergyPath);
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
