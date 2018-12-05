
public class Game 
{
    private Parser parser;
    private Room currentRoom;
        

    public Game() 
    {
        createRooms();
        parser = new Parser();
    }


    private void createRooms()
    {
        Room DungeonEntrance, NarrowPassage, Forest, DampCave, AbandonedCamp, UndergroundRiver, UndergroundLake, WebbedCave, TreasureRoom;
      
        DungeonEntrance = new Room("Dungeon Entrance the main entrance of the Dungeon");
        NarrowPassage = new Room("A narrow passage leading from the entrance to multible larger caves");
        Forest = new Room("The dark forest in which the dungeon is found");
        DampCave = new Room("In a damp cave leading to a small river and what looks to be an abandoned camp");
        AbandonedCamp = new Room("What looks to be an abandoned camp from whom and why there's no clue");
        UndergroundRiver = new Room("An underground river leading from the damp cave to some sort of lake");
        UndergroundLake = new Room("Some kind of underground lake");
        WebbedCave = new Room("A webbed cave which looks abandoned for now, but there's a bit locked door ahead");
        TreasureRoom = new Room("The treasure room of the dungeon there more riches here than im able to count");

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

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
