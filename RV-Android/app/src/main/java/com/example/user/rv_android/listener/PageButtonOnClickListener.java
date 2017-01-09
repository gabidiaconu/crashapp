package com.example.user.rv_android.listener;

import com.example.user.rv_android.OSMTracker;
import com.example.user.rv_android.R;
import com.example.user.rv_android.db.TrackContentProvider.Schema;
import com.example.user.rv_android.db.model.Track;
import com.example.user.rv_android.layout.UserDefinedLayout;
import com.example.user.rv_android.util.FileSystemUtils;import android.view.View;
import android.view.View.OnClickListener;

/**
 * Listener for page-type buttons. Provokes a navigation
 * to the target page.
 * 
 * @author Nicolas Guillaumin
 *
 */
public class PageButtonOnClickListener implements OnClickListener {

	/**
	 * Name of the target layout (page) for this button
	 */
	private String targetLayoutName;
	
	/**
	 * Main layout
	 */
	private UserDefinedLayout rootLayout;

	public PageButtonOnClickListener(UserDefinedLayout layout, String target) {
		rootLayout = layout;
		targetLayoutName = target;
	}

	@Override
	public void onClick(View v) {
		if (targetLayoutName != null) {
			rootLayout.push(targetLayoutName);
		}
	}

}
