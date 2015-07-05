package info.project.datapotal.viewpager.sign.holder;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
/* 뷰홀더 
 * 
 *  위젯들의 속성을 변경하기 위해 findViewById를 호출하는데 
 *  이것의 비용이 큰것을 줄이기 위해 사용합니다.
 *  참조 : http://theeye.pe.kr/archives/1253
 *  
 */
public class SignOneViewHolder 
{
	public ImageView icon;
	public TextView description;
	public TextView applyField;
	public TextView img;
	public TextView korName;
	public TextView engName;
	public ProgressBar progressBar;
}
