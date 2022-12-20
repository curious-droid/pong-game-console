
public abstract class Shape {
	private double x, y;
	private String color;

	public Shape(double x, double y, String color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public abstract int draw(double x, double y);

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
