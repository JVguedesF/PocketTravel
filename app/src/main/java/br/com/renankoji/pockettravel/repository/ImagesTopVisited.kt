package br.com.renankoji.pockettravel.repository

import br.com.renankoji.pockettravel.R

data class ImagesPlaces(
    val id: Int,
    val imageResId: Int
) {
    val location: Any
        get() {
            TODO()
        }
}

fun ImagesTopVisited(): List<ImagesPlaces> {
    return listOf(
        ImagesPlaces(id = 0, imageResId = R.drawable.paodeacucar),
        ImagesPlaces(id = 1, imageResId = R.drawable.elevadorlacerda),
        ImagesPlaces(id = 2, imageResId = R.drawable.cataratasdoiguacu),
        ImagesPlaces(id = 3, imageResId = R.drawable.rionegroeriosolimoes),
        ImagesPlaces(id=4, imageResId = R.drawable.museudoipiranga)
    )
}