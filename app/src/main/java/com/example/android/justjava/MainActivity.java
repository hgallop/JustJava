package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /** This variable is the global quantity used in all onClick methods */
    int quantity = 0;

    private TextView textQuantity;
    private TextView textPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textQuantity = (TextView) findViewById(R.id.quantity_text_view);
        textPrice = (TextView) findViewById(R.id.price_text_view);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        quantity += 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity -= 1;
        } else {
            quantity = 0;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int total = calculatePrice(quantity);
        String priceMessage = "Total: $" + total + "\nThank You!";

        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = textQuantity;
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = textPrice;
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity) {
        quantity = quantity * 5;
        return quantity;
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = textPrice;
        priceTextView.setText(message);
    }

}