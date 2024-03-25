package main.java.logic.users;

import main.java.model.fixtures.Light;
import main.java.model.openings.Door;
import main.java.model.openings.Window;
import main.java.model.rooms.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserPersistence {


    public UserPersistence(String filePath, ArrayList<User> users) {
        fetchUsers(filePath, users);
    }

    //Use permissions.add(Permissions.WINDOW) to add a permission to a user (for eg)

    public static void fetchUsers(String filePath, ArrayList<User> users) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line, name;
            User currentUser = null;
            List<Permissions> permissions = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("User:")) {
                    if (currentUser != null) {

                        currentUser.setPermissions(List.copyOf(permissions));
                        permissions.clear();

                        users.add(currentUser);
                    }
                    if (line.substring(6).trim().equalsIgnoreCase("parent")) {
                        currentUser = new Parent();
                    } else if (line.substring(6).trim().equalsIgnoreCase("child")) {
                        currentUser = new Child();
                    } else if (line.substring(6).trim().equalsIgnoreCase("guest")) {
                        currentUser = new Guest();
                    } else if (line.substring(6).trim().equalsIgnoreCase("stranger")) {
                        currentUser = new Stranger();
                    }

                } else if (currentUser != null) {

                    if (line.startsWith("Name:")) {
                        name = line.substring(6);
                        currentUser.setName(name);
                    } else if (line.startsWith("Window Permission:")) {
                        if (line.substring(19).trim().equalsIgnoreCase("true")) {
                            permissions.add(Permissions.WINDOW);
                        }

                    } else if (line.startsWith("Light Permission:")) {
                        if (line.substring(18).trim().equalsIgnoreCase("true")) {
                            permissions.add(Permissions.LIGHT);
                        }
                    } else if (line.startsWith("Tempature Permission:")) {
                        if (line.substring(22).trim().equalsIgnoreCase("true")) {
                            permissions.add(Permissions.TEMP);
                        }
                    } else if (line.startsWith("Door Permission:")) {
                        if (line.substring(17).trim().equalsIgnoreCase("true")) {
                            permissions.add(Permissions.DOOR);
                        }
                    }
                }
            }

            if (currentUser != null) {

                currentUser.setPermissions(List.copyOf(permissions));
                permissions.clear();

                users.add(currentUser); // Adds the last user

            }
            JOptionPane.showMessageDialog(null, "Users fetched!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Users cannot be fetched");
        }
    }

    public static void saveUsers(String filePath, ArrayList<User> users, String message) {

        try {
            File file = new File(filePath);

            // Create a new file everytime to save versions of different users
                file.createNewFile();


            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(fw);

            for (User user : users) {

                if (user instanceof Parent) {
                    writer.write("User: Parent");
                } else if (user instanceof Child) {
                    writer.write("User: Child");
                } else if (user instanceof Guest) {
                    writer.write("User: Guest");
                } else if (user instanceof Stranger) {
                    writer.write("User: Stranger");
                }

                writer.newLine();
                writer.write("Name: " + user.getName());
                writer.newLine();
                writer.write("Window Permission: " + user.getPermissions().contains(Permissions.WINDOW));
                writer.newLine();
                writer.write("Light Permission: " + user.getPermissions().contains(Permissions.LIGHT));
                writer.newLine();
                writer.write("Tempature Permission: " + user.getPermissions().contains(Permissions.TEMP));
                writer.newLine();
                writer.write("Door Permission: " + user.getPermissions().contains(Permissions.DOOR) + "\n");

                writer.newLine();
                writer.write("");
            }

            writer.close();

            JOptionPane.showMessageDialog(null, "Users saved!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Users cannot be saved");

        }


    }

}
