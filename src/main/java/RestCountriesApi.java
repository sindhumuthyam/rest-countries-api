/*Created by Sinduri Muthyam*/
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class RestCountriesApi {


    public static void main(String args[]) {
        RestCountriesApi restCountriesApi = new RestCountriesApi();
        String country = "aruba";
        System.out.println("Please enter the country code or name:");
        Scanner s = new Scanner(System.in);
        country = s.nextLine();
        while(true) {
            String capital = restCountriesApi.getCapitalFromCountry(country);
            System.out.println("Capital of " + country + " is " + capital);
            System.out.println("Please enter the country code or name:");
            country = s.nextLine();
        }
    }
    public String getCapitalFromCountry(String country) {
        String result=null;
        try {
            String urlformed = "https://restcountries.com/v3.1/name/"+country;
            URL url = new URL(urlformed);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            //we are converting from url to uri to escape space in the url like "united states of america" and then after encoding we are changing back to url.

            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                //System.out.println("Failed for url "+url.toString()+" "+conn.getResponseCode() +" "+conn.getResponseMessage());
                return conn.getResponseMessage();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                result = output;
            }
            if(result !=null) {
                final JSONArray obj = new JSONArray(result);
                JSONObject obj1 = obj.getJSONObject(0);
                JSONArray capitalAr = obj1.getJSONArray("capital");
                result = capitalAr.getString(0);
            }
            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }
}
