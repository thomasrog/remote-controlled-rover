package sample.jsp;

import org.codehaus.jackson.annotate.JsonProperty;

public class Joystick {

	@JsonProperty("left")
	private int left;
	@JsonProperty("right")
	private int right;
	@JsonProperty("up")
	private int up;
	@JsonProperty("down")
	private int down;

	public Joystick(int left, int right, int up, int down) {
		super();
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public int getUp() {
		return up;
	}

	public int getDown() {
		return down;
	}

}
