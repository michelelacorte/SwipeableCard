#CUSTOMIZATION

![alt tag](http://s30.postimg.org/hkt4zmcht/Screenshot_2015_12_12_01_43_35.png)

Use SwipeableCard, and than you can customize card with `OptionViewAddional.Builder` !

```
        OptionView.setOptionView(new OptionView.Builder()
                .normalCard()
                //.image(R.drawable.image)
                .text("Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text," +
                        " a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text")
                .title("TITLE")
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                .setCardRadius(40) //Optional, default 4
                .setAdditionalItem(new OptionViewAdditional.Builder()
                        .setFabIcon(android.R.drawable.ic_dialog_info)
                        .setFabColor(R.color.colorPrimary)
                        .setOnClickListenerFab(clickFab)
                        //And other method!
                        .build())
                .build());
```
