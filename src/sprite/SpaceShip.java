package sprite;

public abstract class SpaceShip extends Sprite {

	public SpaceShip(double positionX, double positionY, double velocityX, double velocityY) {
		super(positionX, positionY, velocityX, velocityY);
	}
	
	public abstract void receivedDamage(int damage);

}
