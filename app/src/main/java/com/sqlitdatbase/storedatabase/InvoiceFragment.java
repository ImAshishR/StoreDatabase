package com.sqlitdatbase.storedatabase;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceFragment extends Fragment {

    TextView invoice_product_id_view,invoice_product_name_view,
            invoice_sold_quantity_view,invoice_product_price_view,invoice_total_price_view;
    String invoice_product_id,invoice_product_name,invoice_sold_quantity,invoice_product_price,invoice_total_price;
    static String PRODUCT_ID="product_id",PRODUCT_NAME="product_name",
            SOLD_QUANTITY="sold_quantity",PRODUCT_PRICE="product_price",TOTAL_PRICE="total_price";
    LayoutInflater inflater;
    Bundle b;
    public InvoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.inflater=inflater;
        return inflater.inflate(R.layout.fragment_invoice, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getView()!=null){
            invoice_product_id_view=getView().findViewById(R.id.invoice_product_id_view);
            invoice_product_name_view=getView().findViewById(R.id.invoice_product_name_view);
            invoice_sold_quantity_view=getView().findViewById(R.id.invoice_sold_quantity_view);
            invoice_product_price_view=getView().findViewById(R.id.invoice_product_price_view);
            invoice_total_price_view=getView().findViewById(R.id.invoice_total_price_view);
            b=getArguments();
            invoice_product_id_view.setText(""+b.getString(PRODUCT_ID));
            invoice_product_name_view.setText(""+b.getString(PRODUCT_NAME));
            invoice_sold_quantity_view.setText(""+b.getString(SOLD_QUANTITY));
            invoice_product_price_view.setText(""+b.getString(PRODUCT_PRICE)+" /-");
            invoice_total_price_view.setText(""+b.getString(TOTAL_PRICE)+" /-");

        }
    }

}
