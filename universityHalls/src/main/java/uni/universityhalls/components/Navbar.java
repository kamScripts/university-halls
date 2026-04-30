package uni.universityhalls.components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Navbar extends MenuBar {

    private final Menu newMenu   = new Menu("New");
    private final Menu editMenu  = new Menu("Edit");
    private final Menu viewMenu  = new Menu("View");
    private final Menu toolsMenu = new Menu("Tools");
    private final Menu helpMenu  = new Menu("Help");

    //New sub-menu
    private final MenuItem newStudent = new MenuItem("Student");
    private final MenuItem newEmployee = new MenuItem("Employee");
    //Edit sub-menu
    private final MenuItem editHall = new MenuItem("Hall features");
    private final MenuItem editRoom = new MenuItem("Room features");
    //View sub-menu
    private final MenuItem viewHall = new MenuItem("Hall");
    private final MenuItem viewTenant = new MenuItem("Tenant");
    private final MenuItem viewRoom = new MenuItem("Room");
    //tools sub-menu
    private final MenuItem roomFinder = new MenuItem("Room Finder");
    private final MenuItem hallWizard = new MenuItem("Hall Wizard");
    private final MenuItem backupCenter = new MenuItem("Backup Center");
    //help sub-menu
    private final MenuItem manualHelp = new MenuItem("Manual");
    private final MenuItem aboutHelp = new MenuItem("About");

    public Navbar(){
        newMenu.getItems().addAll(newStudent,newEmployee);
        editMenu.getItems().addAll(editHall,editRoom);
        viewMenu.getItems().addAll(viewHall,viewRoom,viewTenant);
        toolsMenu.getItems().addAll(roomFinder, hallWizard, backupCenter);
        helpMenu.getItems().addAll(manualHelp,aboutHelp);
        this.getMenus().addAll(newMenu,editMenu,viewMenu,toolsMenu, helpMenu);
        //NAVBAR UNDER CONSTRUCTION
        this.setDisable(true);
    }

    public Menu getNewMenu() {
        return newMenu;
    }

    public Menu getEditMenu() {
        return editMenu;
    }

    public Menu getViewMenu() {
        return viewMenu;
    }

    public Menu getToolsMenu() {
        return toolsMenu;
    }

    public MenuItem getNewStudent() {
        return newStudent;
    }

    public MenuItem getNewEmployee() {
        return newEmployee;
    }

    public MenuItem getEditHall() {
        return editHall;
    }

    public MenuItem getEditRoom() {
        return editRoom;
    }

    public MenuItem getViewHall() {
        return viewHall;
    }

    public MenuItem getViewTenant() {
        return viewTenant;
    }

    public MenuItem getViewRoom() {
        return viewRoom;
    }

    public MenuItem getRoomFinder() {
        return roomFinder;
    }

    public Menu getHelpMenu() {
        return helpMenu;
    }

    public MenuItem getManualHelp() {
        return manualHelp;
    }

    public MenuItem getAboutHelp() {
        return aboutHelp;
    }

    public MenuItem getHallWizard() {
        return hallWizard;
    }

    public MenuItem getBackupCenter() {
        return backupCenter;
    }
}
