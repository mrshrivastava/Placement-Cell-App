package com.example.k_g;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    ImageView image;
    Animation u_anim,b_anim;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        text=findViewById(R.id.textView);
        image=findViewById(R.id.imageView);
        u_anim= AnimationUtils.loadAnimation(this,R.anim.upper_animation);
        b_anim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image.setAnimation(u_anim);
        text.setAnimation(b_anim);

        Thread thread= new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{

                    Intent intent=new Intent(MainActivity.this,SignIn.class);
                    startActivity(intent);
                    finish();
                }
            }
        };thread.start();



    }



}