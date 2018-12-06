package Main;

public enum CommandWord
{
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    EXPLORE("explore"),
    SUICIDE("give_up");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
