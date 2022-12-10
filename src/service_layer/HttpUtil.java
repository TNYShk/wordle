package service_layer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpUtil {

    private static HttpURLConnection httpConn;

    /**
     * Makes an HTTP request using GET method to the specified URL.
     *
     * @param requestURL the URL of the remote server
     * @return An HttpURLConnection object
     * @throws IOException thrown if any I/O error occurred
     *
     */
    public static HttpURLConnection sendGetRequest(String requestURL) throws IOException {
        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);

        httpConn.setDoInput(true); // true if we want to read server's response
        httpConn.setDoOutput(false); // false indicates this is a GET request

        return httpConn;
    }

    /**
     * GET response
     * Returns only one line from the server's response. This method should be
     * used if the server returns only a single line of String.
     *
     * @return a String of the server's response
     * @throws IOException thrown if any I/O error occurred
     *
     */
    public static String readSingleLineResponse() throws IOException {
        InputStream inputStream = null;
        if (httpConn != null) {
            inputStream = httpConn.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }
        String response;
        try( BufferedReader reader = new BufferedReader(new InputStreamReader( inputStream))){
            response = reader.readLine();
        }

        return response;
    }

    public static List<String> readMultipleLinesResponse() throws IOException {
        InputStream inputStream = null;
        if (httpConn != null) {
            inputStream = httpConn.getInputStream();
        } else {
            throw new IOException("Connection is not established.");
        }
        List<String> response = new ArrayList<>();


        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
        }

        return  response;
    }

    /**
     * Closes the connection if opened
     */
    public static void disconnect() {
        if (httpConn != null) {
            httpConn.disconnect();
        }
    }
}
