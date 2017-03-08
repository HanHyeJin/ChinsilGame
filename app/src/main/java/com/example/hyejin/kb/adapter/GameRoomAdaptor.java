package com.example.hyejin.kb.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hyejin.kb.R;
import com.example.hyejin.kb.dto.GameRoom;

import java.util.List;

/**
 * Created by Taehoon Yoo
 * User  : NOEP
 * Date  : 2016. 12. 1.
 * Time  : 오후 11:15
 * Page  : http:noep.github.io
 * Email : noep@naver.com
 * Desc  :
 */
public class GameRoomAdaptor extends BaseAdapter {

    private List<GameRoom.Response> list;
    private LayoutInflater inflater;

    public GameRoomAdaptor(List<GameRoom.Response> list, LayoutInflater inflater) {
        this.list = list;
        this.inflater = inflater;
    }

    public void setList(List<GameRoom.Response> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public GameRoom.Response getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_custom_list_multiwait, parent, false);
        }

        TextView roomName = (TextView) convertView.findViewById(R.id.room_name);
        roomName.setText(list.get(position).getName());

        return convertView;
    }
}
