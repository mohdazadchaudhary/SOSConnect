package com.example.sosconnect;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<ContactModel> arrContact;

    public RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrContact) {
        this.context = context;
        this.arrContact = arrContact;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel contact = arrContact.get(position);

        if (contact == null) return;

        holder.imgContact.setImageResource(contact.img);
        holder.txtName.setText(contact.name != null ? contact.name : "Unknown");
        holder.txtNumber.setText(contact.number != null ? contact.number : "N/A");

        holder.btnCall.setOnClickListener(v -> {
            if (contact.number != null && !contact.number.trim().isEmpty()) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.number));

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    if (context instanceof Activity) {
                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    } else {
                        Toast.makeText(context, "Unable to request permission: Invalid context", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    context.startActivity(callIntent);
                }
            } else {
                Toast.makeText(context, "Phone number is not available", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnMessage.setOnClickListener(v -> {
            if (contact.number != null && !contact.number.trim().isEmpty()) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:" + contact.number));
                smsIntent.putExtra("sms_body", contact.msg != null ? contact.msg : "Emergency!");
                context.startActivity(smsIntent);
            } else {
                Toast.makeText(context, "Phone number is not available", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnEmail.setOnClickListener(v -> {
            if (contact.email != null && !contact.email.trim().isEmpty()) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{contact.email});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Emergency Contact");
                emailIntent.putExtra(Intent.EXTRA_TEXT, contact.msg != null ? contact.msg : "Emergency message");
                try {
                    context.startActivity(Intent.createChooser(emailIntent, "Send Email"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "No email clients installed.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Email address is not available", Toast.LENGTH_SHORT).show();
            }
        });

        holder.setAnimation(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return arrContact != null ? arrContact.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtNumber;
        ImageView imgContact;
        LinearLayout llRow;
        ImageButton btnCall, btnMessage, btnEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            imgContact = itemView.findViewById(R.id.imgContact);
            llRow = itemView.findViewById(R.id.llRow);
            btnCall = itemView.findViewById(R.id.btnCall);
            btnMessage = itemView.findViewById(R.id.btnMessage);
            btnEmail = itemView.findViewById(R.id.btnEmail);
        }

        public void setAnimation(View viewToAnimate) {
            Animation slideIn = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(slideIn);
        }
    }
}
