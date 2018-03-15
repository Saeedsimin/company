package com.example.shehnepours.taxam.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

import com.example.shehnepours.taxam.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by shehnepour.s on 11/12/2017.
 */

public class GraphViewData{


    private static LineGraphSeries<DataPoint> series;
    private int lastX = 0;

    public static void graphDraw(LineChartView lineChartView, String Xlabel, String Ylabel, List<AxisValue> XaxisLabels) {
        // we get graph view instance
        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0,new Random().nextFloat()*5));
        values.add(new PointValue(1, new Random().nextFloat()*5));
        values.add(new PointValue(2, new Random().nextFloat()*6));
        values.add(new PointValue(3, new Random().nextFloat()*6));
        values.add(new PointValue(4, new Random().nextFloat()*6));

        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        List<Line> lines = new ArrayList<Line>();
        line.setColor(Color.rgb(102,153,102));
        line.setStrokeWidth(5);
        line.setShape(ValueShape.CIRCLE);
        line.setPointColor(Color.YELLOW);
        line.setPointRadius(4);
        lines.add(line);




        List<Column> columns = new ArrayList<Column>();

        LineChartData data = new LineChartData();
        data.setLines(lines);
        data.setAxisXBottom(new Axis(XaxisLabels).setName(Xlabel).setTextColor(R.color.main_btn_bck));
        data.setAxisYLeft(new Axis().setName(Ylabel).setTextColor(R.color.main_btn_bck));

        // Set Grid Lines
        data.getAxisXBottom().setHasLines(true);
        data.getAxisYLeft().setHasLines(true);
        lineChartView.setLineChartData(data);
        lineChartView.setViewportCalculationEnabled(false);

        lecho.lib.hellocharts.model.Viewport viewport = lineChartView.getMaximumViewport();
        viewport.bottom = -1;
        viewport.top = 12;
        viewport.left = 0;
        viewport.right = 5;
//        lineChartView.startDataAnimation();
//        lineChartView.setMaximumViewport(viewport);
//        prepareDataAnimation(data);
        lineChartView.animate();
        lineChartView.startDataAnimation(1000);
        lineChartView.setCurrentViewportWithAnimation(viewport);
//        series = new LineGraphSeries<DataPoint>(new DataPoint[]{
//                new DataPoint(new Random().nextDouble(), new Random().nextDouble()),
//                new DataPoint(0.3, 0.5),
//                new DataPoint(0.4, 0.2),
//                new DataPoint(0.5, 0.6),
//                new DataPoint(0.6, 0.1),
//                new DataPoint(0.7, 0.7)});
//        // data
//        graph.addSeries(series);
//        series.setThickness(5);
//        series.setColor(Color.WHITE);
//        series.setAnimated(true);
//        series.setBackgroundColor(Color.CYAN);
//        series.setDrawDataPoints(true);
//        series.setDataPointsRadius(10);
//        series.setDrawBackground(false);
//        series.setDrawAsPath(true);
//
//        // customize a little bit viewport
//        Viewport viewport = graph.getViewport();
//        viewport.setYAxisBoundsManual(true);
//        viewport.setBorderColor(Color.WHITE);
//        viewport.setMinY(0);
//        viewport.setMaxY(10);
//        viewport.setScrollable(true);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        // we're going to simulate real time with thread that append data to the graph
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                // we add 100 new entries
//                for (int i = 0; i < 100; i++) {
//                    runOnUiThread(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            addEntry();
//                        }
//                    });
//
//                    // sleep to slow down the add of entries
//                    try {
//                        Thread.sleep(600);
//                    } catch (InterruptedException e) {
//                        // manage error ...
//                    }
//                }
//            }
//        }).start();
//    }
//
//    // add random data to graph
//    private void addEntry() {
//        // here, we choose to display max 10 points on the viewport and we scroll to end
//        series.appendData(new DataPoint(lastX++, new Random().nextDouble() * 10d), true, 10);
//    }
    private static void prepareDataAnimation(LineChartData data) {
        for (Line line : data.getLines()) {
            for (PointValue value : line.getValues()) {
                // Here I modify target only for Y values but it is OK to modify X targets as well.
                value.setTarget(value.getX(), (float) Math.random() * 10);
            }
        }
}


}
