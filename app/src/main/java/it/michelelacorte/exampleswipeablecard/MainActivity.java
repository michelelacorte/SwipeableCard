package it.michelelacorte.exampleswipeablecard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.michelelacorte.swipeablecard.CustomCardAnimation;
import it.michelelacorte.swipeablecard.OptionView;
import it.michelelacorte.swipeablecard.OptionViewAdditional;
import it.michelelacorte.swipeablecard.SwipeableCard;
import it.michelelacorte.swipeablecard.SwipeableCardAdapter;

public class MainActivity extends AppCompatActivity {
    SwipeableCard swipeableCard;
    RecyclerView rv;
    LinearLayoutManager llm;
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout drawerLayout;
    CardView cardOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Example of single Swipeable Card.
         */

        final Toolbar.OnMenuItemClickListener toolbarListener = new Toolbar.OnMenuItemClickListener() {
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

        final View.OnClickListener clickFab = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Fab Button!", Toast.LENGTH_LONG).show();
            }
        };

        final OptionView singleSwipe = new OptionView.Builder()
                .text("Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text," +
                        " a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text, a lot of Text")
                .title("Single Card")
                .menuItem(R.menu.menu_main)
                .toolbarListener(toolbarListener)
                .setAdditionalItem(new OptionViewAdditional.Builder()
                        .setFabIcon(android.R.drawable.ic_dialog_info)
                        .setFabColor(R.color.colorPrimary)
                        .setOnClickListenerFab(clickFab)
                        .build())
                .build();

        final OptionView customSwipe = new OptionView.Builder()
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

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cardOther = (CardView) findViewById(R.id.cardOther);
        swipeableCard = (SwipeableCard) findViewById(R.id.swipeCard);
        rv = (RecyclerView) findViewById(R.id.rv);


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.SingleSwipe:
                        /**
                         * Example of Single Swipeable Card.
                         */
                        rv.setVisibility(View.GONE);
                        cardOther.setVisibility(View.GONE);
                        swipeableCard.init(getApplicationContext(), singleSwipe);
                        swipeableCard.setVisibility(View.VISIBLE);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.OtherSwipe:
                        /**
                         * Example of Other Card Layout.
                         */
                        rv.setVisibility(View.GONE);
                        swipeableCard.setVisibility(View.INVISIBLE);
                        cardOther.setVisibility(View.VISIBLE);
                        final CustomCardAnimation cardAnim = new CustomCardAnimation(getApplicationContext(), cardOther, 200);
                        cardOther.setOnClickListener(new View.OnClickListener() {
                            boolean swipeUp = false;

                            @Override
                            public void onClick(View v) {
                                if (!swipeUp) {
                                    cardAnim.animationCustomCardUp();
                                    swipeUp = true;
                                } else {
                                    cardAnim.animationCustomCardDown();
                                    swipeUp = false;
                                }
                            }
                        });
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.CustomSwipe:
                        /**
                         * Custom Card View Example
                         */
                        cardOther.setVisibility(View.GONE);
                        rv.setVisibility(View.GONE);
                        swipeableCard.init(getApplicationContext(), customSwipe);
                        swipeableCard.setVisibility(View.VISIBLE);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.DismissSwipe:
                        /**
                         * Example of Dismissable Swipeable Card.
                         */
                        cardOther.setVisibility(View.GONE);
                        rv.setVisibility(View.GONE);
                        swipeableCard.init(getApplicationContext(), dismissableSwipe);
                        swipeableCard.setVisibility(View.VISIBLE);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.RecyclerSwipe:
                        /**
                         * Example of Recycler View Swipeable Card.
                         */
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
                        swipeableCard.setVisibility(View.GONE);
                        cardOther.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.About:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogCustom);
                        builder.setTitle("Swipeable Card Library")
                                .setMessage(R.string.dialog_message)
                                .setCancelable(false)
                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });

                        AlertDialog about = builder.create();
                        about.show();
                        ((TextView) about.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
                        break;
                    case R.id.Donate:
                        AlertDialog.Builder donate = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogCustom);
                        donate.setTitle("Donate")
                                .setMessage(R.string.donate_message)
                                .setCancelable(false)
                                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });

                        AlertDialog donation = donate.create();
                        donation.show();
                        ((TextView) donation.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
                        break;
                    case R.id.Share:
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.putExtra(Intent.EXTRA_TEXT, "Hello!\nSee SwipeableCardLibrary at https://github.com/michelelacorte/SwipeableCard");
                        startActivity(Intent.createChooser(share, "Swipeable Card Library"));
                        break;
                }
                return false;
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }
}

