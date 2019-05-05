package gui;

import javafx.scene.Group;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.InGameTimer;
import logic.Main;
import logic.SpaceShipController;
import sprite.PlayerSpaceShip;

public class InGameScreen extends Group {

	public static InGameTimer inGameTimer;

	public InGameScreen() {

		super();

		Canvas canvas = new Canvas(Main.WIDTH, Main.HEIGHT);
		this.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		PlayerSpaceShip player = new PlayerSpaceShip(100, Main.HEIGHT/2, 0, 0);
		SpaceShipController spaceShipController = new SpaceShipController(player);
		
		

		inGameTimer = new InGameTimer(player, spaceShipController, gc);
		inGameTimer.clip.play();
		inGameTimer.start();
	}
	public static void checkMusic() {
		if (!Main.loader.gamePlaySound.isPlaying() && !inGameTimer.isend)
			Main.loader.gamePlaySound.play();
	}

}
