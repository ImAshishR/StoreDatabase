package com.sqlitdatbase.storedatabase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductQuantityFragment extends Fragment implements View.OnClickListener {

    LayoutInflater inflater;
    EditText add_product_quantity_id_field,add_product_quantity_name_field,add_product_quantity_price_field,update_product_quantity_field;
    String add_product_quantity_id,add_product_quantity_name,add_product_quantity_price,update_product_quantity;
    Button add_product_quantity_button,add_product_quantity_search_button;
    Product product;
    Bundle b;
    public AddProductQuantityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater=inflater;
        return inflater.inflate(R.layout.fragment_add_product_quantity, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        b=getArguments();
        if(getView()!=null){
            add_product_quantity_id_field=getView().findViewById(R.id.add_product_quantity_id_field);
            add_product_quantity_name_field=getView().findViewById(R.id.add_product_quantity_name_field);
            add_product_quantity_price_field=getView().findViewById(R.id.add_product_quantity_price_field);
            update_product_quantity_field=getView().findViewById(R.id.update_product_quantity_field);
            add_product_quantity_search_button=getView().findViewById(R.id.add_product_quantity_search_button);
            add_product_quantity_button=getView().findViewById(R.id.add_product_quantity_button);
            add_product_quantity_search_button.setOnClickListener(this);
            add_product_quantity_button.setOnClickListener(this);

            if(b!=null){
                product=MainActivity.database.getAllProduct().get((Integer) b.get(ShowProductFragment.POSITION));
                add_product_quantity_id_field.setText(""+product.getProductId());
                add_product_quantity_name_field.setText(""+product.getProductName());
                add_product_quantity_price_field.setText(""+product.getProductPrice());
                add_product_quantity_id_field.setKeyListener(null);
            }else
                add_product_quantity_id_field.setKeyListener(add_product_quantity_id_field.getKeyListener());
        }
    }

    @Override
    public void onClick(View v) {
        add_product_quantity_id=add_product_quantity_id_field.getText().toString();
        add_product_quantity_name=add_product_quantity_name_field.getText().toString();
        update_product_quantity=update_product_quantity_field.getText().toString();
        add_product_quantity_price=add_product_quantity_price_field.getText().toString();
        switch(v.getId()){
            case R.id.add_product_quantity_search_button:
                if(add_product_quantity_id.length()==0)
                    add_product_quantity_id_field.setError("Please Enter Id");
                else{
                    product=MainActivity.database.findProduct(Integer.parseInt(add_product_quantity_id));
                    add_product_quantity_name_field.setError(null);
                    add_product_quantity_price_field.setError(null);
                    if(product!=null){
                        add_product_quantity_name_field.setText(""+product.getProductName());
                        add_product_quantity_price_field.setText(""+product.getProductPrice());
                        Toast.makeText(inflater.getContext(), "current quantity:"+product.getProductQuantity(), Toast.LENGTH_SHORT).show();
                    }else{
                        add_product_quantity_name_field.setText("");
                        add_product_quantity_price_field.setText("");
                        Toast.makeText(inflater.getContext(), "Product isn't available", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.add_product_quantity_button:
                if(add_product_quantity_id.length()==0)
                    add_product_quantity_id_field.setError("Please Enter id");
                else if(add_product_quantity_name.length()==0){
                    add_product_quantity_name_field.setError("Search for product name");
                    add_product_quantity_price_field.setError("Search for product price");
                }if(update_product_quantity.length()==0)
                    update_product_quantity_field.setError("Please enter quantity");
                else if(product.getProductId()!=Integer.parseInt(add_product_quantity_id)){
                    add_product_quantity_name_field.setText("");
                    add_product_quantity_price_field.setText("");
                    Toast.makeText(inflater.getContext(), "Product id is changed", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.database.addQuantity(Integer.parseInt(add_product_quantity_id),Integer.parseInt(update_product_quantity));
                    add_product_quantity_id_field.setText("");
                    add_product_quantity_name_field.setText("");
                    add_product_quantity_price_field.setText("");
                    update_product_quantity_field.setText("");
                    Toast.makeText(inflater.getContext(), "Product Quantity is updated", Toast.LENGTH_SHORT).show();
                    product=null;
                    if(b!=null)
                        getFragmentManager().popBackStack();
                }
        }
    }
}
