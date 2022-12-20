import java.util.LinkedList;

public class Snake {
	private LinkedList<Pair> snake;
	private boolean dead;
	private int height, width;
	private int vx, vy;
	private Apple apple;
	private boolean grow;

	public Snake(int height, int width, Apple apple) {
		this.height = height;
		this.width = width;
		snake = new LinkedList<Pair>();
		this.apple = apple;
		reset();
	}

	public void reset() {
		snake.clear();
		snake.addFirst(new Pair(height / 2, width / 2));
		snake.addFirst(new Pair(height / 2, width / 2 + 1));
		snake.addFirst(new Pair(height / 2, width / 2 + 2));
		vy = 1;
		vx = 0;
		dead = false;
		grow = false;
	}

	public boolean draw(double x, double y) {
		for (Pair i : snake) {
			if (((int) i.getX()-x)*((int) i.getX()-x) + ((int) i.getY()-y)*((int) i.getY()-y) < 1) {
				return true;
			}
		}
		return false;
	}

	public void move(double speed) {
		if(!grow) {
			snake.removeLast();
		}
		else {
			grow = false;
		}
		Pair p = new Pair(snake.getFirst().getX() + vx * speed, snake.getFirst().getY() + vy * speed);
		for (Pair i : snake) {
			if (i.getX() == p.getX() && i.getY() == p.getY()) {
				dead = true;
			}
		}
		if (p.getX() >= height || p.getX() < 0 || p.getY() >= width || p.getY() < 0) {
			dead = true;
		}
		snake.addFirst(p);
	}

	public void handleInput(String input) {
		switch (input) {
		case "Up":
			if (vx != 1) {
				vy = 0;
				vx = -1;
			}
			return;
		case "Down":
			if (vx != -1) {
				vy = 0;
				vx = 1;
			}
			return;
		case "Right":
			if (vy != -1) {
				vy = 1;
				vx = 0;
			}
			break;
		case "Left":
			if (vy != 1) {
				vy = -1;
				vx = 0;
			}
			return;
		}
	}

	public boolean getDead() {
		return dead;
	}

	public void grow() {
		grow = true;
	}
}
