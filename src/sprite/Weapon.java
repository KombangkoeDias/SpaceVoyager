package sprite;

public interface Weapon {
	
	public void doDamage(Sprite sprite);
	
	public Sprite checkCollide();
	
	public void disappear();
	
}
