package de.sogeti.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.github.javafaker.Faker;

public class AutomationPage {

	private WebDriver driver;

	@FindBy(id = "4ff2ed4d-4861-4914-86eb-87dfa65876d8")
	private WebElement FirstName;

	@FindBy(id = "11ce8b49-5298-491a-aebe-d0900d6f49a7")
	private WebElement LastName;

	@FindBy(id = "056d8435-4d06-44f3-896a-d7b0bf4d37b2")
	private WebElement Email;

	@FindBy(id = "755aa064-7be2-432b-b8a2-805b5f4f9384")
	private WebElement Phone;

	@FindBy(id = "703dedb1-a413-4e71-9785-586d609def60")
	private WebElement Company;

	@FindBy(id = "e74d82fb-949d-40e5-8fd2-4a876319c45a")
	private WebElement Country;

	@FindBy(id = "88459d00-b812-459a-99e4-5dc6eff2aa19")
	private WebElement Message;

	@FindBy(id = "863a18ee-d748-4591-bb64-ef6eae65910e")
	private WebElement iAgreeCheckbox;

	@FindBy(css = ".recaptcha-checkbox-border")
	private WebElement captcha;

	public AutomationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Enter the random values in contact form
	public void fillContantUsFormWithRandomData() {

		Faker faker = new Faker();
		// Enter the first name
		FirstName.sendKeys(faker.name().firstName());
		// Enter the last name
		LastName.sendKeys(faker.name().lastName());
		// Enter the Email address
		Email.sendKeys(faker.internet().emailAddress());
		// Enter the phone number
		Phone.sendKeys(faker.phoneNumber().cellPhone());
		// Enter the company
		Company.sendKeys(faker.company().catchPhrase());
		// Select the country from dropdown
		Select selectCountry = new Select(Country);
		selectCountry.selectByIndex(faker.random().nextInt(1, 42).intValue());
		// Enter the message in Message box
		Message.sendKeys(faker.lorem().sentence(50));
		// Select the agree checkbox
		iAgreeCheckbox.click();
		captcha.click();

	}
}
