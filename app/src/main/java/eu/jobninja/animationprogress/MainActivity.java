package eu.jobninja.animationprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import eu.jobninja.dotbarlib.DotBarView;

public class MainActivity extends AppCompatActivity {

    DotBarView dbv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbv = (DotBarView) findViewById(R.id.dbv);
    }


    public void update(View view) {
        int which = Integer.parseInt(view.getTag().toString());
        dbv.setCurrentStatus(which);
    }
}
