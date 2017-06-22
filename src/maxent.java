import edu.stanford.nlp.classify.Classifier;
import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.classify.GeneralDataset;

/**
 * Maximum Entropy Classifier for i2b2 smoking data
 *
 * @author MichaelK
 * @date 06/17/17
 */

public class maxent{

    public static void main(String[] args) throws Exception {
        ColumnDataClassifier cdc = new ColumnDataClassifier("maxent.prop");
        // load in properties file
        Classifier<String,String> cl =
                cdc.makeClassifier(cdc.readTrainingExamples("smoker-train-stanford-classifier.txt"));
        // create classifier
        GeneralDataset<String, String> data = cdc.readTestExamples("smoker-test-stanford-classifier.txt").first();
        //create local var to cut down on read time
        System.out.println(cl.evaluateAccuracy(data));
        //Somehow this is 1.0 as well
        for (String label : cl.labels()) {
            System.out.println(label + ": " + cl.evaluatePrecisionAndRecall(data, label));
            // precision and recall is always 1, what is wrong here?
        }
    }



}
