package main.java.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;

public class ModulePanel extends JPanel {
    private JTabbedPane tabbedPane;

    public ModulePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Modules"));
        tabbedPane = new JTabbedPane();

        // Create SHC Panel
        JPanel shcPanel = createShcPanel();

        // Add the SHC panel to the tabbed pane
        tabbedPane.addTab("SHC", new JScrollPane(shcPanel));
        tabbedPane.addTab("SHP", new JLabel("SHP Content"));
        tabbedPane.addTab("SHH", new JLabel("SHH Content"));
        // ... Add other tabs as needed

        add(tabbedPane, BorderLayout.CENTER);
    }

      private JPanel createShcPanel() {
        JPanel shcPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Items Panel
        JPanel itemsPanel = createItemsPanel();

        // Open/Close Panel
        JPanel openClosePanel = createOpenClosePanel();

        // Add the items and open/close panels to the SHC panel
        shcPanel.add(itemsPanel, gbc);
        shcPanel.add(openClosePanel, gbc);

        return shcPanel;
    }

    private JPanel createItemsPanel() {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Items"));

        // Create and add buttons
        JToggleButton windowsButton = new JToggleButton("Windows");
        JToggleButton lightsButton = new JToggleButton("Lights");
        JToggleButton doorsButton = new JToggleButton("Doors");

        ButtonGroup itemsGroup = new ButtonGroup();
        itemsGroup.add(windowsButton);
        itemsGroup.add(lightsButton);
        itemsGroup.add(doorsButton);

        // resize buttons
        resizeButton(windowsButton);
        resizeButton(lightsButton);
        resizeButton(doorsButton);

        itemsPanel.add(windowsButton);
        itemsPanel.add(lightsButton);
        itemsPanel.add(doorsButton);

        return itemsPanel;
    }

    private JPanel createOpenClosePanel() {
        JPanel openClosePanel = new JPanel();
        openClosePanel.setLayout(new BoxLayout(openClosePanel, BoxLayout.Y_AXIS));
        openClosePanel.setBorder(BorderFactory.createTitledBorder("Open/Close"));
        
        // Create and add checkboxes
        openClosePanel.add(new JCheckBox("Garage"));
        openClosePanel.add(new JCheckBox("Living Room"));
        openClosePanel.add(new JCheckBox("Backyard"));

        return openClosePanel;
    }

    private void resizeButton(JToggleButton button) {
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
    }
}
