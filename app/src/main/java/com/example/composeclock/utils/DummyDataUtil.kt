package com.example.composeclock.utils

import com.example.composeclock.data.model.User

object DummyDataUtil {
  private const val ALL = -1

  fun getDummyUsers(count: Int = ALL): List<User> {
    return when {
      count == ALL -> users
      count <= 0 -> emptyList()
      else -> users.take(count)
    }
  }

  // thanks to https://randomuser.me/photos
  private fun getRandomUserDp(): String {
    val randomGender = listOf("men", "women").random()
    val randomIndex = (0..80).random()
    return "https://randomuser.me/api/portraits/$randomGender/$randomIndex.jpg"
  }

  private val names = listOf(
    "Addie Richmond",
    "Aj Case",
    "1Al Stevenson",
    "Alice Strong",
    "&Angelia Gibson",
    "-Angelo Rosario",
    "0Ann Sanchez",
    "Ashley Mclean",
    "Aurora Schultz",
    "Beau Acosta",
    "Benton Blackwell",
    "Bernardo Williams",
    "Bertie Cannon",
    "Blaine Daniel",
    "Blake Macias",
    "Brad Bowers",
    "Bradford Goodman",
    "Buddy Acosta",
    "Carlos Fisher",
    "Carlos Ortiz",
    "Carly Rush",
    "Cathy Morrison",
    "Cedric Conner",
    "Connie Walls",
    "Conrad Strickland",
    "Cornelius Ali",
    "Craig Dixon",
    "Edmond Jacobs",
    "Edmund Stephens",
    "Eldon Monroe",
    "Elmer Diaz",
    "Elvin Mathis",
    "Erin Irwin",
    "Ernie Cannon",
    "Esmeralda Mason",
    "Esteban Garcia",
    "Fabian Neal",
    "Florencio Fox",
    "Franklyn Vazquez",
    "Fredric Good",
    "Fredrick Huerta",
    "Freeman Ortega",
    "Garfield Marsh",
    "Gary Howe",
    "Gertrude Nichols",
    "Gilda Richmond",
    "Guillermo Buck",
    "Herbert Choi",
    "Hilton Schaefer",
    "Hubert Jensen",
    "Hunter Green",
    "Jacklyn Hayden",
    "Jean Graham",
    "Jeanette Parks",
    "Jed Berg",
    "Jerold Oneal",
    "Jo Hester",
    "Joey Whitaker",
    "Josiah Barrera",
    "Katherine Chen",
    "Katherine Wilson",
    "kathryn Carpenter",
    "Kerry Wiley",
    "Korey Davila",
    "Latonya Parker",
    "Leland Werner",
    "Lena Meyers",
    "Lenore Conrad",
    "Letitia Webb",
    "Marian Mejia",
    "Marietta Dunlap",
    "Matthew Frederick",
    "mayra Holloway",
    "Mercedes Welch",
    "Mervin Carter",
    "Micah Ramsey",
    "Michel Shannon",
    "Mitchell Roth",
    "Monte Guerra",
    "Morgan Donovan",
    "Moses Eaton",
    "Noel Matthews",
    "Opal Rocha",
    "Paris Bright",
    "Patty Collier",
    "Petra Gallegos",
    "Petra Ibarra",
    "Quentin Bass",
    "Randall Santos",
    "Randi Green",
    "Rashad Reilly",
    "Ray Stephenson",
    "Renaldo Spencer",
    "Rhett Terry",
    "Rosalie Serrano",
    "Ross Villanueva",
    "Sandra Molina",
    "Saul Watkins",
    "Seymour Whitehead",
    "Shari Hernandez",
    "Shelby Horne",
    "Shirley Moses",
    "Simon Wade",
    "Sonny Hale",
    "Stacy Hardy",
    "Susanna Church",
    "Tamara Galvan",
    "Tammy Cochran",
    "Taylor Vance",
    "Titus Clay",
    "Vicente Warren",
    "Vilma Romero",
    "Violet Salinas",
    "Winifred David",
    "Yolanda Beasley",
    "Zane Frank"
  )

  private val users = names.shuffled()
    .mapIndexed { index, name ->
      User(
        index.toString(),
        name,
        getRandomUserDp()
      )
    }
}
