package net.hoyoung.app.wfp_webdown;

import net.hoyoung.app.wfp_webdown.utils.WfpUrlUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	boolean b = WfpUrlUtils.isSameHost("http://www.hoyoung.net", "http://2.net/");
    	System.out.println(b);
    }
}
