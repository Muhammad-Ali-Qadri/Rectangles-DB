package processor;

import database.Database;
import database.PRQaudTreeDatabase;
import quadtree.*;
import skiplist.*;

public class PointCommandProcessor implements Processor{
    /**
     * {@inheritDoc}
     */

    private final Database<String, Point> data;

    public PointCommandProcessor() {
        data = new PRQaudTreeDatabase();
    }

    @Override
    public String process(String input) {
        if (input.equals("dump") ) {
            data.dump();
        }
        else if (input.equals("duplicates") ) {
            ((PRQaudTreeDatabase)data).duplicates(); //nothing to do... just call method
        }
        else {
            String command = input.substring(0, input.indexOf(' '));
            if (command.equals("insert") ) {
                //I want what's left of the string, after the space
                String rectObj = everythingAfter1stSpace(input);
                String[] container = rectObj.split("\\s+");
                String name = container[0];
                int x = Integer.parseInt(container[1]);
                int y = Integer.parseInt(container[2]);

                Point sendMe = new Point(x, y);
                KVPair<String, Point> kvobj = new KVPair<>(name, sendMe);
                data.insert(kvobj);
            }
            else if (command.equals("remove" ) ) {
                //I want what's beyond the first space, in Rect_obj
                String rectObj = everythingAfter1stSpace(input);
                String[] container = rectObj.split("\\s+");
                if (container.length > 1) { //we were given coordinates
                    int x = Integer.parseInt(container[0]);
                    int y = Integer.parseInt(container[1]);
                    data.removeByValue(new Point (x, y) );
                }
                else {
                    data.remove(container[0]);
                }

            }
            else if (command.equals("regionsearch") ) {
                //I want what's left of the string... after the space
                String rectObj = everythingAfter1stSpace(input);
                String[] container = rectObj.split("\\s+");
                int x = Integer.parseInt(container[0]);
                int y = Integer.parseInt(container[1]);
                int w = Integer.parseInt(container[2]);
                int h = Integer.parseInt(container[3]);
                data.regionSearch( x, y, w, h);
            }
            else {
                //I want what's left of the string, this is search
                String rectObj = everythingAfter1stSpace(input);
                String[] container = rectObj.split("\\s"); //just the name
                data.search(container[0] );
            }
        }
        return "";
    }

    public String everythingAfter1stSpace(String line) {
        int findSpace = line.indexOf(' ') + 1;
        int lineLength = line.length();
        return line.substring(findSpace, lineLength ).trim();
    }
}
