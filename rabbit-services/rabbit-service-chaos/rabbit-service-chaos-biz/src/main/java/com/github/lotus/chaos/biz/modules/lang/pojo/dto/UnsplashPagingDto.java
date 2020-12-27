package com.github.lotus.chaos.biz.modules.lang.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@NoArgsConstructor
@Data
public class UnsplashPagingDto {

    /**
     * id : GuCqQn8HQSI
     * created_at : 2020-10-03T16:51:13-04:00
     * updated_at : 2020-12-26T16:14:59-05:00
     * promoted_at : null
     * width : 6000
     * height : 4000
     * color : #a6c0d9
     * blur_hash : LiJH]vb{bwIpPXoMofn~o#MxRPs,
     * description : null
     * alt_description : null
     * urls : {"raw":"https://images.unsplash.com/photo-1601758260892-a62c486ace97?ixid=MXwxOTM4MzB8MXwxfGFsbHwxfHx8fHx8Mnw&ixlib=rb-1.2.1","full":"https://images.unsplash.com/photo-1601758260892-a62c486ace97?crop=entropy&cs=srgb&fm=jpg&ixid=MXwxOTM4MzB8MXwxfGFsbHwxfHx8fHx8Mnw&ixlib=rb-1.2.1&q=85","regular":"https://images.unsplash.com/photo-1601758260892-a62c486ace97?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxOTM4MzB8MXwxfGFsbHwxfHx8fHx8Mnw&ixlib=rb-1.2.1&q=80&w=1080","small":"https://images.unsplash.com/photo-1601758260892-a62c486ace97?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxOTM4MzB8MXwxfGFsbHwxfHx8fHx8Mnw&ixlib=rb-1.2.1&q=80&w=400","thumb":"https://images.unsplash.com/photo-1601758260892-a62c486ace97?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxOTM4MzB8MXwxfGFsbHwxfHx8fHx8Mnw&ixlib=rb-1.2.1&q=80&w=200"}
     * links : {"self":"https://api.unsplash.com/photos/GuCqQn8HQSI","html":"https://unsplash.com/photos/GuCqQn8HQSI","download":"https://unsplash.com/photos/GuCqQn8HQSI/download","download_location":"https://api.unsplash.com/photos/GuCqQn8HQSI/download"}
     * categories : []
     * likes : 83
     * liked_by_user : false
     * current_user_collections : []
     * sponsorship : {"impression_urls":["https://secure.insightexpressai.com/adServer/adServerESI.aspx?script=false&bannerID=7686996&rnd=[timestamp]&gdpr=&gdpr_consent=&redir=https://secure.insightexpressai.com/adserver/1pixel.gif"],"tagline":"Pets Bring Us Together","tagline_url":"https://www.chewy.com/?utm_source=unsplash&utm_medium=brand&utm_term=chewy-47&utm_content=GuCqQn8HQSI","sponsor":{"id":"21uOSEd-cSI","updated_at":"2020-12-27T00:03:58-05:00","username":"chewy","name":"Chewy","first_name":"Chewy","last_name":null,"twitter_username":"chewy","portfolio_url":"https://www.chewy.com/","bio":"There are endless ways #PetsBringUsTogether. We\u2019re just here to help.","location":null,"links":{"self":"https://api.unsplash.com/users/chewy","html":"https://unsplash.com/@chewy","photos":"https://api.unsplash.com/users/chewy/photos","likes":"https://api.unsplash.com/users/chewy/likes","portfolio":"https://api.unsplash.com/users/chewy/portfolio","following":"https://api.unsplash.com/users/chewy/following","followers":"https://api.unsplash.com/users/chewy/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"},"instagram_username":"chewy","total_collections":0,"total_likes":0,"total_photos":50,"accepted_tos":true}}
     * user : {"id":"21uOSEd-cSI","updated_at":"2020-12-27T00:03:58-05:00","username":"chewy","name":"Chewy","first_name":"Chewy","last_name":null,"twitter_username":"chewy","portfolio_url":"https://www.chewy.com/","bio":"There are endless ways #PetsBringUsTogether. We\u2019re just here to help.","location":null,"links":{"self":"https://api.unsplash.com/users/chewy","html":"https://unsplash.com/@chewy","photos":"https://api.unsplash.com/users/chewy/photos","likes":"https://api.unsplash.com/users/chewy/likes","portfolio":"https://api.unsplash.com/users/chewy/portfolio","following":"https://api.unsplash.com/users/chewy/following","followers":"https://api.unsplash.com/users/chewy/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"},"instagram_username":"chewy","total_collections":0,"total_likes":0,"total_photos":50,"accepted_tos":true}
     */
    @JsonProperty("id")
    private String id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("promoted_at")
    private String promotedAt;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("color")
    private String color;
    @JsonProperty("blur_hash")
    private String blurHash;
    @JsonProperty("description")
    private String description;
    @JsonProperty("alt_description")
    private String altDescription;
    @JsonProperty("urls")
    private UrlsDTO urls;
    @JsonProperty("links")
    private LinksDTO links;
    @JsonProperty("likes")
    private Integer likes;
    @JsonProperty("liked_by_user")
    private Boolean likedByUser;
    @JsonProperty("sponsorship")
    private SponsorshipDTO sponsorship;
    @JsonProperty("user")
    private UserDTO user;
    @JsonProperty("categories")
    private List<?> categories;
    @JsonProperty("current_user_collections")
    private List<?> currentUserCollections;

    @NoArgsConstructor
    @Data
    public static class UrlsDTO {
        /**
         * raw : https://images.unsplash.com/photo-1601758260892-a62c486ace97?ixid=MXwxOTM4MzB8MXwxfGFsbHwxfHx8fHx8Mnw&ixlib=rb-1.2.1
         * full : https://images.unsplash.com/photo-1601758260892-a62c486ace97?crop=entropy&cs=srgb&fm=jpg&ixid=MXwxOTM4MzB8MXwxfGFsbHwxfHx8fHx8Mnw&ixlib=rb-1.2.1&q=85
         * regular : https://images.unsplash.com/photo-1601758260892-a62c486ace97?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxOTM4MzB8MXwxfGFsbHwxfHx8fHx8Mnw&ixlib=rb-1.2.1&q=80&w=1080
         * small : https://images.unsplash.com/photo-1601758260892-a62c486ace97?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxOTM4MzB8MXwxfGFsbHwxfHx8fHx8Mnw&ixlib=rb-1.2.1&q=80&w=400
         * thumb : https://images.unsplash.com/photo-1601758260892-a62c486ace97?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MXwxOTM4MzB8MXwxfGFsbHwxfHx8fHx8Mnw&ixlib=rb-1.2.1&q=80&w=200
         */

        @JsonProperty("raw")
        private String raw;
        @JsonProperty("full")
        private String full;
        @JsonProperty("regular")
        private String regular;
        @JsonProperty("small")
        private String small;
        @JsonProperty("thumb")
        private String thumb;
    }

    @NoArgsConstructor
    @Data
    public static class LinksDTO {
        /**
         * self : https://api.unsplash.com/photos/GuCqQn8HQSI
         * html : https://unsplash.com/photos/GuCqQn8HQSI
         * download : https://unsplash.com/photos/GuCqQn8HQSI/download
         * download_location : https://api.unsplash.com/photos/GuCqQn8HQSI/download
         */

        @JsonProperty("self")
        private String self;
        @JsonProperty("html")
        private String html;
        @JsonProperty("download")
        private String download;
        @JsonProperty("download_location")
        private String downloadLocation;
    }

    @NoArgsConstructor
    @Data
    public static class SponsorshipDTO {
        /**
         * impression_urls : ["https://secure.insightexpressai.com/adServer/adServerESI.aspx?script=false&bannerID=7686996&rnd=[timestamp]&gdpr=&gdpr_consent=&redir=https://secure.insightexpressai.com/adserver/1pixel.gif"]
         * tagline : Pets Bring Us Together
         * tagline_url : https://www.chewy.com/?utm_source=unsplash&utm_medium=brand&utm_term=chewy-47&utm_content=GuCqQn8HQSI
         * sponsor : {"id":"21uOSEd-cSI","updated_at":"2020-12-27T00:03:58-05:00","username":"chewy","name":"Chewy","first_name":"Chewy","last_name":null,"twitter_username":"chewy","portfolio_url":"https://www.chewy.com/","bio":"There are endless ways #PetsBringUsTogether. We\u2019re just here to help.","location":null,"links":{"self":"https://api.unsplash.com/users/chewy","html":"https://unsplash.com/@chewy","photos":"https://api.unsplash.com/users/chewy/photos","likes":"https://api.unsplash.com/users/chewy/likes","portfolio":"https://api.unsplash.com/users/chewy/portfolio","following":"https://api.unsplash.com/users/chewy/following","followers":"https://api.unsplash.com/users/chewy/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"},"instagram_username":"chewy","total_collections":0,"total_likes":0,"total_photos":50,"accepted_tos":true}
         */

        @JsonProperty("tagline")
        private String tagline;
        @JsonProperty("tagline_url")
        private String taglineUrl;
        @JsonProperty("sponsor")
        private SponsorDTO sponsor;
        @JsonProperty("impression_urls")
        private List<String> impressionUrls;

        @NoArgsConstructor
        @Data
        public static class SponsorDTO {
            /**
             * id : 21uOSEd-cSI
             * updated_at : 2020-12-27T00:03:58-05:00
             * username : chewy
             * name : Chewy
             * first_name : Chewy
             * last_name : null
             * twitter_username : chewy
             * portfolio_url : https://www.chewy.com/
             * bio : There are endless ways #PetsBringUsTogether. We’re just here to help.
             * location : null
             * links : {"self":"https://api.unsplash.com/users/chewy","html":"https://unsplash.com/@chewy","photos":"https://api.unsplash.com/users/chewy/photos","likes":"https://api.unsplash.com/users/chewy/likes","portfolio":"https://api.unsplash.com/users/chewy/portfolio","following":"https://api.unsplash.com/users/chewy/following","followers":"https://api.unsplash.com/users/chewy/followers"}
             * profile_image : {"small":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"}
             * instagram_username : chewy
             * total_collections : 0
             * total_likes : 0
             * total_photos : 50
             * accepted_tos : true
             */

            @JsonProperty("id")
            private String id;
            @JsonProperty("updated_at")
            private String updatedAt;
            @JsonProperty("username")
            private String username;
            @JsonProperty("name")
            private String name;
            @JsonProperty("first_name")
            private String firstName;
            @JsonProperty("last_name")
            private Object lastName;
            @JsonProperty("twitter_username")
            private String twitterUsername;
            @JsonProperty("portfolio_url")
            private String portfolioUrl;
            @JsonProperty("bio")
            private String bio;
            @JsonProperty("location")
            private Object location;
            @JsonProperty("links")
            private LinksDTO links;
            @JsonProperty("profile_image")
            private ProfileImageDTO profileImage;
            @JsonProperty("instagram_username")
            private String instagramUsername;
            @JsonProperty("total_collections")
            private Integer totalCollections;
            @JsonProperty("total_likes")
            private Integer totalLikes;
            @JsonProperty("total_photos")
            private Integer totalPhotos;
            @JsonProperty("accepted_tos")
            private Boolean acceptedTos;

            @NoArgsConstructor
            @Data
            public static class LinksDTO {
                /**
                 * self : https://api.unsplash.com/users/chewy
                 * html : https://unsplash.com/@chewy
                 * photos : https://api.unsplash.com/users/chewy/photos
                 * likes : https://api.unsplash.com/users/chewy/likes
                 * portfolio : https://api.unsplash.com/users/chewy/portfolio
                 * following : https://api.unsplash.com/users/chewy/following
                 * followers : https://api.unsplash.com/users/chewy/followers
                 */

                @JsonProperty("self")
                private String self;
                @JsonProperty("html")
                private String html;
                @JsonProperty("photos")
                private String photos;
                @JsonProperty("likes")
                private String likes;
                @JsonProperty("portfolio")
                private String portfolio;
                @JsonProperty("following")
                private String following;
                @JsonProperty("followers")
                private String followers;
            }

            @NoArgsConstructor
            @Data
            public static class ProfileImageDTO {
                /**
                 * small : https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32
                 * medium : https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64
                 * large : https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128
                 */

                @JsonProperty("small")
                private String small;
                @JsonProperty("medium")
                private String medium;
                @JsonProperty("large")
                private String large;
            }
        }
    }

    @NoArgsConstructor
    @Data
    public static class UserDTO {
        /**
         * id : 21uOSEd-cSI
         * updated_at : 2020-12-27T00:03:58-05:00
         * username : chewy
         * name : Chewy
         * first_name : Chewy
         * last_name : null
         * twitter_username : chewy
         * portfolio_url : https://www.chewy.com/
         * bio : There are endless ways #PetsBringUsTogether. We’re just here to help.
         * location : null
         * links : {"self":"https://api.unsplash.com/users/chewy","html":"https://unsplash.com/@chewy","photos":"https://api.unsplash.com/users/chewy/photos","likes":"https://api.unsplash.com/users/chewy/likes","portfolio":"https://api.unsplash.com/users/chewy/portfolio","following":"https://api.unsplash.com/users/chewy/following","followers":"https://api.unsplash.com/users/chewy/followers"}
         * profile_image : {"small":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32","medium":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64","large":"https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128"}
         * instagram_username : chewy
         * total_collections : 0
         * total_likes : 0
         * total_photos : 50
         * accepted_tos : true
         */

        @JsonProperty("id")
        private String id;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("username")
        private String username;
        @JsonProperty("name")
        private String name;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private Object lastName;
        @JsonProperty("twitter_username")
        private String twitterUsername;
        @JsonProperty("portfolio_url")
        private String portfolioUrl;
        @JsonProperty("bio")
        private String bio;
        @JsonProperty("location")
        private Object location;
        @JsonProperty("links")
        private LinksDTO links;
        @JsonProperty("profile_image")
        private ProfileImageDTO profileImage;
        @JsonProperty("instagram_username")
        private String instagramUsername;
        @JsonProperty("total_collections")
        private Integer totalCollections;
        @JsonProperty("total_likes")
        private Integer totalLikes;
        @JsonProperty("total_photos")
        private Integer totalPhotos;
        @JsonProperty("accepted_tos")
        private Boolean acceptedTos;

        @NoArgsConstructor
        @Data
        public static class LinksDTO {
            /**
             * self : https://api.unsplash.com/users/chewy
             * html : https://unsplash.com/@chewy
             * photos : https://api.unsplash.com/users/chewy/photos
             * likes : https://api.unsplash.com/users/chewy/likes
             * portfolio : https://api.unsplash.com/users/chewy/portfolio
             * following : https://api.unsplash.com/users/chewy/following
             * followers : https://api.unsplash.com/users/chewy/followers
             */

            @JsonProperty("self")
            private String self;
            @JsonProperty("html")
            private String html;
            @JsonProperty("photos")
            private String photos;
            @JsonProperty("likes")
            private String likes;
            @JsonProperty("portfolio")
            private String portfolio;
            @JsonProperty("following")
            private String following;
            @JsonProperty("followers")
            private String followers;
        }

        @NoArgsConstructor
        @Data
        public static class ProfileImageDTO {
            /**
             * small : https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32
             * medium : https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64
             * large : https://images.unsplash.com/profile-1600206400067-ef9dc8ec33aaimage?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128
             */

            @JsonProperty("small")
            private String small;
            @JsonProperty("medium")
            private String medium;
            @JsonProperty("large")
            private String large;
        }
    }
}
