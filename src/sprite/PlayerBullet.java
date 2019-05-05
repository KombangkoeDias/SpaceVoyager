package sprite;

import java.util.ArrayList;

import javafx.application.Platform;
import logic.Main;

public class PlayerBullet extends Bullet implements PlayerWeapon {

	private static ArrayList<PlayerBullet> bulletList = new ArrayList<PlayerBullet>();
	private PlayerSpaceShip shooter;

	public PlayerBullet(double positionX, double positionY, double velocityX, double velocityY, PlayerSpaceShip shooter) {
		super(positionX, positionY, velocityX, velocityY);
		this.shooter = shooter;
		setImage(Main.loader.playerBeamImage);
	}
	
	@Override
	public void doDamage(Sprite sprite) {
		if (sprite instanceof EnemySpaceShip) {
			EnemySpaceShip enemy = (EnemySpaceShip) sprite;
			enemy.receivedDamage(shooter.getFirePower());
		}
	}

	@Override
	public Sprite checkCollide() {
		for (Asteroid asteroid : Asteroid.getAsteroidList()) {
			if (asteroid.intersects(this)) {
				return asteroid;
			}
		}
		for (EnemySpaceShip enemy : EnemySpaceShip.getEnemySpaceShipList()) {
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

	public PlayerSpaceShip getShooter() {
		return shooter;
	}

}
