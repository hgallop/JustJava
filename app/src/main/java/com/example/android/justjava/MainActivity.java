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

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public final String QUANTITY = "quantity";
    public final String NAME = "name";

    int quantity = 0;
    String name;

    private TextView textQuantity;
    private CheckBox checkWhip;
    private CheckBox checkChoc;
    private EditText nameText;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textQuantity = findViewById(R.id.quantity_text_view);
        checkWhip = findViewById(R.id.whip_check_box);
        checkChoc = findViewById(R.id.choc_check_box);
        nameText = findViewById(R.id.name_input);
        message = findViewById(R.id.displayMessage);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(QUANTITY, quantity);
        outState.putString(NAME, name);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        quantity = savedInstanceState.getInt(QUANTITY);
        name = savedInstanceState.getString(NAME);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity >= 100) {
            Toast.makeText(this,this.getString(R.string.toastUpper), Toast.LENGTH_SHORT).show();
            return;
        } else {
            quantity += 1;
        }
        displayQuantity(quantity);
        displayMessage(getResources().getString(R.string.summ_total, NumberFormat.getCurrencyInstance().format(calculatePrice(checkWhip.isChecked(), checkChoc.isChecked()))));
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity <= 0) {
            quantity = 0;
            displayQuantity(quantity);
            Toast.makeText(this,this.getString(R.string.toastLower), Toast.LENGTH_SHORT).show();
            return;
        } else {
            quantity -= 1;
        }
        displayQuantity(quantity);
        displayMessage(getResources().getString(R.string.summ_total, NumberFormat.getCurrencyInstance().format(calculatePrice(checkWhip.isChecked(), checkChoc.isChecked()))));
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        // Checks for user input
        boolean isWhipChecked = checkWhip.isChecked();
        boolean isChocChecked = checkChoc.isChecked();
        name = nameText.getText().toString();
        // Calls order summary method to create message
        String summaryMessage = createOrderSummary(calculatePrice(isWhipChecked, isChocChecked), isWhipChecked, isChocChecked, name);
        String orderPlaced = getResources().getString(R.string.summOrderPlaced);
        displayMessage(summaryMessage + orderPlaced);
        // Creates and executes an intent to send the information from order summary by email
        String emailSubject = getResources().getString(R.string.order_summary_email_subject);
        emailSubject += " " + name;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, summaryMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     * @param numberOfCoffees int value of quantity
     */
    private void displayQuantity(int numberOfCoffees) {
        textQuantity.setText(String.valueOf(numberOfCoffees));
    }

    /**
     * This method displays the orderSummary message on the screen.
     * @param order a String summaryMessage of the whole coffee order
     */
    private void displayMessage(String order){
        message.setText(order);
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream boolean that checks if whipped cream is added.
     * @param hasChocolate boolean that checks if chocolate is added.
     * @return quantity * price.
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
        String name = getResources().getString(R.string.summ_name, typedName);
        String whip = getResources().getString(R.string.summ_whip, hasWhippedCream);
        String choc = getResources().getString(R.string.summ_choc, hasChocolate);
        String quan = getResources().getString(R.string.summ_quan, quantity);
        String tot = getResources().getString(R.string.summ_total, NumberFormat.getCurrencyInstance().format(total));
        String thanks = getResources().getString(R.string.summ_thanks);
        String summaryMessage = name;
        summaryMessage += "\n" + whip;
        summaryMessage += "\n" + choc;
        summaryMessage += "\n" + quan;
        summaryMessage += "\n" + tot + "\n" + thanks;
        return summaryMessage;
    }

}