/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mankido.resources;

import static org.junit.Assert.assertTrue;

import javax.json.JsonObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.mankido.setup.Configuration;

@RunWith(SpringRunner.class)
public class LoginResourceTest {
	
	LoginResource resource;
	
	Configuration configuration;
	
	@Before
	public void setUp() {
		resource = new LoginResource();
		configuration = Configuration.instance();
	}

	@Test
	public void testLogin() throws Exception {
		JsonObject pois = resource.login(configuration.getInstaUser(), configuration.getInstaPassword());
		assertTrue(pois != null);
	}

}
