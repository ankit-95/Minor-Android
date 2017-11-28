package com.example.ankit_pc.cameraex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;


/**
 * Created by ANKIT on 15-11-2017.
 */
public class Process extends Activity {
    static ArrayList<Double> x,y,roots;
    ArrayList<ArrayList<Double>> variable;
    static int a1=0,b1=0,c1=0,d1=0;
    static String path;
    int flag=68;
    double a=0,b=0,d=0,e=0;
    double root1=0,root2=0;
TextView r1,r2,r3;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        r1= (TextView) findViewById(R.id.root1);
        r2= (TextView) findViewById(R.id.root2);
        r3= (TextView) findViewById(R.id.root3);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        x=new ArrayList<>();
        y=new ArrayList<>();

        if(bd != null)
        {
            String s=null;
            try
            {
                 s = (String) bd.get("text");
                 if(s.equals("null"))
                     throw  new Exception();
            }
            catch(Exception e)
            {
                Toast t = Toast.makeText(this,"Can't Proceed",Toast.LENGTH_LONG);
                t.show();
            }
            Log.d("BitmapImage",s);

            char c[] = s.toCharArray();
            for(int i=0;i<s.length();i++)
            {
                System.out.print(c[i]+" ");
            }
            for(int i=0;i<s.length();i++)
            {
                if((c[i]=='x')||(c[i]=='X'))
                {
                    if(c[i+1]=='3')
                    {
                        flag=2;
                        break;
                    }
                    else if(c[i+1]=='2')
                    {
                        flag=0;
                        break;
                    }
                    else
                    {
                        flag=1;
                        continue;
                    }
                }
            }
            if(flag==1)
            {
                String s1[] = s.split("\\+");
                Log.d("Linear Equation",s1[0]+"");
                for(int i=0;i<s1.length;i++)
                {
                    if((s1[i].matches("(.*)x"))||(s1[i].matches("(.*)X"))) {
                        if (s1[i].contains("x"))
                        {       if (s1[i].substring(0, s1[i].indexOf("x")).equals(""))
                                a = 1;
                                else
                                a = Integer.parseInt(s1[i].substring(0, s1[i].indexOf("x")));
                    }   else {
                                if(s1[i].substring(0, s1[i].indexOf("X")).equals(""))
                                a=1;
                                else
                                a = Integer.parseInt(s1[i].substring(0, s1[i].indexOf("X")));
                        }
                    }
                    else  if(s1[i].trim().matches("[0-9]+"))
                    {
                        b=Integer.parseInt(s1[i].trim());
                    }

                }
                System.out.println(" a = "+a+" b = "+b);
                double coeff [] = {b,a};
                double dd=-(b/a);
                Log.d("dd roots",dd+"");
                for(double i=1;i<=10;i++)
                {
                    double temp1=i;
                    x.add(i);
                    double temp=a*i+b;
                    y.add(temp);
                }
                for(int i=0;i<10;i++)
                {
                    System.out.println("mohit "+x.get(i)+" "+y.get(i));
                }
                Log.d("Agree","Calling ");
                    setRoot(x,y,dd);
                //new FunctionOfYOverScatter(coeff);

            }
            else if(flag==0)
            {
                String s1[] = s.split("\\+");
                System.out.println("Quadratic Equation");
                for(int i=0;i<s1.length;i++)
                {

                    if(s1[i].matches("(.*)x2(.*)")||s1[i].matches("(.*)X2(.*)"))
                    {
                        if(s1[i].contains("x2"))
                            if(s1[i].substring(0, s1[i].indexOf("x")).equals(""))
                                a=1;
                            else
                                a=Integer.parseInt(s1[i].substring(0, s1[i].indexOf("x")));
                        else
                            if(s1[i].substring(0, s1[i].indexOf("X")).equals(""))
                                a=1;
                            else
                            a=Integer.parseInt(s1[i].substring(0, s1[i].indexOf("X")));
                    }
                    else if(s1[i].matches("(.*)x")||s1[i].matches("(.*)X"))
                    {
                        if(s1[i].contains("x"))
                            if(s1[i].substring(0, s1[i].indexOf("x")).equals(""))
                                b=1;
                            else
                                b=Integer.parseInt(s1[i].substring(0, s1[i].indexOf("x")));
                        else
                            if(s1[i].substring(0, s1[i].indexOf("X")).equals(""))
                                b=1;
                            else
                                b=Integer.parseInt(s1[i].substring(0, s1[i].indexOf("X")));
                    }
                    if(s1[i].trim().matches("[0-9]+"))
                    {
                        e=Integer.parseInt(s1[i].trim());
                    }
                }

                d = b * b - 4 * a * e;
                if(d > 0)
                {
                    System.out.println("Roots are real and unequal");
                    root1 = ( - b + Math.sqrt(d))/(2*a);
                    root2 = (-b - Math.sqrt(d))/(2*a);
                }
                else if(d == 0)
                {
                    System.out.println("Roots are real and equal");
                    root1 = (-b+Math.sqrt(d))/(2*a);
                    System.out.println("Root:"+root1);
                }
                else
                {
                    System.out.println("Roots are imaginary");
                }
                System.out.println("First Root = "+root1);
                System.out.println("Second Root = "+root2);
                double coeff [] = {e,b,a};
                Log.d("check roots",a+" "+b+" "+e);
                //new FunctionOfYOverScatter(coeff);
                Log.d("Coefficient",coeff+"");
                for(double i=1;i<=10;i++)
                {
                    double temp1=i;
                    x.add(i);
                    double temp=a*i*i+b*i+e;
                    y.add(temp);
                }
                for(int i=0;i<10;i++)
                {
                    System.out.println(x.get(i)+" "+y.get(i));
                }
                setRoot(x,y,root1,root2);
            }
            else{
                //Cubic ADDed by MOHIT
                ArrayList<Integer> factors=new ArrayList<>();

                System.out.println("Cubic Equations");
                String arr[]=s.split("\\+");
                for(int i=0;i<arr.length;i++)
                {
                    if(arr[i].matches("(.*)x3(.*)")||arr[i].matches("(.*)X3(.*)"))
                    {
                        if(arr[i].contains("x3")) {
                            if((arr[i].substring(0, arr[i].indexOf("x"))).equals(""))
                            a1=1;
                            else
                                a1 = Integer.parseInt(arr[i].substring(0, arr[i].indexOf("x")));
                        }
                            else
                        {
                            if((arr[i].substring(0, arr[i].indexOf("X"))).equals(""))
                            a1=1;
                            else
                            a1=Integer.parseInt(arr[i].substring(0, arr[i].indexOf("X")));
                        }
                    }
                    else if(arr[i].matches("(.*)x2(.*)")||arr[i].matches("(.*)X2(.*)"))
                    {
                        if(arr[i].contains("x2")) {
                         if(arr[i].substring(0, arr[i].indexOf("x")).equals(""))
                             b1=1;
                         else
                            b1 = Integer.parseInt(arr[i].substring(0, arr[i].indexOf("x")));
                        }
                        else{
                            if(arr[i].substring(0, arr[i].indexOf("X")).equals(""))
                                b1=1;
                            else
                            b1=Integer.parseInt(arr[i].substring(0, arr[i].indexOf("X")));
                            }
                    }
                    else if(arr[i].matches("(.*)x")||arr[i].matches("(.*)X"))
                    {
                        if(arr[i].contains("x")) {
                            if(arr[i].substring(0, arr[i].indexOf("x")).equals(""))
                                c1=1;
                            else
                            c1 = Integer.parseInt(arr[i].substring(0, arr[i].indexOf("x")));
                        }else
                        {
                            if(arr[i].substring(0, arr[i].indexOf("X")).equals(""))
                                c1=1;
                            else
                            c1=Integer.parseInt(arr[i].substring(0, arr[i].indexOf("X")));
                    }
                    }
                    if(arr[i].trim().matches("[0-9]+"))
                    {
                        d1=Integer.parseInt(arr[i].trim());
                    }
                }
                System.out.println(a1+" "+b1+" "+c1+" "+d1);
                for(int i=1;i<=d1;i++)
                {
                    if(d1%i==0){
                        factors.add(i);
                        factors.add(-i);
                    }
                }
                double rootss[]=new double[3];
                    int k=0;
                for(int i=0;i<factors.size();i++)
                {
                    Log.d("rootttttttt","factors");
                    int x=factors.get(i);

                    int y=a1*x*x*x+b1*x*x+c1*x+d1;

                    if(y==0){
                        rootss[k++]=x;
                        System.out.println("roots mohitititiitiit"+x);
                    }
                }

                for(double i=1;i<=10;i++)
                {
                    double temp1=i;
                    x.add(i);
                    double temp=a1*i*i*i+b1*i*i+c1*i+d1;
                    y.add(temp);
                }
                for(int i=0;i<10;i++)
                {
                    System.out.println("mohit "+x.get(i)+" "+y.get(i));
                }

                setRoot(x,y,rootss);
            }
        }
       }

    public void setRoot(ArrayList<Double> x,ArrayList<Double> y, Double root1){
        Log.d("Agree","Calling ");
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(x.get(0), y.get(0)),
                new DataPoint(x.get(1), y.get(1)),
                new DataPoint(x.get(2), y.get(2)),
                new DataPoint(x.get(3), y.get(3)),
                new DataPoint(x.get(4), y.get(4)),
                new DataPoint(x.get(5), y.get(5)),
                new DataPoint(x.get(6), y.get(6)),
                new DataPoint(x.get(7), y.get(7)),
                new DataPoint(x.get(8), y.get(8)),
                new DataPoint(x.get(9), y.get(9))
        });
        graph.addSeries(series);
        r1.setText("Root is : "+root1+" ");
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getApplicationContext(), "Series1: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-150);
        graph.getViewport().setMaxY(150);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true);
    }

    public void setRoot(ArrayList<Double> x,ArrayList<Double> y, Double root1,double root2){
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(x.get(0), y.get(0)),
                new DataPoint(x.get(1), y.get(1)),
                new DataPoint(x.get(2), y.get(2)),
                new DataPoint(x.get(3), y.get(3)),
                new DataPoint(x.get(4), y.get(4)),
                new DataPoint(x.get(5), y.get(5)),
                new DataPoint(x.get(6), y.get(6)),
                new DataPoint(x.get(7), y.get(7)),
                new DataPoint(x.get(8), y.get(8)),
                new DataPoint(x.get(9), y.get(9))
        });
        graph.addSeries(series);
        r1.setText("Root 1 is : "+root1);
        r2.setText("Root 2 is : "+root2);
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getApplicationContext(), "Series1: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-150);
        graph.getViewport().setMaxY(150);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true);
    }

    public void setRoot(ArrayList<Double> x,ArrayList<Double> y, double root1[]){
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(x.get(0), y.get(0)),
                new DataPoint(x.get(1), y.get(1)),
                new DataPoint(x.get(2), y.get(2)),
                new DataPoint(x.get(3), y.get(3)),
                new DataPoint(x.get(4), y.get(4)),
                new DataPoint(x.get(5), y.get(5)),
                new DataPoint(x.get(6), y.get(6)),
                new DataPoint(x.get(7), y.get(7)),
                new DataPoint(x.get(8), y.get(8)),
                new DataPoint(x.get(9), y.get(9))
        });
        graph.addSeries(series);

        r1.setText("Root 1 is : "+root1[0]);
        r2.setText("Root 2 is : "+root1[1]);
        r3.setText("Root 3 is : "+root1[2]);
        System.out.println("roots mohitititiitiit"+root1[0]);
        System.out.println("roots mohitititiitiit"+root1[1]);
        System.out.println("roots mohitititiitiit"+root1[2]);


        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getApplicationContext(), "Series1: On Data Point clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-150);
        graph.getViewport().setMaxY(150);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(80);

        // enable scaling and scrolling
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true);
    }
    }
