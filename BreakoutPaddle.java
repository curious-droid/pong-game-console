
public class BreakoutPaddle extends Shape {
	private double height, width;
	private int paddleLength = 2, paddleWidth = 0;
	private double velocity;

	public BreakoutPaddle(double x, double y, String color, double height, double width) {
		super(x, y, color);
		this.height = height;
		this.width = width;
	}

	public int draw(double x, double y) {
		if (Math.abs(getX() - x) <= paddleWidth && Math.abs(getY() - y) <= paddleLength) {
			return 1;
		}
		return 0;
	}

	// Controller functions:
	public void move(double i) {
		double prevy = getY();
		setY(prevy + i);
		if (Math.abs(getY() - 0) <= paddleWidth || Math.abs(getY() - width + 1) <= paddleWidth) {
			setX(prevy);
			velocity = 0;
		} else {
			velocity = getY() - prevy;
		}
	}

	// Getters/setters:
	public double getVelocity() {
		return velocity;
	}

	public double getL() {
		return paddleLength;
	}
	
	public void setY(int y) {
		velocity = y - getY();
		super.setY(y);
	}
}
