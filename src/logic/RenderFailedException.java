package logic;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RenderFailedException extends Exception {

	private static final long serialVersionUID = 7536665729257575825L;
	private static ArrayList<String> printedMessage = new ArrayList<String>();
	private double positionX;
	private double positionY;

	public RenderFailedException(double positionX, double positionY) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public void handle(String message, int width, int height, GraphicsContext gc) {

		if (!printedMessage.contains(message)) {
			System.out.println("Failed to render " + message);
			printedMessage.add(message);
		}

		gc.setFill(Color.WHITE);
		gc.fillRect(positionX, positionY, width, height);

	}

}
