#CREDIT CARD

![alt tag](http://i.giphy.com/3ornjQLD95Osp4qlm8.gif)

Example instance setted card:

```
        final OptionView creditCardSetted = new OptionView.Builder()
                .creditCard()
                .setCVV("233")
                .setCardHolderName("Lacorte Michele")
                .setCardNumber("5566784298446522")
                .setCardExpiry("01/20")
                .title("Credit Card")
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                .build();
```

Credit card creation:

```
        final OptionView creditCardCreation = new OptionView.Builder()
                .creditCard()
                .setCardCreation(MainActivity.this) //Pass your activity
                .title("Credit Card")
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                .build();
```
