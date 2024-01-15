package rex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	private Player player;
	private Room[] rooms;
	private Item[] items;
	private Enemy[] enemies;
	private boolean ongoing;
	private int currentRoomIndex;
	
	public Game(Player newPlayer) {
		this.player = newPlayer;
		this.rooms = new Room[10];
		this.items = new Item[5];
		this.enemies = new Enemy[5];
		this.ongoing = true;
		this.currentRoomIndex = 0;
	}
	
	public void addPlayer(Player player) {
		this.player = player;
	}
	
	public void addRoom(Room room) {
		for (int i = 0; i < this.rooms.length; i++) {
			if (this.rooms[i] == null) {
				this.rooms[i] = room;
				
				return;
			}
		}
	}
	
	public void addItem(Item item) {
		for (int i = 0; i < this.items.length; i++) {
			if (this.items[i] == null) {
				this.items[i] = item;
				
				return;
			}
		}
	}
	
	public void addEnemy(Enemy enemy) {
		for (int i = 0; i < this.enemies.length; i++) {
			if (this.enemies[i] == null) {
				this.enemies[i] = enemy;
				
				return;
			}
		}
	}
	
	/* List room entities */
	public void getRoomEntities() {
		for (int i = 0; i < this.rooms.length; i++) {
			Room currentRoom = this.rooms[i];
			Enemy enemy = currentRoom.getEnemy();
			int currentRoomNumber = currentRoom.getNumber();
			
			if (currentRoom.hasItem() && currentRoom.hasEnemy()) {
				if (enemy.getItem() != null) {
					System.out.println("(Kamer " + (currentRoomNumber - 1) + "): [" + currentRoom.getItemName() + "] {" + enemy.getName() + "} > [" + enemy.getItem().getName() + "]");
				} else {
					System.out.println("(Kamer " + (currentRoomNumber - 1) + "): [" + currentRoom.getItemName() + "] {" + enemy.getName() + "}");
				}
			} else if (currentRoom.hasItem()) {
				System.out.println("(Kamer " + (currentRoomNumber - 1) + "): [" + currentRoom.getItemName() + "]");
			} else if (currentRoom.hasEnemy()) {
				if (enemy.getItem() != null) {
					System.out.println("(Kamer " + (currentRoomNumber - 1) + "): {" + enemy.getName() + "} > [" + enemy.getItem().getName() + "]");
				} else {
					System.out.println("(Kamer " + (currentRoomNumber - 1) + "): {" + enemy.getName() + "}");
				}
			}
		}
	}
	
	public void initializeRooms() {
		for (int i = 0; i < this.rooms.length; i++) {
			this.rooms[i].setLayout(i + 1);
		}
	}
	
	/* Distribute items throughout maze */
	public void initializeItems() {
		List<Item> itemList = Arrays.asList(this.items);
		Collections.shuffle(itemList);

		/* Create temporary room array to maintain original maze structure */
		List<Integer> roomList = new ArrayList<>();
	    for (int i = 1; i < this.rooms.length; i++) {
	        roomList.add(i);
	    }
	    Collections.shuffle(roomList);

	    /* Assign items to rooms excluding first room */
	    int currentItemIndex = 0;
	    for (int i = 1; i < roomList.size() && currentItemIndex < itemList.size(); i++) {
	    	int currentRoomIndex = roomList.get(i);
	    	Room currentRoom = this.rooms[currentRoomIndex];
	    	Item currentItem = itemList.get(currentItemIndex);
	    	
	    	if (currentItem.getId() != 1) { // Reserve magic object
		    	currentRoom.placeItem(currentItem);
	    	}
	    	currentItemIndex++;
	    }
	}
	
	public void initializeEnemies() {
		List<Enemy> enemyList = Arrays.asList(this.enemies);
		Collections.shuffle(enemyList);
		
		List<Integer> roomList = new ArrayList<>();
		for (int i = 1; i < this.rooms.length; i++) {
			roomList.add(i);
			
		}
		Collections.shuffle(roomList);
		
		int currentEnemyIndex = 0;
		for (int i = 1; i < roomList.size() && currentEnemyIndex < enemyList.size(); i++) {
			int currentRoomIndex = roomList.get(i);
			Room currentRoom = this.rooms[currentRoomIndex];
			Enemy currentEnemy = enemyList.get(currentEnemyIndex);
			
			/* Add singular magic object */
			if (i == 1) {
				for (int itemCount = 0; itemCount < this.items.length; itemCount++) {
					if (this.items[itemCount] != null && this.items[itemCount].getId() == 1) {
						currentEnemy.setItem(this.items[itemCount]);
						break;
					}
				}
			}
			
			currentRoom.placeEnemy(currentEnemy);
			currentEnemyIndex++;
		}
	}
	
	public void navigateRoom(Room currentRoom) {
		if (currentRoom.hasEnemy()) {
			Enemy enemy = currentRoom.getEnemy();
			System.out.println("{" + enemy.getName() + "} blokkeert jouw pad!");
			return;
		}
		
		Scanner scanner = new Scanner(System.in); // Closing causes error
		String direction;
		
		/* Show choices based on stationed room */
		switch(currentRoom.getNumber()) {
		case 1:
			System.out.print("Beschikbare routes: south \n" + "Route: ");
			direction = scanner.nextLine();
			switch(direction) {
			case "south":
			this.currentRoomIndex = 2;
			break;
			}
			break;
		case 2:
			System.out.print("Beschikbare routes: east, south \n" + "Route: ");
			direction = scanner.nextLine();
			switch(direction) {
			case "east":
				this.currentRoomIndex = 2;
				break;
			case "south":
				this.currentRoomIndex = 4;
				break;
			}
			break;
		case 3:
			System.out.print("Beschikbare routes: north, east, south, west \n" + "Route: ");
			direction = scanner.nextLine();
			switch(direction) {
			case "north":
				this.currentRoomIndex = 0;
				break;
			case "east":
				this.currentRoomIndex = 3;
				break;
			case "south":
				this.currentRoomIndex = 5;
				break;
			case "west":
				this.currentRoomIndex = 1;
				break;
			}
			break;
		case 4:
			System.out.print("Beschikbare routes: south, west \n" + "Route: ");
			direction = scanner.nextLine();
			switch(direction) {
			case "south":
				this.currentRoomIndex = 6;
				break;
			case "west":
				this.currentRoomIndex = 2;
				break;
			}
			break;
		case 5:
			System.out.print("Beschikbare routes: north, east, south \n" + "Route: ");
			direction = scanner.nextLine();
			switch(direction) {
			case "north":
				this.currentRoomIndex = 1;
				break;
			case "east":
				this.currentRoomIndex = 5;
				break;
			case "south":
				this.currentRoomIndex = 7;
				break;
			}
			break;
		case 6:
			System.out.print("Beschikbare routes: north, east, south, west \n" + "Route: ");
			direction = scanner.nextLine();
			switch(direction) {
			case "north":
				this.currentRoomIndex = 2;
				break;
			case "east":
				this.currentRoomIndex = 6;
				break;
			case "south":
				this.currentRoomIndex = 8;
				break;
			case "west":
				this.currentRoomIndex = 4;
				break;
			}
			break;
		case 7:
			System.out.print("Beschikbare routes: north, south, west \n" + "Route: ");
			direction = scanner.nextLine();
			switch(direction) {
			case "north":
				this.currentRoomIndex = 3;
				break;
			case "south":
				this.currentRoomIndex = 9;
				break;
			case "west":
				this.currentRoomIndex = 5;
				break;
			}
			break;
		case 8:
			System.out.print("Beschikbare routes: north, east \n" + "Route: ");
			direction = scanner.nextLine();
			switch(direction) {
			case "north":
				this.currentRoomIndex = 4;
				break;
			case "east":
				this.currentRoomIndex = 8;
				break;
			}
			break;
		case 9:
			System.out.print("Beschikbare routes: north, east, west \n" + "Route: ");
			direction = scanner.nextLine();
			switch(direction) {
			case "north":
				this.currentRoomIndex = 5;
				break;
			case "east":
				this.currentRoomIndex = 9;
				break;
			case "west":
				this.currentRoomIndex = 7;
				break;
			}
			break;
		case 10:
			System.out.print("Beschikbare routes: north, west \n" + "Route: ");
			direction = scanner.nextLine();
			switch(direction) {
			case "north":
				this.currentRoomIndex = 6;
				break;
			case "west":
				this.currentRoomIndex = 8;
				break;
			}
			break;
		}
	}
	
	/* Handle item drop */
	public void dropItem(Player player, Room currentRoom) {
		if (player.hasEmptyBackpack()) {
			System.out.println("Je hebt een lege rugzak.");
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		int itemNumber;
		player.getBackpack();
		System.out.print("\n" + "Voorwerpnummer: ");
		itemNumber = scanner.nextInt();
		scanner.nextLine(); // Consume "Enter" newline character
		Item item = this.player.getItemFromBackpack(itemNumber); // Ensure matching item gets dropped
		
        if (currentRoom.hasItem()) {
        	System.out.println("\n" + "Er ligt al een voorwerp hier; pak deze eerst op!");
        	return;
        }
		currentRoom.placeItem(item);
        player.removeItemFromBackpack(itemNumber);
	}
	
	/* Handle item and enemy interaction */
	public void useItem(Player player, Room currentRoom) {
		Random random = new Random();
		double winProbability;
		
		if (player.hasEmptyBackpack()) {
			/* Luck-based enemy fight with no items */
			if (currentRoom.hasEnemy()) {
				Enemy enemy = currentRoom.getEnemy();
				winProbability = 0.25;
				System.out.println("Geen voorwerp in bezit; je gaat {" + enemy.getName() + "} aan in een vuistgevecht!");
				
				if (random.nextDouble() < winProbability) {
					System.out.println("{" + enemy.getName() + "} valt dood op de grond!");
					currentRoom.removeEnemy(true);
				} else {
					System.out.println("{" + enemy.getName() + "} heeft jou kapotgemaakt. \n");
					endGame(false);
				}
				return;
			}
			System.out.println("Je hebt geen voorwerpen in bezit.");
			return;
		}

		Scanner scanner = new Scanner(System.in); // Closing causes error
		int itemNumber;
		int itemId = 0;
		player.getBackpack();
		System.out.print("\n" + "Voorwerpnummer: ");
		itemNumber = scanner.nextInt();
		System.out.println();
		Item item = player.getItemFromBackpack(itemNumber);
		Enemy enemy = currentRoom.getEnemy();
		if (item != null) {
			itemId = item.getId();
		}
		
		/* Item scenario's  */
		if (currentRoom.hasEnemy()) {
			switch (itemId) {
			case 1:
				System.out.println("Je kunt beter aan dit voorwerp blijven vasthouden!");
				break;
			/* Change player location */
			case 2:
				System.out.println("Je eet de [" + item.getName() + "] en wordt weer nuchter in een andere kamer...");
				int newRoomIndex;
				do {
					newRoomIndex = random.nextInt(10);
				} while (newRoomIndex == currentRoomIndex);
				this.currentRoomIndex = newRoomIndex;
				player.removeItemFromBackpack(itemNumber);
				break;
			case 3:
				System.out.println("Je zwaait de [" + item.getName() + "] en snijdt {" + enemy.getName() + "} doormidden!");
				currentRoom.removeEnemy(true);
				break;
			/* Change enemy location */
			case 4:
				System.out.println("Je zwaait de [" + item.getName() + "] en lanceert {" + enemy.getName() + "} naar een andere kamer!");
				int enemyNewRoomIndex;
				Room enemyNewRoom;
				do {
					enemyNewRoomIndex = random.nextInt(10);
					enemyNewRoom = this.rooms[enemyNewRoomIndex];
				} while (enemyNewRoomIndex == currentRoomIndex || enemyNewRoom.hasEnemy());
				enemyNewRoom.placeEnemy(enemy);
				currentRoom.removeEnemy(false);
				break;
			case 5:
				winProbability = 0.5;
				if (random.nextDouble() < winProbability) {
					System.out.println("Je gooit de [" + item.getName() + "] en veroorzaakt {" + enemy.getName() + "} een hersenbloeding!");
					player.removeItemFromBackpack(itemNumber);
					currentRoom.removeEnemy(true);
				} else {
					System.out.println("Je gooit de [" + item.getName() + "] en mist!");
					player.removeItemFromBackpack(itemNumber);
				}
				break;
			default:
				break;
			}
			return;
		}
		
		switch(itemId) {
		case 1:
			System.out.println("Het blijkt niet veel te doen...");
			break;
		case 2:
			System.out.println("Je eet de [" + item.getName() + "] en wordt weer nuchter in een andere kamer...");
			int newRoomIndex;
			do {
				newRoomIndex = random.nextInt(10);
			} while (newRoomIndex == currentRoomIndex);
			this.currentRoomIndex = newRoomIndex;
			player.removeItemFromBackpack(itemNumber);
			break;
		case 5:
			System.out.println("Je gooit de [" + item.getName() + "] door de kamer heen en het breekt doormidden...");
			player.removeItemFromBackpack(itemNumber);
			break;
		default:
			System.out.println("Er is geen vijand in deze kamer.");
			break;
		}
		
	}
	
	public void lootEnemy(Room currentRoom) {
		if (currentRoom.hasEnemy()) {
			Enemy enemy = currentRoom.getEnemy();
			System.out.println("Je moet {" + enemy.getName() + "} eerst verslaan voordat je hem kan plunderen.");
			return;
		}
		
		if (currentRoom.hasDefeatedEnemy()) {
			if (currentRoom.getEnemyItem() != null) {
				Item item = currentRoom.getEnemyItem();
				player.setBackpack(item);
				currentRoom.removeEnemyItem();
				return;
			}
			System.out.println("Geen voorwerp gevonden.");
		}
		
	}
	
	public void printHelpCommands() {
		System.out.println("***************************************************");
		System.out.println("ACTIES");
		System.out.println("go - Navigeer naar een bepaalde richting");
		System.out.println("get - Pak een gevonden voorwerp op");
		System.out.println("drop - Laat een voorwerp achter in de huidige kamer");
		System.out.println("use - Maak gebruik van een voorwerp");
		System.out.println("loot - Plunder een dode vijand");
		System.out.println("pack - Weergeef alle voorwerpen in jouw inventaris");
		System.out.println("help - Print deze lijst nogmaals uit");
		System.out.println("look - Laat de indeling van de huidige kamer zien");
		System.out.println("quit - Stoppen met spelen \n");
		System.out.println("SYMBOLEN \n" + "? - Voorwerp \n" + "! - Vijand \n" + "x - Verslagen vijand");
		System.out.println("***************************************************");
	}
	
	public void startGame() {
		Scanner scanner = new Scanner(System.in);
		String playerCommand;
		int move = 1;
		
		System.out.println("Welkom moeidge avonturier " + this.player.getName() + "! \n");
		System.out.println("Je bevindt je momenteel in de eerste van de tien kamers");
		System.out.println("Elk kamer heeft een unieke indeling met verschillende uitgangen");
		System.out.println("Deze kamers kunnen voorwerpen bevatten die je kunt gebruiken");
		System.out.println("Let wel op, gevaarlijke vijanden zwerven in deze doolhof rond... \n");
		System.out.println("VIND HET MAGISCHE VOORWERP EN BRENG DEZE HIER TERUG! \n");
		
		initializeRooms();
		initializeItems();
		initializeEnemies();
		printHelpCommands();
		
		/* Game loop */
		while (ongoing) {
			Player player = this.player;
			Room currentRoom = this.rooms[currentRoomIndex];
			int itemNumber;
			System.out.print("\n" + "Zet: " + move + "\n" + "Actie: ");
			playerCommand = scanner.nextLine();
			System.out.println();

			switch(playerCommand) {
				case "go":
					navigateRoom(currentRoom);
					break;
				case "get":
					player.setBackpack(currentRoom.getItem());
					currentRoom.removeItem();
					break;
				case "drop":
					dropItem(player, currentRoom);
		            break;
				case "use":
					useItem(player, currentRoom);
					break;
				case "loot":
					lootEnemy(currentRoom);
					break;
				case "pack":
					player.getBackpack();
					break;
				case "help":
					printHelpCommands();
					break;
				case "look":
					currentRoom.getLayout();
					break;
				case "quit":
					endGame(false);
					break;
				case "cheat":
					getRoomEntities();
					break;
			}
			move++;
			checkWin(player);
		}
		scanner.close();
	}
	
	public void checkWin(Player player) {
		if (currentRoomIndex == 0 && !player.hasEmptyBackpack()) {
			if (player.hasSpecificItem(1)) {
				System.out.println("\n" + "Het magische voorwerp is teruggebracht.");
				endGame(true);
			}
		}
	}
	
	public void endGame(boolean playerWonGame) {
		if (playerWonGame) {
			System.out.println("\n" + "Je hebt gewonnen!");
		} else {
			System.out.println("Je hebt verloren...");
		}
		this.ongoing = false;
		return;
	}
}
