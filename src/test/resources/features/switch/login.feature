@test
Feature: Test login scenarios

Background: 
Given I start application

Scenario: Register mobile with valid credentials
Given I Wait for "switchtestapploginpage" to load
When I Provide "PASSCODE" text input as "1234"
Then I Wait for "switchtestappmobileregisterpage" to load
#When I Provide "MobileNumber" text input as "8377095558"
And I click on "RegisterMessage"
And I click on "Verify Mobile"
And I click on "Send"
And I Wait for "switchtestappcreatevpapage" to load
And I Provide "VpaName" random text input as alphanumeric characters
And I click on "CheckAvailabilityButton"
And I Wait for "switchtestappmobilehomepage" to load
And I click on "AddAccount"
And I click on "ListAccountProvider"


Scenario: Register mobile with valid credentials
Given I Wait for "switchtestapploginpage" to load
When I Provide "PASSCODE" text input as "1234"
And I Wait for "switchtestappcreatevpapage" to load
And I Provide "VpaName" random text input as alphanumeric characters
And I click on "CheckAvailabilityButton"
And I Wait for "switchtestappmobilehomepage" to load
And I click on "AddAccount"
And I click on "ListAccountProvider"
And I Wait until "LISTELEMENT" is present
And I click on list element which has text "name='ICICI'"
And I Wait for "5" seconds
And I click on "LISTELEMENT"
And I click on "SelectedAccount"
And I input random card number and expiry as 1221 to upiswitchTestApp app
And I click on "requestOtp"
And I press back button
And I Wait until "RegisterMobile" is present
And I click on "RegisterMobile"
And I select and input UPI Pin
And I submit Mpin in CL page
And I select and input UPI Pin
And I submit Mpin in CL page

@test1
Scenario: Register mobile with valid credentials
Given I Wait for "switchtestapploginpage" to load
When I Provide "PASSCODE" text input as "1234"
And I Wait for "switchtestappmobilehomepage" to load
And I Wait until "AddAccount" is present
And I click on "AddAccount"
And I click on "ListAccountProvider"
And I Wait until "LISTELEMENT" is present
And I click on list element which has text "name='ICICI'"
And I Wait for "5" seconds
And I click on "LISTELEMENT"
And I click on "SelectedAccount"
And I input random card number and expiry as 1221 to upiswitchTestApp app
And I click on "requestOtp"
And I press back button
And I Wait until "RegisterMobile" is present
And I click on "RegisterMobile"
And I select and input UPI Pin
And I submit Mpin in CL page
And I select and input UPI Pin
And I submit Mpin in CL page

@test2
Scenario: Get Accounts request
Given I Wait for "switchtestapploginpage" to load
Given I start a log read thread searching for keyword "com.rohitupreti.testapplication"
When I Provide "PASSCODE" text input as "1234"
And I Wait for "switchtestappmobilehomepage" to load
And I Wait until "GetAccounts" is present
And I click on "GetAccounts"
And I Verify that "LISTELEMENT" contains text "Prakhar"
And I click on element at 0 index of "ListElement" list