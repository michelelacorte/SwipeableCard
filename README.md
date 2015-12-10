# SwipeableCard
##A simple implementation of swipe card like StreetView!!

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SwipeableCard-green.svg?style=true)](https://android-arsenal.com/details/1/2880)

[![alt tag](http://www.android-gems.com/badge/michelelacorte/SwipeableCard.svg)](http://www.android-gems.com/lib/michelelacorte/SwipeableCard?lib_id=753)

![alt tag](http://i.giphy.com/26tP83JrpN9mpN5wA.gif)

![alt tag](http://i.giphy.com/d2Za0uOe8fPYa38Q.gif)

####Possibility to use SwipeableCard in RecyclerView!!!

![alt tag](http://i.giphy.com/3o8doQxv28CVTGdM6Q.gif)


##USAGE

Swipeable Card is pushed to JCenter, so you just need to add the following dependency to your `build.gradle`.
```
compile 'it.michelelacorte.swipeablecard:library:1.0.1'
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
compile 'it.michelelacorte.swipeablecard:library:1.0.1@aar'
```

#SINGLE CARD

In your Layout.xml

```
<it.michelelacorte.swipeablecard.SwipeableCard
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:id="@+id/swipeCard">
</it.michelelacorte.swipeablecard.SwipeableCard>
```

In your `MainActivity.java` before `setContentView()`

```
//Just an example
        Toolbar.OnMenuItemClickListener toolbarListener = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_settings) {
                    Toast.makeText(getApplicationContext(), "Settings Menu!", Toast.LENGTH_LONG).show();
                    return true;
                }

                return false;
            }
        };

        OptionView.setOptionView(new OptionView.Builder()
                .image(R.drawable.image)
                .title("TITLE")
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                //And all you want!
                .build());
```
Than under `setContentView()` just add card to view!

```
SwipeableCard swipeableCard = (SwipeableCard) findViewById(R.id.swipeCard);
```

#RECYCLER VIEW

In your Layout.xml add `RecyclerView`

```
<android.support.v7.widget.RecyclerView
    android:id="@+id/rv"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:cacheColorHint="@android:color/transparent" />
```

In your `MainActivity.java`

This as private variable

```
RecyclerView rv;
LinearLayoutManager llm;
```

Than under `setContentView()`

```
//Just an example

        rv = (RecyclerView) findViewById(R.id.rv);
        llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(llm);

        List<OptionView> optionViews = new ArrayList<>();
        Toolbar.OnMenuItemClickListener toolbarListener = new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_settings) {
                    Toast.makeText(getApplicationContext(), "Settings Menu!", Toast.LENGTH_LONG).show();
                    return true;
                }

                return false;
            }
        };
        optionViews.add(new OptionView.Builder()
                .image(R.drawable.image)
                .title("TITLE")
                .colorTitle(R.color.colorPrimary)
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener).build());
        optionViews.add(new OptionView.Builder()
                .text("Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text," +
                                " a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text")
                .title("TITLE")
                .colorTitle(R.color.colorPrimary)
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener).build());
        optionViews.add(new OptionView.Builder()
                .subTitle("Sub Title!!!")
                .image(R.drawable.image)
                .title("TITLE")
                .colorTitle(R.color.colorPrimary)
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener).build());

        //Set custom adapter.
        SwipeableCardAdapter adapter = new SwipeableCardAdapter(optionViews, getApplicationContext());
            rv.setAdapter(adapter);
```

##SYSTEM REQUIREMENT

Android API 14+

##CHANGELOG

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
