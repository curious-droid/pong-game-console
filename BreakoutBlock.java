
public class BreakoutBlock {
	private double x1, y1, x2, y2;
	private String color;
	private boolean broken;

	public BreakoutBlock(double x1, double y1, double x2, double y2, String color) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.color = color;
		broken = false;
	}

	public int draw(double x, double y) {
		if (x1 <= x && x <= x2 && y1 <= y && y <= y2 && !isBroken()) {
			return 1;
		}
		return 0;
	}

	public String getColor() {
		return color;
	}
	public boolean intersects (double x1, double y1, double x2, double y2) {  
		double minX = this.x1 - 0.5;
		double minY = this.y1 - 0.5;
		double maxX = this.x2 + 0.5;
		double maxY = this.y2 + 0.5;
	    if ((x1 <= minX && x2 <= minX) || (y1 <= minY && y2 <= minY) || (x1 >= maxX && x2 >= maxX) || (y1 >= maxY && y2 >= maxY))
	        return false;

	    double m = (y2 - y1) / (x2 - x1);

	    double y = m * (minX - x1) + y1;
	    if (y > minY && y < maxY) return true;

	    y = m * (maxX - x1) + y1;
	    if (y > minY && y < maxY) return true;

	    double x = (minY - y1) / m + x1;
	    if (x > minX && x < maxX) return true;

	    x = (maxY - y1) / m + x1;
	    if (x > minX && x < maxX) return true;

	    return false;
	}
	public boolean isHorizontal (double x1, double y1, double x2, double y2) {  
		double minX = this.x1 - 0.5;
		double minY = this.y1 - 0.5;
		double maxX = this.x2 + 0.5;
		double maxY = this.y2 + 0.5;
		
	    double m = (y2 - y1) / (x2 - x1);

	    double y = m * (minX - x1) + y1;
	    if (y >= minY && y <= maxY && x1 <= minX) return false;

	    y = m * (maxX - x1) + y1;
	    if (y >= minY && y <= maxY && x1 >= maxX) return false;

	    double x = (minY - y1) / m + x1;
	    if (x >= minX && x <= maxX && y1 <= minY) return true;

	    x = (maxY - y1) / m + x1;
	    if (x >= minX && x <= maxX && y1 >= maxY) return true;

	    return false;
	}
	public void destroy() {
		broken = true;
	}

	public boolean isBroken() {
		return broken;
	}

	public double distance(double prevx, double prevy) {
		return ((x1-x2)/2 - prevx) * ((x1-x2)/2 - prevx) + ((y1-y2)/2 - prevy)*((y1-y2)/2 - prevy);
	}
}
