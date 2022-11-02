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

public class ListViewAdapter extends ArrayAdapter {
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
            viewHolder = new ViewHolder();
            viewHolder.checkBox = convertView.findViewById(R.id.item_checkbox);
            viewHolder.timeTextView = convertView.findViewById(R.id.item_time_textview);
            viewHolder.stockCountTextView = convertView.findViewById(R.id.item_stock_count_textview);
            viewHolder.commentTextView = convertView.findViewById(R.id.item_comment_textview);
            convertView.setTag(viewHolder);

            // チェックボックスのリスナー
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        final InventoryData inventoryData = (InventoryData) getItem(position);
        viewHolder.checkBox.setChecked(inventoryData.getCheck());
        viewHolder.timeTextView.setText(inventoryData.getTime());
        viewHolder.stockCountTextView.setText(inventoryData.getStockCount());
        viewHolder.commentTextView.setText(inventoryData.getComment());

        changeBackgroundColor(convertView, position, viewHolder.checkBox.isChecked());

        return convertView;
    }

    // Viewを保持するクラス
    private class ViewHolder {
        public CheckBox checkBox;
        public TextView timeTextView;
        public TextView stockCountTextView;
        public TextView commentTextView;
    }

    // 背景色の変更
    private static final int EVEN_ITEM_BACKGROUND_COLOR = Color.rgb(100, 149, 237);
    private static final int ODD_ITEM_BACKGROUND_COLOR = Color.WHITE;
    private static final int CHECKED_ITEM_BACKGROUND_COLOR = Color.GREEN;

    private void changeBackgroundColor(final View view, final int position, final boolean isChecked) {
        if (isChecked) {
            view.setBackgroundColor(CHECKED_ITEM_BACKGROUND_COLOR);
            return;
        }
        final int color = position % 2 == 0 ? EVEN_ITEM_BACKGROUND_COLOR : ODD_ITEM_BACKGROUND_COLOR;
        view.setBackgroundColor(color);
    }
}
