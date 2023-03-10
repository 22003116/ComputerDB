package testframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Context {
	
	private Repository _repository;
	private WebDriver _driver;
	private String _baseURL;
	public static enum Browser { CHROME, FIREFOX, EDGE }
	
	public Context( Repository repository, String baseURL, Browser browser ) {
		_repository = repository;
		_baseURL = baseURL;
		switch( browser ) {
		case CHROME  :
			newChromeDriver();
			break;
		case FIREFOX :
			newFirefoxDriver();
			break;
		case EDGE    :
			newEdgeDriver();
		}
	}
	
	static {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.edgedriver().setup();
	}
	
	public void newChromeDriver() {
		_driver =  new ChromeDriver();
		_driver.get(_baseURL);
		
	}
	
	public void newFirefoxDriver() {
		_driver = new FirefoxDriver();
		_driver.get( _baseURL );
	}

	public void newEdgeDriver() {
		_driver = new EdgeDriver();
		_driver.get( _baseURL );
	}
	
	public WebOperations getWebOperations() {
		return new WebOperations( _repository , _driver );
	}

	
}
