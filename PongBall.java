
public class PongBall extends Shape {
	private double r, height, width;
	private double vx, vy;
	private PongPaddle p1, p2;
	private int out;
	private final double curveCoef = 0.075;
	private final double velocityCoef = 0.25;
	private final double afterimageCoef = 0.1;

	public PongBall(double x, double y, double r, String color, double height, double width, PongPaddle player,
			PongPaddle computer) {
		super(x, y, color);
		this.r = r;
		this.height = height;
		this.width = width;
		vx = -0.5;
		vy = -0.5;
		p1 = player;
		p2 = computer;
		out = 0;
	}

	public int draw(double x, double y) {
		if ((getX() - x) * (getX() - x) + (getY() - y) * (getY() - y) <= r * r) {
			return 1;
		}
		double tx = vx * afterimageCoef, ty = vy * afterimageCoef;
		if (tx >= 0) {
			for (double i = 0; i <= tx; i += 0.1) {
				if ((getX() - tx * (i / ty) - x) * (getX() - tx * (i / ty) - x)
						+ (getY() - i - y) * (getY() - i - y) <= r * r) {
					return 2;
				}
			}
		} else {
			for (double i = 0; i >= tx; i -= 0.1) {
				if ((getX() - tx * (i / ty) - x) * (getX() - tx * (i / ty) - x)
						+ (getY() - i - y) * (getY() - i - y) <= r * r) {
					return 2;
				}
			}
		}
		return 0;
	}

	// Physics:
	public void move() {
		double prevx = getX(), prevy = getY();
		setX(prevx + vx);
		setY(prevy + vy);
		if (getX() <= r || getX() >= height - 1 - r) {
			vx = -vx;
			setX(prevx);
		} else if (getY() - p1.getY() >= 0 && getY() - p1.getY() <= r && Math.abs(getX() - p1.getX()) <= r + p1.getL()) {// hit left-side
																								// paddle
			vy = 0.1 - vy;
			setY(prevy);
			vx += (getX() - p1.getX()) * curveCoef;
			vx += p1.getVelocity() * velocityCoef;
		} else if (p2.getY() - getY() >= 0 && p2.getY() - getY() <= r && Math.abs(getX() - p2.getX()) <= r + p2.getL()) {// hit right-side
																								// paddle
			vy = -vy - 0.1;
			setY(prevy);
			vx -= (getX() - p2.getX()) * curveCoef;
			vx += p2.getVelocity() * velocityCoef;
		} else if (getY() <= r) {
			out = -1;
		} else if (getY() >= width - 1 - r) {
			out = 1;
		}
	}

	// Getters/setters:
	public int out() {
		return out;
	}

	public void reset(int x, int y) {
		setX(x);
		setY(y);
		vx = (Math.random() < 0.5) ? -0.5 : 0.5;
		vy = -0.5;
		out = 0;
	}
}
