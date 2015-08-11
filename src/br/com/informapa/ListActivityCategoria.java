package br.com.informapa;

import java.util.ArrayList;
import java.util.List;
import br.com.informapa.categoria.MapaAcadecima;
import br.com.informapa.categoria.MapaAutoEscola;
import br.com.informapa.categoria.MapaAutoPecas;
import br.com.informapa.categoria.MapaBar;
import br.com.informapa.categoria.MapaFarmacia;
import br.com.informapa.categoria.MapaGrafica;
import br.com.informapa.categoria.MapaHospital;
import br.com.informapa.categoria.MapaHotel;
import br.com.informapa.categoria.MapaIgreja;
import br.com.informapa.categoria.MapaLanHouse;
import br.com.informapa.categoria.MapaLanchonete;
import br.com.informapa.categoria.MapaLavaJato;
import br.com.informapa.categoria.MapaLojadeCalcados;
import br.com.informapa.categoria.MapaLojadeRoupas;
import br.com.informapa.categoria.MapaOficina;
import br.com.informapa.categoria.MapaPadaria;
import br.com.informapa.categoria.MapaPapelaria;
import br.com.informapa.categoria.MapaPetShop;
import br.com.informapa.categoria.MapaPizzaria;
import br.com.informapa.categoria.MapaPostodeGasolina;
import br.com.informapa.categoria.MapaRestaurante;
import br.com.informapa.categoria.MapaSalaodeBeleza;
import br.com.informapa.categoria.MapaSorveteria;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivityCategoria extends Activity {
	private List<Car> myCars = new ArrayList<Car>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		populateCarList();
		populateListView();
		registerClickCallback();
	}

	private void populateCarList() {
		myCars.add(new Car("Academia", 1, R.drawable.ic_mark, ""));
		myCars.add(new Car("Auto Peças", 2, R.drawable.ic_mark, ""));
		myCars.add(new Car("Auto Escola", 3, R.drawable.ic_mark, ""));
		myCars.add(new Car("Bar", 4, R.drawable.ic_mark, ""));
		myCars.add(new Car("Farmácia", 5, R.drawable.ic_mark, ""));
		myCars.add(new Car("Gráfica", 6, R.drawable.ic_mark, ""));
		myCars.add(new Car("Hospital", 7, R.drawable.ic_mark, ""));
		myCars.add(new Car("Hotel", 8, R.drawable.ic_mark, ""));
		myCars.add(new Car("Igreja", 9, R.drawable.ic_mark, ""));
		myCars.add(new Car("Lan House", 10, R.drawable.ic_mark, ""));
		myCars.add(new Car("Lanchonete", 11, R.drawable.ic_mark, ""));
		myCars.add(new Car("Lava Jato", 12, R.drawable.ic_mark, ""));
		myCars.add(new Car("Loja de Calçados", 12, R.drawable.ic_mark, ""));
		myCars.add(new Car("Loja de Roupas", 13, R.drawable.ic_mark, ""));
		myCars.add(new Car("Padaria", 14, R.drawable.ic_mark, ""));
		myCars.add(new Car("Papelaria", 15, R.drawable.ic_mark, ""));
		myCars.add(new Car("Pet Shop", 16, R.drawable.ic_mark, ""));
		myCars.add(new Car("Pizzaria", 17, R.drawable.ic_mark, ""));
		myCars.add(new Car("Posto de Gasolina", 18, R.drawable.ic_mark, ""));
		myCars.add(new Car("Oficina", 19, R.drawable.ic_mark, ""));
		myCars.add(new Car("Restaurante", 20, R.drawable.ic_mark, ""));
		myCars.add(new Car("Salão de Beleza", 21, R.drawable.ic_mark, ""));
		myCars.add(new Car("Sorveteria", 22, R.drawable.ic_mark, ""));

	}

	private void populateListView() {
		ArrayAdapter<Car> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.carsListView);
		list.setAdapter(adapter);
	}

	private void registerClickCallback() {
		ListView list = (ListView) findViewById(R.id.carsListView);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked,
					int position, long id) {

				switch (position) {
				case 0:
					// Academia
					Intent intent0 = new Intent(getApplicationContext(),
							MapaAcadecima.class);
					startActivity(intent0);
					break;
				case 1:
					// Auto Peças
					Intent intent1 = new Intent(getApplicationContext(),
							MapaAutoPecas.class);
					startActivity(intent1);
					break;
				case 2:
					// Auto Escola
					Intent intent2 = new Intent(getApplicationContext(),
							MapaAutoEscola.class);
					startActivity(intent2);
					break;
				case 3:
					// Bar
					Intent intent3 = new Intent(getApplicationContext(),
							MapaBar.class);
					startActivity(intent3);
					break;
				case 4:
					// Farmácia
					Intent intent4 = new Intent(getApplicationContext(),
							MapaFarmacia.class);
					startActivity(intent4);
					break;
				case 5:
					// Gráfica
					Intent intent5 = new Intent(getApplicationContext(),
							MapaGrafica.class);
					startActivity(intent5);
					break;
				case 6:
					// Hospital
					Intent intent6 = new Intent(getApplicationContext(),
							MapaHospital.class);
					startActivity(intent6);
					break;
				case 7:
					// Hotel
					Intent intent7 = new Intent(getApplicationContext(),
							MapaHotel.class);
					startActivity(intent7);
					break;
				case 8:
					// Igreja
					Intent intent8 = new Intent(getApplicationContext(),
							MapaIgreja.class);
					startActivity(intent8);
					break;
				case 9:
					// Lan House
					Intent intent10 = new Intent(getApplicationContext(),
							MapaLanHouse.class);
					startActivity(intent10);
					break;
				case 10:
					// Lanchonete
					Intent intent11 = new Intent(getApplicationContext(),
							MapaLanchonete.class);
					startActivity(intent11);
					break;
				case 11:
					// Lava Jato--
					Intent intent12 = new Intent(getApplicationContext(),
							MapaLavaJato.class);
					startActivity(intent12);
					break;
				case 12:
					// Loja de Calçados
					Intent intent13 = new Intent(getApplicationContext(),
							MapaLojadeCalcados.class);
					startActivity(intent13);
					break;
				case 13:
					// Loja de Roupas
					Intent intent14 = new Intent(getApplicationContext(),
							MapaLojadeRoupas.class);
					startActivity(intent14);
					break;
				case 14:
					// Padaria
					Intent intent15 = new Intent(getApplicationContext(),
							MapaPadaria.class);
					startActivity(intent15);
					break;
				case 15:
					// Papelaria
					Intent intent16 = new Intent(getApplicationContext(),
							MapaPapelaria.class);
					startActivity(intent16);
					break;
				case 16:
					// Pet Shop
					Intent intent17 = new Intent(getApplicationContext(),
							MapaPetShop.class);
					startActivity(intent17);
					break;
				case 17:
					// Pizzaria
					Intent intent18 = new Intent(getApplicationContext(),
							MapaPizzaria.class);
					startActivity(intent18);
					break;
				case 18:
					// Posto de Gasolina
					Intent intent19 = new Intent(getApplicationContext(),
							MapaPostodeGasolina.class);
					startActivity(intent19);
					break;
				case 19:
					// Oficina
					Intent intent20 = new Intent(getApplicationContext(),
							MapaOficina.class);
					startActivity(intent20);
					break;
				case 20:
					// Restaurante
					Intent intent21 = new Intent(getApplicationContext(),
							MapaRestaurante.class);
					startActivity(intent21);
					break;
				case 21:
					// Salão de Beleza
					Intent intent22 = new Intent(getApplicationContext(),
							MapaSalaodeBeleza.class);
					startActivity(intent22);
					break;
				case 22:
					// Sorveteria
					Intent intent23 = new Intent(getApplicationContext(),
							MapaSorveteria.class);
					startActivity(intent23);
					break;

				}

			}
		});
	}

	private class MyListAdapter extends ArrayAdapter<Car> {
		public MyListAdapter() {
			super(ListActivityCategoria.this, R.layout.item_view, myCars);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure we have a view to work with (may have been given null)
			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.item_view,
						parent, false);
			}

			// Find the car to work with.
			Car currentCar = myCars.get(position);

			// Fill the view
			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.item_icon);
			imageView.setImageResource(currentCar.getIconID());

			// Make:
			TextView makeText = (TextView) itemView
					.findViewById(R.id.item_txtMake);
			makeText.setText(currentCar.getMake());

			// Year:
			TextView yearText = (TextView) itemView
					.findViewById(R.id.item_txtYear);
			yearText.setText("" + currentCar.getYear());

			// Condition:
			TextView condionText = (TextView) itemView
					.findViewById(R.id.item_txtCondition);
			condionText.setText(currentCar.getCondition());

			return itemView;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_desc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			// app icon in action bar clicked; finish activity to go home
			finish();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
