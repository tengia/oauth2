/* 
 * Copyright (c) 2017 Georgi Pavlov (georgi.pavlov@isoft-technology.com).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT license which accompanies 
 * this distribution, and is available at 
 * https://github.com/tengia/oauth-2/blob/master/LICENSE
 */
package net.oauth2.gson;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class WhitespaceDelimitedCollectionTypeAdapterFactoryTest {

	private Gson g;
	private Collection<String> scopes;
	
	@Before
	public void before() {
		this.g = WhitespaceDelimitedCollectionTypeAdapterFactory.REGISTER(new GsonBuilder()).create();	
		this.scopes = new ArrayList<String>();	
		scopes.add("a");
		scopes.add("b");
		scopes.add("c");
	}
	
	@Test
	public void testSerialization() {
		String s = this.g.toJson(scopes);
		assertEquals("\"a b c\"", s);
	}
	
	@Test
	public void testDeserialization() {
		String s = "\"a b c\"";
		Collection<String> c = this.g.fromJson(s, new TypeToken<Collection<String>>(){}.getType());		
		assertEquals(this.scopes, c);
	} 

}
