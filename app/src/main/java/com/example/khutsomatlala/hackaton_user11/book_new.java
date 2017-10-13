package com.example.khutsomatlala.hackaton_user11;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by Admin on 9/15/2017.
 */

public class book_new extends AppCompatActivity {


    private int mYear;
    private int mMonth;
    private int mDay;

    private static int HOUR_PRICE = 20;

/*    private TextView mDateDisplay, mPrice;
    private Button mPickDate;
    EditText mNumberHours;*/

    int hours;
    static final int DATE_DIALOG_ID = 0;

    String pic, date1, name ,pricee;

    ImageView BookPic;

    Boolean Checked = true;
    TextView placeName,price;

    //Time and date picker
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private TextView text;
    private TextView textout;
    private Button btn_date;
    private Button btn_time;
    private Button btn_time_out;

    //validate
    int date = 0;
    int month = 0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent i = getIntent();
        pic = i.getStringExtra("pic");
        name = i.getStringExtra("name");
        pricee = i.getStringExtra("price");

       /* mDateDisplay = (TextView) findViewById(R.id.showMyDate);
        mPickDate = (Button) findViewById(R.id.myDatePickerButton);
        mNumberHours = (EditText) findViewById(R.id.EdtNumberHours);
        mPrice = (TextView) findViewById(R.id.txtPrice);*/

        BookPic = (ImageView) findViewById(R.id.BookPic);
        placeName = (TextView) findViewById(R.id.txtPlace_name);
        price = (TextView) findViewById(R.id.txtPrice);

        // mPickDate.setOnClickListener(new View.OnClickListener() {
     /*       public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);*/

        Glide.with(this)
                .load(pic)
                //  .override(300, 200)
                .centerCrop()
                .into(BookPic);

        // display the current date
        // updateDisplay();


        placeName.setText(name);
price.setText("    R" +pricee);

        //date and time picker
        text = (TextView) findViewById(R.id.txt_TextDateTime);
        textout = (TextView) findViewById(R.id.txt_TextDateTimeOut);
        btn_date = (Button) findViewById(R.id.btn_datePicker);
        btn_time = (Button) findViewById(R.id.btn_timePicker);
        btn_time_out = (Button) findViewById(R.id.btn_timePickerOut);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });
        btn_time_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTimeOut();
            }
        });

        updateTextLabel();

    }


    //Date and time picker

    private void updateDate() {
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void updateTime() {
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    private void updateTimeOut() {
        new TimePickerDialog(this, out, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            month = Calendar.getInstance().get(Calendar.MONTH);
            if (month < monthOfYear || month == monthOfYear) {
                dateTime.set(Calendar.YEAR, year);
                dateTime.set(Calendar.MONTH, monthOfYear);
                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateTextLabel();
            } else {
                Toast.makeText(book_new.this, " Incorrect month", Toast.LENGTH_SHORT).show();
            }

            if (monthOfYear < dayOfMonth || month == dayOfMonth) {
                dateTime.set(Calendar.YEAR, year);
                dateTime.set(Calendar.MONTH, monthOfYear);
                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateTextLabel();
            } else {
                Toast.makeText(book_new.this, "Enter valid date", Toast.LENGTH_SHORT).show();
            }
        }
    };


    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateTime.set(Calendar.MINUTE, minute);
            date = hourOfDay;
            updateTextLabel();

        }

    };


    TimePickerDialog.OnTimeSetListener out = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hours, int mins) {
            dateTime.set(Calendar.HOUR_OF_DAY, hours);
            dateTime.set(Calendar.MINUTE, mins);
            if (date < hours) {
                updateTimePicker();
            } else {
                Toast.makeText(book_new.this, "Re enter time " + date + " " + hours, Toast.LENGTH_SHORT).show();
            }

        }
    };

    private void updateTextLabel() {
        text.setText(formatDateTime.format(dateTime.getTime()));

    }

    private void updateTimePicker() {

        textout.setText(formatDateTime.format(dateTime.getTime()));


    }







  /*  private void updateDisplay() {
        this.mDateDisplay.setText(
                new StringBuilder()

                        // Month is 0 based so add 1
                        .append(mDay).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mYear).append(" "));


    }*/

  /*  private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();


                }
            };*/


 /*   @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }*/

/*

    public void calPrice(View view) {




        if (mNumberHours.length() > 0) {

            hours = Integer.parseInt(mNumberHours.getText().toString().trim());


            if (hours <= 24) {
                mPrice.setText("R" + hours * HOUR_PRICE);

                Checked = false;

            } else

            {
                Toast.makeText(this, "Hours must be less than 24 hours", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, " Field required", Toast.LENGTH_SHORT).show();
        }

    }
*/


    public void email(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); //email apps will handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, " Space booking ");
        intent.putExtra(Intent.EXTRA_TEXT, "Booking information");
        startActivity(intent);

    }
}

