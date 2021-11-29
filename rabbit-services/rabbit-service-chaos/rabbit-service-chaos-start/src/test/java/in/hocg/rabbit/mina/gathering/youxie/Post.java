package in.hocg.rabbit.mina.gathering.youxie;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by hocgin on 2021/3/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@NoArgsConstructor
@Data
public class Post {

    /**
     * id : 10043
     * date : 2020-03-20T11:35:10
     * date_gmt : 2020-03-20T03:35:10
     * guid : {"rendered":"https://diy.youxie.ren/?p=10043"}
     * modified : 2020-03-20T11:35:10
     * modified_gmt : 2020-03-20T03:35:10
     * status : publish
     * title : {"rendered":"这么可爱的狗狗，你家有么？"}
     * author : 327
     * featured_media : 0
     * comment_status : open
     * ping_status : open
     * sticky : true
     * template :
     * categories : [35,340]
     * tags : []
     * color : []
     * favs : 8
     * likes : 0
     * isfav : false
     * islike : false
     * downs : 88
     * wallpaper : [{"id":0,"meta":{"width":"","height":""},"thumb":"https://img.diy.youxie.ren/uploads/16d2ee841001bfc983fb83d6d1782705.jpg?imageView2/1/w/360/h/640","large":"https://img.diy.youxie.ren/uploads/16d2ee841001bfc983fb83d6d1782705.jpg?imageView2/4/w/750","cover":"https://img.diy.youxie.ren/uploads/16d2ee841001bfc983fb83d6d1782705.jpg?imageView2/1/w/750/h/1134","full":"https://img.diy.youxie.ren/uploads/16d2ee841001bfc983fb83d6d1782705.jpg"},{"id":0,"meta":{"width":"","height":""},"thumb":"https://img.diy.youxie.ren/uploads/0f9f11517736ce5d984a2e6d0624c3e9.jpg?imageView2/1/w/360/h/640","large":"https://img.diy.youxie.ren/uploads/0f9f11517736ce5d984a2e6d0624c3e9.jpg?imageView2/4/w/750","cover":"https://img.diy.youxie.ren/uploads/0f9f11517736ce5d984a2e6d0624c3e9.jpg?imageView2/1/w/750/h/1134","full":"https://img.diy.youxie.ren/uploads/0f9f11517736ce5d984a2e6d0624c3e9.jpg"},{"id":0,"meta":{"width":"","height":""},"thumb":"https://img.diy.youxie.ren/uploads/d4f2e0b5008ff978f40645555b1cb208.jpg?imageView2/1/w/360/h/640","large":"https://img.diy.youxie.ren/uploads/d4f2e0b5008ff978f40645555b1cb208.jpg?imageView2/4/w/750","cover":"https://img.diy.youxie.ren/uploads/d4f2e0b5008ff978f40645555b1cb208.jpg?imageView2/1/w/750/h/1134","full":"https://img.diy.youxie.ren/uploads/d4f2e0b5008ff978f40645555b1cb208.jpg"},{"id":0,"meta":{"width":"","height":""},"thumb":"https://img.diy.youxie.ren/uploads/7518af31bc6db0b1fc4ae7da051f8ec2.jpg?imageView2/1/w/360/h/640","large":"https://img.diy.youxie.ren/uploads/7518af31bc6db0b1fc4ae7da051f8ec2.jpg?imageView2/4/w/750","cover":"https://img.diy.youxie.ren/uploads/7518af31bc6db0b1fc4ae7da051f8ec2.jpg?imageView2/1/w/750/h/1134","full":"https://img.diy.youxie.ren/uploads/7518af31bc6db0b1fc4ae7da051f8ec2.jpg"},{"id":0,"meta":{"width":"","height":""},"thumb":"https://img.diy.youxie.ren/uploads/e90e2103ccd43689e85138572887483b.jpg?imageView2/1/w/360/h/640","large":"https://img.diy.youxie.ren/uploads/e90e2103ccd43689e85138572887483b.jpg?imageView2/4/w/750","cover":"https://img.diy.youxie.ren/uploads/e90e2103ccd43689e85138572887483b.jpg?imageView2/1/w/750/h/1134","full":"https://img.diy.youxie.ren/uploads/e90e2103ccd43689e85138572887483b.jpg"},{"id":0,"meta":{"width":"","height":""},"thumb":"https://img.diy.youxie.ren/uploads/4e239e92a275cbd744b2c231c0f1c188.jpg?imageView2/1/w/360/h/640","large":"https://img.diy.youxie.ren/uploads/4e239e92a275cbd744b2c231c0f1c188.jpg?imageView2/4/w/750","cover":"https://img.diy.youxie.ren/uploads/4e239e92a275cbd744b2c231c0f1c188.jpg?imageView2/1/w/750/h/1134","full":"https://img.diy.youxie.ren/uploads/4e239e92a275cbd744b2c231c0f1c188.jpg"},{"id":0,"meta":{"width":"","height":""},"thumb":"https://img.diy.youxie.ren/uploads/7cb80769f455cd199438239125222589.jpg?imageView2/1/w/360/h/640","large":"https://img.diy.youxie.ren/uploads/7cb80769f455cd199438239125222589.jpg?imageView2/4/w/750","cover":"https://img.diy.youxie.ren/uploads/7cb80769f455cd199438239125222589.jpg?imageView2/1/w/750/h/1134","full":"https://img.diy.youxie.ren/uploads/7cb80769f455cd199438239125222589.jpg"},{"id":0,"meta":{"width":"","height":""},"thumb":"https://img.diy.youxie.ren/uploads/6898c1f26abfaff4049212c52391753a.jpg?imageView2/1/w/360/h/640","large":"https://img.diy.youxie.ren/uploads/6898c1f26abfaff4049212c52391753a.jpg?imageView2/4/w/750","cover":"https://img.diy.youxie.ren/uploads/6898c1f26abfaff4049212c52391753a.jpg?imageView2/1/w/750/h/1134","full":"https://img.diy.youxie.ren/uploads/6898c1f26abfaff4049212c52391753a.jpg"},{"id":0,"meta":{"width":"","height":""},"thumb":"https://img.diy.youxie.ren/uploads/384e2488aacdc726d3bb641aa9febecd.jpg?imageView2/1/w/360/h/640","large":"https://img.diy.youxie.ren/uploads/384e2488aacdc726d3bb641aa9febecd.jpg?imageView2/4/w/750","cover":"https://img.diy.youxie.ren/uploads/384e2488aacdc726d3bb641aa9febecd.jpg?imageView2/1/w/750/h/1134","full":"https://img.diy.youxie.ren/uploads/384e2488aacdc726d3bb641aa9febecd.jpg"}]
     * _links : {"self":[{"href":"https://diy.youxie.ren/wp-json/wp/v2/posts/10043"}],"collection":[{"href":"https://diy.youxie.ren/wp-json/wp/v2/posts"}],"about":[{"href":"https://diy.youxie.ren/wp-json/wp/v2/types/post"}],"author":[{"embeddable":true,"href":"https://diy.youxie.ren/wp-json/wp/v2/users/327"}],"replies":[{"embeddable":true,"href":"https://diy.youxie.ren/wp-json/wp/v2/comments?post=10043"}],"version-history":[{"count":0,"href":"https://diy.youxie.ren/wp-json/wp/v2/posts/10043/revisions"}],"wp:attachment":[{"href":"https://diy.youxie.ren/wp-json/wp/v2/media?parent=10043"}],"wp:term":[{"taxonomy":"category","embeddable":true,"href":"https://diy.youxie.ren/wp-json/wp/v2/categories?post=10043"},{"taxonomy":"post_tag","embeddable":true,"href":"https://diy.youxie.ren/wp-json/wp/v2/tags?post=10043"},{"taxonomy":"color","embeddable":true,"href":"https://diy.youxie.ren/wp-json/wp/v2/color?post=10043"}],"curies":[{"name":"wp","href":"https://api.w.org/{rel}","templated":true}]}
     */

    private Integer id;
    private String date;
    private String date_gmt;
    private GuidDTO guid;
    private String modified;
    private String modified_gmt;
    private String status;
    private TitleDTO title;
    private Integer author;
    private Integer featured_media;
    private String comment_status;
    private String ping_status;
    private Boolean sticky;
    private String template;
    private List<Integer> categories;
    private List<?> tags;
    private List<?> color;
    private Integer favs;
    private Integer likes;
    private Boolean isfav;
    private Boolean islike;
    private Integer downs;
    private List<WallpaperDTO> wallpaper;
    private LinksDTO _links;

    @NoArgsConstructor
    @Data
    public static class GuidDTO {
        /**
         * rendered : https://diy.youxie.ren/?p=10043
         */

        private String rendered;
    }

    @NoArgsConstructor
    @Data
    public static class TitleDTO {
        /**
         * rendered : 这么可爱的狗狗，你家有么？
         */

        private String rendered;
    }

    @NoArgsConstructor
    @Data
    public static class  Categories {
        private String name;
    }

    // FIXME generate failure  field _$WpTerm97
// FIXME generate failure  field _$WpAttachment120
    @NoArgsConstructor
    @Data
    public static class LinksDTO {
        private List<SelfDTO> self;
        private List<CollectionDTO> collection;
        private List<AboutDTO> about;
        private List<AuthorDTO> author;
        private List<RepliesDTO> replies;
        @JSONField(name = "version-history")
        private List<VersionhistoryDTO> versionhistory;
        private List<CuriesDTO> curies;

        @NoArgsConstructor
        @Data
        public static class SelfDTO {
            /**
             * href : https://diy.youxie.ren/wp-json/wp/v2/posts/10043
             */

            private String href;
        }

        @NoArgsConstructor
        @Data
        public static class CollectionDTO {
            /**
             * href : https://diy.youxie.ren/wp-json/wp/v2/posts
             */

            private String href;
        }

        @NoArgsConstructor
        @Data
        public static class AboutDTO {
            /**
             * href : https://diy.youxie.ren/wp-json/wp/v2/types/post
             */

            private String href;
        }

        @NoArgsConstructor
        @Data
        public static class AuthorDTO {
            /**
             * embeddable : true
             * href : https://diy.youxie.ren/wp-json/wp/v2/users/327
             */

            private Boolean embeddable;
            private String href;
        }

        @NoArgsConstructor
        @Data
        public static class RepliesDTO {
            /**
             * embeddable : true
             * href : https://diy.youxie.ren/wp-json/wp/v2/comments?post=10043
             */

            private Boolean embeddable;
            private String href;
        }

        @NoArgsConstructor
        @Data
        public static class VersionhistoryDTO {
            /**
             * count : 0
             * href : https://diy.youxie.ren/wp-json/wp/v2/posts/10043/revisions
             */

            private Integer count;
            private String href;
        }


        @NoArgsConstructor
        @Data
        public static class CuriesDTO {
            /**
             * name : wp
             * href : https://api.w.org/{rel}
             * templated : true
             */

            private String name;
            private String href;
            private Boolean templated;
        }
    }

    @NoArgsConstructor
    @Data
    public static class WallpaperDTO {
        /**
         * id : 0
         * meta : {"width":"","height":""}
         * thumb : https://img.diy.youxie.ren/uploads/16d2ee841001bfc983fb83d6d1782705.jpg?imageView2/1/w/360/h/640
         * large : https://img.diy.youxie.ren/uploads/16d2ee841001bfc983fb83d6d1782705.jpg?imageView2/4/w/750
         * cover : https://img.diy.youxie.ren/uploads/16d2ee841001bfc983fb83d6d1782705.jpg?imageView2/1/w/750/h/1134
         * full : https://img.diy.youxie.ren/uploads/16d2ee841001bfc983fb83d6d1782705.jpg
         */

        private Integer id;
        private MetaDTO meta;
        private String thumb;
        private String large;
        private String cover;
        private String full;

        @NoArgsConstructor
        @Data
        public static class MetaDTO {
            /**
             * width :
             * height :
             */

            private String width;
            private String height;
        }
    }
}
