package sprite;

import gui.InGameScreen;
import javafx.application.Platform;
import logic.Main;

public class PlayerSpaceship extends Spaceship {
	
	private int life;
	private int firePower;
	private int fireRate;
	private int speed;
	public int bulletTimeCount;
	private boolean controllable;
	private boolean visible;
	private boolean alive;

	public PlayerSpaceship(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
		this.speed = 300;
		this.controllable = true;
		this.visible = true;
		this.alive = true;
		bulletTimeCount = 0;
		setImage(Main.loader.playerChosenSpaceShipImage);
		if (Main.loader.playerChosenSpaceShipImage.equals(Main.loader.playerFirstShipImage)) {
			this.firePower = 20;
			this.fireRate = 3;
			this.life = 2;
		}
		else if (Main.loader.playerChosenSpaceShipImage.equals(Main.loader.playerSecondShipImage)) {
			this.firePower = 5;
			this.fireRate = 10;
			this.life = 2;
		}
		else if (Main.loader.playerChosenSpaceShipImage.equals(Main.loader.playerThirdShipImage)) {
			this.firePower = 6;
			this.fireRate = 6;
			this.life = 3;
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
	
	public void reduceLife() {
		life = life - 1;
	}
	
	public void die() {
		Explosion.generateExplosion(this);
		controllable = false;
		visible = false;
		alive = false;
	}
	
	public void revive() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				InGameScreen.clear();
			}
		});
		this.positionX = 100;
		this.positionY = Main.HEIGHT/2;
		this.controllable = true;
		this.visible = true;
		this.alive = true;
	}

	public int getLife() {
		return life;
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
