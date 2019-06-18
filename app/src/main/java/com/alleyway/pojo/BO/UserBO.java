package com.alleyway.pojo.BO;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UserBO {


    /**
     * content : {"user_status":"0","user_email":null,"user_head":"1558918206630.PNG","design_collect":[],"user_name":"5号教师","user_career":null,"course_collect":[17,18],"course_size":5,"JSESSIONID":"970A90FB535ED5A601950AD41E22F036","user_gender":null,"user_city":null,"production_like":[126,121,45,43,42,44],"production_collect":[121,45,44],"user_birthday":null,"discuss_like":[],"course_like":[17,18],"history_time":"1560838516645","design_like":[],"attention":[26,22],"id":22}
     * code : 0
     */

    private ContentBean content;
    private int code = 1;

    public static UserBO objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), UserBO.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserBO{" +
                "content=" + content +
                ", code=" + code +
                '}';
    }

    public static class ContentBean {
        /**
         * user_status : 0
         * user_email : null
         * user_head : 1558918206630.PNG
         * design_collect : []
         * user_name : 5号教师
         * user_career : null
         * course_collect : [17,18]
         * course_size : 5
         * JSESSIONID : 970A90FB535ED5A601950AD41E22F036
         * user_gender : null
         * user_city : null
         * production_like : [126,121,45,43,42,44]
         * production_collect : [121,45,44]
         * user_birthday : null
         * discuss_like : []
         * course_like : [17,18]
         * history_time : 1560838516645
         * design_like : []
         * attention : [26,22]
         * id : 22
         */

        private String user_status;
        private Object user_email;
        private String user_head;
        private String user_name;
        private Object user_career;
        private int course_size;
        private String JSESSIONID;
        private Object user_gender;
        private Object user_city;
        private Object user_birthday;
        private String history_time;
        private int id;
        private List<?> design_collect;
        private List<Integer> course_collect;
        private List<Integer> production_like;
        private List<Integer> production_collect;
        private List<?> discuss_like;
        private List<Integer> course_like;
        private List<?> design_like;
        private List<Integer> attention;

        public static ContentBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), ContentBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public Object getUser_email() {
            return user_email;
        }

        public void setUser_email(Object user_email) {
            this.user_email = user_email;
        }

        public String getUser_head() {
            return user_head;
        }

        public void setUser_head(String user_head) {
            this.user_head = user_head;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public Object getUser_career() {
            return user_career;
        }

        public void setUser_career(Object user_career) {
            this.user_career = user_career;
        }

        public int getCourse_size() {
            return course_size;
        }

        public void setCourse_size(int course_size) {
            this.course_size = course_size;
        }

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }

        public Object getUser_gender() {
            return user_gender;
        }

        public void setUser_gender(Object user_gender) {
            this.user_gender = user_gender;
        }

        public Object getUser_city() {
            return user_city;
        }

        public void setUser_city(Object user_city) {
            this.user_city = user_city;
        }

        public Object getUser_birthday() {
            return user_birthday;
        }

        public void setUser_birthday(Object user_birthday) {
            this.user_birthday = user_birthday;
        }

        public String getHistory_time() {
            return history_time;
        }

        public void setHistory_time(String history_time) {
            this.history_time = history_time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<?> getDesign_collect() {
            return design_collect;
        }

        public void setDesign_collect(List<?> design_collect) {
            this.design_collect = design_collect;
        }

        public List<Integer> getCourse_collect() {
            return course_collect;
        }

        public void setCourse_collect(List<Integer> course_collect) {
            this.course_collect = course_collect;
        }

        public List<Integer> getProduction_like() {
            return production_like;
        }

        public void setProduction_like(List<Integer> production_like) {
            this.production_like = production_like;
        }

        public List<Integer> getProduction_collect() {
            return production_collect;
        }

        public void setProduction_collect(List<Integer> production_collect) {
            this.production_collect = production_collect;
        }

        public List<?> getDiscuss_like() {
            return discuss_like;
        }

        public void setDiscuss_like(List<?> discuss_like) {
            this.discuss_like = discuss_like;
        }

        public List<Integer> getCourse_like() {
            return course_like;
        }

        public void setCourse_like(List<Integer> course_like) {
            this.course_like = course_like;
        }

        public List<?> getDesign_like() {
            return design_like;
        }

        public void setDesign_like(List<?> design_like) {
            this.design_like = design_like;
        }

        public List<Integer> getAttention() {
            return attention;
        }

        public void setAttention(List<Integer> attention) {
            this.attention = attention;
        }

        @Override
        public String toString() {
            return "ContentBean{" +
                    "user_status='" + user_status + '\'' +
                    ", user_email=" + user_email +
                    ", user_head='" + user_head + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", user_career=" + user_career +
                    ", course_size=" + course_size +
                    ", JSESSIONID='" + JSESSIONID + '\'' +
                    ", user_gender=" + user_gender +
                    ", user_city=" + user_city +
                    ", user_birthday=" + user_birthday +
                    ", history_time='" + history_time + '\'' +
                    ", id=" + id +
                    ", design_collect=" + design_collect +
                    ", course_collect=" + course_collect +
                    ", production_like=" + production_like +
                    ", production_collect=" + production_collect +
                    ", discuss_like=" + discuss_like +
                    ", course_like=" + course_like +
                    ", design_like=" + design_like +
                    ", attention=" + attention +
                    '}';
        }
    }
}
