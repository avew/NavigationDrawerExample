package net.qiwary.navigationdrawerexample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.qiwary.navigationdrawerexample.fragment.AboutFragment;
import net.qiwary.navigationdrawerexample.fragment.HelpFragment;

/**
 * Created by avew on 12/5/14.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

	private final String[] TITLES = {"Tab1", "Tab2", "Tab3", "Tab4", "Tab5"};

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

		@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return new HelpFragment();
			case 1:
				return new AboutFragment();
			case 2:
				return new AboutFragment();
			case 3:
				return new AboutFragment();
			case 4:
				return new AboutFragment();
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position];
	}

	@Override
	public int getCount() {
		return TITLES.length;
	}
}
