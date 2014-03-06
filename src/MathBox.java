
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.KeyEvent;

import models.KeywordConvertingModel;
import operations.ConvertDocumentOp;
import operations.SelectKeywordSetOp;
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
    private Menu 	 options;
    private MenuItem miSelectKeywordSet; 
    private MenuItem miConvert;// convert option
    //////////////////////////////////

    // Operations ////////////////////
    private ConvertDocumentOp 	convertDoc;
    private SelectKeywordSetOp	selectKeywordSet;
    //////////////////////////////////
    
    // MathBox stuff //////////////////
    private KeywordConvertingModel converterModel;
    ///////////////////////////////////
    
    public MathBox(){
    	super();
    	initDisplay();
    	initActionListeners();
    	resetTitle();
    }
    
    public static void main(String[] args) {
    	new MathBox();
    }
    
    protected void resetTitle(){
    	frame.setTitle(PROGRAM_TITLE + getCurrentFileName());
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    
    private void initDisplay()
    {
    	options = new Menu("Options");
    	
    	miSelectKeywordSet 	= new MenuItem("Select Keyword Set");
        miConvert 			= new MenuItem("Convert (F1)");

        options.add(miSelectKeywordSet);
        options.add(miConvert);
        
        super.addMenuToMenuBar(options);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////    
   
    private void initActionListeners()
    {
    	convertDoc = new ConvertDocumentOp(textArea, converterModel);
        miConvert.addActionListener(convertDoc.getActionListener());

        selectKeywordSet = new SelectKeywordSetOp(frame, convertDoc);
        miSelectKeywordSet.addActionListener(selectKeywordSet.getActionListener());
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    protected void keyPressedOp(KeyEvent e){
    	
    	super.keyPressedOp(e);
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_F1){
        	convertDoc.executeOp();
        }
    }
   
}