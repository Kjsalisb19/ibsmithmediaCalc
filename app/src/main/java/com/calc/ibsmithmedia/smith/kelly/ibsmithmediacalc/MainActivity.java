package com.calc.ibsmithmedia.smith.kelly.ibsmithmediacalc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.calc.ibsmithmedia.smith.kelly.ibsmithmediacalc.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private View centerLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

      // private View mainLayout = (View) findViewById(R.id.container);

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                   // mainLayout = findViewById(R.id.home);
                    return true;
                case R.id.navigation_Services:
                    mTextMessage.setText(R.string.title_services);
                    //mainLayout = findViewById(R.id.services);
                    return true;
                case R.id.navigation_clients:
                    mTextMessage.setText(R.string.title_clients);
                   // mainLayout = findViewById(R.id.clients);
                    return true;
                case R.id.navigation_newcleint:
                    mTextMessage.setText(R.string.title_newclient);
                    //mainLayout = findViewById(R.id.newClient);
                    return true;
                case R.id.navigation_email:
                    mTextMessage.setText(R.string.title_email);
                   // mainLayout = findViewById(R.id.email);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
