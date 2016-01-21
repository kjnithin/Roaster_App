package com.example.nithin.roasterapp;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String name;
    boolean checked;
    String bday;
    String eyeColor;
    String shirtSizeRadio;
    int pantSize;
    int shirtSize;
    int shoeSize;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialValues();
        getData();
        spinnerSelectionChanged();
        radioSelectionChanged();
        seekBarForPantChanged();
        seekBarForShirtChanged();
        seekBarForShoeChanged();
        calendarChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initialValues(){

       //This is for the Name of the person
        EditText et = (EditText)findViewById(R.id.editText_name);
        et.setText(name);
       //This is for the check box
        CheckBox check = (CheckBox)findViewById(R.id.checkBox);
        check.setChecked(checked);

        //This is to initialise the calender
        TextView tv_birthday = (TextView)findViewById(R.id.textView_birthday);
        tv_birthday.setText("Birthday: " + bday);


        // This is for initialize of  Eye color
        Spinner spin = (Spinner)findViewById(R.id.spinner_eyeColor);

        if(eyeColor == "Amber"){
            spin.setSelection(1);
        }
        else if(eyeColor == "Blue"){
            spin.setSelection(2);
        }
        else if(eyeColor == "Brown"){
            spin.setSelection(3);
        }
        else if(eyeColor == "Gray"){
            spin.setSelection(4);
        }
        else if(eyeColor == "Green"){
            spin.setSelection(5);
        }
        else if(eyeColor == "Hazel"){
            spin.setSelection(6);
        }
        else{
            spin.setSelection(0);
        }
        //This is to add the List of Eye color
        Spinner spinner1=(Spinner)findViewById(R.id.spinner_eyeColor);

        List<String> list=new ArrayList<String>();
        list.add("choose Eye Color");
        list.add("Amber");
        list.add("Blue");
        list.add("Brown");
        list.add("Gray");
        list.add("Green");
        list.add("Hazel");

        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item, list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

        //This is for the radio button for the shirt size
        RadioGroup radio=(RadioGroup)findViewById(R.id.radiogroup);
        if(shirtSizeRadio== "XS"){
            radio.check(R.id.radioButton_xs);
        }
        else if(shirtSizeRadio == "S"){
            radio.check(R.id.radioButton_s);
        }
        else if (shirtSizeRadio == "M"){
            radio.check(R.id.radioButton_m);
        }
        else if(shirtSizeRadio == "L"){
            radio.check(R.id.radioButton_l);
        }
        else if(shirtSizeRadio == "XL"){
            radio.check(R.id.radioButton_xl);
        }
        else if(shirtSizeRadio == "XXL"){
            radio.check(R.id.radioButton_xxl);
        }
    }

    //This is for the selecting the birthday
    public void calendarChanged(){

        TextView text = (TextView)findViewById(R.id.button_setBirthday);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        showFormat();
                    }

                };

                new DatePickerDialog(MainActivity.this, date, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    //This is for showing the selected date in the text view
    public void showFormat() {

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        TextView tv = (TextView)findViewById(R.id.textView_birthday);
        tv.setText( "Birthday: " + sdf.format(calendar.getTime()));
        bday = sdf.format(calendar.getTime());
    }


    //This is for selecting the eye color
    public void spinnerSelectionChanged(){
        final Spinner spin = (Spinner)findViewById(R.id.spinner_eyeColor);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    eyeColor = "";
                } else if (position == 1) {
                    eyeColor = "Amber";
                } else if (position == 2) {
                    eyeColor = "Blue";
                } else if (position == 3) {
                    eyeColor = "Brown";
                } else if (position == 4) {
                    eyeColor = "Gray";
                } else if (position == 5) {
                    eyeColor = "Green";
                } else if (position == 6) {
                    eyeColor = "Hazel";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //This is for selecting the shirt size in radio button
    public void radioSelectionChanged(){
        RadioGroup radio = (RadioGroup)findViewById(R.id.radiogroup);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radioButton_xs) {
                    shirtSizeRadio = "XS";
                } else if (checkedId == R.id.radioButton_s) {
                    shirtSizeRadio = "S";
                } else if (checkedId == R.id.radioButton_m) {
                    shirtSizeRadio = "M";
                } else if (checkedId == R.id.radioButton_l) {
                    shirtSizeRadio = "L";
                } else if (checkedId == R.id.radioButton_xl) {
                    shirtSizeRadio = "XL";
                } else if (checkedId == R.id.radioButton_xxl) {
                    shirtSizeRadio = "XXL";
                } else {
                    shirtSizeRadio = "";
                }
            }
        });
    }

    //This is the for selecting the size of pant using seek bar
    public void seekBarForPantChanged() {
        SeekBar seekBar = (SeekBar) findViewById(R.id.pantSize);
        final TextView text = (TextView) findViewById(R.id.textView_pantsSize);
        text.setText(seekBar.getProgress() + "/" + seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                String string = String.valueOf(progressValue);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                text.setText(progress + "/" + seekBar.getMax());
            }
        });
    }
    //This is the for selecting the size of shirt using seek bar
        public void seekBarForShirtChanged() {
        SeekBar seekBar1 = (SeekBar) findViewById(R.id.shirtSize);
        final TextView text1 = (TextView) findViewById(R.id.textView_shirtSize);
        text1.setText(seekBar1.getProgress() + "/" + seekBar1.getMax());
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress;

            @Override
            public void onProgressChanged(SeekBar seekBar1, int progressValue, boolean fromUser) {
                progress = progressValue;
                String string = String.valueOf(progressValue);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar1) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar1) {
                text1.setText(progress + "/" + seekBar1.getMax());
            }
        });
    }

    //This is the for selecting the size of shoes using seek bar
     public void seekBarForShoeChanged(){
        SeekBar seekBar2=(SeekBar)findViewById(R.id.shoeSize);
         seekBar2.setProgress(shoeSize-4);

          final TextView text2 = (TextView)findViewById(R.id.textView_shoeSize);
             text2.setText(String.valueOf(shoeSize));

         text2.setText(seekBar2.getProgress()+"/"+seekBar2.getMax());
        seekBar2.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener(){
            int progress;

            @Override
            public void onProgressChanged(SeekBar seekBar2,int progressValue,boolean fromUser ){

                progress = progressValue+4;
                String string=String.valueOf(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar2){

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar2){
                text2.setText(progress + "/" + seekBar2.getMax());
            }
        });
     }

    public void getData(){
        SharedPreferences pref = getSharedPreferences("myPreferences", MODE_PRIVATE);

        name = pref.getString("personName", "");
        checked = pref.getInt("isChecked", 0)==1;
        eyeColor = pref.getString("eyeColor","");
        shirtSizeRadio = pref.getString("shirtSizeRadio", "");
        pantSize=pref.getInt("pintSize",0);
        shirtSize=pref.getInt("shirtSize",0);
        shoeSize=pref.getInt("shoeSize",0);
        bday = pref.getString("birthday","");

    }

    public void onClick(View view){

        SharedPreferences pref = getSharedPreferences("myPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("personName", name);

        CheckBox check = (CheckBox) findViewById(R.id.checkBox);
        checked=check.isChecked();
        editor.putInt("isChecked", checked ? 1 : 0);
        editor.putString("eyeColor", eyeColor);
        editor.putString("shirtSizeRadio", shirtSizeRadio);
        editor.putInt("pantSize", pantSize);
        editor.putInt("shirtSize", shirtSize);
        editor.putInt("shoeSize",shoeSize);
        editor.putString("birthday", bday);


    }
}
