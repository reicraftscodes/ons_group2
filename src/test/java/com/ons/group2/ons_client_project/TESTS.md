# Manual Tests

##HelpRequestController
### /createRequest
 - [ ] Page loads and displays "Request help from your co-workers" along with 3 input boxes and a submit button
 - [ ] All skills the user has saved to their profile will appear in the 3rd input box, if none are present go to the 
 endpoint /user/profile and add skills under the skills tab, come back to /createRequest and fill out form
 - [ ] If title is not present submission will not be possible
 - [ ] If the description is under 30 characters submission will not be possible and you will be returned to the form, no error messages will appear on this page as i could not get them working
 - [ ] If at least one skill is not selected then submission will be possible
 
 ### /submitRequest
 - [ ] Data filled out on form saved to the database and taken to the endpoint /helpRequest/{id}
 - [ ] `SELECT
         *
       FROM
         help_request
       ORDER BY help_request_id DESC;` Check most recent db entry to see if help request has been saved.
 ### /helpRequest/{id}
 - [ ] Title of help request is displayed
 - [ ] Date posted of help request is displayed
 - [ ] Description of help request is displayed
 - [ ] Contact field appears with link that takes user to email service to email poster (if possible on user system)
 
 ##Making skills private
 - [ ] Does a check box appear asking if you want to make a skill public when at /user/profile when under the skills tab 
 adding a new skill?
 - [ ] Are you able to select the check box to make a skill private?
 - [ ] After adding that skill are you able to search for the skill using the search engine at /user under the "mydatatable" option on the dropdown button near the search box?
