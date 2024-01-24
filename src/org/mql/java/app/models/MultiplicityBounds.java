package org.mql.java.app.models;

public class MultiplicityBounds {
	
	private char lowerBound;
	private char upperBound;

	public MultiplicityBounds() {
		this.lowerBound = '1';
		this.upperBound = '1';
	}

	public MultiplicityBounds(char lowerBound, char upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public void setUpperBound(char upperBound) {
		this.upperBound = upperBound;
	}

	public char getUpperBound() {
		return upperBound;
	}
	
	public char getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(char lowerBound) {
		this.lowerBound = lowerBound;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
