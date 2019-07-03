package com.example.boredombuster;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

  private SQLiteDatabase db = null;
  private final String DB_NAME = "boredomDB";

  Animation animBlink;

  Button btnMenu;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    btnMenu = findViewById(R.id.menu_button);

    animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
            R.anim.blink);

    btnMenu.startAnimation(animBlink);

    btnMenu.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent Selection = new Intent(MainActivity.this,
                Selection.class);
        startActivity(Selection);
      }
    });

    dbSetup();
  } // onCreate()

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  } // onCreateOptionsMenu()

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
  } // onOptionsItemSelected()

  public void dbSetup()
  {
    // create db it if does not exist
    db = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE,null);

    // create tbl_activity if it does not exist
    db.execSQL("CREATE TABLE IF NOT EXISTS tbl_activity" +
            "(activityID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "activity_name VARCHAR, activity_price VARCHAR, search_term VARCHAR);");

    // create tbl_time if it does not exist
    db.execSQL("CREATE TABLE IF NOT EXISTS tbl_time" +
            "(timeID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "time_frame VARCHAR);");

    // create tbl_group if it does not exist
    db.execSQL("CREATE TABLE IF NOT EXISTS tbl_group" +
            "(groupID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "group_size VARCHAR);");

    // create tbl_activity_time if it does not exist
    db.execSQL("CREATE TABLE IF NOT EXISTS tbl_activity_time" +
            "(activity_timeID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "activityID INTEGER, timeID INTEGER);");

    // create tbl_activity_group if it does not exist
    db.execSQL("CREATE TABLE IF NOT EXISTS tbl_activity_group" +
            "(activity_groupID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "activityID INTEGER, groupID INTEGER);");

    Cursor c = db.rawQuery("SELECT * FROM tbl_activity", null);

    if(c != null) // no records
    {
      if(c.getCount() <= 0)
      {
        // insert activities //////////////////////////////////////////////////////////////
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to a garage sale', 'Free', 'garage sales');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Watch the sunrise', 'Free', null);");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go fishing', 'Free', 'fishing spots');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go for a hike', 'Free', 'hiking spots');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go for a bike ride', 'Free', 'bike paths');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Pack a picnic lunch', 'Free', 'parks');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to the beach', 'Free', 'beach');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go the library', 'Free', 'public library');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Geocaching', 'Free', 'geocaching');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to the park', 'Free', 'parks');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go fly a kite', 'Free', 'parks');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play football', 'Free', 'football field');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play soccer', 'Free', 'soccer field');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play frisbee', 'Free', 'parks');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play basketball', 'Free', 'basketball court');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play tennis', 'Free', 'tennis court');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play baseball', 'Free', 'baseball field');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play discgolf', 'Free', 'disc golf');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Watch the sunset', 'Free', null);");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Trivia night', 'Free', 'trivia night');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Do a puzzle', 'Free', null);");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Read a book', 'Free', 'book store');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go for a walk', 'Free', 'parks');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Make some crafts', 'Free', 'craft ideas');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Watch a movie at home', 'Free', 'new movie releases');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go for a run', 'Free', 'parks');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play board games', 'Free', 'board games');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play cards', 'Free', 'card games');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to the thrift store', 'Low', 'thrift stores');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go fruit picking', 'Low', 'fruit picking');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to the market', 'Low', 'farmers market');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go swimming', 'Low', 'public pools');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play minigolf', 'Low', 'mini golf');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go play pool', 'Low', 'billiards hall');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Get ice cream', 'Low', 'ice cream parlour');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Take a road trip', 'Low', 'road trip destinations');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go bowling', 'Low', 'bowling');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to a local festival', 'Low', 'local festivals');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Host a potluck', 'Low', 'potluck ideas');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Bingo night', 'Low', 'bingo night');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go for coffee', 'Low', 'coffee shops');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Karaoke', 'Low', 'karaoke');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go dancing', 'Low', 'night club');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Host a BBQ', 'Low', 'bbq ideas');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to the driving range', 'Mid', 'driving range');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go for brunch', 'Mid', 'brunch');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play golf', 'Mid', 'golf');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to the zoo', 'Mid', 'zoos');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to an arcade', 'Mid', 'arcades');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to a museum', 'Mid', 'museums');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go see a local sports game', 'Mid', 'local sports');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go on a brewery tour', 'Mid', 'brewery tour');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Play laser tag', 'Mid', 'laser tag');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to the batting cages', 'Mid', 'batting cages');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go axe throwing', 'Mid', 'axe throwing');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to a waterpark', 'Mid', 'waterpark');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go-karting', 'Mid', 'go kart track');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Do an escape room', 'Mid', 'escape room');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go for lunch', 'Mid', 'lunch cafe');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to a racing event', 'Mid', 'motor racing track');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go indoor rock climbing', 'Mid', 'indoor climbing facility');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to a casino', 'Mid', 'casino');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go out for dinner', 'Mid', 'restaurants');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to the movies', 'Mid', 'movie theater');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go out for drinks', 'Mid', 'bars');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to a local concert', 'Mid', 'local concerts');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Take a train to somewhere fun', 'High', 'scenic train tours');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Vineyard tour', 'High', 'vineyard tour');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Hot air balloon ride', 'High', 'hot air balloon rides');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to a sports event', 'High', 'major sports events');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Take a helicopter ride', 'High', 'helicopter rides');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to a fancy dinner', 'High', 'fine dining');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to a concert', 'High', 'stadium concerts');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go to an amusement park', 'High', 'amusement parks');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go skydiving', 'High', 'skydiving');");
        db.execSQL("INSERT INTO tbl_activity VALUES(?1, 'Go camping', 'High', 'campgrounds');");

        // insert times ///////////////////////////////////////////
        db.execSQL("INSERT INTO tbl_time VALUES(?1, 'Morning');");
        db.execSQL("INSERT INTO tbl_time VALUES(?1, 'Afternoon');");
        db.execSQL("INSERT INTO tbl_time VALUES(?1, 'Evening');");

        // insert groups //////////////////////////////////////////
        db.execSQL("INSERT INTO tbl_group VALUES(?1, 'Single');");
        db.execSQL("INSERT INTO tbl_group VALUES(?1, 'Many');");

        // insert activity/time links /////////////////////////////////
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 1, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 2, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 3, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 3, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 4, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 4, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 5, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 5, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 6, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 7, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 7, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 8, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 8, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 9, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 9, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 10, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 10, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 11, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 11, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 12, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 12, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 13, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 13, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 14, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 14, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 15, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 15, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 16, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 16, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 17, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 17, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 18, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 18, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 19, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 20, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 21, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 21, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 21, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 22, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 22, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 22, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 23, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 23, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 24, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 24, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 24, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 25, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 25, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 25, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 26, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 26, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 27, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 27, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 27, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 28, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 28, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 28, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 29, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 29, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 30, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 30, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 31, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 31, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 32, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 32, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 33, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 33, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 33, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 34, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 34, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 35, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 35, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 36, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 36, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 37, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 37, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 38, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 38, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 39, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 39, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 40, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 41, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 41, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 42, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 43, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 44, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 44, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 45, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 45, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 46, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 47, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 47, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 48, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 48, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 49, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 49, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 50, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 50, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 51, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 51, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 52, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 52, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 53, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 53, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 54, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 54, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 55, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 55, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 56, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 56, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 57, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 57, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 57, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 58, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 58, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 59, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 60, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 60, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 60, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 61, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 61, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 61, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 62, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 62, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 63, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 64, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 65, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 66, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 67, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 67, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 68, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 69, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 69, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 70, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 70, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 71, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 71, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 72, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 73, 3);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 74, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 74, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 75, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 75, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 76, 1);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 76, 2);");
        db.execSQL("INSERT INTO tbl_activity_time VALUES(?1, 76, 3);");

        // insert activity/group links /////////////////////////////////
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 1, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 1, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 2, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 2, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 3, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 3, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 4, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 4, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 5, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 5, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 6, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 7, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 8, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 8, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 9, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 10, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 10, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 11, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 11, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 12, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 13, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 14, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 15, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 16, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 17, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 18, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 19, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 19, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 20, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 21, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 22, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 23, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 23, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 24, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 25, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 25, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 26, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 27, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 28, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 29, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 29, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 30, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 30, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 31, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 31, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 32, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 33, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 34, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 35, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 36, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 37, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 38, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 39, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 40, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 41, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 42, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 43, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 44, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 45, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 45, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 46, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 47, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 48, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 49, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 50, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 51, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 52, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 53, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 54, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 54, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 55, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 56, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 57, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 58, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 59, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 60, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 61, 1);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 61, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 62, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 63, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 64, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 65, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 66, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 67, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 68, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 69, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 70, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 71, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 72, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 73, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 74, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 75, 2);");
        db.execSQL("INSERT INTO tbl_activity_group VALUES(?1, 76, 2);");

      } // if(c.getCount() <= 0)
    } // if(c != null)
    db.close(); // close db connection, obviously
  } // dbSetup()

} // class
