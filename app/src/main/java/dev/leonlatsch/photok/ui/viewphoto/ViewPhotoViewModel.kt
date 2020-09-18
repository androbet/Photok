/*
 *   Copyright 2020 Leon Latsch
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package dev.leonlatsch.photok.ui.viewphoto

import android.app.Application
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.drawable.toDrawable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.leonlatsch.photok.model.database.entity.Photo
import dev.leonlatsch.photok.model.repositories.PhotoRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class ViewPhotoViewModel @ViewModelInject constructor(
    private val app: Application,
    private val photoRepository: PhotoRepository
) : ViewModel() {

    var photoDrawable: MutableLiveData<BitmapDrawable> = MutableLiveData()
    var photo: MutableLiveData<Photo> = MutableLiveData()
    var photoSize = 0

    fun loadPhoto(id: Int) = viewModelScope.launch {
        photo.postValue(photoRepository.get(id))

        val photoBytes = photoRepository.readPhotoData(app, id)
        if (photoBytes == null) {
            // TODO: finish activity
            Timber.d("Error reading photo for id: $id")
            return@launch
        }

        photoSize = photoBytes.size
        photoDrawable.postValue(
            BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.size).toDrawable(app.resources)
        )
    }
}