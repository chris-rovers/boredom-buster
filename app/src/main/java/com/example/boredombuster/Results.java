package com.example.boredombuster;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.app.SearchManager;

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

  // ArrayLists of activities (passed from Selection.java putExtras) used by the SetMornActivities(), SetAftActivities() and SetEveActivities()
  private ArrayList<String> mornActivities = new ArrayList<String>();
  private ArrayList<String> mornTerms = new ArrayList<String>();
  private ArrayList<String> aftActivities = new ArrayList<String>();
  private ArrayList<String> aftTerms = new ArrayList<String>();
  private ArrayList<String> eveActivities = new ArrayList<String>();
  private ArrayList<String> eveTerms = new ArrayList<String>();
  private ArrayList<String> activityChecks = new ArrayList<String>();
  private Boolean isFirstActivitySet = false;

  ///////////////////////////
  // Morning activity results strings and bools
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
  // Afternoon activity results strings and bools
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
  // Evening activity results strings and bools
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

  // Declare LinearLayout results elements
  LinearLayout layoutMornActivityOne, layoutMornActivityTwo, layoutMornActivityThree,
          layoutAftActivityOne, layoutAftActivityTwo, layoutAftActivityThree,
          layoutEveActivityOne, layoutEveActivityTwo, layoutEveActivityThree,
          layoutMornParent, layoutAftParent, layoutEveParent;
  // Declare TextView elements
  TextView txtMornActivityOne, txtMornActivityTwo, txtMornActivityThree,
          txtAftActivityOne, txtAftActivityTwo, txtAftActivityThree,
          txtEveActivityOne, txtEveActivityTwo, txtEveActivityThree,
          txtTryAgain;
  // Declare expand button elements
  Button btnMornActivityOne, btnMornActivityTwo, btnMornActivityThree, schMornActivityOne, schMornActivityTwo, schMornActivityThree,
          btnAftActivityOne, btnAftActivityTwo, btnAftActivityThree, schAftActivityOne, schAftActivityTwo, schAftActivityThree,
          btnEveActivityOne, btnEveActivityTwo, btnEveActivityThree, schEveActivityOne, schEveActivityTwo, schEveActivityThree,
          btnReturnHome, btnReturnSelection;
  // Declare ImageView elements
  ImageView imgMornActivityOne, imgMornActivityTwo, imgMornActivityThree,
          imgAftActivityOne, imgAftActivityTwo, imgAftActivityThree,
          imgEveActivityOne, imgEveActivityTwo, imgEveActivityThree;

  // ArrayLists of elements used in the ExpandActivity() function
  private ArrayList<LinearLayout> resultsElements = new ArrayList<>();
  private ArrayList<Button> expandButtons = new ArrayList<>();
//  private ArrayList<ImageView> imageElements = new ArrayList<>();
  private ArrayList<Button> searchButtons = new ArrayList<>();

  private ArrayList<Boolean> isExpanded = new ArrayList<>();

  private int expandButtonIDs[] = {R.id.morn_exp_button_one, R.id.morn_exp_button_two, R.id.morn_exp_button_three,
          R.id.aft_exp_button_one, R.id.aft_exp_button_two, R.id.aft_exp_button_three,
          R.id.eve_exp_button_one, R.id.eve_exp_button_two, R.id.eve_exp_button_three};

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
      else // if a first activity has already been set, select unique first morning activity
      {
        do {
          randNum = SelectRandNum(mornActivities.size());
          mornActOne = mornActivities.get(randNum);
          mornTermOne = mornTerms.get(randNum);
          isDupe = CheckDupeActivity(mornActOne);
        }while(isDupe);
        activityChecks.add(mornActOne);
        isMornOneSet = true;
        txtMornActivityOne.setText(mornActOne);
      }
    } // end first morning activity selection
    if(!isMornTwoSet) { // select unique second morning activity
      do {
        randNum = SelectRandNum(mornActivities.size());
        mornActTwo = mornActivities.get(randNum);
        mornTermTwo = mornTerms.get(randNum);
        isDupe = CheckDupeActivity(mornActTwo);
        txtMornActivityTwo.setText(mornActTwo);
      }while(isDupe);
      activityChecks.add(mornActTwo);
      isMornTwoSet = true;
    }  // end second morning activity selection
    if(!isMornThreeSet) { // select unique third morning activity
      do {
        randNum = SelectRandNum(mornActivities.size());
        mornActThree = mornActivities.get(randNum);
        mornTermThree = mornTerms.get(randNum);
        isDupe = CheckDupeActivity(mornActThree);
        txtMornActivityThree.setText(mornActThree);
      }while(isDupe);
      activityChecks.add(mornActThree);
      isMornThreeSet = true;
    } // end third morning activity selection
  } // SetMornActivities()

  private void SetAftActivities() {
    int randNum;
    Boolean isDupe = false;
    if(!isAftOneSet) { // select unique first afternoon activity
      do {
        randNum = SelectRandNum(aftActivities.size());
        aftActOne = aftActivities.get(randNum);
        aftTermOne = aftTerms.get(randNum);
        isDupe = CheckDupeActivity(aftActOne);
        txtAftActivityOne.setText(aftActOne);
      }while(isDupe);
      activityChecks.add(aftActOne);
      isAftOneSet = true;
      } // end first afternoon activity selection
    if(!isAftTwoSet) { // select unique second afternoon activity
      do {
        randNum = SelectRandNum(aftActivities.size());
        aftActTwo = aftActivities.get(randNum);
        aftTermTwo = aftTerms.get(randNum);
        isDupe = CheckDupeActivity(aftActTwo);
        txtAftActivityTwo.setText(aftActTwo);
      }while(isDupe);
      activityChecks.add(aftActTwo);
      isAftTwoSet = true;
    } // end second afternoon activity selection
    if(!isAftThreeSet) { // select unique third afternoon activity
      do {
        randNum = SelectRandNum(aftActivities.size());
        aftActThree = aftActivities.get(randNum);
        aftTermThree = aftTerms.get(randNum);
        isDupe = CheckDupeActivity(aftActThree);
        txtAftActivityThree.setText(aftActThree);
      }while(isDupe);
      activityChecks.add(aftActThree);
      isAftThreeSet = true;
    } // end third afternoon activity selection
  } // SetAftActivities()

  private void SetEveActivities() {
    int randNum;
    Boolean isDupe = false;
    if(!isEveOneSet) { // select unique first evening activity
      do {
        randNum = SelectRandNum(eveActivities.size());
        eveActOne = eveActivities.get(randNum);
        eveTermOne = eveTerms.get(randNum);
        isDupe = CheckDupeActivity(eveActOne);
        txtEveActivityOne.setText(eveActOne);
      }while(isDupe);
      activityChecks.add(eveActOne);
      isEveOneSet = true;
    } // end first evening activity selection
    if(!isEveTwoSet) { // select unique second evening activity
      do {
        randNum = SelectRandNum(eveActivities.size());
        eveActTwo = eveActivities.get(randNum);
        eveTermTwo = eveTerms.get(randNum);
        isDupe = CheckDupeActivity(eveActTwo);
        txtEveActivityTwo.setText(eveActTwo);
      }while(isDupe);
      activityChecks.add(eveActTwo);
      isEveTwoSet = true;
    } // end second evening activity selection
    if(!isEveThreeSet) { // select unique third evening activity
      do {
        randNum = SelectRandNum(eveActivities.size());
        eveActThree = eveActivities.get(randNum);
        eveTermThree = eveTerms.get(randNum);
        isDupe = CheckDupeActivity(eveActThree);
        txtEveActivityThree.setText(eveActThree);
      }while(isDupe);
      activityChecks.add(eveActThree);
      isEveThreeSet = true;
    } // end third evening activity selection
  } // SetEveActivities()

  public void DisplayMorningActivities()
  {
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);
    layoutMornParent.setLayoutParams(lp);
    SetMornActivities();
  } // DisplayMorningActivities()

  public void DisplayAfternoonActivities()
  {
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);
    layoutAftParent.setLayoutParams(lp);
    SetAftActivities();
  } // DisplayAfternoonActivities()

  public void DisplayEveningActivities()
  {
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);
    layoutEveParent.setLayoutParams(lp);
    SetEveActivities();
  } // DisplayEveningActivities()

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

    /////////////////////////////////////////
    ////////// DEFINE ALL ELEMENTS //////////
    /////////////////////////////////////////
    // MORNING ELEMENTS
    layoutMornParent = findViewById(R.id.layout_morning);
    // Morning 1 elements
    layoutMornActivityOne = findViewById(R.id.morn_result_one); // morning 1 result box
    txtMornActivityOne = findViewById(R.id.morn_text_one); // morning 1 text view
    btnMornActivityOne = findViewById(R.id.morn_exp_button_one); // morning 1 expand button
//    imgMornActivityOne = findViewById(R.id.morn_image_one); // morning 1 image
    schMornActivityOne = findViewById(R.id.morn_search_button_one); // morning 1 search button

    // Morning 2 elements
    layoutMornActivityTwo = findViewById(R.id.morn_result_two); // morning 2 result box
    txtMornActivityTwo = findViewById(R.id.morn_text_two); // morning 2 text view
    btnMornActivityTwo = findViewById(R.id.morn_exp_button_two); // morning 2 expand button
//    imgMornActivityTwo = findViewById(R.id.morn_image_two); // morning 2 image
    schMornActivityTwo = findViewById(R.id.morn_search_button_two); // morning 2 search button

    // Morning 3 elements
    layoutMornActivityThree = findViewById(R.id.morn_result_three); // morning 3 result box
    txtMornActivityThree = findViewById(R.id.morn_text_three); // morning 3 text view
    btnMornActivityThree = findViewById(R.id.morn_exp_button_three); // morning 3 expand button
//    imgMornActivityThree = findViewById(R.id.morn_image_three); // morning 3 image
    schMornActivityThree = findViewById(R.id.morn_search_button_three); // morning 3 search button

    ////////// AFTERNOON ELEMENTS //////////
    layoutAftParent = findViewById(R.id.layout_afternoon);
    // Afternoon 1 elements
    layoutAftActivityOne = findViewById(R.id.aft_result_one); // afternoon 1 result box
    txtAftActivityOne = findViewById(R.id.aft_text_one); // afternoon 1 text view
    btnAftActivityOne = findViewById(R.id.aft_exp_button_one); // afternoon 1 expand button
//    imgAftActivityOne = findViewById(R.id.aft_image_one); // afternoon 1 image
    schAftActivityOne = findViewById(R.id.aft_search_button_one); // afternoon 1 search button

    // Afternoon 2 elements
    layoutAftActivityTwo = findViewById(R.id.aft_result_two); // afternoon 2 result box
    txtAftActivityTwo = findViewById(R.id.aft_text_two); // afternoon 2 text view
    btnAftActivityTwo = findViewById(R.id.aft_exp_button_two); // afternoon 2 expand button
//    imgAftActivityTwo = findViewById(R.id.aft_image_two); // afternoon 2 image
    schAftActivityTwo = findViewById(R.id.aft_search_button_two); // afternoon 2 search button

    // Afternoon 3 elements
    layoutAftActivityThree = findViewById(R.id.aft_result_three); // afternoon 3 result box
    txtAftActivityThree = findViewById(R.id.aft_text_three); // afternoon 3 text view
    btnAftActivityThree = findViewById(R.id.aft_exp_button_three); // afternoon 3 expand button
//    imgAftActivityThree = findViewById(R.id.aft_image_three); // afternoon 3 image
    schAftActivityThree = findViewById(R.id.aft_search_button_three); // afternoon 3 search button

    ////////// EVENING ELEMENTS //////////
    layoutEveParent = findViewById(R.id.layout_evening);
    // Evening 1 elements
    layoutEveActivityOne = findViewById(R.id.eve_result_one); // afternoon 1 result box
    txtEveActivityOne = findViewById(R.id.eve_text_one); // afternoon 1 text view
    btnEveActivityOne = findViewById(R.id.eve_exp_button_one); // afternoon 1 expand button
//    imgEveActivityOne = findViewById(R.id.eve_image_one); // afternoon 1 image
    schEveActivityOne = findViewById(R.id.eve_search_button_one); // afternoon 1 search button

    // Evening 2 elements
    layoutEveActivityTwo = findViewById(R.id.eve_result_two); // afternoon 2 result box
    txtEveActivityTwo = findViewById(R.id.eve_text_two); // afternoon 2 text view
    btnEveActivityTwo = findViewById(R.id.eve_exp_button_two); // afternoon 2 expand button
//    imgEveActivityTwo = findViewById(R.id.eve_image_two); // afternoon 2 image
    schEveActivityTwo = findViewById(R.id.eve_search_button_two); // afternoon 2 search button

    // Evening 3 elements
    layoutEveActivityThree = findViewById(R.id.eve_result_three); // afternoon 3 result box
    txtEveActivityThree = findViewById(R.id.eve_text_three); // afternoon 3 text view
    btnEveActivityThree = findViewById(R.id.eve_exp_button_three); // afternoon 3 expand button
//    imgEveActivityThree = findViewById(R.id.eve_image_three); // afternoon 3 image
    schEveActivityThree = findViewById(R.id.eve_search_button_three); // afternoon 3 search button

    // Return buttons
    txtTryAgain = findViewById(R.id.try_again_text);
    btnReturnHome = findViewById(R.id.return_home);
    btnReturnSelection = findViewById(R.id.return_selection);

    // add activity view elements to ArrayLists for use in ExpandActivity() function
    // Morning elements ////////////////
    resultsElements.add(layoutMornActivityOne);
    resultsElements.add(layoutMornActivityTwo);
    resultsElements.add(layoutMornActivityThree);
    expandButtons.add(btnMornActivityOne);
    expandButtons.add(btnMornActivityTwo);
    expandButtons.add(btnMornActivityThree);
//    imageElements.add(imgMornActivityOne);
//    imageElements.add(imgMornActivityTwo);
//    imageElements.add(imgMornActivityThree);
    searchButtons.add(schMornActivityOne);
    searchButtons.add(schMornActivityTwo);
    searchButtons.add(schMornActivityThree);
    // Afternoon elements ////////////////
    resultsElements.add(layoutAftActivityOne);
    resultsElements.add(layoutAftActivityTwo);
    resultsElements.add(layoutAftActivityThree);
    expandButtons.add(btnAftActivityOne);
    expandButtons.add(btnAftActivityTwo);
    expandButtons.add(btnAftActivityThree);
//    imageElements.add(imgAftActivityOne);
//    imageElements.add(imgAftActivityTwo);
//    imageElements.add(imgAftActivityThree);
    searchButtons.add(schAftActivityOne);
    searchButtons.add(schAftActivityTwo);
    searchButtons.add(schAftActivityThree);
    // Evening elements ////////////////
    resultsElements.add(layoutEveActivityOne);
    resultsElements.add(layoutEveActivityTwo);
    resultsElements.add(layoutEveActivityThree);
    expandButtons.add(btnEveActivityOne);
    expandButtons.add(btnEveActivityTwo);
    expandButtons.add(btnEveActivityThree);
//    imageElements.add(imgEveActivityOne);
//    imageElements.add(imgEveActivityTwo);
//    imageElements.add(imgEveActivityThree);
    searchButtons.add(schEveActivityOne);
    searchButtons.add(schEveActivityTwo);
    searchButtons.add(schEveActivityThree);

    // initialize tracking of which results views have been expanded
    for(int i = 0; i < resultsElements.size(); i++)
    {
      isExpanded.add(false);
    }

    btnReturnHome.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent MainActivity = new Intent(Results.this,
                MainActivity.class);
        startActivity(MainActivity);
      }
    });

    btnReturnSelection.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent Selection = new Intent(Results.this,
                Selection.class);
        startActivity(Selection);
      }
    });

    // make sure there are items in the activities ArrayLists before running Set...Activities() functions
    if(mornActivities.size() > 0){
      RemoveErrorMessage();
      DisplayMorningActivities();
    }
    if(aftActivities.size() > 0){
      RemoveErrorMessage();
      DisplayAfternoonActivities();
    }
    if(eveActivities.size() > 0){
      RemoveErrorMessage();
      DisplayEveningActivities();
    }

  } // onCreate()

  public void ExpandActivity(View v)
  {
    int ID = v.getId();

    for(int i = 0; i < expandButtons.size(); i++) // loop through the expand buttons
    {
      if(expandButtonIDs[i] == ID) // the result view expand button that was clicked
      {
        if(!isExpanded.get(i)) // if the clicked result view is not expanded, expand it
        {
          LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
          lp.setMargins(39, 39, 39, 39);
          resultsElements.get(i).setLayoutParams(lp);

//          imageElements.get(i).setImageDrawable(getDrawable(R.drawable.updated_mike));

//          LinearLayout.LayoutParams image = new LinearLayout.LayoutParams(300, 350);
//          image.gravity = Gravity.CENTER;
//          imageElements.get(i).setLayoutParams(image);

          searchButtons.get(i).setBackground(getDrawable(R.drawable.menu_button));

          LinearLayout.LayoutParams button = new LinearLayout.LayoutParams(600, 150);
          button.gravity = Gravity.CENTER;
          button.setMargins(0, 25, 0, 25);
          searchButtons.get(i).setLayoutParams(button);

          searchButtons.get(i).setText("More Details");
          expandButtons.get(i).setText("-");

          isExpanded.set(i, true);
        } // end inner if
        else // collapse all other results views
        {
          LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 263);
          lp.setMargins(39, 39, 39, 39);
          resultsElements.get(i).setLayoutParams(lp);
//          imageElements.get(i).setImageDrawable(getDrawable(R.drawable.mike));
//          imageElements.get(i).getLayoutParams().height = 0;
//          imageElements.get(i).getLayoutParams().width = 0;
          searchButtons.get(i).setBackground(getDrawable(R.drawable.menu_button));
          searchButtons.get(i).getLayoutParams().height = 0;
          searchButtons.get(i).getLayoutParams().width = 0;
          expandButtons.get(i).setText("+");
          isExpanded.set(i, false);
        } // end else
      } // end outer if
      else  // result view expand button that was not clicked
      {
        if(isExpanded.get(i)) // any non-selected result view that is expanded, collapse it
        {
          LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 276);
          lp.setMargins(41, 41, 41, 41);
          resultsElements.get(i).setLayoutParams(lp);
//          imageElements.get(i).setImageDrawable(getDrawable(R.drawable.mike));
//          imageElements.get(i).getLayoutParams().height = 0;
//          imageElements.get(i).getLayoutParams().width = 0;
          searchButtons.get(i).setBackground(getDrawable(R.drawable.menu_button));
          searchButtons.get(i).getLayoutParams().height = 0;
          searchButtons.get(i).getLayoutParams().width = 0;
          expandButtons.get(i).setText("+");
          isExpanded.set(i, false);
        } // end if
      } // end outer else
    } // end for
  } // ExpandActivity()

  public void RemoveErrorMessage()
  {
    txtTryAgain.getLayoutParams().height = 0;
    txtTryAgain.getLayoutParams().width = 0;
  } // RemoveErrorMessage()

  public void Replace(View v) { // user can replace an unwanted activity suggestion
    int searchID = v.getId();

    switch(searchID)
    {
      case(R.id.morn_replace_button_one):
      {
        PullUnwanted(mornActOne);
        isMornOneSet = false;
        SetMornActivities();
        break;
      }
      case(R.id.morn_replace_button_two):
      {
        PullUnwanted(mornActTwo);
        isMornTwoSet = false;
        SetMornActivities();
        break;
      }
      case(R.id.morn_replace_button_three):
      {
        PullUnwanted(mornActThree);
        isMornThreeSet = false;
        SetMornActivities();
        break;
      }
      case(R.id.aft_replace_button_one):
      {
        PullUnwanted(aftActOne);
        isAftOneSet = false;
        SetAftActivities();
        break;
      }
      case(R.id.aft_replace_button_two):
      {
        PullUnwanted(aftActTwo);
        isAftTwoSet = false;
        SetAftActivities();
        break;
      }
      case(R.id.aft_replace_button_three):
      {
        PullUnwanted(aftActThree);
        isAftThreeSet = false;
        SetAftActivities();
        break;
      }
      case(R.id.eve_replace_button_one):
      {
        PullUnwanted(eveActOne);
        isEveOneSet = false;
        SetEveActivities();
        break;
      }
      case(R.id.eve_replace_button_two):
      {
        PullUnwanted(eveActTwo);
        isEveTwoSet = false;
        SetEveActivities();
        break;
      }
      case(R.id.eve_replace_button_three):
      {
        PullUnwanted(eveActThree);
        isEveThreeSet = false;
        SetEveActivities();
        break;
      }
    } // end switch
  } // Replace()

  public void PullUnwanted(String unwanted) { // pulls the replaced activity out of the checks ArrayList
    for(int i = 0; i < activityChecks.size(); i++)
    {
      if(activityChecks.contains(unwanted))
      {
        activityChecks.remove(unwanted);
      }
    }
  } // PullUnwanted()

  public void Search(View v) { // searches the selected activity in the Google app
    int searchID = v.getId();
    String searchFor = "";

    switch (searchID)
    {
      case (R.id.morn_search_button_one):
      {
        searchFor = mornTermOne;
        break;
      }
      case (R.id.morn_search_button_two):
      {
        searchFor = mornTermTwo;
        break;
      }
      case (R.id.morn_search_button_three):
      {
        searchFor = mornTermThree;
        break;
      }
      case (R.id.aft_search_button_one):
      {
        searchFor = aftTermOne;
        break;
      }
      case (R.id.aft_search_button_two):
      {
        searchFor = aftTermTwo;
        break;
      }
      case (R.id.aft_search_button_three):
      {
        searchFor = aftTermThree;
        break;
      }
      case (R.id.eve_search_button_one):
      {
        searchFor = eveTermOne;
        break;
      }
      case (R.id.eve_search_button_two):
      {
        searchFor = eveTermTwo;
        break;
      }
      case (R.id.eve_search_button_three):
      {
        searchFor = eveTermThree;
        break;
      }
    } // switch

    Intent viewSearch = new Intent(Intent.ACTION_WEB_SEARCH);
    viewSearch.putExtra(SearchManager.QUERY, searchFor);
    startActivity(viewSearch);

  } // Search()

} // class