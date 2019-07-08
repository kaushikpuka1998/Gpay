package com.example.gpay;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    Button btn;

    float amont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.etAmount);
        btn = findViewById(R.id.bPay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amont = Integer.parseInt(et1.getText().toString().trim());
                amont = amont*100;


                startPayment(amont);
            }


        });



    }

    private void startPayment(float amont) {


        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        //checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "Merchant Name");

            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Order #123456");

            options.put("currency", "INR");

            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            options.put("amount", amont);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("Error",e.toString());
        }

    }

    public void onPaymentSuccess(String s ){
        Toast.makeText(MainActivity.this,"Payment is Successful",Toast.LENGTH_LONG).show();
    }

    public void onPaymentError(int i,String s){
        Toast.makeText(MainActivity.this,"Payment is Failed",Toast.LENGTH_LONG).show();
    }
}
