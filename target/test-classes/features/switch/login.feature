@test
Feature: Test login scenarios

Background: 
Given I start application

Scenario: Login with valid credentials
Given I Wait for "switchtestapploginpage" to load
When I Provide "PASSCODE" text input as "1234"
Then I Wait for "switchtestappmobileregisterpage" to load
When I Provide "MobileNumber" text input as "8377095558"
And I click on "Verify Mobile"
