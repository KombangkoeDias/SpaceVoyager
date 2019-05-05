package gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameButton extends Button
{
	private Image image;
	private AudioClip sound;
	private double X;
	private String text;
	public GameButton(double valueX,double valueY) {
		this.setLayoutX(valueX);
		this.setLayoutY(valueY);
		this.setFont(Font.font(20));
		this.sound = new AudioClip("file:audio/button-3.mp3");
		this.sound.setVolume(this.sound.getVolume()/3);
		this.X = this.getWidth();
	}
	public void setImage(Image image) {
		this.image = image;
		ImageView imageview = new ImageView(image);
		this.setGraphic(imageview);
	}
	public Image getImage() {
		return this.image;
	}
	public AudioClip getAudioClip() {
		return this.sound;
	}
	public void seteffect() {
		
		this.setOnMouseEntered(enter ->{
			getAudioClip().play();
			setBorder(new Border(new BorderStroke(Color.GOLD,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
			if (this.getWidth() == this.X) {
			setLayoutX(getLayoutX()-5);
			setLayoutY(getLayoutY());
			setPrefSize(this.getWidth()+10,this.getHeight());
			}
			
		});
		this.setOnMouseExited(exit -> {
			getAudioClip().play();
			setBorder(null);
			if (this.getWidth() < this.X) {
			setLayoutX(getLayoutX()+5);
			setLayoutY(getLayoutY());
			setPrefSize(this.getWidth()-10,this.getHeight());
			}
			
		});
	}
	public double getX() {
		return this.X;
	}
	public void sayText(String value) {
		this.text = value;
	}
	public String returnText() {
		return this.text;
	}
	
}
