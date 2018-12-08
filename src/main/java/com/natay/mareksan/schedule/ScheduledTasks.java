package com.natay.mareksan.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.http.HttpHeaders.USER_AGENT;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 5000*60*5)
    public void requestMareksan() throws IOException {
        sendGET();
    }

    private static void sendGET() throws IOException {
        URL obj = new URL("https://mareksan.herokuapp.com");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
    }


}
