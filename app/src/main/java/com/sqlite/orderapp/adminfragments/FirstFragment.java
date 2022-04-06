package com.sqlite.orderapp.adminfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sqlite.orderapp.R;
import com.sqlite.orderapp.databinding.FragmentFirstBinding;
import com.sqlite.orderapp.models.Role;

public class FirstFragment extends Fragment {
    FirebaseDatabase db=FirebaseDatabase.getInstance();
DatabaseReference databaseReference=db.getReference(Role.class.getSimpleName());
private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentFirstBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(view1 -> NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment));

        binding.btnSaveRole.setOnClickListener(v->{
            String text=binding.txtRole.getText().toString();

            Role role=new Role(text);
            databaseReference.push().setValue(role).addOnSuccessListener(unused ->
                    Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show()).addOnFailureListener(e -> Toast.makeText(getContext(), e.getMessage(),Toast.LENGTH_SHORT).show());
//            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();

        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}