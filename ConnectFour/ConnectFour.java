package ConnectFour;

import java.util.ArrayList;

/**
 * Connect Four for the trolls and the lols
 * 
 * @author curiousdroid
 */

public class ConnectFour {
	private static int[][] board = new int[6][7];
	private static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	private static int[] dy = { 1, 1, 0, -1, -1, -1, 0, 1 };
	private static int difficulty = 4;

	/**
	 * Main function. It does stuff. You probably want to run this (you will always
	 * lose though).
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		System.out.println("MWAHAHAHAHAHA WECLDOM TO BUDGET CONNECT FOUR\n"); // prints a welcoming message
		do {
			System.out.print("input difficulty (1-4): ");
			difficulty = TextIO.getlnInt();
		} while (difficulty > 4 || difficulty < 1); // gets difficulty
		System.out.println();
		printBoard(board);
		while (!gameEnd()) {
			int move;
			do {
				System.out.print("input move (1-7): ");
				move = TextIO.getlnInt();
			} while (!validMove(move - 1, board)); // gets your move
			board = processMove(move - 1, board, 1);
			System.out.print("\nthinking...\r");
			if (isWinner(1, board)) {
				break;
			}
			move = gimmebestmove(board); // thinks
			int[][] newboard = processMove(move, board, 2);
			printBoard(newboard);
			if (!isWinner(1, board) && !isWinner(1, newboard)) {
				taunt(); // insults you
			} else {
				break;
			}
			board = newboard;
		}
		if (isWinner(1, board)) { // this will never occur
			System.out.println("YOU WIN");
			System.out.println("But it was only against difficulty " + difficulty + " bot so... Small L");
		} else if (isWinner(2, board)) {
			System.out.println("YOU LOSE");
			System.out.println("Big L");
		} else {
			System.out.println("TIE");
			System.out.println("Medium L");
		}
	}

	/**
	 * Gets the best move. Does this by thinking very hard.
	 * 
	 * @param board
	 * @return best move
	 */
	private static int gimmebestmove(int[][] board) {
		double max = 10;
		ArrayList<Pair> m = new ArrayList<>();
		ArrayList<Integer> i = new ArrayList<>();
		Pair[] moves = thinkreallyhard(difficulty, board);
		int c = 0;
		for (Pair p : moves) {
			if (p.max < max) {
				m.clear();
				i.clear();
			}
			if (p.max <= max) {
				max = p.max;
				m.add(p);
				i.add(c);
			}
			c++;
		}
		double maxavg = 10;
		ArrayList<Integer> bestmoves = new ArrayList<>();
		for (int k = 0; k < m.size(); k++) {
			if (m.get(k).average < maxavg) {
				bestmoves.clear();
				bestmoves.add(i.get(k));
				maxavg = m.get(k).average;
			}
			if (m.get(k).average == maxavg) {
				bestmoves.add(i.get(k));
			}
		}
		return bestmoves.get((int) (Math.random() * bestmoves.size()));
	}

	/**
	 * Checks that you're not cheating by putting in an invalid move >:(
	 * 
	 * @param move
	 * @param board
	 * @return whether move is valid
	 */
	private static boolean validMove(int move, int[][] board) {
		return move >= 0 && move < 7 && board[5][move] == 0;
	}

	/**
	 * Thinks really hard. Like, reeaally hard.
	 * In all seriousness, its just minimax with tiebreaking by calculating the probability that you will find the winning method (obviously zero)
	 * 
	 * @param depth of thinking. always very deep.
	 * @param board
	 * @return deep, insightful thoughts
	 */
	private static Pair[] thinkreallyhard(int depth, int[][] board) {
		Pair[] res = new Pair[7];
		for (int selfmove = 0; selfmove < 7; selfmove++) {
			if (!validMove(selfmove, board)) {
				res[selfmove] = new Pair(0, 10);
				continue;
			}
			int[][] newboardstate = processMove(selfmove, board, 2);
			if (isWinner(2, newboardstate)) {
				res[selfmove] = new Pair(0, 0);
				continue;
			}
			double average = 0, max = 0;
			for (int playermove = 0; playermove < 7; playermove++) {
				if (!validMove(playermove, newboardstate)) {
					continue;
				}
				int[][] newnewboardstate = processMove(playermove, newboardstate, 1);
				if (isWinner(1, newnewboardstate)) {
					max = 1;
					average += 1.0 / 7;
				} else if (depth != 1) {
					double avg = 0;
					Pair[] r = thinkreallyhard(depth - 1, newnewboardstate);
					double min = 10;
					for (int k = 0; k < 7; k++) {
						min = Math.min(min, r[k].max);
						avg += r[k].average;
					}
					avg /= 7.0;
					max = Math.max(max, min);
					average += avg / 7.0;
				} else {
					max = Math.max(max, 0.1 * (6 - difficulty));
					average += 0.1 * (6 - difficulty) / 7;
				}
			}
			res[selfmove] = new Pair(average, max);
		}
		return res;
	}

	/**
	 * Plays a move.
	 */

	private static int[][] processMove(int move, int[][] board, int k) {
		int[][] newboard = new int[6][7];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				newboard[i][j] = board[i][j];
			}
		}
		int i = 0;
		while (board[i][move] != 0) {
			i++;
		}
		newboard[i][move] = k;
		return newboard;
	}

	/**
	 * Prints the board so that you can see how badly you are losing.
	 * 
	 * @param board
	 */

	private static void printBoard(int[][] board) {
		for (int j = 0; j < 15; j++) {
			System.out.print("-");
		}
		System.out.println();
		for (int i = 5; i >= 0; i--) {
			System.out.print("|");
			for (int j = 0; j < 7; j++) {
				switch (board[i][j]) {
				case 1:
					System.out.print("◯|");
					break;
				case 2:
					System.out.print("●|");
					break;
				default:
					System.out.print(" |");
				}
			}
			System.out.println();
		}
		for (int j = 0; j < 15; j++) {
			System.out.print("-");
		}
		System.out.println();
		for (int j = 0; j < 7; j++) {
			System.out.print("|" + (j + 1));
		}
		System.out.println("|");
		for (int j = 0; j < 15; j++) {
			System.out.print("-");
		}
		System.out.println("\n");
	}

	/**
	 * Checks if the game is over. In other words, check if you have lost yet.
	 * 
	 * @return whether the game is over
	 */

	private static boolean gameEnd() {
		if (isWinner(1, board) || isWinner(2, board)) {
			return true;
		}
		for (int i = 0; i < 7; i++) {
			if (validMove(i, board)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether given row, column, or diagonal is a connected four of the
	 * given color.
	 * 
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 * @param i
	 * @param board
	 * @return whether it is a connected four
	 */
	private static boolean isConnectedFour(int x, int y, int dx, int dy, int i, int[][] board) {
		for (int k = 0; k < 4; k++) {
			if ((x < 0 || x >= 6 || y < 0 || y >= 7) || board[x][y] != i) {
				return false;
			}
			x += dx;
			y += dy;
		}
		return true;
	}

	/**
	 * Checks if given player has won. Obviously, I will always win.
	 * 
	 * @param p
	 * @param board
	 * @return whether player has won
	 */
	private static boolean isWinner(int p, int[][] board) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				for (int d = 0; d < 8; d++) {
					if (isConnectedFour(i, j, dx[d], dy[d], p, board)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Insults you if I have stopped you from connecting four. Obviously, this
	 * function runs very often.
	 */
	private static void taunt() {
		for (int i = 0; i < 7; i++) {
			if (!validMove(i, board)) {
				continue;
			}
			int[][] newboard = processMove(i, board, 1);
			if (isWinner(1, newboard)) {
				System.out.println("L bozo did you think i would fall for that?\n");
				return;
			}
		}
	}
}
