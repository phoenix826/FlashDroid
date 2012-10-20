FlashDroid
Team Ux
    - William Wenzel
    - Matthew Addy
    - Edward Dinki
    - Shaun Devos

FlashDroid is a flash card study application for the Android platform. This
application was developed and designed for use with Android SDK 4.1.2, API 16.
The current state of the prototype is as follows:

- Full create, update, and delete operations on decks and cards. Cards and
  decks are currently stored to a database on the Android device, and are
  saved between sessions.
  
- Partial implementation of a timed study activity; the current
  prototype allows for a study session to be fully completed with
  the selected time (set in the settings menu), but does not take into
  account all of the settings.
  
- Statistics are tracked and are stored for the current session of the
  application (restarting the application will clear this data).
  
- Settings are currently implemented, but only stored for the current
  session of the application (as with statistics). While all settings
  are represented in the UI, only the current study time is impactful on
  the study activity.
  
- Almost all of the UI considerations have been made, with the exception of
  a few minor tweeks that are planned. Overall the UI is a good representation
  of what we intend to have for the final product.

This package includes all of the eclipse settings that should be necessary to
run the application, if imported correctly with the correct source folder settings
and the correct Android SDK installed.