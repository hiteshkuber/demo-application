package com.example.demure_demo_app.data.profile_data

class GeneralInformation {
    var date_of_birth: String? = null
    var first_name: String? = null
}

class Invites {
    var profiles: ArrayList<Profile>? = null
}

class Likes {
    var profiles: ArrayList<Profile>? = null
    var can_see_profile = false
}

class Photo {
    var photo: String? = null
    var selected = false
}

class Profile {
    var general_information: GeneralInformation? = null
    var photos: ArrayList<Photo>? = null
    var first_name: String? = null
    var avatar: String? = null
}

class Feed {
    var invites: Invites? = null
    var likes: Likes? = null
}
