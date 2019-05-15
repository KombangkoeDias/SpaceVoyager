package sprite;

import java.util.ArrayList;

import javafx.application.Platform;
import logic.Main;

public class EnemySpaceship extends Spaceship implements Enemy {
	
	private static ArrayList<EnemySpaceship> enemySpaceShipList = new ArrayList<EnemySpaceship>();
	private int maxHealth;
	private int remainingHealth;
	private double stopPositionX;
	private boolean stopped;
	private boolean moveAgain;
	private int type;
	public int bulletTimeCount;
	public static final int BEIGE = 0;
	public static final int BLUE = 1;
	public static final int GREEN = 2;
	public static final int PINK = 3;
	public static final int YELLOW = 4;

	public EnemySpaceship(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
		this.maxHealth = (int) (Math.random() * 100 + 100);
		this.remainingHealth = this.maxHealth;
		this.stopPositionX = Main.WIDTH - (Math.random() * 200 + 100);
		this.stopped = false;
		this.moveAgain = false;
		this.bulletTimeCount = 0;
	}

	public static void generateEnemy() {
		
		double positionY = Math.random() * (Main.HEIGHT - 80);
		double velocityX = -(Math.random() * 200 + 100);
		EnemySpaceship enemy = new EnemySpaceship(Main.WIDTH, positionY, velocityX, 0);
		enemy.type = (int) (Math.random() * 5);
		switch(enemy.type) {
			case BEIGE:
				enemy.setImage(Main.loader.enemyShipBeige);
				break;
			case BLUE:
				enemy.setImage(Main.loader.enemyShipBlue);
				break;
			case GREEN:
				enemy.setImage(Main.loader.enemyShipGreen);
				break;
			case PINK:
				enemy.setImage(Main.loader.enemyShipPink);
				break;
			case YELLOW:
				enemy.setImage(Main.loader.enemyShipYellow);
				break;
		}
		enemySpaceShipList.add(enemy);
			
	}
	
	public void fireBullet() {
		EnemyBullet bullet = new EnemyBullet 
				(positionX + this.getWidth()/2, positionY + this.getHeight()/2, -1000, 0, this);
		EnemyBullet.getBulletList().add(bullet);
		Main.loader.enemyBulletSound.play();
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
	
	public void receiveDamage(int damage) {
		remainingHealth = remainingHealth - damage;
		if (remainingHealth <= 0) {
			Explosion.generateExplosion(this);
			Star.generateStar(this);
			this.disappear();
		}
	}
	
	@Override
	public boolean checkOutOfScreen() {
		return (this.positionX + this.getWidth() < 0 || this.positionY + this.getHeight() < 0
				|| this.positionY > Main.HEIGHT);
	}
	
	@Override
	public void disappear() {
		EnemySpaceship enemy = this;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				enemySpaceShipList.remove(enemySpaceShipList.indexOf(enemy));
			}
		});
	}
	
	public void stop() {
		velocityX = 0;
		stopped = true;
	}
	
	public void moveAgain() {
		velocityX = -300;
		moveAgain = true;
	}
	
	public static ArrayList<EnemySpaceship> getEnemySpaceShipList() {
		return enemySpaceShipList;
	}

	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getRemainingHealth() {
		return remainingHealth;
	}

	public double getStopPositionX() {
		return stopPositionX;
	}

	public int getType() {
		return type;
	}

	public boolean isStopped() {
		return stopped;
	}

	public boolean isMoveAgain() {
		return moveAgain;
	}

}
