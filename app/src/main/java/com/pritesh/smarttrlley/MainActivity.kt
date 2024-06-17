package com.pritesh.smarttrlley

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import java.sql.Connection
import java.sql.DriverManager

class MainActivity : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    var mchat: TabItem? = null
    var maddpost: TabItem? = null
    var mhome: TabItem? = null
    var viewPager: ViewPager? = null
    var pagerAdapter: PagerAdapter? = null
    lateinit var qrScanner: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tabLayout=findViewById(R.id.include)
        mchat=findViewById(R.id.searchTab)
        mhome=findViewById(R.id.homeTab)
        maddpost=findViewById(R.id.settingTab)
        viewPager=findViewById(R.id.fragmentcontainer)
        qrScanner = findViewById(R.id.qrScannerBtn)

        pagerAdapter= PagerAdapter(supportFragmentManager,tabLayout!!.tabCount)
        viewPager!!.adapter=pagerAdapter

        viewPager!!.currentItem = 1
        pagerAdapter!!.notifyDataSetChanged()

        tabLayout!!.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
                if (viewPager!!.currentItem == 0 || viewPager!!.currentItem == 1 || viewPager!!.currentItem == 2 || viewPager!!.currentItem == 3) {
                    pagerAdapter!!.notifyDataSetChanged()
                    qrScanner.isVisible=viewPager!!.currentItem == 1
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        qrScanner.setOnClickListener {
            startActivity(Intent(this,QrScannerAcrivity::class.java))
        }

        fetch()
    }

    fun connect(): Connection? {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
            val url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12648051?serverTimezone=UTC"
            val user = "sql12648051"
            val password = "vFXHu5gxVM"
            return DriverManager.getConnection(url, user, password)
        } catch (e: Exception) {
            //e.printStackTrace()
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            Log.d("sql",e.toString())
            return null
        }
    }

    fun fetch(){
        val connection = connect()
        connection?.let {
            try {
                val statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT * FROM User")

                while (resultSet.next()) {
                    val columnName = resultSet.getString("UserId")
                    Toast.makeText(this, columnName, Toast.LENGTH_SHORT).show()
                }
                resultSet.close()
                statement.close()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection.close()
            }
        }
    }
}