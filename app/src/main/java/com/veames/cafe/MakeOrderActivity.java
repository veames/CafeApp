package com.veames.cafe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";
    private TextView textViewGreetings;
    private RadioGroup radioGroupDrinks;
    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffee;
    private TextView textViewAdditives;
    private CheckBox checkBoxSugarAdditive;
    private CheckBox checkBoxMilkAdditive;
    private CheckBox checkBoxLemonAdditive;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private Button buttonMakeOrder;

    private String userName, drink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_make_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        setupUserName();
        radioGroupDrinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if (id == radioButtonTea.getId()) {
                    onUserChoseTea();
                } else if (id == radioButtonCoffee.getId()) {
                    onUserChoseCoffee();
                }
            }
        });
        radioButtonTea.setChecked(true);
        buttonMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> additives = new ArrayList<>();
                if (checkBoxSugarAdditive.isChecked()) {
                    additives.add(checkBoxSugarAdditive.getText().toString());
                }
                if (checkBoxMilkAdditive.isChecked()) {
                    additives.add(checkBoxMilkAdditive.getText().toString());
                }
                if (radioButtonTea.isChecked() && checkBoxLemonAdditive.isChecked()) {
                    additives.add(checkBoxLemonAdditive.getText().toString());
                }
                String drinkType = "";
                if (radioButtonTea.isChecked()) {
                    drinkType = spinnerTea.getSelectedItem().toString();
                } else if (radioButtonCoffee.isChecked()) {
                    drinkType = spinnerCoffee.getSelectedItem().toString();
                }
                launchNextScreen(userName,
                        drink,
                        drinkType,
                        additives);
            }
        });
    }

    private void initViews() {
        textViewGreetings = findViewById(R.id.textViewGreetings);
        radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee);
        textViewAdditives = findViewById(R.id.textViewAdditives);
        checkBoxSugarAdditive = findViewById(R.id.checkBoxSugarAdditive);
        checkBoxMilkAdditive = findViewById(R.id.checkBoxMilkAdditive);
        checkBoxLemonAdditive = findViewById(R.id.checkBoxLemonAdditive);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        buttonMakeOrder = findViewById(R.id.buttonMakeOrder);
    }


    private void onUserChoseTea() {
        drink = getString(R.string.rb_tea);
        setupDrinkType(drink);
        checkBoxLemonAdditive.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
    }

    private void onUserChoseCoffee() {
        drink = getString(R.string.rb_coffee);
        setupDrinkType(drink);
        checkBoxLemonAdditive.setVisibility(View.INVISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
    }

    private void setupUserName() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        textViewGreetings.setText(getString(R.string.tw_greetings, userName));
    }

    private void setupDrinkType(String drinkType) {
        String result = getString(R.string.tw_additives, drinkType);
        textViewAdditives.setText(result);
    }

    private void launchNextScreen(String userName,
                                  String drink,
                                  String drinkType,
                                  ArrayList<String> additives) {
        Intent intent = OrderDetailActivity.newIntent(this,
                userName,
                drink,
                drinkType,
                additives);
        startActivity(intent);
    }

    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }

}