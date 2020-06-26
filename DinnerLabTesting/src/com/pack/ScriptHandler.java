/**
 * Author:Basappa Hunsikatti
 * Created date:13-07-2015
 * Modified date:
 * 
 */
package com.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.dinnerLab.constants.TestConstants;

import com.dinnerLab.util.BaseClass;
import com.dinnerLab.util.Console;
import com.dinnerLab.util.CopyExcelData;
import com.dinnerLab.util.ExcelLib;
import com.dinnerLab.util.HTMLToExcel;
import com.dinnerLab.util.JmeterReport;
import com.dinnerLab.util.OutputReport;
import com.dinnerLab.util.OutputReportArchiveFile;
//import com.dynamicload.LoadClass;
import com.jmeter.batchFile.JmeterBatchFile;
import com.zap.exefile.ZapExecutionFile;

//import cohbainessandbox.LoadClassNew;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**Purpose:Fetching the value passed from dashboard and running the respective script
 */
@Test
public class ScriptHandler 
{
	boolean flag = false;
	//Connection con = null;
    //Statement stm=null;
    //ResultSet rs=null;
    String endTime=null;
    String startTime=null;
    String startDateTime=null;
    String executedDateTime=null;
    String scriptstarttime=null;
    String archiveInputLocation=null;
    String excelReportArchiveLocation=null;
    String reportURL =null;
    String TestNGFilePath=null;
    public static int scriptIdFlag =0;
   // ScriptResult scripResult = new ScriptResult();
    SimpleDateFormat format = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
    public static String result;
	//public static String comment;
	public static ArrayList<String> comment = new ArrayList<String>();
	public static int executionId;
	public static int ScriptID;
	public static int ProjectID;
	int printscriptStartTime=0;
	public static int runIncrement;
	 private static Logger log = Logger.getLogger(ScriptHandler.class);
			
	public void execute(ArrayList<ScriptEntity> scriptlist,int execId) throws Exception
	{ 
		
		Connection con = null;
	    Statement stm=null;
	    ResultSet rs=null;
		con = Database.getConnection();
		//System.out.println("value of Con ::"+con);
		stm=con.createStatement();
		executionId=execId;
		ArrayList<String> temp = new ArrayList<String>();
		//creating custom xml file
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		System.out.println("id in script handler class "+execId);
		//DOMImplementation domImpl = document.getImplementation();
		//DocumentType doctype = domImpl.createDocumentType("doctype","-//Oberon//YOUR PUBLIC DOCTYPE//EN", "YOURDTD.dtd");
		// root elements
		Element rootElement = doc.createElement("suite");
		doc.appendChild(rootElement);
		
		Attr attr1 = doc.createAttribute("name");
		attr1.setValue("Suite1");
		rootElement.setAttributeNode(attr1);
		//attr1 = doc.createAttribute("parallel");
		//attr1.setValue("false");		
		rootElement.setAttributeNode(attr1);
		
	   	// test elements
		Element test = doc.createElement("test");
		rootElement.appendChild(test);
 
		// set attribute to test element
		attr1 = doc.createAttribute("name");
		attr1.setValue("test1");
		test.setAttributeNode(attr1);
		
		//classes Elements
		Element classes = doc.createElement("classes");
		test.appendChild(classes);
		       		
		Iterator<ScriptEntity> itr = scriptlist.iterator();
		for(int i=0;i<scriptlist.size();i++)
		{
			ScriptID=scriptlist.get(i).ScriptId;
			ProjectID=scriptlist.get(i).Project_Id;
			System.out.println("PROJECTID>>>>"+ProjectID);
			//System.out.println("Working inside script handler loop ::"+i);
			int ScriptID=scriptlist.get(i).ScriptId;
			int ProjectID=scriptlist.get(i).Project_Id;
			System.out.println("after assigning to integer inside script handler ScriptID:"+ScriptID);
			System.out.println("after assigning to integer inside script handler ProjectID:"+ProjectID);
			//System.out.println("after assigning to integer inside script handler ScriptID:"+ScriptID);
			// updating the starttime in executiondetails table
			scriptstarttime = "UPDATE executiondetails SET starttime=now()  WHERE Project_Id="+ProjectID+" and scriptid="+ScriptID+" and execid="+execId;
			System.out.println("scriptstarttime:"+scriptstarttime);
			printscriptStartTime=stm.executeUpdate(scriptstarttime);
			System.out.println("Script start time :"+printscriptStartTime);
			
			//Retrieving the starttime value from executiondetails table
			rs=stm.executeQuery("SELECT starttime from executiondetails WHERE Project_Id="+ProjectID+" and scriptid="+ScriptID+" and execid="+execId);
			while(rs.next())
			{			
			startTime=rs.getString(1);
            }
			
			result = null;
			
			
			scriptIdFlag = scriptlist.get(i).ScriptId;
			String Script_Name = scriptlist.get(i).ScriptName;
			String Script_Packagename = scriptlist.get(i).ScriptPackageName;
			
			
			System.out.println("scriptIdFlag:"+scriptIdFlag);
			System.out.println("Class Name inside ScrptHandler is:"+Script_Name);
			System.out.println("Package Name inside ScrptHandler is:"+Script_Packagename);
			
			//Switch Case to select TestNg 
			switch(scriptlist.get(i).ScriptId)
    		{
    			case 1:
    				Element class1 = doc.createElement("class");
    				classes.appendChild(class1);
    				//set attribute to class element
    				attr1 = doc.createAttribute("name");
    				attr1.setValue("com.dinnerLab.adminTests.DinnerCallTest");
    				class1.setAttributeNode(attr1);
    				break;
				
    			case 2:
    				Element class2 = doc.createElement("class");
					classes.appendChild(class2);
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.FreeMemberSignUpTest");
					class2.setAttributeNode(attr1);
					break;
					
    			case 3:
    				Element class3 = doc.createElement("class");
					classes.appendChild(class3);
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.PaidMemberSignUpTest");
					class3.setAttributeNode(attr1);
					break;
    			case 4:	
    				Element class4 = doc.createElement("class");
	    	   		classes.appendChild(class4);
	    	   		//set attribute to class element
	    	   		attr1 = doc.createAttribute("name");
	    	   		attr1.setValue("com.dinnerLab.userTests.ChefSignUpTest");
	    	   		class4.setAttributeNode(attr1);
	    	   		break;
    			case 5: 
    				Element class5 = doc.createElement("class");
					classes.appendChild(class5);
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.FreeMemberTicketPurchaseTest");
					class5.setAttributeNode(attr1);
					break;
    			case 6: 
    				Element class6 = doc.createElement("class");
					classes.appendChild(class6);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.PaidMemberTicketPurchaseTest");
					class6.setAttributeNode(attr1);
					break;
    			case 7:
    				Element class7 = doc.createElement("class");
					classes.appendChild(class7);
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.ChefTicketPurchaseTest");
					class7.setAttributeNode(attr1);
					break;
					
    			case 8:
    				Element class8 = doc.createElement("class");
					classes.appendChild(class8);
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.HybridFlow");
					class8.setAttributeNode(attr1);
					break;
    			case 9:	
    				Element class9 = doc.createElement("class");
	    	   		classes.appendChild(class9);
	    	   		//set attribute to class element
	    	   		attr1 = doc.createAttribute("name");
	    	   		attr1.setValue("com.dinnerLab.userTests.GiftPurchaseTest");
	    	   		class9.setAttributeNode(attr1);
	    	   		break;
    			case 10: 
    				Element class10 = doc.createElement("class");
					classes.appendChild(class10);
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.ReferralThroughInviteLinkTest");
					class10.setAttributeNode(attr1);
					break;
    			case 11: 
    				Element class11 = doc.createElement("class");
					classes.appendChild(class11);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.ReferralMemberUpgradeAccountTest");
					class11.setAttributeNode(attr1);
					break;
    			case 12:
    				Element class12 = doc.createElement("class");
					classes.appendChild(class12);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.MemberLoginLogoutTest");
					class12.setAttributeNode(attr1);
					break;
    			case 13:
    				Element class13 = doc.createElement("class");
					classes.appendChild(class13);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.CreateUserTool");
					class13.setAttributeNode(attr1);
					break;
    			case 14:
    				Element class14 = doc.createElement("class");
					classes.appendChild(class14);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.MembershipExtendTest");
					class14.setAttributeNode(attr1);
					break;
    			case 15:
    				Element class15 = doc.createElement("class");
					classes.appendChild(class15);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.UserGenerationCodesTest");
					class15.setAttributeNode(attr1);
					break;
    			case 16:
    				Element class16 = doc.createElement("class");
					classes.appendChild(class16);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.CreateNewEventTest");
					class16.setAttributeNode(attr1);
					break;
    			case 17:
    				Element class17 = doc.createElement("class");
					classes.appendChild(class17);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.BuyingTicketsTest");
					class17.setAttributeNode(attr1);
					break;
    			case 18:
    				Element class18 = doc.createElement("class");
					classes.appendChild(class18);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.CreateCustomEventLinksTest");
					class18.setAttributeNode(attr1);
					break;
    			case 19:
    				Element class19 = doc.createElement("class");
					classes.appendChild(class19);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.UserAccountInfoUpdateTest");
					class19.setAttributeNode(attr1);
					break;
    			case 20:
    				Element class20 = doc.createElement("class");
					classes.appendChild(class20);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.UserAccountChangePasswordTest");
					class20.setAttributeNode(attr1);
					break;
    			case 21:
    				Element class21 = doc.createElement("class");
					classes.appendChild(class21);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.AddNewCreditCardInfoTest");
					class21.setAttributeNode(attr1);
					break;
    			case 22:
    				Element class22 = doc.createElement("class");
					classes.appendChild(class22);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.DietaryRestrictionTest");
					class22.setAttributeNode(attr1);
					break;
    			case 23:
    				Element class23 = doc.createElement("class");
					classes.appendChild(class23);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.MyEventsTest");
					class23.setAttributeNode(attr1);
					break;
    			case 24:
    				Element class24 = doc.createElement("class");
					classes.appendChild(class24);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.ManageCitiesTest");
					class24.setAttributeNode(attr1);
					break;
    			case 25:
    				Element class25 = doc.createElement("class");
					classes.appendChild(class25);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.ManageGuestsTest");
					class25.setAttributeNode(attr1);
					break;
    			case 26:
    				Element class26 = doc.createElement("class");
					classes.appendChild(class26);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.ChangeUserTypeTest");
					class26.setAttributeNode(attr1);
					break;
    			case 27:
    				Element class27 = doc.createElement("class");
					classes.appendChild(class27);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.AddUserToWaitlistTest");
					class27.setAttributeNode(attr1);
					break;
    			case 28:
    				Element class28 = doc.createElement("class");
					classes.appendChild(class28);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.RefundMembershipTest");
					class28.setAttributeNode(attr1);
					break;
    			case 29:
    				Element class29 = doc.createElement("class");
					classes.appendChild(class29);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.GiftCreditTest");
					class29.setAttributeNode(attr1);
					break;
    			case 30:
    				Element class30 = doc.createElement("class");
					classes.appendChild(class30);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.ReferFriendsTest");
					class30.setAttributeNode(attr1);
					break;
    			case 31:
    				Element class31 = doc.createElement("class");
					classes.appendChild(class31);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.ViewEditTicketsTest");
					class31.setAttributeNode(attr1);
					break;
    			case 32:
    				Element class32 = doc.createElement("class");
					classes.appendChild(class32);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.ChangeHomeCityTest");
					class32.setAttributeNode(attr1);
					break;
    			case 33:
    				Element class33 = doc.createElement("class");
					classes.appendChild(class33);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.ViewEditCreditsTest");
					class33.setAttributeNode(attr1);
					break;
    			case 34:
    				Element class34 = doc.createElement("class");
					classes.appendChild(class34);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.GiftOverviewTest");
					class34.setAttributeNode(attr1);
					break;
    			case 35:
    				Element class35 = doc.createElement("class");
					classes.appendChild(class35);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.ChefBioInformationUpdateTest");
					class35.setAttributeNode(attr1);
					break;
    			case 36:
    				Element class36 = doc.createElement("class");
					classes.appendChild(class36);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.EventStaffAvailabilityUpdateTest");
					class36.setAttributeNode(attr1);
					break;
    			case 37:
    				Element class37 = doc.createElement("class");
					classes.appendChild(class37);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.adminTests.MemberStatisticsTest");
					class37.setAttributeNode(attr1);
					break;
    			case 38:
    				Element class38 = doc.createElement("class");
					classes.appendChild(class38);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.jmeter.batchFile.JmeterBatchFile");
					class38.setAttributeNode(attr1);
					JmeterBatchFile.runJMeter();
					break;
    			case 39:
    				Element class39 = doc.createElement("class");
					classes.appendChild(class39);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.tronixss.userTests.CreateNewEntityTest");
					class39.setAttributeNode(attr1);
					break;
    			case 40:
    				Element class40 = doc.createElement("class");
					classes.appendChild(class40);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.dinnerLab.userTests.SignupDinnerlabTest");
					class40.setAttributeNode(attr1);
					break;
    			case 41:
    				Element class41 = doc.createElement("class");
					classes.appendChild(class41);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.stos.usersPage.OpportunityTest");
					class41.setAttributeNode(attr1);
					break;
    			case 42:
    				Element class42 = doc.createElement("class");
					classes.appendChild(class42);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.zap.exefile.ZapExecutionFile");
					class42.setAttributeNode(attr1);
					ZapExecutionFile.runZAp();
					break;
    			case 43:
    				Element class43 = doc.createElement("class");
					classes.appendChild(class43);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.jmeter.batchFile.JmeterBatchFile");
					class43.setAttributeNode(attr1);
					JmeterBatchFile.runJMetereLift();
					break;
    			case 44:
    				Element class44 = doc.createElement("class");
					classes.appendChild(class44);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.gee.testClasses.CreateEditCampaignsTest");
					class44.setAttributeNode(attr1);
					break;
    			case 45:
    				Element class45 = doc.createElement("class");
					classes.appendChild(class45);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.gee.testClasses.GenerateProposalTest");
					class45.setAttributeNode(attr1);
					break;
    			case 46:
    				Element class46 = doc.createElement("class");
					classes.appendChild(class46);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.gee.testClasses.RevenueReportTest");
					class46.setAttributeNode(attr1);
					break;
    			case 47:
    				Element class47 = doc.createElement("class");
					classes.appendChild(class47);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateEditAccountTest");
					class47.setAttributeNode(attr1);
					break;
				case 48:
    				Element class48 = doc.createElement("class");
					classes.appendChild(class48);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateEditProductsTest");
					class48.setAttributeNode(attr1);
					break;
				case 49:
    				Element class49 = doc.createElement("class");
					classes.appendChild(class49);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateEditPurchaseOrdersTest");
					class49.setAttributeNode(attr1);
					break;
				case 50:
    				Element class50 = doc.createElement("class");
					classes.appendChild(class50);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateLotTest");
					class50.setAttributeNode(attr1);
					break;
				case 51:
    				Element class51 = doc.createElement("class");
					classes.appendChild(class51);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateInventoryLocationTest");
					class51.setAttributeNode(attr1);
					break;
				case 52:
    				Element class52 = doc.createElement("class");
					classes.appendChild(class52);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateStockReceivingTest");
					class52.setAttributeNode(attr1);
					break;
				case 53:
    				Element class53 = doc.createElement("class");
					classes.appendChild(class53);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateStockAdjustmentsTest");
					class53.setAttributeNode(attr1);
					break;
				case 54:
    				Element class54 = doc.createElement("class");
					classes.appendChild(class54);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateStockMovementsTest");
					class54.setAttributeNode(attr1);
					break;
				case 55:
    				Element class55 = doc.createElement("class");
					classes.appendChild(class55);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateNewPackagesTest");
					class55.setAttributeNode(attr1);
					break;
				case 56:
    				Element class56 = doc.createElement("class");
					classes.appendChild(class56);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateShipmentsTest");
					class56.setAttributeNode(attr1);
					break;
				case 57:
    				Element class57 = doc.createElement("class");
					classes.appendChild(class57);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.elift.testClasses.CreateNewTransactionTypeBillAndSalesInvoiceTest");
					class57.setAttributeNode(attr1);
					break;
				case 58:
    				Element class58 = doc.createElement("class");
					classes.appendChild(class58);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.autoparts.userclass.CreateEditQuotationTest");
					class58.setAttributeNode(attr1);
					break;	
				case 59:
    				Element class59 = doc.createElement("class");
					classes.appendChild(class59);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.autoparts.userclass.CreateQuotationPartsTest");
					class59.setAttributeNode(attr1);
					break;
				case 60:
    				Element class60 = doc.createElement("class");
					classes.appendChild(class60);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.autoparts.userclass.CreateOrderTest");
					class60.setAttributeNode(attr1);
					break;
				case 61:
    				Element class61 = doc.createElement("class");
					classes.appendChild(class61);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.autoparts.userclass.CreateOrderPartsTest");
					class61.setAttributeNode(attr1);
					break;
				case 62:
    				Element class62 = doc.createElement("class");
					classes.appendChild(class62);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.SigmaERP.testclass.CreateLotClass");
					class62.setAttributeNode(attr1);
					break;
				case 63:
    				Element class63 = doc.createElement("class");
					classes.appendChild(class63);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.SigmaERP.testclass.CreateStockReceivingClass");
					class63.setAttributeNode(attr1);
					break;				
				case 64:
    				Element class64 = doc.createElement("class");
					classes.appendChild(class64);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.SigmaERP.testclass.CreateStockmovementClass");
					class64.setAttributeNode(attr1);
					break;
				case 65:
    				Element class65 = doc.createElement("class");
					classes.appendChild(class65);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.SigmaERP.testclass.CreatePackageClass");
					class65.setAttributeNode(attr1);
					break;
				case 66:
    				Element class66 = doc.createElement("class");
					classes.appendChild(class66);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.SigmaERP.testclass.CreateShipmentsClass");
					class66.setAttributeNode(attr1);
					break;
				case 67:
    				Element class67 = doc.createElement("class");
					classes.appendChild(class67);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.cohbainies.testclasses.LoginLogout");
					class67.setAttributeNode(attr1);
					break;
				case 68:
    				Element class68 = doc.createElement("class");
					classes.appendChild(class68);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.jmeter.batchFile.Openjmeterbatchfile");
					class68.setAttributeNode(attr1);
					break;
				case 69:
    				Element class69 = doc.createElement("class");
					classes.appendChild(class69);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					Thread.sleep(5000);					
					attr1.setValue("SalesForce.TestPages.CreateNewLeadLightingTestPage");					
					class69.setAttributeNode(attr1);
					break;
				case 70:
    				Element class70 = doc.createElement("class");
					classes.appendChild(class70);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.src.test.java.com.cohtestcases.Create_product");
					class70.setAttributeNode(attr1);
					break;
				case 71:
    				Element class71 = doc.createElement("class");
					classes.appendChild(class71);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.src.test.java.com.cohtestcases.Productprice");
					class71.setAttributeNode(attr1);
					break;
				case 72:
    				Element class72 = doc.createElement("class");
					classes.appendChild(class72);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.src.test.java.com.cohtestcases.Create_transaction");
					class72.setAttributeNode(attr1);
					break;	
				case 73:
    				Element class73 = doc.createElement("class");
					classes.appendChild(class73);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.src.test.java.com.cohtestcases.Create_StockReceiving");
					class73.setAttributeNode(attr1);
					break;
				case 74:
    				Element class74 = doc.createElement("class");
					classes.appendChild(class74);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.src.test.java.com.cohtestcases.Create_Stapporder");
					class74.setAttributeNode(attr1);
					break;
				case 75:
    				Element class75 = doc.createElement("class");
					classes.appendChild(class75);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.src.test.java.com.cohtestcases.Create_package");
					class75.setAttributeNode(attr1);
					break;
				case 76:
    				Element class76 = doc.createElement("class");
					classes.appendChild(class76);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.alignsandbox.userclass.UserSignupClass");
					class76.setAttributeNode(attr1);
					break;
				case 77:
    				Element class77 = doc.createElement("class");
					classes.appendChild(class77);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.alignsandbox.userclass.UserLoginClass");
					class77.setAttributeNode(attr1);
					break;
				case 78:
    				Element class78 = doc.createElement("class");
					classes.appendChild(class78);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.alignsandbox.userclass.CreateDailyHuddleClass");
					class78.setAttributeNode(attr1);
					break;
				case 79:
    				Element class79 = doc.createElement("class");
					classes.appendChild(class79);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.alignsandbox.userclass.CreateWeeklyHuddleClass");
					class79.setAttributeNode(attr1);
					break;
				case 80:
    				Element class80 = doc.createElement("class");
					classes.appendChild(class80);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.alignsandbox.userclass.CreatePrioritiesClass");
					class80.setAttributeNode(attr1);
					break;
				case 81:
    				Element class81 = doc.createElement("class");
					classes.appendChild(class81);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.alignsandbox.userclass.UserAlignIntegrationclass");
					class81.setAttributeNode(attr1);
					break;
				
				case 82:
    				Element class82 = doc.createElement("class");
					classes.appendChild(class82);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.sigmaERP_Lightening.CreateLoginClass");
					class82.setAttributeNode(attr1);
					break;
					
				case 83:
    				Element class83 = doc.createElement("class");
					classes.appendChild(class83);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("securiTax.javaprogram.Securitaxclass");
					class83.setAttributeNode(attr1);
					break;
					
					
				case 84:
    				Element class84 = doc.createElement("class");
					classes.appendChild(class84);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.quicksolar.userclass.Userloginlogoutclass");
					class84.setAttributeNode(attr1);
					break;	
				
				case 85:
    				Element class85 = doc.createElement("class");
					classes.appendChild(class85);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.securitax.userclass.Securitaxclass");
					class85.setAttributeNode(attr1);
					break;	
				
					
				case 86:
    				Element class86 = doc.createElement("class");
					classes.appendChild(class86);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.src.test.java.com.cohtestcases.StockAdjustmentBulkFinancial");
					class86.setAttributeNode(attr1);
					break;	
					
				case 87:
    				Element class87 = doc.createElement("class");
					classes.appendChild(class87);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.sigmaERP_Lightening.CreateLoginClass");
					class87.setAttributeNode(attr1);
					break;	
					
				case 88:
    				Element class88 = doc.createElement("class");
					classes.appendChild(class88);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.sigmaERP_Lightening.CreatePurchaseOrderPage");
					class88.setAttributeNode(attr1);
					break;	
					
				case 89:
    				Element class89 = doc.createElement("class");
    				System.out.println("entered class");
					classes.appendChild(class89);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					System.out.println("attribute");
					attr1.setValue("cohbainessandbox.Completeintegration");
					class89.setAttributeNode(attr1);
					System.out.println("package");
					break;		
					
					
				case 90:
    				Element class90 = doc.createElement("class");
					classes.appendChild(class90);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.src.test.java.com.cohtestcases.Create_Account");
					class90.setAttributeNode(attr1);
					break;	
					
				case 91:
    				Element class91 = doc.createElement("class");
					classes.appendChild(class91);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("SalesForce.TestPages.CreatestandardobjectsTestPage");
					class91.setAttributeNode(attr1);
					break;	
					
				case 92:
    				Element class92 = doc.createElement("class");
					classes.appendChild(class92);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("SalesForce.TestPages.CreateCase");
					class92.setAttributeNode(attr1);
					break;	
					
				case 93:
    				Element class93 = doc.createElement("class");
					classes.appendChild(class93);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.align.userclass.UserAlignIntegrationclassdemo2");
					class93.setAttributeNode(attr1);
					break;
					
				case 95:
    				Element class95 = doc.createElement("class");
					classes.appendChild(class95);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("Securitax.initialregistration");
					class95.setAttributeNode(attr1);
					break;
					
				case 96:
    				Element class96 = doc.createElement("class");
					classes.appendChild(class96);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.Sigma.TCCancelRun");
					class96.setAttributeNode(attr1);
					break;
					
				case 97:
    				Element class97 = doc.createElement("class");
					classes.appendChild(class97);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.Sigma.TCAutoallocation");
					class97.setAttributeNode(attr1);
					break;
					
				case 98:
    				Element class98 = doc.createElement("class");
					classes.appendChild(class98);  
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					attr1.setValue("com.Sigma.TCAutoallocation");
					class98.setAttributeNode(attr1);
					break;
					
					
					
					
					

				default:
					//accessing different package class 
					Element class99 = doc.createElement("class");
					classes.appendChild(class99);
					attr1 = doc.createAttribute("name");
					/*LoadClassNew load=new LoadClassNew();
					load.classLoading();*/

					String packageclass = Script_Packagename+"."+Script_Name;
					attr1.setValue(packageclass);

					System.out.println("packageclass::" +packageclass);
					System.out.println("after setvalue inside scheduler:::");
					class99.setAttributeNode(attr1);
					break;
					
					/*Element class99 = doc.createElement("class");
					classes.appendChild(class99); 
					System.out.println("inside default::::");
					//set attribute to class element
					attr1 = doc.createAttribute("name");
					//attr1.setValue("com.upload.external.scriptlist.get(i).ScriptName");
					Script_Name =Script_Packagename+"."+Script_Name;
					System.out.println("Package and classname::::"+Script_Name);
					//com.upload.external.CreatestandardobjectsTestPage
					attr1.setValue(Script_Name);*/
					
					
    		}//switch end
			
			//System.out.println("after executing switch statements126");
			//listeners Elements
			Element listeners = doc.createElement("listeners");
			rootElement.appendChild(listeners);
			
			//listener elements
			Element listener = doc.createElement("listener");
			listeners.appendChild(listener);
			attr1 = doc.createAttribute("class-name");
			attr1.setValue("atu.testng.reports.listeners.ATUReportsListener");
			listener.setAttributeNode(attr1);
			
			//listener elements
			Element listener1 = doc.createElement("listener");
			listeners.appendChild(listener1);
			attr1 = doc.createAttribute("class-name");
			attr1.setValue("atu.testng.reports.listeners.ConfigurationListener");
			listener1.setAttributeNode(attr1);
			
			//listener elements
			Element listener2 = doc.createElement("listener");
			listeners.appendChild(listener2);
			attr1 = doc.createAttribute("class-name");
			attr1.setValue("atu.testng.reports.listeners.MethodListener");
			listener2.setAttributeNode(attr1);
			
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			//TestNGFilePath =AutomateLoader.config.getProperty("TESTNG_PATH");
			TestNGFilePath =AutomateLoader.config.getProperty("TESTNG_FILE");
			//TestNGFilePath="E:/DinnerLab_WorkSpace/DinnerLabAutomationDashboard/DinnerLabTesting/testng.xml";
			StreamResult tempStreamResult = new StreamResult(new File(TestNGFilePath));
			
			transformer.transform(source, tempStreamResult);
		}//end for loop
		
		//running testNG.xml file
		System.out.println("testng");
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		System.out.println("");
		suites.add(TestNGFilePath);
		testng.setTestSuites(suites);
		System.out.println("suites:"+suites);
		System.out.println("run the testng file");
		testng.run();
		
		
		// Copying output parameters from atu reports into the excel
		// sheet,Date:14-06-2018
		int run1 = reportValidate();// calling reportValidate method 23-7-2015
		System.out.println("run1" + run1);
		HTMLToExcel.report_Link = run1;
		System.out.println("run before calling copyHTMLToExcelReportActions:" + run1);
		
		//Copying output parameters into the  jmeter excel sheet,Date:03-06-2016
		JmeterReport jmeterReport = new JmeterReport();
		jmeterReport.jmeterReportActions(execId);
				
		//Loading the file location to the execution table,Date:23-07-2015
		//System.out.println("before calling result path ");
		 Date d1 = new Date();
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
         String todaysDate = sdf.format(d1);
		 String resultPath="Outputreport_"+run1+"_"+todaysDate+".xlsx";		
         //"Outputreport_13_15-Jun-18.xlsx"
		
		//System.out.println("after setting result path value : "+resultPath);
		archiveInputLocation = "UPDATE execution SET resultfile='"+resultPath+"' WHERE  Id="+execId;
		//System.out.println("after archieve input location ::"+archiveInputLocation);
		
		
		//Creating new file and copying data from console-----------console report file creation------------------------
		Console console = new  Console();		
		console.main1(reportValidate());
				
				
		int printarchiveInputfile=stm.executeUpdate(archiveInputLocation);		
		//System.out.println("value of print archive input file ::"+printarchiveInputfile);
		//Loading the Excel report file location to the execution table,Date:19-08-2015
		
		String excelReportPath="ConsoleReport_"+run1+new SimpleDateFormat("'.html'").format(new Date())+"";
		//System.out.println("reportPath:::::"+excelReportPath);
		excelReportArchiveLocation = "UPDATE execution SET ExcelReport='"+excelReportPath+"' WHERE  Id="+execId;
		//System.out.println("excelReportArchiveLocation:"+excelReportArchiveLocation);
		int printarchiveExcelReportfile=stm.executeUpdate(excelReportArchiveLocation);
		//System.out.println("printarchiveExcelReportfile:"+printarchiveExcelReportfile);
		
		
		
		// updating the execution table for status , ExecutedDateTime
			/*	try 
		    	{

		    		String executedby = "UPDATE execution SET executedBy='"+LoginAuthenticate.name+"' WHERE Id = "+execId;
		    		int printQuery= stm.executeUpdate(executedby);
		    		
		
		    	} //End of try 
		    	catch (SQLException e) 
		    	{
			
		    		e.printStackTrace();
		    	}*/
				
		// updating the execution table for status , ExecutedDateTime
		try 
    	{

    		String updateQuery = "UPDATE execution SET status='COMPLETED' , ExecutedDateTime=now() WHERE Id = "+execId;
    		int printQuery= stm.executeUpdate(updateQuery);
    			
    	} //End of try 
    	catch (SQLException e) 
    	{
	
    		e.printStackTrace();
    	}
		

    	//Retrieving the StartDateTime and executedDateTime value from execution table
    	rs=stm.executeQuery("SELECT StartDateTime,ExecutedDateTime from execution WHERE Id="+execId);
    	while(rs.next())
    	{			
    		startDateTime = rs.getString(1);
    		executedDateTime = rs.getString(2);
     	}  
	
	// Custom date format
	Date d11 = null;
	Date d21 = null;
	try 
	{
		d11 = format.parse(startDateTime);
		d21 = format.parse(executedDateTime);
	}catch (Exception e)
	{
		e.printStackTrace();
	}
	
	long diff1 = d21.getTime() - d11.getTime();
	long exetimeinsec = diff1 / 1000 % 60;  
	long exetimeinnmin = diff1 / (60 * 1000) % 60; 
	long diffHours = diff1 / (60 * 60 * 1000) % 24;
	String newTotalTime = diffHours+"h:"+exetimeinnmin+"m:"+exetimeinsec+"s";
	
	
	//System.out.println("exetimetakenInMinSec:"+exetimetakenInMinSec);
	//updating the timetaken,overall_result,comments , Report_number value in execution table 22-07-2015
		try
		{
			
			
			String ResultsValue = validate(temp);
					
			String FinalComments = commentsValidate(comment);
			
			int run = reportValidate();// calling reportValidate method 23-7-2015
			
			String updateexection = "UPDATE execution SET timetaken='"+newTotalTime+"' , overall_result='"+result+"' , Comments='"+comment+"' ,Report_No='"+run+"' WHERE Id="+execId;
			//System.out.println("updateexection:"+updateexection);
			int count=stm.executeUpdate(updateexection);
		}
		catch(SQLException e)
		{
			//e.printStackTrace();
		}
		finally
		{
			rs.close();
            stm.close();
            con.close();
		}
	}//End of Execution Method.
	
	//method for fetching the overall_result
	public String validate(List<String> lst)
	{
		String val = "PASS";
		for (String tsr : lst) 
		{
			if(tsr == "FAIL")
			{
				val = "FAIL";
			 
			}
		}
		return val;
	}

	//Method for fetching the overall comments 
	public String commentsValidate(List<String> lst)
	{
		String TempComments="";
		for (String temp : lst)
		{
			TempComments = TempComments+""+temp;
		}
		return TempComments;
	}
	
	//method for reading the settings.properties file from ATU reports 23-7-2015
	public int reportValidate() throws IOException
	{
		String tempRun;
	    //To fetch a Run Number from settings.properties file(ATU) 22-07-2015
	   // Properties prop=new Properties();
	//FileInputStream inputStream = new FileInputStream(new File("E:/DinnerLab_WorkSpace/DinnerLabAutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/DinnerLabTesting/config.properties"));

	   Properties setting = new Properties();
		FileInputStream inputSettingsgStream = new FileInputStream(new File(AutomateLoader.SettingFile));
		setting.load(inputSettingsgStream);
		//System.out.println("inputSettingsgStream:"+inputSettingsgStream);
		
		tempRun = setting.getProperty("run");
		//System.out.println("tempRun:"+tempRun);
	    runIncrement=(Integer.parseInt(tempRun)+1);
	   // System.out.println("runIncrement:"+runIncrement);
	          
	    viewReportPage(runIncrement);
	    return runIncrement;
	}
	
	EditPieChartContents pieChart = new EditPieChartContents();
	
	public String viewReportPage(String runIncrement) 
	{
		 //return reportURL ="http://localhost:8080/DinnerLabAutomationDashboard/Report/Results/Run_"+runIncrement+"+/CurrentRun.html";
		
		String URL=AutomateLoader.config.getProperty("REPORT_URL");
		return reportURL =URL+runIncrement+"+/CurrentRun.html";
	}
	
	public String viewReportPage(int runIncrement) {
		 //return reportURL ="http://localhost:8080/DinnerLabAutomationDashboard/Report/Results/Run_"+runIncrement+"+/CurrentRun.html";
		 
		 String URL=AutomateLoader.config.getProperty("REPORT_URL");
		 			
		 //System.out.println("URL:"+URL);
		// System.out.println("reportURL:"+reportURL);
		 return reportURL =URL+runIncrement+"+/CurrentRun.html";
	}
	
	
	/*public void pieChartActions()
	{
		 System.out.println("Before Pie Chart:");
		 pieChart.editPieChartContentsActions();
		 System.out.println("After Pie Chart:");
	}
	*/
	
	
	
	
	
	//Email Notification code(27-Aug-2015)
	public void sendEmail(ArrayList<String> ExecutedScriptNames,String emailAdress,String UserName) throws Exception
	{
		
		//log.info("Inside ScriptHandler's SendEmail():" +"email Adrress is :"+emailAdress +"UserName is :"+UserName);
		//log.info("Inside ScriptHandler's sendEmail(): Executed ScriptNames are :"+ExecutedScriptNames);	
		//System.out.println("Email Address to Send Email  inside ScriptHandler.java :"+emailAdress);
		//System.out.println("HI '"+UserName+"' \n\n Your Automation Execution Request is Completed,And You Have Executed Following Scripts :\n\n '"+ExecutedScriptNames+"' \n\n For Details, Login To Portal and check RunNo :'"+RunNo+"' Email Id to Send is:'"+emailAdress+"' ");
		// sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        // *** BEGIN CHANGE
        properties.put("mail.smtp.user","testquicksolar@gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");	
        Session session = Session.getInstance(properties,new javax.mail.Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication
        		("testquicksolar@gmail.com","testquicksolar");
            }
         });
        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session.getProperties().put("mail.smtp.starttls.enable", "true");
        try 
        {
        	
        	
        	
           	 Message message = new MimeMessage(session);
            // message.setFrom(new InternetAddress("gmail.com"));
             message.setFrom(new InternetAddress("testquicksolar@gmail.com"));
             
            // message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("ssranjitha1228@gmail.com"));//Recipient Email Address:
             message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(LoginAuthenticate.email));//Recipient Email Address:
             System.out.println("Entered Email Inside scripthandler in sendEmail method "+LoginAuthenticate.email );
             
             // Set Subject: header field
             message.setSubject("Automation Script Execution Completion Details");
             // Create the message part
             BodyPart messageBodyPart = new MimeBodyPart();

             int Run2 = reportValidate();
             int Run3 = Run2-1;
             System.out.println("Run3::" + Run3);
			// Now set the actual message
            // messageBodyPart.setText("Hi "+UserName+" \n\n Your Automation Execution Request is Completed,And You Have Executed Following Scripts :\n\n "+ExecutedScriptNames+" \n \n For Details, Login To Portal and check RunNo :'"+Run3+"' \n \n Please do find the attached Output Report File \n \n  Regards, \n Mindlinks Solution Automation");
              messageBodyPart.setText("Hi, \n\n Find the attached results of the script \n\n Regards, \n Mindlinks Solution Automation");

             // Create a multipar message
             Multipart multipart = new MimeMultipart();

             // Set text message part
             multipart.addBodyPart(messageBodyPart);

             // Part two is attachment
             messageBodyPart = new MimeBodyPart();
             
             // String path = AutomateLoader.config.getProperty("OUTPUT_REPORT_ARCHIVE_LOCATION");
             //changes  made on 10-Nov-2015 client dont want excel report but instead he need QAInputOutput result file
             
             //String path = AutomateLoader.config.getProperty("NEW_INPUTFILE_DOWNLOAD_LOCATION");
             String path = AutomateLoader.config.getProperty("OUTPUT_REPORT_ARCHIVE_LOCATION");
             //System.out.println("path is :"+path);
             //System.out.println("executionId is :"+executionId);
             String excelReportDBFileName = null;
             Connection con1 = null;
             ResultSet resultset  = null;
             try
                {
            	 	con1=Database.getConnection();
            	 	//System.out.println("con1 value ::"+con1);
                     Statement st= con1.createStatement();
                     //System.out.println("statement value ::"+st);
                  	 //resultset = st.Query("SELECT ExcelReport from execution where Id="+executionId+" " );
                     
                     //changes  made on 10-Nov-2015 client dont want excel report but instead he need QAInputOutput result file
                     
                  	 resultset = st.executeQuery("SELECT resultfile from execution where Id="+executionId+" " );
                  	 //System.out.println("value of result set:"+resultset);
                  	 while(resultset.next())
                  	 {
                  		//excelReportDBFileName = resultset.getString("ExcelReport");
                  		excelReportDBFileName = resultset.getString(1);
                  	 }
             }
             catch(Exception ex)
             	{
                	 ex.printStackTrace();
                }
             finally
                 {
                	 //resultset.close();
                	 con1.close();
                 }
                
             String fullPath = path+excelReportDBFileName;
             System.out.println("fullPath:"+fullPath);
             //System.out.println("excelReportDBFileName is :"+excelReportDBFileName);
            //System.out.println("full path is :"+fullPath);
             DataSource source = new FileDataSource(fullPath);
            System.out.println("source is :"+source);
             messageBodyPart.setDataHandler(new DataHandler(source));
             messageBodyPart.setFileName(excelReportDBFileName);
             multipart.addBodyPart(messageBodyPart);

             // Send the complete message parts
             message.setContent(multipart);

             // Send message
             //Transport transport = session.getTransport("smtp"); 
             //transport.send(message);
             Transport.send(message);
           
             System.out.println("Email Has been Sent SuccessFully");
			}catch(Exception ex)
            {
				log.info("*********Log Ends here for ScriptHandler **************************");
				ex.printStackTrace();
				System.out.println("EXception during email send :"+ex.getMessage());
            
            }
        
   	
    }
        
	}//end of SendEmail()

	



	
 

