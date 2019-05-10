package gui;

import java.util.ArrayList;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.Main;

public class MainMenu extends Pane {
	private GameButton StartButton;
	private GameButton ExitButton;
	public static AudioClip audio = Main.loader.menuMusic;
	
	public MainMenu() {
		
		this.setStyle("-fx-background-image: url('file:res/startSpace.jpg')");
		this.setPrefHeight(Main.HEIGHT);
		this.setPrefWidth(Main.WIDTH);
		StartButton = new GameButton(550, 400);
		ExitButton = new GameButton(550, 500);
		StartButton.setText("Start");
		ExitButton.setText("Exit");
		StartButton.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		ExitButton.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 30));
		StartButton.setTextFill(Color.GREY);
		ExitButton.setTextFill(Color.BLACK);
		StartButton.setStyle("-fx-background-color: #7fffd4");
		ExitButton.setStyle("-fx-background-color: #FF0000");
		StartButton.setPrefSize(150, 20);
		ExitButton.setPrefSize(150, 20);
		this.getChildren().add(StartButton);
		this.getChildren().add(ExitButton);
		setUpButtons();
		Image logo = new Image("file:res/SpaceVoyage_logo.png");
		ImageView logoview = new ImageView(logo);
		logoview.setLayoutX(340);
		logoview.setLayoutY(100);
		this.getChildren().add(logoview);
		logoview.setFitHeight(200);
		logoview.setFitWidth(600);
		setRefText();
		audio.setCycleCount(MediaPlayer.INDEFINITE);
		audio.play();

	}

	public GameButton getStartButton() {
		return this.StartButton;
	}

	public GameButton getExitButton() {
		return this.ExitButton;
	}

	public void GotoSelectMenu() {
		Main.gotoSelectMenu();
	}

	public void setRefText() {
		Text RefText = new Text();
		Text Credit = new Text("Credit for sprites goes to " + "\n"
				+ "[http://millionthvector.blogspot.com/2015/11/even-more-new-free-sprites.html]");
		Credit.setLineSpacing(10);
		Credit.setLayoutX(720);
		Credit.setLayoutY(655);
		Credit.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
		Credit.setFill(Color.AQUAMARINE);
		this.getChildren().add(Credit);
		String ref = "";
		ref = "SpaceVoyage by " + "\n" + "Sakon Thephamongkhol" + "\n" + "Pongsakorn Chairatanakul" + "\n"
				+ "for Programming Methodology Year 1 Computer Engineering " + "\n" + "Chulalongkorn University";
		RefText.setText(ref);
		RefText.setLineSpacing(10);
		RefText.setLayoutX(50);
		RefText.setLayoutY(570);
		RefText.setFont(Font.font("Arial", FontWeight.MEDIUM, 15));
		RefText.setFill(Color.GREENYELLOW);
		this.getChildren().add(RefText);
	}

	public void setUpButtons() {
		StartButton.setOnAction(click -> {
			StartButton.getAudioClip().play();
			GotoSelectMenu();
		});
		ExitButton.setOnAction(click -> {
			audio.stop();
			ExitButton.getAudioClip().play();
			System.exit(0);
		});
		ArrayList<GameButton> button = new ArrayList<GameButton>();
		button.add(StartButton);
		button.add(ExitButton);
		for (GameButton b : button) {
			b.seteffect();
		}

	}

}
