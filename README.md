# Color Spree HD: connect gems

[![Build Status](https://travis-ci.org/dawidkotarba/ColorSpreeHD.png)](https://travis-ci.org/dawidkotarba/ColorSpreeHD)

Game can be downloaded here:
https://github.com/dawidkotarba/ColorSpreeHD/raw/master/apk/ColorSpreeHD.apk

##### Key tech features:
- integration with Google Play services for high scores submission
- usage of Google Analytics
- availability to show banner and/or interstitial ads
- intensive usage of particle effects
- assets buffering and caching
- implemented light effects
- stop motion animation

##### Still to do:
- unit testing
- javadoc

##### Description from Google Play:

![Screen](https://raw.githubusercontent.com/dawidkotarba/ColorSpreeHD/master/documentation/readme/screen1.png)
![Screen](https://raw.githubusercontent.com/dawidkotarba/ColorSpreeHD/master/documentation/readme/screen9.png)
![Screen](https://raw.githubusercontent.com/dawidkotarba/ColorSpreeHD/master/documentation/readme/screen7.png)
![Screen](https://raw.githubusercontent.com/dawidkotarba/ColorSpreeHD/master/documentation/readme/screen8.png)
![Screen](https://raw.githubusercontent.com/dawidkotarba/ColorSpreeHD/master/documentation/readme/screen2.png)
![Screen](https://raw.githubusercontent.com/dawidkotarba/ColorSpreeHD/master/documentation/readme/screen10.png)

Connect as many gems of the same color as possible! The more tiles you connect, the better score you get. Sounds easy?:) You have only 2 minutes! But do not worry - you can get an extra time easily by linking multiple gems together.
Take advantage of Dynamite Tile or Bomb Tile - they activate gems around.
Use extra gems to multiply points by 2. Not enough? Star Tile can make it 3!
You can get them as a reward for selecting multiple blocks. The more you select, the better reward you get :)

Something went wrong? Use second finger to cancel the selection or just swipe to last selected gem.

Good luck, have fun:)

##### Supported resolutions:
Game supports HD resolution. You can play both on smartphones and tablets.

##### Tips:

Function that calculates points:
x = linked blocks
points = x*x-x

Function that calculates extra seconds:
points / 12, max 20 second per move

##### Blocks available:
- red / extra red (points x2)
- blue / extra blue (points x2)
- yellow / extra yelow (points x2)
- green / extra green (points x2)
- violet / extra violet (points x2)
- extra cyan (points x2)
- Star (points x3, neutral color)
- Red Dynamite (destroys upper, lower, left and right blocks, neutral color)
- Blue Dynamite (destroys blocks diagonally, neutral color)
- Green Dynamite (destroys one random surrounding block, neutral color)
- Bomb (destroys all blocks around, neutral color)
- Thunder (destroys 2 random blocks, neutral color)
- Multicolor (works for every color)

##### Other:
In order to improve the game, this app uses Google Analytics for gathering anonymous usage statistics.
You can turn it off in options menu.

##### Links:
- Travis: https://travis-ci.org/dawidkotarba/ColorSpreeHD
