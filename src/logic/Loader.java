package logic;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Loader {
	
	public Image backgroundImage;
	public Image playerSpaceShipImage;
	public Image playerBeamImage;
	public Image asteroidImage;
	public Image explosionImage;
	public Image enemyShipBeige;
	public Image enemyShipBlue;
	public Image enemyShipGreen;
	public Image enemyShipPink;
	public Image enemyShipYellow;
	public Image enemyBulletBeige;
	public Image enemyBulletBlue;
	public Image enemyBulletGreen;
	public Image enemyBulletPink;
	public Image enemyBulletYellow;
	public Image bigStarImage;
	public Image mediumStarImage;
	public Image smallStarImage;
	public Image playerSecondShipImage;
	public Image playerThirdShipImage;
	public Image playerChosenSpaceShipImage;
	public AudioClip gamePlaySound;
	public AudioClip bigStarSound;
	public AudioClip mediumStarSound;
	public AudioClip smallStarSound;
	public AudioClip enemyBulletSound;
	public AudioClip explosionSound;
	
	public Loader() {
		
		this.backgroundImage = new Image("file:res/space.jpg");
		this.playerSpaceShipImage = new Image("file:res/spaceship0.png");
		this.playerBeamImage = new Image("file:res/beam.png");
		this.asteroidImage = new Image("file:res/asteroid.png");
		this.explosionImage = new Image("file:res/explosion.png");
		this.playerSecondShipImage = new Image("file:res/spaceship2.png");
		this.playerThirdShipImage = new Image("file:res/spaceship3.png");
		
		this.enemyShipBeige = new Image("file:res/shipBeige.png");
		this.enemyShipBlue = new Image("file:res/shipBlue.png");
		this.enemyShipGreen = new Image("file:res/shipGreen.png");
		this.enemyShipPink = new Image("file:res/shipPink.png");
		this.enemyShipYellow = new Image("file:res/shipYellow.png");
		
		this.enemyBulletBeige = new Image("file:res/bulletBeige.png");
		this.enemyBulletBlue = new Image("file:res/bulletBlue.png");
		this.enemyBulletGreen = new Image("file:res/bulletGreen.png");
		this.enemyBulletPink = new Image("file:res/bulletPink.png");
		this.enemyBulletYellow = new Image("file:res/bulletYellow.png");
		
		this.bigStarImage = new Image("file:res/big_star.png");
		this.mediumStarImage = new Image("file:res/medium_star.png");
		this.smallStarImage = new Image("file:res/small_star.png");
		
		this.gamePlaySound = new AudioClip(getClass().getClassLoader().getResource("music.mp3").toString());
		this.bigStarSound = new AudioClip(getClass().getClassLoader().getResource("bigStar.mp3").toString());
		this.mediumStarSound = new AudioClip(getClass().getClassLoader().getResource("mediumStar.mp3").toString());
		this.smallStarSound = new AudioClip(getClass().getClassLoader().getResource("smallStar.mp3").toString());
		this.enemyBulletSound = new AudioClip(getClass().getClassLoader().getResource("enemyBullet.mp3").toString());
		this.explosionSound = new AudioClip(getClass().getClassLoader().getResource("explosion.mp3").toString());
		
	}
	
}
