package com.example.mani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ShowableListMenu;

import android.app.DatePickerDialog;
import android.media.MediaCodec;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private Button findDob_btn, calculate_btn;
    private TextView today_txt, dob_txt, age_txt;
    String mbirthday, mtoday;
    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        today_txt = findViewById(R.id.datetext);
        dob_txt = findViewById(R.id.dobtext);
        age_txt = findViewById(R.id.agetext);

        findDob_btn = findViewById(R.id.dobtn);
        calculate_btn = findViewById(R.id.calbtn);

        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        mtoday = simpleDateFormat.format(Calendar.getInstance().getTime());
        today_txt.setText("Today :" + mtoday);
        findDob_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), dateSetListener, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                mbirthday = dayOfMonth + "/" + month + "/" + year;
                dob_txt.setText("Birthday" + mbirthday);
            }
        };
        calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mbirthday == null) {
                    Toast.makeText(getApplicationContext(), "please enter your date of birth",
                            Toast.LENGTH_SHORT).show();

                } else {

                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date date1 = simpleDateFormat1.parse(mbirthday);
                        Date date2 = simpleDateFormat1.parse(mtoday);

                        long startDate = date1.getTime();
                        long enddate = date2.getTime();

                        Period period = new Period(startDate, enddate, PeriodType.yearMonthDay());
                        int years = period.getYears();
                        int months = period.getMonths();
                        int days = period.getDays();

                        age_txt.setText(years + "years |" + months + "months |" + days + "days");

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}