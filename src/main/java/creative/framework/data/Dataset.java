package creative.framework.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author creapar team
 *
 */
public class Dataset {

	private ArrayList<Instance> instances;
	private Integer inputAttributes;

	public Dataset(ArrayList<Instance> instances){
		this.instances = instances;
		this.inputAttributes = instances.get(0).getNumberOfAtributtes();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Instance> getInstances() {
		return instances;
	}

	/**
	 * 
	 * @param instances
	 */
	public void setInstances(ArrayList<Instance> instances) {
		this.instances = instances;
	}
	
    /**
     * Return a list of average for each attribute
     *
     * @return
     */
	public List<Double> getAverages() {

		ArrayList<Double> averages = new ArrayList<>();
		List<Double> instanceData;
		double[] sum = new double[inputAttributes];

		for (Instance instance : instances) {
			instanceData = instance.getData();
			for (int i = 0; i < inputAttributes; i++) {
				sum[i] += instanceData.get(i);
			}
		}

		for (int i = 0; i < inputAttributes; i++) {
			averages.add(sum[i] / instances.size());
		}

		return averages;
	}
	
    /**
     * Return a list of variance for each attribute
     *
     * @param averages
     * @return
     */
    public List<Double> getVariances(List<Double> averages) {
    	
        ArrayList<Double> variances = new ArrayList<>();
        List<Double> instanceData;
        double[] sum = new double[inputAttributes];

        for (Instance instance : instances) {
            instanceData = instance.getData();
            for (int i = 0; i < inputAttributes; i++) {
                sum[i] += Math.pow(instanceData.get(i) - averages.get(i), 2);
            }
        }
        
        for (int i = 0; i < inputAttributes; i++) {
            variances.add(sum[i] / (instances.size() - 1));
        }
        
        return variances;
    }
}
