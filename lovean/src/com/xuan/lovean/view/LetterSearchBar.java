package com.xuan.lovean.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * ��ĸ����
 * 
 * @author xuan
 */
public class LetterSearchBar extends View {
	private final String[] strArray = new String[] { "A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z", "#" };

	private int width;// ������ĸ�Ŀ�
	private int height;// ������ĸ�ĸ�

	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);// ����

	private OnLetterChange onLetterChange;// ��ĸ�ı��¼�

	public LetterSearchBar(Context context) {
		super(context);
	}

	public LetterSearchBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LetterSearchBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	// ���ֵĴ�С�ı�ʱ���ͻ���ø÷���
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h / strArray.length;
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_MOVE) {
			int tempY = (int) ((event.getY() - 0) / height);// ������ĸ��λ��
			if (tempY < strArray.length && event.getY() > 0) {
				if (null != onLetterChange) {
					onLetterChange.letterChange(strArray[tempY]);
				}
				// �������ñ���2chatfrom_bg_new_fmessage_pressed.9.png
			}
		} else {
			// �������ñ���1
			// setBackgroundResource(0);
		}

		return true;// ��ֹ�¼�������������
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// ����Paint������
		paint.setFakeBoldText(true);
		paint.setColor(Color.GRAY);
		paint.setStyle(Style.FILL);
		paint.setTextSize(height * 0.75f);
		paint.setTextAlign(Paint.Align.CENTER);
		FontMetrics fm = paint.getFontMetrics();
		float x = width / 2;
		float y = height / 2 - (fm.ascent + fm.descent) / 2;

		for (int i = 0; i < strArray.length; i++) {
			canvas.drawText(strArray[i], x, i * height + y + 0, paint);
		}
	}

	/**
	 * ������ĸ�ı��¼�
	 * 
	 * @author xuan
	 * @version $Revision: 1.0 $, $Date: 2012-11-1 ����6:10:00 $
	 */
	public interface OnLetterChange {
		void letterChange(String letter);
	}

	public void setOnLetterChange(OnLetterChange onLetterChange) {
		this.onLetterChange = onLetterChange;
	}

}
