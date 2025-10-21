package com.smartinvestment.kiteconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.models.Margin;
import com.zerodhatech.models.OrderParams;
import com.zerodhatech.models.User;
import com.zerodhatech.models.Order;
import com.zerodhatech.kiteconnect.kitehttp.SessionExpiryHook;
import com.zerodhatech.kiteconnect.utils.Constants;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;

import java.io.IOException;

@SpringBootApplication
public class KiteconnectApplication {

	public static void main(String[] args) {
		// SpringApplication.run(KiteconnectApplication.class, args);
		System.out.println("Hello World");

		// Initialize Kiteconnect using apiKey.
        KiteConnect kiteSdk = new KiteConnect("YYYYY");
		System.out.println("Kite SDK: " + kiteSdk);

		// Set userId.
		// kiteSdk.setUserId("XXXXXX");
		// System.out.println("your_userId: " + kiteSdk);

		/* First you should get request_token, public_token using kitconnect login and then use request_token, public_token, api_secret to make any kiteconnect api call.
		Get login url. Use this url in webview to login user, after authenticating user you will get requestToken. Use the same to get accessToken. */
		// String url = kiteSdk.getLoginURL();
		// System.out.println("URL: " + url);

		// // Get accessToken as follows,
		// User user = null;
		// try {
		// 	user = kiteSdk.generateSession("8PUS5G2jInSzLnVsS5EQpmTFC2HRpmq5", "ZZZZZZ");
		// 	System.out.println("User Access Token: " + user.accessToken);
		// 	System.out.println("User Public Token: " + user.publicToken);
		// } catch (KiteException | IOException e) {
		// 	System.err.println("Error generating session: " + e.getMessage());
		// 	e.printStackTrace();
		// }

			// Set request token and public token which are obtained from login process.
			kiteSdk.setAccessToken("AAAAAAA");
			kiteSdk.setPublicToken("BBBBBB");


		// Set session expiry callback.
		// kiteSdk.setSessionExpiryHook(new SessionExpiryHook() {
		// 	@Override
		// 	public void sessionExpired() {
		// 		System.out.println("session expired");                    
		// 	}
		// });

		try {
			// Get margins returns margin model, you can pass equity or commodity as arguments to get margins of respective segments.
			Margin margins = kiteSdk.getMargins("equity");
			System.out.println(margins.available.cash);
			System.out.println(margins.utilised.debits);
		} catch (KiteException | IOException e) {
			System.err.println("Error fetching margins: " + e.getMessage());
			e.printStackTrace();
		}

		try {

            // Order parameters
            OrderParams orderParams = new OrderParams();
            orderParams.exchange = "NSE";
            orderParams.tradingsymbol = "URBANCO";
            orderParams.transactionType = "BUY";
            orderParams.quantity = 1;
            orderParams.orderType = "MARKET";
            orderParams.product = "CNC";
            orderParams.validity = "DAY";

            // Place order
            Order order = kiteSdk.placeOrder(orderParams, Constants.VARIETY_REGULAR);

            System.out.println("Order placed. ID: " + order);
        } catch (KiteException | IOException e) {
			System.err.println("Error placing order: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
