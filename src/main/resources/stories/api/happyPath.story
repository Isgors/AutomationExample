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
