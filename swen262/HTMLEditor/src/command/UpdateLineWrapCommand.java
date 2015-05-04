package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tab.Tab;
import main.Display;
import main.MenuItemOption;
import main.StaticUtilities;

/**
 * UpdateLineWrapCommand is a concrete command object responsible for toggling
 * the auto line wrapping 
 */
public class UpdateLineWrapCommand implements CommandInterface, ActionListener {

    /**
     * Menu item observed for when to toggle
     */
    private MenuItemOption menuItemOption;
    
    /**
     * Concrete Command that observes a menu item to toggle auto line wrapping
     * @param menuItemOption - Menu item observed for when to toggle
     */
    public UpdateLineWrapCommand(MenuItemOption menuItemOption) {
        this.menuItemOption = menuItemOption;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        execute();
    }

    @Override
    public void execute() {
        StaticUtilities.toggleLineWrap();
        for(Tab t : Display.getDisplay().getTabPanel().getList()){
            t.getContent().getBuffer().setLineWrap( StaticUtilities.getLineWrap() );
        }
        if(StaticUtilities.getLineWrap()){
            menuItemOption.setIcon( StaticUtilities.onImage );
        }else{
            menuItemOption.setIcon( StaticUtilities.offImage );
        }
    }

}
