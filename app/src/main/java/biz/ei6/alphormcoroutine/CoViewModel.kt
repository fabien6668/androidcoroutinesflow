package biz.ei6.alphormcoroutine

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoViewModel : ViewModel() {

    var data = MutableLiveData<String>("")

    fun traitementLong() {
        viewModelScope.launch(Dispatchers.Default) {
            repeat(60) {
                delay(1000)
                Log.d("COR", "${Thread.currentThread().id}et clic !")
                withContext(Dispatchers.Main) {
                    data.value = "$it"
                }
            }


        }
    }

    fun traitementLongFlowAsLiveData(): LiveData<String> = liveData {

            repeat(60) {
             withContext(Dispatchers.Default) {
                 delay(1000)
                 Log.d("COR", "${Thread.currentThread().id}et clic !")
             }
                emit("$it")
            }

    }

    fun traitementLongFlow(): Flow<String> = flow {
        repeat(60) {
            delay(1000)
            Log.d("COR", "${Thread.currentThread().id}et clic !")
            emit("$it")
        }
    }.flowOn(Dispatchers.Default)
}
