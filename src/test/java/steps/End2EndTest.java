package steps;

import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.CheckoutPage;
import pages.OrderDetailsPage;
import pages.ProductDetailsPage;
import pages.SearchPage;
import pages.ShoppingCartPage;
import tests.TestBase;

public class End2EndTest extends TestBase{
	
	SearchPage searchObject;
	ProductDetailsPage productdetailsObject;
	CheckoutPage checkoutObject;
	ShoppingCartPage cartObject;
	OrderDetailsPage orderObject;
	String productName = "Apple MacBook Pro 13-inch";
	
	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
	    Assert.assertTrue(driver.getCurrentUrl().contains("demo.nopcommerce.com"));
	}

	@When("^he search for \"([^\"]*)\"$")
	public void he_search_for(String productName) throws Throwable {
	    searchObject = new SearchPage(driver);
	    productdetailsObject = new ProductDetailsPage(driver);
	    searchObject.ProductSearchUsingAutoSuggest(productName);
	    Assert.assertTrue(productdetailsObject.productNamebreadCrumb.getText().contains(productName));
	}

	@When("^choose to buy Two items$")
	public void choose_to_buy_Two_items() throws Throwable {
	    productdetailsObject.AddToCart();
		driver.navigate().to("http://demo.nopcommerce.com/" + "cart");
	}

	@When("^moves to checkout cart and enter personal details on checkout page and place the order$")
	public void moves_to_checkout_cart_and_enter_personal_details_on_checkout_page_and_place_the_order() throws Throwable {
	    checkoutObject = new CheckoutPage(driver);
	    cartObject = new ShoppingCartPage(driver);
	    cartObject.openCheckoutPageAsGuest();
	    checkoutObject.CheckoutProduct("test", "user", "Egypt"
				, "testuser1@test.com", "test address", "123456", "32445566677", "Cairo", productName);
	    Assert.assertTrue(checkoutObject.prodcutName.isDisplayed());
		Assert.assertTrue(checkoutObject.prodcutName.getText().contains(productName));
		checkoutObject.confirmOrder();
		Assert.assertTrue(checkoutObject.ThankYoulbl.isDisplayed());
	}

	@Then("^he can view the order and download the invoice$")
	public void he_can_view_the_order_and_download_the_invoice() throws Throwable {
		orderObject = new OrderDetailsPage(driver);
		checkoutObject.viewOrderDetails();
		Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
		orderObject.DownloadPDFInvoice();
		Thread.sleep(3000);
		orderObject.PrintOrderDetails();
	}
}
