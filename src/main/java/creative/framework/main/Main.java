package creative.framework.main;

import creative.framework.model.Apparel;
import creative.framework.model.Artifact;
import creative.framework.model.PatternItem;
import creative.framework.model.Type;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	// Global variables
	public static List<String> authorIds;
	public static List<String> categories;
	public static List<String> crafts;
	public static List<String> yarns;
	public static List<String> tags;
	
    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Insufficient number of arguments. \nEnter a colors to shirt, pants and shoes (in that order).");
        } else {
            //List<String> shirtColors = Arrays.asList("BLUE", "GRAY", "LILAC", "NAVY", "WHITE", "blue", "gray", "lilac", "navy", "white");
            //List<String> pantsColors = Arrays.asList("BLACK", "BROWN", "GRAY", "NAVY", "WHITE", "black", "brown", "gray", "navy", "white");
            //List<String> shoesColors = Arrays.asList("BLACK", "BROWN", "GRAY", "NAVY", "WHITE", "black", "brown", "gray", "navy", "white");
        	
        	// Initialize variables
        	authorIds = new ArrayList<String>();
        	categories = new ArrayList<String>();
        	crafts = new ArrayList<String>();
        	yarns = new ArrayList<String>();
        	tags = new ArrayList<String>();
        	List<PatternItem> patternList = new ArrayList<PatternItem>();
        	
        	// Read patterns file
        	patternList = readFile(patternList);
        	
        	// 
            /*String color1 = args[0].toUpperCase();
            String color2 = args[1].toUpperCase();
            String color3 = args[2].toUpperCase();
            if (shirtColors.contains(color1)) {
                if (pantsColors.contains(color2)) {
                    if (shoesColors.contains(color3)) {
                        //apparelExample(color1, color2, color3);
                    } else {
                        System.out.println(color3 + " is not a color to shoes");
                    }
                } else {
                    System.out.println(color2 + " is not a color to pants");
                }
            } else {
                System.out.println(color1 + " is not a color to shirt");
            }*/
        }

    }

    public static List<PatternItem> readFile(List<PatternItem> patternList){
    	String csvFile = "pattern.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

            	String id;
                String authorId;
                List<String> categoriesFromPattern = new ArrayList<>();
                String craft;
                String yarnCompany;
                String published;
                List<String> tagsFromPattern = new ArrayList<>();
            	int i = 0;
            	
                // use comma as separator
                String[] patternData = line.split(cvsSplitBy);

                // Id
                id = patternData[i];
                i++;
                // Name
                i++;
                // Author id
                authorId = patternData[i];
                if(!authorIds.contains(authorId)){
                	authorIds.add(authorId);
                }
                i++;
                // Proj. count
                i++;
                // Diff. count
                i++;
                // Diff.average
                i++;
                // Free
                i++;
                // Price
                i++;
                // Currency
                i++;
                // Comments count
                i++;
                // Favorites count
                i++;
                // Rating average
                i++;
                // Categories
                String categoriesSplitBy = ",";
                String[] categoriesSplited = patternData[i].split(categoriesSplitBy);
                for(int j = 0; j < categoriesSplited.length; j++){
                	String category = categoriesSplited[j].replaceAll("\"", "");
                	category = categoriesSplited[j].replaceAll("[", "");
                	category = categoriesSplited[j].replaceAll("]", "");
                	categoriesFromPattern.add(category);
                	// Check if exists in list
                	if(!categories.contains(category)){
                		categories.add(category);
                	}
                }
                i++;
                // Needle sizes
                i++;
                // Photo
                i++;
                // Craft
                craft = patternData[i];
                if(!crafts.contains(craft)){
                	crafts.add(craft);
                }
                i++;
                // Yarn name
                i++;
                // Yarn Company
                yarnCompany = patternData[i];
                if(!yarns.contains(yarnCompany)){
                	yarns.add(yarnCompany);
                }
                i++;
                // Published
                published = patternData[i];
                i++;
                // Tags
                String tagsSplitBy = ",";
                String[] tagsSplited = patternData[i].split(tagsSplitBy);
                for(int j = 0; j < tagsSplited.length; j++){
                	String tag = tagsSplited[j].replaceAll("\"", "");
                	tag = tagsSplited[j].replaceAll("[", "");
                	tag = tagsSplited[j].replaceAll("]", "");
                	tagsFromPattern .add(tag);
                	
                	if(!tags.contains(tags)){
                		tags.add(tag);
                	}
                }
                i++;
                
                // Add attributes to pattern
                PatternItem pattern = new PatternItem();
                pattern.setAuthorId(Integer.parseInt(authorId));
                pattern.setCategories(categories);
                pattern.setCraft(craft);
                pattern.setId(Integer.parseInt(id));
                pattern.setYarnCompany(yarnCompany);
                pattern.setPublished(published);
                pattern.setTags(tags);
                
                // Add pattern to list
                patternList.add(pattern);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return patternList;
    }
    
    /**
     * Run Apparel Example
     *
     * @param color1 - shirt color
     * @param color2 - pants color
     * @param color3 - shoes color
     */
    public static void apparelExample(
            String authorId,
            List<String> categoriesFromPatern,
            String craft,
            String yarnCompany,
            List<String> tagsFromPattern) {

        // a context which the artifact is evalutated
        ArtifactContext<Apparel> context = new ApparelContext(
                "/datafiles/apparelDataset.json",
                "/datafiles/apparelSynergy.json");
        System.out.println(context.toString());

        // a julde to evaluate an artifact in a context
        ArtifactJudge judge = new ApparelJudge();

        // some clothing items
        /*List<ClothingItem> clothingItem = Arrays.asList(
                new ClothingItem(Type.SHIRT, Color.valueOf(color1)), // a navy shirt
                new ClothingItem(Type.PANTS, Color.valueOf(color2)), // pants white
                new ClothingItem(Type.SHOES, Color.valueOf(color3))); // shoes brown
         */
        
        List<PatternItem> patternItem = new ArrayList<PatternItem>();
        
        // an artifact to be evaluated
        Artifact artifact = new Apparel(patternItem);
        System.out.println(artifact.toString());

        // show the evaluation of an artifact
        System.out.println("Apparel RDC Metric:" + judge.evaluateArtifact(context, artifact));

    }
}
