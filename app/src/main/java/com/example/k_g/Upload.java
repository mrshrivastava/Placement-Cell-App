package com.example.k_g;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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

public class Upload extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST=1;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private EditText mEditTextDescription;
    private EditText mEditTextLink;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri img;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private String source;
    private String mdpimg;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        getSupportActionBar().hide();
        source=getIntent().getStringExtra("keyname");
        mdpimg=getIntent().getStringExtra("Imgurl");
        mButtonChooseImage=findViewById(R.id.addImage);
        mButtonUpload=findViewById(R.id.uploadimage);
        mEditTextDescription=findViewById(R.id.description);
        mEditTextLink=findViewById(R.id.link);
        mImageView=(ImageView) findViewById(R.id.imageview);
        mProgressBar=findViewById(R.id.uploadbar);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("kiit");

        mButtonChooseImage.setOnClickListener(view -> {
            openFileChooser();
        });
        mButtonUpload.setOnClickListener(view -> {
            uploadFile();
        });
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile() {
        StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(img));
        fileReference.putFile(img)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setProgress(0);
                            }
                        },500);
                        Toast.makeText(Upload.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                        Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());
                        Uri url = uri.getResult();
                        Calendar calender=Calendar.getInstance();
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("E, dd MMM yyyy");
                        String pdate=simpleDateFormat.format(calender.getTime());
                        SimpleDateFormat simpletimeFormat=new SimpleDateFormat("HH:mm");
                        String ptime=simpletimeFormat.format(calender.getTime());
                        Uploadmodel uploadmodel=new Uploadmodel(mEditTextDescription.getText().toString().trim(),mdpimg
                                ,url.toString(),mEditTextLink.getText().toString(),pdate,ptime,source);
                        String uploadId=mDatabaseRef.push().getKey();
                        mDatabaseRef.child(uploadId).setValue(uploadmodel);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Upload.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress=(100.0*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                        mProgressBar.setProgress((int)progress);
                    }
                });

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
        }
    }
}