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
