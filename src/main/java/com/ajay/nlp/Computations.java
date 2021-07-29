package com.ajay.nlp;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.nio.file.*;;
import java.util.List;
import java.io.*;

public class Computations {
    public static void main(String[] args) throws  Exception{

        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();;
        String data = new String(Files.readAllBytes(Paths.get("C:\\NLProject\\Document.txt")));
        CoreDocument coreDocument = new CoreDocument(data);
        stanfordCoreNLP.annotate(coreDocument);

        PrintStream o = new PrintStream(new File("C:\\NLProject\\Output.txt"));
        PrintStream console = System.out;
        System.setOut(o);
        //No of Sentences Code Block
        List<CoreSentence> sentences = coreDocument.sentences();
        int noOfSentences=0;
        System.out.println("The Sentences in File are:");
        for(CoreSentence sentence : sentences) {
            System.out.println(sentence.toString());
            noOfSentences++;
        }
        System.out.println("\nThe Number of Sentences is File are: "+noOfSentences);
        //Number of Words Code Block
        System.out.println("\nThe words in File are:");
        int countNoOfWords=0;
        List<CoreLabel> coreLabelList= coreDocument.tokens();
        for (CoreLabel coreLabel: coreLabelList){
            System.out.println(coreLabel.originalText());
            countNoOfWords++;
        }
        System.out.println("\nThe number of words in File are: "+countNoOfWords);
        //Number of Nouns in File Code Block
        System.out.println("\nThe nouns in file are:");
        int countNoOfNouns=0;
        for(CoreLabel coreLabel : coreLabelList) {
            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            if(pos.charAt(0)=='N' && pos.charAt(1)=='N'){
                System.out.println(coreLabel.originalText() + " = "+ pos);
                countNoOfNouns++;
            }
        }
        System.out.println("\nThe number of nouns in File are: "+countNoOfNouns);
        //Sentiments code block
        System.out.println("\nThe sentiments of sentences in File are:");
        for(CoreSentence sentence : sentences) {

            String sentiment = sentence.sentiment();
            System.out.println(sentence + ":\t" + sentiment);
        }
    }
}
