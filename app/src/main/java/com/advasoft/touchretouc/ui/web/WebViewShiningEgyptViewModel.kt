package com.advasoft.touchretouc.ui.web

import androidx.lifecycle.ViewModel
import com.advasoft.touchretouc.data.repo.ShiningEgyptProtoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewShiningEgyptViewModel @Inject constructor(
    private val shiningEgyptProtoRepository: ShiningEgyptProtoRepository
) : ViewModel() {
    suspend fun updateUrl(newUrl: String) = shiningEgyptProtoRepository.updateUrl(newUrl)
}