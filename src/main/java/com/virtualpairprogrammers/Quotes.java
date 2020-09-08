/**
 * 
 */
package com.virtualpairprogrammers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import com.virtualpairprogrammers.models.DoubleWithSqrt;

/**
 * @author Sarath
 *
 */
public class Quotes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Double> inputData = new ArrayList<>();
		inputData.add(34.5);
		inputData.add(46.26);
		inputData.add(84.3);
		Logger.getLogger("org.apache").setLevel(Level.WARN);
		SparkConf conf = new SparkConf()
				.setAppName("Find Qoutes for Tata Motors")
				// Use all the available cores on this machine to run this program
				.setMaster("local[*]");
		JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
		
		// JavaRDD wrapper class for scalaRDD
		JavaRDD<Double> rdd = javaSparkContext.parallelize(inputData);
		
		// Computation of inputData
		Double result = rdd.reduce((value1, value2) -> value1 + value2 );
		System.out.println("Result is : " + result);
		List<Double> squareRoots = rdd.map((value) -> Math.sqrt(value) ).collect();
		JavaRDD<DoubleWithSqrt> squareRootObjects = rdd.map((value) -> new DoubleWithSqrt(value) );
		//squareRoots.forEach(System.out::println);

		System.out.println(squareRoots);
		// close the javaSparkContext
		javaSparkContext.close();
		

	}

}
