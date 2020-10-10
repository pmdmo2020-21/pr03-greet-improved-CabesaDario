package es.iessaladillo.pedrojoya.pr02_greetimproved;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private int times=0, counterMaxName=20, counterMaxSurName = 20;
    private String treatmentButtoms="Mr.";
    private String firstGreet="Hello";
    private String treatment="";
    private String binaryPolite="what's up?";
    String name = "";
    String surname="";
    boolean politeBool=false, premiumBool=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setUpViews();
    }

    //Método que valida, oculta el teclao y muestra el toast

    private void greetings(){
        EditText editName = ActivityCompat.requireViewById(this, R.id.editt1);
        EditText editSurName = ActivityCompat.requireViewById(this, R.id.editt2);
        TextView textBar = ActivityCompat.requireViewById(this, R.id.textBar);
        ProgressBar bar = ActivityCompat.requireViewById(this, R.id.progressbar);
        name=editName.getText().toString();
        surname=editSurName.getText().toString();
        if(surname.length()>0 && name.length()>0 && (times<10 || premiumBool)){
            Toast toast =
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.main_textgreet,firstGreet, treatment, name, surname, binaryPolite),
                            Toast.LENGTH_SHORT);

            toast.show();
            times++;
            bar.setProgress(times);
            textBar.setText(getString(R.string.main_text_bar, times));
        }

        if(editName.length()==0 && editSurName.length() == 0){
            editName.setError("Required");
            editSurName.setError("Required");
            editName.requestFocus();
        }else if(editName.length()==0){
            editName.setError("Required");
            editName.requestFocus();
        }else if(editSurName.length() == 0){
            editSurName.setError("Required");
            editSurName.requestFocus();
        }

        hideSoftKeyboard(getCurrentFocus());

    }


    private void setUpViews(){

        ImageView icono = ActivityCompat.requireViewById(this, R.id.iconperson);
        RadioButton mister = ActivityCompat.requireViewById( this, R.id.senor);
        RadioButton mrs = ActivityCompat.requireViewById( this, R.id.senora);
        RadioButton ms = ActivityCompat.requireViewById(this, R.id.senorita);
        CheckBox check = ActivityCompat.requireViewById(this, R.id.polite);
        SwitchCompat premium = ActivityCompat.requireViewById(this, R.id.swt1);
        ProgressBar bar = ActivityCompat.requireViewById(this, R.id.progressbar);
        Button greetButton = ActivityCompat.requireViewById(this, R.id.button);
        TextView finalGreet = ActivityCompat.requireViewById(this, R.id.textgreet);
        TextView textBar = ActivityCompat.requireViewById(this, R.id.textBar);
        TextView maxName = ActivityCompat.requireViewById(this, R.id.maxName);
        TextView maxSurName = ActivityCompat.requireViewById(this, R.id.maxSurName);
        EditText editName = ActivityCompat.requireViewById(this, R.id.editt1);
        EditText editSurName = ActivityCompat.requireViewById(this, R.id.editt2);
        finalGreet.setText(getString(R.string.main_textgreet,"",
                "", "","", ""));
        textBar.setText(getString(R.string.main_text_bar, times));
        maxName.setText(getResources().getQuantityString(R.plurals.main_max_name, counterMaxName, counterMaxName));
        maxSurName.setText(getResources().getQuantityString(R.plurals.main_max_surname, counterMaxSurName, counterMaxSurName));
        mister.setChecked(true);



        //inicialmente como editName tiene foco:
        maxName.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

        //hacemos que cuando le debos a ok del teclao en apellido, se cierre haga la validacion y el saludo
        editSurName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                greetings();
                return true;
            }

        });



        mister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                icono.setImageDrawable(getDrawable(R.drawable.ic_mr));
                treatmentButtoms="Mr.";
                treatment=treatmentButtoms;
            }
        });

        mrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                icono.setImageDrawable(getDrawable(R.drawable.ic_mrs));
                treatmentButtoms="Mrs.";
                treatment=treatmentButtoms;
            }
        });

        ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                icono.setImageDrawable(getDrawable(R.drawable.ic_ms));
                treatmentButtoms="Ms.";
                treatment=treatmentButtoms;
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(politeBool){
                    politeBool=false;
                    firstGreet="Hello";
                    treatment="";
                    binaryPolite="What's up?";
                }else{
                    politeBool=true;
                    firstGreet="Good morning";
                    treatment=treatmentButtoms;
                    binaryPolite="Pleased to meet you";
                }
            }
        });



        greetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greetings();

            }
        });

        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(premiumBool){
                    premiumBool=false;
                    textBar.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.VISIBLE);
                    times=0;
                    bar.setProgress(times);
                    textBar.setText(getString(R.string.main_text_bar, times));
                }else{
                    premiumBool=true;
                    textBar.setVisibility(View.GONE);
                    bar.setVisibility(View.GONE);
                }
            }


        });

        editName.addTextChangedListener(new TextWatcher() {
            String aux="";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                aux = editName.getText().toString();
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editName.getText().toString().length() < aux.length()){
                    counterMaxName++;
                }else if(editName.getText().toString().length() > aux.length()){
                    counterMaxName--;
                }

                if(editName.length()==0){
                    editName.setError("Required");
                }

                maxName.setText(getResources().getQuantityString(R.plurals.main_max_name, counterMaxName, counterMaxName));

            }
        });

        editSurName.addTextChangedListener(new TextWatcher() {
            String aux="";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                aux = editSurName.getText().toString();
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editSurName.getText().toString().length() < aux.length()){
                    counterMaxSurName++;
                }else if(editSurName.getText().toString().length() > aux.length()){
                    counterMaxSurName--;
                }

                if(editSurName.length()==0){
                    editSurName.setError("Required");
                }



                maxSurName.setText(getResources().getQuantityString(R.plurals.main_max_surname, counterMaxSurName, counterMaxSurName));

            }
        });







    }

    //Método para ocultar el teclado
    public static boolean hideSoftKeyboard(@NonNull View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return false;
    }

    protected void onStart() {
        TextView maxName = ActivityCompat.requireViewById(this, R.id.maxName);
        TextView maxSurName = ActivityCompat.requireViewById(this, R.id.maxSurName);
        EditText editName = ActivityCompat.requireViewById(this, R.id.editt1);
        EditText editSurName = ActivityCompat.requireViewById(this, R.id.editt2);



        super.onStart();
        editName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    maxName.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));


                }else{
                    maxName.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textPrimary));
                }

            }
        });

        editSurName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    maxSurName.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                }else{
                    maxSurName.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.textPrimary));
                }

            }
        });

    }

    @Override
    protected void onStop() {
        EditText editName = ActivityCompat.requireViewById(this, R.id.editt1);
        EditText editSurName = ActivityCompat.requireViewById(this, R.id.editt2);

        super.onStop();
        editName.setOnFocusChangeListener(null);
        editSurName.setOnFocusChangeListener(null);

    }

}