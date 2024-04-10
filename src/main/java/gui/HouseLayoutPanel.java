package main.java.gui;

import main.java.logic.layout.House;
import main.java.logic.layout.Layout;
import main.java.logic.modules.SHC;
import main.java.logic.modules.SHP;
import main.java.logic.modules.SHS;
import main.java.logic.observerPattern.Observable;
import main.java.logic.observerPattern.Observer;
import main.java.model.rooms.Outside;
import main.java.model.rooms.Room;
import main.java.model.rooms.zones.BathroomsZone;
import main.java.model.rooms.zones.BedroomsZone;
import main.java.model.rooms.zones.CommonZone;
import main.java.model.rooms.zones.GatewayZone;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class HouseLayoutPanel extends JPanel implements Observer {
    private ImageIcon windowOpenIcon, windowClosedIcon, doorOpenIcon, doorClosedIcon,
    lightOnIcon, lightOffIcon, userIcon, ACIcon, HeaterIcon, MotionDetectorIcon;
    private String tempInfo, userCountInfo, zoneInfo;
    private int rowHeight;
    private Color redColor;
    private SHS shs;
    private SHP shp;
    private SHC shc;


    private Boolean isAway = false;

    private double temperature;
    Layout extractLayout;
    House house = House.getInstance();

    public HouseLayoutPanel()  {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("House Layout"));
        windowOpenIcon = new ImageIcon("src/main/resources/houseLayoutIcons/WindowOpenIcon.png");
        doorOpenIcon = new ImageIcon("src/main/resources/houseLayoutIcons/DoorOpenIcon.png");
        lightOnIcon = new ImageIcon("src/main/resources/houseLayoutIcons/LightOnIcon.png");
        windowClosedIcon = new ImageIcon("src/main/resources/houseLayoutIcons/WindowClosedIcon.png");
        doorClosedIcon = new ImageIcon("src/main/resources/houseLayoutIcons/DoorClosedIcon.png");
        lightOffIcon = new ImageIcon("src/main/resources/houseLayoutIcons/LightOffIcon.png");
        userIcon = new ImageIcon("src/main/resources/houseLayoutIcons/UserIcon.png");
        ACIcon = new ImageIcon("src/main/resources/houseLayoutIcons/ACIcon.png");
        HeaterIcon = new ImageIcon("src/main/resources/houseLayoutIcons/HeaterOnIcon.png");
        MotionDetectorIcon = new ImageIcon("src/main/resources/houseLayoutIcons/MotionDetectorIcon.png");
        redColor = Color.RED;
        this.rowHeight = 150 + 10; // Assuming room height of 150 and 10 units of spacing
        // checkRoomInfo();
        updatePanelSize();
        shs = SHS.getInstance();
        shs.addObserver(this);
        for(Room room: shs.getHouseLayout()){
            room.getHvac().addObserver(this);
        }
    }

    private ImageIcon changeIconColor(ImageIcon icon, Color color) {
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.drawImage(icon.getImage(), 0, 0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
        g.dispose();
        return new ImageIcon(image);
    }


    private void checkRoomInfo(){
        for (Room room : house.getRooms()) {
            System.out.println("INFO: " + room.getName() + " --- Light: " + room.getLight().getLight() + "  --- Temp: " 
            + room.getCurrentTemperature() + "  --- Door: " + room.getDoor1().isOpen() + "  --- Window: " +
            room.getWindow(1).isOpen() + " --- Number Of Users " + room.getUserFromRoom().size()
            + " ---- Motion Detector " + room.getMotionDetector());
        }
    }

    private void updatePanelSize() {
        int totalRows = (int) Math.ceil((double) house.getRooms().size() / 2); // 2 rooms per row
        // Calculate total height needed based on the number of rooms
        int totalHeight = totalRows * rowHeight + 100;
        Dimension panelSize = new Dimension(670, totalHeight);
        setPreferredSize(panelSize);
        setMinimumSize(panelSize); 
        revalidate();
        repaint();
    }

    public void updateRoomIcon() {
        // Simply repaint the panel to update the icons based on the current state of the rooms
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int numRoomsPerRow = 2;
        int panelWidth = getWidth();
        int roomWidth = (panelWidth - 40) / numRoomsPerRow; // Subtract margins and divide by number of rooms
        int roomHeight = 150; // Keeping the height constant 
        int x = 10; // Initial x position
        int y = 30; // Initial y position
        int roomCounter = 0; // Counter to track the number of rooms in the current row
        for (Room room : house.getRooms()) {
             // Check if we've reached the number of rooms per row and need to start a new row
            if (roomCounter >= numRoomsPerRow) {
                x = 10; // Reset x position
                y += rowHeight + 15; // Move y position to the next row
                roomCounter = 0; // Reset room counter
            }
            g.drawRect(x, y, roomWidth, roomHeight); // Draw the room

            // Draw the room name centered above the rectangle
            FontMetrics fm = g.getFontMetrics();
            int nameWidth = fm.stringWidth(room.getName());
            int nameX = x + (roomWidth - nameWidth) / 2; // Center the name horizontally
            g.drawString(room.getName(), nameX, y - 5); // Position the name above the square


            int iconX = x + 15; // Position the icon inside the room
            int iconY = y + 15;

            if (!(room instanceof Outside)){ // Draw the first row of icons (Window and Door)
            if (room.getWindow(1).isOpen()) {
                windowOpenIcon.paintIcon(this, g, iconX, iconY);
                if(room.getWindow(1).getBlockedStatus()){
                    windowOpenIcon = changeIconColor(windowOpenIcon, redColor);
                }
            } else {
                windowClosedIcon.paintIcon(this, g, iconX, iconY);
                if(room.getWindow(1).getBlockedStatus()){
                    windowClosedIcon = changeIconColor(windowClosedIcon, redColor);
                }
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
            }}

            iconX += userIcon.getIconWidth() + 10; // Move to the right for the next icon

            // Draw the User icon
            if (room.getUserFromRoom().size() > 0) {
                userIcon.paintIcon(this, g, iconX, iconY);
            }


            // Draw the temperature and number of users to the right of the room box
            int infoX = iconX + 120; // Set a margin of 10 pixels from the room box
            int infoY = y + 30; // Align with the top of the room box
            temperature = room.getCurrentTemperature();
            if (!(room instanceof Outside)){
                tempInfo = "Temperature: " + String.format("%.1f",temperature);
            }
            userCountInfo = "Nb. Of Users: " + room.getUserFromRoom().size();
            if (!(room instanceof Outside)){
            if(room.getZone() instanceof CommonZone){
                zoneInfo = "Common Zone"; 
            }
            else if(room.getZone() instanceof BedroomsZone){
                zoneInfo = "Bedroom Zone"; 
            }
            else if(room.getZone() instanceof BathroomsZone){
                zoneInfo = "Bathroom Zone"; 
            }
            else if(room.getZone() instanceof GatewayZone){
                zoneInfo = "Gateway Zone"; 
            }
        }
            

            g.drawString(tempInfo, infoX, infoY);
            infoY += 15; // Add some vertical space between lines
            g.drawString(userCountInfo, infoX, infoY);
            infoY += 15; // Add some vertical space between lines
            g.drawString(zoneInfo, infoX, infoY);

            iconX += userIcon.getIconWidth() + 60; // Move to the right for the next icon

            if(room.getHvac().getHeatingOn()){
                HeaterIcon.paintIcon(this, g, iconX, iconY);
            } else if(room.getHvac().getCoolingOn()){
                ACIcon.paintIcon(this, g, iconX, iconY);
            }

            if (!(room instanceof Outside)){
            iconX += userIcon.getIconWidth() + 20; // Move to the right for the next icon


            // The icon displaying the motion detectors would display here
            if(room.getActiveMotionDetector())
                MotionDetectorIcon.paintIcon(this, g, iconX, iconY);
            }

            
            x += roomWidth + 10; // Increment x position for the next room
            roomCounter++; // Increment room counter
        }
        updatePanelSize();
    }

    @Override
    public void update(Observable o) {
        for (Room room : shs.getHouseLayout()) {
            temperature = room.getCurrentTemperature();
        }
        repaint();
    }
}
