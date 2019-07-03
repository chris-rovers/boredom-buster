package com.example.boredombuster;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;

public class Selection extends AppCompatActivity {

  private SQLiteDatabase db = null;
  private final String DB_NAME = "boredomDB";

  private ArrayList<Button> budgetOptions = new ArrayList<>();
  private int budgetIDs[] = {R.id.button_free, R.id.button_low, R.id.button_mid, R.id.button_high};
  private String budgetValue[] = {"free", "low", "mid", "high"};

  private ArrayList<String> mornActivities = new ArrayList<String>();
  private ArrayList<String> mornTerms = new ArrayList<String>();
  private ArrayList<String> aftActivities = new ArrayList<String>();
  private ArrayList<String> aftTerms = new ArrayList<String>();
  private ArrayList<String> eveActivities = new ArrayList<String>();
  private ArrayList<String> eveTerms = new ArrayList<String>();

  Animation animBlink;

  Button btnResult, btnFree, btnLow, btnMid, btnHigh,
          btnMorn, btnAft, btnEve, btnSingle, btnMany;

  public String budgetVal = "";
  public String mornVal = "";
  public String aftVal = "";
  public String eveVal = "";
  public String groupVal = "";

  public boolean free = false;
  public boolean low = false;
  public boolean mid = false;
  public boolean high = false;
  public boolean morning = false;
  public boolean afternoon = false;
  public boolean evening = false;
  public boolean single = false;
  public boolean many = false;

  // public buttons = ["btnFree", "btnLow", "btnMid", "btnHigh"];

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_selection);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
            R.anim.blink);

    btnResult = findViewById(R.id.results_button);
    btnFree = findViewById(R.id.button_free);
    btnLow = findViewById(R.id.button_low);
    btnMid = findViewById(R.id.button_mid);
    btnHigh = findViewById(R.id.button_high);
    btnMorn = findViewById(R.id.button_morning);
    btnAft = findViewById(R.id.button_afternoon);
    btnEve = findViewById(R.id.button_evening);
    btnSingle = findViewById(R.id.button_single);
    btnMany = findViewById(R.id.button_many);

    // add budget buttons to array list for use in BudgetSelect() function.
    budgetOptions.add(btnFree);
    budgetOptions.add(btnLow);
    budgetOptions.add(btnMid);
    budgetOptions.add(btnHigh);

    btnResult.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        GetActivity(); // query the DB for matching activities (budget, time, group size)
        Bundle activitiesBundle = new Bundle(); // send arrays of all results to Results activity
        activitiesBundle.putStringArrayList("mornAs", mornActivities);
        activitiesBundle.putStringArrayList("mornTs", mornTerms);
        activitiesBundle.putStringArrayList("aftAs", aftActivities);
        activitiesBundle.putStringArrayList("aftTs", aftTerms);
        activitiesBundle.putStringArrayList("eveAs", eveActivities);
        activitiesBundle.putStringArrayList("eveTs", eveTerms);
        Intent Results = new Intent(Selection.this, Results.class);
        Results.putExtras(activitiesBundle);
        startActivity(Results);
      }
    });
  } // onCreate()

  public void BudgetSelect(View v) {
    int ID = v.getId();

    for(int i = 0; i < budgetIDs.length; i++)
    {
      if(budgetIDs[i] == ID)
      {
        budgetOptions.get(i).startAnimation(animBlink);
        budgetOptions.get(i).setBackgroundResource(R.drawable.menu_button);
        budgetVal = budgetValue[i];
      }
      else
      {
        budgetOptions.get(i).clearAnimation();
        budgetOptions.get(i).setBackgroundResource(R.drawable.grey_button);
      }
    }
  } // BudgetSelect()

  public void TimeSelect(View v) {

    int ID = v.getId();

    switch(ID) {
      case(R.id.button_morning):
      {
        if(!morning) {
          btnMorn.startAnimation(animBlink);
          btnMorn.setBackgroundResource(R.drawable.menu_button);
          morning = true;
          mornVal = "Morning";
        }
        else {
          btnMorn.setBackgroundResource(R.drawable.grey_button);
          btnMorn.clearAnimation();
          morning = false;
          mornVal = "";
        }
        break;
      } // case(R.id.button_morning)
      case(R.id.button_afternoon):
      {
        if(!afternoon) {
          btnAft.startAnimation(animBlink);
          btnAft.setBackgroundResource(R.drawable.menu_button);
          afternoon = true;
          aftVal = "Afternoon";
        }
        else {
          btnAft.setBackgroundResource(R.drawable.grey_button);
          btnAft.clearAnimation();
          afternoon = false;
          aftVal = "";
        }
        break;
      } // case(R.id.button_afternoon)
      case(R.id.button_evening):
      {
        if(!evening) {
          btnEve.startAnimation(animBlink);
          btnEve.setBackgroundResource(R.drawable.menu_button);
          evening = true;
          eveVal = "Evening";
        }
        else {
          btnEve.setBackgroundResource(R.drawable.grey_button);
          btnEve.clearAnimation();
          evening = false;
          eveVal = "";
        }
        break;
      } // case(R.id.button_evening)
    } // switch
  } // TimeSelect()

  public void GroupSelect(View v) {
    if(v.getId() == R.id.button_single)
    {
      btnSingle.startAnimation(animBlink);
      btnSingle.setBackgroundResource(R.drawable.menu_button);
      btnMany.clearAnimation();
      btnMany.setBackgroundResource(R.drawable.grey_button);
      groupVal = "Single";
    }
    else
    {
      btnMany.startAnimation(animBlink);
      btnMany.setBackgroundResource(R.drawable.menu_button);
      btnSingle.clearAnimation();
      btnSingle.setBackgroundResource(R.drawable.grey_button);
      groupVal = "Many";
    }
  } // GroupSelect()

  public void GetActivity()
  {
    String curActivity;
    String curSearchTerm;
    String mornCheck = "Morning";

    if(mornVal.equals(mornCheck))
    {
      db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
      Cursor c = db.rawQuery("SELECT tbl_activity.activity_name, tbl_activity.search_term " +
              "FROM tbl_activity " +
              "JOIN tbl_activity_time " +
              "ON tbl_activity.activityID = tbl_activity_time.activityID " +
              "JOIN tbl_time " +
              "ON tbl_activity_time.timeID = tbl_time.timeID " +
              "JOIN tbl_activity_group " +
              "ON tbl_activity.activityID = tbl_activity_group.activityID " +
              "JOIN tbl_group " +
              "ON tbl_activity_group.groupID = tbl_group.groupID " +
              "WHERE tbl_activity.activity_price = '" + budgetVal + "' AND tbl_time.time_frame = '" + mornVal + "' AND tbl_group.group_size = '" + groupVal + "'", null);
      if(c != null)
      {
        if(c.moveToFirst())
        {
          do {
            curActivity = c.getString(0);
            curSearchTerm = c.getString(1);
            mornActivities.add(curActivity);
            mornTerms.add(curSearchTerm);
          }while(c.moveToNext());
        }
      }
      db.close();
    } // if Morning
    if(aftVal.equals("Afternoon"))
    {
      db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
      Cursor c = db.rawQuery("SELECT tbl_activity.activity_name, tbl_activity.search_term " +
              "FROM tbl_activity " +
              "JOIN tbl_activity_time " +
              "ON tbl_activity.activityID = tbl_activity_time.activityID " +
              "JOIN tbl_time " +
              "ON tbl_activity_time.timeID = tbl_time.timeID " +
              "JOIN tbl_activity_group " +
              "ON tbl_activity.activityID = tbl_activity_group.activityID " +
              "JOIN tbl_group " +
              "ON tbl_activity_group.groupID = tbl_group.groupID " +
              "WHERE tbl_activity.activity_price = '" + budgetVal + "' AND tbl_time.time_frame = '" + aftVal + "' AND tbl_group.group_size = '" + groupVal + "'", null);
      if(c != null)
      {
        if(c.moveToFirst())
        {
          do {
            curActivity = c.getString(0);
            curSearchTerm = c.getString(1);
            aftActivities.add(curActivity);
            aftTerms.add(curSearchTerm);
          }while(c.moveToNext());
        }
      }
      db.close();
    } // if Afternoon
    if(eveVal.equals("Evening"))
    {
      db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null);
      Cursor c = db.rawQuery("SELECT tbl_activity.activity_name, tbl_activity.search_term " +
              "FROM tbl_activity " +
              "JOIN tbl_activity_time " +
              "ON tbl_activity.activityID = tbl_activity_time.activityID " +
              "JOIN tbl_time " +
              "ON tbl_activity_time.timeID = tbl_time.timeID " +
              "JOIN tbl_activity_group " +
              "ON tbl_activity.activityID = tbl_activity_group.activityID " +
              "JOIN tbl_group " +
              "ON tbl_activity_group.groupID = tbl_group.groupID " +
              "WHERE tbl_activity.activity_price = '" + budgetVal + "' AND tbl_time.time_frame = '" + eveVal + "' AND tbl_group.group_size = '" + groupVal + "'", null);
      if(c != null)
      {
        if(c.moveToFirst())
        {
          do {
            curActivity = c.getString(0);
            curSearchTerm = c.getString(1);
            eveActivities.add(curActivity);
            eveTerms.add(curSearchTerm);
          }while(c.moveToNext());
        }
      }
      db.close();
    } // if Evening
  }

} // class
