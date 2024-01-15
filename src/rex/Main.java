package rex;

public class Main {

	public static void main(String[] args) {
		/* Initialize classes */
		Player player = new Player("Carron Schilders");
		Game game = new Game(player);
		Room room1 = new Room(1);
		Room room2 = new Room(2);
		Room room3 = new Room(3);
		Room room4 = new Room(4);
		Room room5 = new Room(5);
		Room room6 = new Room(6);
		Room room7 = new Room(7);
		Room room8 = new Room(8);
		Room room9 = new Room(9);
		Room room10 = new Room(10);
		Item item1 = new Item(1, "Magisch Voorwerp", "???");
		Item item2 = new Item(2, "Verdachte Paddo", "Word wakker in een andere kamer");
		Item item3 = new Item(3, "Middeleeuws Zwaard", "Vermoord een vijand");
		Item item4 = new Item(4, "Honkbalknuppel", "Lanceer een vijand naar een andere kamer");
		Item item5 = new Item(5, "Handvorm Baksteen", "Kans om een vijand uit te schakelen");
		Enemy enemy1 = new Enemy(1, "Vincent Kreuzen");
		Enemy enemy2 = new Enemy(2, "Bart Mutsaers");
		Enemy enemy3 = new Enemy(3, "Luc Claessens");
		Enemy enemy4 = new Enemy(4, "Bob van der Putten");
		Enemy enemy5 = new Enemy(5, "Ger Saris");
		
		/* Initialize game class */
		game.addPlayer(player);
		game.addRoom(room1);
		game.addRoom(room2);
		game.addRoom(room3);
		game.addRoom(room4);
		game.addRoom(room5);
		game.addRoom(room6);
		game.addRoom(room7);
		game.addRoom(room8);
		game.addRoom(room9);
		game.addRoom(room10);
		game.addItem(item1);
		game.addItem(item2);
		game.addItem(item3);
		game.addItem(item4);
		game.addItem(item5);
		game.addEnemy(enemy1);
		game.addEnemy(enemy2);
		game.addEnemy(enemy3);
		game.addEnemy(enemy4);
		game.addEnemy(enemy5);
		game.startGame();
	}
}
