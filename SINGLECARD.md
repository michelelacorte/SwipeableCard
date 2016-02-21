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
                .normalCard()
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

##MULTIPLE INSTANCE

```
        final OptionView customSwipe = new OptionView.Builder()
                .normalCard()
                .image(R.drawable.image)
                .title("Custom Card")
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                .setAdditionalItem(new OptionViewAdditional.Builder()
                        .iconButton(android.R.drawable.ic_menu_camera,
                                android.R.drawable.ic_menu_call)
                        .textButton("SHARE")
                        .textColorButton(R.color.colorPrimary)
                        .textSize(15f)
                        .setOnClickListenerTextButton(clickTextButton)
                        .setOnClickListenerIconButton(clickIconButton1, clickIconButton2)
                        .build())
                .build();

        final OptionView dismissableSwipe = new OptionView.Builder()
                .normalCard()
                .image(R.drawable.image)
                .title("Dismissable Card")
                .menuItem(R.menu.menu_main)
                .setSwipeToDismiss(true)
                .toolbarListener(toolbarListener)
                .setAdditionalItem(new OptionViewAdditional.Builder()
                        .textButton("SHARE", "LEARN MORE")
                        .textColorButton(R.color.colorPrimary)
                        .textSize(15f)
                        .setOnClickListenerTextButton(clickTextButton, clickTextButton)
                        .build())
                .build();
                
      swipeableCard = (SwipeableCard) findViewById(R.id.swipeCard);
      //Than you can init your Swipeable Card with
      swipeableCard.init(getApplicationContext(), singleSwipe);
      //Or
      swipeableCard.init(getApplicationContext(), customSwipe);
```
