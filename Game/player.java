import sedgewick.StdDraw;

public class player implements Ship {
	private final double startX = .5;
	private final double startY = .1;
	private double currX;
	private double currY;
	public final double size = .03;
	private int deathCount=0;
	

	public player() {
		currX = startX;
		currY = startY;
	}

	public double getx() {
		return currX;
	}

	public double gety() {
		return currY;
	}
	public int getdeathCount() {
		return deathCount;
	}
	public void move(int key) {
		switch (key) {
		/*
		case 0:
			if ((1 - currY) <= 2*size) {

			} else {
				currY = currY + .02;
			}

			break;

		case 1:
			if ((currY) <= 2*size) {

			} else {
				currY = currY - .02;
			}
			break;
			*/
		case 2:
			if ((currX) <= 2*size) {

			} else {
				currX = currX - .02;
			}
			break;
		case 3:
			if ((1 - currX) <= 2*size) {

			}else{
			currX = currX + .02;}
			break;
			
		}
	}

	public void reset() {
		currX = startX;
		currY = startY;
	}
	public void crashCheck(Ship a){
		if (Math.pow((Math.pow(gety()-a.gety(), 2)+Math.pow(getx()-a.getx(),2 )), .5)<(Math.pow(Math.pow(size, 2)+Math.pow(a.size, 2), .5))*2&&(a.isDead()==false)){
		deathCount++;
		reset();
		}
	}
	public void kill(){
	}
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledSquare(currX, currY, size);
	}

}
