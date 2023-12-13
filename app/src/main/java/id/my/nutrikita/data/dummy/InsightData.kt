package id.my.nutrikita.data.dummy

import id.my.nutrikita.data.remote.model.InsightsModel

object InsightData {
    val insights: List<InsightsModel> = listOf(
        InsightsModel(
            id = 1,
            title = "Rekomendasi Suplemen Selama Kehamilan",
            url = "https://ayosehat.kemkes.go.id/1000-hari-pertama-kehidupan/rekomendasi-suplemen-selama-kehamilan",
            image = "https://example.com/images/suplemen-kehamilan.jpg"
        ),
        InsightsModel(
            id = 2,
            title = "Gizi Seimbang Ibu Hamil",
            url = "https://yankes.kemkes.go.id/view_artikel/405/gizi-seimbang-ibu-hamil",
            image = "https://example.com/images/gizi-seimbang-ibu-hamil.jpg"
        ),
        InsightsModel(
            id = 3,
            title = "1000 Hari Pertama Kehidupan",
            url = "https://ayosehat.kemkes.go.id/1000-hari-pertama-kehidupan/home",
            image = "https://example.com/images/1000-hari-pertama.jpg"
        ),
        InsightsModel(
            id = 4,
            title = "Ragam Makanan untuk Ibu Hamil",
            url = "https://promkes.kemkes.go.id/ragam-makanan-yang-mengandung-nutrisi-penting-untuk-ibu-hamil-pk",
            image = "https://example.com/images/makanan-ibu-hamil.jpg"
        ),
        InsightsModel(
            id = 5,
            title = "Mengenal Apa Itu Stunting",
            url = "https://yankes.kemkes.go.id/view_artikel/1388/mengenal-apa-itu-stunting",
            image = "https://example.com/images/mengenal-stunting.jpg"
        )
    )
}