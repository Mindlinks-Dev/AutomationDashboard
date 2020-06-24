package com.pack;

import com.google.common.collect.ImmutableMap;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.Address;
import com.stripe.model.AlipayAccount;
import com.stripe.model.ApplicationFee;
import com.stripe.model.Balance;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;
import com.stripe.model.BitcoinReceiver;
import com.stripe.model.BitcoinTransaction;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.ChargeRefundCollection;
import com.stripe.model.Coupon;
import com.stripe.model.Customer;
import com.stripe.model.CustomerSubscriptionCollection;
import com.stripe.model.DeletedBitcoinReceiver;
import com.stripe.model.DeletedCard;
import com.stripe.model.DeletedCoupon;
import com.stripe.model.DeletedCustomer;
import com.stripe.model.DeletedInvoiceItem;
import com.stripe.model.DeletedPlan;
import com.stripe.model.DeletedRecipient;
import com.stripe.model.Dispute;
import com.stripe.model.Event;
import com.stripe.model.EvidenceDetails;
import com.stripe.model.EvidenceSubObject;
import com.stripe.model.ExternalAccount;
import com.stripe.model.ExternalAccountCollection;
import com.stripe.model.FileUpload;
import com.stripe.model.FraudDetails;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import com.stripe.model.InvoiceLineItemCollection;
import com.stripe.model.MetadataStore;
import com.stripe.model.Plan;
import com.stripe.model.Recipient;
import com.stripe.model.Refund;
import com.stripe.model.ShippingDetails;
import com.stripe.model.Subscription;
import com.stripe.model.Token;
import com.stripe.model.Transfer;
import com.stripe.model.TransferReversalCollection;
import com.stripe.net.RequestOptions;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StripeHandler 
{
	public static String planID;
	public static String item_Name1;
	public static String city;
	public static String type;
	public static String financeCode;
	public static String description;
	public static String status;
	public static String email;
	public static String id;
	public static String memberDescription;
	public static String createdDate;
	public static String discountAmount;
	public static String day;
	public static String quantity;
	public static String date;
	public static String totalTax;
	public static long unixCreatedDate;
	
	
		
	//Fetching Stripe API Secret Key
	static String stripeAPISecretKey = AutomateLoader.config.getProperty("STRIPE_API_SECRET_KEY");
	static Map<String, Object> defaultCardParams = new HashMap<String, Object>();
	static Map<String, Object> defaultDebitCardParams = new HashMap<String, Object>();
	static Map<String, Object> defaultChargeParams = new HashMap<String, Object>();
	static Map<String, Object> defaultCustomerParams = new HashMap<String, Object>();
	static Map<String, Object> defaultPlanParams = new HashMap<String, Object>();
	static Map<String, Object> defaultCouponParams = new HashMap<String, Object>();
	static Map<String, Object> defaultTokenParams = new HashMap<String, Object>();
	static Map<String, Object> defaultDebitTokenParams = new HashMap<String, Object>();
	static Map<String, Object> defaultBankAccountParams = new HashMap<String, Object>();
	static Map<String, Object> defaultRecipientParams = new HashMap<String, Object>();
	static Map<String, Object> defaultBitcoinReceiverParams = new HashMap<String, Object>();
	static Map<String, Object> defaultAlipayTokenParams = new HashMap<String, Object>();
	static RequestOptions cardSupportedRequestOptions;
	public String stripeCustomerId = null;
	public String stripeTransactionId = null;

	@BeforeClass
	public static void setUp() 
	{
		Stripe.apiKey = "sk_test_4SL8NJJRnDSpyXUb7URMC5LE"; // stripe public
		//Stripe.apiKey = "pk_test_4SL8JpeKvnuCyOZmR9hc0mKY"; // stripe public
		cardSupportedRequestOptions = RequestOptions.builder().setStripeVersion("2015-02-16").build();
	}
	
	@Test
	public static void testChargeRetrieve(WebDriver driver,String stripeTransactionId) throws StripeException 
	{
		try{
		//Charge createdCharge = Charge.create(defaultChargeParams);
		Charge retrievedCharge = Charge.retrieve(stripeTransactionId);
		System.out.println("retrievedCharge: "+retrievedCharge);
		//planID = retrievedCharge.getMetadata().get("PlanID").toString();
		item_Name1 = retrievedCharge.getMetadata().get("item_name1").toString();
		city = retrievedCharge.getMetadata().get("City").toString();
		type = retrievedCharge.getMetadata().get("Type").toString();
		financeCode = retrievedCharge.getMetadata().get("Finance code").toString();
		description =  retrievedCharge.getDescription().toString();
		status =  retrievedCharge.getStatus().toString();
		
		discountAmount =  retrievedCharge.getMetadata().get("discount_amount");
		day =  retrievedCharge.getMetadata().get("day");
		quantity =  retrievedCharge.getMetadata().get("quantity");
		date =  retrievedCharge.getMetadata().get("date");
		totalTax =  retrievedCharge.getMetadata().get("total_tax");
		
		/*System.out.println("planID:"+planID);
		System.out.println("item_Name1:"+item_Name1);
		System.out.println("city:"+city);
		System.out.println("type:"+type);
		System.out.println("financeCode:"+financeCode);
		System.out.println("description:"+description);
		System.out.println("status:"+status);
		System.out.println("discountAmount:"+discountAmount);
		System.out.println("day:"+day);
		System.out.println("quantity:"+quantity);
		System.out.println("date:"+date);
		System.out.println("totalTax:"+totalTax);*/
		
	}catch(Exception e){
		e.printStackTrace();
	}
		
	}
	@Test
	public static void testCustomerRetrieve(WebDriver driver,String stripeCustomerId) throws StripeException 
	{
		//Customer createdCustomer = Customer.create(defaultCustomerParams);
		//System.out.println("created customer:"+createdCustomer);
		Customer retrievedCustomer = Customer.retrieve(stripeCustomerId);
		System.out.println("retrieved customer:"+retrievedCustomer);
		
		id =  retrievedCustomer.getId().toString();
		email = retrievedCustomer.getEmail().toString();
		memberDescription = retrievedCustomer.getDescription().toString();
		unixCreatedDate = retrievedCustomer.getCreated();
		
		long unixSeconds = unixCreatedDate;
		Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // give a timezone reference for formating (see comment at the bottom
		createdDate = sdf.format(date);
		
		/*System.out.println("createdDate:"+createdDate);
		System.out.println("email:"+email);
		System.out.println("memberDescription:"+memberDescription);
		System.out.println("status:"+status);*/
		
		Map<String,String> Details = new HashMap<String,String>();
		Details.put("Email",retrievedCustomer.getEmail());
		Details.put("Customer_Id",retrievedCustomer.getId());
		Details.put("Description",retrievedCustomer.getDescription());
		Details.put("Created_Date",retrievedCustomer.getCreated().toString());
		//System.out.println("Printing Map Values :" +Details);
	}
}


