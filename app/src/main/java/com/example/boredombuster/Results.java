package com.example.boredombuster;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.text.Layout;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Results extends AppCompatActivity {

  private ArrayList<String> mornActivities = new ArrayList<String>();
  private ArrayList<String> mornTerms = new ArrayList<String>();
  private ArrayList<String> aftActivities = new ArrayList<String>();
  private ArrayList<String> aftTerms = new ArrayList<String>();
  private ArrayList<String> eveActivities = new ArrayList<String>();
  private ArrayList<String> eveTerms = new ArrayList<String>();
  private ArrayList<String> activityChecks = new ArrayList<String>();
  private Boolean isFirstActivitySet = false;

  ///////////////////////////
  // Morning activity results strings
  private String mornActOne = "";
  private String mornTermOne = "";
  private String mornActTwo = "";
  private String mornTermTwo = "";
  private String mornActThree = "";
  private String mornTermThree = "";
  //
  private boolean isMornOneSet = false;
  private boolean isMornTwoSet = false;
  private boolean isMornThreeSet = false;

  /////////////////////////////
  // Afternoon activity results strings
  private String aftActOne = "";
  private String aftTermOne = "";
  private String aftActTwo = "";
  private String aftTermTwo = "";
  private String aftActThree = "";
  private String aftTermThree = "";
  //
  private boolean isAftOneSet = false;
  private boolean isAftTwoSet = false;
  private boolean isAftThreeSet = false;

  ///////////////////////////
  // Evening activity results strings
  private String eveActOne = "";
  private String eveTermOne = "";
  private String eveActTwo = "";
  private String eveTermTwo = "";
  private String eveActThree = "";
  private String eveTermThree = "";
  //
  private boolean isEveOneSet = false;
  private boolean isEveTwoSet = false;
  private boolean isEveThreeSet = false;


  String testingImage = "mike.png";

  LinearLayout layoutMornActivityOne, layoutMornActivityTwo, layoutMornActivityThree;

  Button btnMornActivityOne, btnMornActivityTwo, btnMornActivityThree, schMornActivityOne,
          schMornActivityTwo, schMornActivityThree;

  ImageView imgMornActivityOne, imgMornActivityTwo, imgMornActivityThree;

  TextView txtMornActivityOne, txtMornActivityTwo, txtMornActivityThree;

  private ArrayList<LinearLayout> activityViewElements = new ArrayList<>();
  private int activityViewIDs[] = {R.id.morn_result_one, R.id.morn_result_two, R.id.morn_result_three};
  private ArrayList<LinearLayout> expandButtons = new ArrayList<>();
  private int expandButtonIDs[] = {R.id.morn_result_one, R.id.morn_result_two, R.id.morn_result_three};
  private boolean openMornActivityOne = false;

  private int SelectRandNum(int size) {
   int randNum = new Random().nextInt(size);
   return randNum;
  } // SelectRandNum()

  private Boolean CheckDupeActivity(String randActivity) {
    Boolean check = false;
    for(int i = 0; i < activityChecks.size(); i++) {
      if(activityChecks.contains(randActivity))
      {
        check = true;
        break;
      }
    }
    return check;
  } // CheckDupeActivity()

  private void SetMornActivities() {
    int randNum;
    Boolean isDupe = false;
    if(!isMornOneSet) { // select first morning activity
      if(!isFirstActivitySet) // if this is the first activity overall don't check for duplicate
      {
        randNum = SelectRandNum(mornActivities.size());
        mornActOne = mornActivities.get(randNum);
        mornTermOne = mornTerms.get(randNum);
        activityChecks.add(mornActOne);
        isMornOneSet = true;
        isFirstActivitySet = true;
        txtMornActivityOne.setText(mornActOne);
      }
      else // if a first activity has already been set
      {
        do {
          randNum = SelectRandNum(mornActivities.size());
          mornActOne = mornActivities.get(randNum);
          mornTermOne = mornTerms.get(randNum);
          isDupe = CheckDupeActivity(mornActOne);
        }while(isDupe);
        activityChecks.add(mornActOne);
        isMornOneSet = true;
      }
    } // end first morning activity selection
    if(!isMornTwoSet) { // select a second unique morning activity
      do {
        randNum = SelectRandNum(mornActivities.size());
        mornActTwo = mornActivities.get(randNum);
        mornTermTwo = mornTerms.get(randNum);
        isDupe = CheckDupeActivity(mornActTwo);
      }while(isDupe);
      activityChecks.add(mornActTwo);
      isMornTwoSet = true;
    }  // end second morning activity selection
    if(!isMornThreeSet) { // select a third unique morning activity
      do {
        randNum = SelectRandNum(mornActivities.size());
        mornActThree = mornActivities.get(randNum);
        mornTermThree = mornTerms.get(randNum);
        isDupe = CheckDupeActivity(mornActThree);
      }while(isDupe);
      activityChecks.add(mornActThree);
      isMornThreeSet = true;
    } // end third morning activity selection
  } // SetMornActivities()

  private void SetAftActivities() {
    int randNum;
    Boolean isDupe = false;
    if(!isAftOneSet) { // select first afternoon activity
      do {
        randNum = SelectRandNum(aftActivities.size());
        aftActOne = aftActivities.get(randNum);
        aftTermOne = aftTerms.get(randNum);
        isDupe = CheckDupeActivity(aftActOne);
      }while(isDupe);
      activityChecks.add(aftActOne);
      isAftOneSet = true;
      } // end first afternoon activity selection
    if(!isAftTwoSet) { // select second afternoon activity
      do {
        randNum = SelectRandNum(aftActivities.size());
        aftActTwo = aftActivities.get(randNum);
        aftTermTwo = aftTerms.get(randNum);
        isDupe = CheckDupeActivity(aftActTwo);
      }while(isDupe);
      activityChecks.add(aftActTwo);
      isAftTwoSet = true;
    } // end second afternoon activity selection
    if(!isAftThreeSet) { // select third afternoon activity
      do {
        randNum = SelectRandNum(aftActivities.size());
        aftActThree = aftActivities.get(randNum);
        aftTermThree = aftTerms.get(randNum);
        isDupe = CheckDupeActivity(aftActThree);
      }while(isDupe);
      activityChecks.add(aftActThree);
      isAftThreeSet = true;
    } // end third afternoon activity selection
  } // SetAftActivities()

  private void SetEveActivities() {
    int randNum;
    Boolean isDupe = false;
    if(!isEveOneSet) { // select first evening activity
      do {
        randNum = SelectRandNum(eveActivities.size());
        eveActOne = eveActivities.get(randNum);
        eveTermOne = eveTerms.get(randNum);
        isDupe = CheckDupeActivity(eveActOne);
      }while(isDupe);
      activityChecks.add(eveActOne);
      isEveOneSet = true;
    } // end first evening activity selection
    if(!isEveTwoSet) { // select second evening activity
      do {
        randNum = SelectRandNum(eveActivities.size());
        eveActTwo = eveActivities.get(randNum);
        eveTermTwo = eveTerms.get(randNum);
        isDupe = CheckDupeActivity(eveActTwo);
      }while(isDupe);
      activityChecks.add(eveActTwo);
      isEveTwoSet = true;
    } // end second evening activity selection
    if(!isEveThreeSet) { // select third evening activity
      do {
        randNum = SelectRandNum(eveActivities.size());
        eveActThree = eveActivities.get(randNum);
        eveTermThree = eveTerms.get(randNum);
        isDupe = CheckDupeActivity(eveActThree);
      }while(isDupe);
      activityChecks.add(eveActThree);
      isEveThreeSet = true;
    } // end third evening activity selection
  } // SetEveActivities()

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_results);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Bundle activitiesBundle = this.getIntent().getExtras();
    if(activitiesBundle != null) {
      mornActivities = activitiesBundle.getStringArrayList("mornAs");
      mornTerms = activitiesBundle.getStringArrayList("mornTs");
      aftActivities = activitiesBundle.getStringArrayList("aftAs");
      aftTerms = activitiesBundle.getStringArrayList("aftTs");
      eveActivities = activitiesBundle.getStringArrayList("eveAs");
      eveTerms = activitiesBundle.getStringArrayList("eveTs");
    }

    // Morning 1 view elements
    layoutMornActivityOne = findViewById(R.id.morn_result_one); // morning 1 result box
    txtMornActivityOne = findViewById(R.id.morn_text_one); // morning 1 text view
    btnMornActivityOne = findViewById(R.id.morn_exp_button_one); // morning 1 expand button
    imgMornActivityOne = findViewById(R.id.morn_image_one); // morning 1 image
    schMornActivityOne = findViewById(R.id.morn_search_button_one); // morning 1 search button

    // Morning 2 view elements
    layoutMornActivityTwo = findViewById(R.id.morn_result_two); // morning 2 result box
    txtMornActivityTwo = findViewById(R.id.morn_text_two); // morning 2 text view
    btnMornActivityTwo = findViewById(R.id.morn_exp_button_two); // morning 2 expand button
    imgMornActivityTwo = findViewById(R.id.morn_image_two); // morning 2 image
    schMornActivityTwo = findViewById(R.id.morn_search_button_two); // morning 2 search button

    // Morning 3 view elements
    layoutMornActivityThree = findViewById(R.id.morn_result_three); // morning 3 result box
    txtMornActivityThree = findViewById(R.id.morn_text_three); // morning 3 text view
    btnMornActivityThree = findViewById(R.id.morn_exp_button_three); // morning 3 expand button
    imgMornActivityThree = findViewById(R.id.morn_image_three); // morning 3 image
    schMornActivityThree = findViewById(R.id.morn_search_button_three); // morning 3 search button

    // add activity view elements to array list for use in ActivityExpand() function
    activityViewElements.add(layoutMornActivityOne);
    activityViewElements.add(layoutMornActivityTwo);
    activityViewElements.add(layoutMornActivityThree);


    if(mornActivities.size() > 0){
      SetMornActivities();
    }
    if(aftActivities.size() > 0){
      SetAftActivities();
    }
    if(eveActivities.size() > 0){
      SetEveActivities();
    }

  } // onCreate()

  private void ActivityExpand(View v)
  {
    int ID = v.getId();

    for(int i = 0; i < activityViewElements.size(); i++)
    {
      if(activityViewIDs[i] == ID)
      {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
              LinearLayout.LayoutParams.WRAP_CONTENT);
      lp.setMargins(20, 20, 20, 20);
      layoutMornActivityOne.setLayoutParams(lp);

      imgMornActivityOne.setImageDrawable(getDrawable(R.drawable.updated_mike));

      LinearLayout.LayoutParams image = new LinearLayout.LayoutParams(300, 350);
      image.gravity = Gravity.CENTER;
      imgMornActivityOne.setLayoutParams(image);

      schMornActivityOne.setBackground(getDrawable(R.drawable.menu_button));

      LinearLayout.LayoutParams button = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 60);
      button.gravity = Gravity.CENTER;
      button.setMargins(60, 40, 60, 40);
      schMornActivityOne.setLayoutParams(button);

      schMornActivityOne.setText("More Details");
      btnMornActivityOne.setText("-");

      openMornActivityOne = true;
      }
    }

//    if(openMornActivityOne)
//    {
//      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//              120);
//      lp.setMargins(20, 20, 20, 20);
//      layoutMornActivityOne.setLayoutParams(lp);
//      imgMornActivityOne.setImageDrawable(getDrawable(R.drawable.mike));
//      imgMornActivityOne.getLayoutParams().height = 0;
//      imgMornActivityOne.getLayoutParams().width = 0;
//      schMornActivityOne.setBackground(getDrawable(R.drawable.menu_button));
//      schMornActivityOne.getLayoutParams().height = 0;
//      schMornActivityOne.getLayoutParams().width = 0;
//      btnMornActivityOne.setText("+");
//      openMornActivityOne = false;
//    }
//    else {
//      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//              LinearLayout.LayoutParams.WRAP_CONTENT);
//      lp.setMargins(20, 20, 20, 20);
//      layoutMornActivityOne.setLayoutParams(lp);
//
//      imgMornActivityOne.setImageDrawable(getDrawable(R.drawable.updated_mike));
//
//      LinearLayout.LayoutParams image = new LinearLayout.LayoutParams(300, 350);
//      image.gravity = Gravity.CENTER;
//      imgMornActivityOne.setLayoutParams(image);
//
//      schMornActivityOne.setBackground(getDrawable(R.drawable.menu_button));
//
//      LinearLayout.LayoutParams button = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 60);
//      button.gravity = Gravity.CENTER;
//      button.setMargins(60, 40, 60, 40);
//      schMornActivityOne.setLayoutParams(button);
//
//      schMornActivityOne.setText("More Details");
//      btnMornActivityOne.setText("-");
//
//      openMornActivityOne = true;
//    }
  } // ActivityExpand()

} // class
