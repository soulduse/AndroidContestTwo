package info.project.datapotal.viewpager.product.holder;

import android.widget.ImageView;
import android.widget.TextView;
/* 뷰홀더 
 * 
 *  위젯들의 속성을 변경하기 위해 findViewById를 호출하는데 
 *  이것의 비용이 큰것을 줄이기 위해 사용합니다.
 *  참조 : http://theeye.pe.kr/archives/1253
 *  
 */
public class DangerViewHolder 
{
	public ImageView icon;
	public TextView model;
	public TextView name;
	public TextView company;
	public TextView content;
	public TextView safety_B;
	public TextView safety_U;
	public TextView date;
}
