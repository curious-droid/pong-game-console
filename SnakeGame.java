public class SnakeGame {
	private int[][] screen;
	private String[][] color;
	private static final int height = 40, width = 40;

	private Snake player;
	private Apple apple;

	private boolean gameStart;
	private boolean gameEnd = false;

	private boolean paused;
	private boolean resume = true;

	private static final int levels = 4;
	private int setting;
	private int difficulty = 2;

	private int playerScore = 0;

	public SnakeGame() {
		screen = new int[height][width];
		color = new String[height][width];
		apple = new Apple(height, width);
		player = new Snake(height, width, apple);

		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[0].length; j++) {
				screen[i][j] = 0;
				color[i][j] = Color.RESET;
			}
		}

		paused = false;
		gameStart = false;
	}

	// Menu code:
	public void drawMenuIntro() {
		for (int i = 0; i < Color.SNAKE[0].length(); i++) {
			Console.clearScreen();
			for (int j = 0; j < Color.SNAKE.length; j++) {
				System.out.println(Color.SNAKE[j].substring(0, i + 1));
			}
			Console.sleep(50);
		}
		Console.clearScreen();
	}

	public void drawMainMenu() {
		if (paused) {
			while (paused && !gameEnd) {
				for (int i = 0; i < Color.SNAKE.length; i++) {
					System.out.println(Color.SNAKE[i]);
				}
				printMenu();
				if (resume) {
					System.out.println("Press space to resume");
				} else {
					System.out.println("Press space to restart");
				}
				Console.sleep(50);
				Console.clearScreen();
			}
		} else {
			while (!gameStart && !gameEnd) {
				for (int i = 0; i < Color.SNAKE.length; i++) {
					System.out.println(Color.SNAKE[i]);
				}
				printMenu();
				System.out.println("Press space to play");
				Console.sleep(50);
				Console.clearScreen();
			}
		}
	}

	private void printMenu() {
		if (paused) {
			System.out.print("\n~GAME PAUSED~\n");
		}
		System.out.println("\n----------------------------------------------\n");
		if (setting == 0) {
			System.out.print(Color.YELLOW);
		}
		System.out.print("Difficulty: [");
		for (int i = 1; i <= levels; i++) {
			if (i == difficulty) {
				System.out.print(Color.HIGHLIGHT);
				System.out.print(i);
				System.out.print(Color.UNHIGHLIGHT);
				if (setting == 0) {
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
		if (setting == 0) {
			System.out.print(Color.RESET);
		}
		if (paused) {
			if (setting == 1) {
				System.out.print(Color.YELLOW);
			}
			System.out.print("[");
			if (!resume) {
				System.out.print("Resume / ");
				System.out.print(Color.HIGHLIGHT);
				System.out.print("Restart");
				System.out.print(Color.UNHIGHLIGHT);
				if (setting == 1) {
					System.out.print(Color.YELLOW);
				}
			} else {
				System.out.print(Color.HIGHLIGHT);
				System.out.print("Resume");
				System.out.print(Color.UNHIGHLIGHT);
				if (setting == 1) {
					System.out.print(Color.YELLOW);
				}
				System.out.print(" / Restart");
			}
			System.out.println("]\n");
			if (setting == 1) {
				System.out.print(Color.RESET);
			}
		}
		if ((!paused && setting == 1) || (paused && setting == 2)) {
			System.out.print(Color.HIGHLIGHT);
			System.out.print("Quit SNAKE");
			System.out.print(Color.UNHIGHLIGHT);
		} else {
			System.out.print("Quit SNAKE");
		}
		System.out.println("\n");
		System.out.println("----------------------------------------------");
		System.out.println("\nArrow Keys to navigate Menu\n");
	}

	public void draw() {
		Console.clearScreen();
		String s = String.format("Score: %-3d", playerScore);
		if (this.isPaused()) {
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
		s += "Arrow keys to move snake. Press space to pause.\n";
		System.out.print(s);
	}

	// Main Game code:
	public void playGame() {
		while (!gameEnd) {
			playRound();
			player.reset();
			gameStart = false;
			paused = false;
			playerScore = 0;
			drawMainMenu();
		}
	}

	private void playRound() {
		while (!player.getDead() && !gameEnd) {
			if (!resume) {
				resume = true;
				playerScore = 0;
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

	public void setGame() {
		playerScore = 0;
		player.reset();
		apple.randomize();
		paused = false;
		gameStart = false;
		gameEnd = false;
	}

	// Physics/rendering code:
	public void updateDraw() {
		for (int i = 0; i < screen.length; i++) {
			for (int j = 0; j < screen[0].length; j++) {
				if (player.draw(i, j)) {
					screen[i][j] = 1;
					color[i][j] = Color.GREEN;
				} else if (apple.draw(i, j)) {
					screen[i][j] = 1;
					color[i][j] = Color.RED;
				} else {
					screen[i][j] = 0;
					color[i][j] = Color.RESET;
				}
				if(apple.draw(i, j) && player.draw(i, j)) {
					playerScore++;
					player.grow();
					apple.randomize();
				}
			}
		}
	}

	public void runPhysics() {
		player.move(computeDifficultySpeed());
	}

	public void drawPipeline() {
		runPhysics();
		updateDraw();
		draw();
	}

	// Keyboard handling code:
	public void handleInput(String input) {
		if (input.equals("Space")) {
			if ((paused && setting == 2) || (!paused && setting == 1)) {
				endGame();
				return;
			}
			if (!gameStart) {
				gameStart = true;
				return;
			}
			paused = !paused;
			return;
		}
		if (!gameStart || paused) {
			switch (input) {
			case "Up":
				if (paused) {
					setting = (setting + 2) % 3;
				} else {
					setting = (setting + 1) % 2;
				}
				break;
			case "Down":
				if (paused) {
					setting = (setting + 1) % 3;
				} else {
					setting = (setting + 1) % 2;
				}
				break;
			}
			if (setting == 0) {
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
			} else if (setting == 1) {
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
		player.handleInput(input);
	}

	// Helper functions, getters/setters etc.
	public int getPlayerScore() {
		return playerScore;
	}

	public boolean isPaused() {
		return paused;
	}

	private double computeDifficultySpeed() {
		return 0.25 * (difficulty);
	}
}
