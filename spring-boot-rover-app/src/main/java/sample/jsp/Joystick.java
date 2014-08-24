package sample.jsp;

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
	 * position
	 * 
	 * */

	public double getVelocity() {
		return Math.hypot(this.x, this.y);
	}

	public double getWheehlPos() {
		return 180 * Math.atan2(this.x, this.y) / Math.PI;
	}

}
