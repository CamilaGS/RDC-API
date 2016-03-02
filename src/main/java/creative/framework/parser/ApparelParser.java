package creative.framework.parser;

import java.util.HashMap;

import creative.framework.data.Instance;
import creative.framework.model.Apparel;

public class ApparelParser implements Parser<Apparel> {

	final String COLOR_WHITE = "white";
	final String COLOR_BLACK = "black";
	final String COLOR_NAVY = "navy";
	final String COLOR_GRAY = "gray";
	final String COLOR_BLUE = "blue";
	final String COLOR_BROWN = "brown";
	final String COLOR_LILAC = "lilac";

	HashMap<String, Integer> attributes;

	public ApparelParser() {

		int index = 0;
		attributes = new HashMap<>();

		attributes.put(COLOR_WHITE, index++);
		attributes.put(COLOR_BLACK, index++);
		attributes.put(COLOR_NAVY, index++);
		attributes.put(COLOR_GRAY, index++);
		attributes.put(COLOR_BLUE, index++);
		attributes.put(COLOR_BROWN, index++);
		attributes.put(COLOR_LILAC, index);
	}

	@Override
	public Instance getInstance(Apparel apparel) {

		double[] dataInstance = new double[attributes.size()];

		switch (apparel.getPants()) {

		case COLOR_WHITE:
			dataInstance[attributes.get(COLOR_WHITE)]++;
			break;
		case COLOR_BLACK:
			dataInstance[attributes.get(COLOR_BLACK)]++;
			break;
		case COLOR_NAVY:
			dataInstance[attributes.get(COLOR_NAVY)]++;
			break;
		case COLOR_GRAY:
			dataInstance[attributes.get(COLOR_GRAY)]++;
			break;
		case COLOR_BLUE:
			dataInstance[attributes.get(COLOR_BLUE)]++;
			break;
		case COLOR_BROWN:
			dataInstance[attributes.get(COLOR_BROWN)]++;
			break;
		case COLOR_LILAC:
			dataInstance[attributes.get(COLOR_LILAC)]++;
			break;
		}

		switch (apparel.getShirt()) {

		case COLOR_WHITE:
			dataInstance[attributes.get(COLOR_WHITE)]++;
			break;
		case COLOR_BLACK:
			dataInstance[attributes.get(COLOR_BLACK)]++;
			break;
		case COLOR_NAVY:
			dataInstance[attributes.get(COLOR_NAVY)]++;
			break;
		case COLOR_GRAY:
			dataInstance[attributes.get(COLOR_GRAY)]++;
			break;
		case COLOR_BLUE:
			dataInstance[attributes.get(COLOR_BLUE)]++;
			break;
		case COLOR_BROWN:
			dataInstance[attributes.get(COLOR_BROWN)]++;
			break;
		case COLOR_LILAC:
			dataInstance[attributes.get(COLOR_LILAC)]++;
			break;
		}

		switch (apparel.getShoes()) {

		case COLOR_WHITE:
			dataInstance[attributes.get(COLOR_WHITE)]++;
			break;
		case COLOR_BLACK:
			dataInstance[attributes.get(COLOR_BLACK)]++;
			break;
		case COLOR_NAVY:
			dataInstance[attributes.get(COLOR_NAVY)]++;
			break;
		case COLOR_GRAY:
			dataInstance[attributes.get(COLOR_GRAY)]++;
			break;
		case COLOR_BLUE:
			dataInstance[attributes.get(COLOR_BLUE)]++;
			break;
		case COLOR_BROWN:
			dataInstance[attributes.get(COLOR_BROWN)]++;
			break;
		case COLOR_LILAC:
			dataInstance[attributes.get(COLOR_LILAC)]++;
			break;
		}

		return new Instance(dataInstance);
	}

}
