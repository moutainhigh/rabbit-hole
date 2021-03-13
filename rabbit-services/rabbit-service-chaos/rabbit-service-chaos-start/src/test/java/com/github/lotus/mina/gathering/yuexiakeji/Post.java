package com.github.lotus.mina.gathering.yuexiakeji;

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
     * id : 2264
     * date : 2021-01-18 20:28:15
     * time : 2021年01月18日
     * week : 星期一
     * status : publish
     * title : {"rendered":"可爱的手绘壁纸精选"}
     * author : {"id":3,"name":"左","gender":1,"status":"follow","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/TYPuwz3zUppr51yAwjicaJg3jpcuYkVUeBrXOukbLngPFnr1ibWyVq7bqfvU9LicDPNHK5ehgc6eJumtTcEPOZnVA/132","description":"这个家伙太懒，连个签名都没有","honour":[]}
     * categories : [{"id":4,"name":"插画精选","description":"","cover":"https://img.yuexiakeji.com/wp-content/uploads/2020/12/2020122509085128.jpg"}]
     * tags : [{"id":27,"name":"可爱","description":"","cover":""},{"id":36,"name":"手绘","description":"","cover":""},{"id":70,"name":"插画","description":"","cover":""}]
     * color : []
     * favs : 13
     * likes : 0
     * isfav : false
     * islike : false
     * we_video_ad : false
     * video_adunit : adunit-8388cd68fe54ecc4
     * downs : 280
     * wallpaper : [{"id":2265,"meta":{"width":1080,"height":2339},"thumb":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245191.jpg?x-oss-process=image/resize,m_fill,w_360,h_640/format,webp","large":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245191.jpg?x-oss-process=image/resize,w_1080/format,webp","cover":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245191.jpg?x-oss-process=image/resize,m_fill,w_750,h_1134/format,webp","full":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245191.jpg?x-oss-process=image/resize,m_lfit,w_3000,h_3000/quality,q_90/format,jpg"},{"id":2266,"meta":{"width":1080,"height":2339},"thumb":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245243.jpg?x-oss-process=image/resize,m_fill,w_360,h_640/format,webp","large":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245243.jpg?x-oss-process=image/resize,w_1080/format,webp","cover":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245243.jpg?x-oss-process=image/resize,m_fill,w_750,h_1134/format,webp","full":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245243.jpg?x-oss-process=image/resize,m_lfit,w_3000,h_3000/quality,q_90/format,jpg"},{"id":2267,"meta":{"width":1080,"height":2339},"thumb":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245389.jpg?x-oss-process=image/resize,m_fill,w_360,h_640/format,webp","large":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245389.jpg?x-oss-process=image/resize,w_1080/format,webp","cover":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245389.jpg?x-oss-process=image/resize,m_fill,w_750,h_1134/format,webp","full":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245389.jpg?x-oss-process=image/resize,m_lfit,w_3000,h_3000/quality,q_90/format,jpg"},{"id":2268,"meta":{"width":1080,"height":2339},"thumb":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245282.jpg?x-oss-process=image/resize,m_fill,w_360,h_640/format,webp","large":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245282.jpg?x-oss-process=image/resize,w_1080/format,webp","cover":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245282.jpg?x-oss-process=image/resize,m_fill,w_750,h_1134/format,webp","full":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245282.jpg?x-oss-process=image/resize,m_lfit,w_3000,h_3000/quality,q_90/format,jpg"},{"id":2269,"meta":{"width":1080,"height":2339},"thumb":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245495.jpg?x-oss-process=image/resize,m_fill,w_360,h_640/format,webp","large":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245495.jpg?x-oss-process=image/resize,w_1080/format,webp","cover":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245495.jpg?x-oss-process=image/resize,m_fill,w_750,h_1134/format,webp","full":"https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245495.jpg?x-oss-process=image/resize,m_lfit,w_3000,h_3000/quality,q_90/format,jpg"}]
     */

    private Integer id;
    private String date;
    private String time;
    private String week;
    private String status;
    private TitleDTO title;
    private AuthorDTO author;
    private List<CategoriesDTO> categories;
    private List<TagsDTO> tags;
    private List<?> color;
    private Integer favs;
    private Integer likes;
    private Boolean isfav;
    private Boolean islike;
    private Boolean we_video_ad;
    private String video_adunit;
    private Integer downs;
    private List<WallpaperDTO> wallpaper;

    @NoArgsConstructor
    @Data
    public static class TitleDTO {
        /**
         * rendered : 可爱的手绘壁纸精选
         */

        private String rendered;
    }

    @NoArgsConstructor
    @Data
    public static class AuthorDTO {
        /**
         * id : 3
         * name : 左
         * gender : 1
         * status : follow
         * avatar : https://thirdwx.qlogo.cn/mmopen/vi_32/TYPuwz3zUppr51yAwjicaJg3jpcuYkVUeBrXOukbLngPFnr1ibWyVq7bqfvU9LicDPNHK5ehgc6eJumtTcEPOZnVA/132
         * description : 这个家伙太懒，连个签名都没有
         * honour : []
         */

        private Integer id;
        private String name;
        private Integer gender;
        private String status;
        private String avatar;
        private String description;
        private List<?> honour;
    }

    @NoArgsConstructor
    @Data
    public static class CategoriesDTO {
        /**
         * id : 4
         * name : 插画精选
         * description :
         * cover : https://img.yuexiakeji.com/wp-content/uploads/2020/12/2020122509085128.jpg
         */

        private Integer id;
        private String name;
        private String description;
        private String cover;
    }

    @NoArgsConstructor
    @Data
    public static class TagsDTO {
        /**
         * id : 27
         * name : 可爱
         * description :
         * cover :
         */

        private Integer id;
        private String name;
        private String description;
        private String cover;
    }

    @NoArgsConstructor
    @Data
    public static class WallpaperDTO {
        /**
         * id : 2265
         * meta : {"width":1080,"height":2339}
         * thumb : https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245191.jpg?x-oss-process=image/resize,m_fill,w_360,h_640/format,webp
         * large : https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245191.jpg?x-oss-process=image/resize,w_1080/format,webp
         * cover : https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245191.jpg?x-oss-process=image/resize,m_fill,w_750,h_1134/format,webp
         * full : https://img.yuexiakeji.com/wp-content/uploads/2021/01/2021011820245191.jpg?x-oss-process=image/resize,m_lfit,w_3000,h_3000/quality,q_90/format,jpg
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
             * width : 1080
             * height : 2339
             */

            private Integer width;
            private Integer height;
        }
    }
}
