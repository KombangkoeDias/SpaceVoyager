package gui;

import java.util.ArrayList;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.Loader;
import logic.Main;

public class SelectMenu extends Pane {

	private Loader loader = Main.loader;

	ArrayList<GameButton> buttonlist = new ArrayList<GameButton>();

	public SelectMenu() {
		GameButton firstButton = new GameButton(370, 200);
		GameButton secondButton = new GameButton(370, 350);
		GameButton thirdButton = new GameButton(370, 500);
		firstButton.sayText("Spaceship 1" + "\n" + "Power : High" + "\n" + "Fire Rate : Low" + "\n" + "Life : 2");
		secondButton.sayText("Spaceship 2" + "\n" + "Power : Low" + "\n" + "Fire Rate : High" + "\n" + "Life : 2");
		thirdButton.sayText("Spaceship 3" + "\n" + "Power : Medium" + "\n" + "Fire Rate : Medium" + "\n" + "Life : 3");
		firstButton.setImage(loader.playerFirstShipImage);
		secondButton.setImage(loader.playerSecondShipImage);
		thirdButton.setImage(loader.playerThirdShipImage);
		buttonlist.add(firstButton);
		buttonlist.add(secondButton);
		buttonlist.add(thirdButton);
		this.setStyle("-fx-background-image: url('file:res/selectSpace.jpg')");
		for (GameButton b : buttonlist) {
			b.setPrefSize(500, 170);
			this.getChildren().add(b);
		}
		setAction();
		setButton();
		MakeTextvalue();
	}

	public void setAction() {
		for (GameButton e : buttonlist) {
			e.setOnAction(click -> {
				loader.playerChosenSpaceShipImage = e.getImage();
				try {
					MainMenu.audio.stop();
					Main.goToGame();
				} catch (Exception r) {

				}
			});
		}
	}

	public void MakeTextvalue() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(1.0f, 0f, 0f));
		InnerShadow is = new InnerShadow();
		is.setOffsetX(5.0f);
		is.setOffsetY(5.0f);
		Text text = new Text("Let's begin the Voyage!!" + "\n" + "Choose one Spacecraft to begin");
		text.setEffect(is);
		text.setEffect(ds);
		text.setLayoutX(120);
		text.setLayoutY(100);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFill(Color.INDIANRED);
		text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 70));
		text.setFontSmoothingType(FontSmoothingType.LCD);
		this.getChildren().add(text);
	}

	public void setButton() {
		for (GameButton e : buttonlist) {
			e.setStyle("-fx-background-color: transparent;");
			e.setOnMouseEntered(enter -> {
				e.getAudioClip().play();
				if (e.equals(buttonlist.get(0))) {
					e.setStyle("-fx-background-color:#66CDAA");
				} else if (e.equals(buttonlist.get(1))) {
					e.setStyle("-fx-background-color:#6495ed");
				} else if (e.equals(buttonlist.get(2))) {
					e.setStyle("-fx-background-color:#F08080");
				}
				e.setTextFill(Color.BLACK);
				e.setBorder(new Border(new BorderStroke(Color.GOLD, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
						BorderWidths.DEFAULT)));
				e.setText(e.returnText());
			});
			e.setOnMouseExited(exit -> {
				e.setStyle("-fx-background-color: transparent;");
				e.getAudioClip().play();
				e.setBorder(null);
				e.setText(null);
			});
		}
	}

	public Loader getLoader() {
		return this.loader;
	}

}
