package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.datapocket.item.GenreDataItem;
import com.example.datapocket.utility.Const;
import com.example.datapocket.utility.Key;
import com.example.datapocket.utility.MyDBHelper;

/**
 * Created by masakisakamoto on 2015/04/05.
 * GenreTopActivityClass
 * ジャンル画面設定用クラス
 *
 * メソッド
 * #onCreate(Bundle savedInstanceState)
 * #splashAnimation()   スプラッシュアニメーションメソッド
 * #onAnimationStart(Animation animation)
 * #onAnimationEnd(Animation animation)
 * #onAnimationRepeat(Animation animation)
 * #onCreateOptionsMenu(Menu menu)  ActionBarMenu追加メソッド
 * #onOptionsItemSelected(MenuItem item)    ActionBarMenuClickイベント
 * #onActivityResult(int requestCode, int resultCode, Intent data)
 * #setAdapters()
 * #findViews()
 */
public class GenreTopActivity extends BaseBackgroundActivity implements Animation.AnimationListener {

    static final String TAG = "GenreTopActivity";

    private ListView mListView;
    private ViewPager mViewPager;
    private DeletePagerAdapter deleteAdapter;

    static List<GenreDataItem> dataList = new ArrayList<GenreDataItem>();
    static GenreAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_top);
        // TODO テスト用終わったら消す
//        dataList.add(new GenreDataItem(1, "坂本", "テスト中です。"));
        Log.v(TAG, dataList.toString());
        splashAnimation();
        findViews();

        MyDBHelper helper = new MyDBHelper(this);
        helper.isStartFirst();
        dataList = helper.selectGenre();
        Log.v(TAG, dataList.toString());
        setAdapters();
        /*** ここからテスト用のデータ　本番では消す ***/
        setBackground(R.drawable.background_pocket);
        /***************** ここまで ***************/

        // TODO #5 SQLiteから背景データを読み取り描画する。データがなければデフォルトを設定する
//        int genreBackground = SQLiteから背景データを取得する処理
//        if(!genreBackground==null) {
//        setBackground(genreBackground);
//        }
    }

    /**
     * splashAnimation
     * スプラッシュのアニメーション描画処理
     */
    private void splashAnimation() {
        AlphaAnimation alphaanime = new AlphaAnimation(1, 0);
        alphaanime.setStartOffset(2000);
        alphaanime.setDuration(1000);
        alphaanime.setFillAfter(true);
        alphaanime.setAnimationListener(this);
        LinearLayout linear = (LinearLayout) findViewById(R.id.splash);
        linear.startAnimation(alphaanime);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    /**
     * ActionBarMenu
     * #ジャンル追加ボタン
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    /**
     * ActionBarClickイベント
     * #ジャンル追加ボタン
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.genre_add:
                Intent intent = new Intent();
                intent.setClassName(getApplicationContext(), Const.ADD_GENRE_ACTIVITY);
                startActivityForResult(intent, Const.REQUEST_CODE);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * AddGenreActivityがfinishにより破棄された際に呼ばれるメソッド。
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
                    String keyTitle = bundle.getString(Key.GENRE_TITLE);
                    String keyDescription = bundle.getString(Key.GENRE_DESCRIPTION);
                    // TODO if文の条件を修正する
//        if(bundle.getParcelable(Key.GENRE_IMAGE) != null) {
//            keyImage = bundle.getString(Key.GENRE_IMAGE);
//            Toast.makeText(this, keyImage, Toast.LENGTH_SHORT).show();
//            Log.v(TAG, keyImage);
//        } else {
                    keyImage = "";
//        }
                    MyDBHelper helper = new MyDBHelper(this);
                    helper.insertGenre(keyTitle, keyDescription);
                    dataList = helper.selectGenre();
                    adapter.notifyDataSetChanged();
                }
                break;

            default:
                break;
        }
    }

    protected void setAdapters() {
        adapter = new GenreAdapter(this);
//        adapter = new GenreAdapter(this, R.layout.row, dataList);
        mListView.setAdapter(adapter);
    }

    protected void findViews() {
        mListView = (ListView) findViewById(R.id.listView1);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ListView listView = (ListView) parent;
//                GenreDataItem genreDataItem = (GenreDataItem) listView.getItemAtPosition(position);
//                String primary = genreDataItem.toString();
//                Log.v(TAG, genreDataItem.toString());
//                startActivity(ListActivity.createIntent(getApplicationContext(), primary));
//            }


//        });
    }

    public class DeletePagerAdapter extends PagerAdapter {

        private LayoutInflater inflater;
        private static final int PAGE_NUM = 2;
        private GenreDataItem item;

        public DeletePagerAdapter(Context context, GenreDataItem item) {
            super();
            inflater = LayoutInflater.from(context);
            this.item = item;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout layout = null;

            if (position == 0) {
                // TODO #7 画像も扱えるようにする
                layout = (LinearLayout) inflater.inflate(R.layout.row_genre, null);
                LinearLayout linearLayout = (LinearLayout)layout.findViewById(R.id.viewpager1);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v(TAG, "viewPagerクリック");
                        Log.v(TAG, item.getTitle() + item.getMsg());
                        startActivity(ListActivity.createIntent(getApplicationContext(), item.toString()));
                    }
                });
                TextView text1 = (TextView) layout.findViewById(R.id.genreTitle);
                TextView text2 = (TextView) layout.findViewById(R.id.genreMessage);
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

    private class GenreAdapter extends BaseAdapter {

        private LayoutInflater inflater = null;
        private static final float BUTTON_WIDTH_DP = 70f;
        private int margin;

        public GenreAdapter(Context context) {
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
                convertView = inflater.inflate(R.layout.row, null);
            }

            ViewPager viewPager = (ViewPager) convertView.findViewById(R.id.viewpager);
            viewPager.setPageMargin(-margin);
            deleteAdapter = new DeletePagerAdapter(getApplicationContext(), (GenreDataItem) getItem(position));
            viewPager.setAdapter(deleteAdapter);

            return convertView;

        }
    }
}

// TODO #8 GenreTopのレイアウトを修正する。margin,paddingなど
