package com.example.iyashwant.mycolorbuttons;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import org.w3c.dom.Text;

import static android.R.attr.button;
import static android.R.attr.color;

public class MainActivity extends AppCompatActivity {
    int r , g, b ,x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r=getred();
        g=getgreen();
        b=getblue();
        x=1;


        Button red = (Button) findViewById(R.id.red);
        Button green = (Button) findViewById(R.id.green);
        Button blue = (Button) findViewById(R.id.blue);
        Button reset = (Button) findViewById(R.id.reset);
        final TextView redv = (TextView) findViewById(R.id.redv);
        final TextView greenv = (TextView) findViewById(R.id.greenv);
        final TextView bluev = (TextView) findViewById(R.id.bluev);

        redv.setText(Integer.toString(r));
        greenv.setText(Integer.toString(g));
        bluev.setText(Integer.toString(b));

        final RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
        back.setBackgroundColor(Color.rgb(r, g, b));

        red.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                x=spinn();
                r=r+x;

                if (color_change(redv, r) == 1) {

                    redv.setText(Integer.toString(r));
                    back.setBackgroundColor(Color.rgb(r, g, b));
                    showToastMessage("Red Increase", 100);
                }
                else {
                    r = 0;
                    back.setBackgroundColor(Color.rgb(r, g, b));
                }
                savereddaata(r,g,b);

            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                x=spinn();
                g=g+x;
                if(color_change(greenv,g) == 1) {

                    greenv.setText(Integer.toString(g));
                    RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
                    back.setBackgroundColor(Color.rgb(r, g, b));
                    showToastMessage("Green Increase", 100);
                }
                else {
                    g=0;
                    back.setBackgroundColor(Color.rgb(r, g, b));
                }
                savereddaata(r,g,b);

            }
        });


        blue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                x=spinn();
                b=b+x;
                if(color_change(bluev,b)==1) {

                    bluev.setText(Integer.toString(b));
                    RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
                    back.setBackgroundColor(Color.rgb(r, g, b));
                    showToastMessage("Blue Increase", 100);
                }
                else {
                    b=0;
                    back.setBackgroundColor(Color.rgb(r, g, b));
                }
                savereddaata(r,g,b);

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                r = 0;
                g = 0;
                b = 0;

                redv.setText(Integer.toString(r));
                greenv.setText(Integer.toString(g));
                bluev.setText(Integer.toString(b));
                showToastMessage("Reset done!", 1000);

                RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
                back.setBackgroundColor(Color.rgb(r, g, b));
                savereddaata(r,g,b);

            }
        });
    }

    public void showToastMessage(String text, int duration) {
        final Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, duration);
    }

    public int color_change(TextView v, int val) {
        RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
        if (val > 255) {
            val = 0;
            v.setText(Integer.toString(val));
            showToastMessage("Auto Reset done", 2500);

            return 0;
        } else {

            return 1;
        }
    }

    public int spinn()
    {
        int a;
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        String value1 = String.valueOf(spin.getSelectedItem());
        //showToastMessage(value1,1000);
        switch(value1)
        {
            case " (Default) 1": return 1;
            case "           2": return 2;
            case "           4": return 4;
            case "           8": return 8;
            case "          16": return 16;
            case "          32": return 32;
            default: return 1;
        }

    }

    public void savereddaata(int r1, int g1, int b1) {
        SharedPreferences sharedpref = getSharedPreferences("color", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.putInt("red", r1);
        editor.putInt("green", g1);
        editor.putInt("blue", b1);
        editor.commit();

    }
    public  int getred()
    {
        SharedPreferences sharedpref = getSharedPreferences("color", Context.MODE_PRIVATE);
        Integer r2 = sharedpref.getInt("red",0);
        return r2;

    }

    public  int getgreen()
    {
        SharedPreferences sharedpref = getSharedPreferences("color", Context.MODE_PRIVATE);
        Integer g2 = sharedpref.getInt("green",0);
        return g2;

    }

    public  int getblue()
    {
        SharedPreferences sharedpref = getSharedPreferences("color", Context.MODE_PRIVATE);
        Integer b2 = sharedpref.getInt("blue",0);
        return b2;

    }


}

