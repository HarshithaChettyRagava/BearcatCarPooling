# Team Project
Bearcat Car Pooling

## Team members
- Harshitha Chetty Ragava
- Gouthami Pasham
- Vamshi krishna Nuka

## GitHub repo URL:
https://github.com/HarshithaChettyRagava/BearcatCarPooling

## Back4app Parse database URL and credentials:
http://back4app.com
- Username : harshitha.chettyragava@gmail.com
- Password: Back4app@12
- App name in the Dashboard - BearcatCarPooling

## App Credentials to directly signIn 
- Username : Saisri@gmail.com
- Password : Saihoney@22

## Pre requisites to display Google maps on the emulator
- Install google play services, google repository from the SDK manager
- You have to use the android emulator of API version 24 or more(Nougat or more)
- You have to update your google play services on the emulator.
- You should login with your gmail account to update google play services on the emulator
- You should have API key present in google_maps_api.xml in resources/values
- The above xml file we sent via an email to the GA due to security reasons and also will be submitted along with the zip file in the submission(can not place in gitHub since it will be accessible publicly)

## User Guide:

### Login Page:

- User can login into the applicaion, if he an already existing user.
- Application will validate the user and redirect him to the car booking page.

### Sign Up Page:

- New users are allowed to login by providing the basic information.
- System validates the entries provided by the user.

### Map Page:

- By default, Current location will be taken as the pick up location or source location.
- User should enter destination in order to book a cab.
- After entering destination user should press enter to find the location.
- After clicking on the tiney forward arrow button which is on the bottom right of the screen, Source and destination locations will be send to confirmation activity for calculating the distance in miles.
- Then the user will be navigated to the next page "Confirmation Page".

### Cofirmation/ Cost Estimation Page:

- Application calculates the number of miles the user wish to travel by capturing the latitude and longitude on the map.
- The number of available seats in the selected car would be displayed on the screen.
- User should choose the number of seats he wish to book. This number should be less than or equal to the available seats.
- The cost for required seats would be calculated and displayed for the user.
- Now the user can make his dicision and confirm his booking. He can choose to cancel the ride.

### Reached Page:

- Once the user reached the destination, he will be redirected to the reached page.
- The user can see the detailed description of his ride. 
- The total amount he needs to pay and the tax amount.
- The user has an option to pay by cash or card.

### Feedback Page:

- Feedback is really important for any business. To understand the user satisfaction and improve the business.
- If the user is happy with the ride, A simple Thank you message appears on the screen.
- If the user gives any ratig less than 3, he would be redirected to another page.

### Aditional Feedback:

- A detailed feedback page, where he will get to chhose more options to help improve his satisfaction.
- Once the user click submit, he will be redirected to the home page.

## Techical Details:

- We have tried to implement the concept leared in class and tried to explore more.
- Layouts: Grid layout, Linear Layout, Relative Layout.
- Intent
- Parse Database
- Fragment
- Alert Boxes
- Action Bar
- Google Maps- Not taught in class

## List of functionalities from the app proposal:
- User login page - Sign Up and Sign In - Completed
- Database to store user data - Completed
- Validating data to ensure no multiple Sign-Ups for same email or mobile number - Completed
- Getting GPS location and matching user locations using Google APK’s with the help of coordinates - Completed
- An activity of getting ride confirmation and payment method - Completed.
- Creation of hamburger menu option for home, user profile, logout, etc.,. - 70% Completed with the overflow menu containing ContactUs and logout functionalities. 
- If possible will add a mile range of 10, 20, 30 - Not yet completed.
- Feedback from riders - Completed

## .apk file in the following path and also uploading in the submission:
- BearcatCarPooling\app\build\outputs\apk\debug