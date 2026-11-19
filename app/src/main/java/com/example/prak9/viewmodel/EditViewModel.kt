package com.example.prak9.viewmodel

import androidx.lifecycle.ViewModel

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa): ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiEditSiswa.itemIdArg])
        init {
            viewModelScope.launch {
                uiStateSiswa = repositoriSiswa.getSiswaStream(idSiswa)
                    .filterNotNull()
                    .first()
                    .toUiStateSiswa(true)
                }
            }

        fun updateUiState(siswa: DetailSiswa) {
            uiStateSiswa =
                UIStateSiswa(detailSiswa = siswa, isEntryValid = validasiInput(siswa))
        }

        private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa ): Boolean {
            return with(uiState) {
                nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
            }
        }

        suspend fun updateSiswa() {
            if (validasiInput(uiStateSiswa.detailSiswa)) {
                repositoriSiswa.updateSiswa(uiStateSiswa.detailSiswa.toSiswa())
            }
        }

}