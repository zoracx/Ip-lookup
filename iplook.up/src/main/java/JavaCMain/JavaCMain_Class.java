package JavaCMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class JavaCMain_Class implements JavaCMain_Interface {

    private static String ip;
    private String accessKey;
    private String url;

    // Constructor
    public JavaCMain_Class(String ip, String accessKey) {
        this.ip = ip;
        this.accessKey = accessKey;
        this.url = String.format("https://apiip.net/api/check?ip=%s&accessKey=%s", this.ip, this.accessKey);
    }

    // Method to fetch API response
    private String fetchApiResponse() {
        try {
            URL urlObj = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Print the API response for debugging
            System.out.println("API Response: " + response.toString());
            return response.toString();
        } catch (IOException e) {
            return null;
        }
    }


    // Fetch and format IP details
    @Override
    public String getIPDetails(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) {
            return "Error retrieving IP details.";
        }

        JSONObject json = new JSONObject(jsonResponse);

        return String.format(
            "IP Address: %s\n" +
            "Decimal IP: %d\n" +
            "Hostname: %s\n" +
            "ASN: %d\n" +
            "ISP: %s\n" +
            "Service Type: %s\n" +
            "Country: %s\n" +
            "State: %s\n" +
            "City: %s\n" +
            "Latitude: %.4f\n" +
            "Longitude: %.4f\n",
            json.optString("ip", "N/A"),
            json.optLong("decimal", 0),
            json.optString("hostname", "N/A"),
            json.optInt("asn", 0),
            json.optString("isp", "N/A"),
            json.optString("type", "N/A"),
            json.optString("country_name", "N/A"),
            json.optString("region", "N/A"),
            json.optString("city", "N/A"),
            json.optDouble("latitude", 0.0),
            json.optDouble("longitude", 0.0)
        );
    }

    @Override
    public long convertToDecimalIP(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) return 0;
        return new JSONObject(jsonResponse).optLong("decimal", 0);
    }

    @Override
    public String resolveHostname(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) return "N/A";
        return new JSONObject(jsonResponse).optString("hostname", "N/A");
    }

    @Override
    public int fetchASNInfo(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) return 0;
        return new JSONObject(jsonResponse).optInt("asn", 0);
    }

    @Override
    public String getISPDetails(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) return "N/A";
        return new JSONObject(jsonResponse).optString("isp", "N/A");
    }

    @Override
    public String identifyServiceType(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) return "N/A";
        return new JSONObject(jsonResponse).optString("type", "N/A");
    }

    @Override
    public String getCountryByIP(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) return "N/A";
        return new JSONObject(jsonResponse).optString("country_name", "N/A");
    }

    @Override
    public String getStateByIP(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) return "N/A";
        return new JSONObject(jsonResponse).optString("region", "N/A");
    }

    @Override
    public String getCityByIP(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) return "N/A";
        return new JSONObject(jsonResponse).optString("city", "N/A");
    }

    @Override
    public double getLatitudeByIP(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) return 0.0;
        return new JSONObject(jsonResponse).optDouble("latitude", 0.0);
    }

    @Override
    public double getLongitudeByIP(String ip) {
        String jsonResponse = fetchApiResponse();
        if (jsonResponse == null) return 0.0;
        return new JSONObject(jsonResponse).optDouble("longitude", 0.0);
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        JavaCMain_Class obj = new JavaCMain_Class("8.8.8.8", "YOUR_ACCESS_KEY");
        try {
			System.out.println(obj.getIPDetails(ip));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
