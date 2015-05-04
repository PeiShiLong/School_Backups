package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import validator.Validator;
import main.StaticUtilities;

/**
 * SaveCommand is a concrete command object responsible for saving a file
 * to the given filepath. If save is performed on a unsaved file, it should
 * be redirected to the saveas command structure.
 */
public class SaveCommand implements CommandInterface, ActionListener {

	//file io field
    private BufferedWriter saver;
    
    @Override
    /**
     * If the buffer is well formed and the file location is legitimate, the buffer is
     * written to the file. If the buffer isn't well formed, a dialog box appears in the
     * view warning the user that they're saving poor formed html code. They then decide
     * whether they want to continue saving or edit the file.
     */
    public void execute() {
        if(StaticUtilities.getCurrentTab().getFilePath() != null){
        	if(!Validator.getValidator().validate(StaticUtilities.getCurrentTab().getContent().getBuffer())){
        		if(JOptionPane.showConfirmDialog(null, 
        		        "Buffer isn't well formed! Continue saving?","WARNING - HTML Inconsistent", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION){return;}
        	}
        	save();
        }else{
            new SaveAsCommand().execute();
        }
    }
    
    /**
     * save() writes to the file and updates the tab to saved.
     */
    private void save(){
        try {
            saver = new BufferedWriter(new FileWriter(StaticUtilities.getCurrentTab().getFilePath()));
            saver.write( StaticUtilities.getCurrentTab().getContent().getBuffer().getText() );
            saver.close();
            StaticUtilities.getCurrentTab().updateToSaved();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * When the save command is selected in the view, execute will
     * be called.
     */
    public void actionPerformed(ActionEvent e) {
        execute();
    }

}
