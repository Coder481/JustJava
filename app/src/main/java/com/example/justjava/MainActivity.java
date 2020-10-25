package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.justjava.databinding.ActivityMainBinding;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int pricePerCup = 5;

    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        activityMainBinding.orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * This method is called when the order button is clicked.
             */
            public void onClick(View v) {

                // Checking for Whipped Cream And Chocolate
                CheckBox whippedCreamCheckBox = findViewById(R.id.check_whipped_cream);
                CheckBox chocolateCheckBox = findViewById(R.id.check_choclate);
                boolean isWhippedCream = whippedCreamCheckBox.isChecked();
                boolean isChocolate = chocolateCheckBox.isChecked();

                // Getting Name from EditView
                EditText text = findViewById(R.id.editText_customerName);
                String name = text.getText().toString();

                // Giving a short message after a order is placed
                Toast.makeText(MainActivity.this, "Order Placed",Toast.LENGTH_SHORT).show();

                if (isWhippedCream) pricePerCup = 10;
                if (isChocolate) pricePerCup = 15;
                String priceMessage = createOrderSummary(name ,isWhippedCream,isChocolate , pricePerCup);
                displayMessage(priceMessage);
            }
        });


    }

    private String createOrderSummary(String name, boolean whippedCream , boolean chocolate,int pricePerCup){
        String summaryMessage = "Name : "+name+"\nAdd Whipped Cream:"+whippedCream+"\nAdd Chocolate:"+chocolate+
                "\nQuantity : "+quantity+"\nTotal Amount: Rs." + calculatePrice(quantity , pricePerCup)+
                "\nThank You !! ";;
        return summaryMessage;
    }

    /**
     * This method is called when + button is clicked
     */
     public void increment(View view){
         quantity = quantity + 1;
         display(quantity);
     }

    /**
     * This method is called when - button is clicked
     */
     public void decrement(View view){
         if (quantity != 0){
         quantity = quantity - 1;
         display(quantity);}
     }

     // When Reset Button is Clicked
     public void resetBtn(View view){
         displayMessage("");
         display(0);
     }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText( "" + number);
    }

    /**
     * This method (displayMessage) will display the price (with a salutation message) on the screen
     */
    private void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is calculating the total price according to the order placed
     */
    private int calculatePrice(int quantity , int pricePerCup){
        int totalPrice = quantity * pricePerCup;
        return totalPrice;
    }
}