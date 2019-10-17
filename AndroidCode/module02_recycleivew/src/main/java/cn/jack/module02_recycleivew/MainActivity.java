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
import cn.jack.module02_recycleivew.cardlist.OnSwipeListener;

/**
 * 尝试完成项目的一个需求,暂时先写的,先不建议学习,后期自己会记录recycleview由浅到深的学习笔记;
 *
 * 效果不理想，时间紧迫，暂时放下了
 */
public class MainActivity extends AppCompatActivity {

    private List<CardBean> list = new ArrayList<>();

    //被移除出去的数据
    private List<CardBean> mPreCardBean = new ArrayList<>();

    private MyAdapter mMyAdapter;


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

            }

            @Override
            public void onSwipedClear() {

                //是否选择循环加载
                Toast.makeText(MainActivity.this, "data clear", Toast.LENGTH_SHORT).show();
            }

        });


        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);

        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);

    }

    private void initData() {

        //第一次加载,默认加载10条数据
        list.add(new CardBean("凯婷",18+"","双秤座",R.drawable.img_avatar_01));
        list.add(new CardBean("凯婷",18+"","双秤座",R.drawable.img_avatar_02));
        list.add(new CardBean("凯婷",18+"","双秤座",R.drawable.img_avatar_03));
        list.add(new CardBean("凯婷",18+"","双秤座",R.drawable.img_avatar_04));
        list.add(new CardBean("凯婷",18+"","双秤座",R.drawable.img_avatar_05));
        list.add(new CardBean("凯婷",18+"","双秤座",R.drawable.img_avatar_06));
        list.add(new CardBean("凯婷",18+"","双秤座",R.drawable.img_avatar_07));

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