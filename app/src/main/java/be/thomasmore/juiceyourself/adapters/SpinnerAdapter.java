package be.thomasmore.juiceyourself.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import be.thomasmore.juiceyourself.R;

public class SpinnerAdapter extends BaseAdapter {
// members
    Context context;
    String[] values;
    LayoutInflater inflater;
// methods
    public SpinnerAdapter(Context applicationContext, String[] values) {
        this.context = applicationContext;
        this.values = values;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public Object getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.spinner_item, null);
        TextView values = (TextView) convertView.findViewById(R.id.textView);
        values.setText(this.values[position]);
        return convertView;
    }
}
