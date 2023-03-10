package computerdatabase;

import io.cucumber.java.en.*;
import testframework.Context;
import testframework.WebOperations;

import static org.junit.jupiter.api.Assertions.*;

public class SearchComputersStepDefinitions {
	
	private static final String BASE_URL="https://computer-database.gatling.io/computers"; 
	private Context _context;
	private WebOperations _webOps;
	private ComputerSearchPage _searchPage;
	
	
	@Given("I load the computer database homepage")
	public void i_load_the_computer_database_homepage() {
		_context = new Context( new ComputerDBRepository(),BASE_URL, Context.Browser.CHROME );
		_webOps = _context.getWebOperations();
		_webOps.maximizeBrowserWindow();
		_searchPage = new ComputerSearchPage( _webOps );
	}

	@When("I search computers with {string} keyword")
	public void i_search_computers_with_keyword(String keyword) {
	   _searchPage.searchComputersByNameContaining( keyword );
	}

	@Then("I should see a list of computer names all containing {string}")
	public void i_should_see_a_list_of_computer_names_all_containing(String keyword) {
	    assertTrue( _searchPage.areAllComputerNamesInPageContains( keyword ));
	}

	@Then("I should see {string} as counting message")
	public void i_should_see_as_counting_message(String message ) {
	    assertEquals( message,  _searchPage.getCountingMessage() );
	}


}
