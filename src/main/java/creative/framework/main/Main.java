package creative.framework.main;

import creative.framework.model.Apparel;
import creative.framework.model.Artifact;
import creative.framework.model.ClothingItem;
import creative.framework.model.Color;
import creative.framework.model.Type;
import java.util.Arrays;
import java.util.List;

public class Main {

    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Insufficient number of arguments. \nEnter a colors to shirt, pants and shoes (in that order).");
        } else {
            List<String> shirtColors = Arrays.asList("BLUE", "GRAY", "LILAC", "NAVY", "WHITE", "blue", "gray", "lilac", "navy", "white");
            List<String> pantsColors = Arrays.asList("BLACK", "BROWN", "GRAY", "NAVY", "WHITE", "black", "brown", "gray", "navy", "white");
            List<String> shoesColors = Arrays.asList("BLACK", "BROWN", "GRAY", "NAVY", "WHITE", "black", "brown", "gray", "navy", "white");

            String color1 = args[0].toUpperCase();
            String color2 = args[1].toUpperCase();
            String color3 = args[2].toUpperCase();
            if (shirtColors.contains(color1)) {
                if (pantsColors.contains(color2)) {
                    if (shoesColors.contains(color3)) {
                        apparelExample(color1, color2, color3);
                    } else {
                        System.out.println(color3 + " is not a color to shoes");
                    }
                } else {
                    System.out.println(color2 + " is not a color to pants");
                }
            } else {
                System.out.println(color1 + " is not a color to shirt");
            }
        }

    }

    /**
     * Run Apparel Example
     *
     * @param color1 - shirt color
     * @param color2 - pants color
     * @param color3 - shoes color
     */
    public static void apparelExample(
            String color1,
            String color2,
            String color3) {

        // a context which the artifact is evalutated
        ArtifactContext<Apparel> context = new ApparelContext(
                "/datafiles/apparelDataset.json",
                "/datafiles/apparelSynergy.json");
        System.out.println(context.toString());

        // a julde to evaluate an artifact in a context
        ArtifactJudge judge = new ApparelJudge();

        // some clothing items
        List<ClothingItem> clothingItem = Arrays.asList(
                new ClothingItem(Type.SHIRT, Color.valueOf(color1)), // a navy shirt
                new ClothingItem(Type.PANTS, Color.valueOf(color2)), // pants white
                new ClothingItem(Type.SHOES, Color.valueOf(color3))); // shoes brown

        // an artifact to be evaluated
        Artifact artifact = new Apparel(clothingItem);
        System.out.println(artifact.toString());

        // show the evaluation of an artifact
        System.out.println("Apparel RDC Metric:" + judge.evaluateArtifact(context, artifact));

    }
}
