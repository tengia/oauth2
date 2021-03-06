/* 
 * Copyright (c) 2017 Georgi Pavlov (georgi.pavlov@isoft-technology.com).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT license which accompanies 
 * this distribution, and is available at 
 * https://github.com/tengia/oauth-2/blob/master/LICENSE
 */
package net.oauth2.client.retrofit;

import java.io.IOException;

import org.junit.Test;

import net.oauth2.AccessToken;
import net.oauth2.ClientCredentialsGrantRequest;
import net.oauth2.client.TokenService;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class ExtendedTokenServiceIT {
	
	protected String geolocation;

	@Test
	public void test() throws IOException {
		MockWebServer server = new MockWebServer();

		// Schedule some responses.
		server.enqueue(new MockResponse().setBody("{"
			 + "	\"access_token\": \"1234\","
			 + "	\"expiresIn\": 3600,"
			 + "	\"refresh_token\":\"456\","
			 + "	\"token_type\": \"Bearer\","
			 + "	\"geolocation\": \"custom\""
			 + "}"));
		
		server.start();
		
		HttpUrl baseUrl = server.url("/token");
		String url = baseUrl.url().toExternalForm().substring(0,  baseUrl.url().toExternalForm().indexOf("token"));
		TokenService ts = new RetrofitTokenService<TokenEndpoint, AccessToken>(url, new ClientCredentialsGrantRequest("123", "123", null), "", "" , null, null);
		AccessToken myToken = ts.fetch();
		/*String url = baseUrl.url().toExternalForm().substring(0,  baseUrl.url().toExternalForm().indexOf("token"));
		Retrofit retrofitServiceFactory = new Retrofit.Builder()
														.baseUrl(url)
														.addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
														.build();
		MyEndpoint tokenEndpointDelegate = retrofitServiceFactory.create(MyEndpoint.class);
				
		Call<MyToken> token = tokenEndpointDelegate.getAccessToken("token", new HashMap<>());
		
		Response<MyToken> tokenResponse = token.execute();
		System.out.println(tokenResponse.body().getGeolocation());*/
		
		System.out.println(myToken);
		server.shutdown();
	}

}
