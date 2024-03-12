package main.java.gui;

import main.java.logic.layout.Layout;
import main.java.model.rooms.Kitchen;
import main.java.model.rooms.Room;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class HouseLayoutPanel extends JPanel {
    private ImageIcon windowOpenIcon, windowClosedIcon, doorOpenIcon, doorClosedIcon, lightOnIcon, lightOffIcon;
    private ArrayList<Room> rooms;
    private int rowHeight;
    

    public HouseLayoutPanel() {
        windowOpenIcon = new ImageIcon("src\\main\\resources\\houseLayoutIcons\\WindowOpenIcon.png");
        doorOpenIcon = new ImageIcon("src\\main\\resources\\houseLayoutIcons\\DoorOpenIcon.png");
        lightOnIcon = new ImageIcon("src\\main\\resources\\houseLayoutIcons\\LightOnIcon.png");
        windowClosedIcon = new ImageIcon("src\\main\\resources\\houseLayoutIcons\\WindowClosedIcon.png");
        doorClosedIcon = new ImageIcon("src\\main\\resources\\houseLayoutIcons\\DoorClosedIcon.png");
        lightOffIcon = new ImageIcon("src\\main\\resources\\houseLayoutIcons\\LightOffIcon.png");
        this.rooms = new ArrayList<>();
        this.rowHeight = 150 + 10; // Assuming room height of 150 and 10 units of spacing
        initializeRooms(); // Call this method to add rooms upon panel initialization
        checkRoomInfo();
    }

    private void checkRoomInfo(){
        for (Room room : this.rooms) {
            System.out.println("INFO: " + room.getName() + " --- Light: " + room.getLight().getLight() + "  --- Temp: " 
            + room.getTemp().getTemperature() + "  --- Door: " + room.getDoor1().isOpen() + "  --- Window: " + room.getWindow(1).isOpen());
        }
    }

    private void initializeRooms() {
        Layout extractLayout = new Layout("src/main/java/logic/modules/houseLayoutFile.txt");
        ArrayList<Room> fileRooms = (ArrayList<Room>) extractLayout.getRooms();
        for (Room room : fileRooms) {
            addRoom(room);
        }
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
        updatePanelSize();
        repaint(); // Trigger the paintComponent to redraw with the new room
    }

    private void updatePanelSize() {
        // Calculate total height needed based on the number of rooms and the row height
        int rows = (rooms.size() / 4) + 1;
        int totalHeight = this.rowHeight * rows;
        setPreferredSize(new Dimension(670, totalHeight));
        revalidate(); // Revalidate the layout after changing the size
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int roomWidth = 150;
        int roomHeight = 150;
        int x = 10; // Initial x position
        int y = 20; // Initial y position
        int maxWidth = getWidth() - 10; // Panel width minus some margin
        for (Room room : rooms) {
            if (x + roomWidth > maxWidth) {
                x = 10; // Reset x position
                y += rowHeight + 10; // Move y position to the next row
            }
            g.drawRect(x, y, roomWidth, roomHeight); // Draw the room

             // Draw the room name centered above the square
            FontMetrics fm = g.getFontMetrics();
            int nameWidth = fm.stringWidth(room.getName());
            int nameX = x + (roomWidth - nameWidth) / 2; // Center the name horizontally
            g.drawString(room.getName(), nameX, y - 5); // Position the name above the square


            int iconX = x + 15; // Position the icon inside the room
            int iconY = y + 15;

             // Draw the first row of icons (Window and Door)
            if (room.getWindow(1).isOpen()) {
                windowOpenIcon.paintIcon(this, g, iconX, iconY);
            } else {
                windowClosedIcon.paintIcon(this, g, iconX, iconY);
            }
            iconX += windowOpenIcon.getIconWidth() + 10; // Move to the right for the next icon
            
            if (room.getDoor1().isOpen()) {
                doorOpenIcon.paintIcon(this, g, iconX, iconY);
            } else {
                doorClosedIcon.paintIcon(this, g, iconX, iconY);
            }
            
            // Move to the second row for the Light icon
            iconX = x + 15; // Reset iconX for the second row
            iconY += windowOpenIcon.getIconHeight() + 15; // Move iconY down to the second row
            
            // Draw the Light icon
            if (room.getLight().getLight()) {
                lightOnIcon.paintIcon(this, g, iconX, iconY);
            } else {
                lightOffIcon.paintIcon(this, g, iconX, iconY);
            }

            x += roomWidth + 10; // Increment x position for the next room
        }
    }
}
