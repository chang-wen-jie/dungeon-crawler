package rex;

public class Enemy {
	private int id;
	private String name;
	private Item item;

	public Enemy(int enemyId, String enemyName) {
		this.id = enemyId;
		this.name = enemyName;
		this.item = null;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
