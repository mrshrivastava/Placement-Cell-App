package com.example.k_g;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class past_company_upload_page extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST=1;

    private AppCompatButton mButtonChooseImage;
    private AppCompatButton mButtonUpload;

    private EditText mEditTextDescription;
    private EditText mEditTextLink;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri img;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private EditText mEditTextbatch, mEditTextbranch, mEditTextsem, mEditTextpkg, mEditTextcompany, mEditTexthired;
    private int check=0;
    private int s=0;
    private String mdpimg;
    // private String description,link;
    private String description="No description";
    private String link="no link";

    private String CollegeDatabase;
    private String source;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_company_upload_page);

        CollegeDatabase=getIntent().getStringExtra("database");

        mStorageRef = FirebaseStorage.getInstance().getReference(CollegeDatabase+"_past_company_uploads");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference(CollegeDatabase).child("pastcompanies");

        mButtonChooseImage=findViewById(R.id.addImage);

        mButtonUpload=findViewById(R.id.upload);


        mEditTextbatch=findViewById(R.id.batch);
        mEditTextbranch=findViewById(R.id.branch);
        mEditTextcompany=findViewById(R.id.company);
        mEditTextsem=findViewById(R.id.sem);
        mEditTextpkg=findViewById(R.id.pkg);
        mEditTexthired=findViewById(R.id.hired);
        mEditTextDescription=findViewById(R.id.description);

        mImageView=(ImageView) findViewById(R.id.imageview);




        mButtonChooseImage.setOnClickListener(view -> {
            openFileChooser();
        });
        mButtonUpload.setOnClickListener(view -> {
            if(mEditTextDescription.getText().toString().trim().equalsIgnoreCase("")||
                    mEditTextbranch.getText().toString().trim().equalsIgnoreCase("") ||
                    mEditTextbatch.getText().toString().trim().equalsIgnoreCase("") ||
                    mEditTextcompany.getText().toString().trim().equalsIgnoreCase("") ||
                    mEditTextsem.getText().toString().trim().equalsIgnoreCase("") ||
                    mEditTexthired.getText().toString().trim().equalsIgnoreCase("") ||
                    mEditTextpkg.getText().toString().trim().equalsIgnoreCase(""))
            {
                Toast.makeText(this, "Details Missing", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(check==0)
                {
                    Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ProgressBar bar=findViewById(R.id.progressbar);
                    bar.setVisibility(View.VISIBLE);
                    uploadFile();
                }


            }

        });



    }

    private String getFileExtension(Uri uri){
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {

//        if(check==0)
//        {
//            String url="https://firebasestorage.googleapis.com/v0/b/kiit-app-93db4.appspot.com/o/uploads%2F1681387683530.jpg?alt=media&token=81cd7975-350e-4e1d-8be7-320f2650ef3a";
//            Calendar calender=Calendar.getInstance();
//            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("E, dd MMM yyyy");
//            String pdate=simpleDateFormat.format(calender.getTime());
//            SimpleDateFormat simpletimeFormat=new SimpleDateFormat("HH:mm");
//            String ptime=simpletimeFormat.format(calender.getTime());
//            Uploadmodel uploadmodel=new Uploadmodel(description,mdpimg,url,link,pdate,ptime,source);
//            String uploadId=mDatabaseRef.push().getKey();
//            mDatabaseRef.child(uploadId).setValue(uploadmodel);
//            Thread thread= new Thread(){
//                public void run(){
//                    try{
//                        sleep(3000);
//                    }
//                    catch(Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//            };thread.start();
//            Toast.makeText(past_company_upload_page.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getApplicationContext(),Home.class));
//            finish();
//
//        }
//        else
//        {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(img));
            fileReference.putFile(img)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler=new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                }
                            },500);
                            Toast.makeText(past_company_upload_page.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                            while(!uri.isComplete());
                            Uri url = uri.getResult();
                            Log.d("Hello","this is the link->"+url.toString());
                            Past_Companies_Uploadmodel uploadmodel=new Past_Companies_Uploadmodel(mEditTextbatch.getText().toString().trim(),
                                    mEditTextbranch.getText().toString().trim(),
                                    mEditTextcompany.getText().toString().trim(),
                                    mEditTextDescription.getText().toString().trim(),
                                    mEditTexthired.getText().toString().trim(),
                                    url.toString(),
                                    mEditTextpkg.getText().toString().trim(),
                                    mEditTextsem.getText().toString().trim());
                            String uploadId=mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(uploadmodel);
                            startActivity(new Intent(getApplicationContext(),placement_fragment.class));
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(past_company_upload_page.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

//        }





    }

    private void openFileChooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK
                && data!=null && data.getData()!=null)
        {
            img=data.getData();

            Picasso.get().load(img).into(mImageView);
            check=1;
        }
    }
}