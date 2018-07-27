package com.sqlitdatbase.storedatabase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellProductFragment extends Fragment implements View.OnClickListener{

    EditText sell_product_id_field,sell_product_name_field,sell_product_quantity_field,sell_product_price_field,sell_product_total_price_field;
    Product sellProduct=null;
    String sell_product_id,sell_product_name,sell_product_quantity,sell_product_price,sell_product_totalPrice;
    LayoutInflater inflater;
    Bundle b,b1;

    public SellProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater=inflater;
        return inflater.inflate(R.layout.fragment_sell_product, container, false);
    }



    @Override
    public void onStart() {
        super.onStart();
        b=getArguments();
        View view=getView();
        if(view!=null){
            sell_product_id_field=view.findViewById(R.id.sell_product_id_field);
            sell_product_name_field=view.findViewById(R.id.sell_product_name_field);
            sell_product_quantity_field=view.findViewById(R.id.sell_product_quantity_field);
            sell_product_price_field=view.findViewById(R.id.sell_product_price_field);
            sell_product_total_price_field=view.findViewById(R.id.sell_product_total_price_field);
            Button search,calc,sell;
            search=view.findViewById(R.id.sell_product_search_button);
            calc=view.findViewById(R.id.sell_product_calculate_price_button);
            sell=view.findViewById(R.id.sell_product_button);
            if(b!=null){
                sellProduct=MainActivity.database.getAllProduct().get((Integer) b.get(ShowProductFragment.POSITION));
                sell_product_id_field.setText(""+sellProduct.getProductId());
                sell_product_name_field.setText(""+sellProduct.getProductName());
                sell_product_price_field.setText(""+sellProduct.getProductPrice());
                sell_product_id_field.setKeyListener(null);
            }else{
                sell_product_id_field.setKeyListener(sell_product_id_field.getKeyListener());
            }
            search.setOnClickListener(this);
            calc.setOnClickListener(this);
            sell.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        sell_product_id=sell_product_id_field.getText().toString();
        sell_product_name=sell_product_name_field.getText().toString();
        sell_product_quantity=sell_product_quantity_field.getText().toString();
        sell_product_price=sell_product_price_field.getText().toString();
        sell_product_totalPrice=sell_product_total_price_field.getText().toString();
        switch(v.getId()){
            case R.id.sell_product_search_button:
                if(sell_product_id.length()==0)
                    sell_product_id_field.setError("Please Enter id");
                else{
                    sell_product_name_field.setError(null);
                    sell_product_price_field.setError(null);
                    sellProduct=MainActivity.database.findProduct(Integer.parseInt(sell_product_id));
                    if(sellProduct!=null){
                        sell_product_name_field.setText(""+sellProduct.getProductName());
                        sell_product_price_field.setText(""+sellProduct.getProductPrice());
                    }else
                        Toast.makeText(inflater.getContext(), "No Product Found", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sell_product_calculate_price_button:
                if(sell_product_quantity.length()==0)
                    sell_product_quantity_field.setError("Please Enter Quantity");
                else if(sell_product_price.length()==0)
                    sell_product_price_field.setError("Search product to get price");
                else{
                    sell_product_total_price_field.setError(null);
                    if(Integer.parseInt(sell_product_quantity)<=sellProduct.getProductQuantity()){
                        int total=Integer.parseInt(sell_product_quantity)*sellProduct.getProductPrice();
                        sell_product_total_price_field.setText(""+total);
                    }else {
                        sell_product_quantity_field.setError("Quantity Exceeded");
                        Toast.makeText(inflater.getContext(), "Current Quantity:"+sellProduct.getProductQuantity(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.sell_product_button:
                if(sell_product_id.length()==0)
                    sell_product_id_field.setError("Please Enter Id");
                else if(sell_product_name.length()==0)
                    sell_product_name_field.setError("Search for name");
                else if(sell_product_quantity.length()==0)
                    sell_product_quantity_field.setError("Enter Quantity");
                else if(sell_product_totalPrice.length()==0)
                    sell_product_total_price_field.setError("Calculate Total Price");
                else if(Integer.parseInt(sell_product_id)!=sellProduct.getProductId()){
                    Toast.makeText(inflater.getContext(), "Product Name and id doesn't match", Toast.LENGTH_SHORT).show();
                    sell_product_name_field.setText("");
                    sell_product_price_field.setText("");
                    sell_product_total_price_field.setText("");
                }else if(Integer.parseInt(sell_product_quantity)*Integer.parseInt(sell_product_price)!=Integer.parseInt(sell_product_totalPrice)){
                    sell_product_total_price_field.setText("");
                    sell_product_total_price_field.setError("calculate total price");
                    Toast.makeText(inflater.getContext(), "quantity is changed", Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(sell_product_quantity)==0){
                    sell_product_quantity_field.setError("enter valid quantity");
                } else{
                    MainActivity.database.sellProduct(Integer.parseInt(sell_product_id),Integer.parseInt(sell_product_quantity));
                    Toast.makeText(inflater.getContext(), "Sold", Toast.LENGTH_SHORT).show();

                    if(b!=null) {
                        getFragmentManager().popBackStack();
                    }
                        InvoiceFragment invoiceFragment = new InvoiceFragment();
                        b1 = new Bundle();
                        b1.putString(InvoiceFragment.PRODUCT_ID, sell_product_id);
                        b1.putString(InvoiceFragment.PRODUCT_NAME, sell_product_name);
                        b1.putString(InvoiceFragment.SOLD_QUANTITY, sell_product_quantity);
                        b1.putString(InvoiceFragment.PRODUCT_PRICE, sell_product_price);
                        b1.putString(InvoiceFragment.TOTAL_PRICE, sell_product_totalPrice);
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        invoiceFragment.setArguments(b1);
                        ft.replace(R.id.fragment_frame, invoiceFragment);
                        ft.addToBackStack(null);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.commit();

                    clearFields();

                }

                break;
        }
    }

    void clearFields(){
        sell_product_id_field.setText("");
        sell_product_name_field.setText("");
        sell_product_quantity_field.setText("");
        sell_product_price_field.setText("");
        sell_product_total_price_field.setText("");
        sellProduct=null;
    }

}
