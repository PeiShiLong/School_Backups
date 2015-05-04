package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MenuItemOption;
import main.StaticUtilities;

/**
 * UpdateAutoIndent Command is a concrete command object responsible for
 * updating the global variable that says whether or not to auto indent on
 * new lines
 */
public class UpdateAutoIndentCommand implements ActionListener,CommandInterface {

    /**
     * The menu item the will be observed
     */
    private MenuItemOption menuItemOption;
    
    /**
     * Adds the menu item to be observed
     * @param menuItemOption - the menu item being observed
     */
    public UpdateAutoIndentCommand(MenuItemOption menuItemOption) {
        this.menuItemOption = menuItemOption;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        execute();
    }

    @Override
    public void execute() {
        StaticUtilities.toggleAutoIndent();
        if(StaticUtilities.getAutoIndent()){
            menuItemOption.setIcon( StaticUtilities.onImage );
        }else{
            menuItemOption.setIcon( StaticUtilities.offImage );
        }
    }
}
