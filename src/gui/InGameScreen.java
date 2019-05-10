package gui;

import javafx.scene.Group;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.InGameTimer;
import logic.Main;
import logic.SpaceshipController;
import sprite.Asteroid;
import sprite.BigStar;
import sprite.EnemyBullet;
import sprite.EnemySpaceship;
import sprite.MediumStar;
import sprite.PlayerBullet;
import sprite.PlayerSpaceship;
import sprite.SmallStar;

public class InGameScreen extends Group {

	public InGameTimer inGameTimer;

	public InGameScreen() {

		super();

		Canvas canvas = new Canvas(Main.WIDTH, Main.HEIGHT);
		this.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		PlayerSpaceship player = new PlayerSpaceship(100, Main.HEIGHT/2, 0, 0);
		SpaceshipController spaceShipController = new SpaceshipController(player);

		inGameTimer = new InGameTimer(player, spaceShipController, gc);
		inGameTimer.start();
		
	}
	
	public static void clear() {
		PlayerBullet.getBulletList().clear();
		BigStar.getBigStarList().clear();
		MediumStar.getMediumStarList().clear();
		SmallStar.getSmallStarList().clear();
		Asteroid.getAsteroidList().clear();
		EnemySpaceship.getEnemySpaceShipList().clear();
		EnemyBullet.getBulletList().clear();
	}

}
