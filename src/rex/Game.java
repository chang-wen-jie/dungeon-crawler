package rex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
	private Player player;
	private Room[] rooms;
	private Item[] items;
	private boolean ongoing;
	private int currentRoomIndex;
	
	public Game(Player newPlayer) {
		this.player = newPlayer;
		this.rooms = new Room[10];
		this.items = new Item[5];
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
	
	public void getRooms() {
		for (int i = 0; i < this.rooms.length; i++) {
			if (this.rooms[i] != null) {
				System.out.println(this.rooms[i].getNumber() + " - " + this.rooms[i].getItemName());
			}
		}
	}
	
	public void initializeRooms() {
		for (int i = 0; i < this.rooms.length; i++) {
			this.rooms[i].generateLayout(i + 1);
		}
	}
	
	public void distributeItems() {
		List<Item> itemList = Arrays.asList(this.items);
		Collections.shuffle(itemList);
		
		/* Create temporary room array to maintain maze layout */
		List<Integer> roomList = new ArrayList<>();
	    for (int i = 1; i < this.rooms.length; i++) {
	        roomList.add(i);
	    }
	    Collections.shuffle(roomList);

	    /* Assign items to rooms */
	    int currentItemIndex = 0;
	    for (int i = 1; i < roomList.size() && currentItemIndex < itemList.size(); i++) {
	    	int currentRoomIndex = roomList.get(i);
	    	Room currentRoom = this.rooms[currentRoomIndex];
	    	Item currentItem = itemList.get(currentItemIndex);
	    	
	    	currentRoom.setItem(currentItem);
	    	currentItemIndex++;
	    }
	}
	
	public void printHelpCommands() {
		System.out.println("***************************************************");
		System.out.println("ACTIES");
		System.out.println("go - Navigeer naar een bepaalde richting");
		System.out.println("get - Pak een gevonden voorwerp op");
		System.out.println("drop - Laat een voorwerp achter in de huidige kamer");
		System.out.println("use - Maak gebruik van een voorwerp");
		System.out.println("pack - Weergeef alle voorwerpen in de rugzak");
		System.out.println("help - Print deze lijst nogmaals uit");
		System.out.println("look - Laat de indeling van de huidige kamer zien");
		System.out.println("quit - Stoppen met spelen");
		System.out.println("\n" + "SYMBOLEN" + "\n" + "? - Voorwerp" + "\n" + "! - Vijand");
		System.out.println("***************************************************");
	}
	

	public void navigateRoom(int currentRoomIndex) {
		Scanner playerInput = new Scanner(System.in); //Closing causes error
		String direction;
		
		/* Show choices based on stationed room */
		switch(currentRoomIndex) {
		case 0:
			System.out.print("Beschikbare routes: south" + "\n" + "Route: ");
			direction = playerInput.nextLine();
			switch(direction) {
			case "south":
			this.currentRoomIndex = 2;
			break;
			}
			break;
		case 1:
			System.out.print("Beschikbare routes: east, south" + "\n" + "Route: ");
			direction = playerInput.nextLine();
			switch(direction) {
			case "east":
				this.currentRoomIndex = 2;
				break;
			case "south":
				this.currentRoomIndex = 4;
				break;
			}
			break;
		case 2:
			System.out.print("Beschikbare routes: north, east, south, west" + "\n" + "Route: ");
			direction = playerInput.nextLine();
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
		case 3:
			System.out.print("Beschikbare routes: south, west" + "\n" + "Route: ");
			direction = playerInput.nextLine();
			switch(direction) {
			case "south":
				this.currentRoomIndex = 6;
				break;
			case "west":
				this.currentRoomIndex = 2;
				break;
			}
			break;
		case 4:
			System.out.print("Beschikbare routes: north, east, south" + "\n" + "Route: ");
			direction = playerInput.nextLine();
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
		case 5:
			System.out.print("Beschikbare routes: north, east, south, west" + "\n" + "Route: ");
			direction = playerInput.nextLine();
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
		case 6:
			System.out.print("Beschikbare routes: north, south, west" + "\n" + "Route: ");
			direction = playerInput.nextLine();
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
		case 7:
			System.out.print("Beschikbare routes: north, east" + "\n" + "Route: ");
			direction = playerInput.nextLine();
			switch(direction) {
			case "north":
				this.currentRoomIndex = 4;
				break;
			case "east":
				this.currentRoomIndex = 8;
				break;
			}
			break;
		case 8:
			System.out.print("Beschikbare routes: north, east, west" + "\n" + "Route: ");
			direction = playerInput.nextLine();
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
		case 9:
			System.out.print("Beschikbare routes: north, west" + "\n" + "Route: ");
			direction = playerInput.nextLine();
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
	
	public void startGame() {
		Scanner playerInput = new Scanner(System.in);
		String playerCommand;
		int move = 1;
		
		System.out.println("\n" + "Welkom moeidge avonturier " + this.player.getName() + "!");
		System.out.println("Vind het magische voorwerp en breng deze terug.");
		System.out.println("Let op! Gevaarlijke vijanden bevinden zich in deze doolhof..." + "\n");
		distributeItems();
		initializeRooms();
		printHelpCommands();
		
		/* Game loop */
		while (ongoing) {
			Player currentPlayer = this.player;
			Room currentRoom = this.rooms[currentRoomIndex];
			System.out.print("\n" + "Zet: " + move + "\n" + "Actie: ");
			playerCommand = playerInput.nextLine();
			System.out.println();
			
			switch(playerCommand) {
				case "go":
					navigateRoom(currentRoomIndex);
					break;
				case "get":
					currentPlayer.addItemToBackpack(currentRoom.getItem());
					currentRoom.removeItem();
					break;
				case "drop":
					int itemNumber;
					System.out.print("Voorwerpnummer: ");
					itemNumber = playerInput.nextInt();
					playerInput.nextLine(); //Consume "Enter" newline character
					currentPlayer.dropItemFromBackpack(itemNumber);
					break;
				case "use":
					getRooms();
					break;
				case "pack":
					currentPlayer.getBackpack();
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
			}
			move++;
			
			if (currentRoomIndex == 9) {
				endGame(true);
			}
		}
		playerInput.close();
	}
	
	public void endGame(boolean playerWonGame) {
		if (playerWonGame) {
			System.out.println("\n" + "Je hebt gewonnen!");
		} else {
			System.out.println("Spel wordt nu afgesloten...");
		}
		this.ongoing = false;
	}
}
