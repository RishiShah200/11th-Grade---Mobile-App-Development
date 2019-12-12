package com.example.listviewproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompatExtras;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import static com.example.listviewproject.App.CHANNEL_1_ID;
import static com.example.listviewproject.App.CHANNEL_2_ID;


public class MainActivity extends AppCompatActivity {

    public static final String KEY = "thisisthekey";
    public static final String KEY2 = "thisisthekey2";
    public static final String KEY3 = "thisisthekey3";
    public static final String IMAGEKEY = "thisbetterworkl";
    public static final String moreinfoKEY = "mykeyz";
    public static String url;
    public static int tempPosition;
    public static String name;
    public String tempMoreInfoString = "";
    ListView listView;
    ArrayList<Candidate> list;
    TextView gamesWonText;
    TextView ppgText;
    TextView extraInfo;
    TextView efgText;
    private NotificationManagerCompat notificationManager;

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setTitle("MVP Candidates");

        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);

        gamesWonText = findViewById(R.id.id_gamesWon);
        ppgText = findViewById(R.id.id_ppg);

        efgText = findViewById(R.id.id_efgtext);

        gamesWonText.setTextSize(25);
        gamesWonText.setTextColor(Color.BLACK);

        ppgText.setTextSize(25);
        ppgText.setTextColor(Color.BLACK);

        efgText.setTextSize(25);
        efgText.setTextColor(Color.BLACK);

        listView = findViewById(R.id.id_listview);

        list = new ArrayList<>();
        Candidate candidate1 = new Candidate("Giannis Antetokounmpo", 30.9, 17, R.drawable.giannis, "Giannis Antetokounmpo has a 56.5% field goal percentage and plays 31.7 minutes per game", 60.4, false, "https://www.youtube.com/watch?v=3MevoSBvNkA");
        Candidate candidate2 = new Candidate("James Harden", 38.9, 13, R.drawable.harden, "James Harden has a 43.8% field goal percentage and plays 37.6 minutes per game", 53.7, false, "https://www.youtube.com/watch?v=H6u8-AZ0Wb0");
        Candidate candidate3 = new Candidate("Luka Doncic", 30.6, 13, R.drawable.luka, "Luka Doncic has a 47.8% field goal percentage and plays 33.7 minutes per game,55.4", 55.4, false, "https://www.youtube.com/watch?v=L81XN4_iH2M");
        Candidate candidate4 = new Candidate("LeBron James", 25.7, 17, R.drawable.lebron, "LeBron James has a 49.8% field goal percentage and plays 34.7 minutes per game", 54.6, false, "https://www.youtube.com/watch?v=E467962fHXQ");
        Candidate candidate5 = new Candidate("Anthony Davis", 26.1, 17, R.drawable.anthonydavis, "Anthony Davis has a 49.0% field goal percentage and plays 34.2 minutes per game", 51.9, false, "https://www.youtube.com/watch?v=rmCoPHVaPww");
        Candidate candidate6 = new Candidate("Jimmy Butler", 18.8, 14, R.drawable.jimmy, "Jimmy Butler has a 43.6% field goal percentage and plays 34.1 minutes per game", 47.6, false, "https://www.youtube.com/watch?v=62lrcgTEa6E");
        Candidate candidate7 = new Candidate("Pascal Siakam", 25.6, 15, R.drawable.pascal, "Pascal Siakam has a 50.3% field goal percentage and plays 33.3 minutes per game", 60.9, false, "https://www.youtube.com/watch?v=bXcY9BqvPSU");
        Candidate candidate8 = new Candidate("Karl-Anthony Towns", 25.9, 10, R.drawable.karl, "Karl-Anthony Towns has a 43.9% field goal percentage and plays 31.2 minutes per game", 47.9, false, "https://www.youtube.com/watch?v=-61u32zXr5s");
        Candidate candidate9 = new Candidate("Fred Vanvleet", 18.6, 15, R.drawable.fred, "Fred Vanvleet o has a 45.9% field goal percentage and plays 30.3 minutes per game", 49.6, false, "https://www.youtube.com/watch?v=AAy22CPbxp8");
        Candidate candidate10 = new Candidate("Joel Embiid", 22.8, 14, R.drawable.joel, "Joel Embiid has a 51.5% field goal percentage and plays 34.2 minutes per game", 52.4, false, "https://www.youtube.com/watch?v=5rcib8A7myY");
//extra info is going to be field goal percentage and minutes per game

        list.add(candidate1);
        list.add(candidate2);
        list.add(candidate3);
        list.add(candidate4);
        list.add(candidate5);
        list.add(candidate6);
        list.add(candidate7);
        list.add(candidate8);
        list.add(candidate9);
        list.add(candidate10);

        if (savedInstanceState != null) {
            list = savedInstanceState.getParcelableArrayList("List");
            ppgText.setText(savedInstanceState.getString(KEY));
            gamesWonText.setText(savedInstanceState.getString(KEY2));
            efgText.setText(savedInstanceState.getString(KEY3));
            /*if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && extraInfo.getText().toString().length() > 0) {
                Log.d("BIGBOI", "RETREIVING" + tempMoreInfoString);
                extraInfo.setText(savedInstanceState.getString(moreinfoKEY));
            }   //it is only retrieving if it is in landscape mode. CHANGE THIS make it retrieve in portrait mode*/
        }

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, list);
        listView.setAdapter(customAdapter);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            extraInfo = findViewById(R.id.id_land_extrainfo);
            extraInfo.setMovementMethod(new ScrollingMovementMethod());
            extraInfo.setTextColor(Color.WHITE);

        }




//https://www.basketball-reference.com/friv/mvp.html
        /*share.setOnClickListener(new View.OnClickListener() {       //change this to what is clicked on the listview at the current time
            @Override
            public void onClick(View v) {
                Log.d("RISHI_ERROR",""+tempPosition);
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = list.get(tempPosition).getMoreInfo();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, list.get(tempPosition).getName());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });*/ //MOVE THIS CODE ONTO A BUTTON IN THE NOTIFICATION

        /*listView.setDivider(null);
        listView.setDividerHeight(0);*/

    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("List", list);
        outState.putString(KEY, (String) ppgText.getText());
        outState.putString(KEY2, (String) gamesWonText.getText());
        outState.putString(KEY3, (String) efgText.getText());
        outState.putInt(IMAGEKEY, list.get(tempPosition).getImg());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && extraInfo.getText().toString().length() > 0) {
            Log.d("BIGBOI", "SAVING" + tempMoreInfoString);
            outState.putString(moreinfoKEY, tempMoreInfoString);
        }

    }

    public class Candidate implements Parcelable {

        public final Parcelable.Creator<Candidate> CREATOR = new Parcelable.Creator<Candidate>() {
            public Candidate createFromParcel(Parcel in) {
                return new Candidate(in);
            }

            public Candidate[] newArray(int size) {
                return new Candidate[size];
            }
        };
        private double ppg;
        private String name;
        private int gamesWon;
        private int img;
        private String moreInfo;
        private double efg;
        private boolean imgClicked;
        private String url;

        public Candidate(String name, double ppg, int gamesWon, int img, String moreInfo, double efg, boolean imgClicked, String url) {

            this.ppg = ppg;
            this.name = name;
            this.gamesWon = gamesWon;
            this.img = img;
            this.moreInfo = moreInfo;
            this.efg = efg;
            this.imgClicked = imgClicked;
            this.url = url;

        }

        private Candidate(Parcel in) {
            ppg = in.readDouble();
            name = in.readString();
            gamesWon = in.readInt();
            img = in.readInt();
            moreInfo = in.readString();
            efg = in.readDouble();
            url = in.readString();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeDouble(ppg);
            out.writeString(name);
            out.writeInt(gamesWon);
            out.writeInt(img);
            out.writeString(moreInfo);
            out.writeDouble(efg);
            out.writeString(url);
        }

        public double getPPG() {
            return ppg;
        }

        public String getURL() {
            return url;
        }

        public String getName() {
            return name;
        }

        public int gamesWon() {
            return gamesWon;
        }

        public int getImg() {
            return img;
        }

        public String getMoreInfo() {
            return moreInfo;
        }

        public double getEFG() {
            return efg;
        }

        public boolean getImgClicked() {
            return imgClicked;
        }

        public void setImgClicked() {
            imgClicked = true;
        }

    }

    public class CustomAdapter extends ArrayAdapter<Candidate> {

        ArrayList<Candidate> list;
        Context parentContext;
        int xmlResource;


        public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Candidate> objects) {
            super(context, resource, objects);

            list = objects;
            parentContext = context;
            xmlResource = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            tempPosition = position;


            LayoutInflater layoutInflater = (LayoutInflater) parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);  //sets layout on the screeen
            View view = layoutInflater.inflate(R.layout.adapter_custom, null);

            TextView names = view.findViewById(R.id.id_adapter_name);
            ImageView deleteImage = view.findViewById(R.id.id_adapter_remove_button);       //changes this to an image

            ImageView imageView = view.findViewById(R.id.id_adpater_image);
            imageView.setImageResource(list.get(position).getImg());


            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!(list.get(position).getImgClicked())) {
                        list.get(position).setImgClicked();
                        url = list.get(position).getURL();      //fix this
                        ;
                    }
                    Intent fullPicture = new Intent(MainActivity.this, FullPictureActivity.class);
                    startActivity(fullPicture);
                }
            });

            names.setText(list.get(position).getName());

            view.setOnClickListener(new View.OnClickListener() {        //portrait mode

                @Override
                public void onClick(View v) {
                    gamesWonText.setText(Integer.toString(list.get(position).gamesWon()));
                    ppgText.setText((Double.toString(list.get(position).getPPG())));
                    efgText.setText(Double.toString(list.get(position).getEFG()));
                    name = list.get(position).getName();

                    Intent activityIntent = new Intent(MainActivity.this, MainActivity.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this,
                            0, activityIntent, 0);

                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                  /*  sharingIntent.setType("text/plain");
                    String shareBody = list.get(tempPosition).getMoreInfo();
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, list.get(tempPosition).getName());
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));*/

                    PendingIntent actionIntent = PendingIntent.getBroadcast(MainActivity.this, 0, sharingIntent, 0);

                    Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), list.get(position).getImg());

                    Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_1_ID)
                            .setSmallIcon(list.get(position).getImg())
                            .setContentTitle(list.get(position).getName())
                            .setContentText("Expand to see more statistics")
                            .setLargeIcon(largeIcon)
                            .setStyle(new NotificationCompat.BigTextStyle()
                                    .bigText("has these statistics " + list.get(position).getPPG() + " points per game and " + list.get(position).getEFG() + " is his effective field goal percentage")
                                    .setBigContentTitle(list.get(position).getName()))
                            .setPriority(NotificationCompat.PRIORITY_LOW)
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .setColor(Color.BLUE)
                            .setAutoCancel(true)
                            .setOnlyAlertOnce(true)
                            .setContentIntent(contentIntent)
                            .addAction(R.mipmap.ic_launcher, "Share", actionIntent)
                            .build();
                    notificationManager.notify(1, notification);

                }
            });

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {     //landscape mode
                // extraInfo = findViewById(R.id.id_land_extrainfo);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gamesWonText.setText(Integer.toString(list.get(position).gamesWon()));
                        ppgText.setText(Double.toString(list.get(position).getPPG()));
                        efgText.setText(Double.toString(list.get(position).getEFG()));
                        extraInfo.setText(list.get(position).getMoreInfo());
                        tempMoreInfoString = list.get(position).getMoreInfo();

                        Intent activityIntent = new Intent(MainActivity.this, MainActivity.class);
                        PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this,
                                0, activityIntent, 0);

                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                     /*  sharingIntent.setType("text/plain");
                        String shareBody = list.get(tempPosition).getMoreInfo();
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, list.get(tempPosition).getName());
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));*/

                        PendingIntent actionIntent = PendingIntent.getBroadcast(MainActivity.this, 0, sharingIntent, 0);

                        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), list.get(position).getImg());

                        Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_1_ID)
                                .setSmallIcon(list.get(position).getImg())
                                .setContentTitle(list.get(position).getName())
                                .setContentText("Expand to see more statistics")
                                .setLargeIcon(largeIcon)
                                .setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText("has these statistics " + list.get(position).getPPG() + " points per game and " + list.get(position).getEFG() + " is his effective field goal percentage")
                                        .setBigContentTitle(list.get(position).getName()))
                                .setPriority(NotificationCompat.PRIORITY_LOW)
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .setColor(Color.BLUE)
                                .setAutoCancel(true)
                                .setOnlyAlertOnce(true)
                                .setContentIntent(contentIntent)
                                .addAction(R.mipmap.ic_launcher, "Share", actionIntent)
                                .build();
                        notificationManager.notify(1, notification);
                    }
                });
            }

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });     //change this to an image later

            return view;
        }

    }

}


