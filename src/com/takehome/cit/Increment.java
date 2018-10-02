package com.takehome.cit;



import java.text.DecimalFormat;

import android.R.string;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class Increment extends Activity implements OnClickListener{

	
	// View control declarations begin
	EditText ietoincome;
	EditText ietbasicpay;
	EditText iettakehome;
	EditText ietdeductions;

	EditText ietoincomeinc;
	EditText ietbasicpayinc;
	EditText iettakehomeinc;
	EditText ietdeductionsinc;

	EditText setincper;
	

ImageButton 	minusby1;
	
ImageButton plusby1;
	// View controls declarations ends
	
	String ietoincomes;
	String ietbasicpays;
	String iettakehomes;
	String ietdeductionss;

	string ietoincomeincs;
	string ietbasicpayincs;
	string iettakehomeincs;
    string ietdeductionsincs;
    
    String setincpers;
    String usercat;
    
	double ietoincomed;
	double ietbasicpayd;
	double iettakehomed;
	double ietdeductionsd;

	double ietoincomeincd;
	double ietbasicpayincd;
	double iettakehomeincd;
	double ietdeductionsincd;
	
	double incper =0.0;
	
	
	
	
	SharedPreferences sd1;
	

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.increment);

		ietoincome = (EditText) findViewById(R.id.ietoincome);
		ietbasicpay = (EditText) findViewById(R.id.ietbasicpay);
		iettakehome = (EditText) findViewById(R.id.iettakehome);
		ietdeductions = (EditText) findViewById(R.id.ietdeductions);

		ietoincomeinc = (EditText) findViewById(R.id.ietoincomeinc);
		ietbasicpayinc = (EditText) findViewById(R.id.ietbasicpayinc);
		ietdeductionsinc = (EditText) findViewById(R.id.ietdeductionsinc);
		iettakehomeinc = (EditText) findViewById(R.id.iettakehomeinc);
			
		 setincper = (EditText) findViewById(R.id.ietincper);
		 minusby1 = (ImageButton) findViewById(R.id.sbtdec);
		 plusby1= (ImageButton) findViewById(R.id.sbtinc);
		
		// getting values from previous activity
		
		sd1 = getSharedPreferences("shared", 0);

		ietoincomes = sd1.getString("Income", "0");
		ietbasicpays = sd1.getString("basic", "0");
		ietdeductionss = sd1.getString("deductions", "0");
		iettakehomes = sd1.getString("takehome", "0");
		usercat = sd1.getString("category" , "0");

		displayresults();

		ietbasicpayd = Double.valueOf(ietbasicpays);
		ietoincomed = Double.valueOf(ietoincomes);
		
		minusby1.setOnClickListener(this);
		plusby1.setOnClickListener(this);
	}

	public double calculate(double incomed, double deductionsd,
			String usercategory) {

		double totaltaxd = 0.0;
		double taxableincomed = 0.0;
		double tax1 = 0.0;
		double tax2 = 0.0;
		double tax3 = 0.0;
		Double malelowerlimit = 250000.00;
		Double femalelowerlimit = 250000.00;
		Double senior60lowerlimit = 300000.00;
		Double senior80lowerlimit = 500000.00;

		Double ceilinglimit1 = 500000.00;
		Double ceilinglimit2 = 1000000.00;

		taxableincomed = (incomed - deductionsd);

		switch (usercategory) {

		case "Male":

			if (taxableincomed > ceilinglimit2) {

				tax3 = (taxableincomed - ceilinglimit2) * 30 / 100;
				tax2 = (ceilinglimit2 - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - malelowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2 + tax3;

			}

			else if (taxableincomed > ceilinglimit1) {

				tax2 = (taxableincomed - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - malelowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2;
			}

			else if (taxableincomed > malelowerlimit) {
				tax1 = (taxableincomed - malelowerlimit) * 10 / 100;
				totaltaxd = tax1;

			}

			else {
				totaltaxd = 0.0;
			}

			break;

		case "Female":

			if (taxableincomed > ceilinglimit2) {

				tax3 = (taxableincomed - ceilinglimit2) * 30 / 100;
				tax2 = (ceilinglimit2 - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - femalelowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2 + tax3;
			}

			else if (taxableincomed > ceilinglimit1) {

				tax2 = (taxableincomed - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - femalelowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2;
			}

			else if (taxableincomed > femalelowerlimit) {
				tax1 = (taxableincomed - femalelowerlimit) * 10 / 100;
				totaltaxd = tax1;

			}

			else {
				totaltaxd = 0.0;
			}

			break;

		case "Senior Citizen(>60 years)":

			if (taxableincomed > ceilinglimit2) {

				tax3 = (taxableincomed - ceilinglimit2) * 30 / 100;
				tax2 = (ceilinglimit2 - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - senior60lowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2 + tax3;
			}

			else if (taxableincomed > ceilinglimit1) {

				tax2 = (taxableincomed - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - senior60lowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2;
			}

			else if (taxableincomed > senior60lowerlimit) {
				tax1 = (taxableincomed - senior60lowerlimit) * 10 / 100;
				totaltaxd = tax1;

			}

			else {
				totaltaxd = 0.0;
			}

			break;

		case "Super Senior(>80 years)":

			if (taxableincomed > ceilinglimit2) {

				tax3 = (taxableincomed - ceilinglimit2) * 30 / 100;
				tax2 = (ceilinglimit2 - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - senior80lowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2 + tax3;
			}

			else if (taxableincomed > ceilinglimit1) {

				tax2 = (taxableincomed - ceilinglimit1) * 20 / 100;
				tax1 = (ceilinglimit1 - senior80lowerlimit) * 10 / 100;

				totaltaxd = tax1 + tax2;
			}

			else if (taxableincomed > senior80lowerlimit) {
				tax1 = (taxableincomed - senior80lowerlimit) * 10 / 100;
				totaltaxd = tax1;

			}

			else {
				totaltaxd = 0.0;
			}

			break;

		}

		return (totaltaxd * (.03) + totaltaxd);

	}

	//calculation of basic pay.
	
	
	public void basiccalculation(double incper) {

		double cbasicpercent = 0.0;
		double nbasicinc=0.0;
		double taxamountinc=0.0;
		
		cbasicpercent = ((ietbasicpayd *12)/ ietoincomed) * 100;
		
		ietoincomeincd = ((ietoincomed * incper)/100 ) + ietoincomed ;
		nbasicinc = (ietoincomeincd*cbasicpercent)/100;
		ietbasicpayincd= nbasicinc/12;
		ietdeductionsd=Double.valueOf(ietdeductionss);
		ietdeductionsincd=ietdeductionsd;// not calculating the increase in deductions 
		taxamountinc = calculate(ietoincomeincd,ietdeductionsd,usercat);
		
		double finalincaftertax = ietoincomeincd - taxamountinc;
		double totalsalaftertax = (finalincaftertax/12) - ((.24*ietbasicpayincd ) +(.0481*ietbasicpayincd));
		
		iettakehomeincd=totalsalaftertax;
		

			
	}

	public static String convertdecimalformat(double frresult)

	{
		String sresult = "";
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		sresult = twoDForm.format(frresult);
		return sresult;

	}
	public static String convertdecimalformat2(double frresult)

	{
		String sresult = "";
		DecimalFormat twoDForm = new DecimalFormat("#");
		sresult = twoDForm.format(frresult);
		return sresult;

	}
	
	public void displayresultinc()
	
	{
		
		
		
		ietoincomeinc.setText(convertdecimalformat(ietoincomeincd));
		ietbasicpayinc.setText(convertdecimalformat(ietbasicpayincd));
		iettakehomeinc.setText(convertdecimalformat(iettakehomeincd));
		ietdeductionsinc.setText(convertdecimalformat(ietdeductionsincd));
		
		
		
	}
	public void displayresults() {

		ietoincome.setText(ietoincomes);
		ietbasicpay.setText(ietbasicpays);
		iettakehome.setText(iettakehomes);
		ietdeductions.setText(ietdeductionss);
		
		
		
		

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
		case R.id.sbtinc:
		{
			setincpers=setincper.getText().toString();
			double setincperd = Double.valueOf(setincpers);
			
			setincperd = setincperd + 1;
			
			setincper.setText(convertdecimalformat2(setincperd));
			basiccalculation(setincperd);
			
			 displayresultinc();
			
		}
		
		break;
		case R.id.sbtdec:
			
		{
			
			setincpers=setincper.getText().toString();
			double setincperd = Double.valueOf(setincpers);
			
			setincperd = setincperd - 1;
			setincper.setText(convertdecimalformat2(setincperd));
			basiccalculation(setincperd);
			
			 displayresultinc();
			
			
			
		}
			break;
		
		
		
		}
		
		
		
		
		
	}
	
	
	

}
