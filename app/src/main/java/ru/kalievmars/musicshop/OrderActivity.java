package ru.kalievmars.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    TextView name, order, price, quantity, generalPrice, editTextEmail;
    Button sendToEmailBtn;
    Order newOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initViews();

        Intent orderIntent = getIntent();
        newOrder = new Order(orderIntent.getStringExtra("buyerName"),
                orderIntent.getStringExtra("orderName"),
                orderIntent.getDoubleExtra("price", 0),
                orderIntent.getIntExtra("quantity", 0));



        name.setText(newOrder.getBuyerName());
        order.setText(newOrder.getOrderName());
        price.setText(String.valueOf(newOrder.getOrderPrice()));
        quantity.setText(String.valueOf(newOrder.getQuantity()));
       generalPrice.setText(String.valueOf(newOrder.getPrice()));


       sendToEmailBtn.setOnClickListener(sendToEmailListener);


    }

    View.OnClickListener sendToEmailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(editTextEmail.getText().toString().equals("")) {
                Toast.makeText(OrderActivity.this, "Please write your email address", Toast.LENGTH_SHORT).show();
                return;
            }
            String[] emails = {editTextEmail.getText().toString()};
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, emails);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your order from Music Shop");
            emailIntent.putExtra(Intent.EXTRA_TEXT, newOrder.toString());
//            Toast.makeText(OrderActivity.this, "Sending email...", Toast.LENGTH_SHORT).show();

            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
                Toast.makeText(OrderActivity.this, "Sending email...", Toast.LENGTH_SHORT).show();

            }

        }
    };


    private void initViews() {
        name = findViewById(R.id.name);
        order = findViewById(R.id.order);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        generalPrice = findViewById(R.id.general_order);
        sendToEmailBtn = findViewById(R.id.send_to_email_btn);
        editTextEmail = findViewById(R.id.edittext_email);
    }
}