package com.example.ideanote_ino.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.ideanote_ino.Common.IdeaDatas;
import com.example.ideanote_ino.R;
import com.example.ideanote_ino.SQLite.IdeaDto;
import com.example.ideanote_ino.SQLite.QueryForMain;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements Filterable {

    private Context mContext;
    private int layout = 0;
    //항상 ideadatas의 원본이 되는 배열
    ArrayList<IdeaDto> f_list;
    //검색으로 걸러지는 배열 (실제 보여주는 배열)
    ArrayList<IdeaDto> list = new ArrayList<>();
    private LayoutInflater inflater = null;
    ItemFilter itemFilter = new ItemFilter();

    public ListViewAdapter(Context mContext, int layout, ArrayList<IdeaDto> list) {
        this.mContext = mContext;
        this.layout = layout;
        this.list = list;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        f_list = new ArrayList<>(list);
    }

    public ArrayList<IdeaDto> getList() {
        return list;
    }

    public void setList(ArrayList<IdeaDto> list) {
        this.list = list;
        f_list = new ArrayList<>(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position).getIno_num();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(this.layout, parent, false);

        TextView tv_list_num = convertView.findViewById(R.id.tv_list_num);
        TextView tv_list_date = convertView.findViewById(R.id.tv_list_date);
        TextView tv_list_content = convertView.findViewById(R.id.tv_list_content);

        tv_list_num.setText(Integer.toString(list.get(position).getIno_num()));
        tv_list_date.setText(list.get(position).getIno_date());
        tv_list_content.setText(list.get(position).getIno_idea());

        return convertView;
    }

    // ------ filter -------
    @Override
    public Filter getFilter() {
        return itemFilter;
    }

    private class ItemFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString();
            FilterResults results = new FilterResults();

            ArrayList<IdeaDto> filteredList = new ArrayList<>();

            if (filterString != null && filterString.trim().length() != 0) {
                String filterPattern = filterString.toLowerCase();
                for (IdeaDto dto: f_list) {
                    if (dto.getIno_idea().toLowerCase().contains(filterPattern)) {
                        filteredList.add(dto);
                    }
                }
                results.values = filteredList;
                results.count = filteredList.size();
            } else {
                results.values = f_list;
                results.count = f_list.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList<IdeaDto>) results.values);
            notifyDataSetChanged();
        }
    }
}
