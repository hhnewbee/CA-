package com.example.downloadui;

import java.util.ArrayList;
import java.util.List;

import com.example.downlaodui.infodata.Temporarydata;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author yangyu
 *	���������������������
 */
public class AdViewpager  implements OnClickListener,OnPageChangeListener {
	//����ViewPager����
	private ViewPager viewPager;
	
	//����ViewPager������
	private Myviewpageradapter vpAdapter;
	
	//����һ��ArrayList�����View
	private ArrayList<View> list;

	//����ͼƬ��Դ
    private List<ImageView> pics;
    
    //�ײ�С���ͼƬ
    private ImageView[] points;
    
    //��¼��ǰѡ��λ��
    private int currentIndex;
    private View view;
    private Context context;
    private boolean pd=false;
    private Handler mHandler = new Handler();
    
    public AdViewpager(Context context,View view,List<ImageView> image){
    	this.context=context;
    	this.view=view;
    	this.pics=image;
    	initView();
    	initData();	
    	mHandler.postDelayed(mImageTimerTask, 2000);
    }

	/**
	 * ��ʼ�����
	 */
	private void initView(){
		//ʵ����ArrayList����
		list = new ArrayList<View>();
		
		//ʵ����ViewPager
		viewPager = (ViewPager)view.findViewById(R.id.adviewpager);
		
		//ʵ����ViewPager������
		vpAdapter =new Myviewpageradapter(list);
	}
	
	/**
	 * ��ʼ������
	 */
	private void initData(){
		//����һ�����ֲ����ò���
		for(int i=0;i<pics.size();i++){
			list.add(pics.get(i));
		}
        //��������
        viewPager.setAdapter(vpAdapter);
        //���ü���
        viewPager.setOnPageChangeListener(this);
        
        //��ʼ���ײ�С��
        initPoint();
	}
	
	/**
	 * ��ʼ���ײ�С��
	 */
	private void initPoint(){
		LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.ll);       
		
        points = new ImageView[pics.size()];

        //ѭ��ȡ��С��ͼƬ
        for (int i = 0; i < pics.size(); i++) {
        	LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams
        			(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        	mParams.setMargins(10,5,10,0);
			ImageView iv = new ImageView(context);
			iv.setLayoutParams(mParams);
			iv.setImageResource(R.drawable.point);
        	points[i] =iv;
        	//��ӵ�һ��LinearLayout�����ÿһ����Ԫ��
        	linearLayout.addView(iv);
        	//Ĭ�϶���Ϊ��ɫ
        	points[i].setEnabled(true);
        	//��ÿ��С�����ü���
        	points[i].setOnClickListener(this);
        	//����λ��tag������ȡ���뵱ǰλ�ö�Ӧ
        	points[i].setTag(i);
        }
        
        //���õ���Ĭ�ϵ�λ��
        currentIndex = 0;
        //����Ϊ��ɫ����ѡ��״̬
        points[currentIndex].setEnabled(false);
	}
	
	/**
	 * ������״̬�ı�ʱ����
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}
	/**
	 * ����ǰҳ�汻����ʱ����
	 */

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}
	
	/**
	 * ���µ�ҳ�汻ѡ��ʱ����
	 */

	@Override
	public void onPageSelected(int position) {
		//���õײ�С��ѡ��״̬
        setCurDot(position);
	}

	/**
	 * ͨ������¼����л���ǰ��ҳ��
	 */
	@Override
	public void onClick(View v) {
		 int position = (Integer)v.getTag();
         setCurView(position);
         setCurDot(position);		
	}

	/**
	 * ���õ�ǰҳ���λ��
	 */
	private void setCurView(int position){
         if (position < 0 || position >= pics.size()) {
             return;
         }
         viewPager.setCurrentItem(position);
     }

     /**
     * ���õ�ǰ��С���λ��
     */
    private void setCurDot(int positon){

         if (positon < 0 || positon > pics.size() - 1 || currentIndex == positon) {
             return;
         }
         points[positon].setEnabled(false);
         points[currentIndex].setEnabled(true);

         currentIndex = positon;
     }
    private Runnable mImageTimerTask = new Runnable() {
		@Override
		public void run() {
			if (pics != null) {
				if(pd==false){
				viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
				if(viewPager.getCurrentItem()==pics.size()-1)
					pd=true;
				}
				else{
				viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
				if(viewPager.getCurrentItem()==0){
					pd=false;
					}
				}
				if(!(Temporarydata.stop)){  //if  isStop=true   //�����˳��� Ҫ�������ͣ���� ��Ȼ ���һֱ���� ��һֱ�ں�̨ѭ�� 
					mHandler.postDelayed(mImageTimerTask, 3000);
				}

			}
		}
	};

}
