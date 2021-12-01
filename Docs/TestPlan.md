# Test Plan

## 1 Testing Strategy

A Test Strategy is a plan for defining an approach to the Software Testing Life Cycle (STLC). It guides QA teams to define test coverage and testing scope.

### 1.1 Overall strategy

The purpose of our strategy is to provide a rational deduction from organizational, high-level objectives to actual test activities to meet those objectives from a quality assurance perspective. To achieve it, we will go through four stages of software testing, Unit Testing, Integration Testing, System Testing, and Acceptance Testing. Regression Testing will take place throughout the four stages to test the unchanged parts of the software.

Unit Testing : We will test the individual components of the system to check whether they are functioning properly or not. This testing will take place whenever a change is made in the codebase. By doing this step, we can make sure that all issues are resolved quickly. After implementing or resolving the testing, we will verify the result.

> - list Name
> - add Reminder
> - delete Reminder
> - edit Reminder
> - check off Reminder
> - add type of Reminder
> - edit type of Reminder

Integration Testing : After testing the individual component we will test them as a collective group. This will allow us to determine the performance of individual components as a group and identify any problems in the interface between the modules and functions.

> - create new type of Reminder
> - create new Reminder
> - create new ReminderList
> - set List Name
> - rename List

System Testing : We will check whether or not the collective group of integrated components is performing optimally. Then we will run the whole application to check if they function as expected.

> TBA

Acceptance Testing : This testing is necessary to evaluate if the software is ready to be released for user consumption. The representatives of the customer who test the application by using it.

> TBA

Regression Testing : This testing is done after the codes are fixed, upgraded or any other system maintenance to check the new code has not affected the existing code.

### 1.2 Test Selection

White-Box Testing Technique: White Box Testing, also known as Structural Testing, is required to detect and fix bugs and errors emerging during the pre-production stage of the software development process. At this stage, unit testing based on the software structure will be performed using regression testing. This testing technique will be used in Unit Testing and Integration Testing.

Black-Box Testing Technique: Black Box Testing, also known as Behavioral Testing, presupposes running numerous tests, mostly manual, to see the product from the user’s point of view. It also may include regression testings to eliminate human error if repetitive activities are required. This technique will be used in system Testing and Acceptance Testing.

\*test cases mentioned in 1.1 for all the Testing stages will be performed in this section.

### 1.3 Adequacy Criterion

Adequacy Criterion is a predicate that is either true or false. It is the completeness of a test result.

Functional Coverage is the measure of the completeness of software testing showing how much design functionality has been covered by the verification environment. This coverage ensures that all the functions are tested properly. So to assess the quality of our test cases, we will use this coverage at Unit and Integration Testing,

Structural Coverage is the measure of the completeness of software testing showing which areas of the source code are exercised in the application during the test. We will use it in System Testing to assess the completeness of structure of the application as a whole.

### 1.4 Bug Tracking

Unit Testing: As each unit is tested individually, if an error occurs we will know where the bug is and fix it.

Integration Testing: Collective group of two or more units is tested in this stage. As the units passed the Unit Testing, if an error occurs, we will know that it occurs when two units are combined. Based on that, we can fix the bug.

System Testing: After both Unit Testing and Integration Testing are passed through bug tracking, if an error occurs, it could mean that the units do not support the application as a whole.

Acceptance Testing: After the application has been tested by the customer, if they want to make any changes, they can make enhancement requests.

### 1.5 Technology

JUnit, Android Studio, Android Emulator. SQLite.

## 2 Test Cases

| Test Cases                  | Test Case ID                   | Purpose                                                  | Pre-Steps                                         | Expected Result                   | Actual Result                                                     | Pass/Fail |
| --------------------------- | ------------------------------ | -------------------------------------------------------- | ------------------------------------------------- | --------------------------------- | ----------------------------------------------------------------- | --------- |
| Add Reminders               | addReminder(Reminder) 11/14    | users will be able to add reminders.                     | Enter the application                             | Created new Reminder              | No Result                                                         | N/A       |
| ""                          | addReminder(Reminder) 11/22    | ""                                                       | ""                                                | ""                                | Does not show up                                                  | Fail      |
| ""                          | addReminder(Reminder) 11/23    | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| Remove Reminder             | deleteReminder(Reminder)       | users will be able to remove the reminders.              | An existing Reminder                              | Reminder Removed                  | TBA                                                               | TBA       |
| --                          | deleteReminder(Reminder) 11/14 | ""                                                       | ""                                                | ""                                | No Result                                                         | N/A       |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| Edit Reminders              | editReminder(Reminder)         | users will be able to edit their reminders               | An existing Reminder                              | Edited the Reminder               | TBA                                                               | TBA       |
| --                          | editReminder(Reminder) 11/14   | ""                                                       | ""                                                | ""                                | No Result                                                         | N/A       |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| Check Off Reminder          | checkoffReminder()             | users will be able to check off the reminders.           | An existing Reminder                              | Checked off the Reminder          | TBA                                                               | TBA       |
| --                          | checkoffReminder() 11/14       | ""                                                       | ""                                                | ""                                | No Result                                                         | N/A       |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| Clear Checked off Reminders | clearCheckOffs()               | users will be able to clear off the checked reminders    | I. An existing Reminder II. Cleared off Reminders | Cleared the checked off Reminders | TBA                                                               | TBA       |
| --                          | clearCheckOffs() 11/14         | ""                                                       | ""                                                | ""                                | No Result                                                         | N/A       |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| Add Type of Reminder        | addType                        | users will be able to add their own type of reminders    | I. Enter the application. II. Create a Reminder.  | Added a new type of Reminder      | TBA                                                               | TBA       |
| --                          | addType 11/14                  | ""                                                       | ""                                                | ""                                | No Result                                                         | N/A       |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| Remove Type of Reminder     | deleteType                     | users will be able to remove different type of reminders | Pre-existing Reminder Type                        | Removed a type of Reminder        | TBA                                                               | TBA       |
| ""                          | deleteType 11/14               | ""                                                       | ""                                                | ""                                | No Result                                                         | N/A       |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| Edit Type of Reminder       | editType                       | users will be able to edit different type of reminders   | Pre-existing Reminder Type                        | Edited the type of Reminder       | TBA                                                               | TBA       |
| ""                          | editType 11/14                 | ""                                                       | ""                                                | ""                                | No Result                                                         | N/A       |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| Reminder Repeat             | toggleRepeatAlert              | users will be able repeat the reminders                  | An existing Reminder                              | Reminder Repeats                  | TBA                                                               | TBA       |
| --                          | toggleRepeatAlert 11/14        | ""                                                       | ""                                                | ""                                | No Result                                                         | N/A       |
| ""                          | Alert Dialog 11/23             | ""                                                       | ""                                                | ""                                | Reminder Repeat set up. Insead of toggle, changed to Alert Dialog | Pass      |
| ""                          | toggleRepeatAlert 11/24        | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| Reminder Alert              | alert()                        | users will be alerted for a reminder                     | An existing Reminder                              | Sends Alerts                      | TBA                                                               | TBA       |
| ""                          | alert() 11/14                  | ""                                                       | ""                                                | ""                                | No Result                                                         | N/A       |
| ""                          | --                             | ""                                                       | ""                                                | ""                                | --                                                                | --        |
| ""                          | --                             | ""                                                       | ""                                                | --                                | --                                                                | --        |

\*11/14/2021 was the day, app prototype was created.

```

```
