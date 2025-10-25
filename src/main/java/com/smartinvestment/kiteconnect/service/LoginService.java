package com.smartinvestment.kiteconnect.service;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.models.User;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class LoginService {
    @Value("${my.app.apiKey}")
    private String apiKey;
    @Value("${my.app.apiSecret}")
    private String apiSecret;
    @Value("${my.app.requestToken}")
    private String requestToken;
    @Value("${my.app.userId}")
    private String userId;

    public KiteConnect login() throws KiteException, IOException {

        // Initialize Kiteconnect using apiKey.
        KiteConnect kiteSdk = new KiteConnect(apiKey);
        System.out.println("Kite SDK: " + kiteSdk);

		// Set userId.
        kiteSdk.setUserId(userId);
		System.out.println("User Id: " + userId);

		// Get login URL
		String url = kiteSdk.getLoginURL();
		System.out.println("Login URL: " + url);

		// // Get accessToken as follows,
		// User user = null;
        // try {
		// 	user = kiteSdk.generateSession(requestToken, apiSecret);
		// 	System.out.println("User Access Token: " + user.accessToken);
		// 	System.out.println("User Public Token: " + user.publicToken);
		// } catch (KiteException | IOException e) {
		// 	System.err.println("Error generating session: " + e.getMessage());
		// 	e.printStackTrace();
		// }

        // Set request token and public token which are obtained from login process.
        kiteSdk.setAccessToken(requestToken);
        System.out.println("Access Token: " + requestToken);
        // kiteSdk.setPublicToken(user.publicToken);

        return kiteSdk;
    }
}