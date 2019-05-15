package sprite;

import java.util.ArrayList;

import javafx.application.Platform;
import logic.Main;

public class PlayerBullet extends Bullet {

	private static ArrayList<PlayerBullet> bulletList = new ArrayList<PlayerBullet>();
	private PlayerSpaceship shooter;

	public PlayerBullet(double positionX, double positionY, double velocityX, double velocityY, PlayerSpaceship shooter) {
		super(positionX, positionY, velocityX, velocityY);
		this.shooter = shooter;
		setImage(Main.loader.playerBeamImage);
	}
	
	@Override
	public void doDamage(Sprite sprite) {
		if (sprite instanceof EnemySpaceship) {
			EnemySpaceship enemy = (EnemySpaceship) sprite;
			enemy.receiveDamage(shooter.getFirePower());
		}
	}

	@Override
	public Sprite checkCollide() {
		for (Asteroid asteroid : Asteroid.getAsteroidList()) {
			if (asteroid.intersects(this)) {
				return asteroid;
			}
		}
		for (EnemySpaceship enemy : EnemySpaceship.getEnemySpaceShipList()) {
			if (enemy.intersects(this)) {
				return enemy;
			}
		}
		return null;
	}

	@Override
	public void disappear() {
		PlayerBullet bullet = this;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				PlayerBullet.bulletList.remove(PlayerBullet.bulletList.indexOf(bullet));
			}
		});
	}
	
	public static ArrayList<PlayerBullet> getBulletList() {
		return bulletList;
	}

	public PlayerSpaceship getShooter() {
		return shooter;
	}

}
