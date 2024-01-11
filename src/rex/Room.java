package rex;

public class Room {
	private int number;
	private String[][] room;
	private Item item;
	
	public Room(int roomNumber) {
		this.number = roomNumber;
		this.room = new String[5][5];
		this.item = null;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void getLayout() {
		for (int rowCount = 0; rowCount < this.room.length; rowCount++) {
			for (int columnCount = 0; columnCount < this.room[rowCount].length; columnCount++) {
				System.out.print(this.room[rowCount][columnCount] + " ");
			}
			System.out.println();
		}
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public void removeItem() {
		this.item = null;
	}
	
	// TEMP CODE
	public String getItemName() {
		if (this.item == null) {
			return null;
		}
		return this.item.getName();
	}
	// END TEMP CODE
	
	public void generateLayout(int roomNumber) {
		/* Generate walls and whitespace */
		for (int rowCount = 0; rowCount < this.room.length; rowCount++) {
			for (int columnCount = 0; columnCount < this.room[rowCount].length; columnCount++) {
				if (rowCount == 0 || rowCount == 4 || columnCount == 0 || columnCount == 4) {
					this.room[rowCount][columnCount] = "*";
				} else {
					this.room[rowCount][columnCount] = " ";
				}
			}
		}
		this.room[2][2] = String.valueOf(getNumber() - 1);
		
		// PLACE ITEMS IF HAVE
		// END PLACE ITEM
		
		/* Replace walls with doors accordingly */
		switch(roomNumber) {
		case 1:
			this.room[4][2] = " ";
			break;
		case 2:
			this.room[2][4] = " ";
			this.room[4][2] = " ";
			break;
		case 3:
			this.room[0][2] = " ";
			this.room[2][0] = " ";
			this.room[2][4] = " ";
			this.room[4][2] = " ";
			break;
		case 4:
			this.room[2][0] = " ";
			this.room[4][2] = " ";
			break;
		case 5:
			this.room[0][2] = " ";
			this.room[2][4] = " ";
			this.room[4][2] = " ";
			break;
		case 6:
			this.room[0][2] = " ";
			this.room[2][0] = " ";
			this.room[2][4] = " ";
			this.room[4][2] = " ";
			break;
		case 7:
			this.room[0][2] = " ";
			this.room[2][0] = " ";
			this.room[4][2] = " ";
			break;
		case 8:
			this.room[0][2] = " ";
			this.room[2][4] = " ";
			break;
		case 9:
			this.room[0][2] = " ";
			this.room[2][0] = " ";
			this.room[2][4] = " ";
			break;
		case 10:
			this.room[0][2] = " ";
			this.room[2][0] = " ";
			break;
		}
	}
}
