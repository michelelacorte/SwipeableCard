package it.michelelacorte.swipeablecard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * SwipeableCardAdapter is a class for use SwipeableCard with RecyclerView
 * Created by Michele on 02/12/2015.
 */

/**
 * This is an Adapter for RecyclerView that is implemented for al user!
 * @author Michele Lacorte
 */
@SuppressWarnings({"deprecation", "ConstantConditions"})
public class SwipeableCardAdapter extends RecyclerView.Adapter<SwipeableCardAdapter.CardViewHolder> implements AnimationCard{

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView image;
        TextView text;
        TextView subTitle;
        Toolbar toolbar;
        /**
         * Customizable Icon and Text
         */
        ImageView iconBtn1;
        ImageView iconBtn2;
        ImageView iconBtn3;
        TextView textBtn1;
        TextView textBtn2;
        /**
         * Maps Variable
         */
        MapView mapView;
        TextView streetNameView;
        RelativeLayout relativeMaps;
        RelativeLayout relativeNormal;


        CardViewHolder(View itemView) {
            super(itemView);
            /*
            Set up recycler view custom object.
             */
            card = (CardView)itemView.findViewById(R.id.card);
            image = (ImageView) itemView.findViewById(it.michelelacorte.swipeablecard.R.id.image);
            text = (TextView) itemView.findViewById(it.michelelacorte.swipeablecard.R.id.text);
            subTitle = (TextView) itemView.findViewById(it.michelelacorte.swipeablecard.R.id.subTitle);
            toolbar = (Toolbar) itemView.findViewById(it.michelelacorte.swipeablecard.R.id.toolbar);
            mapView = (MapView) itemView.findViewById(R.id.mapLite);
            streetNameView = (TextView) itemView.findViewById(R.id.streetName);
            relativeNormal = (RelativeLayout) itemView.findViewById(R.id.relativeNormal);
            relativeMaps = (RelativeLayout) itemView.findViewById(R.id.relativeMaps);
            /**
             * Set-up customizable Icon and Text
             */
            iconBtn1 = (ImageView) itemView.findViewById(R.id.iconBtn1);
            iconBtn2 = (ImageView) itemView.findViewById(R.id.iconBtn2);
            iconBtn3 = (ImageView) itemView.findViewById(R.id.iconBtn3);
            textBtn1 = (TextView) itemView.findViewById(R.id.textBtn1);
            textBtn2 = (TextView) itemView.findViewById(R.id.textBtn2);
        }


    }
    GoogleMap mGoogleMap;
    LatLng mMapLocation;
    float mapsZoom;
    Context context;
    List<OptionView> optionsView;
    int height;
    int width;
    public SwipeableCardAdapter(List<OptionView> optionsView, Context context){
        this.optionsView = optionsView;
        this.context = context;
    }

    protected void updateMapContents(OptionView mOptionView) {
        mGoogleMap.clear();
        if(mOptionView.isMultipleMarker() && !mOptionView.isSingleMarker()) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            if(mOptionView.getLatLngArray() != null && mOptionView.getLatLngArray().length > 0) {
                if (mOptionView.getMarkerTitleArray() != null && mOptionView.getMarkerTitleArray().length > 0
                        && mOptionView.getLatLngArray().length == mOptionView.getMarkerTitleArray().length) {
                    if(mOptionView.getMarkerIconArray() != null && mOptionView.getMarkerIconArray().length > 0
                            && mOptionView.getLatLngArray().length == mOptionView.getMarkerIconArray().length)
                    {
                        for (int i = 0; i < mOptionView.getLatLngArray().length; i++) {
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIconArray()[i]);
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngArray()[i]).title(mOptionView.getMarkerTitleArray()[i]).icon(icon));
                            builder.include(mOptionView.getLatLngArray()[i]);
                        }
                    }else {
                        for (int i = 0; i < mOptionView.getLatLngArray().length; i++) {
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngArray()[i]).title(mOptionView.getMarkerTitleArray()[i]));
                            builder.include(mOptionView.getLatLngArray()[i]);
                        }
                    }
                } else {
                    if(mOptionView.getMarkerIconArray() != null && mOptionView.getMarkerIconArray().length > 0
                            && mOptionView.getLatLngArray().length == mOptionView.getMarkerIconArray().length)
                    {
                        for (int i = 0; i < mOptionView.getLatLngArray().length; i++) {
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIconArray()[i]);
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngArray()[i]).title(mOptionView.getMarkerTitleArray()[i]).icon(icon));
                            builder.include(mOptionView.getLatLngArray()[i]);
                        }
                    }else {
                        for (int i = 0; i < mOptionView.getLatLngArray().length; i++) {
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngArray()[i]));
                            builder.include(mOptionView.getLatLngArray()[i]);
                        }
                    }
                }
            }
            if(mOptionView.getLatLngList() != null && mOptionView.getLatLngList().size() > 0)
            {
                if(mOptionView.getMarkerTitleList() != null && mOptionView.getMarkerTitleList().size() > 0
                        && mOptionView.getMarkerTitleList().size() == mOptionView.getLatLngList().size())
                {
                    if(mOptionView.getMarkerIconList() != null && mOptionView.getMarkerIconList().size() > 0
                            && mOptionView.getMarkerIconList().size() == mOptionView.getLatLngList().size())
                    {
                        for (int i = 0; i < mOptionView.getLatLngList().size(); i++) {
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIconList().get(i));
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngList().get(i)).title(mOptionView.getMarkerTitleList().get(i)).icon(icon));
                            builder.include(mOptionView.getLatLngList().get(i));
                        }
                    }else {
                        for (int i = 0; i < mOptionView.getLatLngList().size(); i++) {
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngList().get(i)).title(mOptionView.getMarkerTitleList().get(i)));
                            builder.include(mOptionView.getLatLngList().get(i));
                        }
                    }
                }else{
                    if(mOptionView.getMarkerIconList() != null && mOptionView.getMarkerIconList().size() > 0
                            && mOptionView.getMarkerIconList().size() == mOptionView.getLatLngList().size())
                    {
                        for (int i = 0; i < mOptionView.getLatLngList().size(); i++) {
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIconList().get(i));
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngList().get(i)).icon(icon));
                            builder.include(mOptionView.getLatLngList().get(i));
                        }
                    }else {
                        for (int i = 0; i < mOptionView.getLatLngList().size(); i++) {
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngList().get(i)));
                            builder.include(mOptionView.getLatLngList().get(i));
                        }
                    }
                }
            }
            LatLngBounds bounds = builder.build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 0);
            mGoogleMap.moveCamera(cameraUpdate);
        }else{
            if(mOptionView.getMarkerTitle() != null) {
                if (mOptionView.getMarkerIcon() != 0) {
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIcon());
                    mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation).title(mOptionView.getMarkerTitle()).icon(icon));
                } else {
                    mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation).title(mOptionView.getMarkerTitle()));
                }
            }else{
                if (mOptionView.getMarkerIcon() != 0) {
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIcon());
                    mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation).icon(icon));
                } else {
                    mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation));
                }
            }
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mMapLocation, mapsZoom);
            mGoogleMap.moveCamera(cameraUpdate);
        }
    }

    public void setMapLocation(double lat, double lon, OptionView mOptionView) {
        mMapLocation = new LatLng(lat, lon);
        if (mGoogleMap != null) {
            updateMapContents(mOptionView);
        }
    }

    public void setMapLocation(OptionView mOptionView)
    {
        if (mGoogleMap != null) {
            updateMapContents(mOptionView);
        }
    }


    public String getStreetNameFromLatLong(double lat, double lon, Context context)
    {
        String streetName = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);

            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();
                for (int j = 0; j < returnedAddress.getMaxAddressLineIndex(); j++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(j)).append("");
                }
                streetName = strReturnedAddress.toString();
            }
        } catch (IOException e) {
            Log.e("SwipeableCardAdapter", "Error tryng to retrieve street name from lat long");
        }
        return streetName;
    }
    /**
     * Animation Card at start layout, please do not modify this.
     * @param card card view instance
     * @param toolbar toolbar instance
     */
    @Override
    public void animationCardStart(@NotNull final CardView card, @NotNull final Toolbar toolbar){
        new CountDownTimer(1, 1) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                card.animate()
                        .translationY(height - ((int) (toolbar.getHeight() * 1.7)))
                        .setDuration(1)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                card.setVisibility(View.VISIBLE);
                            }
                        }).start();
            }
        }.start();

    }

    /**
     * Animation Card for down animation, please do not modify this.
     * @param card card view instance
     * @param toolbar toolbar instance
     */
    @Override
    public void animationCardDown(@NotNull final CardView card, @NotNull final Toolbar toolbar, final long duration){
        new CountDownTimer(1, 1) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                card.animate()
                        .translationY(height - ((int)(toolbar.getHeight() * 1.7)))
                        .setDuration(duration).start();
            }
        }.start();
    }

    /**
     * Animation Card for up animation, please do not modify this.
     * @param card card view instance
     * @param toolbar toolbar instance
     */
    @Override
    public void animationCardUp(@NotNull final CardView card,  final Toolbar toolbar, final long duration){
        new CountDownTimer(1, 1) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                card.animate()
                        .translationY(height - (card.getHeight() + toolbar.getHeight()))
                        .setDuration(duration).start();
            }
        }.start();
    }


    /**
     * Get number of option view.
     * @return item count
     */
    @Override
    public int getItemCount() {
        return optionsView.size();
    }

    private Point getScreenSize(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return new Point(size.x, size.y);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Inflate layout on recycler view.
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_card, viewGroup, false);
        final CardViewHolder cvh = new CardViewHolder(v);

        //Get screen size.
        width = getScreenSize(context).x;
        height = getScreenSize(context).y;

        //Return view.
        return cvh;

    }

    @Override
    public void onBindViewHolder(final CardViewHolder cardViewHolder, int i) {
        /*
        Set option for all swipeable card on recycler view.
         */
        //Set Text
        final long duration = optionsView.get(i).getDuration();
        cardViewHolder.card.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
        cardViewHolder.card.setRadius(optionsView.get(i).getCardRadius());

        /**
         * Check if Maps Card is set (reset object before)
         */
        cardViewHolder.streetNameView.setVisibility(View.GONE);
        cardViewHolder.mapView.setVisibility(View.GONE);
        cardViewHolder.relativeNormal.setVisibility(View.GONE);
        cardViewHolder.relativeMaps.setVisibility(View.GONE);
        if(optionsView.get(i).isTYPE_CARD_MAPS()) {
            if((optionsView.get(i).getLatLngArray() != null && optionsView.get(i).getLatLngArray().length > 0) ||
                    (optionsView.get(i).getLatLngList() != null && optionsView.get(i).getLatLngList().size() > 0)
                            && optionsView.get(i).isMultipleMarker() && !optionsView.get(i).isSingleMarker())
            {
                cardViewHolder.relativeNormal.setVisibility(View.GONE);
                cardViewHolder.relativeMaps.setVisibility(View.VISIBLE);
                cardViewHolder.mapView.setVisibility(View.VISIBLE);
                cardViewHolder.text.setVisibility(View.GONE);
                cardViewHolder.image.setVisibility(View.GONE);
                cardViewHolder.mapView.onCreate(null);
                final int j = i;
                cardViewHolder.mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        mGoogleMap = googleMap;
                        MapsInitializer.initialize(context);
                        googleMap.getUiSettings().setMapToolbarEnabled(true);
                        if (mMapLocation != null && !optionsView.get(j).isMultipleMarker() && optionsView.get(j).isSingleMarker()) {
                            updateMapContents(optionsView.get(j));
                        }
                        if(optionsView.get(j).getLatLngArray() != null && optionsView.get(j).getLatLngArray().length > 0 && optionsView.get(j).isMultipleMarker() && !optionsView.get(j).isSingleMarker())
                        {
                            updateMapContents(optionsView.get(j));
                        }
                        if(optionsView.get(j).getLatLngList() != null && optionsView.get(j).getLatLngList().size() > 0 && optionsView.get(j).isMultipleMarker() && !optionsView.get(j).isSingleMarker())
                        {
                            updateMapContents(optionsView.get(j));
                        }
                    }
                });
                setMapLocation(optionsView.get(i));
            }else {
                mapsZoom = optionsView.get(i).getMapsZoom();
                cardViewHolder.relativeNormal.setVisibility(View.GONE);
                cardViewHolder.relativeMaps.setVisibility(View.VISIBLE);
                cardViewHolder.mapView.setVisibility(View.VISIBLE);
                cardViewHolder.text.setVisibility(View.GONE);
                cardViewHolder.image.setVisibility(View.GONE);
                cardViewHolder.mapView.onCreate(null);
                final int j = i;
                cardViewHolder.mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        mGoogleMap = googleMap;
                        MapsInitializer.initialize(context);
                        googleMap.getUiSettings().setMapToolbarEnabled(true);
                        if (mMapLocation != null && !optionsView.get(j).isMultipleMarker() && optionsView.get(j).isSingleMarker()) {
                            updateMapContents(optionsView.get(j));
                        }
                        if(optionsView.get(j).getLatLngArray() != null && optionsView.get(j).getLatLngArray().length > 0 && optionsView.get(j).isMultipleMarker() && !optionsView.get(j).isSingleMarker())
                        {
                            updateMapContents(optionsView.get(j));
                        }
                        if(optionsView.get(j).getLatLngList() != null && optionsView.get(j).getLatLngList().size() > 0 && optionsView.get(j).isMultipleMarker() && !optionsView.get(j).isSingleMarker())
                        {
                            updateMapContents(optionsView.get(j));
                        }
                    }
                });
                setMapLocation(optionsView.get(i).getLatitude(), optionsView.get(i).getLongitude(), optionsView.get(i));
                if(optionsView.get(i).isStreetName())
                {
                    cardViewHolder.streetNameView.setText(getStreetNameFromLatLong(optionsView.get(i).getLatitude(), optionsView.get(i).getLongitude(), context));
                    cardViewHolder.streetNameView.setVisibility(View.VISIBLE);
                }
            }
        } else {
            cardViewHolder.relativeNormal.setVisibility(View.VISIBLE);
            cardViewHolder.relativeMaps.setVisibility(View.GONE);
        }
        if(optionsView.get(i).isText())
        {
            cardViewHolder.image.setVisibility(View.GONE);
            cardViewHolder.text.setVisibility(View.VISIBLE);
            cardViewHolder.text.setText(optionsView.get(i).getText());
        }
        //Set Sub Title
        if(optionsView.get(i).isSubTitle())
        {
            cardViewHolder.subTitle.setVisibility(View.VISIBLE);
            cardViewHolder.subTitle.setText(optionsView.get(i).getSubTitle());
        }
        //Set Image
        if(optionsView.get(i).isImage())
        {
            cardViewHolder.text.setVisibility(View.GONE);
            cardViewHolder.image.setVisibility(View.VISIBLE);
            cardViewHolder.image.setImageResource(optionsView.get(i).getImage());
        }

        //Set toolbar
        cardViewHolder.toolbar.setTitle(optionsView.get(i).getTitle());

        //Check if has menu item
        if (optionsView.get(i).isMenuItem()) {
            cardViewHolder.toolbar.setOnMenuItemClickListener(optionsView.get(i).getToolbarListener());
            cardViewHolder.toolbar.inflateMenu(optionsView.get(i).getMenuItem());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cardViewHolder.toolbar.setTitleTextColor(ContextCompat.getColor(context, optionsView.get(i).getColorTitle()));
            cardViewHolder.toolbar.setBackgroundColor(ContextCompat.getColor(context, optionsView.get(i).getColorToolbar()));

        } else {
            cardViewHolder.toolbar.setTitleTextColor(context.getResources().getColor(optionsView.get(i).getColorTitle()));
            cardViewHolder.toolbar.setBackgroundColor(context.getResources().getColor(optionsView.get(i).getColorToolbar()));
        }

        /**
         * Reset OptionViewAdditional object
         */
        cardViewHolder.iconBtn1.setVisibility(View.GONE);
        cardViewHolder.iconBtn2.setVisibility(View.GONE);
        cardViewHolder.iconBtn3.setVisibility(View.GONE);
        cardViewHolder.textBtn1.setVisibility(View.GONE);
        cardViewHolder.textBtn2.setVisibility(View.GONE);
        if(optionsView.get(i).getOptionViewAdditional() != null) {
            /**
             * Check if Additional Icon Button 1
             */
            if (optionsView.get(i).getOptionViewAdditional().isIconBtn1()) {
                cardViewHolder.iconBtn1.setVisibility(View.VISIBLE);
                cardViewHolder.iconBtn1.setImageResource(optionsView.get(i).getOptionViewAdditional().getIconBtn1());
                if (optionsView.get(i).getOptionViewAdditional().getOnClickListenerIconBtn1() != null) {
                    cardViewHolder.iconBtn1.setOnClickListener(optionsView.get(i).getOptionViewAdditional().getOnClickListenerIconBtn1());
                }
            }
            /**
             * Check if Additional Icon Button 2
             */
            if (optionsView.get(i).getOptionViewAdditional().isIconBtn2()) {
                cardViewHolder.iconBtn2.setVisibility(View.VISIBLE);
                cardViewHolder.iconBtn2.setImageResource(optionsView.get(i).getOptionViewAdditional().getIconBtn2());
                if (optionsView.get(i).getOptionViewAdditional().getOnClickListenerIconBtn2() != null) {
                    cardViewHolder.iconBtn2.setOnClickListener(optionsView.get(i).getOptionViewAdditional().getOnClickListenerIconBtn2());
                }
            }
            /**
             * Check if Additional Icon Button 3
             */
            if (optionsView.get(i).getOptionViewAdditional().isIconBtn3()) {
                cardViewHolder.iconBtn3.setVisibility(View.VISIBLE);
                cardViewHolder.iconBtn3.setImageResource(optionsView.get(i).getOptionViewAdditional().getIconBtn3());
                if (optionsView.get(i).getOptionViewAdditional().getOnClickListenerIconBtn3() != null) {
                    cardViewHolder.iconBtn3.setOnClickListener(optionsView.get(i).getOptionViewAdditional().getOnClickListenerIconBtn3());
                }
            }
            /**
             * Check if Additional Text Button 1
             */
            if (optionsView.get(i).getOptionViewAdditional().isTextBtn1()) {
                cardViewHolder.textBtn1.setVisibility(View.VISIBLE);
                cardViewHolder.textBtn1.setText(optionsView.get(i).getOptionViewAdditional().getTextBtn1());
                cardViewHolder.textBtn1.setTextSize(optionsView.get(i).getOptionViewAdditional().getTextSizeBtn1());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ContextCompat.getColor(context, optionsView.get(i).getOptionViewAdditional().getTextColorBtn1());
                } else {
                    cardViewHolder.textBtn1.setTextColor(context.getResources().getColor(optionsView.get(i).getOptionViewAdditional().getTextColorBtn1()));
                }
                /**
                 * Check if OnClickListener Additional Text Button 1
                 */
                if (optionsView.get(i).getOptionViewAdditional().getOnClickListenerTextBtn1() != null) {
                    cardViewHolder.textBtn1.setOnClickListener(optionsView.get(i).getOptionViewAdditional().getOnClickListenerTextBtn1());
                }
            }
            /**
             * Check if Additional Text Button 2
             */
            if (optionsView.get(i).getOptionViewAdditional().isTextBtn2()) {
                cardViewHolder.textBtn2.setVisibility(View.VISIBLE);
                cardViewHolder.textBtn2.setText(optionsView.get(i).getOptionViewAdditional().getTextBtn2());
                cardViewHolder.textBtn2.setTextSize(optionsView.get(i).getOptionViewAdditional().getTextSizeBtn2());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cardViewHolder.textBtn2.setTextColor(ContextCompat.getColor(context, optionsView.get(i).getOptionViewAdditional().getTextColorBtn2()));
                } else {
                    cardViewHolder.textBtn2.setTextColor(context.getResources().getColor(optionsView.get(i).getOptionViewAdditional().getTextColorBtn2()));
                }
                /**
                 * Check if OnClickListener Additional Text Button 2
                 */
                if (OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerTextBtn2() != null) {
                    cardViewHolder.textBtn2.setOnClickListener(OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerTextBtn2());
                }
            }
        }

        //Start animation at end of creation of swipeable card
         animationCardStart(cardViewHolder.card, cardViewHolder.toolbar);

        //Set Listener for View and Card
        cardViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationCardDown(cardViewHolder.card, cardViewHolder.toolbar, duration);
            }
        });

        cardViewHolder.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationCardUp(cardViewHolder.card, cardViewHolder.toolbar, duration);
            }
        });
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

