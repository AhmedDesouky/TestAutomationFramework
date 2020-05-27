package steps;

import org.testng.Assert;

import com.github.javafaker.Faker;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegisteration extends TestBase {

	HomePage homeObject;
	UserRegistrationPage registerObject;
	/*
	 * Faker fakeData = new Faker(); String firstname = fakeData.name().firstName();
	 * String lastname = fakeData.name().lastName(); String email =
	 * fakeData.internet().emailAddress(); String password =
	 * fakeData.number().digits(8).toString();
	 */

	@Given("^the user going to home page$")
	public void the_user_going_to_home_page() throws Throwable {
		homeObject = new HomePage(driver);
		homeObject.openRegistrationPage();
	}

	@When("^click on register button$")
	public void click_on_register_button() throws Throwable {
		Assert.assertTrue(driver.getCurrentUrl().contains("register"));
	}


	/*
	 * @When("^enter the user data$") public void enter_the_user_data() throw
	 * Throwable { registerObject = new UserRegistrationPage(driver);
	 * registerObject.userRegistration("asd", "asdasd", "asda@test.com",
	 * "123456789"); }
	 */


	@When("^enter the user data \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void enter_the_user_data(String firstName, String lastName, String email, String password) {
		registerObject = new UserRegistrationPage(driver);
		registerObject.userRegistration(firstName, lastName, email, password);
	}

	@Then("^registration page displayed successfully$")
	public void registration_page_displayed_successfully() throws Throwable {
		registerObject.userLogout();
	}
}
