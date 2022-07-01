package com.rinoarias.listviewcardviewsitemjsondata

import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable


class Usuario : Serializable {
    var nombres: String

    var email: String

    var urlavatar: String

    constructor(nombres: String, email: String, urlavatar: String){
        this.nombres = nombres
        this.email = email
        this.urlavatar = urlavatar
    }

    @Throws(JSONException::class)
    constructor(a: JSONObject){
        this.nombres = a.getString("first_name").toString() + " " + a.getString("last_name").toString()
        this.email = a.getString("email").toString()
        this.urlavatar = a.getString("avatar").toString()
    }
}