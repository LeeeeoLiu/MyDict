package com.leeeeo.mydict.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.leeeeo.mydict.R;
import com.leeeeo.mydict.apps.AppEngine;
import com.leeeeo.mydict.models.beans.DictBean;
import com.leeeeo.mydict.utils.WinToast;

import java.util.List;

/**
 * Created by Jacob on 16/5/18.
 * Email: Jacob.Deng@about-bob.com
 */
public class DictImportAdpater extends BaseAdapter {
    private List<DictBean> list;
    private Context context;
    private LayoutInflater inflater;

    public DictImportAdpater(Context context, List<DictBean> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DictBean dictBean = list.get(position);
        if (null != dictBean) {

            ViewHolder viewHolder = null;
            if (null == convertView) {

                convertView = inflater.inflate(R.layout.list_item_dict_path_info, null);

                viewHolder = new ViewHolder();
                viewHolder.btn_clickbtn = (Button) convertView.findViewById(R.id.btn_item_click);
                viewHolder.tv_dictName = (TextView) convertView.findViewById(R.id.tv_dict_name);
                viewHolder.tv_dictPath = (TextView) convertView.findViewById(R.id.tv_dict_path);
                viewHolder.isHandled = false;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_dictName.setText(dictBean.getDictName());
            viewHolder.tv_dictPath.setText(dictBean.getFileName());
            viewHolder.btn_clickbtn.setText("导入");
            viewHolder.btn_clickbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WinToast.toast(AppEngine.getInstance().getCurrentContext(), "操作成功");
                }
            });

        }
        return convertView;
    }

    class ViewHolder {

        public TextView tv_dictName;
        public TextView tv_dictPath;
        public boolean isHandled;
        public Button btn_clickbtn;

    }
}
