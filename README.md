# Team Project
Bearcat Car Pooling

## Team members
- Harshitha Chetty Ragava
- Gouthami Pasham
- Vamshi krishna Nuka

## GitHub repo URL:
https://github.com/HarshithaChettyRagava/BearcatCarPooling


## App Credentials to directly signIn 
- Username : Saisri@gmail.com
- Password : Saihoney@22

## Pre requisites to display Google maps on your emulator
- Install google play services, google repository from the SDK manager
- You have to use the android emulator of API version 24 or more(Nougat or more)
- You have to update your google play services on the emulator.
- You should have API key present in google_maps_api.xml in resources/values
- The above xml file we will sending an email to the corresponding person due to security reasons. (can not place in gitHub since it will accessible publicly)

## User Guide:

### Login Page:

- User can login into the applicaion, if he an already existing user.
- Application will validate the user and redirect him to the car booking page.

### Sign Up Page:

- New users are allowed to login by providing the basic information.
- System validates the entries provided by the user.

### Car Booking:

- Once the user Login, a map would be displayed where he would be allowed to enter the destination location.
- The current location will already be pointed on the map.
- Select the destination location and click the tiney arrow button on the bottom left of the screen.
- User will be navigated to the next page "Confirmation Page".

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
- Parse Database.
- Alert Boxes.
- Action Bar.
- Google Maps- Not taught in class