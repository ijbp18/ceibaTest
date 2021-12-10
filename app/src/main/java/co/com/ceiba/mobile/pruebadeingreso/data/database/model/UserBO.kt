package co.com.ceiba.mobile.pruebadeingreso.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "users")
data class UserBO(
        @PrimaryKey
        @SerializedName("id")
        @Expose
        var id: Int,
        @SerializedName("name")
        @Expose
        val name: String,
        @SerializedName("email")
        @Expose
        val email: String,
        @SerializedName("phone")
        @Expose
        val phone: String

):Serializable