package com.david0926.travity.database

import com.david0926.travity.model.UserModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

val db = FirebaseFirestore.getInstance();

/**
 * @see 구글계정용 :D
 */
fun addGoogleAccount(name: String, email: String) {
    db.collection("users")
            .get()
            .addOnSuccessListener {
                for (response in it) {
                    if (response.id == email) {
                        return@addOnSuccessListener
                    }
                }
                // Do something...
                db.collection("users")
                                .document(email)
                                .set(UserModel(name, email, timeNow()))
                        .addOnSuccessListener {
                            return@addOnSuccessListener
                        }
                        .addOnFailureListener {
                            return@addOnFailureListener
                        }
            }
            .addOnFailureListener {

            }
}

/**
 *  @return 파이어 스토어에 이미 이메일 정보가 존재하는지 반환
 */

fun timeNow(): String {
    return SimpleDateFormat("yyyy/MM/dd hh:mm aa", Locale.ENGLISH).format(Date())
}