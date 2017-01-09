package com.example.user.rv_android.overlay;



import android.content.Context;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.example.user.rv_android.db.TrackContentProvider;

public class WayPointsOverlay extends ItemizedOverlay<OverlayItem> {

	/**
	 * List of waypoints to display on the map.
	 */
	private List<OverlayItem> wayPointItems = new ArrayList<OverlayItem>();
	
	private long trackId;
	
	private ContentResolver pContentResolver;
	
	public WayPointsOverlay(
			final Drawable pDefaultMarker,
			final Context pContext,
			final long trackId
			)
	{
		super(pDefaultMarker, new DefaultResourceProxyImpl(pContext));
		
		this.trackId = trackId;
		this.pContentResolver = pContext.getContentResolver();
		refresh();
	}
	
	public WayPointsOverlay(
			final Context pContext,
			final long trackId
			)
	{
		this(pContext.getResources().getDrawable(R.drawable.star), pContext, trackId);
	}

	@Override
	public boolean onSnapToItem(final int pX, final int pY, final Point pSnapPoint, final IMapView pMapView) {
		// TODO Implement this!
		return false;
	}
	
	@Override
	protected OverlayItem createItem(final int index) {
		return wayPointItems.get(index);
	}
	
	@Override
	public int size() {
		return wayPointItems.size();
	}
	
	public void refresh() {
		wayPointItems.clear();
		
		Cursor c = this.pContentResolver.query(
				TrackContentProvider.waypointsUri(trackId),
				null, null, null, TrackContentProvider.Schema.COL_TIMESTAMP + " asc");
 
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			OverlayItem i = new OverlayItem(
					c.getString(c.getColumnIndex(TrackContentProvider.Schema.COL_NAME)),
					c.getString(c.getColumnIndex(TrackContentProvider.Schema.COL_NAME)),
					new GeoPoint(
							c.getDouble(c.getColumnIndex(TrackContentProvider.Schema.COL_LATITUDE)),
							c.getDouble(c.getColumnIndex(TrackContentProvider.Schema.COL_LONGITUDE)))
					);
			wayPointItems.add(i);
		}
		c.close();
		populate();
	}


}
