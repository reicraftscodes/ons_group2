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
 
 ### /helpRequest/{id}
 - [ ] Title of help request is displayed
 - [ ] Date posted of help request is displayed
 - [ ] Description of help request is displayed
 - [ ] Contact field appears with link that takes user to email service to email poster (if possible on user system)