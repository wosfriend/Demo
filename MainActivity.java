package com.example.admin.linechartdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private LineChart chart;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chart = findViewById(R.id.chart1);

        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/.Statuses/");
        file.mkdirs();

        ArrayList<Entry> entries = new ArrayList<>();
       entries.add(new Entry(0, 4));
        entries.add(new Entry(1, 1));
        entries.add(new Entry(2, 2));
        entries.add(new Entry(3, 4));
        entries.add(new Entry(4, 3));
        entries.add(new Entry(5, 1));
        entries.add(new Entry(6, 9));
        entries.add(new Entry(7, 3));
        entries.add(new Entry(8, 4));
        entries.add(new Entry(9, 3));
        entries.add(new Entry(10, 18));
        entries.add(new Entry(11, 2));

        LineDataSet dataSet = new LineDataSet(entries, "Customized values");
        dataSet.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        dataSet.setCircleColor(Color.WHITE);
        dataSet.setLineWidth(1.7f);

        dataSet.setCircleSize(5f);
        dataSet.setColor(Color.WHITE);

        //****
        // Controlling X axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
       // xAxis.setTextSize(10);
        xAxis.setDrawAxisLine(false);
        xAxis.setLabelCount(entries.size(),true);

        // Set the xAxis position to bottom. Default is top
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //Customizing x axis value
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int) value];
            }
        };
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        xAxis.setYOffset(20f);
        //***
        // Controlling right side of y axis
        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setTextColor(Color.WHITE);
        yAxisRight.setEnabled(false);

        //***
        // Controlling left side of y axis
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setGridColor(getResources().getColor(R.color.gray));
        yAxisLeft.setGridLineWidth(1f);
        yAxisLeft.setAxisMinimum(0);

        yAxisLeft.setTextColor(Color.WHITE);
        yAxisLeft.setTextSize(10);
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setGranularity(0.5f);

        // Setting Data
        LineData data = new LineData(dataSet);

        data.setDrawValues(false);
        chart.setData(data);

        chart.getDescription().setEnabled(false);
        chart.setHighlightPerTapEnabled(false);
        chart.setHighlightPerDragEnabled(false);
        chart.setBackgroundColor(getResources().getColor(R.color.colorGreen));
       // chart.setGridBackgroundColor(getResources().getColor(R.color.gray));
        chart.setGridBackgroundColor(Color.WHITE);

        chart.setExtraOffsets(0,0,0,10);
        Legend l = chart.getLegend();

        l.setEnabled(false);
        chart.animateX(1000);
        //refresh
        chart.invalidate();
    }
}
