package uni.universityhalls.components;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Navbar extends MenuBar {

    Menu newMenu = new Menu("New");
    Menu editMenu = new Menu("Edit");
    Menu viewMenu = new Menu("View");
    Menu toolsMenu = new Menu("Tools");
    //New sub-menu
    MenuItem studentMenu = new MenuItem("Student");
    MenuItem employeeMenu = new MenuItem("Employee");
    //Edit sub-menu
    //View sub-menu
    //tools sub-menu
    public Navbar(){
        newMenu.getItems().addAll(studentMenu,employeeMenu);
        this.getMenus().addAll(newMenu,editMenu,viewMenu,toolsMenu);
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

    public MenuItem getStudentMenu() {
        return studentMenu;
    }

    public MenuItem getEmployeeMenu() {
        return employeeMenu;
    }
}
