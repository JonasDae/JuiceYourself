package be.thomasmore.juiceyourself.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.juiceyourself.Models.CocktailCounter;
import be.thomasmore.juiceyourself.Models.CocktailIngredient;
import be.thomasmore.juiceyourself.R;

public class IngredientListAdapter extends BaseAdapter {
// members
    Context context;
    List<CocktailIngredient> list;
    LayoutInflater inflater;
// methods
    public IngredientListAdapter(Context context, List<CocktailIngredient> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.ingrlist_item, null);
        TextView namen = (TextView) convertView.findViewById(R.id.textNaam);
        namen.setText(this.list.get(position).getNaam());
        TextView amnts = (TextView) convertView.findViewById(R.id.textAmount);
        amnts.setText(this.list.get(position).getHoeveelheid());
        return convertView;
    }
}
