package com.example.model

object DataRepository {
    val userList = mutableListOf<User>()
    val sneakerList = mutableListOf<Sneacker>()


    init {
        userList.add(
            User(
                userId = 1,
                userName = "user",
                email = "u@mail.ru",
                password = "1234",
                favoriteSneakerIds = mutableListOf(),
                cartSneakerIds = mutableListOf(),
            )
        )
    }


    init {
        sneakerList.addAll(
            listOf(
                Sneacker(
                    id = 1,
                    name = "Розовые пионы",
                    description = "папм",
                    price = 6100.0,
                    imageUrl = "peonia",
                    category = "Монобукеты",
                    isPopular = true
                ),
                Sneacker(
                    id = 2,
                    name = "Воздушный зефир",
                    description = "пам",
                    price = 5000.0,
                    imageUrl = "comb_1",
                    category = "Сборные букеты",
                    isPopular = false
                ),
                Sneacker(
                    id = 3,
                    name = "Розы в коробке",
                    description = "пам",
                    price = 2999.0,
                    imageUrl = "box_rose",
                    category = "Букеты в коробке",
                    isPopular = true
                ),
                Sneacker(
                    id = 4,
                    name = "Кустовые розы розовые",
                    description = "zebra",
                    price =3390.0,
                    imageUrl = "mono_rose",
                    category = "Монобукеты",
                    isPopular = false
                ),
                Sneacker(
                    id = 5,
                    name = "Красные тюльпаны 25шт",
                    description = "monkey",
                    price = 4700.0,
                    imageUrl = "mono_toulpin_red",
                    category = "Монобукеты",
                    isPopular = true
                ),
                Sneacker(
                    id = 6,
                    name = "Розовые тюльпаны 25шт",
                    description = "pig",
                    price = 4600.0,
                    imageUrl = "mono_toulip_pink",
                    category = "Монобукеты",
                    isPopular = true
                ),
                Sneacker(
                    id = 7,
                    name = "Букет в коробке",
                    description = "пам",
                    price = 2500.0,
                    imageUrl = "box_1",
                    category = "Букеты в коробке",
                    isPopular = false
                ),
                Sneacker(
                    id = 8,
                    name = "Букет в коробке 2",
                    description = "пам",
                    price = 2999.0,
                    imageUrl = "box_3",
                    category = "Букеты в коробке",
                    isPopular = true
                ),
            )
        )
    }

    fun findUserByEmailAndPassword(email: String, password: String): User? {
        return userList.firstOrNull { it.email == email && it.password == password }
    }
}