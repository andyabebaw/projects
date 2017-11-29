import java.awt.Color;

import sedgewick.StdDraw;

public class enemy implements Ship {
	private final double startX;
	private final double startY;
	private double currX;
	private double currY;
	private boolean isqueen;
	private double direction = 1;
	private boolean isDead = false;
	public final double size = .02;
	double speed=.008;

	public enemy(double startX, double startY, boolean isqueen) {
		this.startX = startX;
		this.startY = startY;
		this.currX = startX;
		this.currY = startY;
		this.isqueen = isqueen;

	}
	
	public void reset() {
		currX = startX;
		currY = startY;
		isDead=false;
		speed=.008;
	}

	public double getx() {
		return currX;
	}

	public double gety() {
		return currY;
	}

	public void move(int key) {
		if(!isDead){
		if (isqueen) {
			if (Math.abs(currX - startX) > .4) {
				direction++;
			}
			currX = currX + 1.5*speed * Math.pow(-1.0, direction);
		}
		if (!isqueen) {
			if (Math.abs(currX - startX) > .18) {
				currY = currY - size;
				direction++;
			}
			currX = currX + speed * Math.pow(-1.0, direction);
		}}
	}

	public boolean isDead() {
		return isDead;
	}
	public void kill(){
		isDead=true;
	}

	public void draw() {
		if (!isDead) {
			if (!isqueen){
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledSquare(currX, currY, size);}
			else {
				StdDraw.setPenColor(StdDraw.YELLOW);
				StdDraw.filledSquare(currX, currY, size);
			}
		}
	}

}
