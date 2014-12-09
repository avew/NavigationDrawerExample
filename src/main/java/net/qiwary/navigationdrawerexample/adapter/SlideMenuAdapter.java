package net.qiwary.navigationdrawerexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.qiwary.navigationdrawerexample.R;
import net.qiwary.navigationdrawerexample.model.NavDrawerItem;

import java.util.ArrayList;

/**
 * Created by avew on 12/5/14.
 */
public class SlideMenuAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<NavDrawerItem> navDrawerItems;

	//private String[] menuTitles;
//	private int[] menuImages = {
//			R.drawable.ic_home,
//			R.drawable.ic_communities,
//			R.drawable.ic_pages,
//			R.drawable.ic_people,
//			R.drawable.ic_photos};

	public SlideMenuAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;
		//menuTitles = context.getResources().getStringArray(R.array.menu_title);
	}


	@Override
	public int getCount() {

		//return menuTitles.length;
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {

		//return menuTitles[position];
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		//View rootView = null;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			//rootView = inflater.inflate(R.layout.slide_menu_list, parent, false);
			convertView = inflater.inflate(R.layout.slide_menu_list, null);
		}
		// else {
		//rootView = convertView;
		//      }

		TextView title = (TextView) convertView.findViewById(R.id.menuTitle);
		ImageView image = (ImageView) convertView.findViewById(R.id.menuImage);
		TextView count = (TextView) convertView.findViewById(R.id.menuCount);

		image.setImageResource(navDrawerItems.get(position).getIcon());
		title.setText(navDrawerItems.get(position).getTitle());

		//Menampilkan jumlah
		if (navDrawerItems.get(position).getCounterVisibility()) {
			count.setText(navDrawerItems.get(position).getCount());
		} else {
			//hide jika tidak ada count
			count.setVisibility(View.GONE);
		}


		//title.setText(menuTitles[position]);
		//image.setImageResource(menuImages[position]);


		return convertView;
	}
}
