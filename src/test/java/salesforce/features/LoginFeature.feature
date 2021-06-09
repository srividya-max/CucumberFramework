Feature: Login functionality

Background:
Given User launches the application
When  User should be able to enter the required credentials

Scenario: Validate Login with blank password
And   User enters valid username and blank password and clicks login
Then  User should see the error message for the blank password entered and the browser should close

Scenario: Validate Login with wrong username
And   User enters wrong username and valid password and clicks login
Then  User should see the error message for the wrong credentials entered and the browser should close

Scenario: Validate Login with valid credentials
And   User enters valid username and valid password and clicks login
Then  User should be able to see the Homepage of the application and the browser should close

Scenario: Validate Remember me Check box
And  User enters valid username and password
And  User clicks on Remember me checkbox and clicks login
Then User should be able to see the Homepage of the application
And  User should see the usermenu dropdown
And  User should select the logout option and click on it
And  User should close the browser

Scenario: Validate Forgot password link
And  User clicks on Forgot password link 
Then Salesforce Forgot password page should be displayed
When User enters valid username in username field and clicks on continue button
Then Check your email page should be diaplayed
And  Close the browser 