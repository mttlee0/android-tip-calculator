package com.codepath.example.androidtipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class TipActivity extends Activity {
	TextView tvTipAmount;
	EditText etBillTotal;	
	RadioGroup rdgTipPercent;
	RadioButton rd10Percent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip);
		tvTipAmount = (TextView)findViewById(R.id.tvTipAmount);
		etBillTotal = (EditText)findViewById(R.id.etBillTotal);
		rdgTipPercent = (RadioGroup)findViewById(R.id.rdgTipPercent);
		rd10Percent = (RadioButton)findViewById(R.id.rd10Percent);
		
		// Sets 10% tip as the default
		rd10Percent.setChecked(true);
		// Sets up the listeners
		setupTextViewListener();
		setupRadioGroupListener();
	}
	
	// TextView listener to update the tip amount as the user is typing. 
	private void setupTextViewListener() {
		etBillTotal.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable textView) {				
				try {
					double tip = getTipAmount();
					tvTipAmount.setText("Tips is: $" + String.valueOf(tip));
 				} catch (NumberFormatException e) {
 					tvTipAmount.setText("Tip is: ______");
 				}
									
				return;
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				return;
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int count, int after) {
				return;
			}
		});
	}
	
	// RadioGroup listener for when the tip amount chnages   
	private void setupRadioGroupListener() {
		rdgTipPercent.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int id) {
				try {
					double tip = getTipAmount();
					tvTipAmount.setText("Tip is: $" + String.valueOf(tip));
				} catch (NumberFormatException e) {
					tvTipAmount.setText("Tip is: ______");
				}
													
			}
		});
	}
	
	// Returns the tip amount as a double based upon bill total entered and tip percent checked. Helper function.
	private double getTipAmount() {
		double tip= Double.parseDouble(etBillTotal.getText().toString());
		int id = rdgTipPercent.getCheckedRadioButtonId();
		DecimalFormat twoDForm = new DecimalFormat("#.##"); 
		
		if (id == R.id.rd10Percent) {
			tip *= 0.10;
		} else if (id == R.id.rd15Percent) {
			tip *= 0.15;		
		} else {
			tip *= 0.20;					
		}	
		
		return Double.valueOf(twoDForm.format(tip));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip, menu);
		return true;
	}

}
