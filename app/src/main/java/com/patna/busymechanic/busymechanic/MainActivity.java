package com.patna.busymechanic.busymechanic;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnFragmentInteractionListener, AccountFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private AccountFragment accountFragment;
    private SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);


//            mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
//            mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);



            setContentView(R.layout.activity_main);
            homeFragment = new HomeFragment();
            accountFragment = new AccountFragment();
            searchFragment = new SearchFragment();
            mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
            mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);
            setFragment(homeFragment);

            mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_home:
//                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                            setFragment(homeFragment);
                            return true;


                        case R.id.nav_account:
                            setFragment(accountFragment);
                            return true;


                        case R.id.nav_search:
                            setFragment(searchFragment);
                            return true;

                        default:
                            return false;
                    }

                }

            });




    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }


    private void setFragment(android.support.v4.app.Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onBackPressed() {

        homeFragment.canGoBack();
        if(homeFragment.canGoBack()){
            homeFragment.goBack();
        }else if(searchFragment.canGoBack()){
            searchFragment.goBack();
        }else if(accountFragment.canGoBack()){
            accountFragment.goBack();
        }else{
            super.onBackPressed();
        }





    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


}
