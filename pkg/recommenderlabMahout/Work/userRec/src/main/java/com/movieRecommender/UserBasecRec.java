package com.movieRecommender;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.io.IOException;
import java.lang.String;

import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.commons.cli2.OptionException; 
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.commons.cli2.OptionException; 
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.slopeone.SlopeOneRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;



class UserBasecRec {

public static String userbasecrec(String files) throws FileNotFoundException, TasteException, IOException, OptionException {

//DataModel model = new FileDataModel(new File("datasets/movieRatings.dat")); 

File ratingsFile = new File(files);                        
        DataModel model = new FileDataModel(ratingsFile);


UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity); 
List<RecommendedItem> recommendations = recommender.recommend(1, 1); 

String ls = "";
for (RecommendedItem recommendation : recommendations) {
	System.out.println(recommendation);
	ls= recommendation+"";
}
return ls;

}

}
