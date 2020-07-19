package pnu.hakathon.anyone.model

import androidx.lifecycle.MutableLiveData

class HomeFragmentItem(
    var list1: MutableLiveData<List<HomeFragmentItemOne>> = MutableLiveData(),
    var list2: MutableLiveData<List<HomeFragmentItemTwo>> = MutableLiveData(),
    var list3: MutableLiveData<List<HomeFragmentItemThree>> = MutableLiveData()
)