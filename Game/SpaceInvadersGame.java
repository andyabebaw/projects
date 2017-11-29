import org.biojava.bio.structure.align.util.SynchronizedOutFile;

import lab10.ArcadeKeys;
import sedgewick.StdDraw;

public class SpaceInvadersGame {
	public static int lives = 5;
	public static boolean alive = true;
	public static int a = 0;
	public static player p1 = new player();
	public static enemy aliens[] = new enemy[8];
	public static enemy queen = new enemy(.5, .85, true);
	public static double curr = .25;
	public static double height = .8;
	public static int score = 0;
	public static bullet bullets[] = new bullet[30];
	public static int bCount = 0;
	public static bullet b;
	public static boolean reload=true;
	public static int timer=0;
	public static int queenDead=0;
	public static int scoreCount=0;

	public static void runGame() {

		StdDraw.clear();
		StdDraw.setXscale(0, 1.0);
		StdDraw.setYscale(0, 1.0);
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.filledSquare(.5, .5, .5);

		int keys[] = { ArcadeKeys.KEY_UP, ArcadeKeys.KEY_DOWN, ArcadeKeys.KEY_LEFT, ArcadeKeys.KEY_RIGHT };
		for (int i = 0; i < keys.length; i++) {
			if (ArcadeKeys.isKeyPressed(0, keys[i])) {
				p1.move(i);
			}
		}
		// bullets

		if (ArcadeKeys.isKeyPressed(0, ArcadeKeys.KEY_UP)) {
			if(reload){
			bullets[bCount] = new bullet(p1.getx(), p1.gety());
			bCount++;
			reload=false;}
			

		}
		
		for (int k = 0; k < bCount; k++) {
			if (!(bullets[k] == null)) {
			
			if (!bullets[k].isDead()) {
				bullets[k].move(0);
				bullets[k].draw();
				
			}}
		}

		for (int j = 0; j < aliens.length; j++) {
			if (!aliens[j].isDead()) {
				aliens[j].move(0);
				aliens[j].draw();
			}
		}
		p1.draw();
		queen.move(0);
		queen.draw();

		for (int i = 0; i < aliens.length; i++) {
			p1.crashCheck(aliens[i]);
		}
		
		for(int k=0; k<bCount;k++){
		for (int i = 0; i < aliens.length; i++) {
			if(bullets[k].crashCheck(aliens[i])){
			
				score+=50;
			
		}}
		bullets[k].crashCheck(queen);
		if (queen.isDead()){
			queenDead++;
			if (queenDead==1){
				score+=100;}
			}
		}
		p1.crashCheck(queen);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(.2, .9, "Lives: " + (lives - p1.getdeathCount()));
		StdDraw.text(.5, .9, "Bullets left: " + (bullets.length-bCount));
		StdDraw.text(.8, .9, "Score: " + score);
		StdDraw.show(20);
		a++;
		if (p1.getdeathCount() == lives) {
			StdDraw.clear();
			alive = false;

		}
		boolean allDead=true;
		for (int i=0;i<aliens.length;i++){
			if (aliens[i].isDead()==false){
				allDead=false;
			}
		}
		if (allDead&&queen.isDead()){
			bCount=0;
			for (int j = 0; j < aliens.length; j++) {
				aliens[j].reset();
				}
			queen.reset();
			p1.reset();
			}
		
		
		if(!reload){
			if(timer!=35){
				timer++;
			}
			else {
				reload=true;
				timer=0;
			}
		}
	}

	public static void reset() {
		StdDraw.clear();
		alive = true;
		curr = .25;
		height = .8;
		score = 0;
		bullets=new bullet[30];
		bCount=0;
		for (int j = 0; j < aliens.length; j++) {
			aliens[j] = new enemy(.08 * j + .23, .8 - .04, false);

		}
		for (int j = 0; j < aliens.length; j++) {
			aliens[j].speed = aliens[j].speed * 1.25;

		}
		queen.speed = queen.speed * 1.2;
		queen = new enemy(.5, .85, true);
		p1 = new player();
	}

	public static void postResults() {
		StdDraw.clear();
		StdDraw.setXscale(0, 1.0);
		StdDraw.setYscale(0, 1.0);
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.filledSquare(.5, .5, .5);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(.5, .6, "GAME OVER");
		StdDraw.text(.5, .5, "Final Score: " + score);
		StdDraw.show(5000);
	}

	public static void main(String[] args) {

		for (int j = 0; j < aliens.length; j++) {
			aliens[j] = new enemy(.08 * j + .23, .8 - .04, false);

		}
		while (true) {
			while (alive) {
				runGame();
			}
			postResults();
			reset();
		}
	}
}
