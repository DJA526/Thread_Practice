package threads;

import java.awt.Color;

import org.jointheleague.graphical.robot.Robot;

public class OlympicRings_Threaded {
	// Make A Program that uses Threads and robots to draw the Olympic rings. One robot should draw one ring simultaneously with the other 4 robots.
	
	public static void main(String[] args) {
		Robot a = new Robot(200, 400);
		Robot b = new Robot(400, 600);
		Robot c = new Robot(600, 400);
		Robot d = new Robot(800, 600);
		Robot e = new Robot(1000, 400);
		
		a.setSpeed(10);
		b.setSpeed(10);
		c.setSpeed(10);
		d.setSpeed(10);
		e.setSpeed(10);
		
		a.penDown();
		b.penDown();
		c.penDown();
		d.penDown();
		e.penDown();
		
		a.setPenColor(Color.blue);
		b.setPenColor(Color.yellow);
		c.setPenColor(Color.black);
		d.setPenColor(Color.green);
		e.setPenColor(Color.red);
		
		new Thread(()->drawCircle(a)).start();
		new Thread(()->drawCircle(b)).start();
		new Thread(()->drawCircle(c)).start();
		new Thread(()->drawCircle(d)).start();
		new Thread(()->drawCircle(e)).start();
		
	}
	
	public static void drawCircle (Robot r) {
		for (int i = 0; i < 360; i++) {
			r.move(3);
			r.turn(1);
		}
	}

}

