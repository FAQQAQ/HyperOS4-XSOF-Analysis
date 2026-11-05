package com.miui.policeassist.view;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.miui.common.utils.h0;
import com.miui.earthquakewarning.utils.MsgObservable;
import com.miui.securitycenter.R;
import com.miui.warningcenter.policeassist.PaService;
import com.miui.warningcenter.policeassist.PaUtils;
import java.util.Observable;
import java.util.Observer;

/* JADX INFO: loaded from: classes3.dex */
public class PaLocationFloatingView extends LinearLayout implements Observer {
    private static final String TAG = "PaLocationFloatingView";
    private TextView flowTipsText;
    private boolean isAdded;
    private TextView locationStatus;
    private TextView locationText;
    private LinearLayout mContainerLocation;
    private RelativeLayout mContainerLocationStatus;
    private LinearLayout mContainerflowTips;
    private Context mContext;
    private int mDownX;
    private int mDownY;
    private WindowManager mWindowManager;
    private int moveAfterX;
    private int moveAfterY;
    private int moveBeforeX;
    private int moveBeforeY;

    /* JADX DEBUG: Don't trust debug lines info. Lines numbers was adjusted: min line is 1 */
    public PaLocationFloatingView(Context context) {
        super(context);
        init(context);
    }

    private void doLocateAndSendMessage() {
        if (this.mContainerflowTips.getVisibility() == 0) {
            MsgObservable.getInstance().sendEmptyMessage(4);
            Intent intent = new Intent(getContext(), (Class<?>) PaService.class);
            intent.setAction(PaService.ACTION_SEND_MESSAGE);
            getContext().startService(intent);
        }
    }

    private void init(Context context) {
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.warning_center_pa_location_float_view, (ViewGroup) null);
        this.flowTipsText = (TextView) viewInflate.findViewById(R.id.flow_tips);
        this.mContainerflowTips = (LinearLayout) viewInflate.findViewById(R.id.ll_flow_tips);
        this.mContainerLocation = (LinearLayout) viewInflate.findViewById(R.id.ll_flow_location);
        this.mContainerLocationStatus = (RelativeLayout) viewInflate.findViewById(R.id.ll_location);
        this.locationStatus = (TextView) viewInflate.findViewById(R.id.location_status);
        this.locationText = (TextView) viewInflate.findViewById(R.id.location_text);
        this.mContainerflowTips.setVisibility(0);
        this.mContainerLocation.setVisibility(8);
        if (PaUtils.getPoliceAssistToggle(this.mContext)) {
            this.flowTipsText.setText(R.string.warningcenter_policeassist_flow_locating);
            this.flowTipsText.setTextColor(context.getResources().getColor(R.color.white));
            doLocateAndSendMessage();
        } else {
            String string = context.getString(R.string.warningcenter_policeassist_flow_tips);
            SpannableString spannableString = new SpannableString(string);
            spannableString.setSpan(new UnderlineSpan(), 0, string.length(), 0);
            this.flowTipsText.setText(spannableString);
            this.flowTipsText.setTextColor(context.getResources().getColor(R.color.pa_float_view_tip_text_color));
        }
        addView(viewInflate);
    }

    public boolean isAdded() {
        return this.isAdded;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MsgObservable.getInstance().addObserver(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MsgObservable.getInstance().deleteObserver(this);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.moveBeforeX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            this.moveBeforeY = rawY;
            this.mDownX = this.moveBeforeX;
            this.mDownY = rawY;
            return false;
        }
        if (action == 1) {
            int rawX = (int) motionEvent.getRawX();
            int rawY2 = (int) motionEvent.getRawY();
            if (Math.abs(rawX - this.mDownX) > 10 || Math.abs(rawY2 - this.mDownY) > 10) {
                return false;
            }
            Log.i(TAG, "doLocateAndSendMessage click!");
            doLocateAndSendMessage();
            return false;
        }
        if (action != 2) {
            return false;
        }
        this.moveAfterX = (int) motionEvent.getRawX();
        this.moveAfterY = (int) motionEvent.getRawY();
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) getLayoutParams();
        int i10 = this.moveAfterX - this.moveBeforeX;
        int i11 = this.moveAfterY - this.moveBeforeY;
        layoutParams.x += i10;
        layoutParams.y += i11;
        this.mWindowManager.updateViewLayout(this, layoutParams);
        this.moveBeforeX = this.moveAfterX;
        this.moveBeforeY = this.moveAfterY;
        return false;
    }

    public void setAdded(boolean z10) {
        this.isAdded = z10;
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        if (obj instanceof Message) {
            final Message message = (Message) obj;
            post(new Runnable() { // from class: com.miui.policeassist.view.PaLocationFloatingView.1
                @Override // java.lang.Runnable
                public void run() {
                    Message message2 = message;
                    int i10 = message2.what;
                    if (i10 == 1) {
                        PaLocationFloatingView.this.locationText.setText((String) message2.obj);
                        PaLocationFloatingView.this.mContainerLocation.setVisibility(0);
                        if (h0.o()) {
                            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) PaLocationFloatingView.this.getLayoutParams();
                            layoutParams.y = -240;
                            PaLocationFloatingView.this.mWindowManager.updateViewLayout(PaLocationFloatingView.this, layoutParams);
                        }
                        PaLocationFloatingView.this.locationStatus.setVisibility(8);
                        PaLocationFloatingView.this.mContainerLocationStatus.setVisibility(0);
                        PaLocationFloatingView.this.mContainerflowTips.setVisibility(8);
                    } else if (i10 == 2) {
                        PaLocationFloatingView.this.mContainerLocation.setVisibility(8);
                        PaLocationFloatingView.this.mContainerflowTips.setVisibility(8);
                        PaLocationFloatingView.this.mContainerLocation.setVisibility(0);
                        PaLocationFloatingView.this.mContainerLocationStatus.setVisibility(0);
                        PaLocationFloatingView.this.locationStatus.setVisibility(0);
                        PaLocationFloatingView.this.locationStatus.setText(R.string.warningcenter_policeassist_flow_locfailed);
                    } else if (i10 == 3) {
                        PaLocationFloatingView.this.mContainerflowTips.setVisibility(8);
                        PaLocationFloatingView.this.mContainerLocation.setVisibility(0);
                        PaLocationFloatingView.this.mContainerLocationStatus.setVisibility(0);
                    } else if (i10 == 4) {
                        PaLocationFloatingView.this.mContainerLocation.setVisibility(8);
                        PaLocationFloatingView.this.mContainerLocationStatus.setVisibility(8);
                        PaLocationFloatingView.this.mContainerflowTips.setVisibility(0);
                        PaLocationFloatingView.this.flowTipsText.setText(R.string.warningcenter_policeassist_flow_locating);
                        PaLocationFloatingView.this.flowTipsText.setTextColor(PaLocationFloatingView.this.getResources().getColor(R.color.white));
                    }
                    PaLocationFloatingView.this.invalidate();
                }
            });
        }
    }

    public PaLocationFloatingView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public PaLocationFloatingView(Context context, @Nullable AttributeSet attributeSet, int i10) {
        super(context, attributeSet, i10);
        init(context);
    }
}
