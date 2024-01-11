package rex;

public class Player {
	private String name;
	private Item[] backpack;
	
	public Player(String playerName) {
		this.name = playerName;
		this.backpack = new Item[5];
	}
	
	public String getName() {
		return this.name;
	}

	/* Backpack CRUD */
	public void getBackpack() {
		for (int i = 0; i < this.backpack.length; i++) {
			if (this.backpack[i] != null) {
				System.out.println((i + 1) + ". " + this.backpack[i].getName() + " [" + this.backpack[i].getDescription() + "]");
			}
		}
	}
	
	public void addItemToBackpack(Item item) {
		if (item != null) {
			for (int i = 0; i < this.backpack.length; i++) {
				if (this.backpack[i] == null) {
					this.backpack[i] = item;
					
					System.out.println(item.getName() + " is toegevoegd aan jouw inventaris.");
					return;
				}
			}
		} else {
			System.out.println("Geen voorwerp gevonden.");
		}
	}
	
	public void dropItemFromBackpack(int itemNumber) {
		for (int i = 0; i < this.backpack.length; i++) {
			if (this.backpack[i] != null) {
				if (this.backpack[i].getId() == this.backpack[itemNumber - 1].getId()) {
					this.backpack[i] = null;
					
					break;
				}
			}
		}
		
		/* Shift all items to fill array gap */
		for (int i = itemNumber - 1; i < this.backpack.length - 1; i++) {
	        this.backpack[i] = this.backpack[i + 1];
	    }
	    this.backpack[this.backpack.length - 1] = null;
	}
}
