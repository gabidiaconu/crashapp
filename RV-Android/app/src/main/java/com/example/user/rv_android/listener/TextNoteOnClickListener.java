package com.example.user.rv_android.listener;

import com.example.user.rv_android.OSMTracker;
import com.example.user.rv_android.R;
import com.example.user.rv_android.db.TrackContentProvider.Schema;
import com.example.user.rv_android.db.model.Track;
import com.example.user.rv_android.util.FileSystemUtils;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Manages text note button.
 * 
 * @author Nicolas Guillaumin
 *
 */
public class TextNoteOnClickListener implements OnClickListener {

	
	private TrackLogger tl;
	
	public TextNoteOnClickListener(TrackLogger trackLogger) {
		tl = trackLogger;
	}
	
	@Override
	public void onClick(final View v) {
		// let the TrackLogger activity open and control the dialog
		tl.showDialog(TrackLogger.DIALOG_TEXT_NOTE);
	}

}
