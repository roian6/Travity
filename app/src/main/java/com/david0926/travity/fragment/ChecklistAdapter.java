package com.david0926.travity.fragment;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.david0926.travity.R;
import com.david0926.travity.database.DataManagerKt;
import com.david0926.travity.dialog.DialogDelete;
import com.david0926.travity.model.TodoModel;

import java.util.ArrayList;

public class ChecklistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<TodoModel> list;
    String fragmentType;
    private final int FOOTER = -10001;
    private final int DEFAULT = -10000;
    private final int INPUT = -10002;

    Context context;
    public ChecklistAdapter(ArrayList<TodoModel> list, String type, Context context) {
        this.list = list;
        fragmentType = type;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder vh = null;
        Log.d("now view type", viewType + " ");
        if(viewType == DEFAULT) {
            View view = inflater.inflate(R.layout.row_main2_checklist, parent, false);
            vh = new ChecklistAdapter.ViewHolder(view);
        } else if (viewType == FOOTER) {
            View view = inflater.inflate(R.layout.row_main2_checklist_add, parent, false);
            vh = new ChecklistAdapter.FooterViewHolder(view);
        } else if (viewType == INPUT) {
            View view = inflater.inflate(R.layout.row_main2_checklist_edit, parent, false);
            vh = new ChecklistAdapter.EditViewHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int position) {
        if(h instanceof  ChecklistAdapter.FooterViewHolder) {
            FooterViewHolder holder = (FooterViewHolder) h;
            holder.itemView.setOnClickListener(item -> {
                list.add(new TodoModel(null, false));
                notifyDataSetChanged();
            });
        } else if(h instanceof  ChecklistAdapter.EditViewHolder) {

            EditViewHolder holder = (EditViewHolder) h;
            holder.delete.setOnClickListener(item -> {
                DialogDelete dialog = new DialogDelete(context, (view)-> {
                    // 삭제 눌렀을 때
                    list.remove(position);
                    holder.text.setText("");
                    notifyDataSetChanged();
                }, (view) -> {
                    // 취소 눌렀을 때
                });

                dialog.show();
            });
            holder.text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if(i == EditorInfo.IME_ACTION_NEXT) {
                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);

                        list.remove(position);
                        list.add(new TodoModel(textView.getText().toString(), false));
                        holder.text.setText("");
                        notifyDataSetChanged();
                        if(fragmentType.equals("Todo"))
                            DataManagerKt.updateTodos(context, list);
                        else
                            DataManagerKt.updateThings(context, list);
                        return true;
                    }
                    return false;
                }
            });

        } else if(h instanceof  ChecklistAdapter.EmptyViewHolder) {
            EmptyViewHolder holder = (EmptyViewHolder) h;


        } else {
            ViewHolder holder = (ViewHolder) h;

            holder.text.setText(list.get(position).getMsg());
            holder.delete.setOnClickListener(item -> {
                DialogDelete dialog = new DialogDelete(context, (view)-> {
                    // 삭제 눌렀을 때
                    list.remove(position);
                    notifyDataSetChanged();
                    if(fragmentType.equals("Todo"))
                        DataManagerKt.updateTodos(context, list);
                    else
                        DataManagerKt.updateThings(context, list);
                }, (view) -> {
                    // 취소 눌렀을 때
                });
                dialog.show();

            });

            if(list.get(position).isFinished()) {
                holder.check.setImageDrawable(context.getDrawable(R.drawable.ic_check_selected));
            } else {
                holder.check.setImageDrawable(context.getDrawable(R.drawable.ic_check_normal_primary));
            }

            holder.check.setOnClickListener(item -> {
                list.get(position).setFinished(!list.get(position).isFinished());
                if(list.get(position).isFinished()) {
                    holder.check.setImageDrawable(context.getDrawable(R.drawable.ic_check_selected));
                } else {
                    holder.check.setImageDrawable(context.getDrawable(R.drawable.ic_check_normal_primary));
                }
                if(fragmentType.equals("Todo"))
                    DataManagerKt.updateTodos(context, list);
                else
                    DataManagerKt.updateThings(context, list);
            });
        }
    }

    @Override
    public int getItemViewType(int position) {


        if(position == list.size())
            return FOOTER;
        else
            if(list.get(position).getMsg() == null)
                    return INPUT;
                else
                    return DEFAULT;


    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView delete, check;
        ViewHolder(View view) {
            super(view);

            text = view.findViewById(R.id.textView23);
            delete = view.findViewById(R.id.imageView16);
            check = view.findViewById(R.id.imageView14);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        FooterViewHolder(View view) {
            super(view);

        }
    }

    public class EditViewHolder extends RecyclerView.ViewHolder {
        EditText text;
        ImageView delete, check;
        EditViewHolder(View view) {
            super(view);

            text = view.findViewById(R.id.textView23);
            delete = view.findViewById(R.id.imageView16);
            check = view.findViewById(R.id.imageView14);
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {

        EmptyViewHolder(View view) {
            super(view);

        }
    }
}
