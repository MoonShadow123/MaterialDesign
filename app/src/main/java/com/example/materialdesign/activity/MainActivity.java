package com.example.materialdesign.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.materialdesign.bean.Fruit;
import com.example.materialdesign.adapter.FruitAdapter;
import com.example.materialdesign.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The type Main activity.
 *
 * @author PengLiang
 * Time: 2019/8/27
 */
public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private List<Fruit> fruitList;
    private FruitAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        setViews();
        setListeners();
    }


    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefresh = findViewById(R.id.swipe_refresh);
        adapter = new FruitAdapter(fruitList);
        fruitList = new ArrayList<>();
    }

    private void initData() {
        initFruits();
    }

    private void setViews() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.title_head);
        }
        navView.setCheckedItem(R.id.nav_camera);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
    }


    private void setListeners() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                Toast.makeText(MainActivity.this, "此功能暂未开放", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "数据删除", Snackbar.LENGTH_SHORT)
                        .setAction("撤消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "数据恢复", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });

        adapter.onClickListener(new FruitAdapter.onItemClick() {
            @Override
            public void onClick(View view, int position, String name, int id) {
                Intent intent = new Intent(MainActivity.this, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, name);
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, id);
                startActivity(intent);
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(Fruit.fruits.length);
            fruitList.add(Fruit.fruits[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                adapter.addData(0);
                recyclerView.scrollToPosition(0);
                break;
            case R.id.delete:
                adapter.removeData(0);
                recyclerView.scrollToPosition(0);
                break;
            case R.id.setting:
                Toast.makeText(this, "你点击了设置", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
