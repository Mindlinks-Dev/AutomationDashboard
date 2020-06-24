
/*import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import java.io.FileInputStream;

public class JMeterFromExistingJMX 
{

	public static void main(String[] argv) throws Exception
		{
	        // JMeter Engine
	        StandardJMeterEngine jmeter = new StandardJMeterEngine();

	        JMeterUtils.setJMeterHome("C:/jmeter/apache-jmeter-2.13");
	        
	        // Initialize Properties, logging, locale, etc.
	        JMeterUtils.loadJMeterProperties("C:/jmeter/apache-jmeter-2.13/bin/jmeter.properties");
	        //JMeterUtils.setJMeterHome("C:/jmeter");
	        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
	        JMeterUtils.initLocale();

	        // Initialize JMeter SaveService
	        SaveService.loadProperties();

	        // Load existing .jmx Test Plan
	        FileInputStream in = new FileInputStream("C:/jmeter/apache-jmeter-2.13/extras/HomePage.jmx");
	        System.out.println("After executing the existing test plan");
	        HashTree testPlanTree = SaveService.loadTree(in);
	        in.close();

	        // Run JMeter Test
	        jmeter.configure(testPlanTree);
	        
	        //Result
	        Summariser summer = null;
	        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");//$NON-NLS-1$
	        if (summariserName.length() > 0)
	        {
	            summer = new Summariser(summariserName);
	        }

	        String logFile = "C:/jmeter/apache-jmeter-2.13/bin/Test1.csv";
	        ResultCollector logger = new ResultCollector(summer);
	        logger.setFilename(logFile);
	        testPlanTree.add(testPlanTree.getArray()[0], logger);
	        jmeter.run();
	    }
} */

 