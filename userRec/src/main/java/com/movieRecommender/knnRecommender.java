package com.movieRecommender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.io.IOException;

import org.apache.commons.cli2.OptionException; 
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.knn.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.similarity.*;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;


import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.recommender.Recommender;

public class knnRecommender {
    
    public static double knnRec(String files) throws FileNotFoundException, TasteException, IOException, OptionException {
               
        File ratingsFile = new File(files);                        
        DataModel model = new FileDataModel(ratingsFile);

		final ItemSimilarity similarity = new LogLikelihoodSimilarity(model);
		final Optimizer optimizer = new NonNegativeQuadraticOptimizer();

        
        
        RecommenderBuilder builder = new RecommenderBuilder() {
			public Recommender buildRecommender(DataModel model) throws TasteException{
				return new CachingRecommender(new KnnItemBasedRecommender(model, similarity, optimizer, 2));
			}
		};

        RecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
        double score = evaluator.evaluate(builder,
				null,
				model,
				0.9,
				1);
 
		System.out.println(score);
               return score;  

        }        	
}