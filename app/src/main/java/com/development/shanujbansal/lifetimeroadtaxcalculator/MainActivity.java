package com.development.shanujbansal.lifetimeroadtaxcalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable backgroundImage = getResources().getDrawable(R.drawable.background);
        backgroundImage.setAlpha(25);
        LinearLayout mainActivityLayout = (LinearLayout) findViewById(R.id.MainActivity);
        mainActivityLayout.setBackgroundDrawable(backgroundImage);

        final TextView roadTaxText = (TextView) findViewById(R.id.roadTaxPayableText);
        final TextView roadTaxAmt = (TextView) findViewById(R.id.roadTaxPayableAmt);
        final TextView errorDisplayLabelText = (TextView) findViewById(R.id.errorDisplayLbl);
        final TextView errorMessageText = (TextView) findViewById(R.id.errorMessage);

        // Initially on first load, these values should not be shown.
        roadTaxAmt.setVisibility(View.GONE);
        roadTaxText.setVisibility(View.GONE);
        errorDisplayLabelText.setVisibility(View.GONE);
        errorMessageText.setVisibility(View.GONE);

        // could be either New/Existing
        final Spinner vehicleStatusSpinner = (Spinner) findViewById(R.id.vehicleStatusId);
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("Existing");
        statusList.add("New");
        vehicleStatusSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, statusList));

        // could be either 2 wheeler or 3 wheeler
        final Spinner vehicleTypeSpinner = (Spinner) findViewById(R.id.vehicleTypeId);
        ArrayList<String> typeList = new ArrayList<String>();
        typeList.add("2-Wheeler");
        typeList.add("4-Wheeler");
        vehicleTypeSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, typeList));

        final EditText invoiceAmountTxtBox = (EditText) findViewById(R.id.invoiceAmountId);
        final EditText invoiceDateTxtBox = (EditText) findViewById(R.id.invoiceDateId);

        invoiceAmountTxtBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    try {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch (Exception ex) {
                    }
                }
                return false;
            }
        });

        invoiceDateTxtBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    try {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch (Exception ex) {
                    }
                }
                return false;
            }
        });

//        invoiceAmountTxtBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                invoiceAmountTxtBox.getText().clear();
//            }
//        });
//
//        invoiceDateTxtBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                invoiceDateTxtBox.getText().clear();
//            }
//        });

        // button click to calculate the tax.
        Button calculateBtn = (Button) findViewById(R.id.submitBtn);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to hide the keypad in case it's opened
                try {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (Exception ex) {
                }

                roadTaxAmt.setVisibility(View.GONE);
                roadTaxText.setVisibility(View.GONE);
                errorDisplayLabelText.setVisibility(View.GONE);
                errorMessageText.setVisibility(View.GONE);

                String invoiceDateData = invoiceDateTxtBox.getText().toString();
                String invoiceAmtData = invoiceAmountTxtBox.getText().toString();
                final boolean isNewVehicle = vehicleStatusSpinner.getSelectedItem().toString().equals("New") ? true : false;

                if ((!isNewVehicle && invoiceDateData.isEmpty() == false && invoiceAmtData.isEmpty() == false) || (isNewVehicle && invoiceAmtData.isEmpty() == false)) {

                    Date parsedDate = LogicController.getInstance().isValidDate(invoiceDateTxtBox.getText().toString());
                    int purchaseAmt = Integer.parseInt(invoiceAmountTxtBox.getText().toString());

                    // in case of new registrations, the invoice date need not be entered.
                    if (isNewVehicle)
                        parsedDate = new Date();

                    if (parsedDate != null) {
                        boolean isTwoWheeler = vehicleTypeSpinner.getSelectedItem().toString().equals("2-Wheeler") ? true : false;
                        double totalTaxAmt = 0;
                        if (isTwoWheeler)
                            totalTaxAmt = LogicController.getInstance().calculateTaxForTwoWheeler(parsedDate, purchaseAmt, isNewVehicle);
                        else
                            totalTaxAmt = LogicController.getInstance().calculateTaxForFourWheeler(parsedDate, purchaseAmt, isNewVehicle);

                        if (totalTaxAmt != 0) {
                            DecimalFormat dateFormat = new DecimalFormat("#.##");
                            roadTaxAmt.setText("Rs. " + dateFormat.format(totalTaxAmt));
                            roadTaxAmt.setVisibility(View.VISIBLE);
                            roadTaxText.setVisibility(View.VISIBLE);
                        }

                        // reset the other values
//                        invoiceAmountTxtBox.setText("");
//                        invoiceDateTxtBox.setText("");

                    } else {
                        errorMessageText.setText("Provide correct invoice date.");
                        errorDisplayLabelText.setVisibility(View.VISIBLE);
                        errorMessageText.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Provide correct invoice date", Toast.LENGTH_SHORT);
                    }
                } else {
                    errorMessageText.setText("Please Provide complete details.");
                    errorDisplayLabelText.setVisibility(View.VISIBLE);
                    errorMessageText.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Please Provide complete details.", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            // Here we need to show the Info
            Intent infoIntent = new Intent(this, InfoActivity.class);
            startActivity(infoIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
