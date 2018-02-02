package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /** This variable is the global quantity used in all onClick methods */
    int quantity = 0;

    private TextView textQuantity;
    private TextView textSummary;
    private CheckBox checkWhip;
    private CheckBox checkChoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textQuantity = findViewById(R.id.quantity_text_view);
        textSummary = findViewById(R.id.order_summary_text_view);
        checkWhip = findViewById(R.id.whip_check_box);
        checkChoc = findViewById(R.id.choc_check_box);
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
        boolean isWhipChecked = checkWhip.isChecked();
        boolean isChocChecked = checkChoc.isChecked();
        String summaryMessage = createOrderSummary(calculatePrice(), isWhipChecked, isChocChecked);
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
    private String createOrderSummary(int total, boolean hasWhippedCream, boolean hasChocolate) {
        String summaryMessage = "Name: Heather \nAdd whipped cream? ";
        summaryMessage = summaryMessage +  hasWhippedCream + "\nAdd chocolate? " + hasChocolate;
        summaryMessage = summaryMessage + "\nQuantity: " + quantity + "\nTotal: $" + total + "\nThank You!";
        return  summaryMessage;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = textSummary;
        orderSummaryTextView.setText(message);
    }

}