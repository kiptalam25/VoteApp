package com.sqlite.orderapp.repository;


import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class FirebaseSignUpInstance {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser;
    public MutableLiveData<FirebaseUser> firebaseUsers = new MutableLiveData<>();

    public MutableLiveData<Task> signInUser(String userNameSignIn, String emailSignIn, String passwordSignIn) {
        final MutableLiveData<Task> taskSignIn = new MutableLiveData<>();
        mAuth.createUserWithEmailAndPassword(emailSignIn, passwordSignIn).addOnCompleteListener(value -> {
            firebaseUser = mAuth.getCurrentUser();
            firebaseUsers.setValue(firebaseUser);
            taskSignIn.setValue(value);
        });

        return taskSignIn;
    }

}
