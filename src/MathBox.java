
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    private static MenuItem  miSelectKeywordSet; 
    private static MenuItem  miConvert;// convert option
    //////////////////////////////////

    // MathBox stuff //////////////////
    private static KeywordConvertingModel converterModel;
    ///////////////////////////////////
    
    public MathBox(){
    	main(null);
    }
    
    public static void main(String[] args) {
    	Notepad.main(args);
    	Notepad.setTitle(PROGRAM_TITLE);
    	initDisplay();
    	initActionListeners();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    private static void initDisplay()
    {
        miConvert = new MenuItem("Convert (F1)");
        miSelectKeywordSet = new MenuItem("Select Keyword Set");
        
        fileMenu.addSeparator();
        fileMenu.add(miConvert);
        fileMenu.add(miSelectKeywordSet);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////    
   
    private static void initActionListeners()
    {
        miConvert.addActionListener(convertAction);
        miSelectKeywordSet.addActionListener(selectKeywordSetAction);
        
        if(frame != null){
        	frame.addKeyListener(keyboardSpecialCommands);
        }
        textArea.addKeyListener(keyboardSpecialCommands);
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    private static void selectKeywordSetOp(){
        // Open up the file selection screen, if "Open" is pressed
        if(jfc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
            converterModel = new KeywordConvertingModel(jfc.getSelectedFile());
        }
    }
    
    private static ActionListener selectKeywordSetAction = new ActionListener(){
    	 @Override
         public void actionPerformed(ActionEvent e) {
    		 selectKeywordSetOp();
    	 }
    };
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private static void convertOp(){
    	textArea.setText(converterModel.convert(textArea.getText()));
    }
    
    private static ActionListener convertAction = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			convertOp();
		}
    	
    };
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    protected static KeyListener keyboardSpecialCommands = new KeyListener(){

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_F1){
            	convertOp();
            }    
        	Notepad.keyboardSpecialCommands.keyPressed(e);;

        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
    	
    };
   
}