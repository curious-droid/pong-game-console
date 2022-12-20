
public class BreakoutWall {
	private BreakoutBlock[] wall;
	private static final String[] colors = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE };

	public BreakoutWall() {
		setup();
	}

	public void setup() {
		wall = new BreakoutBlock[5 * 10];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				wall[i * 10 + j] = new BreakoutBlock(2 * i + 3, 4 * j, 2 * i + 4, 4 * j + 3, colors[i]);
			}
		}
	}

	public boolean gone() {
		for(BreakoutBlock b : wall) {
			if(!b.isBroken()) {
				return false;
			}
		}
		return true;
	}
	
	public BreakoutBlock[] getWall() {
		return wall;
	}
}
