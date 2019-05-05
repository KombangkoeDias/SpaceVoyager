package gui;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.Main;

public class GameOverScreen extends VBox{
	private int score;
	
	public GameOverScreen() {
		this.score = InGameScreen.inGameTimer.getScore();
		GameOver();
	}
	public void GameOver() {
		Text GameOverText = new Text("Game Over!");
		GameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 50));
		GameOverText.setTextAlignment(TextAlignment.CENTER);
		this.getChildren().add(GameOverText);
	}
	public void setButton() {
		GameButton button = new GameButton(300,500);
		button.seteffect();
		button.setText("Try Again");
		button.setTextFill(Color.AQUAMARINE);
		button.setOnAction(click -> {
			Main.main(null);
		});
		GameButton exitButton = new GameButton(300,600);
		exitButton.seteffect();
		exitButton.setText("Exit");
		exitButton.setTextFill(Color.RED);
		
	}
	
	
}
