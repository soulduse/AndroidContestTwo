package info.project.datapotal.viewpager.product;


import info.project.datapotal.R;
import info.project.datapotal.viewpager.product.adapter.DB_Grid_Nation_Adapter;
import info.project.datapotal.viewpager.product.adapter.DB_Grid_Products_Adapter;
import info.project.datapotal.viewpager.product.adapter.DB_List_Search_Main_Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class FragmentMain extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
		@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_main_activity, container, false);
		final GridView gridview = (GridView) layout.findViewById(R.id.frag_main_grid);
		gridview.setAdapter(new FragmentMain_Grid_Adapter(getActivity(),R.layout.fragment_main_grid_item));
		return layout;
	}

	
}
