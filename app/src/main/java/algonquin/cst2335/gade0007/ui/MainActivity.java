package algonquin.cst2335.gade0007.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.gade0007.data.MainViewModel;
import algonquin.cst2335.gade0007.databinding.ActivityMainBinding;

// hi :)
// hello :)
// BONJOUR!!!!!!
public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    // Same as public static void main (String[] args)...
    @Override   // aka its the starting point of the app
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        // this is the only function! this loads stuff onto the screen
//        setContentView(R.layout.activity_main);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());


//        TextView mytextview = findViewById(R.id.theTextView);
//        Button mybutton = findViewById(R.id.theButton);
//        EditText myedittext = findViewById(R.id.theEditText);
        TextView mytextview = variableBinding.theTextView;
        Button mybutton = variableBinding.theButton;
        EditText myedittext = variableBinding.theEditText;

        model.editString.observe(this, s -> {
            mytextview.setText(s);
        });

        if(mybutton != null) mybutton.setOnClickListener( vw -> {
            model.editString.postValue(myedittext.getText().toString());
            model.editString.observe(this, s -> {
                mytextview.setText("Your edit text has: " + s);
            });
        });
    }
}