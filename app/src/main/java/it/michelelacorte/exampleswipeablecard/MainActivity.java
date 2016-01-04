package it.michelelacorte.exampleswipeablecard;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.michelelacorte.swipeablecard.AnimationCard;
import it.michelelacorte.swipeablecard.CustomCardAnimation;
import it.michelelacorte.swipeablecard.OptionView;
import it.michelelacorte.swipeablecard.OptionViewAdditional;
import it.michelelacorte.swipeablecard.SwipeableCard;
import it.michelelacorte.swipeablecard.SwipeableCardAdapter;

public class MainActivity extends AppCompatActivity{
    SwipeableCard swipeableCard;
    RecyclerView rv;
    LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Example of single Swipeable Card.
         */

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

        View.OnClickListener clickTextButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Text Button!", Toast.LENGTH_LONG).show();
            }
        };

        View.OnClickListener clickIconButton1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Icon Button 1!", Toast.LENGTH_LONG).show();
            }
        };

        View.OnClickListener clickIconButton2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Icon Button 2!", Toast.LENGTH_LONG).show();
            }
        };

        View.OnClickListener clickFab = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Fab Button!", Toast.LENGTH_LONG).show();
            }
        };

        OptionView.setOptionView(new OptionView.Builder()
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
                        .build())

                /*
                .setAdditionalItem(new OptionViewAdditional.Builder()
                        .iconButton(android.R.drawable.ic_menu_camera,
                                android.R.drawable.ic_menu_call)
                                //    android.R.drawable.ic_menu_delete)
                        .textButton("SHARE")
                        .textColorButton(R.color.colorPrimary)
                        .textSize(15f)
                        .setOnClickListenerTextButton(clickTextButton)
                        .setOnClickListenerIconButton(clickIconButton1, clickIconButton2)
                        .build())
                */
                .build());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Single Card instance
        swipeableCard = (SwipeableCard) findViewById(R.id.swipeCard);




        /**
         * Custom Card View Example
         */

        /*
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

*/

        /**
         * Example of Recycler View Swipeable Card.
         */

/*
        rv = (RecyclerView) findViewById(R.id.rv);

        //Set layout HORIZONTAL
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
                .toolbarListener(toolbarListener)
                .setAdditionalItem(new OptionViewAdditional.Builder()
                        .iconButton(android.R.drawable.ic_menu_camera,
                                android.R.drawable.ic_menu_call)
                        .textButton("SHARE")
                        .textColorButton(R.color.colorPrimary)
                        .textSize(15f)
                        .build())
                .build());
        optionViews.add(new OptionView.Builder()
                .text("Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text," +
                                " a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text")
                .title("TITLE")
                .colorTitle(R.color.colorPrimary)
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                .setCardRadius(40) //Optional, default 4
                .build());
        optionViews.add(new OptionView.Builder()
                .subTitle("Sub Title!!!")
                .image(R.drawable.image)
                .title("TITLE")
                .colorTitle(R.color.colorPrimary)
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                .setAdditionalItem(new OptionViewAdditional.Builder()
                        .iconButton(android.R.drawable.ic_menu_camera,
                                android.R.drawable.ic_menu_call,
                                android.R.drawable.ic_menu_delete)
                        .build())
                .build());

        //Set custom adapter.
        SwipeableCardAdapter adapter = new SwipeableCardAdapter(optionViews, getApplicationContext());
            rv.setAdapter(adapter);

*/
    }

}

