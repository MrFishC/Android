package cn.jack.module02_recycleivew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.jack.module02_recycleivew.cardlist.CardBean;
import cn.jack.module02_recycleivew.cardlist.CardConfig;
import cn.jack.module02_recycleivew.cardlist.CardItemTouchHelperCallback;
import cn.jack.module02_recycleivew.cardlist.CardLayoutManager;
import cn.jack.module02_recycleivew.cardlist.OnLoadMoreListener;
import cn.jack.module02_recycleivew.cardlist.OnSwipeListener;

public class MainActivity extends AppCompatActivity {

    private List<CardBean> list = new ArrayList<>();

    //被移除出去的数据
    private List<CardBean> mPreCardBean = new ArrayList<>();

    private MyAdapter mMyAdapter;

    int count = 1;

    //标记,模拟分页加载使用
    private int mPageLoad = 0;

    //每一页加载出来的数据
    private int mPageNum = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initView() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mMyAdapter = new MyAdapter(list);

        recyclerView.setAdapter(mMyAdapter);
        CardItemTouchHelperCallback cardCallback = new CardItemTouchHelperCallback<CardBean>(mMyAdapter, list, new OnSwipeListener<CardBean>() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
                if (direction == CardConfig.SWIPING_UP) {
                    myHolder.dislikeImageView.setAlpha(Math.abs(ratio));
                } else if (direction == CardConfig.SWIPING_DOWN) {
                    myHolder.likeImageView.setAlpha(Math.abs(ratio));
                } else {
                    myHolder.dislikeImageView.setAlpha(0f);
                    myHolder.likeImageView.setAlpha(0f);
                }
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, CardBean cardBean, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
                myHolder.dislikeImageView.setAlpha(0f);
                myHolder.likeImageView.setAlpha(0f);

                updateItemData(cardBean,direction);
            }

            @Override
            public void onSwipedClear() {

                //是否选择循环加载
                Toast.makeText(MainActivity.this, "data clear", Toast.LENGTH_SHORT).show();

                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPageLoad = 0;
                        initData();
                        mMyAdapter.notifyDataSetChanged();
                    }
                },500);

            }

        });


        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper, new OnLoadMoreListener() {
            @Override
            public void loadMoreData() {
                System.out.println("count " + count++ );

                if(mPageLoad == 0){
                    //没有更多数据
                    Toast.makeText(MainActivity.this, "没有更多数据", Toast.LENGTH_SHORT).show();
                }else {

                    switch (mPageLoad){
                        case 1:
                            //加载第二页数据
                            for (int i = 0; i < mPageNum; i++) {
                                list.add(new CardBean("雅婷"+i,20+i+"","天蝎座",R.drawable.img_avatar_02));
                            }
                            mPageLoad++;
                            break;
                        //                        case 2:
                        //                            //加载第三页数据
                        //                            for (int i = 0; i < mPageNum; i++) {
                        //                                list.add(R.drawable.img_avatar_03);
                        //                            }
                        //                            mPageLoad++;
                        //                            break;
                        //                        case 3:
                        //                            //加载第四页数据
                        //                            for (int i = 0; i < mPageNum; i++) {
                        //                                list.add(R.drawable.img_avatar_04);
                        //                            }
                        //                            mPageLoad++;
                        //                            break;
                        //                        case 4:
                        //                            //加载第五页数据
                        //                            for (int i = 0; i < mPageNum; i++) {
                        //                                list.add(R.drawable.img_avatar_05);
                        //                            }
                        //                            mPageLoad++;
                        //                            break;
                        //                        case 5:
                        //                            //加载第六页数据
                        //                            for (int i = 0; i < mPageNum; i++) {
                        //                                list.add(R.drawable.img_avatar_06);
                        //                            }
                        //                            mPageLoad++;
                        //                            break;
                        //                        case 6:
                        case 2:
                            //加载第七页数据
                            for (int i = 0; i < mPageNum/2; i++) {
                                list.add(new CardBean("诗婷"+i,18+i+"","处女座",R.drawable.img_avatar_07));
                            }
                            mPageLoad++;
                            break;

                        default:
                            Toast.makeText(MainActivity.this, "没得了啊 " + mPageLoad, Toast.LENGTH_SHORT).show();
                            break;
                    }

                    Toast.makeText(MainActivity.this, "loadMoreData " + mPageLoad, Toast.LENGTH_SHORT).show();

                }
            }
        });

        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);

    }

    /**
     * 上下滑动更新列表数据
     * @param cardBean
     * @param direction
     */
    private void updateItemData(CardBean cardBean, int direction) {
        if(direction == ItemTouchHelper.UP){
            CardBean bean = list.get(0);
            mPreCardBean.add(bean);
            list.remove(bean);
            mMyAdapter.notifyDataSetChanged();
        }else {
            if(mPreCardBean.size() != 0){
                //更改逻辑
                CardBean cardB = mPreCardBean.get(mPreCardBean.size() - 1);
                list.add(0,cardB);
                mPreCardBean.remove(cardB);
                mMyAdapter.notifyDataSetChanged();
            }
        }

        //mPreCardBean

    }



    private void initData() {

        if (list.size() != 0) {
            list.clear();
        }

        if (mPreCardBean.size() != 0) {
            mPreCardBean.clear();
        }


        //第一次加载,默认加载10条数据
        for (int i = 0; i < mPageNum; i++) {
            list.add(new CardBean("凯婷"+i,16+i+"","双秤座",R.drawable.img_avatar_01));
        }

        //首页加载出来的数据小于pageNum的情况下,控制pageLoad标记
        if(list.size() >= mPageNum){
            mPageLoad++;
            System.out.println("mPageLoad " + mPageLoad);
        }

    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<CardBean> mCardBeans;

        public MyAdapter(List<CardBean> CardBeans) {
            mCardBeans = CardBeans;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            ImageView avatarImageView = ((MyViewHolder) holder).avatarImageView;

            final CardBean cardBean =  mCardBeans.get(position);
            avatarImageView.setImageResource(cardBean.getIcon());

            holder.name.setText(cardBean.getName());
            holder.age.setText(cardBean.getAge());
            holder.constellation.setText(cardBean.getConstellation());

            holder.constellation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this," " + cardBean.getName(),Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mCardBeans.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView avatarImageView;
            ImageView likeImageView;
            ImageView dislikeImageView;
            TextView constellation;
            TextView age;
            TextView name;

            MyViewHolder(View itemView) {
                super(itemView);
                avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
                likeImageView = (ImageView) itemView.findViewById(R.id.iv_like);
                dislikeImageView = (ImageView) itemView.findViewById(R.id.iv_dislike);
                constellation = (TextView) itemView.findViewById(R.id.tv_constellation);
                age = (TextView) itemView.findViewById(R.id.tv_age);
                name = (TextView) itemView.findViewById(R.id.tv_name);
            }

        }

    }

}