package tabsswipe.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.charlieweather.R;
import com.example.charlieweather.TermometerView;
import com.example.charlieweather.data.ForecastForOneDay;
import com.example.charlieweather.data.Helper;

public class RowAdapter extends ArrayAdapter<ForecastForOneDay> {

	private Context context;
	private int layoutResourceID;
	private List<ForecastForOneDay> data;
	
	
	public RowAdapter(Context context,int layoutResourceID,List<ForecastForOneDay> data){
		super(context, layoutResourceID,data);
		this.context=context;
		this.layoutResourceID=layoutResourceID;
		this.data=data;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row=convertView;
		final RowHolder holder;
		if(row==null){
			LayoutInflater inFlater=((Activity)context).getLayoutInflater();
			row=inFlater.inflate(layoutResourceID, parent,false);
			
			holder=new RowHolder();
			holder.img=(ImageView)row.findViewById(R.id.weatherImageViewOnly);
			holder.date=(TextView) row.findViewById(R.id.Date);
			holder.description=(TextView)row.findViewById(R.id.weatherDescriptionOnly);
			holder.details=(TextView)row.findViewById(R.id.weatherDetailsListOne);
			holder.temp=(TermometerView)row.findViewById(R.id.termometerView1);
			holder.layout=(LinearLayout)row.findViewById(R.id.mainLayout);
			row.setTag(holder);
		}else{
			holder=(RowHolder) row.getTag();
		}
		ForecastForOneDay item=data.get(position);
		holder.temp.setTemperature(item.getTemperature().getMax(),item.getTemperature().getMin());
		holder.date.setText(item.getDateString());
		String imgName = "i"+item.getImageUrl();
        holder.img.setImageResource(context.getResources().getIdentifier(imgName, "drawable", Helper.context.getPackageName()));
        
        
        
        String values = "";
		values += "Deszcz: "+Double.toString(item.getRain())+" mm\n";
		values += "Œnieg: "+Double.toString(item.getSnow())+" mm\n";
		values += "Wiatr: "+Double.toString(item.getSpeed()) +" km/h\n";
		values += "Wilgotnoœæ: "+Integer.toString(item.getHumidity())+" %\n";
		values += "Æiœnienie : "+Double.toString(item.getPressure())+" hPa\n";
		holder.details.setText(values);
		
		holder.description.setText(item.getDescription());
		if(position==0)
			holder.layout.setVisibility(View.VISIBLE);
		else
			holder.layout.setVisibility(View.GONE);
        holder.date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		    	if(holder.layout.isShown()){
		    		Animation.slide_up(context, holder.layout);
		    		
		    	}else{
		    		holder.layout.setVisibility(View.VISIBLE);
		    		Animation.slide_down(context, holder.layout);
		    	}
			}
		});
		return row;
	}
	static class RowHolder{
			TextView date;
			TextView description;
			ImageView img;
			TextView details;
			TermometerView temp;
			LinearLayout layout;
	}
	
}