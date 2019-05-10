package sprite;

import java.util.ArrayList;

import javafx.application.Platform;
import logic.Main;

public class Asteroid extends Sprite implements Enemy {

	private static ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();

	public Asteroid(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
		setImage(Main.loader.asteroidImage);
	}

	public static void generateAsteroid() {
		double asteroidPositionY = Math.random() * (Main.HEIGHT - 64);
		double asteroidVelocityX = -(Math.random() * 200 + 300);
		double asteroidVelocityY = (Math.random() * 200) - 100;
		Asteroid asteroid = new Asteroid(Main.WIDTH, asteroidPositionY, asteroidVelocityX, asteroidVelocityY);
		asteroidList.add(asteroid);
	}
	
	@Override
	public void doDamage(Sprite sprite) {
		if (sprite instanceof PlayerSpaceship) {
			PlayerSpaceship player = (PlayerSpaceship) sprite;
			if (player.isAlive()) {
				player.die();
			}
		}
	}

	@Override
	public boolean checkOutOfScreen() {
		return (this.positionX + this.getWidth() < 0 || this.positionY + this.getHeight() < 0
				|| this.positionY > Main.HEIGHT);
	}
	
	@Override
	public void disappear() {
		Asteroid asteroid = this;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				asteroidList.remove(asteroidList.indexOf(asteroid));
			}
		});
	}

	public static ArrayList<Asteroid> getAsteroidList() {
		return asteroidList;
	}

}
