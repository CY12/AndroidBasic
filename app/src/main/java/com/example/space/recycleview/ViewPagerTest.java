package com.example.space.recycleview;

public class ViewPagerTest {
    /**
     viewPager.setOffscreenPageLimit(int limit); 预加载个数


     public class SalaryActivity extends MainBaseActivity {

        private ActivitySalaryBinding mBinding;
        private String[] titles = new String[]{"工资","党费"};
        private List<MainBaseFragment> fragments = new ArrayList<>();
        private FragmentPagerAdapter pagerAdapter;
        private int[] pics = new int[]{R.mipmap.ic_salary,R.mipmap.ic_party};
        private int width;



        public static XIntent getIntent(Context context) {
            return new XIntent(context, SalaryActivity.class);
        }

        @Override
        public int getLayoutID() {
            return R.layout.activity_salary;
        }

        @Override
        public void getDataBinding(ViewDataBinding viewDataBinding) {
            mBinding = (ActivitySalaryBinding) viewDataBinding;
        }

        @Override
        public void initView(View view) {

        }

        @Override
        public void initData() {
            SalaryFragment salaryFragment = new SalaryFragment();
            PartyFeeFragment partyFeeFragment = new PartyFeeFragment();
            fragments.add(salaryFragment);
            fragments.add(partyFeeFragment);
            for(int i=0;i<titles.length;i++){
                mBinding.tabLayout.addTab(mBinding.tabLayout.newTab());
            }
            mBinding.tabLayout.setupWithViewPager(mBinding.viewpager,false);
            pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public int getCount() {
                    return fragments.size();
                }

                @Override
                public Fragment getItem(int i) {
                    return fragments.get(i);
                }
            };

            mBinding.viewpager.setAdapter(pagerAdapter);
            setCustomIcon();

            mBinding.ivBack.setOnClickListener(v -> finish());
            mBinding.ivQuestion.setOnClickListener(v -> {
                TipDialog tipDialog = new TipDialog(SalaryActivity.this);
                tipDialog.show();

            });

        }

        @Override
        public Presenter bindPresenter() {
            return null;
        }

        @Override
        public void attachView() {

        }


        private void setCustomIcon() {



            for(int i=0;i<titles.length;i++){
//            app:tabPaddingStart="-1dp"
//            app:paddingEnd="-1dp"
//            app:tabInlineLabel="true"
//            mBinding.tabLayout.getTabAt(i).setIcon(pics[i]).setText(titles[i]);
                mBinding.tabLayout.getTabAt(i).setCustomView(makeTabView(i));
            }
        }


        private View makeTabView(int position){
            View tabView = LayoutInflater.from(this).inflate(R.layout.customview_tab,null);

            TextView textView = tabView.findViewById(R.id.textview);
            ImageView imageView = tabView.findViewById(R.id.imageview);
            textView.setText(titles[position]);
            imageView.setImageResource(pics[position]);
            return tabView;
        }


        @Override
        public void bindComponent(AppComponent appComponent) {

        }
    }
     */
}
