package sprite;

import java.util.ArrayList;

import javafx.application.Platform;
import logic.Main;

public class EnemyBullet extends Bullet implements EnemyWeapon {
	
	private static ArrayList<EnemyBullet> bulletList = new ArrayList<EnemyBullet>();
	private EnemySpaceship shooter;
	private static final int BEIGE = 0;
	private static final int BLUE = 1;
	private static final int GREEN = 2;
	private static final int PINK = 3;
	private static final int YELLOW = 4;

	public EnemyBullet(double positionX, double positionY, double velocityX, double velocityY, EnemySpaceship shooter) {
		super(positionX, positionY, velocityX, velocityY);
		this.shooter = shooter;
		switch(shooter.getType()) {
			case BEIGE:
				setImage(Main.loader.enemyBulletBeige);
				break;
			case BLUE:
				setImage(Main.loader.enemyBulletBlue);
				break;
			case GREEN:
				setImage(Main.loader.enemyBulletGreen);
				break;
			case PINK:
				setImage(Main.loader.enemyBulletPink);
				break;
			case YELLOW:
				setImage(Main.loader.enemyBulletYellow);
				break;
		}
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
	public Sprite checkCollide() {
		if (this.intersects(Main.inGameScreen.inGameTimer.player) && Main.inGameScreen.inGameTimer.player.isVisible()) {
			return Main.inGameScreen.inGameTimer.player;
		}
		return null;
	}

	@Override
	public void disappear() {
		Bullet bullet = this;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				EnemyBullet.bulletList.remove(EnemyBullet.bulletList.indexOf(bullet));
			}
		});
	}

	public static ArrayList<EnemyBullet> getBulletList() {
		return bulletList;
	}

	public EnemySpaceship getShooter() {
		return shooter;
	}

}
