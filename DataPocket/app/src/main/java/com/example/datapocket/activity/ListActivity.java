package com.example.datapocket.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datapocket.item.ListDataItem;
import com.example.datapocket.utility.Const;
import com.example.datapocket.utility.Key;
import com.example.datapocket.utility.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseBackgroundActivity {

    static final String TAG = "ListViewTest";

    private ListView mListView;
    private String mGid; // TODO getIntentで取得したGIDを保持する為の変数
    private DeletePagerAdapter deleteAdapter;

    static List<ListDataItem> dataList = new ArrayList<ListDataItem>();
    static ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

        getActionBar().setTitle("リスト一覧");
        // TODO getIntent処理
        Intent intent = getIntent();
        if(intent != null) {
            mGid = intent.getStringExtra(Key.GENRE_PRIMARY);
        }
        Log.v(TAG, mGid + "取得しました。");
        String[] key = mGid.split("_", -1);
        int primary = Integer.parseInt(key[1]);
        Log.v(TAG, primary + "です");

        findViews();

        // TODO ListデータをSQLiteから読み込む処理を記述する予定(↓それまでのサンプル)
        if (primary == 1) {
            dataList.add(new ListDataItem(1, "マグロ", "マグロとは"));
        }

        setAdapters();
        setBackground(R.drawable.background_pocket);
        // TODO:サンプル保存処理を記述
        // TODO:背景の設定処理
//        int listBackground = SQLiteから背景データを取得する処理
//        if(!listBackground==null) {
//        setBackground(listBackground);
//        }
        // TODO:タイトル、説明、画像セット処理
	}

    public static Intent createIntent(Context context, String primary) {
        return new Intent(context, ListActivity.class)
                .putExtra(Key.GENRE_PRIMARY, primary);
    }
    // TODO 詳細画面に送るデータ内容が決まり次第変更予定
    protected void findViews() {
        mListView = (ListView) findViewById(R.id.listView2);
    }

    protected void setAdapters() {
        adapter = new ListAdapter(this);
        mListView.setAdapter(adapter);
    }
	
	 /**
	  * ActionBarMenu
	  * #リスト追加ボタン
	  * #編集ボタン
	  */
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.list, menu);
		return true;
	 }
	 
	 /**
	  * ActionBarClickイベント
	  * #リスト追加ボタン
	  * #編集ボタン
	  */
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch(item.getItemId()) {
		  case R.id.list_add:	// リスト追加ボタン
		  	  intent.setClassName(getApplicationContext(), Const.ADD_LIST_ACTIVITY);
		      startActivityForResult(intent, Const.REQUEST_CODE);
		  	  return true;
		  case R.id.all_custom:	// 編集ボタン
			  intent.setClassName(getApplicationContext(), Const.CUSTOM_ALL_ACTIVITY);
			  startActivity(intent);
			  return true;
		  default:
			  return super.onOptionsItemSelected(item);
		}
		
	 }

    /**
     * AddListActivityがfinishにより破棄された際に呼ばれるメソッド。
     * #編集データの回収と保存
     * #編集内容を画面に表示
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String keyImage;

        switch (requestCode) {
            case Const.REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String keyTitle = bundle.getString(Key.LIST_TITLE);
                    String keyDescription = bundle.getString(Key.LIST_DESCRIPTION);
                    // TODO if文の条件を修正する
//        if(bundle.getParcelable(Key.GENRE_IMAGE) != null) {
//            keyImage = bundle.getString(Key.GENRE_IMAGE);
//            Toast.makeText(this, keyImage, Toast.LENGTH_SHORT).show();
//            Log.v(TAG, keyImage);
//        } else {
                    keyImage = "";
//        }
                    // TODO SQLite insert文&select文追加予定箇所
//                    MyDBHelper helper = new MyDBHelper(this);
//                    helper.insertGenre(keyTitle, keyDescription);
//                    dataList = helper.selectGenre();
                      dataList.add(new ListDataItem(1, keyTitle, keyDescription));
                    adapter.notifyDataSetChanged();
                }
                break;

            default:
                break;
        }
    }

    public class DeletePagerAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private static final int PAGE_NUM = 2;
        private ListDataItem item;
        private Context context;

        public DeletePagerAdapter(Context context, ListDataItem item) {
            super();
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.item = item;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout layout = null;

            if (position == 0) {
                // TODO #7 画像も扱えるようにする
                layout = (LinearLayout) inflater.inflate(R.layout.row_list, null);
                LinearLayout linearLayout = (LinearLayout)layout.findViewById(R.id.viewpager2);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, item.getTitle() + item.getMsg());
                        startActivity(DetailActivity.createIntent(getApplicationContext(), item.toString()));
                    }
                });
                TextView text1 = (TextView) layout.findViewById(R.id.listTitle);
                TextView text2 = (TextView) layout.findViewById(R.id.listMessage);
                String title = item.getTitle();
                String Message = item.getMsg();
                text1.setText(title);
                text2.setText(Message);
            } else {
                layout = (LinearLayout) inflater.inflate(R.layout.row_delete, null);
                Button deleteButton = (Button)layout.findViewById(R.id.deleteButton);
                deleteButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v){
                        Log.v(TAG, "デリート");
//                        MyDBHelper helper = new MyDBHelper(context);
//                        helper.deleteGenre(item.getPrimary());
//                        dataList = helper.selectGenre();
//                        adapter.notifyDataSetChanged();
                        Toast.makeText(context, "削除しました。", Toast.LENGTH_SHORT).show();

                    }

                });
            }
            container.addView(layout);

            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public int getCount() {
            return PAGE_NUM;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view.equals(obj);
        }
    }

    /**
     * ListAdapterClass
     * メソッド
     * #getCount()
     * #getItem(int position)
     * #getItemId(int position)
     * #getView(int position, View convertView, ViewGroup parent)
     */
    private class ListAdapter extends BaseAdapter {

        private LayoutInflater inflater = null;
        private static final float BUTTON_WIDTH_DP = 70f;
        private int margin;

        public ListAdapter(Context context) {
            super();
            inflater = LayoutInflater.from(context);

            //ページ2のRelativeLayoutの幅を計算してmarginへ格納する。
            float density = getApplicationContext().getResources().getDisplayMetrics().density;
            int buttonWidthPX = (int) (BUTTON_WIDTH_DP * density + 0.5f);

            WindowManager wm = (WindowManager) getApplicationContext().getSystemService(getApplicationContext().WINDOW_SERVICE);
            Display dp = wm.getDefaultDisplay();
            margin = dp.getWidth() - buttonWidthPX;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.row_viewpager_list, null);
            }

            ViewPager viewPager = (ViewPager) convertView.findViewById(R.id.viewpager);
            viewPager.setPageMargin(-margin);
            deleteAdapter = new DeletePagerAdapter(getApplicationContext(), (ListDataItem) getItem(position));
            viewPager.setAdapter(deleteAdapter);

            return convertView;

        }
    }
}
