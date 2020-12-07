package com.example.metro2

import android.content.Context
import org.json.JSONObject
import com.google.gson.Gson


open class JsonHelper(private var context: Context) {
    private var newspaperList: MutableList<LineModel>? = null

    open fun getBranchData(): List<LineModel>? {
        if (newspaperList == null)
            newspaperList = ArrayList()

        try {
            val jsonObject = JSONObject(getJSONFromAssets("stations.json"))
            val jsonArray = jsonObject.getJSONArray("metro")
            val k = jsonArray.length()

            for (i in 0 until k) {
                val tempJsonObject = jsonArray.getJSONObject(i).toString()
                val gson = Gson()
                val newsPaper = gson.fromJson<LineModel>(tempJsonObject, LineModel::class.java)
                newspaperList?.add(newsPaper)
            }
            return newspaperList
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private fun getJSONFromAssets(fileName: String): String? {
        val json: String
        try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return json
    }
}