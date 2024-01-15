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
				System.out.println("(Slot " + (i + 1) + "): [" + this.backpack[i].getName() + "] ("
						+ this.backpack[i].getDescription() + ")");
			} else {
				System.out.println("(Slot " + (i + 1) + "): Leeg");
			}
		}
	}

	public void setBackpack(Item item) {
		if (item == null) {
			System.out.println("Geen voorwerp gevonden.");
			return;
		}

		for (int i = 0; i < this.backpack.length; i++) {
			if (this.backpack[i] == null) {
				this.backpack[i] = item;

				System.out.println("Je hebt een [" + item.getName() + "] opgepakt.");
				return;
			}
		}
	}

	public Item getItemFromBackpack(int itemNumber) {
		if (this.backpack[itemNumber - 1] == null) {
			System.out.println("Geen voorwerp in dat slot.");
		}
		return this.backpack[itemNumber - 1];
	}

	public void removeItemFromBackpack(int itemNumber) {
		if (this.backpack[itemNumber - 1] == null) {
			System.out.println("Geen voorwerp in dat slot.");
			return;
		}

		/* Nullify item slot */
		Item item = null;
		for (int i = 0; i < this.backpack.length; i++) {
			item = this.backpack[i];

			if (item != null && item.getId() == this.backpack[itemNumber - 1].getId()) {
				if (item.getId() == 2 || item.getId() == 5) {
					System.out.println("Je hebt een [" + item.getName() + "] verloren.");
				} else {
					System.out.println("\n" + "Je hebt een [" + item.getName() + "] laten vallen.");
				}
				item = null;

				/* Shift items to fill array gap */
				for (int j = i; j < this.backpack.length - 1; j++) {
					this.backpack[j] = this.backpack[j + 1];
				}
				this.backpack[this.backpack.length - 1] = null;
				return;
			}
		}
	}

	public boolean hasSpecificItem(int itemId) {
		Item item = null;
		for (int i = 0; i < this.backpack.length; i++) {
			item = this.backpack[i];
			if (item != null && this.backpack[i].getId() == 1)
				return true;
		}
		return false;
	}

	public boolean hasEmptyBackpack() {
		for (int i = 0; i < this.backpack.length; i++) {
			if (this.backpack[i] != null)
				return false;
		}
		return true;
	}
}
