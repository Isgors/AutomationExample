Meta:
@StoryType WEB

Narrative:
As a possible petz client
I want to search for ration
and select the third element from the list
and validate the product's name, supplier, normal price and subscriber price
So that i can add the product to the shopping cart
and validate if the product info is the same

Scenario: Search a product and it to the shopping cart
Meta:
@TestCaseId 3
Given the user opens the browser
And the user access the petz site home page
And the user search for Ração
When the user select the 3 element
And save product info
And the user add the product to the shopping cart
Then the product info remain the same
And product total price is correct
And the user ends the current session

Scenario: Search multiple products and them to the shopping cart
Meta:
@TestCaseId 4
Given the user opens the browser
And the user access the petz site home page
And the user search for <productName>
When the user select the 3 element
And save product info
And the user add the product to the shopping cart
Then the product info remain the same
And product total price is correct
And the user ends the current session
Examples:
|productName|
|Areia|
|Brinquedo|
|Manta|
|Caixa|
|Gaiola|
|Ração umida|
|Semente|
|Aquario|
|Inseticida|
|Terra|