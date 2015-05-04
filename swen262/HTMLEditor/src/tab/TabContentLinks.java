package tab;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import command.ToggleLinkViewOrderCommand;
import command.UpdateLinkViewCommand;

public class TabContentLinks extends JScrollPane {
	
	public final int ORDER_BY_ALPAHBETICAL = 0;
	public final int ORDER_BY_OCCURANCE = 1;
	
	private ArrayList<Link> links;
	private int order = ORDER_BY_ALPAHBETICAL;

	private JPanel linksDisplay;
	
	private JPanel linkButtons;
	private JButton updateLinks;
	private JButton reorderLinks;
	
	public TabContentLinks(){
		super(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.linksDisplay = new JPanel();
		this.setViewportView( linksDisplay );
		this.linkButtons = new JPanel(new GridLayout());
		this.reorderLinks = new JButton("Reorder Links");
		this.reorderLinks.addActionListener( new ToggleLinkViewOrderCommand() );
		this.updateLinks = new JButton("Update Links");
		this.updateLinks.addActionListener( new UpdateLinkViewCommand() );
		this.linkButtons.setMaximumSize(new Dimension(4096,40));
		this.linkButtons.add(updateLinks, 0);
		this.linkButtons.add(reorderLinks, 1);
	}
	
	public void toggleOrder(){
	    if(order == ORDER_BY_ALPAHBETICAL){
	        order = ORDER_BY_OCCURANCE;
	    }else if(order == ORDER_BY_OCCURANCE){
	        order = ORDER_BY_ALPAHBETICAL;
	    }
	}
	
	public void updateLinks(String text){
		extractLinks(text);
		orderLinksAlphabetically();
		linksDisplay.removeAll();
		linksDisplay.setLayout( new BoxLayout(linksDisplay,BoxLayout.Y_AXIS));
		linksDisplay.add( linkButtons );
		JLabel newLabel;
        Box labelBox;
		if(order == ORDER_BY_ALPAHBETICAL){
    		int occurances;
            for(int i = 0;i<links.size();i++){
                occurances = links.get(i).getTotalOccurances();
                newLabel = new JLabel( ((occurances > 1)?occurances + ":":"") + links.get(i).getURL() + ((i == links.size() - 1)?"":","));
                labelBox = Box.createHorizontalBox();
                labelBox.add(newLabel);
                labelBox.add(Box.createHorizontalGlue());
                linksDisplay.add( labelBox );
            }
		}else if(order == ORDER_BY_OCCURANCE){
		    int totalLinks = 0;
		    for(int i = 0;i<links.size();i++){
		        totalLinks += links.get( i ).getTotalOccurances();
		    }
		    for(int occurance = 0;occurance < totalLinks;occurance++){
    		    for(int i = 0;i<links.size();i++){
    		        if(links.get( i ).getOccurances().contains( occurance )){
    		            newLabel = new JLabel(links.get(i).getURL() + ((occurance == totalLinks - 1)?"":","));
    		            labelBox = Box.createHorizontalBox();
    	                labelBox.add(newLabel);
    	                labelBox.add(Box.createHorizontalGlue());
    	                linksDisplay.add( labelBox );
    		        }
    		    }
		    }
		}
        linksDisplay.repaint();
	}
	
	private void extractLinks(String text){
		links = new ArrayList<Link>(0);
		int index = text.indexOf('<');
		int currentOccurance = -1;
		String url = "";
		Link newLink;
		while(index > -1){
			while(text.charAt(index + 1) == ' '){index++;}
			if(text.charAt(index + 1) == 'a'){
				while(text.charAt(index + 1) != '>' && text.charAt(index + 1) != '"'){index++;}
				foundLink:if(text.charAt(index + 1) == '"'){
					url = text.substring(index + 2, text.indexOf('"', index + 2)).trim();
					if(url.length() > 0){
						currentOccurance++;
						newLink = new Link(url, currentOccurance);
						for(Link l : links){
							if(l.equals(newLink)){
								l.addOccurance(currentOccurance);
								break foundLink;
							}
						}
						links.add(newLink);
					}
				}
			}
			index = text.indexOf('<', index + 1);
		}
	}
	
	private void orderLinksAlphabetically(){
		Link l1;
		Link l2;
		for(int i = 0;i<links.size();i++){
		    for(int j = 0;j<links.size();j++){
    			l1 = links.get(i);
    			l2 = links.get(j);
    			if(l1.compare(l2) < 0){
    				links.remove(i);
    				links.add(i,l2);
    				links.remove(j);
    				links.add(j, l1);
    			}
			}
		}
	}
	
}
