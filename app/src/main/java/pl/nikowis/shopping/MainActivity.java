package pl.nikowis.shopping;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.add_button);
        final MainFragment frag = (MainFragment) getFragmentManager().findFragmentById(R.id.main_fragment);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag.showAddPopupEditor();
            }
        });
    }


}
