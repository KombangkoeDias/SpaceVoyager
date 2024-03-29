package sprite;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.Main;

public class Explosion extends Sprite {
	
	private static ArrayList<Explosion> explosionList = new ArrayList<Explosion>();
	private int explosionCounter;
	private int frameX;
	private int frameY;
	private boolean finished;
	private boolean isPlayer;
	
	public Explosion(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
		this.explosionCounter = 0;
		this.finished = false;
		this.isPlayer = false;
	}
	
	public static void generateExplosion(Sprite sprite) {
		Explosion explosion = new Explosion(sprite.positionX, sprite.positionY, 0, 0);
		if (sprite instanceof PlayerSpaceship) {
			explosion.isPlayer = true;
		}
		explosionList.add(explosion);
		Main.loader.explosionSound.play();
	}
	
	public void explode() {
		Image explosionImage = Main.loader.explosionImage;
		WritableImage croppedExplosion = new WritableImage (explosionImage.getPixelReader(), 
				frameX * 125 + 30, frameY * 130 + 30, 64, 64);
		Main.inGameScreen.inGameTimer.gc.drawImage(croppedExplosion, positionX, positionY);
		explosionCounter++;
		if (explosionCounter >= 5) {
			frameX++;
			explosionCounter = 0;
		}
		if (frameX > 3) {
			frameY++;
			frameX = 0;
		}
		if (frameY > 3) {
			finished = true;
			this.disappear();
		}
	}
	
	public void disappear() {
		Explosion explosion = this;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				explosionList.remove(explosionList.indexOf(explosion));
			}
		});
	}

	public static ArrayList<Explosion> getExplosionList() {
		return explosionList;
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean isPlayer() {
		return isPlayer;
	}

}
