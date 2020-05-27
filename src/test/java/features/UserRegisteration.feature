Feature: User Registration
       I want to check the user can register in our website
       
Scenario Outline: User Registration
Given the user going to home page
When click on register button
And enter the user data "<firstName>" , "<lastName>" , "<email>" , "<password>"
Then registration page displayed successfully

Examples:
				
				| firstName | lastName | email | password |
				| Doaaa | Khalafa | doaaa@test.com | 123456789 |
				| Faridaa | Ahmeda | faridaa@test.com | 123456789 |
				| Mariama | Ahmeda | mariama@test.com | 123456789 |