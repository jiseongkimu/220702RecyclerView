package org.techtown.recyclerview

import android.content.ClipData
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.techtown.recyclerview.databinding.ItemViewBinding

// 이런방식도 있습니다
class Adapter(
//    private val listItems: List<GetUserResp>
) : RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    private var listItems : List<GetUserResp> ?= null

    fun submitList(list : List<GetUserResp>) {
        listItems = list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ItemViewHolder {
        //데이터바인딩 적용했으니 레이아웃인플레이트를 이런방식으로
        val itemViewBinding: ItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_view, parent, false // 여긴 국룰
        )
        return ItemViewHolder(itemViewBinding) // 레이아웃 inflate한걸 itemviewholder에 넘겨줌
        // 그래야 저 레이아웃 데이터바인딩의 아이디에 접근이 가능
    }

    override fun getItemCount(): Int {
        return listItems?.size ?: 0 // 널일수도있으니 없으면 0
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // list 포지션하나하나 아이템 bind에 넣어줌
        listItems?.get(position)?.let { holder.onBind(it) } // 리스트의 각 포지션 아이템을 onBind함수에 넘겨줌

    }

    inner class ItemViewHolder(
        private val binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) { // xml의 경로? 부모? 그냥 xml임 R.layout.item_view
        fun onBind(item : GetUserResp) { // 포지션 0번~n번까지의 아이템이 여기에 하나하나 꽂혀서 xml에 데이터 바인딩
            // 데이터바인딩 xml variable에 있는 user에 item을 넣어줌 varaible에 있는 정한
            // 변수명이랑 똑같이 맞춰줘야됨
            // xml에서 android:text="@{user.login}" 이런식으로 써줬으니 item만 꽂으면 알아서 해줌
            binding.user = item
        }
    }

}