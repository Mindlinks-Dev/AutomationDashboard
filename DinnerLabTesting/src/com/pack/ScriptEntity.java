/**
 * 
 */
package com.pack;

import java.util.HashMap;

/**
 * @author win
 *
 */
public class ScriptEntity {
	
	public static HashMap<Integer,String> HScenarios= new  HashMap<Integer,String>();
	public int ScriptId=0;
	public String ScriptName=null;
	public String ScriptInputFile=null;
	public String ScriptScenario=null;
	public String ScriptSequenceNo=null;
	public String ScriptStatus=null;
	public int ScriptCategoryId=0;
	public String ScriptCategoryName=null;
	public String ScriptPackageName = null;
	public int execid=0;
	public boolean containsFlow=false;
	public String FlowID;

	public int getScriptId() {
		return ScriptId;
	}
	public void setScriptId(int scriptId) {
		ScriptId = scriptId;
	}
	public String getScriptName() {
		return ScriptName;
	}
	public void setScriptName(String scriptName) {
		ScriptName = scriptName;
	}
	public String getScriptInputFile() {
		return ScriptInputFile;
	}
	public void setScriptInputFile(String scriptInputFile) {
		ScriptInputFile = scriptInputFile;
	}
	public String getScriptScenario() {
		return ScriptScenario;
	}
	public void setScriptScenario(String scriptScenario) {
		ScriptScenario = scriptScenario;
	}
	public String getScriptSequenceNo() {
		return ScriptSequenceNo;
	}
	public void setScriptSequenceNo(String scriptSequenceNo) {
		ScriptSequenceNo = scriptSequenceNo;
	}
	public String getScriptStatus() {
		return ScriptStatus;
	}
	public void setScriptStatus(String scriptStatus) {
		ScriptStatus = scriptStatus;
	}
	public int getScriptCategoryId() {
		return ScriptCategoryId;
	}
	public void setScriptCategoryId(int scriptCategoryId) {
		ScriptCategoryId = scriptCategoryId;
	}
	public String getScriptCategoryName() {
		return ScriptCategoryName;
	}
	public void setScriptCategoryName(String scriptCategoryName) {
		ScriptCategoryName = scriptCategoryName;
	}
	public String getScriptPackageName() {
		return ScriptPackageName;
	}
	public void setScriptPackageName(String scriptPackageName) {
		ScriptPackageName = scriptPackageName;
	}
	public int getExecid() {
		return execid;
	}
	public void setExecid(int execid) {
		this.execid = execid;
	}
	
	public boolean getcontainsFlow()
	{
		return containsFlow;
	}
	public void  setcontainsFlow(boolean containsFlow)
	{
		this.containsFlow = containsFlow;
	}
	public String getFlowID()
	{
		return FlowID;
	}
public void setFlowID(String FlowID)
	{
		this.FlowID=FlowID;
	}
	

	
	
	

}
