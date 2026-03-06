# SmartHome-Gesture-Control-Application-Project
CSE 535: Mobile Computing : SmartHome Gesture Control Application : Project Part 1

SmartHome Gesture Control Application
Project Part 1

Purpose
Gesture-based SmartHome devices have the ability to increase convenience, but also create more
accessible SmartHome devices for the elderly and those with disabilities. In this project, you will
develop an application service that will control SmartHome devices with gestures, and then develop a
RESTful application service for classifying the SmartHome gestures. This project provides hands-on
experience developing a mobile application and provides an excellent opportunity to gain further
exposure to topics and applications in the areas of mobile computing and machine learning.

Objectives
At the completion of this individual project, learners should be able to:
● Develop an application service that will control SmartHome devices with gestures.
● Develop a mobile application using Android Studio.

Technology Requirements
● Android Studio
○ Download instructions resource: Install Android Studio
● Either MatLab or Python
● YouTube account for video submission

Project Description
To complete Part 1 of this project, you will develop an application service that will control SmartHome
devices with gestures. You will gain hands-on experience developing a mobile application using
Android Studio.
SmartHome Gesture Control Application Project Part 1 is partly peer-graded, and it is also reviewed
by the course team for fairness and accuracy. The overall project accounts for 40% of your grade.

Directions
Functionality
For Part 1 of this project, you will develop a mobile application with the listed functionalities:
A. The user is shown a video of a gesture.
B. The user can replay the video at least three (3) times.
C. Upon clicking the "PRACTICE" button, the user can capture his or her own video through the
smartphone's front camera for a period of at most five (5) seconds.
D. The videos are uploaded to a server.
Note:
● You may also use fog/cloud server. Please make sure to show your video is
successfully uploaded in your demo video.
● You may use your webcam if you do not have or do not want to use a physical Android
phone.

The mobile application should have three (3) screens.
Screen 1
A drop-down menu of 17 different gestures will be shown on this screen. Once a single gesture is
selected, the user will be taken to Screen 2.
● Gesture list: {Turn on lights, Turn off lights, Turn on fan, Turn off fan, Increase fan speed,
decrease fan speed, Set Thermostat to specified temperature, gestures one for each digit
0,1,2,3,4,5,6,7,8,9}

Screen 2
The video of an expert performing the gesture will be shown on this screen. Screen 2 will have
another button that says "PRACTICE". Once this button is pressed, the user will be taken to Screen
3.

Screen 3
In this screen, the camera interface will be opened for the user to record the practice gesture. The
video will be captured for five (5) seconds, and the video will be saved with this filename format:
● [GESTURE NAME]_PRACTICE_[practice number]_[USER LASTNAME].mp4
Use the Gesture - Action and Gesture - Number tables for gesture names:
Gesture - Action Gesture Label
Turn On Light LightOn
Turn Off Light LightOff
Turn On Fan FanOn
Turn Off Fan FanOff
Increase Fan Speed FanUp
Decrease Fan Speed FanDown
Set Thermostat to specified
temperature
SetThermo
Gesture - Number Gesture Name
0 Num0
1 Num1
2 Num2
3 Num3
4 Num4
5 Num5
6 Num6
7 Num7
8 Num8
9 Num9

Screen 3 will have another button that says "UPLOAD". Once this button is pressed, the user will be
able to upload the gesture to a local server. Moreover, clicking this button will take the user back to
Screen 1.

Expert Videos
The file Expert-Gestures.zip contains video clips of the gestures that you will use for the example
shown on Screen 2. This zip file is available in your course in the "Project Overviews and Resources"
page in the Welcome and Start Here section.

Gesture Creation
You will create at least three (3) correct video versions of each gesture for a total of 51 videos. You
will use the best of these as the expert gesture to be shown on Screen 2 of the application. You can
practice as many times as you want. You will upload a zip file of your gestures. Follow these naming
conventions for your files:
● [GESTURE NAME]_PRACTICE_[practice number]_[USER LASTNAME].mp4
● Videos_[Full Name].zip

Video guidelines:
● Videos should focus only on showing the gestures. Do not show your face. Surroundings
should be kept out or kept to a minimum in the videos.
● Use the front camera for recording. However, you can activate the back camera in your app by
default and manually switch to the front camera.
● Your APP needs to provide functions to open and use the camera. You cannot quit the APP
and open the camera manually for recording.

Demo Video
Create a video demonstration of the application being used. For the demo, record an application
demonstration of the Functionality (parts A-C). Begin recording from when you start the application
and show all of the required functions.

Video guidelines:
● Videos should focus on only showing the gestures. Do not show your face. Surroundings
should be kept out or kept to a minimum in the videos.
In order to submit your demo video to the course, you should create an account on Youtube, Vimeo,
or some other free video hosting service and upload the required video for the assignment to that
account. When you create your video, ensure that the settings allow anyone with the link to view the
video.

Local Server
Develop your own local server on your own computer.
Note: Using Flask as your local server is recommended.
Testing Environment for Grading
Occasionally, a source code will not work correctly when the development environment is different
from the listed environments. If this occurs, we are not able to give you the correct grade because it is
not easy to find the reason why your application failed. If possible, please use one of the environment
settings listed here to avoid receiving the wrong grade:
● Pixel 9
● API 35 or above

Peer Review Criteria
Is the user shown a video of a gesture?
Can the user learn the gesture?
Upon clicking the "PRACTICE" button, can the user capture his or her own video through the
smartphone's front camera for a period of five (5) seconds (at most)?
