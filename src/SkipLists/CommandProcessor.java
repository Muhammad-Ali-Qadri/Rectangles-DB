package SkipLists;

public class CommandProcessor implements Processor{

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private final Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     *
     *            the database object to manipulate
     */
    public CommandProcessor() {
        data = new SkipListDatabase();
    }


    /**
     * {@inheritDoc}
     * */
    @Override
    public String process(String input) {
        return "";
    }

}
