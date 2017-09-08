package com.corycharlton.bittrexapi.demo.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class SelectableAdapter<VH extends SelectableAdapter.ViewHolder, I> extends RecyclerView.Adapter<VH> {

    private EventListener _eventListener;
    private int _selectedItemPosition;

    public abstract I getItem(int position);

    //public abstract int getItemPosition(I item);

    public int getSelectedItemPosition() {
        return _selectedItemPosition;
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        onBindViewHolder(viewHolder, getItem(position));
    }

    protected abstract void onBindViewHolder(VH holder, I item);

    protected void raiseOnItemClicked(int position) {
        final EventListener eventListener = _eventListener;
        if (eventListener != null) {
            eventListener.onItemClicked(position);
        }
    }

    protected void raiseOnItemSelected(int position) {
        final EventListener eventListener = _eventListener;
        if (eventListener != null) {
            eventListener.onItemSelected(position);
        }
    }

    public void setEventListener(EventListener eventListener) {
        _eventListener = eventListener;
    }

    /*
    public void setSelectedItem(I item) {
        final int position = getItemPosition(item);
        if (position != RecyclerView.NO_POSITION) {
            setSelectedItemPosition(position);
        }
    }
    */

    public void setSelectedItemPosition(int position) {
        _selectedItemPosition = position;

        notifyItemChanged(position);
    }

    // region EventListener
    public interface EventListener {
        void onItemClicked(int position);
        void onItemSelected(int position);
    }
    // endregion

    // region ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnFocusChangeListener {
        View rootLayout;

        ViewHolder(@NonNull View view) {
            super(view);

            ButterKnife.bind(this, view);

            rootLayout = view;
            rootLayout.setOnClickListener(this);
            rootLayout.setOnFocusChangeListener(this);
        }

        public Context getContext() {
            return rootLayout.getContext();
        }

        @CallSuper
        @Override
        public void onClick(View view) {
            raiseOnItemClicked(getAdapterPosition());
        }

        @CallSuper
        @Override
        public void onFocusChange(View view, boolean isFocused) {
            view.setActivated(isFocused);

            if (isFocused) {
                _selectedItemPosition = getAdapterPosition();
                raiseOnItemSelected(_selectedItemPosition);
            }
        }

        void requestFocus() {
            rootLayout.requestFocus();
        }
    }
    // endregion
}
