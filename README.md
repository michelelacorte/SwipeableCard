# SwipeableCard
##A simple implementation of swipe card like StreetView!!

<span class="badge-paypal"><a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&amp;hosted_button_id=LY7EX8WMWPWV6" title="Donate to this project using Paypal"><img src="https://img.shields.io/badge/paypal-donate-yellow.svg" alt="PayPal donate button" /></a></span>

[![API](https://img.shields.io/badge/API-14%2B-yellow.svg?style=flat)](https://android-arsenal.com/api?level=14)

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SwipeableCard-green.svg?style=true)](https://android-arsenal.com/details/1/2880)

[![alt tag](http://www.android-gems.com/badge/michelelacorte/SwipeableCard.svg)](http://www.android-gems.com/lib/michelelacorte/SwipeableCard?lib_id=753)

[![Awesome](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://github.com/sindresorhus/awesome)

####New Floating Action Button!!!

![alt tag](http://i.giphy.com/3o8doUXxjOCwHw2GEo.gif)

![alt tag](http://i.giphy.com/26tP83JrpN9mpN5wA.gif)

![alt tag](http://i.giphy.com/d2Za0uOe8fPYa38Q.gif)

####Possibility to use SwipeableCard in RecyclerView!!!

![alt tag](http://i.giphy.com/3o8doQxv28CVTGdM6Q.gif)


##USAGE

Swipeable Card is pushed to JCenter, so you just need to add the following dependency to your `build.gradle`.
```
compile 'it.michelelacorte.swipeablecard:library:2.0.0'
```

In alternative you can use AAR repository with:

```
allprojects {
    repositories {
        maven { url "https://dl.bintray.com/michelelacorte/maven/" }
        jcenter()
        mavenCentral()

    }
}
```

And add this dependecies

```
compile 'it.michelelacorte.swipeablecard:library:2.0.0@aar'
```

##DOCUMENTATION

- [Swipeable Single Card Example](https://github.com/michelelacorte/SwipeableCard/blob/master/SINGLECARD.md)

- [Swipeable RecyclerView Card Example](https://github.com/michelelacorte/SwipeableCard/blob/master/RECYCLERVIEW.md)

- [Customization Card Example](https://github.com/michelelacorte/SwipeableCard/blob/master/CUSTOMIZATION.md)

- [Any Card Layout Example](https://github.com/michelelacorte/SwipeableCard/blob/master/ANYCARD.md)

##SYSTEM REQUIREMENT

Android API 14+

##CHANGELOG

**v2.0.0**
- Support all custom `CardView` Layout
- `Fab` button added
- Fixed minor bug  for compatibility with API 14+
- Added three `ImageView` button (see Customizable)
- Added two `TextView` (see Customizable)
- Added method `setCardRadius(int radius)` default 4

Preview:

![alt tag](http://s30.postimg.org/hkt4zmcht/Screenshot_2015_12_12_01_43_35.png)

**v1.0.1**
- Support API 14+
- Update library and gradle

**v1.0.0**
- Support API 21+
- Added class `SwipeableCard.java` for setUp view of Swipeable Card.
- Added class `OptionView.java` that contains setter for set-up card with your own options.
- Added class `SwipeableCardAdapter.java` an adapter ready to use the Swipeable Card in RecyclerView, its constructor accepts `List<OptionView>` for each optionsView of card and `Context`.
- Added interface `AnimationCard` with abstract method for animation (for completeness only).
- Added method `setOptionView(OptionView optionViews)` called by OptionView class for set-up card with your own options.
- Added example App.

Method called by `OptionView.getOptionView()`
- Added method `getDuration()`
- Added method `getTitle()`
- Added method `getColorTitle()`
- Added method `getMenuItem()`
- Added method `getImage()`
- Added method `getSubTitle()`
- Added method `getColorToolbar()`
- Added method `getText()`
- Added method `getToolbarListener()`
- Added method `isSubTitle()` check if Sub Title is set
- Added method `isMenuItem()` check if menuItem is set
- Added method `isImage()` check if Image is set
- Added method `isText()` check if Text is set

Method called by `OptionView.Builder()`

- Added method `setDuration(long duration)` default is 500 millis
- Added method `toolbarListener(Toolbar.OnMenuItemClickListener toolbarListener)` for set-up the menù item on click
- Added method `title(String title)` default is empty
- Added method `colorTitle(int color)` default is black
- Added method `menuItem(int menuItem)` default there isn't a menu
- Added method `image(int image)` for set drawable image
- Added method `subTitle(String subTitle)` default isn't set
- Added method `toolbarColor(int color)` default is `transparent`
- Added method `text(String text)`for set custom text
- Added method `build()` for build swipeable card with custom configuration

##CREDITS

Author: Michele Lacorte (micky1995g@gmail.com)

##LICENSE

```
Copyright 2015 Michele Lacorte

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
