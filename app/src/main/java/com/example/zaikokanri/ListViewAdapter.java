package com.example.zaikokanri;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

public final class ListViewAdapter extends ArrayAdapter {

    private static final int ITEM_BACKGROUND_COLOR_EVEN = Color.rgb(100, 149, 237);
    private static final int ITEM_BACKGROUND_COLOR_ODD = Color.WHITE;
    private static final int ITEM_BACKGROUND_COLOR_CHECKED = Color.GREEN;

    private MainActivity mainActivity;
    private LayoutInflater inflater;
    private int itemLayout;

    ListViewAdapter(final Context context, final int itemLayout, final MainActivity mainActivity) {
        super(context, itemLayout);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemLayout = itemLayout;
        this.mainActivity = mainActivity;
    }

    @Override
    @NonNull
    public View getView(final int position, View convertView, @NonNull final ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(itemLayout, parent, false);
            viewHolder = new ViewHolder(
                    (CheckBox) convertView.findViewById(R.id.item_checkbox),
                    (TextView) convertView.findViewById(R.id.item_time_text_view),
                    (TextView) convertView.findViewById(R.id.item_inventory_count_text_view),
                    (TextView) convertView.findViewById(R.id.item_comment_text_view)
            );
            convertView.setTag(viewHolder);

            // チェックボックスのリスナー
            viewHolder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override

                public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                    if (buttonView.isPressed()) {
                        final InventoryInfo inventoryInfo = (InventoryInfo) getItem(position);
                        inventoryInfo.setCheck(isChecked);

                        final View parentView = (View) buttonView.getParent();
                        changeBackgroundColor(parentView, position, isChecked);
                    }
                }
            });

            // 詳細ボタンのリスナー
            convertView.findViewById(R.id.item_detail_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    mainActivity.changeActivity(position);
                }
            });

            // 削除ボタンのリスナー
            convertView.findViewById(R.id.item_delete_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    remove(getItem(position));
                    notifyDataSetChanged();
                }
            });
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final InventoryInfo inventoryInfo = (InventoryInfo) getItem(position);
        viewHolder.getCheckBox().setChecked(inventoryInfo.isChecked());
        viewHolder.getTimeTextView().setText(inventoryInfo.getTimeString());
        viewHolder.getStockCountTextView().setText(inventoryInfo.getInventoryCountString());
        viewHolder.getCommentTextView().setText(inventoryInfo.getComment());

        changeBackgroundColor(convertView, position, viewHolder.getCheckBox().isChecked());

        return convertView;
    }

    // 背景色の変更
    private void changeBackgroundColor(final View view, final int position, final boolean isChecked) {
        int color = position % 2 == 0 ? ITEM_BACKGROUND_COLOR_EVEN : ITEM_BACKGROUND_COLOR_ODD;
        if (isChecked) {
            color = ITEM_BACKGROUND_COLOR_CHECKED;
        }
        view.setBackgroundColor(color);
    }
}
