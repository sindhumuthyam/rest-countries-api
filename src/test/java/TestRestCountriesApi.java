import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestRestCountriesApi {
    RestCountriesApi restCountriesApi;
    @Before
    public void setup(){
        restCountriesApi = new RestCountriesApi();
    }

    @Test
    public void testgetCapitalFromCountry(){
        Map<String, String> testcasesPositive = new HashMap<>();
        Map<String, String> testcasesNegative = new HashMap<>();

        //Add all positive Testcases
        testcasesPositive.put("aruba","Oranjestad");
        testcasesPositive.put("Aruba","Oranjestad");
        testcasesPositive.put("AruBa","Oranjestad");
        testcasesPositive.put("United States of America","Washington, D.C.");
        testcasesPositive.put("USA","Washington, D.C.");


        //Test all negative testcases
        testcasesNegative.put("aruba1","Not Found");
        testcasesNegative.put("unitedstatesofamerica","Not Found");
        for(String country:testcasesPositive.keySet()) {
            Assert.assertEquals("Test fail for country "+country,restCountriesApi.getCapitalFromCountry(country), testcasesPositive.get(country));
        }

        for(String country:testcasesNegative.keySet()) {
            Assert.assertEquals("Test fail for country "+country,restCountriesApi.getCapitalFromCountry(country), testcasesNegative.get(country));
        }

    }
}
