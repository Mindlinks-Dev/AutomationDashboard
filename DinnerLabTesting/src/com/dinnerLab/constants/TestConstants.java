 /**
 * @author: Basappa Hunsikatti


 * @Created Date :14/07/2015
 * @Updated Date :
 * @Comments This class will serve the constants which are used in the whole project
 */

package com.dinnerLab.constants;

/**
* Method for declaring the constants required for the project
*/
public class TestConstants
{
	public static final String Browser_Chrome = "CH";
	public static final String Browser_IE = "IE";
	public static final String Browser_Firefox="FF";   
	//public static final String LogIn_Url ="https://dev1.dinnerlab.com/login/?verify=boop";
	//public static final String SignUp_Url ="https://dev1.dinnerlab.com/signup/?verify=boop";
	public static final String Promotion_Code = "Please Enter Promotion Code";
	//public static final String Payment_Url="http://dev1.dinnerlab.com/signup/payment/";		
	//public static final String SFLOGIN_URL="https://test.salesforce.com/";
	public static final String UseDifferentCard = "yes";
	public static final String SaveButton = "save";
	public static final String Dietary_Pescetarian = "pescetarian";
	public static final String Dietary_Vegetarian = "vegetarian";
	public static final String Dietary_Vegan = "vegan";
	public static final String Dietary_NoneOfThese = "none of these";
	public static final String Allergies_Peanuts = "peanuts";
	public static final String Allergies_Shellfish = "shellfish";
	public static final String Allergies_TreeNuts = "tree nuts";
	public static final String Allergies_Lactose = "lactose";
	public static final String Allergies_Gluten = "gluten";
	public static final String Allergies_NoneOfThese = "none of these";
	public static final String Allergies_SelectAll = "all";
	public static final String DoYouHaveOtherDietaryRestrictOrAllergies = "yes";
	//public static final String DoYouWantToUseDifferentCard = "yes";
	public static final String DinnerCredits50 = "50";
	public static final String DinnerCredits100 = "100";
	public static final String DinnerCredits150 = "150";
	public static final String DinnerCredits200 = "200";
	public static final String ContinueAsGuest = "yes";
	public static final String MemberTypeFree = "free";
	public static final String MemberTypePaid = "paid";
	public static final String saveCardInformation = "no";
	public static final String STATUS_YES = "yes";
	public static final String STATUS_NO = "no";
	public static final String DIETARY_VEGETARIAN = "Vegetarian";
	public static final String DIETARY_VEGAN = "Vegan";
	public static final String ALLERGY_TREENUTS = "Treenuts";
	public static final String ALLERGY_SHELLFISH = "Shellfish";
	public static final String ALLERGY_PESCETARIAN = "Pescetarian";
	public static final String ALLERGY_PEANUTS = "Peanuts";
	public static final String ALLERGY_LACTOSE = "Lactose";
	public static final String ALLERGY_GLUTEN = "Gluten";
	
	//Below code will contain all script constants
	public static final int SFDC_DINNER_CREATION=1;
	public static final int FREE_MEMBERSHIP_SIGN_UP=2;  
	public static final int PAID_MEMBERSHIP_SIGN_UP=3; 
	public static final int CHEF_SIGN_UP=4;
	public static final int FREE_MEMBER_TICKET_PURCHASE=5;
	public static final int PAID_MEMBER_TICKET_PURCHASE=6;
	public static final int CHEF_TICKET_PURCHASE=7;
	public static final int HYBRID_FLOW=8;
	public static final int GIFT_PURCHASE=9;
	public static final int REFERRAL_THROUGH_INVITE_LINK=10;
	public static final int REFERRAL_THROUGH_UPGRADE_ACCOUNT=11;
	public static final int LOGIN=12;
	public static final int CREATE_USER_TOOL=13;
	public static final int MEMBERSHIP_EXTEND=14;
	public static final int USER_GENERATION_CODES=15;
	public static final int CREATE_NEW_EVENT=16;
	public static final int BUYING_TICKETS=17;
	public static final int CREATE_CUSTOM_EVENT_LINKS=18;
	public static final int USER_ACCOUNT_INFO_UPDATE=19;
	public static final int USER_ACCOUNT_CHANGE_PASSWORD=20;
	public static final int ADD_NEW_CREDIT_CARD=21;
	public static final int DIETARY_RESTRICTIONS=22;
	public static final int MY_EVENTS=23;
	public static final int MANAGE_CITIES=24;
	public static final int MANAGE_GUESTS=25;
	public static final int CHANGE_USER_TYPE=26;
	public static final int ADD_USER_TO_WAITLIST=27;
	public static final int REFUND_MEMBERSHIP=28;
	public static final int GIFT_CREDIT=29;
	public static final int REFER_FRIENDS=30;
	public static final int VIEW_EDIT_TICKETS=31;
	public static final int CHANGE_HOME_CITY=32;
	public static final int VIEW_EDIT_CREDITS=33;
	public static final int GIFT_OVERVIEW=34;
	public static final int CHEF_BIO_INFO_UPDATE=35;
	public static final int EVENT_STAFF_AVAILABILITY_UPDATE=36;
	public static final int MEMBER_STATISTICS = 37;
	public static final int SIGNUP_DINNERLAB = 40;
	public static final int CHEF_SIGNUP=4;
	public static final int PAYMENT=5;
	public static final int MEMBER_PROFILE=6;
	public static final int TICKET=7;
	public static final int MEMBER_GIFTPURCHASE=8;
	public static final int NONMEMBER_GIFTPURCHASE=9;
	public static final String INPUT_ARCHIVED_FILES_PATH="D:/AutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/DinnerLabTesting/InputArchivedFiles/"+"QAInput_";
	//public static final String InputExcelFilePath1="E:\\DinnerLab_WorkSpace\\DinnerLabAutomationDashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\DinnerLabTesting\\UploadedInputFiles\\Data.xls\\";
	//public static final String InputExcelFilePath="C:\\Automation\\DinnerLab_WorkSpace\\DinnerLabAutomationDashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\DinnerLabTesting\\UploadedInputFiles\\Data.xls";
	//public static final String InputExcelFilePath="D:/DinnerLabWorkSpace/DinnerLabTesting/src/Data.xls";
	public static final String InputExcelFilePath="D:/AutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/DinnerLabTesting/UploadedInputFiles/Book1.xlsx";
	public static final String TestNGFilePath="D:/AutomationDashboard/DinnerLabTesting/testng.xml";
	//public static final String CONFIG_FILE_PATH = "D:/DinnerLabWorkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/DinnerLabTesting/config.properties";--modified by spoo
	public static final String CONFIG_FILE_PATH = "D:/AutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/DinnerLabTesting/config.properties";
	//public static final String SETTINGS_FILE_PATH = "E:/DinnerLab_WorkSpace/DinnerLabAutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/DinnerLabTesting/Report/Results/Settings.properties";//Previous
	//public static final String SETTINGS_FILE_PATH = "D:/DinnerLabWorkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/DinnerLabTesting/Report/Results/Settings.properties";//modified by spoo
	public static final String SETTINGS_FILE_PATH = "D:/AutomationDashboard/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/DinnerLabTesting/Report/Results/Settings.properties";
	//public static final String CHROME_BROWSER_EXE_FILE_PATH = "E:/DinnerLab_Workspace/DinnerLabAutomationDashboard/DinnerLabTesting/src/chromedriver.exe"; 
	//public static final String IE_BROWSER_EXE_FILE_PATH = "E:/DinnerLab_Workspace/DinnerLabAutomationDashboard/DinnerLabTesting/src/IEDriverServer_32.exe";
	public static final String HTTPS="https://";
	public static final String HTTP="http://";
	public static final String LOGIN_URL="/login/?verify=boop";
	public static final String SIGNUP_URL="/signup/?verify=boop";
	public static final String LOG_IN="/login/";
	public static final String SIGN_UP="/signup/selector/";
	public static final String SUPER_ADMIN_USER_GEN ="/superadmin/usergen/";
	public static final String DEFAULT_FLOWS="-1";
	public static final int MEMBER_REGISTRATION=1;
	public static final int DIETARY_RESTRICTION=2;
	public static final int UPGRADE_ACCOUNT=3;
	public static final int MEMBER_TICKET_PURCHASE=4;
	public static final String TICKET_SOLD_OUT = "Sold Out";
	public static final String TICKET_AVAILABLE = "Available";
	public static final String BUTTON_STATE_SOLD_OUT = "Sold Out";
	public static final String BUTTON_STATE_PURCHASE = "Purchase";
	public static final String RESULT_PASS = "Pass";
	public static final String RESULT_FAIL = "Fail";
	public static final String USER_TYPE_MEMBER = "Member";
	public static final String SELECT_MEMBER = "Select Member";
	public static final String SELECT_GIFT_TYPE_MEMBERSHIP = "Gift Select Membership";
	public static final String SELECT_GIFT_CREDIT = "Gift Credit";
	public static final String DOLLAR_ZERO = "$0.00";
	public static final String NUMBER_ONE="1";
	public static final String MATCH_SEATING_CAPACITY_WITH_LIMIT = "Match seating capacity with limit";
	public static final String SUPERSEDE_SEATING_CAPACITY = "Supersede seating capacity";
	public static final String STRIPE_LOGIN_URL = "https://dashboard.stripe.com/test/customers";
	public static final String REFUND_STATUS="TRUE";
	//Column number in excel report
	public static final int col=2;
	//End of script constants
	
	//Create User constants
	public static final String STATUS_TRUE="TRUE";
	public static final String STATUS_FALSE="FALSE";
	public static final String SelectMember="SELECT MEMBER";
	public static final String PAID_MEMBER="PAID MEMBER";
	public static final String FREE_USER="FREE USER";
	public static final String DONATED="DONATED";
	public static final String GIFTED="GIFTED";
	public static final String CREATE_USER_TOOL_URL="/superadmin/user-transactions/create-users/";
	public static final String MEMBERSHIP_EXTEND_URL="/superadmin/memberships/extend/";
	//public static final String FERRARI_URL="ferrari1.dinnerlab.com";
	public static final String RELEASE1_URL="release1.dinnerlab.com";
	public static final String SUPER_ADMIN_URL="/superadmin/";
	public static final String EVENTS="/events/";
	public static final String FORWARD_SLASH="/";
	public static final String NOT_REDEEMED="(Not Redeemed)";

	//Salesforce constants
	public static final String BROWSER_CHROME = "CH";
	public static final String BROWSER_IE = "IE";
	public static final String BROWSER_FIREFOX="FF";
	public static final String SIGNUP_URL_SALES ="https://dev1.dinnerlab.com/signup/?verify=boop";
	public static final String PROMOTION_CODE = "Please Enter Promotion Code";
	public static final String PAYMENT_URL="http://dev1.dinnerlab.com/signup/payment/";		
	public static final String SFLOGIN_URL="https://login.salesforce.com/";
	
	//STOS constants
		public static final String TIMESCALE="TRUE";
		public static final String NEEDS="TRUE";
		public static final String KEYPLAYERS="TRUE";
		public static final String BUDGET="TRUE";
		public static final String BARRIERS="TRUE";
		public static final String PRESENTATION="TRUE";
		public static final String PROPOSAL="TRUE";
		public static final String RELATIONSHIP="TRUE";
		public static final String COMPETITION="TRUE";
		public static final String CLOSE="TRUE";
		
		public static final String ANSTIMESCALE="1";
		public static final String ANSNEEDS="1";
		public static final String ANSKEYPLAYERS="1";
		public static final String ANSBUDGET="1";
		public static final String ANSBARRIERS="1";
		public static final String ANSPRESENTATION="1";
		public static final String ANSPROPOSAL="1";
		public static final String ANSRELATIONSHIP="1";
		public static final String ANSCOMPETITION="1";
		public static final String ANSCLOSE="1";
		
		public static final String CREATEOPP="TRUE";
		public static final String MODIFYOPP="TRUE";
		public static final String NEW_FORECAST="New Forecast";
		public static final String MODIFY="Modify";
		
		//Tronixss constants
		public static final String TEST_ORG = "Test";
		public static final String DEV_ORG = "Dev";
		public static final String NEW_DEV_ORG = "New Dev";
		public static final String TRONIXSS_TEST_ORG = "https://login.salesforce.com/";
		public static final String TRONIXSS_DEV_ORG = "https://tronixssrcap-dev-ed.my.salesforce.com/";
		public static final String CREATE = "create";
		public static final String EDIT = "edit";
		public static final String DELETE = "delete";
		
		//Elift constants
		public static final String SELECT_ORDER_RECORD_TYPE_PRODUCT = "Product Order";
		public static final String SELECT_ORDER_RECORD_TYPE_SERVICE = "Service Order";
		public static final String OBJECT_REPOSITORY_PATH="D://DinnerLabWorkspace//DinnerLabTesting//Object_Repo.properties";
			
		//Gee Constants
		public static final String BILLING_OPTION_ADVANCE = "Advance";
		public static final String BILLING_OPTION_ARREAR = "Arrear";
		public static final String BILLING_FREQUENCY_TERM = "Term";
		public static final String BILLING_FREQUENCY_MONTHLY = "Monthly";
		public static final String BILLING_DETAIL_INSERTION = "Insertion";
		public static final String BILLING_DETAIL_PRODUCT = "Product";
		public static final String Campaign_Id = "Campaign Id";
		public static final String Agency = "Agency";
		public static final String Account_Executive = "Account Executive";
		public static final String Event = "Event";
		public static final String Duration = "Duration";
		public static final String CampaignStatus = "Status";
		public static final String Client = "Client";
		public static final String CampaignNameSearch = "Campaign Name Search";
		public static final String CampaignName = "Campaign Name";
	
	
	
}

