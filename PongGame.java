
public class PongGame {
	private int[][] screen;
	private String[][] color;
	private static final int height = 30, width = 40;

	private PongBall ball;
	private PongPaddle player, computer;

	private boolean mouseMode = true;
	private int mouseX, mouseY;
	private boolean classic = false;

	private boolean calibrated = false;
	private boolean calibrate = false;
	private int topY, bottomY;

	private boolean gameStart;
	private boolean gameEnd = false;

	private int playerScore = 0, computerScore = 0;
	private boolean paused;
	private boolean resume = true;

	private static final int levels = 6;
	private int setting;
	private int difficulty = 2;

	public PongGame() {
		playerScore = 0;
		computerScore = 0;
		classic = false;
		screen = new int[height][width];
		color = new String[height][width];
		player = new PongPaddle(height / 2, 1, Color.BLUE, height, width);
		computer = new PongPaddle(height / 2, width - 2, Color.RED, height, width);
		ball = new PongBall(height / 2, width / 2, 1, Color.GREEN, height, width, player, computer);

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
		computerScore = 0;
		playerScore = 0;
		ball.reset(height / 2, width / 2);
		paused = false;
		gameStart = false;
		gameEnd = false;
	}

	// Menu code:
	public void drawMenuIntro() {
		Console.clearScreen();
		System.out.println(Color.P);
		Console.sleep(500);
		Console.clearScreen();
		System.out.println(Color.PO);
		Console.sleep(500);
		Console.clearScreen();
		System.out.println(Color.PON);
		Console.sleep(500);
		Console.clearScreen();
		System.out.println(Color.PONG);
		Console.sleep(500);
		Console.clearScreen();
	}

	public void drawMainMenu() {
		if (paused) {
			while (paused && !gameEnd) {
				System.out.println(Color.PONG);
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
				System.out.println(Color.PONG);
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
		System.out.print("Computer Difficulty: [");
		for (int i = 1; i <= levels; i++) {
			if (i == difficulty) {
				System.out.print(Color.HIGHLIGHT);
				System.out.print(i);
				System.out.print(Color.UNHIGHLIGHT);
				if (setting == 1) {
					System.out.print(Color.YELLOW);
				}
			} else {
				System.out.print(i);
			}
			if (i != levels) {
				System.out.print(" / ");
			}
		}
		System.out.println("]\n");
		if (setting == 1) {
			System.out.print(Color.RESET);
		}
		if (setting == 2) {
			System.out.print(Color.YELLOW);
		}
		System.out.print("Color Palette: [");
		if (classic) {
			System.out.print("Normal / ");
			System.out.print(Color.HIGHLIGHT);
			System.out.print("Classic");
			System.out.print(Color.UNHIGHLIGHT);
			if (setting == 2) {
				System.out.print(Color.YELLOW);
			}
		} else {
			System.out.print(Color.HIGHLIGHT);
			System.out.print("Normal");
			System.out.print(Color.UNHIGHLIGHT);
			if (setting == 2) {
				System.out.print(Color.YELLOW);
			}
			System.out.print(" / Classic");
		}
		System.out.println("]\n");
		if (setting == 2) {
			System.out.print(Color.RESET);
		}
		if (gameStart) {
			if (setting == 3) {
				System.out.print(Color.YELLOW);
			}
			System.out.print("[");
			if (!resume) {
				System.out.print("Resume / ");
				System.out.print(Color.HIGHLIGHT);
				System.out.print("Restart");
				System.out.print(Color.UNHIGHLIGHT);
				if (setting == 3) {
					System.out.print(Color.YELLOW);
				}
			} else {
				System.out.print(Color.HIGHLIGHT);
				System.out.print("Resume");
				System.out.print(Color.UNHIGHLIGHT);
				if (setting == 3) {
					System.out.print(Color.YELLOW);
				}
				System.out.print(" / Restart");
			}
			System.out.println("]\n");
			if (setting == 3) {
				System.out.print(Color.RESET);
			}
		}
		if ((!gameStart && setting == 3) || (gameStart && setting == 4)) {
			System.out.print(Color.HIGHLIGHT);
			System.out.print("Quit PONG");
			System.out.print(Color.UNHIGHLIGHT);
		} else {
			System.out.print("Quit PONG");
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
		String s = "";
		s += String.format("Your score: %-3d | Computer score: %-3d", playerScore, computerScore);
		if (paused) {
			s += "  ~PAUSED~";
		}
		s += "\n";
		if (classic) {
			s += Color.HIGHLIGHT;
		}
		s += "|";
		for (int i = 0; i < screen[0].length; i++) {
			s += "--";
		}
		s += "|\n";
		for (int i = 0; i < screen.length; i++) {
			s += "|";
			for (int j = 0; j < screen[0].length; j++) {
				if (!classic) {
					s += color[i][j];
				}
				if (screen[i][j] == 1) {
					s += "██";
				} else if (screen[i][j] == 2) {
					s += "▒▒";
				} else {
					s += "  ";
				}
			}

			if (!classic) {
				s += Color.RESET;
			}
			s += "|\n";
		}
		s += "|";
		for (int i = 0; i < screen[0].length; i++) {
			s += "--";
		}
		s += "|\n";
		if (classic) {
			s += Color.UNHIGHLIGHT;
		}
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
			if (out() == -1) {
				computerScore++;
			} else if (out() == 1) {
				playerScore++;
			}
		}
		gameStart = false;
		paused = false;
		computerScore = 0;
		playerScore = 0;
		ball.reset(height / 2, width / 2);
	}

	private void playRound() {
		ball.reset(height / 2, width / 2);
		updateDraw();
		draw();
		Console.sleep(250);
		while (out() == 0 && !gameEnd) {
			if (!resume) {
				resume = true;
				computerScore = 0;
				playerScore = 0;
				ball.reset(height / 2, width / 2);
			} else if (!paused) {
				drawPipeline();
			} else {
				drawMainMenu();
			}
			Console.sleep(50);
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
				} else if (computer.draw(i, j) != 0) {
					screen[i][j] = 1;
					color[i][j] = computer.getColor();
				} else {
					screen[i][j] = 0;
					color[i][j] = Color.RESET;
				}
			}
		}
	}

	public void runPhysics() {
		ball.move();
		computer.move(computeDifficultySpeed(), ball);
		if (mouseMode) {
			player.setX(convertMousetoCoords());
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
		topY = mouseY;
		calibrate = true;
		for (int i = 0; i < screen.length - 1; i++) {
			System.out.println();
		}
		System.out.print(". <- Next, click this dot");
		bottomY = topY;
		while (bottomY == topY) {
			calibrate = true;
			while (calibrate) {
				Console.sleep(10);
			}
			bottomY = mouseY;
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
		return (int) (((float) (mouseY - topY)) / (bottomY - topY) * (screen.length));
	}

	// Keyboard handling code:
	public void handleInput(String input) {
		if (!gameStart && input.equals("Space") && setting == 3) {
			endGame();
			return;
		}
		if (gameStart && input.equals("Space")) {
			if (setting == 4) {
				endGame();
				return;
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
					setting = (setting + 4) % 5;
				} else {
					setting = (setting + 3) % 4;
				}
				break;
			case "Down":
				if (gameStart) {
					setting = (setting + 1) % 5;
				} else {
					setting = (setting + 1) % 4;
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
					classic = !classic;
					break;
				case "Left":
					classic = !classic;
					break;
				}
			} else if (gameStart && setting == 3) {
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
		case "Up":
			player.move(-2);
			break;
		case "Down":
			player.move(2);
			break;
		}
	}

	// Helper functions, getters/setters etc.

	private double computeDifficultySpeed() {
		return 0.375 * (difficulty);
	}

	public int out() {
		return ball.out();
	}

}
