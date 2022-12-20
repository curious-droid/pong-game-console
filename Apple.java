
public class Apple {
	private int height, width;
	private int x, y;
	public Apple(int height, int width) {
		this.height = height;
		this.width = width;
		randomize();
	}
	public void randomize() {
		x = (int) (Math.random()*height);
		y = (int) (Math.random()*width);
	}

	public boolean draw(double x, double y) {
		return (this.x == x && this.y == y);
	}
}
