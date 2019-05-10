package logic;

import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sprite.Asteroid;
import sprite.BigStar;
import sprite.EnemyBullet;
import sprite.EnemySpaceship;
import sprite.Explosion;
import sprite.MediumStar;
import sprite.PlayerBullet;
import sprite.PlayerSpaceship;
import sprite.SmallStar;
import sprite.Sprite;

public class InGameTimer extends AnimationTimer {

	public long lastNanoTime;
	public PlayerSpaceship player;
	public SpaceshipController spaceShipController;
	public GraphicsContext gc;
	private Image spaceBackground;
	private int MinX;
	private int score;
	private int smStarCount;
	private int mdStarCount;
	private int bgStarCount;
	private int killCount;
	public AudioClip clip;

	public InGameTimer(PlayerSpaceship player, SpaceshipController spaceShipController, GraphicsContext gc) {
		super();
		this.lastNanoTime = System.nanoTime();
		this.player = player;
		this.spaceShipController = spaceShipController;
		this.gc = gc;
		this.spaceBackground = Main.loader.backgroundImage;
		this.MinX = 0;
		this.clip = Main.loader.inGameMusic;
	}

	@Override
	public void handle(long currentNanoTime) {
		
		// Check if music ends
		if (!clip.isPlaying()) {
			clip.setPriority(10);
			clip.play();
		}
		
		// Calculate time between sections
		double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
		lastNanoTime = currentNanoTime;

		// Prepare for drawing in this section
		gc.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);

		// Animate background
		WritableImage croppedSpace = new WritableImage(spaceBackground.getPixelReader(), MinX, 0, Main.WIDTH,
				Main.HEIGHT);
		gc.drawImage(croppedSpace, 0, 0);
		MinX = MinX + 5;
		if (MinX >= spaceBackground.getWidth() * 2 / 3) {
			MinX = 0;
		}

		// Player Bullet
		Iterator<PlayerBullet> bulletIter = PlayerBullet.getBulletList().iterator();
		while (bulletIter.hasNext()) {
			PlayerBullet bullet = bulletIter.next();
			bullet.update(elapsedTime);
			Sprite collidedSprite = bullet.checkCollide();
			if (bullet.checkOutOfScreen() || collidedSprite != null) {
				if (collidedSprite instanceof EnemySpaceship) {
					EnemySpaceship enemy = (EnemySpaceship) collidedSprite;
					bullet.doDamage(enemy);
					if (enemy.getRemainingHealth() <=0) {
						killCount++;
					}
				}
				bullet.disappear();
			} else {
				bullet.render(gc);
			}
		}
		
		// Star
		Iterator<BigStar> bigStarIter = BigStar.getBigStarList().iterator();
		while (bigStarIter.hasNext()) {
			BigStar bigStar = bigStarIter.next();
			bigStar.update(elapsedTime);
			Sprite collidedSprite = bigStar.checkCollide();
			if (bigStar.checkOutOfScreen() || collidedSprite != null) {
				if (collidedSprite == player) {
					score += BigStar.SCORE;
					Main.loader.bigStarSound.play();
					bgStarCount++;
				}
				bigStar.disappear();
			}
			else {
				bigStar.render(gc);
			}
		}
		Iterator<MediumStar> mediumStarIter = MediumStar.getMediumStarList().iterator();
		while (mediumStarIter.hasNext()) {
			MediumStar mediumStar = mediumStarIter.next();
			mediumStar.update(elapsedTime);
			Sprite collidedSprite = mediumStar.checkCollide();
			if (mediumStar.checkOutOfScreen() || collidedSprite != null) {
				if (collidedSprite == player) {
					score += MediumStar.SCORE;
					Main.loader.mediumStarSound.play();
					mdStarCount++;
				}
				mediumStar.disappear();
			}
			else {
				mediumStar.render(gc);
			}
		}
		Iterator<SmallStar> smallStarIter = SmallStar.getSmallStarList().iterator();
		while (smallStarIter.hasNext()) {
			SmallStar smallStar = smallStarIter.next();
			smallStar.update(elapsedTime);
			Sprite collidedSprite = smallStar.checkCollide();
			if (smallStar.checkOutOfScreen() || collidedSprite != null) {
				if (collidedSprite == player) {
					score += SmallStar.SCORE;
					Main.loader.smallStarSound.play();
					smStarCount++;
				}
				smallStar.disappear();
			}
			else {
				smallStar.render(gc);
			}
		}
		

		// Asteroid
		if (Math.random() < 0.03 && Asteroid.getAsteroidList().size() < 15) {
			Asteroid.generateAsteroid();
		}
		Iterator<Asteroid> asteroidIter = Asteroid.getAsteroidList().iterator();
		while (asteroidIter.hasNext()) {
			Asteroid asteroid = asteroidIter.next();
			asteroid.update(elapsedTime);
			if (asteroid.checkOutOfScreen()) {
				asteroid.disappear();
			} else {
				asteroid.render(gc);
			}
			if (asteroid.intersects(player)) {
				asteroid.doDamage(player);
			}
		}

		// Enemy
		if (Math.random() < 0.005 - EnemySpaceship.getEnemySpaceShipList().size() * 0.001
				&& EnemySpaceship.getEnemySpaceShipList().size() < 5) {
			EnemySpaceship.generateEnemy();
		}
		Iterator<EnemySpaceship> enemyIter = EnemySpaceship.getEnemySpaceShipList().iterator();
		while (enemyIter.hasNext()) {
			EnemySpaceship enemy = enemyIter.next();
			enemy.update(elapsedTime);
			if (!enemy.isStopped() && enemy.positionX < enemy.getStopPositionX()) {
				enemy.stop();
			}
			enemy.render(gc);
			if (enemy.intersects(player)) {
				enemy.doDamage(player);
			}
			if (enemy.isStopped() && Math.random() < 0.0005) {
				enemy.moveAgain();
			}
			enemy.bulletTimeCount++;
			if (enemy.bulletTimeCount > 300 && enemy.isStopped() && !enemy.isMoveAgain()) {
				enemy.fireBullet();
				enemy.bulletTimeCount = 0;
			}
			
			// Enemy's HP Bar
			gc.setFill(Color.RED);
			gc.fillRect(enemy.positionX, enemy.positionY+70, enemy.getWidth(), 10);
			gc.setFill(Color.GREEN);
			gc.fillRect(enemy.positionX, enemy.positionY+70, 
					(double) enemy.getRemainingHealth()/(double)enemy.getMaxHealth()*enemy.getWidth(), 10);
		}

		// Enemy Bullet
		Iterator<EnemyBullet> enemybulletIter = EnemyBullet.getBulletList().iterator();
		while (enemybulletIter.hasNext()) {
			EnemyBullet bullet = enemybulletIter.next();
			bullet.update(elapsedTime);
			Sprite collidedSprite = bullet.checkCollide();
			if (bullet.checkOutOfScreen() || collidedSprite != null) {
				if (collidedSprite == player) {
					bullet.doDamage(player);
				}
				bullet.disappear();
			}
			else {
				bullet.render(gc);
			}
		}

		// Explosion
		Iterator<Explosion> explosionIter = Explosion.getExplosionList().iterator();
		while (explosionIter.hasNext()) {
			Explosion explosion = explosionIter.next();
			explosion.explode();
			if (explosion.isPlayer() && explosion.isFinished()) {
				player.reduceHealth();
				if (player.getHealth() < 0) {
					clip.stop();
					Main.gotoScoreBoard();
				}
				else {
					player.revive();
				}
			}
		}
		
		// Player
		spaceShipController.handleInput();
		player.update(elapsedTime);
		if (player.isVisible()) {
			player.render(gc);
		}
		
		// Life
		gc.setFont(Font.font(28));
		if (player.isAlive()) {
			gc.setFill(Color.WHITE);
		}
		else {
			gc.setFill(Color.RED);
		}
		gc.fillText("LIFE    :    " + player.getHealth(), 50, Main.HEIGHT - 50);

		// Score
		gc.setFill(Color.WHITE);
		String scoreText;
		if (score < 10) {
			scoreText = "00000" + score;
		}
		else if (score < 100) {
			scoreText = "0000" + score;
		}
		else if (score < 1000) {
			scoreText = "000" + score;
		}
		else if (score < 10000) {
			scoreText = "00" + score;
		}
		else if (score < 100000) {
			scoreText = "0" + score;
		}
		else if (score < 1000000) {
			scoreText = Integer.toString(score);
		}
		else {
			scoreText = "999999";
		}
		gc.fillText(scoreText, 50, 50);

	}

	public int getScore() {
		return score;
	}
	public int getsmCount(){
		return smStarCount;
	}
	public int getmdCount() {
		return mdStarCount;
	}
	public int getbgCount() {
		return bgStarCount;
	}
	public int getKill() {
		return killCount;
	}

}
