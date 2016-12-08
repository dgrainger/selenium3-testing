# selenium3-testing

The AutoCompleteTest is designed to show the differences between Chrome and Firefox using Selenium 3.0.

The test case should open the Southwest website for booking a flight, set the FROM field to be EWR (Newark, NJ) then take a screenshot to show the FROM field has been populated with EWR.

If you set the _browser_ variable to *chrome* and run the test, it will create the screenshot _autoCompleteFromInput-chrome.png_. When you look at this you will see that the FROM field has been populated.

If you set the _browser_ variable to *firefox* and run the test, it will create the screenshot _autoCompleteFromInput-firefox.png_. When you look at this file you will see that the FROM field is *not* populated. This is because the Firefox implementation of Selenium 3.0 isn't quite working.

In Selenium 2.0 this code would have worked in both Chrome and Firefox. But with Selenium 3.0 we have to either wait for Firefox support to improve or find a way around this difference. For a client wanting us to automate this, waiting isn't always an option. So we might have to figure out workarounds for issues like this.

Darrell Grainger - December 2016
