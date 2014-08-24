package sample.jsp;

import java.awt.geom.AffineTransform;

import org.codehaus.jackson.annotate.JsonProperty;

public class Joystick {

	@JsonProperty("x")
	private double x;
	@JsonProperty("y")
	private double y;

	public Joystick() {
		super();
	}

	public Joystick(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	/**
	 * convert cartesian coordinates to polar coordinates to determine servo
	 * position velocity is always positive (abs from 0) wheel postion must
	 * checked if positive or negative
	 * 
	 * */

	public double getVelocity() {
		return Math.hypot(this.x, this.y);
	}

	/**
	 * positive angle is driving forward, negative means backward
	 * 
	 */
	public double getWheehlPos() {

		/*
		 * perform a 90 degree rotation of input x, y because moving the mouse
		 * course up/down should accelerate the rover instead of moving right
		 * and left
		 */
		double[] sourcePoint = { x, y };
		double[] destPoint = { 0.0, 0.0 };
		AffineTransform.getRotateInstance(Math.PI / 2).transform(sourcePoint,
				0, destPoint, 0, 1); // specifying to use this double[] to hold
										// coords
		double newX = destPoint[0];
		double newY = destPoint[1];

		/*
		 * cartesian to polar conversion
		 */
		return Math.toDegrees(Math.atan2(newX, newY));
	}

}
