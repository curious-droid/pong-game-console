public class TwentyFortyEightGame {
	private boolean gameStart;
	private boolean gameEnd = false;

	private boolean paused;
	private boolean resume = true;
	private TwentyFortyEightGrid grid;

	private int setting;

	public TwentyFortyEightGame() {
		grid = new TwentyFortyEightGrid();
		paused = false;
		gameStart = false;
	}

	// Menu code:
	public void drawMenuIntro() {
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.TwoT);
		Console.sleep(250);
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.FourT);
		Console.sleep(250);
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.EightT);
		Console.sleep(250);
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.SixteenT);
		Console.sleep(250);
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.ThirtyTwoT);
		Console.sleep(250);
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.SixtyFourT);
		Console.sleep(250);
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.OneTwentyEightT);
		Console.sleep(250);
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.TwoFiftySixT);
		Console.sleep(250);
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.FiveTwelveT);
		Console.sleep(250);
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.TenTwentyFourT);
		Console.sleep(250);
		Console.clearScreen();
		System.out.print(TwentyFortyEightUtil.TwentyFortyEightT);
		Console.sleep(250);
		Console.clearScreen();
	}

	public void drawMainMenu() {
		if (paused) {
			while (paused && !gameEnd) {
				System.out.print(TwentyFortyEightUtil.TwentyFortyEightT);
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
				System.out.print(TwentyFortyEightUtil.TwentyFortyEightT);
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
		if (paused) {
			if (setting == 0) {
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
			if (setting == 0) {
				System.out.print(Color.RESET);
			}
		} else {
			if (setting == 0) {
				System.out.print(Color.YELLOW);
			}
			System.out.println("Start Game\n");
			if (setting == 0) {
				System.out.print(Color.RESET);
			}
		}
		if (setting == 1) {
			System.out.print(Color.HIGHLIGHT);
			System.out.print("Quit 2048");
			System.out.print(Color.UNHIGHLIGHT);
		} else {
			System.out.print("Quit 2048");
		}
		System.out.println("\n");
		System.out.println("----------------------------------------------");
		System.out.println("\nArrow Keys to navigate Menu\n");
	}

	public void draw() {
		Console.clearScreen();
		System.out.println(String.format("Score: %-3d", grid.score()));
		grid.draw();
		System.out.print("Arrow keys to play. Press space to pause.\n");
	}

	// Main Game code:
	public void playGame() {
		while (!gameEnd) {
			playRound();
			if(grid.getDead()) {
				draw();
				Console.sleep(2000);
				Console.clearScreen();
				System.out.println("GAME OVER");
				Console.sleep(2000);
				Console.clearScreen();
			}
			grid.reset();
			gameStart = false;
			paused = false;
			drawMainMenu();
			if(gameEnd) {
				return;
			}
		}
	}

	private void playRound() {
		while (!grid.getDead() && !gameEnd && !grid.getWin()) {
			if (!resume) {
				resume = true;
			} else if (!paused) {
				draw();
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
		grid.reset();
		paused = false;
		gameStart = false;
		gameEnd = false;
	}

	// Keyboard handling code:
	public void handleInput(String input) {
		if (input.equals("Space")) {
			if (setting == 1) {
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
				setting = (setting + 1) % 2;
				break;
			case "Down":
				setting = (setting + 1) % 2;
				break;
			}
			if (paused && setting == 0) {
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
		grid.handleInput(input);
	}

	// Helper functions, getters/setters etc.
	public boolean isPaused() {
		return paused;
	}
}
