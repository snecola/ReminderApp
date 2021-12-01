# Use Case Model
**Author**: Team 3 - Harjit Liyal

### 1) Use Case Diagram
![](https://i.ibb.co/1Jv3tLS/Use-Case-Model.png) 

### 2) Use Case Descriptions

**Requirements** : The user must be able to: 

* Create a reminder list - A reminder list that holds multiple reminders in multiple categories  
* Create reminders - User can create a reminder that shows the type of reminder, reminder description, and the date/location they want to be alerted at. These are able to be repeating reminders
* Edit reminders - User is able to edit their existing reminders. ex) Change reminder name , change reminder date/location
* Delete reminders - User is able to delete their reminders from the reminder list



**Pre-conditions**: Conditions that must be true before the use case is run: 

* In order to create a reminder list the name for the list must be available
* A reminder list with a category must exist to make a reminder
* A reminder must exist in order for the user to edit a reminder 
* A reminder must exist in order for the user to delete a reminder



**Post-conditions**: Conditions that must be true after the use is run: 

* Reminder list name must be valid
* Reminder must be placed in a category in a reminder list upon creation
* Database must be updated whenever a user creates a reminder list or creates/edits/deletes a reminder
* Reminders must update to reflect what the user 



**Scenarios**: 

##### **User creates reminder list**: 

 ![](https://i.ibb.co/pP3vvLW/reminderlist.png)



##### **User creates reminder**: 

![](https://i.ibb.co/02NX9Pr/reminder.png)



**User edits/deletes reminder**:

 ![](https://i.ibb.co/WB3fqv5/editreminder.png)

