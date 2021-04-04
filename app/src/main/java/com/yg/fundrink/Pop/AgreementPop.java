package com.yg.fundrink.Pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.yg.fundrink.Interface.OnAgreementPopListener;
import com.yg.fundrink.R;
import com.yg.fundrink.Activity.WebActivity;

public class AgreementPop extends PopupWindow {

    public Context mContext;
    private ImageView ivClose;

    private TextView tvIKnow;

    private TextView tvContent;

    private TextView tvExit;

    public AgreementPop(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init(){
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_protocol_agreement,null);

        ivClose = contentView.findViewById(R.id.iv_close);
        tvIKnow = contentView.findViewById(R.id.tv_btn_iknown);
        tvContent = contentView.findViewById(R.id.tv_content);
        tvExit = contentView.findViewById(R.id.tv_exit);

        SpannableString spannableString = new SpannableString("感谢您使用趣喝水！我们非常重视对您的个人信息保护，在您使用趣喝水提供的服务前，请阅读并同意《隐私协议》及《服务协议》。我们将严格按照您同意的各项条款使用您的个人信息，以便为您提供更好的服务。");
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#DD5745")), 45, 51, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#DD5745")), 52, 58, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //WebActivity.launch((Activity) mContext, "服务协议", BuildConfig.BASE_URL + "html/protocol.html");
                WebActivity.launch((Activity) mContext, "隐私协议", "http://shuabu.shyonggui.com/privacy.html");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, 45, 51, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //WebActivity.launch((Activity) mContext, "隐私协议", BuildConfig.BASE_URL + "html/privacy.html");
                WebActivity.launch((Activity) mContext, "服务协议", "http://shuabu.shyonggui.com/protocol.html");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, 51, 58, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        tvContent.setText(spannableString);
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());

        setContentView(contentView);
    }

    public void showAtCenter(OnAgreementPopListener listener){
        ivClose.setOnClickListener(v -> {
            listener.onPopClose();
        });

        tvIKnow.setOnClickListener(v -> {
            listener.onUserAgree();
        });

        tvExit.setOnClickListener(v -> {
            listener.onExit();
        });

        setBackgroundDark(0.2f);
        showAtLocation(((Activity)mContext).getWindow().getDecorView(), Gravity.CENTER,0,0);
    }

    /**
     * 0f全黑
     * 1f全透明
     *
     * @param alpha
     */
    public void setBackgroundDark(float alpha) {
        WindowManager.LayoutParams layoutParams = ((Activity) mContext).getWindow().getAttributes();
        layoutParams.alpha = alpha;
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) mContext).getWindow().setAttributes(layoutParams);
    }

}
