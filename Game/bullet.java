import sedgewick.StdDraw;

public class bullet implements Ship {
	private final double startX;
	private final double startY;
	private double currX;
	private double currY;
	public final double size = .01;
	private double speed = .02;
	public boolean active = true;

	public bullet(double startX, double startY) {
		this.startX = startX;
		this.startY = startY;
		this.currX = startX;
		this.currY = startY;

	}
	public double getx() {
		return currX;
	}

	public double gety() {
		return currY;
	}
	public void move(int key) {
		if (active) {
			currY = currY + speed;
		}

	}
	public boolean isDead() {
		return !active;
	}
	public void kill(){
		active=false;
	}
	public boolean crashCheck(Ship a){
		if ((Math.pow((Math.pow(gety()-a.gety(), 2)+Math.pow(getx()-a.getx(),2 )), .5)<2*(Math.pow(Math.pow(size, 2)+Math.pow(a.size, 2), .5)))&&(a.isDead()!=true)&&(active)){
		a.kill();
		active=false;
		return true;
		}
		return false;
	}
	public void draw() {
		if (active) {
			
			
				StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.filledSquare(currX, currY, size);
			
		}
	}
}
