package com.ygolubev.pexels.ui

import com.ygolubev.pexels.ui.model.AppViewModel
import com.ygolubev.pexels.ui.model.CuratedPhotosViewModel
import com.ygolubev.pexels.ui.model.PhotoDetailsViewModel
import com.ygolubev.pexels.ui.model.PhotoToCuratedPhotoUiModelMapper
import com.ygolubev.pexels.ui.model.PhotoToCuratedPhotoUiModelMapperImpl
import com.ygolubev.pexels.ui.navigation.Navigator
import com.ygolubev.pexels.ui.navigation.NavigatorImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val uiModule = module {
    singleOf(::NavigatorImpl) {
        bind<Navigator>()
    }

    viewModelOf(::AppViewModel)

    singleOf(::PhotoToCuratedPhotoUiModelMapperImpl) {
        bind<PhotoToCuratedPhotoUiModelMapper>()
    }
    viewModelOf(::CuratedPhotosViewModel)

    viewModelOf(::PhotoDetailsViewModel)
}
