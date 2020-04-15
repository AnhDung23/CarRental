/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.recaptcha;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Admin
 */
public class RecaptchaVerifying {
    public static final String URL = "https://www.google.com/recaptcha/api/siteverify";
    public static final String SECRET = "6Lel0-AUAAAAAFq2g0Jdx5PO5u4lhS9KZjFnU0SJ";
    
    public static boolean verify(String gRecaptchaResponse) throws IOException {
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }

        URL obj = new URL(URL);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        // Add request header 
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String postParams = "secret=" + SECRET + "&response="
                + gRecaptchaResponse;

        // Send post request
        con.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
            wr.writeBytes(postParams);
            wr.flush();
        }

        StringBuilder response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        // Parse JSON response
        JsonObject jsonObject;
        try ( 
                JsonReader jsonReader = Json.createReader(new StringReader(response.toString()))) {
            jsonObject = jsonReader.readObject();
        }

        return jsonObject.getBoolean("success");
    }
}
