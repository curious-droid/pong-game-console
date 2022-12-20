
public class BreakoutGame {
	private int[][] screen;
	private String[][] color;
	private static final int height = 35, width = 40;

	private BreakoutBall ball;
	private BreakoutPaddle player;
	private BreakoutWall wall;

	private boolean mouseMode = true;
	private int mouseX, mouseY;

	private boolean calibrated = false;
	private boolean calibrate = false;
	private int topX, bottomX;

	private boolean gameStart;
	private boolean gameEnd = false;

	private int playerLives = 0;
	private boolean paused;
	private boolean resume = true;

	private static final int levels = 2;
	private int setting;
	private int difficulty = 1;

	public BreakoutGame() {
		playerLives = 5;
		screen = new int[height][width];
		color = new String[height][width];
		player = new BreakoutPaddle(height - 4, width / 2, Color.TURQUOISE, height, width);
		wall = new BreakoutWall();
		ball = new BreakoutBall(height / 2, width / 2, 0.75, Color.MAGENTA, height, width, player, wall);

		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[0].length; j++) {
				screen[i][j] = 0;
				color[i][j] = Color.RESET;
			}
		}

		paused = false;
		gameStart = false;

	}

	public void setGame() {
		playerLives = 5;
		wall.setup();
		ball.reset(height / 2, width / 2);
		paused = false;
		gameStart = false;
		gameEnd = false;
	}

	// Menu code:
	public void drawMenuIntro() {
		Console.clearScreen();
	}

	public void drawMainMenu() {
		if (paused) {
			while (paused && !gameEnd) {
				System.out.println(Color.BREAKOUT);
				printMenu();
				if (resume) {
					System.out.println("Press space to resume");
				} else {
					System.out.println("Press space to restart");
				}
				Console.sleep(50);
				Console.clearScreen();
			}
			if (!gameEnd && mouseMode && !calibrated) {
				runMouseCalibration();
			}
		} else {
			while (!gameStart && !gameEnd) {
				System.out.println(Color.BREAKOUT);
				printMenu();
				System.out.println("Press space to play");
				Console.sleep(50);
				Console.clearScreen();
			}
			if (!gameEnd && mouseMode && !calibrated) {
				runMouseCalibration();
			}
		}
	}

	private static final String[] types = { "BREAKOUT", "BREAKTHRU" };

	private void printMenu() {
		if (paused) {
			System.out.print("\n~GAME PAUSED~\n");
		}
		System.out.println("\n----------------------------------------------");
		if (setting == 0) {
			System.out.print(Color.YELLOW);
		}
		System.out.print("\nControl Mode: [");
		if (!mouseMode) {
			System.out.print("Mouse / ");
			System.out.print(Color.HIGHLIGHT);
			System.out.print("Arrow Keys");
			System.out.print(Color.UNHIGHLIGHT);
			if (setting == 0) {
				System.out.print(Color.YELLOW);
			}
		} else {
			System.out.print(Color.HIGHLIGHT);
			System.out.print("Mouse");
			System.out.print(Color.UNHIGHLIGHT);
			if (setting == 0) {
				System.out.print(Color.YELLOW);
			}
			System.out.print(" / Arrow Keys");
		}
		System.out.println("]\n");
		if (setting == 0) {
			System.out.print(Color.RESET);
		}
		if (setting == 1) {
			System.out.print(Color.YELLOW);
		}
		System.out.print("Game Type: [");
		for (int i = 1; i <= levels; i++) {
			if (i == difficulty) {
				System.out.print(Color.HIGHLIGHT);
				System.out.print(types[i - 1]);
				System.out.print(Color.UNHIGHLIGHT);
				if (setting == 1) {
					System.out.print(Color.YELLOW);
				}
			} else {
				System.out.print(types[i - 1]);
			}
			if (i != levels) {
				System.out.print(" / ");
			}
		}
		System.out.println("]\n");
		if (setting == 1) {
			System.out.print(Color.RESET);
		}
		if (gameStart) {
			if (setting == 2) {
				System.out.print(Color.YELLOW);
			}
			System.out.print("[");
			if (!resume) {
				System.out.print("Resume / ");
				System.out.print(Color.HIGHLIGHT);
				System.out.print("Restart");
				System.out.print(Color.UNHIGHLIGHT);
				if (setting == 2) {
					System.out.print(Color.YELLOW);
				}
			} else {
				System.out.print(Color.HIGHLIGHT);
				System.out.print("Resume");
				System.out.print(Color.UNHIGHLIGHT);
				if (setting == 2) {
					System.out.print(Color.YELLOW);
				}
				System.out.print(" / Restart");
			}
			System.out.println("]\n");
			if (setting == 2) {
				System.out.print(Color.RESET);
			}
		}
		if ((!gameStart && setting == 2) || (gameStart && setting == 3)) {
			System.out.print(Color.HIGHLIGHT);
			System.out.print("Quit BREAKOUT");
			System.out.print(Color.UNHIGHLIGHT);
		} else {
			System.out.print("Quit BREAKOUT");
		}
		System.out.println("\n");
		System.out.println("----------------------------------------------");
		System.out.println("\nArrow Keys to navigate Menu\n");
		if (gameStart) {
			System.out.println(
					"Press \"C\" to toggle mouse calibration (currently " + (calibrated ? "off" : "on") + ")\n");
		}
	}

	public void draw() {
		Console.clearScreen();
		String s = String.format("Lives remaining: %-3d", playerLives);
		if (paused) {
			s += "  ~PAUSED~";
		}
		s += "\n";
		s += "|";
		for (int i = 0; i < screen[0].length; i++) {
			s += "--";
		}
		s += "|\n";
		for (int i = 0; i < screen.length; i++) {
			s += "|";
			for (int j = 0; j < screen[0].length; j++) {
				s += color[i][j];
				if (screen[i][j] == 1) {
					s += "██";
				} else if (screen[i][j] == 2) {
					s += "▒▒";
				} else {
					s += "  ";
				}
			}

			s += Color.RESET + "|\n";
		}
		s += "|";
		for (int i = 0; i < screen[0].length; i++) {
			s += "--";
		}
		s += "|\n";
		if (mouseMode) {
			s += "Mouse to move paddle. Press space to pause.\n";
		} else {
			s += "Arrow keys to move paddle. Press space to pause.\n";
		}
		System.out.print(s);
	}

	// Main Game code:
	public void playGame() {
		while (!gameEnd) {
			playRound();
			setGame();
			drawMainMenu();
		}
		setGame();
	}

	private void playRound() {
		ball.reset(height / 2, width / 2);
		playerLives = 5;
		updateDraw();
		draw();
		Console.sleep(250);
		while (playerLives > 0 && !gameEnd && !wall.gone()) {
			while (out() == 0 && !gameEnd && !wall.gone()) {
				if (!resume) {
					resume = true;
					setGame();
					gameStart = true;
				} else if (!paused) {
					ball.setDifficulty(difficulty);
					drawPipeline();
				} else {
					drawMainMenu();
				}
				Console.sleep(50);
			}
			ball.reset(height / 2, width / 2);
			playerLives--;
		}
	}

	private void endGame() {
		gameEnd = true;
	}

	// Physics/rendering code:
	public void updateDraw() {
		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[0].length; j++) {
				if (ball.draw(i, j) != 0) {
					screen[i][j] = ball.draw(i, j);
					color[i][j] = ball.getColor();
				} else if (player.draw(i, j) != 0) {
					screen[i][j] = 1;
					color[i][j] = player.getColor();
				} else {
					screen[i][j] = 0;
					color[i][j] = Color.RESET;
				}
				for (BreakoutBlock b : wall.getWall()) {
					if (b.draw(i, j) != 0) {
						screen[i][j] = 1;
						color[i][j] = b.getColor();
					}
				}
			}
		}
	}

	public void runPhysics() {
		ball.move();
		if (mouseMode) {
			player.setY(convertMousetoCoords());
		}
	}

	public void drawPipeline() {
		runPhysics();
		updateDraw();
		draw();
	}

	// Mouse handling code:
	private void runMouseCalibration() {
		Console.clearScreen();
		System.out.println("STARTING MOUSE CALIBRATION...");
		Console.sleep(1000);
		Console.clearScreen();
		calibrate = true;
		System.out.print(
				". <- First, click the dot firmly (do not click twice if nothing happens--mouse linkage may take a second)");
		while (calibrate) {
			Console.sleep(10);
		}
		Console.clearScreen();
		topX = mouseX;
		calibrate = true;
		for (int i = 0; i < screen[0].length - 1; i++) {
			System.out.print("  ");
		}
		System.out.print(". <- Next, click this dot");
		bottomX = topX;
		while (bottomX == topX) {
			calibrate = true;
			while (calibrate) {
				Console.sleep(10);
			}
			bottomX = mouseX;
		}
		Console.clearScreen();
		Console.sleep(100);
		System.out.println("CALIBRATION COMPLETE!");
		Console.sleep(500);
		Console.clearScreen();
		System.out.println();
		Console.sleep(500);
		Console.clearScreen();
		System.out.println("CALIBRATION COMPLETE!");
		Console.sleep(500);
		Console.clearScreen();
		System.out.println();
		Console.sleep(500);
		Console.clearScreen();
		System.out.println("CALIBRATION COMPLETE!");
		Console.sleep(1500);
		Console.clearScreen();
		calibrated = true;
	}

	public void handleClick() {
		if (calibrate) {
			calibrate = false;
		}
	}

	public void setMouseCoords(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	private int convertMousetoCoords() {
		return (int) (((float) (mouseX - topX)) / (bottomX - topX) * (screen[0].length));
	}

	// Keyboard handling code:
	public void handleInput(String input) {
		if (!gameStart && input.equals("Space") && setting == 2) {
			endGame();
			return;
		}
		if (gameStart && input.equals("Space")) {
			if (setting == 3) {
				endGame();
			}
			paused = !paused;
			return;
		}
		if (!gameStart || paused) {
			switch (input) {
			case "C":
				if (gameStart) {
					calibrated = !calibrated;
				}
				break;
			case "Up":
				if (gameStart) {
					setting = (setting + 3) % 4;
				} else {
					setting = (setting + 2) % 3;
				}
				break;
			case "Down":
				if (gameStart) {
					setting = (setting + 1) % 4;
				} else {
					setting = (setting + 1) % 3;
				}
				break;
			case "Space":
				gameStart = true;
			}
			if (setting == 0) {
				switch (input) {
				case "Left":
					mouseMode = true;
					break;
				case "Right":
					mouseMode = false;
					break;
				}
			} else if (setting == 1) {
				switch (input) {
				case "Right":
					difficulty += 1;
					difficulty %= levels;
					if (difficulty == 0) {
						difficulty = levels;
					}
					break;
				case "Left":
					difficulty -= 1;
					difficulty %= levels;
					if (difficulty == 0) {
						difficulty = levels;
					}
					break;
				}
			} else if (setting == 2) {
				switch (input) {
				case "Right":
					resume = !resume;
					break;
				case "Left":
					resume = !resume;
					break;
				}
			}
			return;
		}
		if (mouseMode) {
			return;
		}
		switch (input) {
		case "Left":
			player.move(-2);
			break;
		case "Right":
			player.move(2);
			break;
		}
	}

	// Helper functions, getters/setters etc.

	public int out() {
		return ball.out();
	}

}
