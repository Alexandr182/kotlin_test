package tapp.kotlintest

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.TextView
import kotlinx.android.synthetic.activity_main.*
import java.util

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_text_view.setText("trarara")
        mMainRecyclerView.setUpList()
    }

    fun RecyclerView.setUpList() {
        setLayoutManager(LinearLayoutManager(this@MainActivity))
        setAdapter(SimpleAdapter(this@MainActivity, listOf(DataHolder(5), DataHolder(6), DataHolder(8))))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    public class SimpleAdapter(context: Context, list: List<DataHolder>) : RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> () {

        var mList = list
        var mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getItemCount(): Int {
            return mList.size()
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): SimpleViewHolder? {
            return SimpleViewHolder(mLayoutInflater.inflate(R.layout.item_main_list, viewGroup, false))
        }

        override fun onBindViewHolder(viewHolder: SimpleViewHolder?, position: Int) {
            viewHolder!!.textview.setText("${mList[position].value}")
        }

        public inner class SimpleViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
            var textview = itemView!!.findViewById(R.id.item_tv_name) as TextView
            var button = itemView!!.findViewById(R.id.item_bt_action) as Button

            init {
                button setOnClickListener {
                    var pos = getAdapterPosition()
                    mList.indices.forEach {
                        var value = mList[it].value
                        mList[it].value = if (pos == it) value + 1 else value - 1
                    }
                    notifyDataSetChanged()
                }
            }
        }
    }

    data class DataHolder(v: Int) {
        var value: Int = v
    }
}
