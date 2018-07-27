package com.sqlitdatbase.storedatabase;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class MainPageFragment extends Fragment implements View.OnClickListener {
    FragmentTransaction ft;
    LayoutInflater inflater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater=inflater;
        return inflater.inflate(R.layout.fragment_main_page, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        View view=getView();
        if(view!=null){
            final Button add=view.findViewById(R.id.add_button);
            final Button show=view.findViewById(R.id.show_button);
            final Button sell=view.findViewById(R.id.sell_button);
            final Button update=view.findViewById(R.id.update_button);
            add.setOnClickListener(this);
            show.setOnClickListener(this);
            sell.setOnClickListener(this);
            update.setOnClickListener(this);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_button:
                AddProductFragment apf = new AddProductFragment();
                ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_frame,apf);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
            case R.id.show_button:
                ShowProductFragment spf=new ShowProductFragment();
                ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_frame,spf);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;

            case R.id.sell_button:
                SellProductFragment sellProductFragment=new SellProductFragment();
                ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_frame,sellProductFragment);
                ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;

            case R.id.update_button:
                final AlertDialog.Builder alertDialog=new AlertDialog.Builder(inflater.getContext());
                alertDialog.setTitle("Update Product");
                alertDialog.setIcon(R.drawable.update);
                alertDialog.setMessage("What do you want?");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Add Product Quantity", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddProductQuantityFragment addProductQuantityFragment=new AddProductQuantityFragment();
                        ft=getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_frame,addProductQuantityFragment);
                        ft.addToBackStack(null);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.commit();
                    }
                });
                alertDialog.setNegativeButton("remove Product", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteProductFragment deleteProductFragment=new DeleteProductFragment();
                        ft=getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_frame,deleteProductFragment);
                        ft.addToBackStack(null);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.commit();
                    }
                });
                alertDialog.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
                break;
        }
    }
}
