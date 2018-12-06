package Main;

import Main.GameObjects.Item;
import Main.GameObjects.Player;
import Main.UserInterface.Start;

public class Game
{
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

        DungeonEntrance.setExit("Forward", NarrowPassage);
        DungeonEntrance.setExit("Back", Forest);

        NarrowPassage.setExit("Back", DungeonEntrance);
        NarrowPassage.setExit("Left", DampCave);
        NarrowPassage.setExit("Right", WebbedCave);

        Forest.setExit("Forward", DungeonEntrance);

        DampCave.setExit("Back", NarrowPassage);
        DampCave.setExit("Left", AbandonedCamp);

        AbandonedCamp.setExit("Back", DampCave);

        UndergroundRiver.setExit("Back", DampCave);
        UndergroundRiver.setExit("Forward", UndergroundLake);

        WebbedCave.setExit("Back", NarrowPassage);
        WebbedCave.setExit("Forward", TreasureRoom);

        TreasureRoom.setExit("Back", WebbedCave);


        currentRoom = DungeonEntrance;
    }

    public void play() {
        printWelcome();


        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to TempGameTitle!");
        System.out.println("You find yourself in the entrance to a dungeon like cave with a dark forest behind you");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.EXPLORE) {
            //printExploration(getCurrentRoom().getExplore());
        } else if (commandWord == CommandWord.SUICIDE) {
            Start.player.setDeath(true);
        }
        return wantToQuit;
    }
    private void printExploration(String explorationresults){
        if("".equals(explorationresults)){
                System.out.println("I didn't find anything, there might be something in another room");
        }   else {
                System.out.println("I found something while exploring the current room");
                //System.out.println(PLACEHOLDER FOR GETITEMINROOM + " (use 'Pickup' to collect the item)")
        }
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no entrance!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }
}
