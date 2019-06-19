package br.com.goldencode.softsports;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MeusEventosActivity extends AppCompatActivity {

    Toolbar toolbar;
    Drawable drawable;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_eventos);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentEventosAtivos(), "Eventos ativos");
        adapter.AddFragment(new FragmentEventosCriados(), "Eventos criados");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawable = (Drawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back, getTheme());



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Meus eventos");
        getSupportActionBar().setHomeAsUpIndicator(drawable);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, TelaInicialActivity.class));
                finishAffinity();
                break;
            default:break;
        }
        return true;
    }

}
