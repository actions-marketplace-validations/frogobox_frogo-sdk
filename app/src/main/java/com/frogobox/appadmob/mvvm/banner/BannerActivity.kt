package com.frogobox.appadmob.mvvm.banner

import com.frogobox.BaseActivity
import com.frogobox.databinding.ActivityBannerBinding

class BannerActivity : BaseActivity<ActivityBannerBinding>() {

    override fun setupViewBinding(): ActivityBannerBinding {
        return ActivityBannerBinding.inflate(layoutInflater)
    }

}