package com.example.downloadui.itemview;

import com.example.downloadui.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Classifyitemonclicklistener  implements OnClickListener{
	private  TextView text;
	private  LinearLayout classifyView;
	private  ScrollView classifyitemscrollview;
	public Classifyitemonclicklistener(TextView text,LinearLayout classifyView,ScrollView classifyitemscrollview){
		this.text=text;
		this.classifyView=classifyView;
		this.classifyitemscrollview=classifyitemscrollview;
	}
				@Override
				public void onClick(View v) {
//					// TODO Auto-generated method stub
					switch(v.getId()){
					case R.id.jixieproduct1:
						text.setText("��Э��Ʒ");
						setView("jixiechupin");	
						break;
						
					case R.id.jixieproduct2:
						text.setText("����ͨѶ");
						setView("liaotiantongxun");						
						break;
					case R.id.jixieproduct3:
						text.setText("Ӱ������");
						setView("yingyinbofang");						
						break;
					case R.id.jixieproduct4:
						text.setText("ͼ��ͼ��");
						setView("tuxingtuxiang");						
						break;
					case R.id.jixieproduct5:
						text.setText("��Ϸ����");
						setView("youxiyule");
						break;
						
					case R.id.jixieproduct6:
						text.setText("��̿���");
						setView("bianchengkaifa");						
						break;
					case R.id.jixieproduct0:
						text.setText("�������");
						setView("wangluoruanjian");						
						break;
					case R.id.jixieproduct8:
						text.setText("ʵ�����");
						setView("shiyongruanjian");
						break;
						
					case R.id.jixieproduct9:
						text.setText("����ϵͳ");
						setView("caozuoxitong");
						break;
		}
	}
				public void setView(String type){
					
					MarginLayoutParams params=(MarginLayoutParams)classifyView.getLayoutParams();
					params.width=0;
					classifyView.requestLayout();
					Classifylistener.listener(type,0);
					classifyitemscrollview.setOnTouchListener(new ClassifyrefreshOnclicklistener(type));
				}
}
