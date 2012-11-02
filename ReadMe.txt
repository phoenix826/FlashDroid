FlashDroid - Final Project Submission
Team Ux
    - William Wenzel
    - Matthew Addy
    - Edward Dinki
    - Shaun Devos

FlashDroid is a flash card study application for the Android platform. This
application was developed and designed for use with Android SDK 4.1.2, API 16.
The current state of the project is as follows:

- Full create, update, and delete operations on decks and cards. Cards and
  decks are currently stored to a database on the Android device, and are
  saved between sessions.
  
- Full implementation of the study activity. Settings have been implemented
  such that they will affect the behavior of the study activity.
  
- Statistics are tracked and are stored for the current session of the
  application (restarting the application will clear this data).
  
- Settings are fully implemented, but only stored for the current
  session of the application (as with statistics).
  
- Almost all of the UI considerations have been implemented. Since the initial
  prototype, modifications have been made based on user feedback. These changes
  include:
    - Underlining list headers to separate them from the list items.
    
    - Moving the "Save" button from the bottom of the screen to the upper
      corner to prevent the keyboard from covering them up.
      
    - Font size changes to make the back of the flash card easier to read.
    
    - Graphical positioning tweeks to make elements easier to target with
      your thumb.
 
- Bug fixes have been implemented since the prototype to fix crashes, fixing
  constraints on certain settings, and fixing element targeting issues.

This package includes all of the eclipse settings that should be necessary to
run the application, if imported correctly with the correct source folder settings
and the correct Android SDK installed.
