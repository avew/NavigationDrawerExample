package net.qiwary.navigationdrawerexample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import net.qiwary.navigationdrawerexample.R;
import net.qiwary.navigationdrawerexample.adapter.TabsPagerAdapter;

/**
 * Created by avew on 12/5/14.
 */
public class TabsDraftFragment extends Fragment {

	private static final String TAG = TabsDraftFragment.class
			.getSimpleName();
	private TabsPagerAdapter tabsPagerAdapter;
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;


	public static TabsDraftFragment newInstance() {
		return new TabsDraftFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tab_draft, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		tabs = (PagerSlidingTabStrip) view
				.findViewById(R.id.tabs);
		pager = (ViewPager) view.findViewById(R.id.pager);

		tabsPagerAdapter = new TabsPagerAdapter(getFragmentManager());
		pager.setAdapter(tabsPagerAdapter);

		tabs.setViewPager(pager);
	}

}


