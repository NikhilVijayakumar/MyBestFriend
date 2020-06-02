# MyBestFriend

This is a sample demo project for Google's 30 Days of Kotlin. The app has a register and login features along with two other views showing cat breed listing and cat details. Users can also save unit preference in the app to view weight in metric(Kg) or imperial(lbs). Cat details will fetch from https://thecatapi.com/ .App is fully build in Kotlin

The app will have the following features

1. Splash 
1. Login 
1. Register 
1. Cat listing   
1. Cat detail  
1. Preference 

## Splash

1. Splash is the launching page of our app. 
1. From splash after 3 seconds app navigate to login. 

The Splash is given below

![Splash](https://github.com/NikhilVijayakumar/MyBestFriend/blob/master/screenshots/splash.png)

## Login

1. App login view
1. Login an emaild and password. 
1. Input validation for email and password
1. Sign up button for navigate to registation view. 
1. On sucessful user navigate to cat listing view
1. Currenly we are not using any API for user management 
1. Userdetails is stored in sqlite using room API

Login is shown below

![Login](https://github.com/NikhilVijayakumar/MyBestFriend/blob/master/screenshots/login.png)

### Login input validations

1. Validate email is not empty
1. Validate password is not empty
1. Validate email format
1. Validate password for minimum length of 6 character 

Login with empty email and password


![Login](https://github.com/NikhilVijayakumar/MyBestFriend/blob/master/screenshots/login_empty.png)

Login with invalid email format and short password

![Login](https://github.com/NikhilVijayakumar/MyBestFriend/blob/master/screenshots/login_fomat.png)
