package com.veames.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";
    private static final String EXTRA_DRINK = "drink";
    private static final String EXTRA_DRINK_TYPE = "drinkType";
    private static final String EXTRA_ADDITIVES = "additives";

    private TextView textViewReceivedOrder;
    private TextView textViewDrink;
    private TextView textViewSelectedDrinkType;
    private TextView textViewSelectedAdditives;

    private String userName, drink, drinkType;
    private ArrayList<String> additives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        setupTextViews();
    }

    public void initViews() {
        textViewReceivedOrder = findViewById(R.id.textViewReceivedOrder);
        textViewDrink = findViewById(R.id.textViewDrink);
        textViewSelectedDrinkType = findViewById(R.id.textViewSelectedDrinkType);
        textViewSelectedAdditives = findViewById(R.id.textViewSelectedAdditives);
    }

    private void setupTextViews() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        textViewReceivedOrder.setText(getString(R.string.tw_order_received, userName));

        drink = getIntent().getStringExtra(EXTRA_DRINK);
        textViewDrink.setText(drink);

        drinkType = getIntent().getStringExtra(EXTRA_DRINK_TYPE);
        textViewSelectedDrinkType.setText(drinkType);

        additives = getIntent().getStringArrayListExtra(EXTRA_ADDITIVES);
        String additivesText = "";
        if (additives != null) {
            additivesText = TextUtils.join(", ", additives);
            textViewSelectedAdditives.setText(additivesText);
        }

    }

    public static Intent newIntent(Context context,
                                   String userName,
                                   String drink,
                                   String drinkType,
                                   ArrayList<String> additives) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        intent.putStringArrayListExtra(EXTRA_ADDITIVES, additives);
        return intent;
    }


}