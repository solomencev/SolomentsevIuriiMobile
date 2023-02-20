Before running tests, set the following environment variables: EPAM_NAME_SURNAME (name and surname in your EPAM account in the following format: "name_surname") MOBITRU_TOKEN (your Mobitru token)

To run the test for the Android Native app, run the following Maven command: mvn clean test -P nativeAndroid To run the test for the iOS Native app, run the following Maven command: mvn clean test -P nativeIOS

To run the test for the Android Web app, run the following Maven command: mvn clean test -P webAndroid To run the test for the iOS Web app, run the following Maven command: mvn clean test -P webIOS 
Currently, the test for iOS Web app fails after inputting search data (not possible to run search query)

To test a native application, you need to add environment variables ( email, username, password) at the maven level.