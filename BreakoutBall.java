
public class BreakoutBall extends Shape {
	private double r, height, width;
	private double vx, vy;
	private BreakoutPaddle p1;
	private BreakoutWall wall;
	private int out;
	private final double curveCoef = 0.05;
	private final double velocityCoef = 0.1;
	private final double afterimageCoef = 0.1;
	private int difficulty;

	public BreakoutBall(double x, double y, double r, String color, double height, double width, BreakoutPaddle player,
			BreakoutWall wall) {
		super(x, y, color);
		this.r = r;
		this.height = height;
		this.width = width;
		this.wall = wall;
		vx = -0.5;
		vy = -0.5;
		p1 = player;
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
		double newx = prevx + vx, newy = prevy + vy;
		setX(prevx + vx);
		setY(prevy + vy);
		BreakoutBlock closest = null;
		double close = 100000000;
		
		for (BreakoutBlock b : wall.getWall()) {
			if (b.intersects(prevx, prevy, newx, newy) && !b.isBroken()) {//intersection function determines whether given line segment (basically the velocity vector) intersects with the block
				if(2*b.distance(prevx, prevy) + b.distance(newx, newy) < close) { //distance to prevpos is weighted more than distance to currpos--we basically want to find the first block that it hit
					closest = b;
					close = 2*b.distance(prevx, prevy) + b.distance(newx, newy);
				}
			}
		}
		if(closest != null) {//if there is a block that has been intersected
			closest.destroy();
			if (difficulty == 1) {
				if (!closest.isHorizontal(prevx, prevy, newx, newy)) {//isHorizontal checks which side of the block has been hit
					vx = -vx;
				} else {
					vy = -vy;
				}
				setX(prevx);
				setY(prevy);
			}
		}
		if (getY() <= r || getY() >= width - 1 - r) {
			vy = -vy;
			setY(prevy);
		} else if (p1.getX() - getX() >= 0 && p1.getX() - getX() <= r && Math.abs(getY() - p1.getY()) <= r + p1.getL()) {
			vx = -vx;
			setX(prevx);
			vy += (getY() - p1.getY()) * curveCoef;
			vy += p1.getVelocity() * velocityCoef;
		} else if (getX() < 0) {
			vx = -vx;
		} else if (getX() >= height - 1 - r) {
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
		vy = (Math.random() < 0.5) ? -0.5 : 0.5;
		vx = -0.5;
		out = 0;
	}

	public void reset() {
		out = 0;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}
