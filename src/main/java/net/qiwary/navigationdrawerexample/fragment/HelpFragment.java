package net.qiwary.navigationdrawerexample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.qiwary.navigationdrawerexample.R;

/**
 * Created by avew on 12/5/14.
 */
public class HelpFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_help, container, false);
		return rootView;
	}
}
