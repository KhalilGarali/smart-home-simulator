/* 
 * command pattern notes: 
 * 
 * 
 */

package rooms;

import java.util.ArrayList;
import java.util.List;

import openings.*;


public abstract class Room {

    // we can use it if we decide to have more openings in a room
    // private List<Opening> openings = new ArrayList<>();

    //making the assumption that the room can have max 2 openings of the same kind
    public Window window1;
    private Window window2;
    private Door door1;
    private Door door2;

    //default constructor
    public Room(){
    } 

    // single opening setters
    public void setWindows(Window window) {
        if(window1 == null){
            this.window1 = window;   
        } else if(window1 != null && window2 == null){
            this.window2 = window;
        } else {
            System.out.println("can't add more than 2 windows to a room!");
        }
    }
    public void setWindow1(Window window) {
        if(window1 == null){
            this.window1 = window;   
        } else if(window1 != null && window2 == null){
            this.window2 = window;
        } else {
            System.out.println("can't add more than 2 windows to a room!");
        }
    }
    public void setWindow2(Window window) {
        this.window2 = window;
    }
    public void setDoor1(Door door) {
        this.door1 = door;
    }
    public void setDoor2(Door door) {
        this.door2 = door;
    }

    //All openers and closers - will be useful with SHH and SHP
    public void openAllOpenings() {
        System.out.println("Open everything");
        window1.open();
        window2.open();
        door1.open();
        door2.open();
    }
    public void openAllWindows(){
        System.out.println("Open all windows");
        window1.open();
        window2.open();
    }
    public void openAllDoors(){
        System.out.println("Open all doors");
        door1.open();
        door2.open();
    }
    public void closeAllOpenings() {
        System.out.println("Close everything");
        window1.close();
        window2.close();
        door1.close();
        door2.close();
    }
    public void closeAllWindows(){
        System.out.println("Close all windows");
        window1.close();
        window2.close();
    }
    public void closeAllDoors(){
        System.out.println("Close all doors");
        door1.close();
        door2.close();
    }
    
    // single opening closers and openers
    public void closeWindow1(){
        System.out.println("Close window1");
        window1.close();
    }
    public void closeWindow2(){
        System.out.println("Close window2");
        window2.close();
    }
    public void closeDoor1(){
        System.out.println("Close door1");
        door1.close();
    }
    public void closeDoor2(){
        System.out.println("Close door2");
        door2.close();
    }
    public void openWindow1(){
        System.out.println("Open window1");
        window1.open();
    }
    public void openWindow2(){
        System.out.println("Open window2");
        window2.open();
    }
    public void openDoor1(){
        System.out.println("Open door1");
        door1.open();
    }
    public void openDoor2(){
        System.out.println("Open door2");
        door2.open();
    }   
}
