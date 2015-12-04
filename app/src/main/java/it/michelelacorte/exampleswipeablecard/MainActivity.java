package it.michelelacorte.exampleswipeablecard;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.michelelacorte.swipeablecard.AnimationCard;
import it.michelelacorte.swipeablecard.OptionView;
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

        OptionView.setOptionView(new OptionView.Builder()
                .image(R.drawable.image)
                .title("TITLE")
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                .build());



        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Single Card instance
        swipeableCard = (SwipeableCard) findViewById(R.id.swipeCard);


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
        */

    }
}

