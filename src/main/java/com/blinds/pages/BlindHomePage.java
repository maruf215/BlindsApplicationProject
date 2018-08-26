package com.blinds.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.blind.common.TestBase;

public class BlindHomePage extends TestBase {
	WebDriver driver;

	public BlindHomePage(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void openBlindHomePageAndVerifyTitle() {

		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Blinds | Window Blinds and Shades | Custom Window Coverings");
	}

	public void searchBlindPriceFromLowToHigh() {

		ArrayList<Float> blindPriceList;

		blindPriceList = allBLindPriceList();
		System.out.println("Blind Price List Before Sort: " + blindPriceList);

		Collections.sort(blindPriceList);
		System.out.println("Blind Price List From Low to High:  " + blindPriceList);

	}

	public ArrayList<Float> allBLindPriceList() {

		driver.findElement(By.xpath("//input[@id='gcc-typeahead-input']")).sendKeys("Blinds");
		driver.findElement(By.xpath("//button[@id='gcc-typeahead-submit']")).click();

		List<WebElement> allPrice = driver.findElements(
				By.xpath("//*[@id=\"gcc-search-results-list\"]/div/article/div/div/div[2]/div[1]/div[2]/div/div/span"));

		ArrayList<Float> al = new ArrayList<Float>();
		int i;

		for (i = 1; i < allPrice.size(); i++) {

			try {
				String myPrice = allPrice.get(i).getText();

				String s = myPrice.replace("$", "");
				Float d = Float.parseFloat(s);
				al.add(d);

			}

			catch (NumberFormatException nfe) {
				nfe.printStackTrace();
			}

		}

		return al;

	}
}
