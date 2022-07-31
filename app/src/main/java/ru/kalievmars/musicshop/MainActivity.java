package ru.kalievmars.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button buttonMinus, buttonPlus, addToCart;
    private TextView textQuantity, textOrderPrice;
    private EditText editText;
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

        addToCart = findViewById(R.id.add_to_cart);
        editText = findViewById(R.id.edittext);

        buttonMinus.setOnClickListener(changeOrderCountOnClickListener);
        buttonPlus.setOnClickListener(changeOrderCountOnClickListener);

        addToCart.setOnClickListener(addToCartListener);

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

    View.OnClickListener addToCartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Order order = new Order(editText.getText().toString(),
                    spinner.getSelectedItem().toString(),
                    getOrderPrice(),
                    quantity);

            Log.d("orderLog", order.toString());

            Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
            orderIntent.putExtra("buyerName", order.getBuyerName());
            orderIntent.putExtra("orderName", order.getOrderName());
            orderIntent.putExtra("price", order.getPrice());
            orderIntent.putExtra("quantity", order.getQuantity());
            startActivity(orderIntent);
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