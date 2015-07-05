package info.project.datapotal.viewpager;

import info.project.datapotal.viewpager.bookmark.FragmentBookmarkMain;
import info.project.datapotal.viewpager.bookmark.FragmentBookmarkOne;
import info.project.datapotal.viewpager.bookmark.FragmentBookmarkThree;
import info.project.datapotal.viewpager.bookmark.FragmentBookmarkTwo;
import info.project.datapotal.viewpager.product.FragmentCertification;
import info.project.datapotal.viewpager.product.FragmentMain;
import info.project.datapotal.viewpager.product.FragmentRecall;
import info.project.datapotal.viewpager.sign.FragmentSignOne;
import info.project.datapotal.viewpager.sign.FragmentSignThree;
import info.project.datapotal.viewpager.sign.FragmentSignTwo;
import info.project.datapotal.viewpager.sign.FragmentSignfive;
import info.project.datapotal.viewpager.sign.FragmentSignfour;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	int idxNum;

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	public TabsPagerAdapter(FragmentManager fm, int idxNum) {
		super(fm);
		this.idxNum = idxNum;
	}

	@Override
	public Fragment getItem(int index) {
		if (idxNum == 1) {
			switch (index) {
			case 0:
				// 메인 프래그먼트
				return new FragmentMain();

			case 1:
				// 인증제품 프래그먼트
				return new FragmentCertification();

			case 2:
				// 리콜제품 프래그먼트
				return new FragmentRecall();
			}
		} else if (idxNum == 2) {
			switch (index) {
			case 0:
				// 검정
				return new FragmentSignOne();

			case 1:
				// 초록
				return new FragmentSignTwo();

			case 2:
				// 빨강
				return new FragmentSignThree();

			case 3:
				// 노랑
				return new FragmentSignfour();

			case 4:
				// 파랑
				return new FragmentSignfive();
			}

		} else if(idxNum==3){
			switch(index){
			case 0:
				return new FragmentBookmarkMain();
			case 1:
				return new FragmentBookmarkOne();
				
			case 2:
				return new FragmentBookmarkTwo();
				
			case 3:
				return new FragmentBookmarkThree();
			}
		}
		return null;
	}

	@Override
	public int getCount() {
		int num = 0;
		// ViewPager 갯수 - tab갯수와 똑같이 맞춰준다.
		if (idxNum == 1) {
			num = 3;
		} else if (idxNum == 2) {
			num = 5;
		} else if(idxNum == 3){
			num=4;
		}
		return num;
	}

}
