import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;

public class GameRunner {

	private static PongGame pong;
	private static SnakeGame snake;
	private static TwentyFortyEightGame twentyfortyeight;
	private static BreakoutGame breakout;
	private static boolean gameStart = false;
	private static boolean realGameStart = false;
	private static final int games = 5;
	private static int game = 0;
	private static boolean quitGame = false;
	private static final boolean DEBUG = false;

	public static void main(String[] args) {
		Console.testConsole();
		registerNativeHook();
		pong = new PongGame();
		snake = new SnakeGame();
		twentyfortyeight = new TwentyFortyEightGame();
		breakout = new BreakoutGame();
		Console.clearScreen();
		if (!DEBUG) {
			System.out.println("AT THIS MOMENT: Please enlarge the terminal for impoved graphics. ");
			Console.sleep(5000);
			Console.printAtari();
		}
		Console.animateMenu();
		drawGameMenu();
		Console.sleep(250);
		Console.clearScreen();
		System.out.println(Color.END_1);
		Console.sleep(250);
		System.out.println(Color.END_2);
		System.exit(0);
	}

	// Game Menu drawers:

	private static void drawGameMenu() {
		while (!quitGame) {
			if (!realGameStart) {
				Console.printMenu();
			} else {
				switch (game) {
				case 0:
					pong.setGame();
					pong.drawMenuIntro();
					pong.drawMainMenu();
					pong.playGame();
					realGameStart = false;
					break;
				case 1:
					snake.setGame();
					snake.drawMenuIntro();
					snake.drawMainMenu();
					snake.playGame();
					realGameStart = false;
					break;
				case 2:
					twentyfortyeight.setGame();
					twentyfortyeight.drawMenuIntro();
					twentyfortyeight.drawMainMenu();
					twentyfortyeight.playGame();
					realGameStart = false;
					break;
				case 3:
					breakout.setGame();
					breakout.drawMenuIntro();
					breakout.drawMainMenu();
					breakout.playGame();
					realGameStart = false;
					break;
				case games - 1:
					quitGame = true;
					break;
				}
			}
			Console.sleep(50);
		}
	}

	// Input handlers:
	public static void handleInput(String input) {
		if (realGameStart) {
			switch (game) {
			case 0:
				pong.handleInput(input);
				break;
			case 1:
				snake.handleInput(input);
				break;
			case 2:
				twentyfortyeight.handleInput(input);
				break;
			case 3:
				breakout.handleInput(input);
				break;
			}
		} else {
			switch (input) {
			case "Space":
				if (gameStart) {
					realGameStart = true;
				} else {
					gameStart = true;
				}
				break;
			case "Up":
				game = (game - 1 + games) % games;
				break;
			case "Down":
				game = (game +1) % games;
				break;
			}
		}
	}

	public static void handleClick() {
		switch (game) {
		case 0:
			pong.handleClick();
			break;
		case 3:
			breakout.handleClick();
			break;
		}
	}

	// Helper functions:

	private static void registerNativeHook() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new KeyLogger());
		MouseLogger logger = new MouseLogger();
		GlobalScreen.addNativeMouseListener(logger);
		GlobalScreen.addNativeMouseMotionListener(logger);
	}

	public static void setMouseCoords(int x, int y) {
		switch (game) {
		case 0:
			pong.setMouseCoords(x, y);
			break;
		case 3:
			breakout.setMouseCoords(x, y);
			break;
		}
	}

	public static boolean getGameStart() {
		return gameStart;
	}

	public static int getGame() {
		return game;
	}

	public static int getGames() {
		return games;
	}
}
