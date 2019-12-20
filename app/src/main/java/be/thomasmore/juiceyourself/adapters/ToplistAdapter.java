package be.thomasmore.juiceyourself.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.juiceyourself.Models.CocktailCounter;
import be.thomasmore.juiceyourself.R;

public class ToplistAdapter extends BaseAdapter {
// members
    Context context;
    List<CocktailCounter> toplist;
    LayoutInflater inflater;
// methods
    public ToplistAdapter(Context context, List<CocktailCounter> list) {
        this.context = context;
        this.toplist = list;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return toplist.size();
    }

    @Override
    public Object getItem(int position) {
        return toplist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.toplist_item, null);
        TextView namen = (TextView) convertView.findViewById(R.id.textNaam);
        TextView counters = (TextView) convertView.findViewById(R.id.textCounter);
        namen.setText(this.toplist.get(position).getCocktailNaam());
        counters.setText(this.toplist.get(position).getCounter()+"");
        return convertView;
    }
}
