package coursenettt.com.ujian;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawer1 = (DrawerLayout) findViewById(R.id.drawer1);
        NavigationView nav1 = (NavigationView)findViewById(R.id.nav1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle
                (MainActivity.this, drawer1, (R.string.open), (R.string.close));

        drawer1.addDrawerListener(toggle);

        nav1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId()==R.id.MenuListRegister){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame1, new ListFragment())
                            .addToBackStack(null)
                            .commit();

                    drawer1.closeDrawers();
                }
                else if (menuItem.getItemId()==R.id.tambah){
                    Intent bgd = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(bgd);
                }
                return false;
            }
        });
    }

    ActionBarDrawerToggle toggle;

    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item){
        toggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    protected void onPostCreate(Bundle savedInstanceState){

        super.onPostCreate(savedInstanceState);

        toggle.syncState();
    }

    boolean clickback = false;

    public void onBackPressed(){
        if (getSupportFragmentManager().getBackStackEntryCount()>0)
        {
            getSupportFragmentManager().popBackStackImmediate();
            return;
        }

        if (clickback==true)
        {
            super.onBackPressed();
            return;
        }
        clickback=true;

        Toast.makeText(getApplicationContext(), "Press Once Again to Exit",Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                clickback=false;
            }
        },2000);
    }

}
