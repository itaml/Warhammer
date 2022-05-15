package com.example.warhammer40000
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    var adapter: MyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setNavigationItemSelectedListener (this)

        var list = ArrayList<ListItem>()

        list.addAll(fillArras(resources.getStringArray(R.array.ig),
            resources.getStringArray(R.array.ig_content),getImageId(R.array.ig_image_array)))
        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(list,this)
        rcView.adapter = adapter


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.id_ig ->
            {
                Toast.makeText(this,"Имперская Гвардия",Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.ig),
                    resources.getStringArray(R.array.ig_content),getImageId(R.array.ig_image_array)))
            }
            R.id.id_eldar ->
            {
                Toast.makeText(this,"Эльдар",Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.eldar),
                    resources.getStringArray(R.array.eldar_content),getImageId(R.array.eldar_image_array)))

            }
            R.id.id_chaos ->
            {
                Toast.makeText(this,"Космодесант Хаоса",Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.chaos),
                    resources.getStringArray(R.array.chaos_content),getImageId(R.array.chaos_image_array)))
            }
            R.id.id_orks ->
            {
                Toast.makeText(this,"Орки",Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.orks),
                    resources.getStringArray(R.array.orks_content),getImageId(R.array.orks_image_array)))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
    fun fillArras(titleArray:Array<String>,contentArray:Array<String>,imageArray:IntArray):List<ListItem>
    {
        var listItemArray = ArrayList<ListItem>()
        for(n in 0..titleArray.size - 1)
        {
            var listItem = ListItem(imageArray[n],titleArray[n],contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    fun getImageId(imageArrayId:Int):IntArray
    {
        var tArray:TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for(i in ids.indices)
        {
            ids[i] = tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids
    }

}