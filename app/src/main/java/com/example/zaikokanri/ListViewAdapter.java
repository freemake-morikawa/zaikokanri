package com.example.zaikokanri;

import android.content.Context;
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

    private LayoutInflater inflater;
    private int itemLayout;

    ListViewAdapter(final Context context, final int itemLayout) {
        super(context, itemLayout);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemLayout = itemLayout;
    }

    @Override
    @NonNull
    public View getView(final int position, View convertView, @NonNull final ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(itemLayout, parent, false);
            viewHolder = new ViewHolder(
                    (CheckBox) convertView.findViewById(R.id.item_checkbox),
                    (TextView) convertView.findViewById(R.id.item_text_view_time),
                    (TextView) convertView.findViewById(R.id.item_text_view_stock_count),
                    (TextView) convertView.findViewById(R.id.item_text_view_comment)
            );
            convertView.setTag(viewHolder);

            // チェックボックスのリスナー
            viewHolder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override

                public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                    if (buttonView.isPressed()) {
                        final InventoryData inventoryData = (InventoryData) getItem(position);
                        inventoryData.setCheck(isChecked);

                        final View parentView = (View) buttonView.getParent();
                        changeBackgroundColor(parentView, position, isChecked);
                    }
                }
            });

            // 削除ボタンのリスナー
            convertView.findViewById(R.id.item_button_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    remove(getItem(position));
                    notifyDataSetChanged();
                }
            });
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final InventoryData inventoryData = (InventoryData) getItem(position);
        viewHolder.getCheckBox().setChecked(inventoryData.isChecked());
        viewHolder.getTimeTextView().setText(inventoryData.getTime());
        viewHolder.getStockCountTextView().setText(inventoryData.getStockCount());
        viewHolder.getCommentTextView().setText(inventoryData.getComment());

        changeBackgroundColor(convertView, position, viewHolder.getCheckBox().isChecked());

        return convertView;
    }

    // 背景色の変更
    private void changeBackgroundColor(final View view, final int position, final boolean isChecked) {
        if (isChecked) {
            view.setBackgroundColor(ITEM_BACKGROUND_COLOR_CHECKED);
            return;
        }
        final int color = position % 2 == 0 ? ITEM_BACKGROUND_COLOR_EVEN : ITEM_BACKGROUND_COLOR_ODD;
        view.setBackgroundColor(color);
    }
}
