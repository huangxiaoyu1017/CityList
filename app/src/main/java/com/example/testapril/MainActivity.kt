package com.example.testapril

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.promeg.pinyinhelper.Pinyin
import com.github.promeg.tinypinyin.lexicons.java.cncity.CnCityDict

import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {


    private lateinit var adapter: CityAdapter
    private var mDatas: ArrayList<CityBean> = ArrayList()
    private var mIndexBar: IndexBar? = null
    private var mTvSideBarHint: TextView? = null
    private var mRv: RecyclerView? = null
    private lateinit var mManager: LinearLayoutManager
    private lateinit var dataSortHelper: IndexBarDataHelperImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataSortHelper = IndexBarDataHelperImpl()
        Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance()))
        initDatas()
        mManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        mRv = findViewById(R.id.rvList)
        mRv?.layoutManager = mManager
        mIndexBar = findViewById(R.id.indexBar)
        mTvSideBarHint = findViewById(R.id.tvSideBarHint)
        adapter = CityAdapter(this, mDatas){
            Toast.makeText(this,mDatas[it].city,Toast.LENGTH_SHORT).show()
        }

        mRv?.adapter = adapter

        initRight()
    }

    private fun initRight() {
        mIndexBar?.setmPressedShowTextView(mTvSideBarHint)
            ?.setNeedRealIndex(false)
            ?.setmLayoutManager(mManager)
            ?.setSourceDatasAlreadySorted(true)
            ?.setmSourceDatas(mDatas)//设置数据
            ?.invalidate()

    }


    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private fun initDatas() {
        mDatas.clear()
        val stringArray = resources.getStringArray(R.array.data)//未排序的字段

        //转化为databean:
        stringArray.forEach {
            var bean = CityBean(it)
            mDatas.add(bean)
        }

        //先排序：
        dataSortHelper.sortSourceDatas(mDatas)

        var list: ArrayList<CityBean> = ArrayList()
        mDatas[0].cap = mDatas[0].baseIndexTag
        list.add(mDatas[0])

        for (i in 1 until mDatas.size) {
            val baseIndexTag = mDatas[i].baseIndexTag
            if (baseIndexTag != list[0].baseIndexTag) {
                list.clear()
                list.add(mDatas[i])
                mDatas[i].cap = baseIndexTag
            }
        }
    }

}