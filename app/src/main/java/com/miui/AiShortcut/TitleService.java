package com.miui.AiShortcut;


import android.content.*;
import android.os.*;
import android.preference.*;
import android.service.quicksettings.*;
import java.lang.reflect.*;

public class TitleService extends TileService
{
	//添加
	@Override
	public void onTileAdded() {
		super.onTileAdded();
		setQuickSettingColor();
	}

	//移除
	@Override
	public void onTileRemoved() {
		super.onTileRemoved();
	}

	//点击
	@Override
	public void onClick() {
		super.onClick();
		collapseStatusBar(getBaseContext());
		cmdUtil.openMiAiShortcut(TitleService.this);

	}

	//只有添加后才调用
	//通知栏下拉
    @Override
    public void onStartListening () {
		super.onStartListening();
		setQuickSettingColor();
    }
	
    //通知栏关闭
    @Override
    public void onStopListening () {
		super.onStopListening();
    }

	//设置磁贴颜色
	public void setQuickSettingColor(){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		boolean b = pref.getBoolean("type",false);
		if (b) {
            getQsTile().setState(Tile.STATE_INACTIVE);
			//更改成非活跃状态(灰色)
        } else {
            getQsTile().setState(Tile.STATE_ACTIVE);
			//更改成活跃状态(白色)
        }
        getQsTile().updateTile();//更新Tile
	}
	
	public static void collapseStatusBar(Context context)
	{
		try
		{
			Object statusBarManager = context.getSystemService("statusbar");
			Method collapse;

			if (Build.VERSION.SDK_INT <= 16)
			{
				collapse = statusBarManager.getClass().getMethod("collapse");
			}
			else
			{
				collapse = statusBarManager.getClass().getMethod("collapsePanels");
			}
			collapse.invoke(statusBarManager);
		}
		catch (Exception localException)
		{
			localException.printStackTrace();
		}
	}
}

