package algonquin.cst2335.gade0007;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// hi :)
// hello :)
// BONJOUR!!!!!!
public class MainActivity extends AppCompatActivity {

    // Same as public static void main (String[] args)...
    @Override   // aka its the starting point of the app
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this is the only function! this loads stuff onto the screen
        setContentView(R.layout.activity_main);
    }
}