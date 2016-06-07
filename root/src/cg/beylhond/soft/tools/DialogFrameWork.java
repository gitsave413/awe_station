package cg.beylhond.soft.tools;

import java.util.List;
import java.util.Map;

public abstract class DialogFrameWork 
{
	/**
	* Open a view in dialog.
	* @param outcome The logical outcome used to resolve a navigation case.
	*/
	public abstract void openDialog(String outcome);
	
	/**
	* Open a view in dialog.
	* @param outcome The logical outcome used to resolve a navigation case.
	* @param options Configuration options for the dialog.
	* @param params Parameters to send to the view displayed in a dialog.
	*/
	public abstract void openDialog(String outcome, Map<String,Object> options,	Map<String,List<String>> params);
	
	/**
	* Close a dialog.
	* @param data Optional data to pass back to a dialogReturn event.
	*/
	public abstract void closeDialog(Object data);

}
