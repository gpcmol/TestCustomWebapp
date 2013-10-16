package nl.molnet.app.controller;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ToCelsiusPageObject {

	@FindBy(id = "fahrenheitInput")
	private WebElement fahrenheitInput;

	@FindBy(id = "submit")
	private WebElement submitButton;

	@FindBy(id = "celsiusResult")
	private WebElement celsiusResult;

	public WebElement getFahrenheitInput() {
		return fahrenheitInput;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	public WebElement getCelsiusResult() {
		return celsiusResult;
	}

	public void clearFahrenheitInput() {
		this.fahrenheitInput.clear();
	}

	public void clickSubmit() {
		this.submitButton.click();
	}

	public void setFahrenheitValue(Integer fahrenheit) {
		this.fahrenheitInput.sendKeys(String.valueOf(fahrenheit));
	}
	
	public void setFahrenheitValue(String fahrenheit) {
		this.fahrenheitInput.sendKeys(fahrenheit);
	}
	
	public String getCelsiusResultValue() {
		return this.celsiusResult.getAttribute("value");
	}

}
