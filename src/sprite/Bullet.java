package sprite;

public abstract class Bullet extends Sprite implements Weapon {

	public Bullet(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
	}

}
