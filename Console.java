public class Console {

	// Initialization functions:
	public static void testConsole() {
		if (!(System.console() != null && System.getenv().get("TERM") != null)) {
			System.err.println("There was a problem linking to console.");
			System.err.println(
					"Remember to execute the code in terminal (Eclipse console and Windows command line will not work)");
			System.exit(1);
		}
	}

	// Drawing Functions:
	
	public static void animateMenu() {
		clearScreen();
		Console.sleep(500);
		System.out.println("\nPONG Console\n");
		Console.sleep(1000);
		System.out.println("Loading Games...");
		System.out.println("----------------------------------------------\n");
		Console.sleep(1000);
		System.out.println("1.PONG\n");
		Console.sleep(1000);
		System.out.println("2.SNAKE\n");
		Console.sleep(1000);
		System.out.println("3.2048\n");
		Console.sleep(1000);
		System.out.println("4.BREAKOUT\n");
		Console.sleep(1000);
		System.out.println("----------------------------------------------");
		Console.sleep(750);
		System.out.println("QUIT PONG CONSOLE");
		System.out.println("----------------------------------------------");
		System.out.println("\nArrow Keys to navigate Menu\n");
		System.out.println("Press space to play\n");
	}
	
	public static void printMenu() {
		clearScreen();
		System.out.println("\nPONG Console\n");
		System.out.println("Loaded Games:");
		System.out.println("----------------------------------------------\n");
		if(GameRunner.getGame() == 0) {
			System.out.print(Color.HIGHLIGHT);
		}
		System.out.println("1.PONG");
		if(GameRunner.getGame() == 0) {
			System.out.print(Color.UNHIGHLIGHT);
		}
		System.out.println();
		if(GameRunner.getGame() == 1) {
			System.out.print(Color.HIGHLIGHT);
		}
		System.out.println("2.SNAKE");
		if(GameRunner.getGame() == 1) {
			System.out.print(Color.UNHIGHLIGHT);
		}
		System.out.println();
		if(GameRunner.getGame() == 2) {
			System.out.print(Color.HIGHLIGHT);
		}
		System.out.println("3.2048");
		if(GameRunner.getGame() == 2) {
			System.out.print(Color.UNHIGHLIGHT);
		}
		System.out.println();
		if(GameRunner.getGame() == 3) {
			System.out.print(Color.HIGHLIGHT);
		}
		System.out.println("4.BREAKOUT");
		if(GameRunner.getGame() == 3) {
			System.out.print(Color.UNHIGHLIGHT);
		}
		System.out.println();
		System.out.println("----------------------------------------------");
		if(GameRunner.getGame() == GameRunner.getGames()-1) {
			System.out.print(Color.HIGHLIGHT);
		}
		System.out.println("QUIT PONG CONSOLE");
		if(GameRunner.getGame() == GameRunner.getGames()-1) {
			System.out.print(Color.UNHIGHLIGHT);
		}
		System.out.println("----------------------------------------------");
		System.out.println("\nArrow Keys to navigate Menu\n");
		System.out.println("Press space to play\n");
	}
	
	public static void printAtari() {
		clearScreen();
		sleep(500);
		System.out.println();
		for (int i = 0; i < Color.ATARI_LOGO.length; i++) {
			System.out.print(" " + Color.ATARI_COLOR[i] + Color.ATARI_LOGO[i]);
			System.out.printf(Color.BLACK + " ~%3.1f%%", ((i + 1) * 100.0 / Color.ATARI_LOGO.length));
			sleep((int) (200 * Math.random()) + 200);
			if (i != Color.ATARI_LOGO.length - 1) {
				System.out.println("\b\b\b\b\b\b       ");
			}
		}
		sleep(1000);
		System.out.println("\b\b\b\b\b\b\b       ");
		System.out.print(Color.RESET);
		sleep(1000);
		System.out.print(Color.ATARI_NAME_1);
		sleep(50);
		System.out.println(Color.ATARI_NAME_2);
		sleep(1000);
		for (int i = 0; i < Color.ATARI.length(); i++) {
			System.out.print(Color.ATARI.charAt(i));
			sleep(50);
		}
		System.out.println();
		sleep(10);
		System.out.println();
		sleep(10);
		String cont = "         Press space to continue        ";
		for (int i = 0; i < cont.length(); i++) {
			System.out.print(cont.charAt(i));
			sleep(25);
		}
		while (!GameRunner.getGameStart()) {
			System.out.print(" ");
			sleep(500);
			System.out.print("\b");
			sleep(500);
		}
	}

	// Helper functions:
	public static void clearScreen() {
		System.out.print("\033\143");
	}

	public static void sleep(int t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
