package edu.chapman.jennifer.filmdatabase;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DisplayAdapter extends BaseAdapter {

    private Context mContext;

    private ArrayList<String> FilmID;
    private ArrayList<String> name;
    private ArrayList<String> dateReleased;
    private ArrayList<String> fileName;

    public DisplayAdapter(Context c, ArrayList<String> FilmID, ArrayList<String> name) {
        this.mContext = c;

        this.FilmID = FilmID;
        this.name = name;
    }

    public int getCount() {
        return FilmID.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int pos, View child, ViewGroup parent) {
        Holder mHolder;
        LayoutInflater layoutInflater;

        if(child == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.filmlist, null);
            mHolder = new Holder();

            mHolder.name = child.findViewById(R.id.txtFilmName);

            child.setTag(mHolder);
        }

        else {
            mHolder = (Holder) child.getTag();
        }

        mHolder.FilmID.setText(FilmID.get(pos));
        mHolder.name.setText(name.get(pos));

        return child;
    }

    public class Holder {
        TextView FilmID;
        TextView name;
    }
}
