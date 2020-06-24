/**
 * 
 */
package com.pack;
import java.util.HashMap;

/**
 * @author User
 *
 */
public class OrderEntity 
{
	public int ScriptId=0;
	public int order=0;
	public static HashMap<String,String> ScriptOrderExecutionMap= new  HashMap<String,String>();
	
	public int getScriptId() {
		return ScriptId;
	}
	public void setScriptId(int scriptId) {
		this.ScriptId = scriptId;
	}
	public int getorder() {
		return order;
	}
	public void setorder(int order) {
		this.order = order;
	}
}
