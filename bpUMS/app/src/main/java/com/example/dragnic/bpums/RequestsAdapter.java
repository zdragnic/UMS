package com.example.dragnic.bpums;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dragnic on 1/8/2018.
 */

public class RequestsAdapter extends ArrayAdapter<Request> {
    private final Context context;
    private List<Request> values;

    public RequestsAdapter(Context context, ArrayList<Request> values) {
        super(context, 0, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Request n = values.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listelement,null);
        TextView title = (TextView) rowView.findViewById(R.id.firstLine);
        TextView text = (TextView) rowView.findViewById(R.id.secondLine);
        Date datum = n.getCreated_at();
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        String o="neobradjen";
        if(n.isStatus() == 1){o="obradjen";}

        text.setText("Status: "+o);

        String naziv = "Potvrda o redovnom studiju- stipendija";
        if(n.getCredential_id() == 2){naziv="Potvrda o redovnom studiju- prevoz";}


        title.setText(naziv +" "+datum);



        return rowView;
    }
}