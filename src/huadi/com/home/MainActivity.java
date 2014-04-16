package huadi.com.home;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity
{
	ImageView homeButton;
	private final Handler handler = new Handler();
	private final Runnable runnable = new Runnable()
	{
		public void run()
		{
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 防止創新的task於當前的task中
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 全螢幕
		setContentView(R.layout.main);
		homeButton = (ImageView) findViewById(R.id.imageButton1);
		homeButton.setOnClickListener(ClickButton);
		homeButton.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						homeButton.setImageResource(R.drawable.button_down);
						handler.postDelayed(runnable, 30); //至少要 50(ms) 才觸發
						break;
					case MotionEvent.ACTION_UP:
						homeButton.setImageResource(R.drawable.button_up);
						handler.removeCallbacks(runnable);
						break;
				}
				return false;
			}
		});
	}

	private Button.OnClickListener ClickButton = new Button.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			// moveTaskToBack(true); // 背景執行
			// Intent intent = new Intent(Intent.ACTION_MAIN);
			// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// //防止創新的task於當前的task中
			// intent.addCategory(Intent.CATEGORY_HOME);
			// startActivity(intent);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
			case R.id.action_settings:
				new AlertDialog.Builder(this).setTitle("關於")
						.setMessage("huadi73@gmail.com").show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK) // 讓 Back 跟 Home一樣的效果
		{
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 防止創新的task於當前的task中
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	// @Override //讓 Home 鍵無法使用
	// public void onAttachedToWindow() {
	// // TODO Auto-generated method stub
	// this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
	// super.onAttachedToWindow();
	// }
}
