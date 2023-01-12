package com.example.zaikokanri;

import android.os.AsyncTask;

import com.example.zaikokanri.db.InventoryData;

public class OperateInventoryDataTask extends AsyncTask<InventoryData, Void, Void> {

    private CallBackTask callBackTask;
    private ExecuteTask task;

    @Override
    protected Void doInBackground(final InventoryData... inventoryData) {
        task.task();
        return null;
    }

    @Override
    protected void onPostExecute(final Void result) {
        callBackTask.callBack();
    }

    public void setCallBackTask(CallBackTask callBackTask) {
        this.callBackTask = callBackTask;
    }

    public void setTask(final ExecuteTask task) {
        this.task = task;
    }

    public abstract static class CallBackTask {
        public void callBack() {
        }
    }

    public static class ExecuteTask {
        public void task() {
        }
    }
}

