package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This variable is the global quantity used in all onClick methods
     */
    int quantity = 0;

    private TextView textQuantity;
    private CheckBox checkWhip;
    private CheckBox checkChoc;
    private EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textQuantity = findViewById(R.id.quantity_text_view);
        checkWhip = findViewById(R.id.whip_check_box);
        checkChoc = findViewById(R.id.choc_check_box);
        nameText = findViewById(R.id.name_input);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity >= 100) {
            Toast.makeText(this, "You cannot order more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        } else {
            quantity += 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity <= 0) {
            quantity = 0;
            displayQuantity(quantity);
            Toast.makeText(this, "You cannot order less than 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        } else {
            quantity -= 1;
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        // Checks for user input
        boolean isWhipChecked = checkWhip.isChecked();
        boolean isChocChecked = checkChoc.isChecked();
        String name = nameText.getText().toString();
        // Calls order summary method to create message
        String summaryMessage = createOrderSummary(calculatePrice(isWhipChecked, isChocChecked), isWhipChecked, isChocChecked, name);
        // Creates and executes an intent to send the information from order summary by email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Coffee Order for "+ name);
        intent.putExtra(Intent.EXTRA_TEXT, summaryMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
     * @param hasWhippedCream boolean that checks if whipped cream is added.
     * @param hasChocolate boolean that checks if chocolate is added.
     * @return total price.
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int price = 5;
        if (hasWhippedCream) {
            price += 1;
        }
        if (hasChocolate) {
            price += 2;
        }
        return quantity * price;
    }

    /**
     * Creates an order summary message
     *
     * @param total           to pay
     * @param hasWhippedCream checked
     * @param hasChocolate    checked
     * @param typedName       from text input
     * @return summaryMessage created
     */
    private String createOrderSummary(int total, boolean hasWhippedCream, boolean hasChocolate, String typedName) {
        String summaryMessage = "Name: " + typedName;
        summaryMessage += "\nAdd whipped cream? " + hasWhippedCream;
        summaryMessage += "\nAdd chocolate? " + hasChocolate;
        summaryMessage += "\nQuantity: " + quantity;
        summaryMessage += "\nTotal: $" + total + "\nThank You!";
        return summaryMessage;
    }

}