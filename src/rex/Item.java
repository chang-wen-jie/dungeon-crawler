package rex;

public class Item {
	private int id;
	private String name;
	private String description;
	
	public Item(int itemId, String itemName, String itemDescription) {
		this.id = itemId;
		this.name = itemName;
		this.description = itemDescription;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
}
