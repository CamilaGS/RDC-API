## Regent-Dependent Creativity API

In order to complement the study cases in this article, an API for evaluating artifacts was developed. Only two inputs are necessary for evaluating an artifact: a knowledge database that contains existing artifacts of the particular application domain, and a set of relations that represent the synergy among the artifacts’ attributes. The knowledge database must contain artifacts encoded in a specific format, such as JSON. In the first example, where garments are combined to form an apparel, the knowledge database has the following format:

```
[ {
"clothingItems": [{
"type": "SHIRT",
"color": "LILAC"
}, {
"type": "PANTS",
"color": "WHITE"
}, {
"type": "SHOES",
"color": "GRAY"
}]
}, ...
```

A specialized parser is responsible for converting the encoded knowledge database into a collection of instances of artifact objects. The decoded collection of artifact objects is depicted bellow:

```
{
"1":[0,0,0,0,1,1,0,0,0,0,0,0,0,1,0],
"2":[1,0,0,0,0,0,0,1,0,0,1,0,0,0,0],
...
"9":[0,0,0,0,1,0,1,0,0,0,0,0,0,1,0]
}
```

Relations representing the synergy of the artifacts are structured as a map between each attribute and its respective synergistic attributes. These relations are illustrated in figure 3, describing the synergy among the colors and their clothing items. The API supports the synergistic relations to be represented as follows:

```
1
Source-code for the Regent Dependent Creativity API:
https://github.com/CreaPar/rd-creativity-metric-api
Figure 7: Class diagram
{
"WHITE":["NAVY", "BLACK", "BLUE", "GRAY", "LILAC", "BROWN"],
"BLACK":["NAVY", "BROWN", "WHITE", "BLUE", "LILAC", "GRAY"],
"NAVY":["GRAY", "BLACK", "WHITE", "BLUE", "BROWN"],
"BLUE":["NAVY", "BLACK", "WHITE", "GRAY", "BROWN"],
"GRAY":["NAVY", "BLACK", "WHITE", "BLUE", "LILAC"],
"BROWN":["NAVY", "BLACK", "WHITE", "BLUE"],
"LILAC":["BLACK", "WHITE", "GRAY"]
}
```

When the parser loads the knowledge database, it computes the mean and variance of each attribute among all loaded artifacts. These information is useful for the calculation of RDC metric. The two main classes responsible for the Regent-Dependent creativity metric are: the SynergyValue class, responsible for calculating the value metric, in which the method getValue (T artifact) will return the synergistic value of the artifact given as parameter; and the BayesianSurprise class, responsible for calculating the novelty metric, by using the method getNovelty (artifact T). With a measure of novelty and value, the evaluateArtifact() method in ArtifactJudge class, judges how creative is an artifact.

## How to run the RDC API:

1. Download jar file;
2. Open cmd prompt (windows) or shell (linux);
3. Run command **java -jar rdcAPI.jar \<shirt color\> \<pants color\> \<shoes color\>**;
  
```  
Possible values:
	
<shirt color>: "BLUE", "GRAY", "LILAC", "NAVY", "WHITE"
<pants color>: "BLACK", "BROWN", "GRAY", "NAVY", "WHITE"
<shoes color>: "BLACK", "BROWN", "GRAY", "NAVY", "WHITE"
```
