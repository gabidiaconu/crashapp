package com.example.user.seproject;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;



public class SendReportActivity extends AppCompatActivity {

    private TextView address;
    private TextView coordinates;
    private ImageView picture1;
    private ImageView picture2;
    private ImageView picture3;
    private Button picture1But;
    private Button picture2But;
    private Button picture3But;

    private StorageReference storageReference;
    private String userId;

    private String picture1Name;
    private String picture2Name;
    private String picture3Name;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;

    private Intent imageIntent;

    public int pictureNumber = 0;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);

        Firebase.setAndroidContext(this);


        address = (TextView) findViewById(R.id.address_textview);
        coordinates = (TextView) findViewById(R.id.location_textview);
        picture1 = (ImageView) findViewById(R.id.picture1_id);
        picture2 = (ImageView) findViewById(R.id.picture2_id);
        picture3 = (ImageView) findViewById(R.id.picture3_id);
        picture1But = (Button) findViewById(R.id.picture1_but);
        picture2But = (Button) findViewById(R.id.picture2_but);
        picture3But = (Button) findViewById(R.id.picture3_but);

        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a storage reference from our app
        storageReference = storage.getReferenceFromUrl("gs://crashapp-e58a9.appspot.com");

        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Image captured and saved to fileUri specified in the Intent\

                progressDialog.setMessage("Loading...");
                progressDialog.show();

                Uri uri = data.getData();

                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                if (firebaseUser != null) {
                    userId = firebaseUser.getUid();
                }

                StorageReference mStorageRef = storageReference.child("" + userId + pictureNumber);

                mStorageRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Uri downloadUri = taskSnapshot.getDownloadUrl();

                        if(pictureNumber == 0) {
                            picture1Name = downloadUri.toString();
                            Picasso.with(SendReportActivity.this).load(downloadUri).into(picture1);
                        } else if(pictureNumber == 1) {
                            picture2Name = downloadUri.toString();
                            Picasso.with(SendReportActivity.this).load(downloadUri).into(picture2);
                        } else {
                            picture3Name = downloadUri.toString();
                            Picasso.with(SendReportActivity.this).load(downloadUri).into(picture3);
                        }
                        progressDialog.dismiss();
                    }
                });
            }
        }
    }

    public void getCurrentLocation(View view){
        GPSTracker gpsTracker = new GPSTracker(this);
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        if(gpsTracker.getLocation()!=null) {
            try {
                coordinates.setText("Latitude: " + gpsTracker.getLocation().getLatitude() + ", Longitude: " + gpsTracker.getLocation().getLongitude());
                addresses = geocoder.getFromLocation(gpsTracker.getLocation().getLatitude(), gpsTracker.getLocation().getLongitude(), 1);
                if (!addresses.isEmpty()) {
                    if (addresses.get(0) != null) {
                        address.setText(addresses.get(0).getAddressLine(0) + ", " + addresses.get(0).getAddressLine(1)+ ", " + addresses.get(0).getAddressLine(2) );
                    }
                } else {
                    Log.e("Error", "No location retrived");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addPhotosToReport(View view){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(R.string.alert_dialog_camera_advice);
        builder1.setCancelable(true);

        builder1.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                        }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }
        return mediaFile;
    }

    public void sendReport(View view){

        Firebase firebase = new Firebase("https://crashapp-e58a9.firebaseio.com/" + "CrashReports/");

        Firebase newUserFirebase = firebase.child("test");
        Map<String, Object> characteristics = new HashMap<>();
        characteristics.put("Name", SaveSharedPreferences.getUserName(this));
        characteristics.put("RegistrationNumber", SaveSharedPreferences.getUserRegistrationNumber(this));
        characteristics.put("CarModel", SaveSharedPreferences.getCarModel(this));
        characteristics.put("CarColor", SaveSharedPreferences.getCarColor(this));
        characteristics.put("PhoneNumber", SaveSharedPreferences.getPhoneNumber(this));
        characteristics.put("InsuranceNumber",SaveSharedPreferences.getUserInsuranceNumber(this));
        characteristics.put("Picture1",picture1Name);
        characteristics.put("Picture2",picture2Name);
        characteristics.put("Picture3",picture3Name);

        if(coordinates.getText().toString().isEmpty()){

        }else {
            characteristics.put("Coordinates",coordinates.getText().toString());
            characteristics.put("CurrentAddress",address.getText().toString());
        }
        newUserFirebase.updateChildren(characteristics);

    }

    public void addPicture1(View view){

        pictureNumber = 0;

        picture1.setVisibility(View.VISIBLE);
        picture1But.setVisibility(View.GONE);

        imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        startActivityForResult(imageIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        Picasso.with(SendReportActivity.this).load(fileUri).into(picture1);

    }

    public void addPicture2(View view){

        pictureNumber = 1;

        picture2.setVisibility(View.VISIBLE);
        picture2But.setVisibility(View.GONE);

        imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        startActivityForResult(imageIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        Picasso.with(SendReportActivity.this).load(fileUri).into(picture2);

    }

    public void addPicture3(View view){

        pictureNumber = 2;

        picture3.setVisibility(View.VISIBLE);
        picture3But.setVisibility(View.GONE);

        imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        startActivityForResult(imageIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        Picasso.with(SendReportActivity.this).load(fileUri).into(picture3);

    }
}
