package algonquin.cst2335.gade0007.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        // initialize variables with each element
//        TextView mytextview = findViewById(R.id.theTextView);
//        Button mybutton = findViewById(R.id.theButton);
//        EditText myedittext = findViewById(R.id.theEditText);
        TextView mytextview = variableBinding.theTextView;
        Button mybutton = variableBinding.theButton;
        EditText myedittext = variableBinding.theEditText;

        // initialize variables for the compound buttons
        CheckBox mycheckbox = variableBinding.checkBox;
        RadioButton myradiobutton = variableBinding.radioButton;
        Switch myswitch1 = variableBinding.switch1;

        // initialize variables for the image elements
        ImageView myimageview = variableBinding.theImageView;
        ImageButton myimagebutton = variableBinding.theImageButton;

        // upon screen rotation, keeps the textview message as the value given in the edittext
        model.editString.observe(this, selected -> {
            mytextview.setText(selected);
        });

        // if the button is pressed, the value entered in the edittext will be placed in the textview, replacing Hello World
        // can survive rotation
        if(mybutton != null) mybutton.setOnClickListener( vw -> {
            model.editString.postValue(myedittext.getText().toString());
            model.editString.observe(this, selected -> {
                mytextview.setText("Your edit text has: " + selected);
            });
        });

        // if the checkbox is selected, all other compound buttons will be selected too, and a toast will appear with the current state of the buttons
        mycheckbox.setOnCheckedChangeListener( (btn, isChecked) -> {
            // save the current state of the button
            model.isSelected.postValue(isChecked);
            // look at what the current state of the button is, and set the other 2 compound buttons to that state
            model.isSelected.observe(this, selected -> {
                myradiobutton.setChecked(selected);
                myswitch1.setChecked(selected);
            });
            // the toast
            Toast toastCB = Toast.makeText(this /* MyActivity */, "The value is now: " + isChecked, Toast.LENGTH_SHORT);
            toastCB.show();
        });
        // if the radiobutton is selected, similar to checkbox, all other compound buttons will be selected
        myradiobutton.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
            model.isSelected.observe(this, selected -> {
                mycheckbox.setChecked(selected);
                myswitch1.setChecked(selected);
            });
            Toast toastRB = Toast.makeText(this /* MyActivity */, "The value is now: " + isChecked, Toast.LENGTH_SHORT);
            toastRB.show();
        });
        // if the switch is selected, every other compound button gets selected
        myswitch1.setOnCheckedChangeListener( (btn, isChecked) -> {
            model.isSelected.postValue(isChecked);
            model.isSelected.observe(this, selected -> {
                myradiobutton.setChecked(selected);
                mycheckbox.setChecked(selected);
            });
            Toast toastS = Toast.makeText(this /* MyActivity */, "The value is now: " + isChecked, Toast.LENGTH_SHORT);
            toastS.show();
        });

        // on click event for the image button; if it's selected, a toast will appear with the dimensions of the image
        myimagebutton.setOnClickListener( vw -> {
            Toast toastIB = Toast.makeText(this /* MyActivity */, "Width = " + myimagebutton.getWidth() + " and Height = " + myimagebutton.getHeight(), Toast.LENGTH_SHORT);
            toastIB.show();
        });
    }
}