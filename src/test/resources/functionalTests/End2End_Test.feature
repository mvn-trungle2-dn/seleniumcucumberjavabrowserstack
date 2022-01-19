Feature: Automated E2E Tests
Description: The purpose of this feature is to test End 2 End integration

  #Scenario: Customer place an order by purchasing an item from search
    #Given : user is on Home page
    #When : he search for "dress"
    #And : choose to buy the first item
    #And : moves to checkout from mini cart
    #And : enter persona_details on checkout page
    #And : place the order

  Scenario Outline: Customer place an order by purchasing an item from search
    Given : user is on Home page
    When : he search for "dress"
    And : choose to buy the first item
    And : moves to checkout from mini cart
    And : enter "<customer>" on checkout page
    And : place the order

  Examples:
	|customer|
	|Hao Do|
    |Vjay Kumar|