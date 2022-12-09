package com.example.zaikokanri;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

public final class InventoryInfoListViewAdapter extends ArrayAdapter
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final int ITEM_BACKGROUND_COLOR_EVEN = Color.rgb(100, 149, 237);
    private static final int ITEM_BACKGROUND_COLOR_ODD = Color.WHITE;
    private static final int ITEM_BACKGROUND_COLOR_CHECKED = Color.GREEN;

    private LayoutInflater inflater;
    private int itemLayout;
    private View.OnClickListener onClickListener;

    InventoryInfoListViewAdapter(final Context context, final int itemLayout,
                                 final View.OnClickListener onClickListener) {
        super(context, itemLayout);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemLayout = itemLayout;
        this.onClickListener = onClickListener;
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
                    (TextView) convertView.findViewById(R.id.item_comment_text_view),
                    (Button) convertView.findViewById(R.id.item_detail_button),
                    (Button) convertView.findViewById(R.id.item_delete_button)
            );
            convertView.setTag(viewHolder);

            // チェックボックスのリスナー
            viewHolder.getCheckBox().setTag(position);
            viewHolder.getCheckBox().setOnCheckedChangeListener(this);

            // 詳細ボタンのリスナー
            viewHolder.getDetailButton().setTag(position);
            viewHolder.getDetailButton().setOnClickListener(onClickListener);

            // 削除ボタンのリスナー
            viewHolder.getDeleteButton().setTag(position);
            viewHolder.getDeleteButton().setOnClickListener(this);
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

    // チェックイベント
    @Override
    public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
        if (buttonView.getTag() == null) {
            return;
        }

        if (buttonView.isPressed()) {
            final int position = (int) buttonView.getTag();
            final InventoryInfo inventoryInfo = (InventoryInfo) getItem(position);
            inventoryInfo.setCheck(isChecked);

            final View parentView = (View) buttonView.getParent();
            changeBackgroundColor(parentView, position, isChecked);
        }
    }

    // クリックイベント
    @Override
    public void onClick(final View v) {
        if (v.getTag() == null) {
            return;
        }

        final int position = (int) v.getTag();
        remove(getItem(position));
        notifyDataSetChanged();
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
