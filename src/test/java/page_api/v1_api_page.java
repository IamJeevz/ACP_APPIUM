package page_api;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class v1_api_page {


    public static String generateMessageId() {
        return UUID.randomUUID().toString();
    }


    public String getAuthToken(String domain, String usr, String pass) {
        try {
            URL url = new URL(domain + "/api/authentication/generate-token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = new JSONObject()
                    .put("username", usr)
                    .put("password", pass)
                    .toString();

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes());
            }

            int status = conn.getResponseCode();
            InputStream inputStream = (status >= 200 && status < 300) ?
                    conn.getInputStream() : conn.getErrorStream();

            String response = new BufferedReader(new InputStreamReader(inputStream))
                    .lines()
                    .reduce("", (acc, line) -> acc + line);

            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse.getString("idToken");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public  String getServiceKeyByWaba(String domain, String token, String wabaNumber) {
        try {
            URL url = new URL(domain + "/api/v1/whatsapp/fetchServiceInfo");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);

            InputStream is = conn.getResponseCode() >= 200 && conn.getResponseCode() < 300
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            String response = new BufferedReader(new InputStreamReader(is))
                    .lines().reduce("", (acc, line) -> acc + line);

            // System.out.println("Full Response: " + response);

            org.json.JSONArray jsonArray = new org.json.JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("waba_number").equals(wabaNumber)) {
                    System.out.println("ServiceKey="+obj.getString("service_key"));
                    return obj.getString("service_key");
                }
            }

            System.out.println("waba_number not found.");
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
        public static void sendAudioMessage(String domain,String from_no,String to_no, String token, String serviceKey,String audio_url) {
            try {
                URL url = new URL(domain + "/api/v1/whatsapp/send-audio");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setRequestProperty("service_key", serviceKey);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                // JSON payload
                JSONObject payload = new JSONObject();
                payload.put("From", from_no);
                payload.put("To", to_no);
                payload.put("MessageId", generateMessageId());

                JSONObject content = new JSONObject();
                content.put("MediaUrl", audio_url);
                payload.put("Content", content);

                payload.put("CallBackData", "Callback data");


                // Send request
                OutputStream os = conn.getOutputStream();
                os.write(payload.toString().getBytes());
                os.flush();
                os.close();

                // Read response
                InputStream is = conn.getResponseCode() >= 200 && conn.getResponseCode() < 300
                        ? conn.getInputStream()
                        : conn.getErrorStream();

                String response = new BufferedReader(new InputStreamReader(is))
                        .lines().reduce("", (acc, line) -> acc + line);

                System.out.println("Send Audio Response: " + response);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
