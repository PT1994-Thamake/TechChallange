# Tech Challange Project

## Project Overview

This project is designed to automate the testing of the Tendable website. The tests focus on two key functionalities:

1. **Menu Accessibility and Request Demo Button**: Tests if the menu items are accessible and if the "Request a Demo" button is visible and active on each page.
2. **Contact Form Submission**: Automates the process of filling out a contact form with the "Message" field left empty to verify if the appropriate error message ("Form Submission Failed") is displayed.

## Strategy Followed

For this challenge, I chose to use **Selenium WebDriver** for browser automation and **TestNG** as the testing framework. The project involves automating a series of functional tests on a contact form that ensures required fields trigger error messages when left empty.

The steps I followed include:
1. **Identifying Test Scenarios**: I first outlined the various test scenarios, including valid submissions, invalid form submissions (missing required fields), and checking if error messages are correctly displayed.

2. **Locating Web Elements**: I used XPath to locate form fields and buttons on the page.

3. **Automation**: Using Selenium, I simulated user interactions (e.g., clicking, typing) to fill out the form and submit it. I also used **WebDriverWait** to wait for elements like error messages to appear.

4. **Error Handling**: I added logic to handle and capture error messages when fields like "Message" are left empty, as per the requirement.

5. **Testing Strategy**: I implemented assertions using **TestNG** to check whether the error message appears when expected, marking tests as PASS or FAIL based on the outcome.

6. **Scrolling Page**: I used JavascriptExecutor to scroll down the page
   
7. **Used select class**: To select the element from dropdown
   
8. **Properties File**: I used properties file to keep all data and locators
    
9. **Utility class**: crate utility class to read properties file 

During the development process, I faced challenges with handling dynamic content and waiting for elements to load. I overcame this by using explicit waits with Selenium to ensure elements were interactable before performing actions on them.


### Prerequisites:
1. **Java**: This project is written in Java. You must have JDK installed on your machine. (Recommended version: Java 8 or higher)
2. **Maven**: Maven is used for managing dependencies and running the tests.
3. **Selenium WebDriver**: Selenium is used for browser automation.

## How to Run the Test Project
1. import the project in your machine 
2. You can directly run the class TendableTest.class or by using testng you can run it

#added Tendable bug report: you can take it out from this project as it is word file and verify seperately 
