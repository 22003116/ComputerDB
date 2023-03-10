package computerdatabase;

import java.util.List;

import org.openqa.selenium.WebElement;

import testframework.WebOperations;

public class ComputerSearchPage  {
	
	private WebOperations _webOp = null;
	
	public ComputerSearchPage( WebOperations webOp ) {
		_webOp = webOp;
	}
	
	public void searchComputersByNameContaining( String keyword ) {
		_webOp.type("txtFilter", keyword, false);
		_webOp.click("btnFilter");
	}
	
	public ComputerCreationPage goToComputerCreationPage() {
		_webOp.click("btnAddComputer");
		return new ComputerCreationPage( _webOp );
	}
	
	public void goToNextPage() {
		_webOp.click("btnNext");
	}
	
	public void goToPreviousPage() {
		_webOp.click("btnPrevious");
	}
	
	public int getNumberOfComputersFound() {
		int nbComputers = 0;
		String text = _webOp.getText("mainMessage");
		if ( text != null ) {
			String nbText = text.substring(0, text.indexOf(" "));
			nbComputers = Integer.valueOf(nbText.trim());
		}
		return nbComputers;
	}
	
	public boolean isComputerInActivePage( String computerName ) {
		_webOp.getRepository().setVar("name", computerName);
		WebElement we = _webOp.waitAndFindEnabledElement("computerLink");
		return we != null;
	}
	
	public String getComputerAtRow( int row ) {
		List<WebElement> weList = _webOp.getElements("computerLinks");
		String computer = null;
		if ( weList.size() >= row ) {
			computer = weList.get(row-1).getText();
		}
		return computer;
	}
	
	public boolean areAllComputerNamesInPageContains( String keyword ) {
		List<WebElement> weList = _webOp.getElements("computerLinks");
		for( WebElement we : weList ) {
			if ( ! we.getText().toUpperCase().contains(keyword.toUpperCase())) {
				return false;
			}
		}
		return true;
	}
	
	public ComputerUpdatePage goToComputerUpdatePage( String computerName ) {
		if ( isComputerInActivePage(computerName)) {
			_webOp.click("computerLink");
			return new ComputerUpdatePage( _webOp);
		}
		return null;
	}

	public String getCountingMessage() {
		return _webOp.getText("mainMessage");
	}
	
}
