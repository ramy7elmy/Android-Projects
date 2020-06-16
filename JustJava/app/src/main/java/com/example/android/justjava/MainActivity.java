package com.example.android.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // Quantity amount
    int quantity = 1;
    // Unit price
    int price = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Gets the name.
        EditText editTextName = findViewById(R.id.nameEditText);
        String requesterName = editTextName.getText().toString();
        // Checks the whipped cream.
        CheckBox checkBoxWhippedCream = findViewById(R.id.whippedCream);
        boolean whippedCreamStatus = checkBoxWhippedCream.isChecked();
        // Checks the chocolate.
        CheckBox checkBoxChocolate = findViewById(R.id.chocolate);
        boolean chocolateStatus = checkBoxChocolate.isChecked();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.our_email)});
        intent.putExtra(Intent.EXTRA_SUBJECT, requesterName + " " + getString(R.string._order));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(requesterName, whippedCreamStatus, chocolateStatus));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method creates order summary.
     *
     * @param requesterName      the name of order creator.
     * @param whippedCreamStatus adds whipped cream or not.
     * @param chocolateStatus    adds chocolate or not.
     * @return order summary.
     */
    private String createOrderSummary(String requesterName, boolean whippedCreamStatus, boolean chocolateStatus) {
        return getString(R.string.Name_is) + " " + requesterName +
                "\n" + getString(R.string.addCream) + " " + whippedCreamStatus +
                "\n" + getString(R.string.addChocolate) + " " + chocolateStatus +
                "\n" + getString(R.string.quantity_is) + " " + quantity +
                "\n" + getString(R.string.total_is) + " " + NumberFormat.getCurrencyInstance().format(calculatePrice(whippedCreamStatus, chocolateStatus)) +
                "\n" + getString(R.string.thankYou);
    }

    /**
     * Calculates the price of the order.
     *
     * @param needCream     determine cream need.
     * @param needChocolate determine chocolate need.
     * @return order total price.
     */
    private int calculatePrice(boolean needCream, boolean needChocolate) {
        int totalPrice = price;
        if (needCream)
            totalPrice = totalPrice + 1;
        if (needChocolate)
            totalPrice = totalPrice + 2;
        return quantity * totalPrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param quantity is the number of units ordered.
     */
    @SuppressLint("SetTextI18n")
    private void displayQuantity(int quantity) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method is called when the "+" button is clicked.
     */
    public void increment(View view) {
        if (quantity == 10) {
            Toast.makeText(this, getString(R.string.maxQuantity), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the "-" button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, getString(R.string.minQuantity), Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        displayQuantity(quantity);
    }
}