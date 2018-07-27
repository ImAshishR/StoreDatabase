package com.sqlitdatbase.storedatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;



public class ShowProductFragment extends Fragment {

    LayoutInflater inflater;
    ListView productList;
    FragmentTransaction ft;
    Bundle b;
    static int list_positon;
    static String POSITION="position";
    public ShowProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater=inflater;
        return inflater.inflate(R.layout.fragment_show_product, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getView()!=null)
        {
            productList=getView().findViewById(R.id.product_list);
            ProductDatabase database=MainActivity.database;
            ArrayList<Product> products= database.getAllProduct();
            ArrayAdapter<Product> adapter=new ArrayAdapter<Product>(inflater.getContext(),android.R.layout.simple_list_item_1,products);
            productList.setAdapter(adapter);
            registerForContextMenu(productList);
            productList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    list_positon=position;
                    return false;
                }
            });
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Add Product");
        menu.add("Sell Product");
        menu.add("Add Quantity");
        menu.add("Delete product");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String itemName=item.getTitle().toString();
        ft=getFragmentManager().beginTransaction();
        b=new Bundle();
        b.putInt(POSITION,list_positon);
        switch (itemName){
            case "Add Product":
                AddProductFragment addProductFragment=new AddProductFragment();
                addProductFragment.setArguments(b);
                ft.replace(R.id.fragment_frame,addProductFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                break;
            case "Sell Product":
                SellProductFragment sellProductFragment=new SellProductFragment();
                sellProductFragment.setArguments(b);
                ft.replace(R.id.fragment_frame,sellProductFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

                break;
            case "Add Quantity":
                AddProductQuantityFragment addProductQuantityFragment=new AddProductQuantityFragment();
                addProductQuantityFragment.setArguments(b);
                ft.replace(R.id.fragment_frame,addProductQuantityFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
            case "Delete product":
                DeleteProductFragment deleteProductFragment=new DeleteProductFragment();
                deleteProductFragment.setArguments(b);
                ft.replace(R.id.fragment_frame,deleteProductFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
        }
        return super.onContextItemSelected(item);
    }


}
