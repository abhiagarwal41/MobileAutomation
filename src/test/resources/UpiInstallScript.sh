#!/bin/sh

#  UpiInstallScript.sh
#  
#
#  Created by Prakhar on 07/09/18.
#  
cd ~/upitestapplication/
./gradlew clean
./gradlew packageDebug
~/Library/Android/sdk/platform-tools/adb install -g ./app/build/outputs/apk/app-debug.apk
#appium
