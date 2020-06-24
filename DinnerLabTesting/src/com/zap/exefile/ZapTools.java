package com.zap.exefile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;
import org.zaproxy.clientapi.core.ClientApiMain;
import org.zaproxy.clientapi.gen.Spider;

public class ZapTools {
	
	
	String ZAP_LOCATION = "C:\\Program Files\\OWASP\\Zed Attack Proxy\\";
	String SAVE_SESSION_DIRECTORY = "ZAPSessions\\";
	String apikey = "";

	public ZapTools()
	{
		System.out.println("inside constroctore");
	}
	
	@Test
	public boolean startZAP(){
		try {
			System.out.println("inside startzap of zaptool");
			String[] command = { "CMD", "/C", this.ZAP_LOCATION + "ZAP.exe" };
			ProcessBuilder proc = new ProcessBuilder(command);
			proc.directory(new File(this.ZAP_LOCATION));
			Process p = proc.start();
			p.waitFor();
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			OutputStreamWriter oStream = new OutputStreamWriter(p.getOutputStream());
			oStream.write("process where name='ZAP.exe'");
			oStream.flush();
			oStream.close();
			String line;
			while ((line = input.readLine()) != null) {
				// kludge to tell when ZAP is started and ready
				if (line.contains("INFO") && line.contains("org.parosproxy.paros.control.Control")
						&& line.contains("New Session")) {
					input.close();
		            break;
		}
			}
			System.out.println("ZAP has started successfully.");
			return true;
		} catch (Exception ex) {
			System.out.println("ZAP was unable to start.");
			ex.printStackTrace();
			return false;
		}
	}
	@Test
	public void stopZAP(String zapaddr, int zapport) {
		ClientApiMain.main(new String[] { "stop", "zapaddr=" + zapaddr, "zapport=" + zapport });
	}
	@Test
	public void startSession(String zapaddr, int zapport) {
		ClientApiMain.main(new String[] { "newSession", "zapaddr=" + zapaddr, "zapport=" + zapport });
		System.out.println("session started");
		System.out.println("Session started successfully.");
	}
	@Test
	public void saveSession(ClientApi api, String fileName) {
		try {
			String path = this.SAVE_SESSION_DIRECTORY + fileName + ".session";
			api.core.saveSession(path, "true");
			System.out.println("Session save successful (" + path + ").");
		} catch (ClientApiException ex) {
			System.out.println("Error saving session.");
			ex.printStackTrace();
		}
	}
	@Test
	public boolean ascan(ClientApi api, String ZAP_URI_PORT) throws InterruptedException {
		try {
			System.out.println("Active scan starting...");
			ApiResponse resp = api.ascan.scan(ZAP_URI_PORT, "", "", "", "", "");
			int progress;
			if (! "OK".equals(((ApiResponseElement)resp).getValue())) {
				System.out.println("Failed to Active Scan target : " + resp.toString(0));
				return false;
			}
			
			// Poll the status until it completes
			while (true) {
				Thread.sleep(5000);
				progress = Integer.parseInt(((ApiResponseElement)api.ascan.status("")).getValue());
				System.out.println("Active Scan progress : " + progress + "%");
				if (progress >= 100) {
					break;
				}
			}
			System.out.println("Active Scan complete");
			return true;
		} catch (ClientApiException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	@Test
	public boolean spider(ClientApi api, String ZAP_URI_PORT) throws InterruptedException {
		try {
			System.out.println("Spider scan starting...");
			Spider spider = new Spider(api);
			ApiResponse resp = spider.scan(ZAP_URI_PORT, "", "", "", "");
			String scanid;
			int progress;

			// The scan now returns a scan id to support concurrent scanning
			scanid = ((ApiResponseElement) resp).getValue();

			// Poll the status until it completes
			while (true) {
				Thread.sleep(1000);
				progress = Integer.parseInt(((ApiResponseElement) api.spider.status(scanid)).getValue());
				System.out.println("Spider progress : " + progress + "%");
				if (progress >= 100) {
					break;
				}
			}
			System.out.println("Spider complete");
			return true;
		} catch (ClientApiException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	@Test
	public String checkErrors(ClientApi api) {
		String errors = "";
		try {
			System.out.println("Checking Alerts...");
			System.out.println(new String(api.core.htmlreport()));
			File f = new File("D:\\DinnerLabWorkspace\\DinnerLabTesting\\src\\Zapresult\\zapResult(Quicksolar).html");
	        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
	        bw.write(new String(api.core.htmlreport()));
	        bw.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			errors = ex.getMessage();
		}
		return errors;
	}
}
