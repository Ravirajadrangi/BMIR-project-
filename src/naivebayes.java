import edu.stanford.nlp.classify.*;


/**
 * Maximum Entropy Classifier for i2b2 smoking data
 *
 * @author MichaelK
 * @date 06/17/17
 */

public class naivebayes{

    public static void main(String[] args) throws Exception {
        ColumnDataClassifier cdc = new ColumnDataClassifier("naivebayes.prop");
        // load in properties file
        // the line "useNB=true" somehow converts the classifier in the next line into a NaiveBayesClassifier?
        NaiveBayesClassifier<String,String> cl =
                (NaiveBayesClassifier) cdc.makeClassifier(cdc.readTrainingExamples("smoker-train-stanford-classifier.txt"));
        // create classifier (cast to nbclassifier)1
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
