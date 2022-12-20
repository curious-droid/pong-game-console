public class Color {
	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String MAGENTA = "\033[35m";
	public static final String PINK = "\033[38;5;206m";
	public static final String TURQUOISE = "\033[38;5;6m";
	public static final String GRAY = "\033[38;5;7m";
	public static final String ORANGE = "\033[38;5;166m";
	public static final String WHITE = "\u001B[37m";
	public static final String HIGHLIGHT = "\033[37;40m";
	public static final String UNHIGHLIGHT = "\033[0m";
	public static final String[] ATARI_LOGO = { "              $$ $$$$$ $$", "              $$ $$$$$ $$",
	"             .$$ $$$$$ $$.", "             :$$ $$$$$ $$:", "             $$$ $$$$$ $$$",
	"             $$$ $$$$$ $$$", "            ,$$$ $$$$$ $$$.", "           ,$$$$ $$$$$ $$$$.",
	"          ,$$$$; $$$$$ :$$$$.", "         ,$$$$$  $$$$$  $$$$$.", "       ,$$$$$$'  $$$$$  `$$$$$$.",
	"     ,$$$$$$$'   $$$$$   `$$$$$$$.", "  ,s$$$$$$$'     $$$$$     `$$$$$$$s.",
	"$$$$$$$$$'       $$$$$       `$$$$$$$$$", "$$$$$Y'          $$$$$          `Y$$$$$" };
	public static final String[] ATARI_COLOR = { YELLOW, YELLOW, YELLOW, GREEN, GREEN,
	GREEN, CYAN, CYAN, BLUE, BLUE, PURPLE, PURPLE, RED,
	RED, RED };
	public static final String ATARI_NAME_1 = 
	    "          _  _____  _    ____  ___ \n"
	  + "         / \\|_   _|/ \\  |  _ \\|_ _|\n"
	  + "        / _ \\ | | / _ \\ | |_) || | \n";
	public static final String ATARI_NAME_2 = 
	  "       / ___ \\| |/ ___ \\|  _ < | | \n"
	  + "      /_/   \\_\\_/_/   \\_\\_| \\_\\___|";
	public static final String ATARI = "          Video Computer System         ";
	public static final String PONG = "  _____   ____  _   _  _____ \n" + " |  __ \\ / __ \\| \\ | |/ ____|\n"
	+ " | |__) | |  | |  \\| | |  __ \n" + " |  ___/| |  | | . ` | | |_ |\n"
	+ " | |    | |__| | |\\  | |__| |\n" + " |_|     \\____/|_| \\_|\\_____|";
	public static final String PON = "  _____   ____  _   _ \n" + " |  __ \\ / __ \\| \\ | |\n"
	+ " | |__) | |  | |  \\| |\n" + " |  ___/| |  | | . ` |\n" + " | |    | |__| | |\\  |\n"
	+ " |_|     \\____/|_| \\_|";
	public static final String PO = "  _____   ____  \n" + " |  __ \\ / __ \\ \n" + " | |__) | |  | |\n"
	+ " |  ___/| |  | |\n" + " | |    | |__| |\n" + " |_|     \\____/";
	public static final String P = "  _____  \n" + " |  __ \\ \n" + " | |__) |\n" + " |  ___/ \n" + " | |     \n"
	+ " |_|     ";
	public static final String END_1 = "  _____ _                 _                          \n"
	+ " |_   _| |__   __ _ _ __ | | __  _   _  ___  _   _   \n"
	+ "   | | | '_ \\ / _` | '_ \\| |/ / | | | |/ _ \\| | | |  \n"
	+ "   | | | | | | (_| | | | |   <  | |_| | (_) | |_| |  \n"
	+ "   |_| |_| |_|\\__,_|_| |_|_|\\_\\  \\__, |\\___/ \\__,_|  \n"
	+ "  / _| ___  _ __   _ __ | | __ _ |___/(_)_ __   __ _ \n"
	+ " | |_ / _ \\| '__| | '_ \\| |/ _` | | | | | '_ \\ / _` |\n"
	+ " |  _| (_) | |    | |_) | | (_| | |_| | | | | | (_| |\n"
	+ " |_|  \\___/|_|    | .__/|_|\\__,_|\\__, |_|_| |_|\\__, |\n"
	+ "                  |_|            |___/         |___/ ";
	public static final String END_2 = "                ____   ___  _   _  ____ \n"
	+ "               |  _ \\ / _ \\| \\ | |/ ___|\n" + "               | |_) | | | |  \\| | |  _ \n"
	+ "               |  __/| |_| | |\\  | |_| |\n" + "               |_|    \\___/|_| \\_|\\____|\n"
	+ "                                        ";
	public static final String[] SNAKE= {
			  "   _____ _   _          _  ________ "
			, "  / ____| \\ | |   /\\   | |/ /  ____|       "
			, " | (___ |  \\| |  /  \\  | ' /| |__          "
			, "  \\___ \\| . ` | / /\\ \\ |  < |  __|       "
			, "  ____) | |\\  |/ ____ \\| . \\| |____       "
			, " |_____/|_| \\_/_/    \\_\\_|\\_\\______|    "};
	public static final String BREAKOUT = "  ____  _____  ______          _  ______  _    _ _______ \n"
			+ " |  _ \\|  __ \\|  ____|   /\\   | |/ / __ \\| |  | |__   __|\n"
			+ " | |_) | |__) | |__     /  \\  | ' / |  | | |  | |  | |   \n"
			+ " |  _ <|  _  /|  __|   / /\\ \\ |  <| |  | | |  | |  | |   \n"
			+ " | |_) | | \\ \\| |____ / ____ \\| . \\ |__| | |__| |  | |   \n"
			+ " |____/|_|  \\_\\______/_/    \\_\\_|\\_\\____/ \\____/   |_|   ";
}