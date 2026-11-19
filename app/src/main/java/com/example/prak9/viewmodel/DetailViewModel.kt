package com.example.prak9.viewmodel

class DetailViewModel(
    savedStateHandle: SavedStateHandle
    private val repositoriSiswa: RepositoriSiswa): ViewModel() {

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.itemIdArg])


}