package ru.kalievmars.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button buttonMinus, buttonPlus;
    private TextView textQuantity, textOrderPrice;
    private ImageView imageOrder;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> arrayAdapter;
    private HashMap<String, Integer> orders;

    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quantity = 0;

        buttonMinus = findViewById(R.id.button_minus);
        buttonPlus = findViewById(R.id.button_plus);
        textQuantity = findViewById(R.id.text_quantity);
        textOrderPrice = findViewById(R.id.text_order_price);
        imageOrder = findViewById(R.id.image_order);

        buttonMinus.setOnClickListener(changeOrderCountOnClickListener);
        buttonPlus.setOnClickListener(changeOrderCountOnClickListener);

        setSpinner();





    }

    private void setSpinner() {
        spinner = findViewById(R.id.spinner);
        orders = new HashMap();
        orders.put("Guitar", 1000);
        orders.put("Piano", 2000);
        orders.put("Drum", 500);

        arrayAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.addAll(orders.keySet());

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(selectedListener);
    }

    private double getOrderPrice() {
        return Integer.parseInt(orders.get(spinner.getSelectedItem()).toString()) * quantity;
    }

    private void setImageOrder(int orderId) {
        imageOrder.setImageResource(orderId);
    }

    View.OnClickListener changeOrderCountOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button_minus:
                    if(quantity == 0) {
                        break;
                    }
                    quantity--;
                    textQuantity.setText(String.valueOf(quantity));
                    textOrderPrice.setText(getOrderPrice() + " $");

                    break;
                case R.id.button_plus:
                    quantity++;
                    textQuantity.setText(String.valueOf(quantity));
                    textOrderPrice.setText(getOrderPrice() + " $");
                    break;
                default:
                    break; // TODO
            }
        }
    };

    AdapterView.OnItemSelectedListener selectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            textOrderPrice.setText(getOrderPrice() + " $");

            switch (position) {
                case 0:
                    setImageOrder(R.drawable.guitar);
                    break;
                case 1:
                    setImageOrder(R.drawable.piano);
                    break;
                case 2:
                    setImageOrder(R.drawable.drum);
                    break;
            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

}