package Main;

import java.util.Arrays;
import java.util.Scanner;

public class Parser 
{
    private CommandWords commands;
    private Scanner reader;

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        String line = "";
        System.out.print("> ");
        if (reader.hasNextLine()) {
            line = reader.nextLine();
        }
        String[] args = line.split(" ");
        return new Command(commands.getCommandWord(args[0]), Arrays.copyOfRange(args, 1, args.length));
    }

    public void showCommands() {
        commands.showAll();
    }
}
