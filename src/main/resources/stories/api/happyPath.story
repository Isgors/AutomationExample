Meta:
@StoryType API

Narrative:
As a petstore manager
I want to create an user
and create a pet
So that i can sell the pet to the user

Scenario: When an user and a pet are created, then the pet is selled to the user
Meta:
@TestCaseId 1
Given a user is created with Maria Assunção name
And a dog named Brutus is created
When the pet is sold to the Maria Assunção user
And the pet sales order status is changed to DELIVERED
Then the sales order status is DELIVERED

Scenario: Create multiple users and pets
Meta:
@TestCaseId 2
Given a user is created with <username> name
And a <petCategoryName> named <petName> is created
When the pet is sold to the <username> user
And the pet sales order status is changed to <salesOrderStatus>
Then the sales order status is <salesOrderStatus>
Examples:
|username|petCategoryName|petName|salesOrderStatus|
|Username1|dog|Pet1|DELIVERED|
|Username1|cat|Pet2|APPROVED|
|Username2|dog|Pet3|DELIVERED|
|Username2|cat|Pet4|APPROVED|
|Username3|dog|Pet5|DELIVERED|
|Username3|cat|Pet6|APPROVED|
|Username4|dog|Pet7|DELIVERED|
|Username4|cat|Pet8|APPROVED|
|Username5|dog|Pet9|DELIVERED|
|Username5|cat|Pet10|APPROVED|
