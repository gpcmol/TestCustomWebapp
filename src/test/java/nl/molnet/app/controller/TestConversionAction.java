package nl.molnet.app.controller;

import nl.molnet.app.selenium.AbstractSeleniumHelper;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.annotation.Report;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = { "data/fahrenheit.xls" })
//@Report(outputFormats={Report.EXPORT_FORMAT.PDF})
public class TestConversionAction extends AbstractSeleniumHelper {

	/**
	 * Web page object which holds the web page elements
	 */
	private ToCelsiusPageObject toCelsiusPageObject;

	/**
	 * Test toCelsius
	 * @param url
	 * @param fahrenheit
	 * @param expectedCelsius
	 */
	@Test
	public void testToCelsiusAction(@Param(name = "url") String url,
			@Param(name = "fahrenheit") Integer fahrenheit, 
			@Param(name = "expectedCelsius") Integer expectedCelsius) {

		// init url and setup web driver
		this.getUrl(url);

		// init page
		toCelsiusPageObject = PageFactory.initElements(driver, ToCelsiusPageObject.class);

		// execute actions
		toCelsiusPageObject.clearFahrenheitInput();
		toCelsiusPageObject.setFahrenheitValue(fahrenheit);
		toCelsiusPageObject.clickSubmit();

		// get result after action
		String celsiusResultFromBrowser = toCelsiusPageObject.getCelsiusResultValue();

		// assert result
		Assert.assertEquals(expectedCelsius, Integer.valueOf(celsiusResultFromBrowser));
	}

}
