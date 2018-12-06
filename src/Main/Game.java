package Main;

import Main.GameObjects.Item;
import Main.GameObjects.Player;
import Main.UserInterface.Start;

import java.util.ArrayList;
import java.util.List;

public class Game
{
    public boolean finished = false;
    private Parser parser;
    private Room currentRoom;
    public Room DungeonEntrance, NarrowPassage, Forest, DampCave, AbandonedCamp, UndergroundRiver, UndergroundLake, WebbedCave, TreasureRoom;

    public Game() {
        createRooms();
        initialiseStuff();
        parser = new Parser();
    }

    private void initialiseStuff() {
        Start.player = new Player(DungeonEntrance);
        //TODO: Add description
        Start.items.add(new Item(UndergroundLake, 1, "Key Piece 1", "Zy Also this one"));
        Start.items.add(new Item(AbandonedCamp, 2, "Key Piece 2", "Zy Also this one"));
        Start.items.add(new Item(Forest, 3, "Key Piece 3", "Zy Also this one"));
    }


    private void createRooms() {


        DungeonEntrance = new Room("in the main entrance of the Dungeon");
        NarrowPassage = new Room("in a narrow passage leading from the entrance to multiple larger caves");
        Forest = new Room("in the dark forest in which the dungeon is found");
        DampCave = new Room("In a damp cave leading to a small river and what looks to be an abandoned camp");
        AbandonedCamp = new Room("in what looks to be an abandoned camp from whom and why there's no clue");
        UndergroundRiver = new Room("besides an underground river leading from the damp cave to some sort of lake");
        UndergroundLake = new Room("besides some kind of underground lake");
        WebbedCave = new Room("in a webbed cave which looks abandoned for now, but there's a bit locked door ahead");
        TreasureRoom = new Room("in the treasure room of the dungeon there more riches here than i'm able to count");

        DungeonEntrance.setExit("forward", NarrowPassage);
        DungeonEntrance.setExit("back", Forest);

        NarrowPassage.setExit("back", DungeonEntrance);
        NarrowPassage.setExit("left", DampCave);
        NarrowPassage.setExit("right", WebbedCave);

        Forest.setExit("forward", DungeonEntrance);

        DampCave.setExit("back", NarrowPassage);
        DampCave.setExit("left", AbandonedCamp);

        AbandonedCamp.setExit("back", DampCave);

        UndergroundRiver.setExit("back", DampCave);
        UndergroundRiver.setExit("forward", UndergroundLake);

        WebbedCave.setExit("back", NarrowPassage);
        WebbedCave.setExit("forward", TreasureRoom);

        TreasureRoom.setExit("Back", WebbedCave);


        currentRoom = DungeonEntrance;
    }

    public void play() {
        printWelcome();

        while (!Start.player.isDeath()) {
            Command command = parser.getCommand();
            processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
        System.exit(0);
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to TempGameTitle!");
        System.out.println("You find yourself in the entrance to a dungeon like cave with a dark forest behind you");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private void processCommand(Command command) {

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            Start.player.setDeath(true); //totally not the exact same thing a Suicide just a bit less dark
        } else if (commandWord == CommandWord.EXPLORE) {
            exploration(currentRoom);
        } else if (commandWord == CommandWord.SUICIDE) {
            Start.player.setDeath(true);
        }
    }

    private void exploration(Room currentRoom) {
        List<Item> itemsInRoom = new ArrayList<>();
        Start.items.forEach(item -> {
            if (item.getRoom().equals(currentRoom)) {
                itemsInRoom.add(item);
            }
        });
        if (itemsInRoom.size() == 0) {
            System.out.println("There where no items in the room.");
        } else {
            StringBuilder sb = new StringBuilder();
            itemsInRoom.forEach(item -> {
                sb.append(item.getName());
                Start.player.addItem(item);
            });
            System.out.println("Hey! I found {0}".replace("{0}", sb.toString()));
        }
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasArgs()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getArgs()[0];

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no entrance!");
        } else {
            currentRoom = nextRoom;
            Start.player.setRoom(currentRoom);
            System.out.println(currentRoom.getLongDescription());
        }
    }

}
