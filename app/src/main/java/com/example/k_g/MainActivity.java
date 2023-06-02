package com.example.k_g;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;




import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    CircleImageView image;
    Animation u_anim,b_anim;

    private String mail,name,dp,user,database;
    private int status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //text=findViewById(R.id.textView);
        image=findViewById(R.id.imageView);
        u_anim= AnimationUtils.loadAnimation(this,R.anim.upper_animation);
        b_anim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image.setAnimation(u_anim);
       // text.setAnimation(b_anim);

        User_Database db=new User_Database(this);
        Cursor cursor=db.readAllData();
        while(cursor.moveToNext())
        {
            mail=cursor.getString(1);
            name=cursor.getString(2);
            dp=cursor.getString(3);
            user=cursor.getString(4);
            database=cursor.getString(5);
            status=cursor.getInt(6);
        }

        Thread thread= new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    if(status==1)
                    {
                        Intent intent=new Intent(MainActivity.this,MainPage.class);
                        intent.putExtra("mail",mail);
                        intent.putExtra("name",name);
                        intent.putExtra("dp",dp);
                        intent.putExtra("user",user);
                        intent.putExtra("database",database);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Intent intent=new Intent(MainActivity.this,SignIn.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        };thread.start();



    }



}