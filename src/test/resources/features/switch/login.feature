@test
Feature: Test login scenarios

Background: 
Given I start application

Scenario: Login with valid credentials
Given I Wait for "switchtestapploginpage" to load
And I Wait until "PASSCODELABEL" is present
When I Provide "PASSCODE" text input as "1234"
Then Verify that "SEARCH_LOAN_BY_NUMBER" should be enabled