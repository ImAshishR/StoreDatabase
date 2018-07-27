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
public class DeleteProductFragment extends Fragment implements View.OnClickListener {

    LayoutInflater inflater;
    EditText delete_product_id_field,delete_product_name_field,delete_product_quantity_field,delete_product_price_field;
    Product product=null;
    String delete_product_id,delete_product_name,delete_product_quantity,delete_product_price,delete_product_totalPrice;
    Button delete_product_search_button,delete_product_button;
    Bundle b;
    public DeleteProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater=inflater;
        return inflater.inflate(R.layout.fragment_delete_product, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        b=getArguments();
        if(getView()!=null){
            delete_product_id_field=getView().findViewById(R.id.delete_product_id_field);
            delete_product_name_field=getView().findViewById(R.id.delete_product_name_field);
            delete_product_quantity_field=getView().findViewById(R.id.delete_product_quantity_field);
            delete_product_price_field=getView().findViewById(R.id.delete_product_price_field);
            delete_product_search_button=getView().findViewById(R.id.delete_product_search_button);
            delete_product_button=getView().findViewById(R.id.delete_product_button);
            delete_product_search_button.setOnClickListener( this);
            delete_product_button.setOnClickListener( this);
            if(b!=null){
                product=MainActivity.database.getAllProduct().get((Integer) b.get(ShowProductFragment.POSITION));
                delete_product_id_field.setText(""+product.getProductId());
                delete_product_name_field.setText(""+product.getProductName());
                delete_product_quantity_field.setText(""+product.getProductQuantity());
                delete_product_price_field.setText(""+product.getProductPrice());
                delete_product_id_field.setKeyListener(null);
            }else
                delete_product_id_field.setKeyListener(delete_product_id_field.getKeyListener());
        }
    }

    @Override
    public void onClick(View v) {
        delete_product_id=delete_product_id_field.getText().toString();
        delete_product_name=delete_product_name_field.getText().toString();
        delete_product_quantity=delete_product_quantity_field.getText().toString();
        delete_product_price=delete_product_price_field.getText().toString();
        switch (v.getId()){
            case R.id.delete_product_search_button:
                if(delete_product_id.length()==0)
                    delete_product_id_field.setError("Please Enter id");
                else {
                    product=MainActivity.database.findProduct(Integer.parseInt(delete_product_id));
                    delete_product_name_field.setError(null);
                    delete_product_quantity_field.setError(null);
                    delete_product_price_field.setError(null);
                    if(product==null){
                        delete_product_name_field.setText("");
                        delete_product_quantity_field.setText("");
                        delete_product_price_field.setText("");
                        Toast.makeText(inflater.getContext(), "Product isn't available", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        delete_product_name_field.setText(""+product.getProductName());
                        delete_product_quantity_field.setText(""+product.getProductQuantity());
                        delete_product_price_field.setText(""+product.getProductPrice());
                    }
                }
                break;
            case R.id.delete_product_button:
                if(delete_product_id.length()==0)
                    delete_product_id_field.setError("Please Enter id");
                else if(delete_product_name.length()==0){
                    delete_product_name_field.setError("search for name");
                    delete_product_quantity_field.setError("search for quantity");
                    delete_product_price_field.setError("search for price");
                }else if(product.getProductId()!=Integer.parseInt(delete_product_id)){
                    delete_product_name_field.setText("");
                    delete_product_quantity_field.setText("");
                    delete_product_price_field.setText("");
                    Toast.makeText(inflater.getContext(), "Product id is changed", Toast.LENGTH_SHORT).show();
                }else{
                    MainActivity.database.delete(product.getProductId());
                    Toast.makeText(inflater.getContext(), "Product is deleted", Toast.LENGTH_SHORT).show();
                    delete_product_id_field.setText("");
                    delete_product_name_field.setText("");
                    delete_product_quantity_field.setText("");
                    delete_product_price_field.setText("");
                    product=null;
                    if(b!=null)
                        getFragmentManager().popBackStack();
                }
                break;
        }
    }
}
