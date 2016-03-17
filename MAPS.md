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

Remember to add this permission on Manifest.xml

```
    <permission android:name="com.yourPackage.MAPS_RECEIVE"
        android:protectionLevel="signature"/>
    <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES" />
    <uses-permission android:name="com.yourPackage.MAPS_RECEIVE"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
    
And Google Maps API Key:

```
        <meta-data
            android:name="com.google.android.maps.v2.API_KEY"
            android:value=Your API key" />
        <meta-data
            android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />
```
