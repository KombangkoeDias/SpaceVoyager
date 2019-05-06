package sprite;

import javafx.application.Platform;
import logic.Main;

public class PlayerSpaceShip extends SpaceShip {
	
	private int health;
	private int firePower;
	private int fireRate;
	private int speed;
	public int bulletTimeCount = 0;
	private boolean controllable;
	private boolean visible;
	private boolean alive;

	public PlayerSpaceShip(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
		this.health = 2;
		this.firePower = 10;
		this.fireRate = 10;
		this.speed = 300;
		this.controllable = true;
		this.visible = true;
		this.alive = true;
		setImage(Main.loader.playerChosenSpaceShipImage);
		if (Main.loader.playerChosenSpaceShipImage.equals(Main.loader.playerSpaceShipImage)) {
			this.firePower = 15;
			this.fireRate = 10;
			this.health = 2;
		}
		else if (Main.loader.playerChosenSpaceShipImage.equals(Main.loader.playerSecondShipImage)) {
			this.firePower = 10;
			this.fireRate = 15;
			this.health = 2;
		}
		else if (Main.loader.playerChosenSpaceShipImage.equals(Main.loader.playerThirdShipImage)) {
			this.firePower = 10;
			this.fireRate = 10;
			this.health = 3;
		}
	}

	@Override
	public void update(double time) {

		positionX += velocityX * time;
		positionY += velocityY * time;
		if (this.positionX < 0) {
			this.positionX = 0;
		}
		if (this.positionX + this.getWidth() > Main.WIDTH) {
			this.positionX = Main.WIDTH - this.getWidth();
		}
		if (this.positionY < 0) {
			this.positionY = 0;
		}
		if (this.positionY + this.getHeight() > Main.HEIGHT) {
			this.positionY = Main.HEIGHT - this.getHeight();
		}

	}
	
	public void receivedDamage(int damage) {
		health = health - damage;
		Explosion.generateExplosion(this);
		controllable = false;
		visible = false;
		alive = false;
	}
	
	public void revive() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				PlayerBullet.getBulletList().clear();
				BigStar.getBigStarList().clear();
				MediumStar.getMediumStarList().clear();
				SmallStar.getSmallStarList().clear();
				Asteroid.getAsteroidList().clear();
				EnemySpaceShip.getEnemySpaceShipList().clear();
				EnemyBullet.getBulletList().clear();
			}
		});
		this.positionX = 100;
		this.positionY = Main.HEIGHT/2;
		this.controllable = true;
		this.visible = true;
		this.alive = true;
	}

	public int getHealth() {
		return health;
	}

	public int getFirePower() {
		return firePower;
	}

	public int getFireRate() {
		return fireRate;
	}

	public int getSpeed() {
		return speed;
	}

	public boolean isControllable() {
		return controllable;
	}

	public boolean isVisible() {
		return visible;
	}
	
	public boolean isAlive() {
		return alive;
	}

}
