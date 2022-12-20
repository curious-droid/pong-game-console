
public class PongPaddle extends Shape {
	private double height, width;
	private int paddleLength = 3, paddleWidth = 0;
	private double velocity;

	public PongPaddle(double x, double y, String color, double height, double width) {
		super(x, y, color);
		this.height = height;
		this.width = width;
	}

	public int draw(double x, double y) {
		if (Math.abs(getX() - x) <= paddleLength && Math.abs(getY() - y) <= paddleWidth) {
			return 1;
		}
		return 0;
	}

	// Controller functions:
	public void move(double i) {
		double prevx = getX();
		setX(prevx + i);
		if (Math.abs(getX() - 0) <= paddleLength || Math.abs(getX() - height + 1) <= paddleLength) {
			setX(prevx);
			velocity = 0;
		} else {
			velocity = getX() - prevx;
		}
	}

	public void move(double i, PongBall b) {
		double prevx = getX();
		if (b.getX() > getX()) {
			setX(prevx + Math.min(i, b.getX() - prevx));
		} else if (b.getX() < getX()) {
			setX(prevx - Math.min(i, prevx - b.getX()));
		}
		if (Math.abs(getX() - 0) <= paddleLength || Math.abs(getX() - height + 1) <= paddleLength) {
			setX(prevx);
			velocity = 0;
		} else {
			velocity = getX() - prevx;
		}
	}

	// Getters/setters:
	public double getVelocity() {
		return velocity;
	}

	public double getL() {
		return paddleLength;
	}

	public void setX(int x) {
		velocity = x - getX();
		super.setX(x);
	}
}
