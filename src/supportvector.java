import edu.stanford.nlp.classify.*;
import edu.stanford.nlp.ling.BasicDatum;
import edu.stanford.nlp.ling.Datum;
import edu.stanford.nlp.objectbank.ObjectBank;

import java.util.*;
import java.io.*;

/**
 * Support Vector Machine Implementation for i2b2 Smoking Data.
 *
 * NOTE: This class is not complete, it needs to install some binaries for SVM to work - and I can't figure it out.
 *
 * @author MichaelK
 * @date 6/20/17
 */


public class supportvector {
    public static void main(String[] args) {
        ColumnDataClassifier cdc = new ColumnDataClassifier("maxent.prop");
        //property file used for feature generation from record
        SVMLightClassifierFactory<String, String> factory = new SVMLightClassifierFactory();
        //initialize SVMfactory
        //make training Dataset<String, String>
        GeneralDataset<String, String> train = cdc.readTrainingExamples("smoker-train-stanford-classifier.txt");
        // Supposedly in order for an SVM to be trained under the Stanford Classifier the svmlight executables must be downloaded:
        // http://svmlight.joachims.org/
        // 
        SVMLightClassifier<String, String> cl = factory.trainClassifier(train);

    }

}
