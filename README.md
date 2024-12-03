# SafeRide Application

This is an Android application designed for UNB's CS2063 Course.  More specifically, this application is designed for a service that UNB provides; SafeRide.  It allows students to be linked with other students who are assigned as drivers.  Students can sing into the app using their student ID and a password of their choice, they are then given details about what the service is, where to find pick-up stations, the soonest they can get picked up, and much more.  Moreover, students are able to input their information; information that includes their destination.  On the driver side of the application, the drivers are able to pick their car, take pictures of it before the ride starts, which station they will be operating from this shift, and then finally they will be taken to page that generates a QR code on their tablet that can be scanned by the passengers through the app on their smart devices.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contact](#contact)

## Features

- Feature 1: The application makes use of various SQLite functionality to store, and retrieve information about users. (Login & Signup are an example of this feature)
- Feature 2: Users can input their next destination and select which station to be picked up from. Driver will then retrieve their next passengers from the database and see their destinations
- Feature 3: On the passenger side, the application is able to open a QR code scanner.
- Feature 4: On the driver side, the application generates a QR code that is to be scanned by the passengers
- Feature 5: Users have all information about what is SafeRide and how to access it, including stations, working hours and radius
- Feature 6: Users can see the status of stations and their vehicles. They can see if the driver is at the station, or is on a trip, and if so, they can see an estimate of how long they would need

## Installation

Follow these steps to install the SafeRide application:

Clone the repository:
'''bash
git clone 

## Usage

Here are a few instructions that can guide you through using our application:

1. Start the application and click on signup to create an account.
2. Once account has been created, you will be redirected to the login page.
3. After logging in successfully as a passenger, you will see a list of different buttons that do different things:
	- "Live Status": This button displays the live status of all the drivers, and at which station the soonest next available driver will be.
	- "Pickup Stations": This button displays where each station is around campus via pin-points on a map and text explanation of where each station is.
	- "Radius": This button takes you to a picture of a map that highlights the radius that SafeRide drivers must not exceed, any location within this radius would be ok 	   to reach by the drivers.
	- "Working Hours": This button takes you to a page that contains text information about the working hours that each pickup station follows.
	- "QR Code": This button starts a QR code scanner that is able to scan any QR code, and then redirect the user to the link that is linked to the QR code.
	- "Manual Input": This button takes the user straight to the Google spreadsheet that users input their information in.  This is a "fail-safe" in the cases where 	   passengers are unable to use the QR code scanner.
	- "News": This button takes you to the SafeRide website for the latest news and information.
	- "About Us": This button takes you to a page that explains what the SafeRide service is, and contains a button that redirects you to the SafeRide IDK.......
4. If you ticked the "I am a driver" box, then after login you will be automatically redirected to a separate set of activities/pages:
	- You, as the driver, will be redirected to a page containing images and license plate numbers of each SafeRide car.
	- Once clicked, the car images will redirect you to another page containing image drop-boxes that require you to take and upload images of the four sides of the car 	  that you will be driving for this shift.  You will be required to upload exactly four images to be able to proceed to the next page.
	- Once that is done, you will be then redirected to another page that contains images of each pickup station.
	- once you click which station you will be working from this shift, you will finally be redirected to the driver home page which contains a QR code that passengers 	  can scan, a "Start Ride" button, and a "Log Out" button.
		- The "Start Ride" button redirects you to the shared Google spreadsheet that contains the passengers' information required to start the ride
		- The "Log Out" button prompts a Confirm Logout message.  If you agree to logging out, you are redirected the first page of this application; the login page.

## Contact

Here are the emails of all three contributors to this project:

1. kalhindi@unb.ca
2. ealalam@unb.ca
3. akoesoema@unb.ca
