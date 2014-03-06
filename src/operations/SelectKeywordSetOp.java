package operations;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import models.KeywordConvertingModel;

public class SelectKeywordSetOp extends Operation{
	
	private JFileChooser			jfc;
	private JFrame 					frame;
	private ConvertDocumentOp		convertDocOp;
	
	public SelectKeywordSetOp(JFrame frame, ConvertDocumentOp convertDocOp){
		this.frame = frame;
		jfc = new JFileChooser();
		this.convertDocOp = convertDocOp;
	}

	@Override
	public void executeOp() {
	    if(jfc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
	        convertDocOp.setNewConverter(new KeywordConvertingModel(jfc.getSelectedFile()));
	    }	
	}
}
