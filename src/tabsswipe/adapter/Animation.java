package tabsswipe.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.example.charlieweather.R;

public class Animation {

	public static void slide_down(Context ctx, View v) {
		android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx,
				R.anim.slide_down);
		if (a != null) {
			a.reset();
			if (v != null) {
				v.clearAnimation();
				v.startAnimation(a);
			}
		}
	}
	public static void slide_up(Context ctx, final View v) {
		android.view.animation.Animation a = AnimationUtils.loadAnimation(ctx,
				R.anim.slide_up);
		if (a != null) {
			a.reset();
			if (v != null) {
				v.clearAnimation();
				v.startAnimation(a);
				a.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationEnd(
							android.view.animation.Animation animation) {
						// TODO Auto-generated method stub
						v.setVisibility(View.GONE);
						
					}

					@Override
					public void onAnimationRepeat(
							android.view.animation.Animation animation) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onAnimationStart(
							android.view.animation.Animation animation) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		}
	}
}
