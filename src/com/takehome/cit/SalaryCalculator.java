package com.takehome.cit;

import java.text.DecimalFormat;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SalaryCalculator extends Activity implements OnClickListener {
	private InterstitialAd mInterstitial;
	String susercategory;
	String[] scategory = new String[] { "Male", "Female",
			"Senior Citizen(>60 years)", "Super Senior(>80 years)" };
	Spinner sSpcategory;
	double sincomed = 0.0;
	double sdeductionsd = 0.0;
	double saftertaxd = 0.0;
	double ssalaryd = 0.0;
	String sincomes = "0.0";
	String sdeductionss = "0.0";
	double sgettaxamount = 0.0;
	String saftertaxs = "";

	String semailid = "";
	String sesubject = "";
	String secontent = "";

	Double smalelowerlimit = 250000.00;
	Double sfemalelowerlimit = 250000.00;
	Double ssenior60lowerlimit = 300000.00;
	Double ssenior80lowerlimit = 500000.00;

	Double sceilinglimit1 = 500000.00;
	Double sceilinglimit2 = 1000000.00;

	Double stax = 0.00;
	Double sbasicpayd = 0.0;
	String sbasicpays = "0.0";

	EditText sincome;
	EditText sdeductions;
	EditText saftertax;
	EditText sincometax;
	EditText semailidadd;
	EditText ssettakehome;

	EditText sbasicpay;
	double stax1 = 0.0;
	double stax2 = 0.0;
	double stax3 = 0.0;
	double staxableincomed = 0.0;
	double stotaltaxd = 0.0;
	double sincaftincd = 0.0;
	double sincperd = 0.0;
	double sthomesald = 0.0;
	Button scheckinc;
	public static String sharevaluesfile = "shared";
	SharedPreferences sh;
	SharedPreferences.Editor editsh;
	Button schecktax;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.salarycalcxml);

		sh = getSharedPreferences(sharevaluesfile, 0);

		editsh = sh.edit();

		sincome = (EditText) findViewById(R.id.setoincome);
		sdeductions = (EditText) findViewById(R.id.setodeductions);

		sbasicpay = (EditText) findViewById(R.id.setbasicpay);
		ssettakehome = (EditText) findViewById(R.id.settakehome);

		sSpcategory = (Spinner) findViewById(R.id.sspocategory);

		sincometax = (EditText) findViewById(R.id.setotax);
		schecktax = (Button) findViewById(R.id.sbtochecktax);

		Button sdeductions = (Button) findViewById(R.id.sbtdeduct);
		ArrayAdapter<String> sadaptcat = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, scategory);
		sSpcategory.setAdapter(sadaptcat);

		scheckinc = (Button) findViewById(R.id.sbtincrement);

		// ssendemail.setOnClickListener(this);
		schecktax.setOnClickListener(this);
		sdeductions.setOnClickListener(this);
		scheckinc.setOnClickListener(this);

		// code for providing/displaying interstitial ads in the app
		mInterstitial = new InterstitialAd(this);
		mInterstitial.setAdUnitId(getResources().getString(
				R.string.ad_unit_id_inst));
		AdRequest adreqin = new AdRequest.Builder().build();
		mInterstitial.loadAd(adreqin);

		mInterstitial.setAdListener(new AdListener() {

			public void onAdLoaded() {

				displayadint();
			}
		});

	}

	public void displayadint() {

		if (mInterstitial.isLoaded()) {
			mInterstitial.show();
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId())

		{
		case R.id.sbtochecktax:
			InputMethodManager Im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			Im.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);

			Im.hideSoftInputFromWindow((null == getCurrentFocus()) ? null
					: getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);

			if ((sSpcategory.getSelectedItem()) != null) {

				susercategory = sSpcategory.getSelectedItem().toString();

				getvalues();
				sgettaxamount = calculate(sincomed, sdeductionsd, susercategory);
				displayvalues();

			//	schecktax.setText("Re-calculate Salary");
				schecktax.setEnabled(false);
				// resetvalues();

			}

			break;

		/*
		 * case R.id.btemailresult: { InputMethodManager Iim =
		 * (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		 * Iim.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),
		 * InputMethodManager.HIDE_NOT_ALWAYS);
		 * 
		 * Iim.hideSoftInputFromWindow((null == getCurrentFocus()) ? null :
		 * getCurrentFocus().getWindowToken(),
		 * InputMethodManager.HIDE_NOT_ALWAYS);
		 * 
		 * semailid= semailidadd.getText().toString(); // String sallemailid[] =
		 * { semailid }
		 * sesubject="Income Tax calculation Result from Android App";
		 * secontent=
		 * "Dear User, we have included the results of the income tax calculations with this email.\n"
		 * + "Income =" + sincomes + "\n" + "Deductions =" + sdeductionss + "\n"
		 * + "Income after tax =" + saftertax.getText().toString() +"\n" +
		 * "Tax you pay = " + sincometax.getText().toString() + "\n" + "\n" +
		 * "\n" + "\n\n" + "Thanks! \n" + "With Regards \n" +
		 * "Income Tax Calcuator \n" + "PN This is a system generated email";
		 * 
		 * 
		 * 
		 * Intent emailintent = new Intent(android.content.Intent.ACTION_SEND);
		 * 
		 * // emailintent.putExtra(android.content.Intent.EXTRA_EMAIL,
		 * sallemailid);
		 * emailintent.putExtra(android.content.Intent.EXTRA_SUBJECT,
		 * sesubject); emailintent.setType("text/plain");
		 * emailintent.putExtra(android.content.Intent.EXTRA_TEXT, secontent);
		 * startActivity(emailintent);
		 * 
		 * }
		 * 
		 * break;
		 */
		case R.id.sbtdeduct:

		{
			InputMethodManager simdt = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			simdt.hideSoftInputFromInputMethod(getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

			simdt.hideSoftInputFromWindow((null == getCurrentFocus()) ? null
					: getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);

			Intent deductintent = new Intent("com.takehome.cit.DEDUCTIONS");
			startActivityForResult(deductintent, 124);

		}

			break;
		case R.id.sbtincrement:

		{
			InputMethodManager sbtincim = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

			sbtincim.hideSoftInputFromInputMethod(getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			sbtincim.hideSoftInputFromWindow((null == getCurrentFocus()) ? null
					: getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);

			Intent sbtincrementintent = new Intent("com.takehome.cit.INCREMENT");
			startActivity(sbtincrementintent);

		}

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
		if (requestCode == 124) {
			// Make sure the request was successful
			if (resultCode == RESULT_OK) {

				sdeductionss = data.getExtras().getString("totalded");
				sdeductions.setText(sdeductionss);

			}
		}
	}

	public void getvalues() {
		try {

			sincomes = sincome.getText().toString();
			sdeductionss = sdeductions.getText().toString();
			sbasicpays = sbasicpay.getText().toString();

			if (sincomes.isEmpty()) {

				sincomes = "0";

			}

			if (sdeductionss.isEmpty())

			{

				sdeductionss = "0";
			}

			if (sbasicpays.isEmpty())

			{

				sbasicpays = "0";
			}

			sincomed = Double.valueOf(sincomes);
			sdeductionsd = Double.valueOf(sdeductionss);
			sbasicpayd = Double.valueOf(sbasicpays);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	public static String convertdecimalformat(double frresult)

	{
		String sresult = "";
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		sresult = twoDForm.format(frresult);
		return sresult;

	}

	public void displayvalues()

	{
		// take home salary calculation

		sthomesald = (((sincomed - sgettaxamount) / 12) - ((0.24 * sbasicpayd) + (.0481 * sbasicpayd))); // calculating
		// salary
		// for
		// one
		// month

		ssettakehome.setText(convertdecimalformat((sthomesald)));
		sincometax.setText(convertdecimalformat(sgettaxamount));

		editsh.putString("Income", sincomes);
		editsh.putString("basic", sbasicpays);
		editsh.putString("deductions", sdeductionss);
		editsh.putString("takehome", convertdecimalformat(sthomesald));
		editsh.putString("category", susercategory);
		editsh.commit();

	}

	public double calculate(double incomed, double deductionsd,
			String usercategory) {

		staxableincomed = (incomed - deductionsd);

		switch (usercategory) {

		case "Male":

			if (staxableincomed > sceilinglimit2) {

				stax3 = (staxableincomed - sceilinglimit2) * 30 / 100;
				stax2 = (sceilinglimit2 - sceilinglimit1) * 20 / 100;
				stax1 = (sceilinglimit1 - smalelowerlimit) * 10 / 100;

				stotaltaxd = stax1 + stax2 + stax3;

			}

			else if (staxableincomed > sceilinglimit1) {

				stax2 = (staxableincomed - sceilinglimit1) * 20 / 100;
				stax1 = (sceilinglimit1 - smalelowerlimit) * 10 / 100;

				stotaltaxd = stax1 + stax2;
			}

			else if (staxableincomed > smalelowerlimit) {
				stax1 = (staxableincomed - smalelowerlimit) * 10 / 100;
				stotaltaxd = stax1;

			}

			else {
				stotaltaxd = 0.0;
			}

			break;

		case "Female":

			if (staxableincomed > sceilinglimit2) {

				stax3 = (staxableincomed - sceilinglimit2) * 30 / 100;
				stax2 = (sceilinglimit2 - sceilinglimit1) * 20 / 100;
				stax1 = (sceilinglimit1 - sfemalelowerlimit) * 10 / 100;

				stotaltaxd = stax1 + stax2 + stax3;
			}

			else if (staxableincomed > sceilinglimit1) {

				stax2 = (staxableincomed - sceilinglimit1) * 20 / 100;
				stax1 = (sceilinglimit1 - sfemalelowerlimit) * 10 / 100;

				stotaltaxd = stax1 + stax2;
			}

			else if (staxableincomed > sfemalelowerlimit) {
				stax1 = (staxableincomed - sfemalelowerlimit) * 10 / 100;
				stotaltaxd = stax1;

			}

			else {
				stotaltaxd = 0.0;
			}

			break;

		case "Senior Citizen(>60 years)":

			if (staxableincomed > sceilinglimit2) {

				stax3 = (staxableincomed - sceilinglimit2) * 30 / 100;
				stax2 = (sceilinglimit2 - sceilinglimit1) * 20 / 100;
				stax1 = (sceilinglimit1 - ssenior60lowerlimit) * 10 / 100;

				stotaltaxd = stax1 + stax2 + stax3;
			}

			else if (staxableincomed > sceilinglimit1) {

				stax2 = (staxableincomed - sceilinglimit1) * 20 / 100;
				stax1 = (sceilinglimit1 - ssenior60lowerlimit) * 10 / 100;

				stotaltaxd = stax1 + stax2;
			}

			else if (staxableincomed > ssenior60lowerlimit) {
				stax1 = (staxableincomed - ssenior60lowerlimit) * 10 / 100;
				stotaltaxd = stax1;

			}

			else {
				stotaltaxd = 0.0;
			}

			break;

		case "Super Senior(>80 years)":

			if (staxableincomed > sceilinglimit2) {

				stax3 = (staxableincomed - sceilinglimit2) * 30 / 100;
				stax2 = (sceilinglimit2 - sceilinglimit1) * 20 / 100;
				stax1 = (sceilinglimit1 - ssenior80lowerlimit) * 10 / 100;

				stotaltaxd = stax1 + stax2 + stax3;
			}

			else if (staxableincomed > sceilinglimit1) {

				stax2 = (staxableincomed - sceilinglimit1) * 20 / 100;
				stax1 = (sceilinglimit1 - ssenior80lowerlimit) * 10 / 100;

				stotaltaxd = stax1 + stax2;
			}

			else if (staxableincomed > ssenior80lowerlimit) {
				stax1 = (staxableincomed - ssenior80lowerlimit) * 10 / 100;
				stotaltaxd = stax1;

			}

			else {
				stotaltaxd = 0.0;
			}

			break;

		}

		return (stotaltaxd * (.03) + stotaltaxd);

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

		editsh.putString("Income", "0");
		editsh.putString("basic", "0");
		editsh.putString("deductions", "0");
		editsh.putString("takehome", "0");
		editsh.putString("category", "0");
		editsh.commit();
		super.finish();
	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		schecktax.setEnabled(true);
		
		super.onResume();
	}
	
}
