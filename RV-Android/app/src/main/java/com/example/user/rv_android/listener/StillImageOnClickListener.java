package com.example.user.rv_android.listener;

import com.example.user.rv_android.OSMTracker;
import com.example.user.rv_android.R;
import com.example.user.rv_android.db.TrackContentProvider.Schema;
import com.example.user.rv_android.db.model.Track;
import com.example.user.rv_android.util.FileSystemUtils;import android.view.View;
import android.view.View.OnClickListener;

/**
 * Manages still image recording with camera app.
 * 
 * @author Nicolas Guillaumin
 *
 */
public class StillImageOnClickListener implements OnClickListener {

	/**
	 * Parent activity
	 */
	TrackLogger activity;
	
	public StillImageOnClickListener(TrackLogger parent) {
		activity = parent;
	}
	
	@Override
	public void onClick(View v) {
		activity.requestStillImage();
	}

}
