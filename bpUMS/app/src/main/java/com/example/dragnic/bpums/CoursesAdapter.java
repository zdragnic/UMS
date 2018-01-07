package com.example.dragnic.bpums;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dragnic on 1/7/2018.
 */

public class CoursesAdapter extends ArrayAdapter<Course> {
    private final Context context;
    private List<Course> values;

    public CoursesAdapter(Context context, ArrayList<Course> values) {
        super(context, 0, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course c = values.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listelement,null);
        TextView title = (TextView) rowView.findViewById(R.id.firstLine);
        TextView text = (TextView) rowView.findViewById(R.id.secondLine);
        Date datum = c.getCreated_at();
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        text.setText(c.getCode());
        title.setText(c.getTitle());



        return rowView;
    }
}
