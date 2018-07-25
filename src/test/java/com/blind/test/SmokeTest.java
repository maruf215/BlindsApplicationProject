package com.blind.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.blind.common.TestBase;
import com.blinds.pages.BlindHomePage;

public class SmokeTest extends TestBase {
	TestBase tBase;
	BlindHomePage bHomePage;

	@Test
	public void SearchTest() throws IOException {
//		tBase = new TestBase();
//		tBase.browserInitialization();
		bHomePage = new BlindHomePage(driver);
		bHomePage.openBlindHomePageAndVerifyTitle();
		bHomePage.searchBlindPriceFromLowToHigh();

	}

}
