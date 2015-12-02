# SwipeableCard
##A simple implementation of swipe card like StreetView!!
#COMING SOON!!!


![alt tag](http://i.giphy.com/26tP83JrpN9mpN5wA.gif)

![alt tag](http://i.giphy.com/d2Za0uOe8fPYa38Q.gif)

####Possibility to use SwipeableCard in RecyclerView!!!

![alt tag](http://i.giphy.com/3o8doQxv28CVTGdM6Q.gif)


##USAGE

Swipeable Card is pushed to JCenter, so you just need to add the following dependency to your `build.gradle`.
```
Coming soon!
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
Coming soon!
```
In your layout.xml

```
<it.michelelacorte.swipeablecard.SwipeableCard
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:id="@+id/swipeCard">
</it.michelelacorte.swipeablecard.SwipeableCard>
```

In your `MainActivity.java`

```
swipeableCard = (SwipeableCard) findViewById(R.id.swipeCard);
swipeableCard.animationCardStart();
```

##SYSTEM REQUIREMENT

Android API 21+

##CHANGELOG

**v1.0.0**
- Support API 21+
- Added class `OptionView.java` for set-up card with your own options
- Added method `setMenuClickListener(Toolbar.OnMenuItemClickListener menuListener)` for set-up the menù item on click

Method called by `OptionView`

- Added method `setTitle(String title)` default is empty
- Added method `getTitle()`
- Added method `setColorTitle(int color)` default is black
- Added method `getColorTitle`
- Added method `setMenuItem(int menuItem)` default there isn't a menu
- Added method `getMenuItem`
- Added method `setImage(int image)` for set drawable image
- Added method `getImage`
- Added method `setSubTitle(String subTitle)` default isn't set
- Added method `getSubTitle`
- Added method `setColorToolbar(int color)` default is `transparent`
- Added method `getColorToolbar`
- Added method `setText(String text)`for set custom text
- Added method `getText`

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
