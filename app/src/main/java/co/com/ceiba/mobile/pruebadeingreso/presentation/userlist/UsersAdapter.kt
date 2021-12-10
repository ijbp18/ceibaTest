package co.com.ceiba.mobile.pruebadeingreso.presentation.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.domain.entities.UsersUI
import co.com.ceiba.mobile.pruebadeingreso.utils.OnItemSelected
import java.util.*

class UsersAdapter(private val onItemSelectedListener: OnItemSelected<UsersUI>) : RecyclerView.Adapter<UsersViewHolder>(){

    private var userItems: List<UsersUI> = arrayListOf()
    private var userItemsFiltered: List<UsersUI> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
            UsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false), onItemSelectedListener)

    override fun getItemCount(): Int = userItemsFiltered.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) = holder.bind(userItemsFiltered[position])

    fun setData(userItems: List<UsersUI>) {
        this.userItems = userItems
        this.userItemsFiltered = userItems
        notifyDataSetChanged()
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                userItemsFiltered = if (charString.isEmpty()) {
                    userItems
                } else {
                    val filteredList: MutableList<UsersUI> = ArrayList<UsersUI>()
                    for (row in userItems) {
                        if (row.name.lowercase(Locale.ROOT).contains(charString.lowercase(Locale.ROOT))) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = userItemsFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                userItemsFiltered = filterResults.values as ArrayList<UsersUI>
                notifyDataSetChanged()
            }
        }
    }
}