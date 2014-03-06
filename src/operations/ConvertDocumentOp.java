package operations;

import javax.swing.JTextPane;

import models.KeywordConvertingModel;


public class ConvertDocumentOp extends Operation{

	private KeywordConvertingModel 	converterModel;
	private JTextPane				textArea;
	
	public ConvertDocumentOp(JTextPane textArea, KeywordConvertingModel converterModel){
		this.textArea = textArea;
		this.converterModel = converterModel;
	}
	
	public void setNewConverter(KeywordConvertingModel converterModel){
		this.converterModel = converterModel;
	}
	
	@Override
	public void executeOp() {
		if(converterModel != null){textArea.setText(converterModel.convert(textArea.getText()));}
	}

}
