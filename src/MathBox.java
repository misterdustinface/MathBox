
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;

import writingTools.Notepad;

/**
 * This is a notepad program.  It reads .txt files.
 * @author Dustin
 */
public class MathBox extends Notepad{

    /////////////////////
    //    CONSTANTS    //
    /////////////////////
    
    final public static String PROGRAM_TITLE     = "MathBox - ";
    
    /////////////////////
    //    VARIABLES    //
    /////////////////////
    private static Menu 	 options;
    private static MenuItem  miSelectKeywordSet; 
    private static MenuItem  miConvert;// convert option
    //////////////////////////////////

    // MathBox stuff //////////////////
    private static KeywordConvertingModel converterModel;
    ///////////////////////////////////
    
    public MathBox(){
    	super();
    	super.setTitle(PROGRAM_TITLE);
    	initDisplay();
    	initActionListeners();
    }
    
    public static void main(String[] args) {
    	new MathBox();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    private void initDisplay()
    {
    	options = new Menu("Options");
    	
    	miSelectKeywordSet = new MenuItem("Select Keyword Set");
        miConvert = new MenuItem("Convert (F1)");

        options.add(miSelectKeywordSet);
        options.add(miConvert);
        
        super.addMenuToMenuBar(options);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////    
   
    private void initActionListeners()
    {
        miConvert.addActionListener(convertAction);
        miSelectKeywordSet.addActionListener(selectKeywordSetAction);
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    private void selectKeywordSetOp(){
        // Open up the file selection screen, if "Open" is pressed
        if(jfc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
            converterModel = new KeywordConvertingModel(jfc.getSelectedFile());
        }
    }
    
    private ActionListener selectKeywordSetAction = new ActionListener(){
    	 @Override
         public void actionPerformed(ActionEvent e) {
    		 selectKeywordSetOp();
    	 }
    };
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private void convertOp(){
    	textArea.setText(converterModel.convert(textArea.getText()));
    }
    
    private ActionListener convertAction = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			convertOp();
		}
    	
    };
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////// 
    
	protected void newWindowOp(){
		new MathBox();
	}
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    protected void keyPressedOp(KeyEvent e){
    	
    	super.keyPressedOp(e);
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_F1){
        	convertOp();
        }
    }
   
}