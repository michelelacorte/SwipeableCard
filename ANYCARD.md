#ANY CARD LAYOUT

Use SwipeableCard with any layout!!!

You just add to your layout a CardView and than use:

```
        CardView card = (CardView) findViewById(R.id.cardCustom);
        final CustomCardAnimation cardAnim = new CustomCardAnimation(getApplicationContext(), card, 200);

        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cardAnim.animationCustomCardUp();
            }
        });
        findViewById(android.R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardAnim.animationCustomCardDown();
            }
        });
```
