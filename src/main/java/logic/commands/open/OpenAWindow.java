package main.java.logic.commands.open;

import main.java.logic.commands.Command;
import main.java.model.rooms.Room;

import main.java.logic.users.Permissions;

/**
 * Command to open a specific window in a room.
 * Implements the Command interface, allowing execution of the window-opening action.
 */
public class OpenAWindow extends Command{
    protected Room room; // The room in which a window will be opened
    protected int windowNumber; // The specific window number to be opened
    
    /**
     * Constructor for OpenAWindow command.
     * @param room The room containing the window to be opened.
     * @param num The number of the window to open.
     */
    public OpenAWindow(Room room, int num){
        this.room = room;
        windowNumber = num;
        this.REQUIRED_PERMISSIONS = Permissions.WINDOW;
    }

    public Room getRoom() {
        return this.room;
    }

    public int getWindowNumber() {
        return windowNumber;
    }
    /**
     * Returns the permissions required to execute this command.
     * Note: The REQUIRED_PERMISSIONS field is assumed to be defined elsewhere,
     * typically as a constant in either this class or a parent/interface.
     * @return The permissions required to execute the open window command.
     */
    @Override
    public Permissions requirePermissions(){
        return REQUIRED_PERMISSIONS;
    }

    /**
     * Executes the action of opening a specific window in the room.
     * The method prints the room's state before and after the window is opened.
     * @return Boolean value indicating the execution status (true for success).
     */
    @Override
    public Boolean execute(){
        // FIXME should remove those ssops after UI
        System.out.println("\n--------------------------------------------------------------------");
        System.out.println(room);
        System.out.print("command done: ");
        room.openWindow(windowNumber);
        System.out.println(room);
        System.out.println("----------------------------------------------------------------------");
        return true;
    }

    @Override
    public String toString(){
        return "open a window";
    }
}
