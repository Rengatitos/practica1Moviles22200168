package com.practica1Moviles.Ticona.data.model

data class AutoModel(
    val marca: String,
    val modelo: String,
    val precio: Double,
    val imagenUrl: String
) {
    companion object {
        // Lista mock con al menos 5 autos deportivos (URLs públicas de imágenes)
        fun mockList(): List<AutoModel> = listOf(
            AutoModel(
                marca = "Ferrari",
                modelo = "488 Pista",
                precio = 330000.0,
                imagenUrl = "https://images.unsplash.com/photo-1542362567-b07e54358753?w=800&q=80&auto=format&fit=crop"
            ),
            AutoModel(
                marca = "Lamborghini",
                modelo = "Huracán EVO",
                precio = 261000.0,
                imagenUrl = "https://images.unsplash.com/photo-1511919884226-fd3cad34687c?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1170"
            ),
            AutoModel(
                marca = "Porsche",
                modelo = "911 GT3",
                precio = 187000.0,
                imagenUrl = "https://images.unsplash.com/photo-1503736334956-4c8f8e92946d?w=800&q=80&auto=format&fit=crop"
            ),
            AutoModel(
                marca = "McLaren",
                modelo = "720S",
                precio = 299000.0,
                imagenUrl = "https://images.unsplash.com/photo-1611740801331-d8b5d6962822?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1171"
            ),
            AutoModel(
                marca = "Aston Martin",
                modelo = "DB11",
                precio = 205000.0,
                imagenUrl = "https://images.unsplash.com/photo-1749746811536-2fd0f03a8095?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1170"
            )
        )
    }
}

