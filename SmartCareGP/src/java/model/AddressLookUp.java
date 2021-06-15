/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Eli
 */
public class AddressLookUp {

    private ArrayList<String> addressList;

    public void lookUp(String Address) {
        
        
//        clearning string of spaces to remove 400 erroers
        Address = Address.replaceAll(" ", "");
      

        String apiKey = "ak_kk2v74myhq3k7JEAyVu7qrCwuUQgw";
        try {
            URL url = new URL("https://api.ideal-postcodes.co.uk/v1/autocomplete/addresses?api_key=" + apiKey + "&query=" + Address);
            System.out.println("url" + url);
            
//            Connecting to internet and retrving json
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            System.out.println("responsecode" + responsecode);
            
//            if an error code that isn't sucsses code 200
            if (responsecode != 200) {
                addressList = null;
                throw new RuntimeException("HttpResponseCode: " + responsecode);

            } else {
                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Json data is turned into a huge string using scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                System.out.println("inline" + inline);
                //Close the scanner
                scanner.close();

                //JSON parses is needed to acsses object
                JSONParser parse = new JSONParser();
                JSONObject allData = (JSONObject) parse.parse(inline);
                
//                Getting data from result node
                JSONObject result = (JSONObject) allData.get("result");
//                  Getting data from hit node
                JSONArray hits = (JSONArray) result.get("hits");

                
//              Variables for manipulation JSON to get list
                JSONObject tempObj;
                addressList = new ArrayList<String>();
                String singleAddress;
                
                for (int i = 0; i < hits.size(); i++) {
                    tempObj = (JSONObject) hits.get(i);
//                    Suggestion is the node the adresses are under, they're taken from the json and stored on the string
                    singleAddress = (String) tempObj.get("suggestion");

                    addressList.add(singleAddress);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String createHTML() {
        System.out.println("addresslist" + addressList);
//        if the json return was nothing
        if (addressList.isEmpty()) {
            return "Please Enter a valid address";
        } else {
//          An String containing html showing all options to be selected is created
            String addressBoxHTML = "<label for=\"Address\">List Of Addreses:</label> <select id=\"Address\" name=\"addressPull\"> ";
            for (int i = 0; i < addressList.size(); i++) {
                addressBoxHTML = addressBoxHTML + "<option value=\"" + addressList.get(i) + "\">";
                addressBoxHTML = addressBoxHTML + addressList.get(i) + "</option>";

            }
            addressBoxHTML = addressBoxHTML + "</select>";
            addressBoxHTML = addressBoxHTML + "<button class=\"btn btn-primary col-12\" type=\"submit\" name=\"act\" value=\"SelectAddress\">Select Address</button>";

            return addressBoxHTML;
        }

    }

    public ArrayList<String> getAddressList() {
        return addressList;
    }

}
