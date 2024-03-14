package main.java.gui;

import java.awt.*;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import main.java.gui.ModulePanelTabs.SHCPanel;
import main.java.logic.layout.House;
import main.java.logic.modules.SHS;
import main.java.logic.users.*;
import main.java.logic.users.User;
import main.java.model.rooms.Room;

public class ModulePanel extends JPanel {
    
    private JTabbedPane tabbedPane;
    House house = House.getInstance();
    SHS shs = SHS.getInstance();
    User activateUser = shs.getActivetUser();
    List<User> listOfUsers = shs.getHouseUser();
    
    public ModulePanel(JLabel usernameDisplay, JLabel locationDisplay) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Modules"));

        // Create SHC Here
        SHCPanel SHCPanel = new SHCPanel();
        // Create SHS Panel
        JPanel shsPanel = createShsPanel(usernameDisplay, locationDisplay);

        // Module Tabs
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("SHC", new JScrollPane(SHCPanel));
        tabbedPane.addTab("SHP", new JLabel("SHP Content"));
        tabbedPane.addTab("SHH", new JLabel("SHH Content"));
        tabbedPane.addTab("SHS", new JScrollPane(shsPanel));

        add(tabbedPane, BorderLayout.CENTER);
    }

    // private void resizeButton(JToggleButton button) {
    //     button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
    // }

    private JPanel createShsPanel(JLabel usernameDisplay, JLabel locationDisplay){
        JPanel shsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        
        JPanel newPanel = createNewUserPanel();
        shsPanel.add(newPanel, gbc);
        JPanel deletePanel = createDeletePanel();
        shsPanel.add(deletePanel, gbc);

        return shsPanel;
    }

    private JPanel createNewUserPanel()
    {
        JPanel newPanel = new JPanel(new GridBagLayout());
        newPanel.setBorder(BorderFactory.createTitledBorder("Create New User Profile"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);//Add paddings

        JLabel usernameLabel = new JLabel ("Username: ");
        JLabel locationLabel = new JLabel ("Location: ");
        JLabel usertypeLabel = new JLabel ("User Type: ");
        
        JTextField usernameField = new JTextField (20);

        JMenuBar locationBar = new JMenuBar();
        JMenu locationMenu = new JMenu("Location");
        locationMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for (Room room: house.getRooms())
        {
            JMenuItem locationItem = new JMenuItem(room.getName());
            locationItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    locationMenu.setText(((JMenuItem) e.getSource()).getText());
                }
            });
            locationMenu.add(locationItem);
        }
        locationBar.add(locationMenu);

        JMenuBar usertypeBar = new JMenuBar();
        JMenu usertypeMenu = new JMenu("Usertype");
        usertypeMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        List<String> usertypes = Arrays.asList("Admin", "Child", "FamilyMember", "Guest", "Parent", "Stranger");
        for (String types: usertypes)
        {
            JMenuItem usertypeItem = new JMenuItem(types);
            usertypeItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    usertypeMenu.setText(((JMenuItem) e.getSource()).getText());
                }
            });
            usertypeMenu.add(usertypeItem);
        }
        usertypeBar.add(usertypeMenu);
        
        JButton submitButton = new JButton ("Create");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        newPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        newPanel.add(usernameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        newPanel.add(locationLabel, gbc);
        
        gbc.gridx = 1;
        newPanel.add(locationBar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        newPanel.add(usertypeLabel, gbc);
        
        gbc.gridx = 1;
        newPanel.add(usertypeBar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        newPanel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the JLabel in the other module
                String newUsername = usernameField.getText().trim();
                String newUserType = usertypeMenu.getText().trim();
                Room newRoom = shs.getRoomByName(locationMenu.getText());
                User newUser;
                switch (newUserType){
                    case "Admin":
                        newUser = new Admin(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    case "Child": 
                        newUser = new Child(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    case "FamilyMember":
                        newUser = new FamilyMember(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    case "Guest":
                        newUser = new Guest(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    case "Stranger":
                        newUser = new Stranger(newUsername, newRoom);
                        listOfUsers.add(newUser);
                        JOptionPane.showMessageDialog(null, "Create user " + newUser.getClass().getSimpleName() + " " + newUser.getName() + " succeed.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Create user failed. Please try again.");
                }
            }
        });

        return newPanel;
    }

    private JPanel createDeletePanel(){
        JPanel deletePanel = new JPanel(new GridBagLayout());
        deletePanel.setBorder(BorderFactory.createTitledBorder("Delete User Profile"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 5, 5, 5);//Add paddings
        
        JButton deleteButton = new JButton ("Delete");
        JButton updateButton = new JButton ("Update");
        JLabel profileLabel = new JLabel ("Choose user profile to delete");

        JMenuBar profileBar = new JMenuBar();
        JMenu profileMenu = new JMenu("Profile");
        profileMenu.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        for (User user: listOfUsers)
        {
            JMenuItem profileItem = new JMenuItem(user.getClass().getSimpleName() + " " + user.getName());
            profileItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    profileMenu.setText(((JMenuItem) e.getSource()).getText());
                }
            });
            profileMenu.add(profileItem);
        }
        profileBar.add(profileMenu);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.BOTH;
        deletePanel.add(profileLabel, gbc);

        gbc.gridx = 1;
        deletePanel.add(profileBar, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        deletePanel.add(deleteButton, gbc);

        //This button updates the list of users
        gbc.gridx = 1;
        deletePanel.add(updateButton, gbc);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the JLabel in the other module
                Iterator<User> iterator = listOfUsers.iterator();
                while (iterator.hasNext()){
                    User user = iterator.next();
                    if (profileMenu.getText().equals(user.getClass().getSimpleName() + " " + user.getName())){
                        user.exitRoom();
                        iterator.remove();
                    }
                }
                for (User user: listOfUsers){
                    System.out.println(user.toString());
                }
                profileMenu.removeAll();
                profileMenu.setText("Profile");
                for (User user: listOfUsers)
                {
                    JMenuItem profileItem = new JMenuItem(user.getClass().getSimpleName() + " " + user.getName());
                    profileItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            profileMenu.setText(((JMenuItem) e.getSource()).getText());
                        }
                    });
                    profileMenu.add(profileItem);
                }
                profileBar.revalidate();
                profileBar.repaint();
                
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the JLabel in the other module
                profileMenu.removeAll();
                profileMenu.setText("Profile");
                for (User user: listOfUsers)
                {
                    JMenuItem profileItem = new JMenuItem(user.getClass().getSimpleName() + " " + user.getName());
                    profileItem.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            profileMenu.setText(((JMenuItem) e.getSource()).getText());
                        }
                    });
                    profileMenu.add(profileItem);
                }
                profileBar.revalidate();
                profileBar.repaint();
            }
        });

        return deletePanel;
    }
}
