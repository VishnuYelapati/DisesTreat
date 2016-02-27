package android.com.autocompletetextview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by srinu on 2/26/2016.
 */
public class WelcomeActivity extends Activity {

    Button welcome_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);

        welcome_next=(Button)findViewById(R.id.btn_welcome_next);
        welcome_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
            }
        });
    }
}
