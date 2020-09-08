package com.virtualpairprogrammers.models;

public class DoubleWithSqrt {

	private double value;
	private double sqrt;
	public DoubleWithSqrt(Double value) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.sqrt = Math.sqrt(Math.sqrt(this.value));
	}

}
