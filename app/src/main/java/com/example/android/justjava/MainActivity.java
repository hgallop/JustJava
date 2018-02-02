package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /** This variable is the global quantity used in all onClick methods */
    int quantity = 0;

    private TextView textQuantity;
    private TextView textSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textQuantity = (TextView) findViewById(R.id.quantity_text_view);
        textSummary = (TextView) findViewById(R.id.order_summary_text_view);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity < 1000) {
            quantity += 1;
        }
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
        String summaryMessage = createOrderSummary(calculatePrice());
        displayMessage(summaryMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = textQuantity;
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price.
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Creates an order summary message
     *
     * @param total to pay
     * @return message created
     */
    private String createOrderSummary(int total) {
        return "Name: Heather \nQuantity: " + quantity + "\nTotal: $" + total + "\nThank You!";
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = textSummary;
        orderSummaryTextView.setText(message);
    }

}