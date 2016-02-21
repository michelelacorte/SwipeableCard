#MAPS CARD

![alt tag](http://s2.postimg.org/i7te06hqx/Screenshot_2016_02_20_21_44_02.png)

Example instance of single marker and multiple marker card

```
        final OptionView mapSwipeSingleMarker = new OptionView.Builder().mapsCard()
                .title("Maps Card")
                .setLocation(44.4937615, 11.3430542, "MyMarker")
                .setZoom(10f)
                .withStreetName(true)
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                .build();

        final OptionView mapSwipeMultipleMarker = new OptionView.Builder().mapsCard()
                .title("Maps Card")
                .setLocation(new LatLng(44.48, 11.33), new LatLng(43.45, 11.32), new LatLng(45.70, 10.11))
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                .build();
```

For full method and description see [documentation](http://michelelacorte.github.io/SwipeableCard/docs/javadoc/index.html)
