package sprite;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.Main;
import logic.RenderFailedException;

public abstract class Sprite {
	
	public double positionX;
	public double positionY;
	public double velocityX;
	public double velocityY;
	private double width;
	private double height;
	protected Image image;

	public Sprite(double positionX, double positionY, double velocityX, double velocityY) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}

	public void update(double time) {
		positionX += velocityX * time;
		positionY += velocityY * time;
	}
	
	public void setImage(Image image) {
		this.image = image;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
	}

	public void render(GraphicsContext gc) throws RenderFailedException {
		gc.drawImage(image, positionX, positionY);
		if (width == 0 || height == 0) {
			throw new RenderFailedException(positionX, positionY);
		}
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}

	public boolean intersects(Sprite otherSprite) {
		return otherSprite.getBoundary().intersects(this.getBoundary());
	}

	public boolean checkOutOfScreen() {
		return (this.positionX + this.width < 0 || this.positionX > Main.WIDTH || this.positionY + this.height < 0
				|| this.positionY > Main.HEIGHT);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

}
