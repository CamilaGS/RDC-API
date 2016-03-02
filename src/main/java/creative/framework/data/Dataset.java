package creative.framework.data;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

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
//	
//    /**
//     * Return a list of average for each attribute
//     *
//     * @return
//     */
//	public List<Double> getAverages() {
//
//		ArrayList<Double> averages = new ArrayList<>();
//		List<Double> instanceData;
//		double[] sum = new double[inputAttributes];
//
//		for (Instance instance : instances) {
//			instanceData = instance.getData();
//			for (int i = 0; i < inputAttributes; i++) {
//				sum[i] += instanceData.get(i);
//			}
//		}
//
//		for (int i = 0; i < inputAttributes; i++) {
//			averages.add(sum[i] / instances.size());
//		}
//
//		return averages;
//	}
//	
//    /**
//     * Return a list of variance for each attribute
//     *
//     * @param averages
//     * @return
//     */
//    public List<Double> getVariances(List<Double> averages) {
//    	
//        ArrayList<Double> variances = new ArrayList<>();
//        List<Double> instanceData;
//        double[] sum = new double[inputAttributes];
//
//        for (Instance instance : instances) {
//            instanceData = instance.getData();
//            for (int i = 0; i < inputAttributes; i++) {
//                sum[i] += Math.pow(instanceData.get(i) - averages.get(i), 2);
//            }
//        }
//        
//        for (int i = 0; i < inputAttributes; i++) {
//            variances.add(sum[i] / (instances.size() - 1));
//        }
//        
//        return variances;
//    }
    
        /**
     * Return a list of means for each attribute
     *
     * @return
     */
    public List<Mean> getMeans() {
        List<Mean> means = new ArrayList<>();
        
        for (int i = 0; i < inputAttributes; i++) {
            means.add(new Mean());
        }

        List<Double> instanceData;
        for (Instance instance : instances) {
            instanceData = instance.getData();
            for (int i = 0; i < inputAttributes; i++) {
                means.get(i).increment(instanceData.get(i));
            }
        }
        
        return means;
    }

    /**
     * Return a list of variance for each attribute
     *
     * @return
     */
    public List<Variance> getVariances() {
        ArrayList<Variance> variances = new ArrayList<>();
        List<Double> instanceData;

        for (int i = 0; i < inputAttributes; i++) {
            variances.add(new Variance());
        }

        for (Instance instance : instances) {
            instanceData = instance.getData();
            for (int i = 0; i < inputAttributes; i++) {
                variances.get(i).increment(instanceData.get(i));
            }
        }
        
        return variances;
    }
}
