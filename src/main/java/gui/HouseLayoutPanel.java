package main.java.gui;

import main.java.model.rooms.Kitchen;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class HouseLayoutPanel extends JPanel {
    private ArrayList<Kitchen> rooms;
    private int rowHeight;

    public HouseLayoutPanel() {
        this.rooms = new ArrayList<>();
        this.rowHeight = 150 + 10; // Assuming room height of 150 and 10 units of spacing
    }

    public void addRoom(Kitchen room) {
        rooms.add(room);
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
        int x = 10; // Initial x position
        int y = 10; // Initial y position
        int maxWidth = getWidth() - 10; // Panel width minus some margin
        for (Kitchen room : rooms) {
            if (x + 150 > maxWidth) {
                x = 10; // Reset x position
                y += rowHeight; // Move y position to the next row
            }
            g.drawRect(x, y, 150, 150); // Draw the room
            x += 150 + 10; // Increment x position for the next room
        }
    }
}
