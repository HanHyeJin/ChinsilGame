package com.example.hyejin.kb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hyejin on 2016-11-30.
 */
public class CustomAdapter_multiwait extends BaseAdapter {
//    Context context;
    private ArrayList<list_item_multiwait> itemDatas =null;
    private LayoutInflater layoutInflater = null;
    String _msg;
    //생성자
    public CustomAdapter_multiwait(ArrayList<list_item_multiwait> itemDatas, Context ctx) {
        this.itemDatas = itemDatas;
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void setItemDatas(ArrayList<list_item_multiwait> itemDatas){
        this.itemDatas = itemDatas;
        this.notifyDataSetChanged();
    }

    // 현재 아이템의 수를 리턴
    @Override
    public int getCount() {
        return itemDatas != null ? itemDatas.size():0;
    }

    // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
    @Override
    public Object getItem(int position) {
        return (itemDatas != null && ( position>=0 && position < itemDatas.size()) ? itemDatas.get(position):null);
    }

    // 아이템 position의 ID 값 리턴
    @Override
    public long getItemId(int position) {
        return (itemDatas != null && ( position>=0 && position < itemDatas.size()) ? position:0);
    }

    // data-> listview에 뿌려주는 역할
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
        if (convertView == null) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_custom_list_multiwait, parent, false);

        }
            TextView room_name = (TextView) convertView.findViewById(R.id.room_name);
            room_name.setText(itemDatas.get(position).getRoom_name());
            list_item_multiwait itemData_temp = itemDatas.get(position);


            room_name.setText(itemData_temp.getRoom_name());
            return convertView;
        }

    }