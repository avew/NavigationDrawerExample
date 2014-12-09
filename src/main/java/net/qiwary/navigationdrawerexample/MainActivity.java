package net.qiwary.navigationdrawerexample;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.qiwary.navigationdrawerexample.adapter.SlideMenuAdapter;
import net.qiwary.navigationdrawerexample.fragment.AboutFragment;
import net.qiwary.navigationdrawerexample.fragment.HelpFragment;
import net.qiwary.navigationdrawerexample.fragment.HomeFragment;
import net.qiwary.navigationdrawerexample.fragment.TabsDraftFragment;
import net.qiwary.navigationdrawerexample.model.NavDrawerItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
	private final String TAG = MainActivity.class.getName();

	private DrawerLayout drawerLayout;
	//List view menu samping
	private ListView drawerListView;

	//Isi menu slide
	//private String[] menuItemList;

	/**
	 * Navigasi menu
	 */

	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private SlideMenuAdapter slideMenuAdapter;

	//Toggle diatas
	private ActionBarDrawerToggle drawerListener;

	//Fragment
	private MenuItem menuItem;
	private HelpFragment helpFragment = null;
	private AboutFragment aboutFragment = null;
	private TabsDraftFragment tabDraftFragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();

		//load slide menu title
		navMenuTitles = getResources().getStringArray(R.array.slide_menu_titles);
		//load slide menu icon
		navMenuIcons = getResources().obtainTypedArray(R.array.slide_menu_icons);


		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		drawerListView = (ListView) findViewById(R.id.drawerList);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// List Report
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Find People
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1), true, "50+"));
		// Photos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1), true, "22"));

		// Recycle the typed array
		navMenuIcons.recycle();

		//Ambil string menu
		//menuItemList = getResources().getStringArray(R.array.planets);
		//drawerListView.setAdapter(new ArrayAdapter<>(this,
		//android.R.layout.simple_list_item_1, menuItemList));
		slideMenuAdapter = new SlideMenuAdapter(getApplicationContext(), navDrawerItems);
		drawerListView.setAdapter(slideMenuAdapter);
		drawerListView.setOnItemClickListener(this);

		//Toggle diatas
		drawerListener = new ActionBarDrawerToggle(this, drawerLayout
				, R.drawable.ic_drawer,
				R.string.drawer_open,
				R.string.drawer_close) {

			//pada saat toggle ditutup
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				//Toast.makeText(MainActivity.this, "Drawer Closed", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "Drawer Closed");
				getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			//pada saat toggle dibuka
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				//Toast.makeText(MainActivity.this, "Drawer Opened", Toast.LENGTH_SHORT).show();
				Log.d(TAG, "Drawer Openend");
				getSupportActionBar().setTitle(mTitle);
			}


		};

		drawerLayout.setDrawerListener(drawerListener);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState == null) {
			selectItem(0);
		}


	}

	/**
	 * List view menu klik
	 *
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//Toast.makeText(this, menuItemList[position] + " was selected ", Toast.LENGTH_SHORT).show();
		selectItem(position);
	}

	/**
	 * Select posisi di slide menu dan menampilkan ke beberapa fragment
	 *
	 * @param position
	 */
	public void selectItem(int position) {
		Fragment fragment = null;
		switch (position) {
			case 0:
				fragment = new HomeFragment();
				break;
			case 1:
				fragment = new HelpFragment();
				break;
			case 2:
//				getSupportFragmentManager().beginTransaction().
//						add(R.id.mainContainer, TabDraftFragment.newInstance(), TabDraftFragment.TAG).commit();
				fragment = new TabsDraftFragment();
				break;
			default:
				break;
		}

		if (fragment != null) {
			getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, fragment).commit();

			//update selected item dan close slide menu
			drawerListView.setItemChecked(position, true);
			drawerListView.setSelection(position);
			setTitle(navMenuTitles[position]);
			drawerLayout.closeDrawer(drawerListView);
		}

	}

	/**
	 * Setting title di slide menu
	 *
	 * @param title
	 */
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(title);
	}


	/**
	 * Menu atas samping
	 *
	 * @param menu
	 * @return
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * jika menu slide open hilangkan action setting
	 *
	 * @param menu
	 * @return
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = drawerLayout.isDrawerOpen(drawerListView);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		//menu.findItem(R.id.action_search).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}


	/**
	 * Menu atas samping
	 *
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		//pada saat ic drawer di klik menu slide samping bakal kebuka
		if (drawerListener.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
			case R.id.action_settings:
				return true;
			case R.id.action_refresh:
				menuItem = item;
				menuItem.setActionView(R.layout.progressbar);
				menuItem.expandActionView();
				refreshTask refreshTask = new refreshTask();
				refreshTask.execute();
				return true;
			case R.id.action_helps:
				helpFragment = new HelpFragment();
				getSupportFragmentManager().
						beginTransaction().
						replace(R.id.mainContainer, helpFragment).commit();
				return true;
			case R.id.action_about:
				aboutFragment = new AboutFragment();
				getSupportFragmentManager().
						beginTransaction().
						replace(R.id.mainContainer, aboutFragment).commit();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


	/**
	 * class digunakan untuk menggunakan drawer
	 */

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerListener.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}


	/**
	 * Refresh task di menu atas
	 */
	private class refreshTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// Simulate something long running
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String s) {
			menuItem.collapseActionView();
			menuItem.setActionView(null);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}
}
