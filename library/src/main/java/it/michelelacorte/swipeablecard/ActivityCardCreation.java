package it.michelelacorte.swipeablecard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardUtils;
import com.cooltechworks.creditcarddesign.CreditCardView;

/**
 * Created by Michele on 03/03/2016.
 */
public class ActivityCardCreation extends AppCompatActivity{
    private static CreditCardView creditCardView;
    private static Button newCreditCard;
    private static RelativeLayout relativeCreditCardCreation;
    private static RelativeLayout relativeCreditCard;

    public static void setCreditCardView(CreditCardView creditCard, OptionView optionView, Button newCreditCardLayout,
                                         RelativeLayout relativeCreditCardCreationLayout, RelativeLayout relativeCreditCardLayout)
    {
        newCreditCard = newCreditCardLayout;
        relativeCreditCardCreation = relativeCreditCardCreationLayout;
        relativeCreditCard = relativeCreditCardLayout;
        creditCardView = creditCard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(ActivityCardCreation.this, CardEditActivity.class);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {

            String cardHolderName = data.getStringExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
            String cardNumber = data.getStringExtra(CreditCardUtils.EXTRA_CARD_NUMBER);
            String expiry = data.getStringExtra(CreditCardUtils.EXTRA_CARD_EXPIRY);
            String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);

            creditCardView.setCardExpiry(expiry);
            creditCardView.setCardNumber(cardNumber);
            creditCardView.setCardHolderName(cardHolderName);
            creditCardView.setCVV(cvv);
            newCreditCard.setVisibility(View.GONE);
            creditCardView.setOnClickListener(new View.OnClickListener() {
                boolean back = false;

                @Override
                public void onClick(View v) {
                    if (!back) {
                        creditCardView.showBack();
                        back = true;
                    } else {
                        creditCardView.showFront();
                        back = false;
                    }
                }
            });
            relativeCreditCardCreation.setVisibility(View.GONE);
            relativeCreditCard.setVisibility(View.VISIBLE);
            this.finish();
        }else
        {
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}