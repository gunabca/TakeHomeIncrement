package com.takehome.cit;
 


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class Deductions extends Activity implements OnClickListener {

	String totaldeduction = "";

	EditText eethra;
	EditText eetlta;
	EditText eetmedbills;
	EditText eettel;
	EditText eettrans;
	EditText eetmedself;
	EditText eetmedpar;
	EditText eethloan;
	EditText eetpf;
	EditText eetppf;
	EditText eetlic;
	EditText eetother80c;
	EditText eetequity;
	EditText eetotherdeduct;
	EditText eettotalded;

	String ethras="0";
	String etltas="0";
	String etmedbillss="0" ;
	String ettels="0";
	String ettranss="0";
	String etmedselfs="0";
	String etmedpars="0";
	String ethloans="0" ;
	String etpfs="0" ;
	String etppfs="0";
	String etlics="0";
	String etother80cs="0";
	String etequitys="0" ;
	String ettotaldeds="0";
	String etotherdeducts="0";

	double ethrad = 0.0;
	double etltad = 0.0;
	double etmedbillsd = 0.0;
	double etteld = 0.0;
	double ettransd = 0.0;
	double etmedselfd = 0.0;
	double etmedpard = 0.0;
	double ethloand = 0.0;
	double etpfd = 0.0;
	double etppfd = 0.0;
	double etlicd = 0.0;
	double etother80cd = 0.0;
	double etequityd = 0.0;
	double ettotaldedd = 0.0;
	double etotherdeductd=0.0;
	Button bttotal;

	// EditText Deduction1 = (EditText) findViewById(R.id.);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deductionsv);
		eethra = (EditText) findViewById(R.id.ethra);
		eetlta = (EditText) findViewById(R.id.etlta);
		eetmedbills = (EditText) findViewById(R.id.etmedbills);
		eettel = (EditText) findViewById(R.id.ettel);
		eettrans = (EditText) findViewById(R.id.ettrans);
		eetmedself = (EditText) findViewById(R.id.etmedself);
		eetmedpar = (EditText) findViewById(R.id.etmedpar);
		eethloan = (EditText) findViewById(R.id.ethloan);
		eetpf = (EditText) findViewById(R.id.etpf);
		eetppf = (EditText) findViewById(R.id.etppf);
		eetlic = (EditText) findViewById(R.id.etlic);
		eetother80c = (EditText) findViewById(R.id.etother80c);
		eetequity = (EditText) findViewById(R.id.etequity);
		eettotalded = (EditText) findViewById(R.id.ettotalded);

		eetotherdeduct = (EditText) findViewById(R.id.etotherdeduct);
		
		bttotal = (Button) findViewById(R.id.bttotaldeduction);

		bttotal.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		
		InputMethodManager imd = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		
		
		imd.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);

		imd.hideSoftInputFromWindow((null == getCurrentFocus()) ? null
				: getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
		getvalandcalc();
		finish();

	}

	public void getvalandcalc() {

		try {
			ethras = eethra.getText().toString();
			etltas = eetlta.getText().toString();
			etmedbillss = eetmedbills.getText().toString();
			ettels = eettel.getText().toString();
			ettranss = eettrans.getText().toString();
			etmedselfs = eetmedself.getText().toString();
			etmedpars = eetmedpar.getText().toString();
			ethloans = eethloan.getText().toString();
			etpfs = eetpf.getText().toString();
			etppfs = eetppf.getText().toString();
			etlics = eetlic.getText().toString();
			etother80cs = eetother80c.getText().toString();
			etequitys = eetequity.getText().toString();
			etotherdeducts = eetotherdeduct.getText().toString();
			
			// ettotaldeds = ettotalded.getText().toString();

			
			if(ethras.isEmpty() )
				
			{
				ethras="0";
				
			}
			if(etltas.isEmpty())
			{
				etltas="0";
			}
			
			if(etmedbillss.isEmpty())
				
			{
				etmedbillss="0";
				
			}
			if(ettels.isEmpty())
			{
				ettels="0";
			}
			
			if(ettranss.isEmpty())
				
			{
				ettranss="0";
				
			}
			
			if (etmedselfs.isEmpty())
			{
				
				etmedselfs="0";
			}
			
			if(etmedpars.isEmpty())
				
			{
				
				etmedpars="0";
			}
			
			if(ethloans.isEmpty())
			{
				
				ethloans="0";
				
			}
			
			if(etpfs.isEmpty())
			{
				
				etpfs="0";
			}
			
			if(etppfs.isEmpty())
				
			{
				
				etppfs="0";
				
			}
			
			if(etlics.isEmpty())
			{
				
				etlics="0";
				
			}
			
			if(etother80cs.isEmpty())
			{
				
				etother80cs="0";
				
			}
			
			if(etequitys.isEmpty())
			{
				etequitys="0";
				
				
			}
			if(etotherdeducts.isEmpty())
			{
				etotherdeducts="0";
			}
			
			
			ethrad = Double.valueOf(ethras);
			etltad = Double.valueOf(etltas);
			etmedbillsd = Double.valueOf(etmedbillss);
			etteld = Double.valueOf(ettels);
			ettransd = Double.valueOf(ettranss);
			etmedselfd = Double.valueOf(etmedselfs);
			etmedpard = Double.valueOf(etmedpars);
			ethloand = Double.valueOf(ethloans);
			etpfd = Double.valueOf(etpfs);
			etppfd = Double.valueOf(etppfs);
			etlicd = Double.valueOf(etlics);
			etother80cd = Double.valueOf(etother80cs);
			etequityd = Double.valueOf(etequitys);
			
			etotherdeductd = Double.valueOf(etotherdeducts);
			ettotaldedd = ((ethrad + etltad) + (etmedbillsd + etteld) +( ettransd
					+ etmedselfd) + (etmedpard + ethloand) + (etpfd + etppfd)
					+ (etlicd + etother80cd + etequityd+ etotherdeductd));

			eettotalded.setText(String.valueOf(ettotaldedd));
			totaldeduction = String.valueOf(ettotaldedd);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		Intent sendtotaldeduct = new Intent();
		sendtotaldeduct.putExtra("totalded", totaldeduction);

		setResult(RESULT_OK, sendtotaldeduct);
		super.finish();
	}

}
