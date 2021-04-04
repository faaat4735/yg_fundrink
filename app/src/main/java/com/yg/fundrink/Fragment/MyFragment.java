package com.yg.fundrink.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.bugly.beta.Beta;
import com.yg.fundrink.BuildConfig;
import com.yg.fundrink.Activity.MainActivity;
import com.yg.fundrink.Adapter.MyListAdaper;
import com.yg.fundrink.DataList.Response.MyListItem;
import com.yg.fundrink.Interface.OnItemClickListener;
import com.yg.fundrink.R;
import com.yg.fundrink.Utils.ClipboardUtils;
import com.yg.fundrink.Utils.UserInfoUtils;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends BaseFragment {
    private MyListAdaper adaper;
    private ImageView iv_headicon;
    private TextView tv_nickname;
    private TextView tv_invitecode;
    private Button btn_copy_invitecode;

    @Override
    public int getResId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        List<MyListItem> myList = new ArrayList<>();
        myList.add(new MyListItem("意见反馈", R.drawable.icon_my_1, R.drawable.icon_arrow));
        myList.add(new MyListItem("隐私政策", R.drawable.icon_my_2, R.drawable.icon_arrow));
        myList.add(new MyListItem("服务协议", R.drawable.icon_my_3, R.drawable.icon_arrow));
        myList.add(new MyListItem("关于我们", R.drawable.icon_my_4, R.drawable.icon_arrow));
        myList.add(new MyListItem("当前版本", R.drawable.icon_my_5, R.drawable.icon_arrow, "V"+ BuildConfig.VERSION_NAME));
//        myList.add(new MyListItem("身体数据", R.drawable.icon_my_5, R.drawable.icon_arrow, "V"+BuildConfig.VERSION_NAME));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adaper = new MyListAdaper(myList, R.layout.item_my_list);
        recyclerView.setAdapter(adaper);
        iv_headicon = view.findViewById(R.id.iv_headicon);
        tv_nickname = view.findViewById(R.id.tv_nickname);
        tv_invitecode = view.findViewById(R.id.tv_invitecode);
        btn_copy_invitecode = view.findViewById(R.id.btn_copy_invitecode);
    }

    @Override
    public void initData(View view) {
//        if (UserInfoUtils.getInstance().getUserInfo().getHeadimgurl() != "") {
//            Glide.with(iv_headicon.getContext())
//                    .load(PlayerManger.getInstance().getPlayerInfo().getHeadimgurl())
//                    .into(iv_headicon);
//        }
        tv_nickname.setText(UserInfoUtils.getInstance().getUserInfo().getNickname());
        tv_invitecode.setText("邀请码: " + UserInfoUtils.getInstance().getUserInfo().getInvitedCode());
    }

    @Override
    public void initEvent(View view) {
        adaper.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e("onItemClick", position+"");
                switch (position) {
                    case 0 :
                        ((MainActivity) getActivity()).onViewChange("myfragment", "advice");
                        break;
                    case 1 :
                        ((MainActivity) getActivity()).onViewChange("web", "http://shuabu.shyonggui.com/privacy.html", "隐私政策");
                        break;
                    case 2 :
                        ((MainActivity) getActivity()).onViewChange("web", "http://shuabu.shyonggui.com/protocol.html", "服务协议");
                        break;
                    case 3 :
                        ((MainActivity) getActivity()).onViewChange("web", "http://shuabu.shyonggui.com/about.html", "关于我们");
                        break;
                    case 4 :
                        // 引入bugly
                        Beta.checkAppUpgrade(true,false);
                        break;
                }
            }
        });
        btn_copy_invitecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardUtils.copy((MainActivity) getActivity(), UserInfoUtils.getInstance().getUserInfo().getInvitedCode());
            }
        });
    }
}
