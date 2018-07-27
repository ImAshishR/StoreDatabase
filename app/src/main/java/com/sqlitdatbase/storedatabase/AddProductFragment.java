package com.sqlitdatbase.storedatabase;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddProductFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AddProductFragment extends Fragment {

    EditText add_product_id_field,add_product_name_field,add_product_quantity_field,add_product_price_field;
    String add_product_id,add_product_name,add_product_quantity,add_product_price;
    LayoutInflater inflater;
    Bundle b;
    public AddProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater=inflater;
        return inflater.inflate(R.layout.fragment_add_product, container, false);
    }



    @Override
    public void onStart() {
        super.onStart();
        View view=getView();
        b=getArguments();
        if(view!=null){
            add_product_id_field=getView().findViewById(R.id.add_product_id_field);
            add_product_name_field=getView().findViewById(R.id.add_product_name_field);
            add_product_quantity_field=getView().findViewById(R.id.add_product_quantity_field);
            add_product_price_field=getView().findViewById(R.id.add_product_price_field);
            final Button add=view.findViewById(R.id.add_product_button);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add_product_id=add_product_id_field.getText().toString();
                    add_product_name=add_product_name_field.getText().toString();
                    add_product_quantity=add_product_quantity_field.getText().toString();
                    add_product_price=add_product_price_field.getText().toString();
                    if(add_product_id.length()==0)
                        add_product_id_field.setError("Please Enter id");
                    else if(add_product_name.length()==0)
                        add_product_name_field.setError("Please Enter Name");
                    else if(add_product_quantity.length()==0)
                        add_product_quantity_field.setError("Please Enter quantity");
                    else if(add_product_price.length()==0)
                        add_product_price_field.setError("Please Enter price");
                    else{
                        ProductDatabase database=MainActivity.database;
                        Product record=new Product(Integer.parseInt(add_product_id),add_product_name,
                                Integer.parseInt(add_product_quantity),Integer.parseInt(add_product_price));
                        if(database.addProduct(record)==-1)
                            Toast.makeText(inflater.getContext(), "Product already exist", Toast.LENGTH_SHORT).show();
                        else{
                            Toast.makeText(inflater.getContext(), "Product added..", Toast.LENGTH_SHORT).show();
                            if(b!=null)
                                getFragmentManager().popBackStack();
                            clearFields();
                        }

                    }
                }
            });
        }
    }

    void clearFields(){
        add_product_id_field.setText("");
        add_product_name_field.setText("");
        add_product_quantity_field.setText("");
        add_product_price_field.setText("");
    }

}
