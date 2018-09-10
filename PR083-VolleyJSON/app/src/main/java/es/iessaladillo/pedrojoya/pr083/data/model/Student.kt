package es.iessaladillo.pedrojoya.pr083.data.model

import org.json.JSONException
import org.json.JSONObject

private const val KEY_NAME = "name"
private const val KEY_ADDRESS = "address"
private const val KEY_PHONE = "phone"
private const val KEY_GRADE = "grade"
private const val KEY_REPEATER = "repeater"
private const val KEY_AGE = "age"
private const val KEY_PHOTO = "photo"

data class Student(
        val photo: String? = null,
        val name: String,
        val age: Int,
        val grade: String? = null,
        val address: String? = null,
        val phone: String? = null,
        val isRepeater: Boolean = false) {

    @Throws(JSONException::class)
    constructor(jsonObject: JSONObject) : this(
            name = jsonObject.getString(KEY_NAME),
            address = jsonObject.getString(KEY_ADDRESS),
            phone = jsonObject.getString(KEY_PHONE),
            grade = jsonObject.getString(KEY_GRADE),
            isRepeater = jsonObject.getBoolean(KEY_REPEATER),
            age = jsonObject.getInt(KEY_AGE),
            photo = jsonObject.getString(KEY_PHOTO)
    )

}
