package com.virtualpairprogrammers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;


public class LoggerTest {
	public static void main(String[] args) {
		List<String> inputData = new ArrayList<>();
		inputData.add("ERROR: Tuesday 8th September 2020");
		inputData.add("FATAL: Monday 7th September 2020");
		inputData.add("ERROR: Saturday 5th September 2020");
		inputData.add("WARN: Friday 4th September 2020");
		inputData.add("ERROR: Tuesday 1th September 2020");

		Logger.getLogger("org.apache").setLevel(Level.WARN);
		
		SparkConf conf = new SparkConf()
				.setAppName("Read the Log information")
				.setMaster("local[*]");
		JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
		
		JavaRDD<String> originalLogMessages = javaSparkContext.parallelize(inputData);
		
		originalLogMessages
			.mapToPair(rawValue -> new Tuple2<>(rawValue.split(":")[0] , 1L  ))
			.reduceByKey((val1, val2) -> val1 + val2)
			.foreach(t -> System.out.println(t._1 + " has " + t._2 + " instances"));
		
		
		
		javaSparkContext.close();

		
		


	}

}
