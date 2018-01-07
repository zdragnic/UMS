package com.example.dragnic.bpums;

/**
 * Created by Dragnic on 1/6/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticesAdapter extends ArrayAdapter<Notice> {
    private final Context context;
    private List<Notice> values;

    public NoticesAdapter(Context context, ArrayList<Notice> values) {
        super(context, 0, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Notice n = values.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listelement,null);
        TextView title = (TextView) rowView.findViewById(R.id.firstLine);
        TextView text = (TextView) rowView.findViewById(R.id.secondLine);
        Date datum = n.getCreated_at();
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        text.setText(n.getText());
        title.setText(n.getTitle()+" "+datum);



        return rowView;
    }
}