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
