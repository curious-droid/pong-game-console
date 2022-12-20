
public class TwentyFortyEightGrid {
	private int[][] grid;
	private boolean dead;
	private boolean win;

	public TwentyFortyEightGrid() {
		reset();
	}

	public int score() {
		int score = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				score = Math.max(score, grid[i][j]);
			}
		}
		return score;
	}

	public void draw() {
		for (int i = 0; i < 4; i++) {
			for (int h = 0; h < TwentyFortyEightUtil.Two.length; h++) {
				for (int j = 0; j < 4; j++) {
					System.out.print(colors[grid[i][j]] + numToString(grid[i][j])[h] + Color.RESET);
				}
				System.out.println();
			}
		}
	}

	private final String[] colors = {Color.GRAY, Color.BLACK, Color.RED, Color.ORANGE, Color.YELLOW,
			Color.GREEN, Color.TURQUOISE, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.PURPLE, Color.PINK};

	private String[] numToString(int num) {
		switch (num) {
		case 1:
			return TwentyFortyEightUtil.Two;
		case 2:
			return TwentyFortyEightUtil.Four;
		case 3:
			return TwentyFortyEightUtil.Eight;
		case 4:
			return TwentyFortyEightUtil.Sixteen;
		case 5:
			return TwentyFortyEightUtil.ThirtyTwo;
		case 6:
			return TwentyFortyEightUtil.SixtyFour;
		case 7:
			return TwentyFortyEightUtil.OneTwentyEight;
		case 8:
			return TwentyFortyEightUtil.TwoFiftySix;
		case 9:
			return TwentyFortyEightUtil.FiveTwelve;
		case 10:
			return TwentyFortyEightUtil.TenTwentyFour;
		case 11:
			return TwentyFortyEightUtil.TwentyFortyEight;
		default:
			return TwentyFortyEightUtil.Zero;
		}
	}

	public void reset() {
		grid = new int[4][4];
		dead = false;
		win = false;
		add2();
	}

	public boolean getDead() {
		return dead;
	}

	public boolean getWin() {
		return win;
	}

	public void handleInput(String input) {
		switch (input) {
		case "Up":
			grid = moveUp(grid);
			break;
		case "Down":
			grid = moveDown(grid);
			break;
		case "Left":
			grid = moveLeft(grid);
			break;
		case "Right":
			grid = moveRight(grid);
			break;
		}
		if (changed) {
			int i = getState();
			if (i == 0) {
				add2();
			} else if (i == -1) {
				dead = true;
			} else {
				win = true;
			}
		}
		if (getState() == -1) {
			dead = true;
		}
	}

	private void add2() {
		int r = (int) (Math.random() * 4);
		int c = (int) (Math.random() * 4);
		while (grid[r][c] != 0) {
			r = (int) (Math.random() * 4);
			c = (int) (Math.random() * 4);
		}

		grid[r][c] = 1;
	}

	private int getState() {

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (grid[i][j] == 11) {
					return 1;
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (grid[i][j] == 0) {
					return 0;
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid[i][j] == grid[i + 1][j] || grid[i][j] == grid[i][j + 1]) {
					return 0;
				}
			}
		}

		for (int j = 0; j < 3; j++) {
			if (grid[3][j] == grid[3][j + 1]) {
				return 0;
			}
		}

		for (int i = 0; i < 3; i++) {
			if (grid[i][3] == grid[i + 1][3]) {
				return 0;
			}
		}

		return -1;
	}

	private boolean changed = false;

	private int[][] slide(int[][] grid) {
		int[][] new_grid = new int[4][4];

		for (int i = 0; i < 4; i++) {
			int pos = 0;
			for (int j = 0; j < 4; j++) {
				if (grid[i][j] != 0) {
					new_grid[i][pos] = grid[i][j];
					if (j != pos) {
						changed = true;
					}
					pos++;
				}
			}
		}
		return new_grid;
	}

	private int[][] merge(int[][] grid) {
		int[][] new_grid = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				new_grid[i][j] = grid[i][j];
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (new_grid[i][j] == new_grid[i][j + 1] && new_grid[i][j] != 0) {
					new_grid[i][j] = new_grid[i][j] + 1;
					new_grid[i][j + 1] = 0;
					changed = true;
				}
			}
		}

		return new_grid;
	}

	private int[][] reverse(int[][] grid) {
		int[][] new_grid = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				new_grid[i][j] = grid[i][3 - j];
			}
		}
		return new_grid;
	}

	private int[][] transpose(int[][] grid) {
		int[][] new_grid = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				new_grid[i][j] = grid[j][i];
			}
		}
		return new_grid;
	}

	private int[][] moveLeft(int[][] grid) {
		changed = false;
		int[][] new_grid = slide(grid);
		new_grid = merge(new_grid);
		new_grid = slide(new_grid);
		return new_grid;
	}

	private int[][] moveRight(int[][] grid) {
		int[][] new_grid = reverse(grid);
		changed = false;
		new_grid = moveLeft(new_grid);
		new_grid = reverse(new_grid);
		return new_grid;
	}

	private int[][] moveUp(int[][] grid) {
		int[][] new_grid = transpose(grid);
		changed = false;
		new_grid = moveLeft(new_grid);
		new_grid = transpose(new_grid);
		return new_grid;
	}

	private int[][] moveDown(int[][] grid) {
		int[][] new_grid = transpose(grid);
		changed = false;
		new_grid = moveRight(new_grid);
		new_grid = transpose(new_grid);
		return new_grid;
	}

}
