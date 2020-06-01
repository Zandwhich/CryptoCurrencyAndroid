package com.example.phone.utility.network.endpoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Class that helps with connecting to the internet
 */
public final class NetworkUtils {

    /**
     * Connects to a given URL and returns the contents as a String
     * @param url The URL to hit
     * @return The contents of the connection as a String
     */
    public static String connect(URL url) throws IOException {
        final URLConnection connection = url.openConnection();
        connection.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder output = new StringBuilder(in.readLine());
        String line = in.readLine();
        while (line != null) {
            output.append(line);
            line = in.readLine();
        }//end while

        return output.toString();
    }//end connect()
}//end NetworkUtils
