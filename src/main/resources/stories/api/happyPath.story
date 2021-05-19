Meta:
@StoryType API

Narrative:
As a petstore manager
I want to create an user
and create a pet
So that i can sell the pet to the user

Scenario: When an user and a pet are created, then the pet is selled to the user
Given a user is created with Maria Assunção name
And a pet is created with Brutus name
When the pet Brutus is selled to the Maria Assunção user
And the pet Brutus sales order status is changed to delivered
Then the sales order status is delivered