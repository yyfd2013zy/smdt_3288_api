package com.smdt.androidapi;


import android.app.Activity;
import android.app.smdt.SmdtManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GpioActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {
	private static final String TAG="GpioActivity";
	private TextView getDirIo_txt, readIo_txt;
	private SmdtManager smdtManager;

	private Spinner setDirIo_number, setDirIo_direction, setDirIo_hi_low,
			getDirIo_number, writeIo_number, writeIo_dir_num, readIo_number;

	private int dir_set_io = 1;
	private int dir_set_impor = 0;
	private int dir_set_value = 0;
	private int dir_get_io = 1;

	private int write_io = 1;
	private int write_impor = 0;

	private int read_io = 1;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private TextView getDirIo_txt_w, readIo_txt_w;
	//private SmdtManager smdtManager;

	private Spinner setDirIo_number_w, setDirIo_direction_w, setDirIo_hi_low_w,
			getDirIo_number_w, writeIo_number_w, writeIo_dir_num_w, readIo_number_w;

	private int dir_set_io_w = 1;
	private int dir_set_impor_w = 0;
	private int dir_set_value_w = 0;
	private int dir_get_io_w = 1;

	private int write_io_w = 1;
	private int write_impor_w = 0;

	private int read_io_w = 1;
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.gpio);
		smdtManager = SmdtManager.create(this);
		findViewById(R.id.setDirIo_btn).setOnClickListener(this);
		findViewById(R.id.getDirIo_btn).setOnClickListener(this);
		findViewById(R.id.writeIo_btn).setOnClickListener(this);
		findViewById(R.id.readIo_btn).setOnClickListener(this);
		readIo_txt = (TextView) findViewById(R.id.readIo_txt);
		getDirIo_txt = (TextView) findViewById(R.id.getDirIo_txt);
		setDirIo_number = (Spinner) findViewById(R.id.setDirIo_number);
		setDirIo_number.setOnItemSelectedListener(this);
		setDirIo_direction = (Spinner) findViewById(R.id.setDirIo_direction);
		setDirIo_direction.setOnItemSelectedListener(this);
		setDirIo_hi_low = (Spinner) findViewById(R.id.setDirIo_hi_low);
		setDirIo_hi_low.setOnItemSelectedListener(this);
		getDirIo_number = (Spinner) findViewById(R.id.getDirIo_number);
		getDirIo_number.setOnItemSelectedListener(this);
		writeIo_number = (Spinner) findViewById(R.id.writeIo_number);
		writeIo_number.setOnItemSelectedListener(this);
		writeIo_dir_num = (Spinner) findViewById(R.id.writeIo_dir_num);
		writeIo_dir_num.setOnItemSelectedListener(this);
		readIo_number = (Spinner) findViewById(R.id.readIo_number);
		readIo_number.setOnItemSelectedListener(this);
////////////////////////////////////////////////////////////////////////////////////////////////////
		findViewById(R.id.setDirIo_btn_w).setOnClickListener(this);
		findViewById(R.id.getDirIo_btn_w).setOnClickListener(this);
		findViewById(R.id.writeIo_btn_w).setOnClickListener(this);
		findViewById(R.id.readIo_btn_w).setOnClickListener(this);
		readIo_txt_w = (TextView) findViewById(R.id.readIo_txt_w);//String[] a;a.length;
		getDirIo_txt_w = (TextView) findViewById(R.id.getDirIo_txt_w);
		setDirIo_number_w = (Spinner) findViewById(R.id.setDirIo_number_w);
		setDirIo_number_w.setOnItemSelectedListener(this);
		setDirIo_direction_w = (Spinner) findViewById(R.id.setDirIo_direction_w);
		setDirIo_direction_w.setOnItemSelectedListener(this);
		setDirIo_hi_low_w = (Spinner) findViewById(R.id.setDirIo_hi_low_w);
		setDirIo_hi_low_w.setOnItemSelectedListener(this);
		getDirIo_number_w = (Spinner) findViewById(R.id.getDirIo_number_w);
		getDirIo_number_w.setOnItemSelectedListener(this);
		writeIo_number_w = (Spinner) findViewById(R.id.writeIo_number_w);
		writeIo_number_w.setOnItemSelectedListener(this);
		writeIo_dir_num_w = (Spinner) findViewById(R.id.writeIo_dir_num_w);
		writeIo_dir_num_w.setOnItemSelectedListener(this);
		readIo_number_w = (Spinner) findViewById(R.id.readIo_number_w);
		readIo_number_w.setOnItemSelectedListener(this);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		
		int value = smdtManager.smdtGetGpioDirection(write_io);
		findViewById(R.id.writeIo_btn).setEnabled(value==1);
		setDirIo_hi_low.setEnabled(false);
		
		//////////////////////waibu banzi gpio
		int value_w = smdtManager.smdtGetGpioDirection(write_io_w);
		findViewById(R.id.writeIo_btn_w).setEnabled(value_w==1);
		setDirIo_hi_low_w.setEnabled(false);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.setDirIo_btn:// 设置GPIO口方向
			Log.d("xzj", "dir_set_io=" + dir_set_io + "   dir_set_impor="
					+ dir_set_impor + "   dir_set_value=" + dir_set_value);
			final int dirIoTemp = smdtManager.smdtSetGpioDirection(dir_set_io,
					dir_set_impor, dir_set_value);
			if (dirIoTemp == 0) {
				Toast.makeText(getApplicationContext(), "设置成功",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getApplicationContext(), "设置失败",
						Toast.LENGTH_LONG).show();
			}
			if(write_io==dir_set_io){
				findViewById(R.id.writeIo_btn).setEnabled(dir_set_impor==1);
			}
			break;

		case R.id.getDirIo_btn:// 获取GPIO口方向
			final int dirValue = smdtManager.smdtGetGpioDirection(dir_get_io);
			Log.d("xzj", "dirValue=" + dirValue);
			if (dirValue == 1) {
				getDirIo_txt.setText("IO为输出");
			} else {
				getDirIo_txt.setText("IO为输入");
				
			}
			break;

		case R.id.writeIo_btn:// 写GPIO口
			smdtManager.smdtSetExtrnalGpioValue(write_io,
					write_impor == 1 ? true : false);
			break;

		case R.id.readIo_btn:// 读GPIO口
			final int read_value = smdtManager
					.smdtReadExtrnalGpioValue(read_io);
			if (read_value == 1) {
				readIo_txt.setText("IO输入为高");
			} else {
				readIo_txt.setText("IO输入为低");
			}
			break;
		
/////////////////////////////////////////////////////////////////////////////////////////
	case R.id.setDirIo_btn_w:// 设置GPIO口方向
		Log.d("xzj", "dir_set_io_w=" + dir_set_io_w + "   dir_set_impor_w="
				+ dir_set_impor_w + "   dir_set_value_w=" + dir_set_value_w);
											//smdtSetXrm117xGpioDirection
		final int dirIoTemp_w = smdtManager.smdtSetXrm117xGpioDirection(dir_set_io_w,
				dir_set_impor_w, dir_set_value_w);
		if (dirIoTemp_w == 0) {
			Toast.makeText(getApplicationContext(), "外部设置成功",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), "外部设置失败",
					Toast.LENGTH_LONG).show();
		}
		if(write_io_w==dir_set_io_w){
			findViewById(R.id.writeIo_btn_w).setEnabled(dir_set_impor_w==1);
		}
		break;

	case R.id.getDirIo_btn_w:// 获取GPIO口方向
		final int dirValue_w = smdtManager.smdtGetXrm117xGpioDirection(dir_get_io_w);
		Log.d("xzj", "dirValue_w=" + dirValue_w+","+dir_get_io_w);
		if (dirValue_w == 1) {
			getDirIo_txt_w.setText("外部IO为输出");
		} else {
			getDirIo_txt_w.setText("外部IO为输入");
		}
		break;

	case R.id.writeIo_btn_w:// 写GPIO口
		smdtManager.smdtSetXrm117xGpioValue(write_io_w,
				write_impor_w);
		break;

	case R.id.readIo_btn_w:// 读GPIO口
		final int read_value_w = smdtManager
				.smdtGetXrm117xGpioValue(read_io_w);
		Log.d(TAG,"xzj====waibu read_value_w="+read_value_w);
		if (read_value_w == 1) {
			readIo_txt_w.setText("高电平");
		} else {
			readIo_txt_w.setText("低电平");
		}
		break;
	
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		switch (parent.getId()) {
		case R.id.setDirIo_number:// GPIO口
			dir_set_io = position + 1;
			break;
		case R.id.setDirIo_direction:// IO口输入或输出
			dir_set_impor = position;
			Log.d("aaa","xzj====setDirIo_hi_low,id="+id+",dir_set_impor="+dir_set_impor);
			//if(id==0){
				setDirIo_hi_low.setEnabled(id==1);
				//findViewById(R.id.writeIo_btn).setEnabled(id==1);
			//}
			break;
		case R.id.setDirIo_hi_low:// IO口高低值
			dir_set_value = position;
			break;
		case R.id.getDirIo_number:// 获取IO方向的IO值
			dir_get_io = position + 1;
			break;
		case R.id.writeIo_number:// 写GPIO状态口
			write_io = position + 1;
			Log.d("aaa","xzj====writeIo_number,id="+id+",write_io="+write_io);
			int value = smdtManager.smdtGetGpioDirection(write_io);
			findViewById(R.id.writeIo_btn).setEnabled(value==1);
			break;
		case R.id.writeIo_dir_num:// 写GPIO输入或输出
			write_impor = position;
			break;
		case R.id.readIo_number:// 读取GPIO IO口
			read_io = position + 1;
			break;
////////////////////////////////////////////////////////////////////////////////////
		case R.id.setDirIo_number_w:// GPIO口
			dir_set_io_w = position ;
			break;
		case R.id.setDirIo_direction_w:// IO口输入或输出
			dir_set_impor_w = position;
			setDirIo_hi_low_w.setEnabled(id==1);
			break;
		case R.id.setDirIo_hi_low_w:// IO口高低值
			dir_set_value_w = position;
			break;
		case R.id.getDirIo_number_w:// 获取IO方向的IO值
			dir_get_io_w = position ;
			break;
		case R.id.writeIo_number_w:// 写GPIO状态口
			write_io_w = position ;
			
			int value_w = smdtManager.smdtGetXrm117xGpioDirection(write_io_w);
			Log.d("aaa","xzj===write_io_w="+write_io_w+",value_w="+value_w);
			findViewById(R.id.writeIo_btn_w).setEnabled(value_w==1);
			break;
		case R.id.writeIo_dir_num_w:// 写GPIO输入或输出
			write_impor_w = position;
			break;
		case R.id.readIo_number_w:// 读取GPIO IO口
			read_io_w = position ;
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}
