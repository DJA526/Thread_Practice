package threads;

import org.jointheleague.graphical.robot.Robot;

public class ThreadDemo {
	public static void main(String[] args) {
			Robot timmy = new Robot(400, 700);
			Robot tammy = new Robot(800, 700);
			Robot sammy = new Robot(1200, 700);
			
			timmy.setSpeed(10);
			tammy.setSpeed(10);
			sammy.setSpeed(10);
			
			timmy.move(400);
			tammy.move(400);
			sammy.move(400);
			
			timmy.moveTo(400, 700);
			tammy.moveTo(800, 700);
			sammy.moveTo(1200, 700);
			
			new Thread(()->timmy.move(400)).start();
			new Thread(()->tammy.move(400)).start();
			new Thread(()->{
				sammy.move(400);
				
			}).start();
			
	}
}
