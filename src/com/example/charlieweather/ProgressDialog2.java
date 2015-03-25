package com.example.charlieweather;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.TextView;

public class ProgressDialog2 extends Dialog {
	
	private ProgressBar2 prBar;
	private TextView textView;

	public ProgressDialog2(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.prog_dialog2);
	}

	public void setText(String txt){
		textView.setText(txt);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		prBar = (ProgressBar2)findViewById(R.id.progressBar1);
		textView = (TextView)findViewById(R.id.prDialogTextView);
	}
	
	public static ProgressDialog2 show(Context ctx,String text){
		ProgressDialog2 pr = new ProgressDialog2(ctx);
		pr.show();
		pr.setText(text);
		return pr;
	}
	
	

}
