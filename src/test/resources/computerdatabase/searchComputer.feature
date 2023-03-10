Feature: Searching computers
  I want to search computers which name contains a given keyword
  I want to navigate through result pages and sort result pages

  @searching
  Scenario Outline: Searching computers by company keyword
    Given I load the computer database homepage
    When I search computers with <company> keyword
    Then I should see a list of computer names all containing <company>
    And I should see <message> as counting message

    Examples: 
      | company  | message 						  |
      | "Apple"  | "13 computers found" |
      | "IBM"    | "25 computers found" |
      

	
    
  
  
  
