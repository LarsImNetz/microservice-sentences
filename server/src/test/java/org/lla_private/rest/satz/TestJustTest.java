package org.lla_private.rest.satz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lla_private.rest.satz.JustTest;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestJustTest {

	private JustTest justTestSUT;

	@Before
	public void setUp() {
		justTestSUT = new JustTest();

	}

	@Test
	public void testAbfrageTest() {
		String value = justTestSUT.test();
		Assert.assertEquals("Just a test", value);
	}

}
