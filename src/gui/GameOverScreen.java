package gui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.Main;

public class GameOverScreen extends Pane {
	
	private int score;
	private int smCount;
	private int mdCount;
	private int bgCount;
	private int killCount;

	public GameOverScreen() {
		this.score = Main.inGameScreen.inGameTimer.getScore();
		this.smCount = Main.inGameScreen.inGameTimer.getsmCount();
		this.mdCount = Main.inGameScreen.inGameTimer.getmdCount();
		this.bgCount = Main.inGameScreen.inGameTimer.getbgCount();
		this.killCount = Main.inGameScreen.inGameTimer.getKill();
		GameOver();
		setResultText();
		try {
			setButton();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setStyle("-fx-background-image: url('file:res/startSpace.jpg')");
		MainMenu.audio.play();
	}

	public void GameOver() {
		Text GameOverText = new Text("Game Over!");
		GameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 70));
		GameOverText.setTextAlignment(TextAlignment.CENTER);
		GameOverText.setLayoutX(450);
		GameOverText.setLayoutY(80);
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(1.0f, 0f, 0f));
		InnerShadow is = new InnerShadow();
		is.setOffsetX(5.0f);
		is.setOffsetY(5.0f);
		GameOverText.setFill(Color.WHITE);
		GameOverText.setEffect(is);
		GameOverText.setEffect(ds);
		this.getChildren().add(GameOverText);
	}

	public void setResultText() {
		String Score = "Your score is: " + this.score;
		Text largeScore = new Text(Score);
		String smText = "Small stars collected: " + this.smCount;
		String mdText = "Medium stars collected: " + this.mdCount;
		String bgText = "Big stars collected: " + this.bgCount;
		String kill = "Enemy defeated: " + this.killCount;
		Text score = new Text(smText + "\n" + mdText + "\n" + bgText + "\n" + kill);
		score.setLineSpacing(20);
		largeScore.setFill(Color.WHITE);
		score.setFill(Color.WHITE);
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(1.0f, 0f, 0f));
		InnerShadow is = new InnerShadow();
		is.setOffsetX(5.0f);
		is.setOffsetY(5.0f);
		largeScore.setEffect(is);
		largeScore.setEffect(ds);
		largeScore.setLayoutX(450);
		largeScore.setLayoutY(180);
		largeScore.setFont(Font.font("Arial", FontWeight.BOLD, 50));
		this.getChildren().add(largeScore);
		score.setEffect(is);
		score.setEffect(ds);
		score.setLayoutX(500);
		score.setLayoutY(300);
		score.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 30));
		this.getChildren().add(score);
	}

	public void setButton() throws Exception {
		
		 GameButton button = new GameButton(535,500);
		 button.seteffect();
		 button.setText("Try Again"); button.setTextFill(Color.AQUAMARINE);
		 button.setPrefSize(200, 20);
		 button.setStyle("-fx-background-color: #7FFFD4");
		 button.setTextFill(Color.GRAY); button.setFont(Font.font("Arial",
		 FontWeight.LIGHT, 30));
		 button.setOnAction(click -> {
			MainMenu.audio.stop();
			Main.goToGame();
		 });
		 this.getChildren().add(button);
		 
		GameButton exitButton = new GameButton(560, 600);
		exitButton.seteffect();
		exitButton.setText("Exit");
		exitButton.setTextFill(Color.BLACK);
		exitButton.setPrefSize(150, 20);
		exitButton.setStyle("-fx-background-color: #FF0000");
		exitButton.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 30));
		exitButton.setOnAction(click -> {
			MainMenu.audio.stop();
			System.exit(1);
		});
		this.getChildren().add(exitButton);
	}
}
