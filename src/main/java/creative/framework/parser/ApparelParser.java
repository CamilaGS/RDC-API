package creative.framework.parser;

import java.util.HashMap;

import creative.framework.data.Instance;
import creative.framework.model.Apparel;

public class ApparelParser implements Parser<Apparel> {

    final String SHIRT_WHITE = "shirt:white";
    final String SHIRT_NAVY  = "shirt:navy";
    final String SHIRT_GRAY  = "shirt:gray";
    final String SHIRT_BLUE  = "shirt:blue";
    final String SHIRT_LILAC = "shirt:lilac";
    final String PANTS_WHITE = "pants:white";
    final String PANTS_BLACK = "pants:black";
    final String PANTS_NAVY  = "pants:navy";
    final String PANTS_GRAY  = "pants:gray";
    final String PANTS_BROWN = "pant:brown";
    final String SHOES_WHITE = "shoes:white";
    final String SHOES_BLACK = "shoes:black";
    final String SHOES_NAVY  = "shoes:navy";
    final String SHOES_GRAY  = "shoes:gray";
    final String SHOES_BROWN = "shoes:brown";

    final String COLOR_WHITE = "white";
    final String COLOR_BLACK = "black";
    final String COLOR_NAVY  = "navy";
    final String COLOR_GRAY  = "gray";
    final String COLOR_BLUE  = "blue";
    final String COLOR_BROWN = "brown";
    final String COLOR_LILAC = "lilac";

    HashMap<String, Integer> attributes;

    public ApparelParser() {

        int index = 0;
        attributes = new HashMap<>();

        attributes.put(SHIRT_WHITE, index++);
        attributes.put(SHIRT_NAVY, index++);
        attributes.put(SHIRT_GRAY, index++);
        attributes.put(SHIRT_BLUE, index++);
        attributes.put(SHIRT_LILAC, index++);
        attributes.put(PANTS_WHITE, index++);
        attributes.put(PANTS_BLACK, index++);
        attributes.put(PANTS_NAVY, index++);
        attributes.put(PANTS_GRAY, index++);
        attributes.put(PANTS_BROWN, index++);
        attributes.put(SHOES_WHITE, index++);
        attributes.put(SHOES_BLACK, index++);
        attributes.put(SHOES_NAVY, index++);
        attributes.put(SHOES_GRAY, index++);
        attributes.put(SHOES_BROWN, index++);

    }

    @Override
    public Instance getInstance(Apparel apparel) {

        double[] dataInstance = new double[attributes.size()];

        switch (apparel.getPants()) {

            case COLOR_WHITE:
                dataInstance[attributes.get(PANTS_WHITE)]++;
                break;
            case COLOR_BLACK:
                dataInstance[attributes.get(PANTS_BLACK)]++;
                break;
            case COLOR_NAVY:
                dataInstance[attributes.get(PANTS_NAVY)]++;
                break;
            case COLOR_GRAY:
                dataInstance[attributes.get(PANTS_GRAY)]++;
                break;
            case COLOR_BROWN:
                dataInstance[attributes.get(PANTS_BROWN)]++;
                break;
        }

        switch (apparel.getShirt()) {

            case COLOR_WHITE:
                dataInstance[attributes.get(SHIRT_WHITE)]++;
                break;
            case COLOR_NAVY:
                dataInstance[attributes.get(SHIRT_NAVY)]++;
                break;
            case COLOR_GRAY:
                dataInstance[attributes.get(SHIRT_GRAY)]++;
                break;
            case COLOR_BLUE:
                dataInstance[attributes.get(SHIRT_BLUE)]++;
                break;
            case COLOR_LILAC:
                dataInstance[attributes.get(SHIRT_LILAC)]++;
                break;
        }

        switch (apparel.getShoes()) {

            case COLOR_WHITE:
                dataInstance[attributes.get(SHOES_WHITE)]++;
                break;
            case COLOR_BLACK:
                dataInstance[attributes.get(SHOES_BLACK)]++;
                break;
            case COLOR_NAVY:
                dataInstance[attributes.get(SHOES_NAVY)]++;
                break;
            case COLOR_GRAY:
                dataInstance[attributes.get(SHOES_GRAY)]++;
                break;
            case COLOR_BROWN:
                dataInstance[attributes.get(SHOES_BROWN)]++;
                break;
        }

        return new Instance(dataInstance);
    }

}
