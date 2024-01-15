package rex;

import java.util.Random;

public class Room {
	private int number;
	private String[][] room;
	private Item item;
	private Item enemyItem;
	private Enemy enemy;
	private boolean defeatedEnemy;

	public Room(int roomNumber) {
		this.number = roomNumber;
		this.room = new String[5][5];
		this.item = null;
		this.enemy = null;
		this.defeatedEnemy = false;
	}

	public int getNumber() {
		return this.number;
	}

	public Item getItem() {
		return this.item;
	}

	public Item getEnemyItem() {
		return this.enemyItem;
	}

	public String getItemName() {
		if (this.item == null) {
			return null;
		}
		return this.item.getName();
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void removeItem() {
		this.item = null;

		for (int rowCount = 0; rowCount < this.room.length; rowCount++) {
			for (int columnCount = 0; columnCount < this.room[rowCount].length; columnCount++) {
				if (this.room[rowCount][columnCount] == "?") {
					this.room[rowCount][columnCount] = " ";
					return;
				}
			}
		}
	}
	
	public void removeEnemyItem() {
		this.enemyItem = null;
	}

	public boolean hasItem() {
		if (this.item == null) {
			return false;
		}
		return true;
	}

	public Enemy getEnemy() {
		return this.enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public void removeEnemy(boolean defeated) {
		for (int rowCount = 0; rowCount < this.room.length; rowCount++) {
			for (int columnCount = 0; columnCount < this.room[rowCount].length; columnCount++) {
				if (this.room[rowCount][columnCount] == "!") {
					if (defeated) {
						if (getEnemy().getItem() != null)
							this.enemyItem = getEnemy().getItem();
						this.room[rowCount][columnCount] = "x";
						this.defeatedEnemy = true;
					} else {
						this.room[rowCount][columnCount] = " ";
					}
					this.enemy = null;
					return;
				}
			}
		}
	}

	public boolean hasEnemy() {
		if (this.enemy == null) {
			return false;
		}
		return true;
	}

	public boolean hasDefeatedEnemy() {
		if (this.defeatedEnemy == false) {
			return false;
		}
		return true;
	}

	public void getLayout() {
		for (int rowCount = 0; rowCount < this.room.length; rowCount++) {
			for (int columnCount = 0; columnCount < this.room[rowCount].length; columnCount++) {
				System.out.print(this.room[rowCount][columnCount] + " ");
			}
			System.out.println();
		}
	}

	public void setLayout(int roomNumber) {
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

		/* Replace walls with doors accordingly */
		switch (roomNumber) {
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

	public void placeItem(Item item) {
		if (item == null) {
			return;
		}

		Random random = new Random();
		int rowIndex;
		int columnIndex;

		/* Generate random integer between 1-3 excluding 2+2 */
		do {
			rowIndex = random.nextInt(3) + 1;
			columnIndex = random.nextInt(3) + 1;
		} while (rowIndex == 2 && columnIndex == 2 || this.room[rowIndex][columnIndex] == "!");

		this.room[rowIndex][columnIndex] = "?";
		setItem(item);
		return;
	}

	public void placeEnemy(Enemy enemy) {
		Random random = new Random();
		int rowIndex;
		int columnIndex;

		/* Ensure enemy does not overwrite item */
		do {
			rowIndex = random.nextInt(3) + 1;
			columnIndex = random.nextInt(3) + 1;
		} while (rowIndex == 2 && columnIndex == 2 || this.room[rowIndex][columnIndex] == "?");

		this.room[rowIndex][columnIndex] = "!";
		setEnemy(enemy);
		return;
	}
}
