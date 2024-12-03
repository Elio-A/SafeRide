# SafeRide Application

This is an Android application designed for UNB's CS2063 Course.  More specifically, this application is designed for UNB's service: SafeRide.  It allows students to be linked with other students who are assigned as drivers.  Students can sign into the app using their student ID and a password of their choice, they are then given details about the services our app provides. Our app gives information on where to find pick-up stations, the soonest time passengers can get picked up, and much more.  On the driver side of the application, the application helps the driver log their shift. The drivers select their car, take pictures of the car, and choose which station they will be operating during their shift. The drivers then will be taken to a page that generates a QR code that the passengers can scan through the app.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contact](#contact)

## Features

- Feature 1: The application makes use of various SQLite functionality to store, and retrieve information about users. (Login & Signup are an example of this feature).
- Feature 2: Users can input their next destination and select which station to be picked up from. The driver will then retrieve their next passengers' information from the database, at which point they can see their destinations.
- Feature 3: On the passenger side, the application is able to open a QR code scanner.
- Feature 4: On the driver side, the application generates a QR code that is to be scanned by the passengers.
- Feature 5: The application provides users with all available information surrounding the SafeRide services. This includes pick-up stations, working hours, the radius the drivers must follow, and more.
- Feature 6: The application provides users with the status of the drivers. This information includes whether the driver is at their designated station or out on a trip. If the driver is out on a trip, the application displays an estimate of how long the driver would need to be back at the station for the next passengers.

## Installation

Follow these steps to install the SafeRide application:

Clone the repository:
'''bash
git clone 

## Usage

Here are a few instructions that can guide you through using our application:

1. Start the application and click on signup to create an account.
2. Once an account has been created, you will be redirected to the login page.
3. After logging in as a passenger, you will see a list of different buttons that do the following:
	- "Live Status": This button displays the live status of all the drivers. The passenger can see which driver will be available the soonest.
	- "Pickup Stations": This button displays where each station is located around campus via pin-points on a map. A picture of each station is also displayed to replicate a street vue.
	- "Radius": This button redirects you to a picture of a map, showing the permited radius that the driver must follow.
	- "Working Hours": This button takes you to a page that contains text information about the working hours that each pickup station follows.
	- "QR Code": This button starts a QR code scanner. The intention of this QR code scanner is to scan the QR code generated on the driver's side, which takes the passenger to the shared Google spreadsheet.
	- "Pick-up Request": This button takes the user to a page where they are asked to input their next destination and which pick-up station they want to be picked up from.
	- "News": This button takes you to the SafeRide website for the latest news and information.
	- "About Us": This button takes you to a page that explains SafeRide's service and the page contains a button that redirects you to SafeRide's Messenger chat.
4. If you tick the "I am a driver" box, you will be automatically redirected to a separate set of activities/pages when "Log in" is pressed:
	- You, as the driver, will be redirected to a page containing images and license plate numbers of each SafeRide car.
	- Once clicked, each car image will redirect you to another page containing image drop-boxes that require you to capture (or choose from gallery) and upload images of the four sides of the car that you will be driving for this shift.  You will be required to upload exactly four images to be able to proceed to the next page.
	- Once that is done, you will be redirected to another page that contains images of each pickup station.
	- Once you click which station you will be working from, you will finally be redirected to the driver home page which contains a QR code that passengers can scan and:
		- a "Start Trip" button which retrieves the passenger ID and the destination of each passenger in your vehicle,
		- a "Spreadsheet" button that redirects you to the same shared Google spreadsheet that the QR code is linked to,
		- a "Google Maps" button that opens Google Maps with Fredericton as the preset view; If Google Maps is not downloaded, you will be asked to do so,
		- and a "Log Out" button.
## Contact

Here are the emails of all three contributors to this project:

1. kalhindi@unb.ca
2. ealalam@unb.ca
3. albertus.university@unb.ca
