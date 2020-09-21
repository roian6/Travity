package com.david0926.travity.database

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

