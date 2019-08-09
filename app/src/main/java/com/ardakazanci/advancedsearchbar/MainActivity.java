package com.ardakazanci.advancedsearchbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

	private Toolbar toolbar;
	private MaterialSearchView materialSearchView;
	private ListView listView;

	String[] listSource = {

			"Arda",
			"Ahmet",
			"Mehmet",
			"Kerem",
			"Aslı"

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("Material Search");
		toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

		materialSearchView = findViewById(R.id.materialSearchView);
		listView = findViewById(R.id.listView);
		materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
			@Override
			public void onSearchViewShown() {

			}

			@Override
			public void onSearchViewClosed() {


				ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listSource);

				listView.setAdapter(adapter);

			}
		});
		materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {

				Log.d("TAG", newText);


				if (newText != null && !newText.isEmpty()) {
					List<String> list = new ArrayList<String>();
					for (String item : listSource) {

						if (item.contains(newText)) {

							list.add(item);

						}

					}

					ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, list);

					listView.setAdapter(adapter);


				} else {


					ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, listSource);

					listView.setAdapter(adapter);

				}

				return true;


			}
		});


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.menu_item, menu); // Menü görünümü şişirildi.
		MenuItem menuItem = menu.findItem(R.id.action_search); // MenuItem referasına ilgili Item değeri atandı
		materialSearchView.setMenuItem(menuItem); // SearchView görünümüne bağlandı.
		return true;
	}
}
