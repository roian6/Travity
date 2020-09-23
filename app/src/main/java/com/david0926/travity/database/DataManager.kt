package com.david0926.travity.database

import android.content.Context
import android.util.Log
import com.david0926.travity.model.FlightModel
import com.david0926.travity.model.NotificationModel
import com.david0926.travity.model.TodoModel
import com.david0926.travity.model.UserModel
import com.david0926.travity.util.UserCache
import kotlinx.android.synthetic.main.activity_login.view.*
import org.json.JSONObject

/**
 * @param collection 콜렉션 이름
 * @param document 문서 이름
 * @return 넘겨주신 "콜렉선" 안에 "문서" 에 있는 모든 필드값을 드려요.. 맵으로... 콜백으로....
 * @sample  DataManagerKt.getDocument("users", "roian6@naver.com", stringMap -> {
                        System.out.println(stringMap.get("name")); // 출력결과 : roain6
                        return null; // 필요한 이유는 모르겠지만 에러나 안나려면...^^
                   });
 */
fun getDocument(collection: String, document:String, callback: (Map<String,Any>) -> Unit) {
    db.collection(collection)
            .get()
            .addOnSuccessListener {
                for(response in it) {
                    if(response.id == document)
                        callback(response.data)
                }
            }
}

/** 위에꺼하고 거의 똑같아요!
 *  전체 필드값을 맵으로 안주고 JSON을 String형식으로 드려요!
 *  JSONObject로 바로 뽑을수 있는데.. Gson 쓰실거 같아서 일단 String...^^
 */
fun getDocumentToJSON(collection: String, document:String, callback: (String) -> Unit) {
    db.collection(collection)
            .get()
            .addOnSuccessListener {
                for(response in it) {
                    if(response.id == document) {
                        var json = JSONObject()
                        for(data in response.data.entries) {
                            json.put(data.key, data.value)
                        }
                        callback(json.toString());
                    }
                }
            }
}

/**
 *  "자신의" 비행 기록에 대해서만 수정하여 업로드 합니다.
 *   변경사항은 UserCache에 자동으로 반영됩니다.
 */
fun updateFlights(context: Context, arr: ArrayList<FlightModel>) {
    var usermodel = UserCache.getUser(context);
    usermodel.flightModels = arr;
    db.collection("users")
            .document(usermodel.email)
            .set(usermodel)
            .addOnSuccessListener {
                Log.w("DataManager", "성공적으로 비행 기록을 저장하였습니다.")
            }
            .addOnFailureListener {

            }
    UserCache.setUser(context, usermodel)
}

/**
 *  "자신의"  할일 기록에 대해서만 수정하여 업로드 합니다.
 *   변경사항은 UserCache에 자동으로 반영됩니다.
 */
fun updateTodos(context: Context,arr: ArrayList<TodoModel>) {
    var usermodel = UserCache.getUser(context);
    usermodel.todoModels = arr;
    db.collection("users")
            .document(usermodel.email)
            .set(usermodel)
            .addOnSuccessListener {
                Log.e("DataManager", "성공적으로 할일 기록을 저장하였습니다.")
            }
            .addOnFailureListener {

            }
    UserCache.setUser(context, usermodel)
}

/**
 *  "자신의"  알람 기록에 대해서만 수정하여 업로드 합니다.
 *   변경사항은 UserCache에 자동으로 반영됩니다.
 */
fun updateNotifications(context: Context, arr: ArrayList<NotificationModel>) {
    var usermodel = UserCache.getUser(context);
    usermodel.notificationModels = arr;
    db.collection("users")
            .document(usermodel.email)
            .set(usermodel)
            .addOnSuccessListener {
                Log.e("DataManager", "성공적으로 알람 기록을 저장하였습니다.")
            }
            .addOnFailureListener {

            }
    UserCache.setUser(context, usermodel)
}

/**
 *  "자신의"  현재 UserCache에 있는 모든 내용을 파이어스토어에 업로드 합니다.
 *   UserCache에 수정된 내용이 있으면 전부  파이어스토어에 반영됩니다.
 */
fun updateAll(context: Context) {
    var usermodel = UserCache.getUser(context);
    db.collection("users")
            .document(usermodel.email)
            .set(usermodel)
            .addOnSuccessListener {
                Log.e("DataManager", "성공적으로 유저 정보를 저장하였습니다.")
            }
            .addOnFailureListener {

            }
    UserCache.setUser(context, usermodel)
}